package services.services_impl;

import custom_exceptions.DomainConstraintsViolationException;
import dao.ForesterDAO;
import dao.SupplyDAO;
import dao.WoodDAO;
import domain.*;
import javassist.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import services.SupplyService;
import util.ApplicationContextProvider;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
@Transactional
public class SupplyServiceImpl extends GenericServiceImpl<Supply> implements SupplyService {

    private SupplyDAO supplyDAO;
    private WoodDAO woodDAO;
    private ForesterDAO foresterDAO;

    public SupplyServiceImpl() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        supplyDAO = (SupplyDAO) applicationContext.getBean("supplyDAO");
        foresterDAO = (ForesterDAO) applicationContext.getBean("foresterDAO");
        woodDAO = (WoodDAO) applicationContext.getBean("woodDAO");
        setGenericDAO(supplyDAO);
    }

    @Override
    public List<Supply> getSupplyByDate(Date date) throws DomainConstraintsViolationException {
        return supplyDAO.getSupplyByDate(date);
    }

    @Override
    public List<Supply> getSupplyByForester(int foresterId) throws NotFoundException {

        User user = foresterDAO.getById(foresterId);
        if ( user == null || ! (user instanceof Forester)) {
            throw new NotFoundException("Failed to found forester with id " + foresterId);
        }

        return supplyDAO.getSupplyByForester((Forester)user);
    }

    @Override
    public List<Supply> getSupplyByWood(int woodId) throws NotFoundException {

        Wood wood = woodDAO.getById(woodId);
        if (wood == null) {
            throw new NotFoundException("Failed to found wood with id " + woodId);
        }

        return supplyDAO.getSupplyByWood(wood);
    }

    @Override
    public boolean rollbackSupply(int supplyId) throws NotFoundException {

        boolean result = true;

        Supply supply = supplyDAO.getById(supplyId);
        if (supply == null) {
            throw new NotFoundException("Failed to find supply with id " + supplyId );
        }

        for (SupplyContent sc : supply.getSupplyContent()) {
            Wood wood = sc.getWood();
            if (wood.getWoodCount() - sc.getCount() < 0) {
                result = false;
            }
        }

        if (result) {

            for (SupplyContent sc : supply.getSupplyContent()) {
                Wood wood = sc.getWood();
                try {
                    wood.setWoodCount(wood.getWoodCount() - sc.getCount());
                } catch (DomainConstraintsViolationException e) {
                    //this should never happen
                    e.printStackTrace();
                }
                woodDAO.save(wood);
            }
            supplyDAO.remove(supply);
            supply.getForester().getSupplyList().remove(supply);
            foresterDAO.save(supply.getForester());
        }
        return result;
    }
}

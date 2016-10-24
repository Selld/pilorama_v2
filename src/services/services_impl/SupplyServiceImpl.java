package services.services_impl;

import custom_exceptions.DomainConstraintsViolationException;
import dao.ForesterDAO;
import dao.SupplyDAO;
import dao.WoodDAO;
import domain.Forester;
import domain.Supply;
import domain.SupplyContent;
import domain.Wood;
import javassist.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.SupplyService;

import java.util.Date;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class SupplyServiceImpl extends GenericServiceImpl implements SupplyService {

    private SupplyDAO supplyDAO;
    private WoodDAO woodDAO;
    private ForesterDAO foresterDAO;

    public SupplyServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        supplyDAO = (SupplyDAO) context.getBean("supplyDAO");
        foresterDAO = (ForesterDAO) context.getBean("foresterDAO");
        woodDAO = (WoodDAO) context.getBean("woodDAO");
    }

    @Override
    public List<Supply> getSupplyByDate(Date date) throws DomainConstraintsViolationException {
        return supplyDAO.getSupplyByDate(date);
    }

    @Override
    public List<Supply> getSupplyByForester(int foresterId) throws NotFoundException {

        Forester forester = foresterDAO.getById(foresterId);
        if (forester == null) {
            throw new NotFoundException("Failed to found forester with id " + foresterId);
        }

        return supplyDAO.getSupplyByForester(foresterId);
    }

    @Override
    public List<Supply> getSupplyByWood(int woodId) throws NotFoundException {
        Wood wood = woodDAO.getById(woodId);
        if (wood == null) {
            throw new NotFoundException("Failed to found wood with id " + woodId);
        }

        return supplyDAO.getSupplyByWood(woodId);
    }

    @Override
    public boolean rollbackSupply(int supplyId) throws NotFoundException {

        Supply supply = supplyDAO.getById(supplyId);
        if (supply == null) {
            throw new NotFoundException("Failed to find supply with id " + supplyId );
        }

        for (SupplyContent sc : supply.getSupplyContent()) {
            Wood wood = sc.getWood();
            if (wood.getBalance() - sc.getCount() < 0) {
                return false;
            }
        }

        for (SupplyContent sc : supply.getSupplyContent()) {
            Wood wood = sc.getWood();
            try {
                wood.setBalance(wood.getBalance() - sc.getCount());
            } catch (DomainConstraintsViolationException e) {
                //this should never happen
                e.printStackTrace();
            }
            woodDAO.save(wood);
        }
        supplyDAO.remove(supply);
        supply.getForester().getSupplyList().remove(supply);
        foresterDAO.save(supply.getForester());

        return true;
    }
}

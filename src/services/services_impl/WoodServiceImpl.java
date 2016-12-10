package services.services_impl;

import custom_exceptions.AlreadyExistsException;
import custom_exceptions.DomainConstraintsViolationException;
import dao.ProductDAO;
import dao.WoodDAO;
import domain.Product;
import domain.ProductMaterial;
import domain.Wood;
import domain.WoodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import services.WoodService;
import util.ApplicationContextProvider;

import javax.persistence.EntityTransaction;
import java.util.List;

@Transactional
public class WoodServiceImpl extends GenericServiceImpl<Wood> implements WoodService {

    private WoodDAO woodDAO;
    private ProductDAO productDAO;

    public WoodServiceImpl() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        woodDAO = (WoodDAO) applicationContext.getBean("woodDAO");
        productDAO = (ProductDAO) applicationContext.getBean("productDAO");
        super.setGenericDAO(woodDAO);
    }

    @Override
    public Wood getByName(String name) throws DomainConstraintsViolationException {
        return woodDAO.getWoodByName(name);
    }

    @Override
    public List<Wood> getMostPopularWood(int count) {
        return woodDAO.getMostPopularWood(count);
    }

    @Override
    public void addNewWood(Wood wood) throws AlreadyExistsException {
        if (woodDAO.woodExists(wood)) {
            throw new AlreadyExistsException("Such wood is already exists " + wood.getName());
        }
        woodDAO.save(wood);
    }

    @Override
    public List<Wood> getWoodsByType(WoodType type) {
        return woodDAO.getWoodByType(type);
    }

    @Override
    public void remove(Wood wood) {

        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            List<ProductMaterial> productMaterials = product.getMaterials();

            for (ProductMaterial pm : productMaterials) {
                if (pm.getWood().equals(wood)) {
                    wood.setIsBuying(false);
                    woodDAO.save(wood);
                }
            }
        }
        woodDAO.remove(wood);
    }
}

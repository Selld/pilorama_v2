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
import services.WoodService;

import javax.persistence.EntityTransaction;
import java.util.List;

public class WoodServiceImpl extends GenericServiceImpl<Wood> implements WoodService {

    private WoodDAO woodDAO;
    private ProductDAO productDAO;

    public WoodServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        woodDAO = (WoodDAO) context.getBean("woodDAO");
        productDAO = (ProductDAO) context.getBean("productDAO");
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

        EntityTransaction tr = entityManager.getTransaction();

        try {
            tr.begin();
            if (woodDAO.woodExists(wood)) {
                throw new AlreadyExistsException("Such wood is already exists " + wood.getName());
            }
            woodDAO.save(wood);

            tr.commit();
        } catch (AlreadyExistsException e) {
            tr.commit();
            throw e;
        }


    }

    @Override
    public List<Wood> getWoodsByType(WoodType type) {
        return woodDAO.getWoodByType(type);
    }

    @Override
    public void remove(Wood wood) {

        EntityTransaction tr = entityManager.getTransaction();

        try {
            tr.begin();
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
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw e;
        }

        woodDAO.remove(wood);
    }
}

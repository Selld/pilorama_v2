package services.services_impl;

import custom_exceptions.AlreadyExistsException;
import custom_exceptions.DomainConstraintsViolationException;
import dao.ProductDAO;
import dao.WoodDAO;
import domain.Product;
import domain.ProductMaterials;
import domain.Wood;
import domain.WoodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.WoodService;

import java.util.List;

public class WoodServiceImpl extends GenericServiceImpl implements WoodService {

    private WoodDAO woodDAO;
    private ProductDAO productDAO;

    public SupplyServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        woodDAO = (WoodDAO) context.getBean("woodDAO");
        productDAO = (ProductDAO) context.getBean("productDAO");
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
    public Wood remove(Wood wood) {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            List<ProductMaterials> productMaterials = product.getMaterials();

            for (ProductMaterials pm : productMaterials) {
                if (pm.getWood().equals(wood)) {
                    wood.setIsBuying(false);
                    woodDAO.save(true);
                    return wood;
                }
            }
        }
        woodDAO.remove(wood);
        return wood;
    }
}

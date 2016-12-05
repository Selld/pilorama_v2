package services.services_impl;

import custom_exceptions.DomainConstraintsViolationException;
import dao.CustomerDAO;
import custom_exceptions.InvalidUserException;
import dao.ProductDAO;
import dao.SaleDAO;
import domain.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.SaleService;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class SaleServiceImpl extends GenericServiceImpl<Sale> implements SaleService {

    private static final long SALE_EXPIRE_TTL = 14;

    private SaleDAO saleDAO;
    private ProductDAO productDAO;
    private CustomerDAO customerDAO;

    public SaleServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        saleDAO = (SaleDAO) context.getBean("saleDAO");
        productDAO = (ProductDAO) context.getBean("productDAO");
        customerDAO = (CustomerDAO) context.getBean("customerDAO");
        setGenericDAO(saleDAO);
    }

    @Override
    public List<Sale> getSaleByCustomer(Customer customer) throws DomainConstraintsViolationException {
        return saleDAO.getSaleByCustomer(customer);
    }

    @Override
    public List<SaleContent> getSaleContent(Sale sale) {
        return sale.getSaleContent();
    }

    @Override
    public List<Sale> getSalesByProduct(Product product) throws DomainConstraintsViolationException {
        return saleDAO.getSalesByProduct(product);
    }

    @Override
    public boolean makeSale(List<SaleContent> saleContents, int customerId)
            throws DomainConstraintsViolationException, InvalidUserException
    {
        EntityTransaction tr = entityManager.getTransaction();

        try {
            tr.begin();

            User user = customerDAO.getById(customerId);
            if ( (user == null) || ! (user instanceof Customer)) {
                throw new InvalidUserException("Customer with id " + customerId + " does not exists");
            }
            Customer customer = (Customer)user;

            Sale sale = new Sale(saleContents);

            for (SaleContent sc : saleContents) {
                Product product = sc.getProduct();
                if (product.getProductCount() - sc.getCount() < 0) {
                    throw new DomainConstraintsViolationException(
                            "Not enough products of type " + product.getName() + " needed "
                                    + sc.getCount() +
                                    "available " + product.getProductCount());
                }
            }
            for (SaleContent sc : saleContents) {
                Product product = sc.getProduct();
                product.setProductCount(product.getProductCount() - sc.getCount());
                productDAO.save(product);
            }

            saleDAO.save(sale);
            customer.addSale(sale);
            customerDAO.save(customer);

            tr.commit();
        } catch (DomainConstraintsViolationException | InvalidUserException e) {
            tr.rollback();
            throw e;
        }
    }

    @Override
    public boolean rollbackSale(Sale sale) {

        EntityTransaction tr = entityManager.getTransaction();
        boolean result = true;

        try {
            tr.begin();

            Date currentDate = new Date();
            long msSinceSale = currentDate.getTime() - sale.getSaleDate().getTime();
            long daysSinceSale = msSinceSale / ( 1000 * 24 * 3600 );

            if (daysSinceSale > SALE_EXPIRE_TTL) {
                result = false;
            }

            if (result) {
                for (SaleContent sc : sale.getSaleContent() ) {
                    Product product = sc.getProduct();
                    product.setProductCount(product.getProductCount() + sc.getCount());
                    productDAO.save(product);
                }
                saleDAO.remove(sale);
            }

            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw e;
        }

        return result;
    }
}

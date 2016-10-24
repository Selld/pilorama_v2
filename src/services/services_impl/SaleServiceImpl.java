package services.services_impl;

import custom_exceptions.DomainConstraintsViolationException;
import dao.CustomerDAO;
import custom_exceptions.InvalidUserException;
import dao.ProductDAO;
import dao.SaleDAO;
import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleContent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.SaleService;

import java.util.Date;
import java.util.List;

public class SaleServiceImpl extends GenericServiceImpl implements SaleService {

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

    }

    @Override
    public List<Sale> getSaleByCustomer(Customer customer) throws DomainConstraintsViolationException {
        return saleDAO.getSaleByCustomer();
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

        Customer customer = customerDAO.getById(customerId);
        if (customer == null) {
            throw new InvalidUserException("Customer with id " + customerId + " does not exists");
        }

        Sale sale = new Sale(saleContents);

        for (SaleContent sc : saleContents) {
            Product product = sc.getProduct();
            if (product.getBalance() - sc.getCount() < 0) {
                throw new DomainConstraintsViolationException(
                        "Not enough products of type " + product.getName() + " needed " + sc.getCount() +
                        "available " + product.getBalance());
            }
        }
        for (SaleContent sc : saleContents) {
            Product product = sc.getProduct();
            product.setBalance(product.getBalance() - sc.getCount());
            productDAO.save(product);
        }

        saleDAO.save(sale);
        customer.addSale(sale);
        customerDAO.save(sale);
    }

    @Override
    public boolean rollbackSale(Sale sale) {

        Date currentDate = new Date();
        long msSinceSale = currentDate.getTime() - sale.getSaleDate().getTime();
        long daysSinceSale = msSinceSale / ( 1000 * 24 * 3600 );

        if (daysSinceSale > SALE_EXPIRE_TTL) {
            return false;
        }

        for (SaleContent sc : sale.getSaleContent() ) {
            Product product = sc.getProduct();
            product.setBalance(product.getBalance() + sc.getCount());
            productDAO.save(product);
        }

        saleDAO.remove(sale);

        return true;
    }
}

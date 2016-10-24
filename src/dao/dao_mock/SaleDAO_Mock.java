package dao.dao_mock;

import custom_exceptions.DomainConstraintsViolationException;
import dao.SaleDAO;
import domain.Product;
import domain.Sale;
import domain.SaleContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class SaleDAO_Mock extends GenericDAO_Mock implements SaleDAO {
    @Override
    public List<Sale> getSaleByCustomer() throws DomainConstraintsViolationException {
        List<SaleContent> sc = new ArrayList<>();
        sc.add(new SaleContent(new Product(new ArrayList<>(), "test", 1), 1));

        Sale sale = new Sale(sc);
        List<Sale> sales = new ArrayList<>();
        sales.add(sale);
        return sales;
    }

    @Override
    public List<Sale> getSalesByProduct(Product product) throws DomainConstraintsViolationException {
        List<SaleContent> sc = new ArrayList<>();
        sc.add(new SaleContent(product, 1));

        Sale sale = new Sale(sc);
        List<Sale> sales = new ArrayList<>();
        sales.add(sale);
        return sales;
    }
}

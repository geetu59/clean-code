package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomerBOTest {

    private CustomerBO customerBO = new CustomerBOImpl();

    /*Refactor:
     * 1. Since Amount is all we need and is what we are asserting, so extract amount and make method of rest of the code.
     * Making a list, to make it generic, so that any no of amounts can be taken
     * 2. Rename expected and actual and move assert to different method*/
    @Test
    public void testCustomerProductSum_TwoProductsSameCurrencies() throws DifferentCurrenciesException {
        AmountImpl amount1 = new AmountImpl(new BigDecimal("5.0"), Currency.EURO);
        AmountImpl amount2 = new AmountImpl(new BigDecimal("6.0"), Currency.EURO);
        Amount[] amounts = {amount1, amount2};
        List<Product> products = getProducts(amounts);

        Amount expectedAmount = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);

        assertAmount(expectedAmount, (AmountImpl) customerBO.getCustomerProductsSum(products));
    }

    private static void assertAmount(Amount actualAmount, AmountImpl expectedAmount) {
        assertEquals(expectedAmount.getCurrency(), actualAmount.getCurrency());
        assertEquals(expectedAmount.getValue(), actualAmount.getValue());
    }

    //Since we have nothing to do with products info, hence changing amount info, adding it and returning list of products
    private static List<Product> getProducts(Amount[] amounts) {
        return Arrays.stream(amounts)
                .map(amount ->
                        new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, amount))
                .collect(Collectors.toList());
    }

    @Test
    public void testCustomerProductSum1() {

        List<Product> products = new ArrayList<Product>();

        products.add(new ProductImpl(100, "Product 15",
                ProductType.BANK_GUARANTEE,
                new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE)));

        products.add(
                new ProductImpl(120, "Product 20", ProductType.BANK_GUARANTEE,
                        new AmountImpl(new BigDecimal("6.0"), Currency.EURO)));

        @SuppressWarnings("unused")
        Amount temp = null;

        try {
            temp = customerBO.getCustomerProductsSum(products);
            fail("DifferentCurrenciesException is expected");
        } catch (DifferentCurrenciesException e) {
        }
    }

    @Test
    public void testCustomerProductSum2() {

        List<Product> products = new ArrayList<Product>();

        Amount temp = null;

        try {
            temp = customerBO.getCustomerProductsSum(products);
        } catch (DifferentCurrenciesException e) {
        }
        assertEquals(Currency.EURO, temp.getCurrency());
        assertEquals(BigDecimal.ZERO, temp.getValue());
    }

}
package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

import java.math.BigDecimal;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    /*Refactor:
     * 1. This method has 3 parts, a. no products are there b. diff currency is there c. return totalAmount
     * a part is fine.
     * 2. Rename temp to totalAmount
     * 3. Remove comments
     * 4. For b and c, use java streams*/
    @Override
    public Amount getCustomerProductsSum(List<Product> products) throws DifferentCurrenciesException {
        BigDecimal totalAmount = BigDecimal.ZERO;

        if (products.isEmpty()) return new AmountImpl(totalAmount, Currency.EURO);

        if (!doAllProductsHaveSameCurrency(products)) {
            throw new DifferentCurrenciesException();
        }


        return getAmount(products);
    }

    private static AmountImpl getAmount(List<Product> products) {
        BigDecimal totalAmount;
        Currency firstProductCurrency = products.get(0).getAmount()
                .getCurrency();

        totalAmount = products.stream().map(product ->
                        product.getAmount().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AmountImpl(totalAmount, firstProductCurrency);
    }

    private static boolean doAllProductsHaveSameCurrency(List<Product> products) {
        Currency firstProductCurrency = products.get(0).getAmount()
                .getCurrency();

        return products.stream().map(product -> product.getAmount().getCurrency())
                .allMatch(currency -> currency.equals(firstProductCurrency));
    }
}
package com.example.shoop.session;

import com.example.shoop.model.Product;

import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return ( o1.getValue().compareTo( o2.getValue()));
    }

}
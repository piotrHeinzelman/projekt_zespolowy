package com.example.shoop.session;

import com.example.shoop.model.Product;

import java.util.Comparator;

public class NameComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return ( o1.getName().compareTo( o2.getName()));
    }

}
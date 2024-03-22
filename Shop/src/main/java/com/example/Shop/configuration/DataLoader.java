package com.example.Shop.configuration;

import com.example.Shop.model.Product;
import com.example.Shop.repo.ProductRepo;
import com.example.Shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class DataLoader implements CommandLineRunner {

    private final ProductService repository;

    @Autowired
    public DataLoader( ProductService repository ) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Product P1=new Product("GByte_RTX_3080", "Karta graficzna Gigabyte RTX 3080");
        P1=repository.getBySKU( P1.getSKU() ).orElse( P1 ); if (P1.getId()==null) repository.save(P1);

        Product P2=new Product("GByte_RTX_3080", "Karta graficzna Gigabyte RTX 3080");
        P2=repository.getBySKU( P2.getSKU() ).orElse( P2 ); if (P2.getId()==null) repository.save(P2);

        Product P3=new Product("GByte_RTX_3080", "Karta graficzna Gigabyte RTX 3080");
        P3=repository.getBySKU( P3.getSKU() ).orElse( P3 ); if (P3.getId()==null) repository.save(P3);

        Product P4=new Product("GByte_RTX_3080", "Karta graficzna Gigabyte RTX 3080");
        P4=repository.getBySKU( P4.getSKU() ).orElse( P4 ); if (P4.getId()==null) repository.save(P4);
    }
}
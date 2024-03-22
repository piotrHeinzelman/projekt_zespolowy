package com.example.Shop;

import com.example.Shop.model.Product;
import com.example.Shop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ShopApplicationTests {

	@Autowired
	private ProductService productService;

	@Disabled
	@Test
	void contextLoads() {

		String sku="GBYTE_RTX_3070";

		Optional<Product> OProduct = productService.getBySKU(sku);
		Assertions.assertTrue( OProduct.isPresent() );

		if (OProduct.isEmpty() ) {
			System.out.println( "Brak produktu!" );
		} else {
			System.out.println( OProduct.get() );
		}



	}

}

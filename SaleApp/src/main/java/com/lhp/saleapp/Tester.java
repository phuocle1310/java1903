/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhp.saleapp;

import com.lhp.service.CategoryService;
import com.lhp.service.ProductService;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class Tester {
    public static void main(String[] args) {
//        CategoryService service = new CategoryService();
//        service.getCategory().forEach(c -> System.out.printf("%d: %s\n", c.getId(), c.getName()));
        
//        ProductService pservice = new ProductService();
//        pservice.getProduct().forEach(c -> System.out.printf("%d - %s - %.2f - %s\n",
//                c.getId(), c.getName(), c.getPrice(), c.getCategory().getName()));

          
          ProductService pservice = new ProductService();
          pservice.getProductByName("pro").forEach(c -> System.out.printf("%d - %s - %.2f - %s\n",
                c.getId(), c.getName(), c.getPrice(), c.getCategory().getName()));
          System.out.println("====== SEARCH BY PRICE");
          pservice.getProductbyPrice(new BigDecimal(10000000), new BigDecimal(20000000)).forEach(c -> System.out.printf("%d - %s - %.2f - %s\n",
                c.getId(), c.getName(), c.getPrice(), c.getCategory().getName()));
    }
}

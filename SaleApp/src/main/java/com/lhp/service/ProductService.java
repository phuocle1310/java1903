/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhp.service;

import com.lhp.config.HibernateUtils;
import com.lhp.pojo.Product;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;


/**
 *
 * @author Admin
 */
public class ProductService {
    public List<Product> getProductBase (String[] args, ProductChecker c) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root root = query.from(Product.class);
            query.select(root);
            
            query = c.getWhere(builder, query, root, args);
            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }
    
    
    public List<Product> getProductByName(String kw) {
        return getProductBase(new String[] {kw}, (builder, query, root, args) -> {
            Predicate p = builder.like(root.get("name").as(String.class), 
                        String.format("%%%s%%", args[0]));
                query = query.where(p);
                return query;
        });
    
//        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Product> query = builder.createQuery(Product.class);
//            Root root = query.from(Product.class);
//            query.select(root);
//            
//            if(kw != null && !kw.isEmpty()) {
//                Predicate p = builder.like(root.get("name").as(String.class), 
//                        String.format("%%%s%%", kw));
//                query = query.where(p);
//                }
//            Query q = session.createQuery(query);
//            return q.getResultList();
//        }
    }
    
    public List<Product> getProductbyPrice(BigDecimal from, BigDecimal to) {
        
        return getProductBase(new String[] {String.valueOf(from), 
        String.valueOf(to)},(builder, query, root, args) -> {
        Predicate p = builder.between(root.get("price").as(BigDecimal.class), 
                        new BigDecimal(args[0]), new BigDecimal(args[1]));
                query = query.where(p);
                return query;
    });
            
        
//        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Product> query = builder.createQuery(Product.class);
//            Root root = query.from(Product.class);
//            query.select(root);
//            
//            if(from != null && to != null) {
//                Predicate p = builder.between(root.get("price").as(BigDecimal.class), 
//                        from, to);
//                query = query.where(p);
//                }
//            Query q = session.createQuery(query);
//            return q.getResultList();
//        }
    }
}
interface ProductChecker {
    CriteriaQuery getWhere(CriteriaBuilder builder,CriteriaQuery query, Root root, String []args);
    }

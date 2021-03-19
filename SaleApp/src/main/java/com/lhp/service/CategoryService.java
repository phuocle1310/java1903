/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhp.service;

import com.lhp.config.HibernateUtils;
import com.lhp.pojo.Category;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class CategoryService {
    public List<Category> getCategory() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query q = session.createQuery("from Category");
            return q.getResultList();
        }
    }
}

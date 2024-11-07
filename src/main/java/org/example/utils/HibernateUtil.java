package org.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class HibernateUtil {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Failed to create sessionFactory object." + ex);
        }
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    @PreDestroy
    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

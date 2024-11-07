package org.example.repositories;

import jakarta.persistence.*;
import org.example.models.Organization;

import java.util.List;

public class OrganizationRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("organizationPU");

    public void save(Organization organization) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(organization);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Organization findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Organization.class, id);
        } finally {
            em.close();
        }
    }

    public List<Organization> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT o FROM Organization o", Organization.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Organization organization) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(organization);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Organization organization = em.find(Organization.class, id);
            if (organization != null) {
                em.remove(organization);
            } else {
                throw new EntityNotFoundException("Organization not found");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}

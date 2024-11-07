package org.example.repositories;

import jakarta.persistence.*;
import org.example.models.Address;
import org.example.models.User;

import java.util.List;

public class AddressRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("addressPU");

    public void save(Address address) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Address findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Address.class, id);
        } finally {
            em.close();
        }
    }

    public List<Address> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Address a", Address.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Address address) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(address);
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
            Address address = em.find(Address.class, id);
            if (address != null) {
                em.remove(address);
            } else {
                throw new EntityNotFoundException("Address not found");
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

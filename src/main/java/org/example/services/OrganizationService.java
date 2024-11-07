package org.example.services;

import org.example.models.Organization;
import org.example.repositories.OrganizationRepository;

import jakarta.inject.Inject;
import java.util.List;

public class OrganizationService {

    @Inject
    private OrganizationRepository organizationRepository;

    public void addOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public void updateOrganization(Organization organization){
        organizationRepository.update(organization);
    }

    public Organization getOrganizationById(Integer id) {
        return organizationRepository.findById(id);
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public void removeOrganization(Integer id) {
        organizationRepository.delete(id);
    }
}

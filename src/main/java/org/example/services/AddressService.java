package org.example.services;

import org.example.models.Address;
import org.example.models.User;
import org.example.repositories.AddressRepository;

import jakarta.inject.Inject;
import java.util.List;

public class AddressService {

    @Inject
    private AddressRepository addressRepository;

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public void updateAddress(Address address){
        addressRepository.update(address);
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public void removeAddress(Integer id) {
        addressRepository.delete(id);
    }
}

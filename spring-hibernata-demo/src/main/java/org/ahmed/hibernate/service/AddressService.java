package org.ahmed.hibernate.service;

import lombok.RequiredArgsConstructor;
import org.ahmed.hibernate.models.entitites.Address;
import org.ahmed.hibernate.repo.AddressRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepo repo;

    public List<Address> getAddresses(){
        return repo.findAll();
    }
}

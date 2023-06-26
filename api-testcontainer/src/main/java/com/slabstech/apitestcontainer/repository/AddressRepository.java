package com.slabstech.apitestcontainer.repository;

import java.util.List;

import com.slabstech.apitestcontainer.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAll();
}
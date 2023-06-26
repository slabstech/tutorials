package com.slabstech.apitestcontainer.repository;

import java.util.List;

import com.slabstech.apitestcontainer.model.DeliveryState;
import com.slabstech.apitestcontainer.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    List<User> findAllByUserNumber(String userNumber);

    List<User> findAllByDeliveryDate(String deliveryDate);

    List<User> findAllByDeliveryDateAndDeliveryState(String deliveryDate, DeliveryState deliveryState);

    List<User> findAllByDeliveryState(DeliveryState deliveryState);

}


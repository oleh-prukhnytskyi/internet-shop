package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.Address;
import com.olegpruh.internetshop.model.Customer;
import com.olegpruh.internetshop.model.DeliveryOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    Customer findByUserEmail(String user_email);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.firstName = ?2, c.lastName = ?3, c.email = ?4," +
            " c.phone = ?5, c.address = ?6, c.deliveryOption = ?7 WHERE c.id = ?1")
    void update(Long id, String firstName, String lastName, String email, String phone,
                Address address, DeliveryOption deliveryOption);
}

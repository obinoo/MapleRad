package com.VirtualCard.maplerads.Repository;

import com.VirtualCard.maplerads.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {


//    @Query("SELECT c.id\n" +
//            "FROM Customers\n" +
//            "LEFT JOIN customerCard ON c.id = customerCard.customerId\n" +
//            "WHERE customerCard.customerId IS NULL")
        @Query("SELECT c.customerId FROM Customer c")
        String findByCustomerId();


}

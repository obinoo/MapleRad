package com.VirtualCard.maplerads.Repository;

import com.VirtualCard.maplerads.Entities.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {

    // Custom query method to retrieve the latest card based on creation timestamp
    Optional<CustomerCard> findTopByOrderByCreatedAtDesc();

    @Query("SELECT c.reference FROM CustomerCard c")
    String findByReference();

    @Query("SELECT b.customerId FROM Customer b")
    String existsByCustomerId(String customerId);
}
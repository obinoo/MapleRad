package com.VirtualCard.maplerads.Repository;

import com.VirtualCard.maplerads.Entities.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FundRepository extends JpaRepository<CardTransaction, Long> {

//    @Query("SELECT cc.data.id FROM FundCard cc")
//    Optional<FundCard> findIdFromEmbedded();
}

package com.bnta.chocolate.repositories;

import com.bnta.chocolate.models.CocoaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocoaOrderRepository extends JpaRepository<CocoaOrder, Long> {
}

package edu.cesi.demo.dao;

import edu.cesi.demo.model.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderLineDao extends JpaRepository<PurchaseOrderLine,Integer> {
}

package edu.cesi.demo.dao;

import edu.cesi.demo.model.Family;
import edu.cesi.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyDao extends JpaRepository<Family,Integer> {
}

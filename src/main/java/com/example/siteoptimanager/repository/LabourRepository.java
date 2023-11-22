package com.example.siteoptimanager.repository;

import com.example.siteoptimanager.model.Labour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabourRepository extends JpaRepository<Labour, Integer> {
    // You can define custom query methods here if needed
}

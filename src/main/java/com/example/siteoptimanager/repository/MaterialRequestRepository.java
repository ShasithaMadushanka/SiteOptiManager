package com.example.siteoptimanager.repository;

import com.example.siteoptimanager.model.MaterialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRequestRepository extends JpaRepository<MaterialRequest, Integer> {

}

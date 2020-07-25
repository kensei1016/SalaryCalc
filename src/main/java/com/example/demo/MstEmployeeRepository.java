package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface MstEmployeeRepository extends JpaRepository<MstEmployee, Integer> {

//  public Optional<MstEmployee> findByEmpId(Integer emp_id);

}
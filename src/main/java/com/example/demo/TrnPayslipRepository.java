package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface TrnPayslipRepository extends JpaRepository<TrnPayslip, Integer> {

//  public List<TrnPayslip> findByYearMonth(Integer year_month);
//  public TrnPayslip findByYearMonth(Integer year_month);

  @Query("select a from TrnPayslip a where a.year_month = :year_month and a.mstEmployee.emp_id = :emp_id")
  Optional<TrnPayslip> findByYearMonth(@Param("year_month") Integer year_month, @Param("emp_id") Integer emp_id);
}
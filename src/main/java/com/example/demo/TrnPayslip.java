package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "trn_payslip")
public class TrnPayslip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer trn_payslip_id;
//  private Integer emp_id;
  @Column(name = "`year_month`")
  private Integer year_month;
  private Integer payment1;
  private Integer payment2;
  private Integer payment3;
  private Integer payment4;
  private Integer payment5;
  private Integer payment6;
  private Integer payment7;
  private Integer payment8;
  private Integer payment9;
  private Integer deduction1;
  private Integer deduction2;
  private Integer deduction3;
  private Integer deduction4;
  private Integer deduction5;
  private Integer deduction6;
  private Integer deduction7;
  private Integer deduction8;
  private Integer deduction9;
  private String update_user;
  private String update_date;
//  @Temporal(TemporalType.DATE)
//  private Date hiredAt;

  @ManyToOne
  @JoinColumn(name = "emp_id", foreignKey = @ForeignKey(name="FK_EMP_ID"))
  private MstEmployee mstEmployee;



  public MstEmployee getMstEmployee() {
    return mstEmployee;
}

  public void setMstEmployee(MstEmployee mstEmployee) {
    this.mstEmployee = mstEmployee;
  }

  public Integer getTrn_payslip_id() {
    return trn_payslip_id;
  }

  public void setTrn_payslip_id(Integer trn_payslip_id) {
    this.trn_payslip_id = trn_payslip_id;
  }

//  public Integer getEmp_id() {
//    return emp_id;
//  }
//  public void setEmp_id(Integer emp_id) {
//    this.emp_id = emp_id;
//  }
  public Integer getYear_month() {
    return year_month;
  }
  public void setYear_month(Integer year_month) {
    this.year_month = year_month;
  }
  public Integer getPayment1() {
    return payment1;
  }
  public void setPayment1(Integer payment1) {
    this.payment1 = payment1;
  }
  public Integer getPayment2() {
    return payment2;
  }
  public void setPayment2(Integer payment2) {
    this.payment2 = payment2;
  }
  public Integer getPayment3() {
    return payment3;
  }
  public void setPayment3(Integer payment3) {
    this.payment3 = payment3;
  }
  public Integer getPayment4() {
    return payment4;
  }
  public void setPayment4(Integer payment4) {
    this.payment4 = payment4;
  }
  public Integer getPayment5() {
    return payment5;
  }
  public void setPayment5(Integer payment5) {
    this.payment5 = payment5;
  }
  public Integer getPayment6() {
    return payment6;
  }
  public void setPayment6(Integer payment6) {
    this.payment6 = payment6;
  }
  public Integer getPayment7() {
    return payment7;
  }
  public void setPayment7(Integer payment7) {
    this.payment7 = payment7;
  }
  public Integer getPayment8() {
    return payment8;
  }
  public void setPayment8(Integer payment8) {
    this.payment8 = payment8;
  }
  public Integer getPayment9() {
    return payment9;
  }
  public void setPayment9(Integer payment9) {
    this.payment9 = payment9;
  }
  public Integer getDeduction1() {
    return deduction1;
  }
  public void setDeduction1(Integer deduction1) {
    this.deduction1 = deduction1;
  }
  public Integer getDeduction2() {
    return deduction2;
  }
  public void setDeduction2(Integer deduction2) {
    this.deduction2 = deduction2;
  }
  public Integer getDeduction3() {
    return deduction3;
  }
  public void setDeduction3(Integer deduction3) {
    this.deduction3 = deduction3;
  }
  public Integer getDeduction4() {
    return deduction4;
  }
  public void setDeduction4(Integer deduction4) {
    this.deduction4 = deduction4;
  }
  public Integer getDeduction5() {
    return deduction5;
  }
  public void setDeduction5(Integer deduction5) {
    this.deduction5 = deduction5;
  }
  public Integer getDeduction6() {
    return deduction6;
  }
  public void setDeduction6(Integer deduction6) {
    this.deduction6 = deduction6;
  }
  public Integer getDeduction7() {
    return deduction7;
  }
  public void setDeduction7(Integer deduction7) {
    this.deduction7 = deduction7;
  }
  public Integer getDeduction8() {
    return deduction8;
  }
  public void setDeduction8(Integer deduction8) {
    this.deduction8 = deduction8;
  }
  public Integer getDeduction9() {
    return deduction9;
  }
  public void setDeduction9(Integer deduction9) {
    this.deduction9 = deduction9;
  }
  public String getUpdate_user() {
    return update_user;
  }
  public void setUpdate_user(String update_user) {
    this.update_user = update_user;
  }
  public String getUpdate_date() {
    return update_date;
  }
  public void setUpdate_date(String update_date) {
    this.update_date = update_date;
  }
}

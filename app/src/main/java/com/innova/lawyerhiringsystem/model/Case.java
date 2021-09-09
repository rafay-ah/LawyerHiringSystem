package com.innova.lawyerhiringsystem.model;
/*
* Model/POJO class
* Represents Case Entity
* The legal case detail entered by user
* user can have multiple cases
* a case can belong to only one user*/
public class Case {
    String tittle, city, budget, statement, courttype, lawyertype,email, hiredlawyer;
    boolean isopen; // default will be true until layer is assigned

    public Case() {
    }

    public Case(String tittle, String city, String budget, String statement, String courttype, String lawyertype, String email, String hiredlawyer, boolean isopen) {
        this.tittle = tittle;
        this.city = city;
        this.budget = budget;
        this.statement = statement;
        this.courttype = courttype;
        this.lawyertype = lawyertype;
        this.email = email;
        this.hiredlawyer = hiredlawyer;
        this.isopen = isopen;
    }

    public Case(String tittle, String city, String budget, String statement, String courttype, String lawyertype, String email, boolean isopen) {
        // will be put in use to create objects locally
        this.tittle = tittle;
        this.city = city;
        this.budget = budget;
        this.statement = statement;
        this.courttype = courttype;
        this.lawyertype = lawyertype;
        this.email = email;
        this.isopen = isopen;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCourttype() {
        return courttype;
    }

    public void setCourttype(String courttype) {
        this.courttype = courttype;
    }

    public String getLawyertype() {
        return lawyertype;
    }

    public void setLawyertype(String lawyertype) {
        this.lawyertype = lawyertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHiredlawyer() {
        return hiredlawyer;
    }

    public void setHiredlawyer(String hiredlawyer) {
        this.hiredlawyer = hiredlawyer;
    }

    public boolean isIsopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }
}

package com.innova.lawyerhiringsystem.model;
/** Model POJO class
 *  Contains the information which will be generated
 *  when lawyer applies for available case.
 *
 *  Primarily used to push objects to realtime database
 *  under 'Quotations' node
 *  */
public class Quotation {
    String date, name, email, phone, status, caseTittle, lawyerid;

    // status can be 'OPEN' / 'HIRED'


    public Quotation() {
    }

    public Quotation(String date, String name, String email, String phone, String status, String caseTittle, String lawyerid) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.caseTittle = caseTittle;
        this.lawyerid = lawyerid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaseTittle() {
        return caseTittle;
    }

    public void setCaseTittle(String caseTittle) {
        this.caseTittle = caseTittle;
    }

    public String getLawyerid() {
        return lawyerid;
    }

    public void setLawyerid(String lawyerid) {
        this.lawyerid = lawyerid;
    }
}

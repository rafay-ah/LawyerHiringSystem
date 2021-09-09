package com.innova.lawyerhiringsystem.model;
/* POJO class
*  In fulfillment of Firebase Database requirements
*  Used to save and retrieve articles created by Lawyers*/
public class Article {
    String tittle, content;

    public Article() {
    }

    public Article(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

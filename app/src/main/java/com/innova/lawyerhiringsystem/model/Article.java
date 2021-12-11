package com.innova.lawyerhiringsystem.model;
/* POJO class
*  In fulfillment of Firebase Database requirements
*  Used to save and retrieve articles created by Lawyers*/
public class Article {
    String tittle, content, article_id, lawyer_id,layer_name;

    public Article() {
    }

    public Article(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }

    public Article(String tittle, String content, String article_id, String lawyer_id, String layer_name) {
        this.tittle = tittle;
        this.content = content;
        this.article_id = article_id;
        this.lawyer_id = lawyer_id;
        this.layer_name = layer_name;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(String lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public String getLayer_name() {
        return layer_name;
    }

    public void setLayer_name(String layer_name) {
        this.layer_name = layer_name;
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

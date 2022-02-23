package com.careeradvisor.degree;

public class DegreeBean {
    private int id;
    private String degree_name;


    public DegreeBean() {
        this.id = 0;
        this.degree_name = "";

    }

    public DegreeBean(int id, String degree_name) {
        this.id = id;
        this.degree_name = degree_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree_name() {
        return degree_name;
    }

    public void setDegree_name(String degree_name) {
        this.degree_name = degree_name;
    }
}

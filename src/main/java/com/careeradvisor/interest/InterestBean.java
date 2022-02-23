package com.careeradvisor.interest;

public class InterestBean {
    private int id;
    private String interest_name;


    public InterestBean() {
        this.id = 0;
        this.interest_name = "";

    }

    public InterestBean(int id, String interest_name) {
        this.id = id;
        this.interest_name = interest_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterest_name() {
        return interest_name;
    }

    public void setInterest_name(String interest_name) {
        this.interest_name = interest_name;
    }
}

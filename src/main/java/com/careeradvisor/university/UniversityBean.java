package com.careeradvisor.university;

public class UniversityBean {
    private int id;
    private String university_name;
    private String university_type;
    private int ranking;
    private String location;
    private String website;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public UniversityBean() {
        this.id = 0;
        this.university_name = "";
        this.university_type = "";

    }

    public UniversityBean(int id, String university_name, String university_type) {
        this.id = id;
        this.university_name = university_name;
        this.university_type = university_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getUniversity_type() {
        return university_type;
    }

    public void setUniversity_type(String university_type) {
        this.university_type = university_type;
    }
}

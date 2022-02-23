package com.careeradvisor.university;

import java.util.List;

public interface UniversityDao {
    public List<UniversityBean> getAllUniversity(int id, String type);
    public int updateUniversity(UniversityBean university) ;
    public int deleteUniversity(String id) ;
    public int createUniversity(UniversityBean university) ;
    public UniversityBean getByName(String name);

}

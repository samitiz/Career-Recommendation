package com.careeradvisor.degree;

import java.util.List;

public interface DegreeDao {
    public List<DegreeBean> getAllDegrees(int id);
    public List<DegreeBean> getAllDegrees();
    public int updateDegree(DegreeBean degree) ;
    public int deleteDegree(String id) ;
    public int createDegree(DegreeBean degree) ;
    public DegreeBean getByName(String name);

}

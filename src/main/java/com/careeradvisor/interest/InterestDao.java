package com.careeradvisor.interest;

import java.util.List;

public interface InterestDao {
    public List<InterestBean> getAllInterests();
    public InterestBean getByName(String interestName);
    public int updateInterest(InterestBean interest) ;
    public int deleteInterest(String id) ;

    int updateInsert(int interestId, int degreeId);

    public InterestBean createInterest(InterestBean interest) ;
    public int insert(int interestId, int degreeId);

}

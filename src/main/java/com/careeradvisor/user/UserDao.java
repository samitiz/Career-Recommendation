package com.careeradvisor.user;

import java.util.List;

public interface UserDao {
    public List<UserBean> getAllUsers();
    public UserBean getUser(String id) ;
    public int updateUser(UserBean user) ;
    public int deleteUser(String id) ;
    public int createUser(UserBean user) ;
    public boolean authenticate(UserBean user) ;
    public boolean duplicate(UserBean user) ;
    public boolean duplicate_admin(UserBean user) ;
    public boolean check_admin(UserBean user);

}

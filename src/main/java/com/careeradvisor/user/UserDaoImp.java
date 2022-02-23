package com.careeradvisor.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao{

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/career_advisor";
    private static final String ID = "root";
    private static final String PASS = "Admin3124";

    private static final String DELETE = "DELETE FROM user WHERE user_id=?";
    private static final String FIND_ALL = "SELECT * FROM user ORDER BY user_id";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE user_id=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM user WHERE user_email=?";
    private static final String INSERT = "INSERT INTO user(user_name, user_email, user_password) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET user_name=?, user_email=?, user_password=? WHERE user_id=?";
    private static final String Auth = "SELECT * FROM user WHERE user_name=? AND user_password=?";
    private static final String AUTH_ADMIN = "SELECT * FROM admin WHERE admin_name=? AND admin_password=?";
    private static final String DUPLICATE_ADMIN = "SELECT * FROM admin WHERE admin_email=?";

    @Override
    public List<UserBean> getAllUsers() {

            Connection conn = null;
            PreparedStatement stmt = null;
            List<UserBean> list = new ArrayList<UserBean>();

            try {
                conn = getConnection();
                stmt = conn.prepareStatement(FIND_ALL);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    UserBean user = new UserBean();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("user_name"));
                    user.setEmail(rs.getString("user_email"));
                    user.setPassword(rs.getString("user_password"));

                    list.add(user);
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                close(stmt);
                close(conn);
            }

            return list;
    }

    @Override
    public UserBean getUser(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserBean user = new UserBean();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("user_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getString("user_password"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public int updateUser(UserBean user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public int deleteUser(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, id);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public int createUser(UserBean user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

            return result;
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public boolean authenticate(UserBean user)  {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(Auth);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

               return true;
            } else {

                return false;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public boolean duplicate(UserBean user) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_EMAIL);
            stmt.setString(1, user.getEmail());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public boolean duplicate_admin(UserBean user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(DUPLICATE_ADMIN);
            stmt.setString(1, user.getEmail());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public boolean check_admin(UserBean user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(AUTH_ADMIN);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    private Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}

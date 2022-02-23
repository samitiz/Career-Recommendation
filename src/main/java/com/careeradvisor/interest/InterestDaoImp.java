package com.careeradvisor.interest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterestDaoImp implements InterestDao{

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/career_advisor";
    private static final String ID = "root";
    private static final String PASS = "Admin3124";

    private static final String DELETE = "DELETE FROM interest WHERE interest_id=?";
    private static final String FIND_ALL = "SELECT * FROM interest ORDER BY interest_id";
    private static final String INSERT = "INSERT INTO interest(interest_name) VALUES(?)";
    private static final String INSERT_TABEL = "INSERT INTO d_interest(interest_id, degree_id) VALUES(?,?)";
    private static final String UPDATE = "UPDATE interest SET interest_name=?  WHERE interest_id=?";
    private static final String GET_BY_NAME = "SELECT *  FROM interest WHERE interest_name=?";
    private static final String INSERT_UPDATE = "UPDATE d_interest SET degree_id = ? WHERE interest_id = ?";

    @Override
    public List<InterestBean> getAllInterests() {

            Connection conn = null;
            PreparedStatement stmt = null;
            List<InterestBean> list = new ArrayList<InterestBean>();

            try {
                conn = getConnection();
                stmt = conn.prepareStatement(FIND_ALL);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    InterestBean interest = new InterestBean();
                    interest.setId(rs.getInt("interest_id"));
                    interest.setInterest_name(rs.getString("interest_name"));

                    list.add(interest);
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
    public InterestBean getByName(String interestName) {

        Connection conn = null;
        PreparedStatement stmt = null;
        InterestBean interest = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(GET_BY_NAME);
            stmt.setString(1, interestName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                interest = new InterestBean();
                interest.setId(rs.getInt("interest_id"));
                interest.setInterest_name(rs.getString("interest_name"));

            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }

        return interest;
    }


    @Override
    public int updateInterest(InterestBean interest) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, interest.getInterest_name());
            stmt.setInt(2, interest.getId());

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
    public int deleteInterest(String id ) {
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
    public int insert(int interestId, int degreeId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT_TABEL, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, interestId);
            stmt.setInt(2, degreeId);


            int result = stmt.executeUpdate();

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
    public int updateInsert(int interestId, int degreeId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT_TABEL, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(2, interestId);
            stmt.setInt(1, degreeId);


            int result = stmt.executeUpdate();

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
    public InterestBean createInterest(InterestBean interest) {
        System.out.println("create");
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, interest.getInterest_name());


            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                interest.setId(rs.getInt(1));
            }

            return interest;
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

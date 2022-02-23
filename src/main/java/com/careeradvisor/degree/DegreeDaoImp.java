package com.careeradvisor.degree;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeDaoImp implements DegreeDao{

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/career_advisor";
    private static final String ID = "root";
    private static final String PASS = "Admin3124";

    private static final String DELETE = "DELETE FROM degree WHERE degree_id=?";
    private static final String FIND_ALL_DEGREE = "SELECT * FROM degree ORDER BY degree_id";
    private static final String FIND_ALL = "SELECT d.degree_id, d.degree_name\n" +
            "FROM degree as d\n" +
            "INNER JOIN d_interest as i ON i.degree_id = d.degree_id\n" +
            "WHERE interest_id = ?";
    private static final String INSERT = "INSERT INTO degree(degree_name) VALUES(?)";
    private static final String UPDATE = "UPDATE degree SET degree_name=?  WHERE degree_id=?";
    private static final String GET_BY_NAME = "SELECT *  FROM degree WHERE degree_name=?";

    @Override
    public List<DegreeBean> getAllDegrees(int id) {

            Connection conn = null;
            PreparedStatement stmt = null;
            List<DegreeBean> list = new ArrayList<DegreeBean>();

            try {
                conn = getConnection();
                stmt = conn.prepareStatement(FIND_ALL);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    DegreeBean degree = new DegreeBean();
                    degree.setId(rs.getInt("degree_id"));
                    degree.setDegree_name(rs.getString("degree_name"));

                    list.add(degree);
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
    public List<DegreeBean> getAllDegrees() {

        Connection conn = null;
        PreparedStatement stmt = null;
        List<DegreeBean> list = new ArrayList<DegreeBean>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL_DEGREE);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DegreeBean degree = new DegreeBean();
                degree.setId(rs.getInt("degree_id"));
                degree.setDegree_name(rs.getString("degree_name"));

                list.add(degree);
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
    public DegreeBean getByName(String name) {

        Connection conn = null;
        PreparedStatement stmt = null;
        DegreeBean degree = new DegreeBean();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(GET_BY_NAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                degree.setId(rs.getInt("degree_id"));
                degree.setDegree_name(rs.getString("degree_name"));

            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }

        return degree;
    }


    @Override
    public int updateDegree(DegreeBean degree) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, degree.getDegree_name());
            stmt.setInt(2, degree.getId());

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
    public int deleteDegree(String id) {
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
    public int createDegree(DegreeBean degree) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, degree.getDegree_name());


            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                degree.setId(rs.getInt(1));
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

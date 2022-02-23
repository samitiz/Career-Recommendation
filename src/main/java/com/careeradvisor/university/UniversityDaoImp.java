package com.careeradvisor.university;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityDaoImp implements UniversityDao{

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/career_advisor";
    private static final String ID = "root";
    private static final String PASS = "Admin3124";

    private static final String DELETE = "DELETE FROM university WHERE uni_id=?";
    private static final String FIND_ALL = "SELECT u.uni_id, u.uni_name \n" +
            "FROM university as u\n" +
            "INNER JOIN u_degree as d ON d.uni_id = u.uni_id\n" +
            "WHERE degree_id = ? AND u.type = ? ;";

    private static final String INSERT = "INSERT INTO university(uni_name, type, ranking, website, location) VALUES(?,?,?,?,?)";
    private static final String UPDATE = "UPDATE university SET uni_name=?, type=?, ranking=?, website=?, location=?  WHERE uni_id=?";
    private static final String GET_BY_NAME = "SELECT *  FROM university WHERE uni_name=?";
    private static final String LIST_ALL = "SELECT *  FROM university ORDER BY uni_id";

    @Override
    public List<UniversityBean> getAllUniversity(int id, String type) {

            Connection conn = null;
            PreparedStatement stmt = null;
            List<UniversityBean> list = new ArrayList<UniversityBean>();

            try {
                conn = getConnection();
                stmt = conn.prepareStatement(FIND_ALL);
                stmt.setInt(1, id);
                stmt.setString(2,type);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    UniversityBean university = new UniversityBean();
                    university.setId(rs.getInt("uni_id"));
                    university.setUniversity_name(rs.getString("uni_name"));


                    list.add(university);
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

    public List<UniversityBean> listAllUniversity(){
        Connection conn = null;
        PreparedStatement stmt = null;
        List<UniversityBean> list = new ArrayList<UniversityBean>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(LIST_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UniversityBean university = new UniversityBean();
                university.setId(rs.getInt("uni_id"));
                university.setUniversity_name(rs.getString("uni_name"));
                university.setWebsite(rs.getString("website"));
                university.setRanking(rs.getInt("ranking"));
                university.setLocation(rs.getString("location"));
                university.setUniversity_type(rs.getString("type"));

                list.add(university);
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
    public UniversityBean getByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        UniversityBean university = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(GET_BY_NAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                university = new UniversityBean();
                university.setId(rs.getInt("uni_id"));
                university.setUniversity_name(rs.getString("uni_name"));
                university.setLocation(rs.getString("location"));
                university.setRanking(rs.getInt("ranking"));
                university.setWebsite(rs.getString("website"));
                university.setUniversity_type(rs.getString("type"));

            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    return university;
    }


    @Override
    public int updateUniversity(UniversityBean university) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, university.getUniversity_name());
            stmt.setString(2, university.getUniversity_type());
            stmt.setInt(3, university.getRanking());
            stmt.setString(4, university.getWebsite());
            stmt.setString(5, university.getLocation());
            stmt.setInt(6, university.getId());

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
    public int deleteUniversity(String id) {
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
    public int createUniversity(UniversityBean university) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, university.getUniversity_name());
            stmt.setString(2, university.getUniversity_type());
            stmt.setInt(3, university.getRanking());
            stmt.setString(4, university.getWebsite());
            stmt.setString(5, university.getLocation());


            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                university.setId(rs.getInt(1));
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

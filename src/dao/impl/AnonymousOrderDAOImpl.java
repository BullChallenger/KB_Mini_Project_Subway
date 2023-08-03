package dao.impl;

import common.DBManager;
import dao.AnonymousOrderDAO;
import dto.AnonymousOrderDTO;
import dto.MemberDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnonymousOrderDAOImpl implements AnonymousOrderDAO<AnonymousOrderDTO, Long> {

    private static final AnonymousOrderDAOImpl instance = new AnonymousOrderDAOImpl();

    private AnonymousOrderDAOImpl(){
    }

    public static AnonymousOrderDAOImpl getInstance() {return instance;}

    @Override
    public Long countByAnonymousOrderDate(String anonymousOrderDate) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM ANANYMOUSORDER WHERE ORDER_DATE LIKE '" + anonymousOrderDate + "'";

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            return rs.getLong(1);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public Long countByMenuId(Long menuId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM ANANYMOUSORDER WHERE MENU_ID = " + menuId;

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            return rs.getLong(1);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public List<AnonymousOrderDTO> findByMenuId(Long menuId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT ANANYMOUS_ORDER_ID, ORDER_DATE, MENU_ID, ORDER_STATUS FROM ANANYMOUSORDER " +
                     "WHERE MENU_ID = " + menuId;
        List<AnonymousOrderDTO> anonymousOrderList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                AnonymousOrderDTO theAnonymousOrder = new AnonymousOrderDTO();

                theAnonymousOrder.setAnonymousOrderId(rs.getLong(1));
                theAnonymousOrder.setOrderDate(rs.getString(2));
                theAnonymousOrder.setMenuId(rs.getLong(3));
                theAnonymousOrder.setOrderStatus(rs.getString(4).charAt(0));

                anonymousOrderList.add(theAnonymousOrder);
            }

            return anonymousOrderList;
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public List<AnonymousOrderDTO> findByAnonymousOrderId(Long anonymousorderId) {
        return null;
    }

    @Override
    public <S extends AnonymousOrderDTO> S save(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO ANANYMOUSORDER(MENU_ID, ORDER_STATUS) VALUES(?, ?)";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, dto.getMenuId());
            pstm.setString(2, String.valueOf(dto.getOrderStatus()));
            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public <S extends AnonymousOrderDTO> S update(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE ANANYMOUSORDER SET ORDER_STATUS = ? WHERE ANANYMOUS_ORDER_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, String.valueOf(dto.getOrderStatus()));
            pstm.setLong(2, dto.getAnonymousOrderId());
            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    @Deprecated
    public AnonymousOrderDTO findById(Long anonymousOrderId) {
        return null;
    }

    @Override
    public Iterable<AnonymousOrderDTO> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT ANANYMOUS_ORDER_ID, ORDER_DATE, MENU_ID, ORDER_STATUS FROM ANANYMOUSORDER";

        List<AnonymousOrderDTO> anonymousOrderList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                AnonymousOrderDTO theAnonymousOrder = new AnonymousOrderDTO();

                theAnonymousOrder.setAnonymousOrderId(rs.getLong(1));
                theAnonymousOrder.setOrderDate(rs.getString(2));
                theAnonymousOrder.setMenuId(rs.getLong(3));
                theAnonymousOrder.setOrderStatus(rs.getString(4).charAt(0));

                anonymousOrderList.add(theAnonymousOrder);
            }

            return anonymousOrderList;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public long count() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM ANANYMOUSORDER";

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            return rs.getLong(1);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public void deleteById(Long anonymousOrderId) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM ANANYMOUSORDER WHERE ANANYMOUS_ORDER_ID = " + anonymousOrderId;

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public void delete(AnonymousOrderDTO dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBER WHERE ANANYMOUS_ORDER_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, dto.getAnonymousOrderId());
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public void deleteAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM ANANYMOUSORDER";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }
}

package dao.impl;

import common.DBManager;
import dao.AnonymousOrderDAO;
import dto.AnonymousOrderDTO;
import exception.admin.AdminException;
import exception.base.BaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnonymousOrderDAOImpl implements AnonymousOrderDAO<AnonymousOrderDTO, Long> {

    private static final AnonymousOrderDAOImpl instance = new AnonymousOrderDAOImpl();

    private AnonymousOrderDAOImpl(){
    }

    public static AnonymousOrderDAOImpl getInstance() {return instance;}

    /**
     * 주문 일자 기준으로 DB 에 저장되어 있는 비회원 주문의 수를 반환
     * @param anonymousOrderDate 비회원 주문 일자
     * @return 비회원 주문의 수
     */
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

    /**
     * 메뉴의 Primary Key 기준으로 DB 에 저장되어 있는 비회원 주문의 수를 반환
     * @param menuId 메뉴의 Primary Key
     * @return 비회원 주문의 수
     */
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

    /**
     * 메뉴의 Primary Key 기준으로 DB 에 저장되어 있는 비회원 주문 목록을 반환
     * @param menuId 메뉴의 Primary Key
     * @return 비회원 주문 목록
     */
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
    @Deprecated
    public List<AnonymousOrderDTO> findByAnonymousOrderId(Long anonymousorderId) {
        return null;
    }

    /**
     * 비회원 주문을 DB에 저장
     * @param dto AnonymousOrderDTO
     * @return DB 에 저장된 비회원 주문 DTO
     * @param <S> AnonymousOrderDTO 를 상속받는 모든 클래스
     */
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

    /**
     * DB에 저장되어 있는 비회원 주문의 값을 수정
     * @param dto 변경사항이 반영된 AnonymousOrderDTO
     * @return 변경사항이 반영된 AnonymousOrderDTO
     * @param <S> AnonymousOrderDTO 를 상속받는 모든 클래스
     */
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

    /**
     * DB에 저장되어 있는 모든 비회원 주문 목록 반환
     * @return List<AnonymousOrderDTO>
     */
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
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 모든 비회원 주문의 수를 반환
     * @return 모든 비회원 주문의 수
     */
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
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 비회원 주문의 id를 기준으로 비회원 주문 삭제
     * @param anonymousOrderId 비회원 주문의 id
     */
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
            throw new AdminException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 삭제하고 하는 비회원 주문 객체를 통해 DB에 저장된 비회원 주문 삭제
     * @param dto 삭제하고 하는 비회원 주문 객체
     */
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
            throw new AdminException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 모든 비회원 주문 삭제
     */
    @Override
    public void deleteAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM ANANYMOUSORDER";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
        }catch (SQLException e) {
            throw new AdminException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }
}

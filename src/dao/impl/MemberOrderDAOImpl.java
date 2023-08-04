package dao.impl;

import common.DBManager;
import dao.MemberOrderDAO;
import dto.MemberOrderDTO;
import exception.base.BaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberOrderDAOImpl implements MemberOrderDAO<MemberOrderDTO, Long> {

    private static final MemberOrderDAOImpl instance = new MemberOrderDAOImpl();

    private MemberOrderDAOImpl(){
    }

    public static MemberOrderDAOImpl getInstance() {return instance;}

    /**
     * 회원 주문을 DB에 저장
     * @param dto 회원 주문의 정보가 담긴 객체
     * @return 저장된 회원 주문의 정보가 담긴 객체
     * @param <S> MemberOrderDTO 를 상속받는 클래스
     */
    @Override
    public <S extends MemberOrderDTO> S save(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO MEMBERORDER (SELECT_BREAD, SELECT_CHEESE, SELECT_ADDITIONALMENU, EXCLUDE_VEGETABLE , SELECT_SOURCE , MENU_ID , MEMBER_ID , ORDER_STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, dto.getSelectBread());
            pstm.setInt(2, dto.getSelectCheese());
            pstm.setString(3, dto.getSelectedAdditionalMenu());
            pstm.setString(4, dto.getExcludedVegetable());
            pstm.setString(5, dto.getSelectedSource());
            pstm.setLong(6, dto.getMenuId());
            pstm.setLong(7, dto.getMemberId());
            pstm.setString(8, String.valueOf(dto.getOrderStatus()));

            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 회원 주문의 정보를 갱신
     * @param dto 수정사항이 반영된 회원 주문의 정보가 담긴 객체
     * @return 수정사항이 반영된 회원 주문의 정보가 담긴 객체
     * @param <S> MemberOrderDTO 를 상속받는 클래스
     */
    @Override
    public <S extends MemberOrderDTO> S update(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE MEMBERORDER SET SELECT_BREAD = ?, SELECT_CHEESE = ?, SELECT_ADDITIONALMENU = ?, EXCLUDE_VEGETABLE = ?, SELECT_SOURCE = ? , MENU_ID = ?, MEMBER_ID = ?, ORDER_STATUS =? WHERE INGREDIENT_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, dto.getSelectBread());
            pstm.setInt(2, dto.getSelectCheese());
            pstm.setString(3, dto.getSelectedAdditionalMenu());
            pstm.setString(4, dto.getExcludedVegetable());
            pstm.setString(5, dto.getSelectedSource());
            pstm.setLong(6, dto.getMenuId());
            pstm.setLong(7, dto.getMemberId());
            pstm.setString(8, String.valueOf(dto.getOrderStatus()));
            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 회원 주문의 Primary Key 를 통해 특정 회원 주문을 조회
     * @param memberOrderId 회원 주문의 Primary Key
     * @return 조회된 회원 주문의 정보가 반영된 객체
     */
    @Override
    public MemberOrderDTO findById(Long memberOrderId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBERORDERID, SELECT_BREAD , SELECT_CHEESE , SELECT_ADDITIONALMENU , EXCLUDE_VEGETABLE , SELECT_SOURCE , ORDER_DATE , MENU_ID , MEMBER_ID , ORDER_STATUS FROM MEMBERORDER WHERE MEMBERORDER_ID = " + memberOrderId;
        MemberOrderDTO theMemberOrder = new MemberOrderDTO();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            theMemberOrder.setMemberOrderId(rs.getLong(1));
            theMemberOrder.setSelectBread(rs.getInt(2));
            theMemberOrder.setSelectCheese(rs.getInt(3));
            theMemberOrder.setSelectedAdditionalMenu(rs.getString(4));
            theMemberOrder.setExcludedVegetable(rs.getString(5));
            theMemberOrder.setSelectedSource(rs.getString(6));
            theMemberOrder.setOrderDate(rs.getString(7));
            theMemberOrder.setMenuId(rs.getLong(8));
            theMemberOrder.setMemberId(rs.getLong(9));
            theMemberOrder.setOrderStatus(rs.getString(10).charAt(0));

            return theMemberOrder;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * DB 에 저장된 모든 회원 주문의 정보를 목록으로 반환
     * @return DB 에 저장된 모든 회원 주문 목록
     */
    @Override
    public Iterable<MemberOrderDTO> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBERORDERID, SELECT_BREAD , SELECT_CHEESE , SELECT_ADDITIONALMENU , EXCLUDE_VEGETABLE , SELECT_SOURCE , ORDER_DATE , MENU_ID , MEMBER_ID , ORDER_STATUS FROM MEMBERORDER " +
                     "WHERE ORDER_STATUS = 'N'";



        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            List<MemberOrderDTO> memberOrderList = new ArrayList<>();

            while(rs.next()) {
                MemberOrderDTO theMemberOrder = new MemberOrderDTO();

                theMemberOrder.setMemberOrderId(rs.getLong(1));
                theMemberOrder.setSelectBread(rs.getInt(2));
                theMemberOrder.setSelectCheese(rs.getInt(3));
                theMemberOrder.setSelectedAdditionalMenu(rs.getString(4));
                theMemberOrder.setExcludedVegetable(rs.getString(5));
                theMemberOrder.setSelectedSource(rs.getString(6));
                theMemberOrder.setOrderDate(rs.getString(7));
                theMemberOrder.setMenuId(rs.getLong(8));
                theMemberOrder.setMemberId(rs.getLong(9));
                theMemberOrder.setOrderStatus(rs.getString(10).charAt(0));

                memberOrderList.add(theMemberOrder);
            }

            return memberOrderList;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * DB에 저장되어 있는 모든 회원 주문의 수
     * @return DB에 저장되어 있는 모든 회원 주문의 수
     */
    @Override
    public long count() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM MEMBERORDER;";

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
     * 회원 주문의 Primary Key 를 통해 특정 회원 주문 삭제
     * @param memberOrderId 삭제하고자 하는 회원 주문의 Primary Key
     */
    @Override
    public void deleteById(Long memberOrderId) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBERORDER WHERE MEMBERORDER_ID = " + memberOrderId;

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.executeUpdate();
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 삭제하고자 하는 회원 주문의 정보가 담긴 객체를 통해 회원 주문 삭제
     * @param dto 삭제하고자 하는 회원 주문의 정보가 담긴 객체
     */
    @Override
    public void delete(MemberOrderDTO dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBERORDER WHERE MEMBERORDER_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, dto.getMemberOrderId());
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * DB에 저장되어 있는 모든 회원 주문 삭제
     */
    @Override
    public void deleteAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBERORDER";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 주문 일자를 기준으로 회원 주문 수 조회
     * @param memberOrderDate 조회하고자 하는 주문일자
     * @return 파라미터로 입력된 주문 일자에 주문된 회원 주문의 수
     */
    @Override
    public Long countByMemberOrderDate(String memberOrderDate) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM MEMBERORDER WHERE ORDER_DATE LIKE '" + memberOrderDate + "'" ;

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            return rs.getLong(1);
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 메뉴의 Primary Key 를 기준으로 회원 주문의 수를 조회
     * @param menuId 수를 조회하고자 하는 메뉴의 Primary Key
     * @return 특정 메뉴의 회원 주문의 수
     */
    @Override
    public Long countByMenuId(Long menuId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM MEMBERORDER WHERE MENU_ID = ;" + menuId ;

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            return rs.getLong(1);
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 회원의 Primary Key 를 기준으로 특정 회원 주문의 수를 조회
     * @param memberId 주문의 수를 조회하고자 하는 회원의 Primary Key
     * @return 특정 회원의 주문 수
     */
    @Override
    public Long countByMemberId(Long memberId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM MEMBERORDER WHERE MEMBERORDERID = ;" + memberId ;

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            return rs.getLong(1);
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 회원의 Primary Key 를 기준으로 특정 회원 주문 목록을 반환
     * @param memberId 주문 목록을 반환하고자 하는 회원의 Primary Key
     * @return 특정 회원 주문 목록
     */
    @Override
    public List<MemberOrderDTO> findByMemberId(Long memberId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBERORDERID, SELECT_BREAD , SELECT_CHEESE , SELECT_ADDITIONALMENU , EXCLUDE_VEGETABLE , SELECT_SOURCE , ORDER_DATE , MENU_ID , MEMBER_ID , ORDER_STATUS FROM MEMBERORDER WHERE MEMBER_ID = " + memberId;

        List<MemberOrderDTO> memberOrderList = new ArrayList<>();
        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                MemberOrderDTO theMemberOrder = new MemberOrderDTO();

                theMemberOrder.setMemberOrderId(rs.getLong(1));
                theMemberOrder.setSelectBread(rs.getInt(2));
                theMemberOrder.setSelectCheese(rs.getInt(3));
                theMemberOrder.setSelectedAdditionalMenu(rs.getString(4));
                theMemberOrder.setExcludedVegetable(rs.getString(5));
                theMemberOrder.setSelectedSource(rs.getString(6));
                theMemberOrder.setOrderDate(rs.getString(7));
                theMemberOrder.setMenuId(rs.getLong(8));
                theMemberOrder.setMemberId(rs.getLong(9));
                theMemberOrder.setOrderStatus(rs.getString(10).charAt(0));

                memberOrderList.add(theMemberOrder);
            }

            return memberOrderList;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 메뉴 Primary Key 를 기준으로 특정 주문의 목록 반환
     * @param menuId 주문 목록을 반환받고자 하는 메뉴의 Primary Key
     * @return 특정 주문의 목록
     */
    @Override
    public List<MemberOrderDTO> findByMenuId(Long menuId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBERORDERID, SELECT_BREAD , SELECT_CHEESE , SELECT_ADDITIONALMENU , EXCLUDE_VEGETABLE , SELECT_SOURCE , ORDER_DATE , MENU_ID , MEMBER_ID , ORDER_STATUS FROM MEMBERORDER WHERE MEMBER_ID = " + menuId;

        List<MemberOrderDTO> memberOrderList = new ArrayList<>();
        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                MemberOrderDTO theMemberOrder = new MemberOrderDTO();

                theMemberOrder.setMemberOrderId(rs.getLong(1));
                theMemberOrder.setSelectBread(rs.getInt(2));
                theMemberOrder.setSelectCheese(rs.getInt(3));
                theMemberOrder.setSelectedAdditionalMenu(rs.getString(4));
                theMemberOrder.setExcludedVegetable(rs.getString(5));
                theMemberOrder.setSelectedSource(rs.getString(6));
                theMemberOrder.setOrderDate(rs.getString(7));
                theMemberOrder.setMenuId(rs.getLong(8));
                theMemberOrder.setMemberId(rs.getLong(9));
                theMemberOrder.setOrderStatus(rs.getString(10).charAt(0));

                memberOrderList.add(theMemberOrder);
            }

            return memberOrderList;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 메뉴의 Primary Key 와 회원의 Primary Key 를 동시에 만족하는 회원 주문 조회
     * @param menuId 찾고자 하는 메뉴의 Primary Key
     * @param memberId 찾고자 하는 회원의 Primary Key
     * @return 메뉴의 Primary Key 와 회원의 Primary Key 를 동시에 만족하는 회원 주문
     */
    @Override
    public MemberOrderDTO findByMenuIdAndMemberId(Long menuId, Long memberId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBERORDERID , SELECT_BREAD, SELECT_CHEESE, SELECT_ADDITIONALMENU, EXCLUDE_VEGETABLE, SELECT_SOURCE, ORDER_DATE, MENU_ID, MEMBER_ID, ORDER_STATUS FROM (SELECT MEMBERORDERID , SELECT_BREAD, SELECT_CHEESE, SELECT_ADDITIONALMENU, EXCLUDE_VEGETABLE, SELECT_SOURCE, ORDER_DATE, MENU_ID, MEMBER_ID, ORDER_STATUS FROM MEMBERORDER WHERE MEMBER_ID = " + memberId + " ORDER BY ORDER_DATE DESC) WHERE ROWNUM = 1";

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            MemberOrderDTO theMemberOrder = new MemberOrderDTO();

            rs.next();
            theMemberOrder.setMemberOrderId(rs.getLong(1));
            theMemberOrder.setSelectBread(rs.getInt(2));
            theMemberOrder.setSelectCheese(rs.getInt(3));
            theMemberOrder.setSelectedAdditionalMenu(rs.getString(4));
            theMemberOrder.setExcludedVegetable(rs.getString(5));
            theMemberOrder.setSelectedSource(rs.getString(6));
            theMemberOrder.setOrderDate(rs.getString(7));
            theMemberOrder.setMenuId(rs.getLong(8));
            theMemberOrder.setMemberId(rs.getLong(9));
            theMemberOrder.setOrderStatus(rs.getString(10).charAt(0));

            return theMemberOrder;
        } catch (SQLException e) {
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 회원 주문의 Primary Key 로 특정 회원 주문의 상태를 변경
     * @param memberOrderId 상태를 변경하고자 하는 회원 주문의 Primary Key
     * @return 변경 완료 1, 실패 0
     */
    @Override
    public int updateOrderStatusByMemberOrderId(Long memberOrderId) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE MEMBERORDER SET ORDER_STATUS = 'Y' WHERE MEMBERORDERID = " + memberOrderId;
        MemberOrderDTO theMemberOrder = new MemberOrderDTO();

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            return pstm.executeUpdate();
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }
}

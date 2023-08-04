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

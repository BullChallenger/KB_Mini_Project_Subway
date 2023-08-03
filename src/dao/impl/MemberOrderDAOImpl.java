package dao.impl;

import common.DBManager;
import dao.MemberOrderDAO;
import dto.IngredientDTO;
import dto.MemberOrderDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberOrderDAOImpl implements MemberOrderDAO<MemberOrderDTO, Long> {
    @Override
    public <S extends MemberOrderDTO> S save(S dto) {
        return null;
    }

    @Override
    public <S extends MemberOrderDTO> S update(S dto) {
        return null;
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
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public Iterable<MemberOrderDTO> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBERORDERID, SELECT_BREAD , SELECT_CHEESE , SELECT_ADDITIONALMENU , EXCLUDE_VEGETABLE , SELECT_SOURCE , ORDER_DATE , MENU_ID , MEMBER_ID , ORDER_STATUS FROM MEMBERORDER";



        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            MemberOrderDTO theMemberOrder = new MemberOrderDTO();
            List<MemberOrderDTO> memberOrderList = new ArrayList<>();

            while(rs.next()) {
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
        String sql = "SELECT COUNT(*) FROM MEMBERORDER;";

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
    public void deleteById(Long memberOrderId) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBERORDER WHERE MEMBERORDER_ID = " + memberOrderId;

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
    public void delete(MemberOrderDTO dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBERORDER WHERE MEMBERORDER_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, dto.getMemberOrderId());
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
        String sql = "DELETE FROM MEMBERORDER";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
        }catch (SQLException e) {
            throw new RuntimeException();
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
        String sql = "SELECT COUNT(*) FROM MEMBERORDER WHERE MENU_ID = ;" + menuId ;

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
            e.printStackTrace();
            throw new RuntimeException();
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
            MemberOrderDTO theMemberOrder = new MemberOrderDTO();
            while(rs.next()) {
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
            throw new RuntimeException();
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
            MemberOrderDTO theMemberOrder = new MemberOrderDTO();
            while(rs.next()) {
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
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }
}

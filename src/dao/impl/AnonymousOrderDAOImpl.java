package dao.impl;

import common.DBManager;
import dao.AnonymousOrderDAO;
import dto.AnonymousOrderDTO;
import dto.MemberDTO;

import java.sql.*;
import java.util.List;

public class AnonymousOrderDAOImpl implements AnonymousOrderDAO<AnonymousOrderDTO, Long> {

    @Override
    public Long countByAnonymousOrderDate(String AnonymousOrderDate) {
        return null;
    }

    @Override
    public Long countByMenuId(String AnonymousOrderDate) {
        return null;
    }

    @Override
    public List<AnonymousOrderDTO> findByMenuId(Long menuId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT ANA FROM ANANYMOUSORDER WHERE MEMBER_ID = " + menuId;
        MemberDTO theMember = new MemberDTO();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            theMember.setMemberId(rs.getLong(1));
            theMember.setMemberName(rs.getString(2));
            theMember.setPhoneNumber(rs.getString(3));
            theMember.setPoint(rs.getInt(4));

            return theMember;
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public <S extends AnonymousOrderDTO> S save(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO ANANYMOUSORDER(MENU_ID, PHONE_NUMBER) VALUES(?, ?)";

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
        return null;
    }

    @Override
    public AnonymousOrderDTO findById(Long aLong) {
        return null;
    }

    @Override
    public Iterable<AnonymousOrderDTO> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(AnonymousOrderDTO dto) {

    }

    @Override
    public void deleteAll() {

    }
}

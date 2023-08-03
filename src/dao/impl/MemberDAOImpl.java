package dao.impl;

import common.DBManager;
import dao.MemberDAO;
import dto.MemberDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO<MemberDTO, Long> {

    private static final MemberDAOImpl instance = new MemberDAOImpl();

    private MemberDAOImpl() {
    };

    public static MemberDAOImpl getInstance() {
        return instance;
    }


    @Override
    public <S extends MemberDTO> S save(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO MEMBER (MEMEBER_NAME, PHONE_NUMBER) VALUES(?, ?)";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, dto.getMemberName());
            pstm.setString(2, dto.getPhoneNumber());
            pstm.executeUpdate();
            return dto;
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public <S extends MemberDTO> S update(S dto) {
        return null;
    }

    @Override
    public MemberDTO findById(Long aLong) {
        return null;
    }

    @Override
    public Iterable<MemberDTO> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBER_ID, MEMBER_NAME, PHONE_NUMBER, POINT FROM MEMBER";

        List<MemberDTO> memberList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            MemberDTO theMember = new MemberDTO();

            while(rs.next()) {
                theMember.setMemberId((long) rs.getInt(1));
                theMember.setMemberName(rs.getString(2));
                theMember.setPhoneNumber(rs.getString(3));
                theMember.setPoint(rs.getInt(4));

                memberList.add(theMember);
            }

            return memberList;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(MemberDTO dto) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public MemberDTO findByPhoneNumber(String phoneNumber) {
        return null;
    }
}
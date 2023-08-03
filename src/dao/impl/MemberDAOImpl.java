package dao.impl;

<<<<<<< HEAD
import dao.MemberDAO;
import dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public <S extends MemberDTO> S save(S dto) {
        return null;
=======
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
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
    }

    @Override
    public <S extends MemberDTO> S update(S dto) {
<<<<<<< HEAD
        return null;
    }

    @Override
    public MemberDTO findById(Long aLong) {
        return null;
=======
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE MEMBER SET MEMBER_NAME = ?, PHONE_NUMBER = ? WHERE MEMBER_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, dto.getMemberName());
            pstm.setString(2, dto.getPhoneNumber());
            pstm.setLong(3, dto.getMemberId());
            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public MemberDTO findById(Long memberId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBER_ID, MEMBER_NAME, PHONE_NUMBER, POINT FROM MEMBER WHERE MEMBER_ID = " + memberId;
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
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
    }

    @Override
    public Iterable<MemberDTO> findAll() {
<<<<<<< HEAD
        return null;
=======
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
                theMember.setMemberId(rs.getLong(1));
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
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
    }

    @Override
    public long count() {
<<<<<<< HEAD
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

=======
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM MEMBER;";

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
    public void deleteById(Long memberId) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = " + memberId;

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
    }

    @Override
    public void delete(MemberDTO dto) {
<<<<<<< HEAD

=======
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, dto.getMemberId());
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
    }

    @Override
    public void deleteAll() {
<<<<<<< HEAD

=======
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBER";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
    }

    @Override
    public MemberDTO findByPhoneNumber(String phoneNumber) {
<<<<<<< HEAD
        return null;
    }
}
=======
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT MEMBER_ID, MEMBER_NAME, PHONE_NUMBER, POINT FROM MEMBER WHERE PHONE_NUMBER LIKE '" + phoneNumber + "'";
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
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }
}
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea

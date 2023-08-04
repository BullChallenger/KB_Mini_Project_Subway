package dao.impl;

import common.DBManager;
import dao.MemberDAO;
import dto.MemberDTO;
import exception.base.BaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO<MemberDTO, Long> {

    private static final MemberDAOImpl instance = new MemberDAOImpl();

    private MemberDAOImpl() {};

    public static MemberDAOImpl getInstance() {
        return instance;
    }

    /**
     * 회원의 정보를 DB에 저장
     * @param dto 저장하고자 하는 회원의 정보가 반영된 객체
     * @return DB에 저장된 회원의 정보가 반영된 객체
     * @param <S> MemberDTO 를 상속받는 모든 클래스
     */
    @Override
    public <S extends MemberDTO> S save(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO MEMBER (MEMBER_NAME, PHONE_NUMBER) VALUES(?, ?)";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, dto.getMemberName());
            pstm.setString(2, dto.getPhoneNumber());
            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * DB에 저장되어 있는 회원 정보를 수정
     * @param dto 수정사항이 반영된 회원 객체
     * @return 수정사항이 반영된 회원 객체
     * @param <S> MemberDTO 를 상속받는 모든 클래스
     */
    @Override
    public <S extends MemberDTO> S update(S dto) {

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
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 회원의 Primary Key 값을 통해 특정 회원의 정보를 반환
     * @param memberId 정보를 찾고자 하는 회원의 Primary Key
     * @return 찾은 회원의 정보가 반영된 객체
     */
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
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * DB에 저장된 모든 회원 목록을 반환
     * @return 모든 회원 목록
     */
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

            while(rs.next()) {
                MemberDTO theMember = new MemberDTO();

                theMember.setMemberId(rs.getLong(1));
                theMember.setMemberName(rs.getString(2));
                theMember.setPhoneNumber(rs.getString(3));
                theMember.setPoint(rs.getInt(4));

                memberList.add(theMember);
            }

            return memberList;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * DB에 저장된 모든 회원의 수
     * @return DB에 저장된 모든 회원의 수
     */
    @Override
    public long count() {

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
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    /**
     * 회원의 Primary Key 값을 통해 특정 회원 삭제
     * @param memberId 삭제하고자 하는 회원의 Primary Key
     */
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
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 삭제하고자 하는 회원의 정보가 담긴 객체를 통해 Db에 저장된 회원 삭제
     * @param dto 삭제하고자 하는 회원의 정보가 담긴 객체
     */
    @Override
    public void delete(MemberDTO dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, dto.getMemberId());
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * DB에 저장되어 있는 모든 회원 정보 삭제
     */
    @Override
    public void deleteAll() {

        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM MEMBER";

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
     * 회원의 전화번호를 통해 DB에 저장된 회원 정보 조회
     * @param phoneNumber 조회하고자 하는 회원의 전화번호
     * @return 전화번호를 통해 조회된 회원의 정보가 반영된 객체
     */
    @Override
    public MemberDTO findByPhoneNumber(String phoneNumber) {

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
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }
}
package dao.impl;
import common.DBManager;
import dao.MenuDAO;
import dto.MenuDTO;
import exception.base.BaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * 신메뉴 삽입 수행
 **/
public class MenuDAOImpl implements MenuDAO<MenuDTO, Long>{

    private static final MenuDAOImpl instance = new MenuDAOImpl();
    private MenuDAOImpl(){}
    public static MenuDAOImpl getInstance() {return instance;}

    /**
     * 메뉴를 DB에 저장
     * @param dto 저장하고자 하는 메뉴의 정보가 반영된 객체
     * @return 저장하고자 하는 메뉴의 정보가 반영된 객체
     * @param <S> MenuDTO 를 상속받은 클래스
     */
    @Override
    public <S extends MenuDTO> S save(S dto) {
        Connection con=null;
        PreparedStatement ps=null;
        int result=0;

        String sql="INSERT INTO MENU VALUES(?,?,?)";

        try{
            con= DBManager.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,dto.getMenuName());
            ps.setInt(2,dto.getMenuPrice());
            ps.setInt(3,dto.getMenuCalorie());
            result=ps.executeUpdate();
            return dto;
        } catch (SQLException e) {
            throw new BaseException();
        }finally{
            DBManager.releaseConnection(con,ps);
        }


    }

    /**
     * 이름 기반 메뉴 검색 및 업데이트 수행
     * @param dto 수정하고자 하는 메뉴의 정보가 반영된 객체
     * @return 수정하고자 하는 메뉴의 정보가 반영된 객체
     * @param <S> MenuDTO 를 상속받은 클래스
     */
    @Override
    public <S extends MenuDTO> S update(S dto) {

        Connection con = null;
        PreparedStatement ps = null;
        int result=0;

        String sql = "UPDATE MENU SET PRICE=? WHERE MENU_NAME=?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getMenuPrice());
            ps.setString(2,dto.getMenuName());
            result=ps.executeUpdate();

            return dto;

        } catch (SQLException e) {
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     * 메뉴의 Primary Key 를 통해 메뉴의 가격을 수정
     * @param menuId 가격을 수정하고자 하는 메뉴의 Primary Key
     * @param price 수정하고자 하는 목표 가격
     * @return 성공 1, 실패 0
     */
    @Override
    public int updatePriceByMenuId(Long menuId, int price) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        String sql = "UPDATE MENU SET PRICE=? WHERE MENU_ID = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,price);
            ps.setLong(2,menuId);
            result=ps.executeUpdate();

            return result;
        } catch (SQLException e) {
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     * 메뉴의 이름을 통해 메뉴의 가격을 수정
     * @param menuName 가격을 수정하고자 하는 메뉴의 이름
     * @param price 수정하고자 하는 목표 가격
     * @return 성공 1, 실패 0
     */
    @Override
    public int updatePriceByMenuName(String menuName, int price) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        String sql = "UPDATE MENU SET PRICE=? WHERE MENU_NAME = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,price);
            ps.setString(2,menuName);
            result=ps.executeUpdate();

            return result;
        } catch (SQLException e) {
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     * 메뉴 이름기반으로 메뉴 검색
     * @param menuName 정보를 조회하고자 하는 메뉴의 이름
     * @return 조회된 정보가 반영된 객체
     */
    @Override
    public MenuDTO findMenuByMenuName(String menuName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        String sql="SELECT * FROM MENU WHERE MENU_NAME = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, menuName);
            rs=ps.executeQuery();
            MenuDTO dto=null;

            while(rs.next()){
                //객체에 메뉴 아이디에 해당하는 정보 저장
                dto = new MenuDTO(rs.getLong("menu_id"),rs.getString("menu_name"),
                        rs.getInt("menu_price"),rs.getInt("menu_calorie"));
            }

            return dto;

        } catch (SQLException e) {
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     * 메뉴 ID기반으로 메뉴 검색
     * @param MenuId 조회하고자 하는 메뉴의 Primary Key
     * @return 조회한 정보가 반영된 객체
     */
    @Override
    public MenuDTO findById(Long MenuId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        String sql="SELECT * FROM MENU WHERE MENU_ID=?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, MenuId);
            rs=ps.executeQuery();
            MenuDTO dto=null;

            while(rs.next()){
                //객체에 메뉴 아이디에 해당하는 정보 저장
                dto = new MenuDTO(rs.getLong("menu_id"),rs.getString("menu_name"),
                        rs.getInt("menu_price"),rs.getInt("menu_calorie"));
            }

            return dto;

        } catch (SQLException e) {
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     * DB에 저장된 모든 메뉴 조회
     * @return DB에 저장된 모든 메뉴 목록을 반환
     */
    @Override
    public Iterable<MenuDTO> findAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        List<MenuDTO> list=new ArrayList<>();
        String sql="SELECT * FROM MENU";
        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                MenuDTO dto=new MenuDTO(
                        rs.getLong("menu_id"),
                        rs.getString("menu_name"),
                        rs.getInt("menu_price"),
                        rs.getInt("menu_calorie")
                );
                list.add(dto);
            }
            return list;
        }catch(SQLException e){
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(con, ps);
        }

    }

    /**
     * DB에 저장된 모든 메뉴의 수를 반환
     * @return DB에 저장된 모든 메뉴의 수
     */
    @Override
    public long count() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count (*) from menu";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();

            return rs.getLong(1);
        } catch (SQLException e) {
            throw new BaseException();
        } finally {
            DBManager.releaseConnection(con, ps);

        }
    }

    /**
     * 메뉴의 Primary Key 를 통해 특정 메뉴 삭제
     * @param Menuid 삭제하고자 하는 메뉴의 Primary Key
     */
    @Override
    public void deleteById(Long Menuid) {
            Connection con = null;
            PreparedStatement ps = null;
            int result=0;
            String sql="DELETE FROM MENU WHERE MENU_ID=?";

            try {
                con = DBManager.getConnection();
                ps = con.prepareStatement(sql);
                ps.setLong(1,Menuid);
                result= ps.executeUpdate();
            }catch(SQLException e){
                throw new BaseException();
            }finally{
                DBManager.releaseConnection(con, ps);
            }
    }

    /**
     * 삭제하고자 하는 메뉴의 정보가 반영된 객체를 통해 특정 메뉴 삭제
     * @param dto 삭제하고자 하는 메뉴의 정보가 반영된 객체
     */
    @Override
    public void delete(MenuDTO dto) {//이름 삭제로 구현
        Connection con = null;
        PreparedStatement ps = null;
        int result=0;
        String sql="DELETE FROM MENU WHERE MENU_NAME=?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,dto.getMenuName());

            result= ps.executeUpdate();
        }catch(SQLException e){
            throw new BaseException();
        }finally{
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     * DB에 저장된 모든 메뉴 삭제
     */
    @Override
    public void deleteAll() {
        Connection con = null;
        PreparedStatement ps = null;
        int result=0;
        String sql="DELETE FROM MENU";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            result= ps.executeUpdate();
        }catch(SQLException e){
            throw new BaseException();
        }finally{
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     * 메뉴의 이름을 통해 특정 메뉴를 삭제
     * @param menuName 삭제하고자 하는 메뉴의 이름
     */
    @Override
    public void deleteByMenuName(String menuName) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql="DELETE FROM MENU WHERE MENU_NAME=?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, menuName);

        }catch(SQLException e){
            throw new BaseException();
        }finally{
            DBManager.releaseConnection(con, ps);
        }
    }
}

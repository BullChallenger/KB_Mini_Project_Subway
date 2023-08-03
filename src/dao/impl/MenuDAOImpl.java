package dao.impl;
import common.DBManager;
import dao.MenuDAO;
import dto.MenuDTO;

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
            throw new RuntimeException(e);
        }finally{
            DBManager.releaseConnection(con,ps);
        }


    }
    /**
     * 이름 기반 메뉴 검색 및 업데이트 수행
     **/
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
            throw new RuntimeException(e);
        } finally {
            DBManager.releaseConnection(con, ps);
        }


    }

    /**
     * 메뉴 ID기반으로 메뉴 검색
     * @param MenuId
     * @return
     */
    @Override
    public MenuDTO findById(Long MenuId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        String sql="SELECT * FROM MENU WHERE MENUID=?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, MenuId);
            rs=ps.executeQuery();
            MenuDTO dto=null;

            while(rs.next()){
                //객체에 메뉴 아이디에 해당하는 정보 저장
                dto=new MenuDTO(rs.getInt("meu_id"),rs.getString("menu_name"),
                        rs.getInt("menu_price"),rs.getInt("menu_calorie"));
            }

            return dto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.releaseConnection(con, ps);
        }
    }

    /**
     *
     * 메뉴 전체 출력
     **/
    @Override
    public Iterable<MenuDTO> findAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        List<MenuDTO> list=new ArrayList<>();
        String sql="SELECT * FROM MENU?";
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
            throw new RuntimeException(e);
        } finally {
            DBManager.releaseConnection(con, ps);
        }

    }

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
            throw new RuntimeException(e);
        } finally {
            DBManager.releaseConnection(con, ps);

        }
    }


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
                throw new RuntimeException(e);
            }finally{
                DBManager.releaseConnection(con, ps);
            }
            }


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
            throw new RuntimeException(e);
        }finally{
            DBManager.releaseConnection(con, ps);
        }
    }


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
            throw new RuntimeException(e);
        }finally{
            DBManager.releaseConnection(con, ps);
        }
    }
}

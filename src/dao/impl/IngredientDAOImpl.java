package dao.impl;

import common.DBManager;
import dao.IngredientDAO;
import dto.IngredientDTO;
import exception.base.BaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO<IngredientDTO, Long> {

    private static final IngredientDAOImpl instance = new IngredientDAOImpl();

    private IngredientDAOImpl(){
    }

    public static IngredientDAOImpl getInstance() {return instance;}


    @Override
    public <S extends IngredientDTO> S save(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO MEMBER (INGREDIENT_NAME, STOCK, INGREDIENT_PRICE, INGREDIENT_CALORIE, INGREDIENT_CATEGORY) VALUES(?, ?, ?, ?, ?)";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, dto.getIngredientName());
            pstm.setInt(2, dto.getStock());
            pstm.setInt(3, dto.getIngredientPrice());
            pstm.setInt(4, dto.getIngredientCalorie());
            pstm.setInt(5, dto.getIngredientCategory());
            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public <S extends IngredientDTO> S update(S dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE INGREDIENT SET INGREDIENT_NAME = ?, STOCK = ?, INGREDIENT_PRICE = ?, INGREDIENT_CALORIE = ?, INGREDIENT_CATEGORY = ? WHERE INGREDIENT_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, dto.getIngredientName());
            pstm.setInt(2, dto.getStock());
            pstm.setInt(3, dto.getIngredientPrice());
            pstm.setInt(4, dto.getIngredientCalorie());
            pstm.setInt(5, dto.getIngredientCategory());
            pstm.setLong(6, dto.getIngredientId());
            pstm.executeUpdate();

            return dto;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public IngredientDTO findById(Long ingredientId) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT INGREDIENT_ID, INGREDIENT_NAME, STOCK, INGREDIENT_PRICE, INGREDIENT_CALORIE, INGREDIENT_CATEGORY FROM INGREDIENT WHERE INGREDIENT_ID = " + ingredientId;
        IngredientDTO theIngredient = new IngredientDTO();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            theIngredient.setIngredientId(rs.getLong(1));
            theIngredient.setIngredientName(rs.getString(2));
            theIngredient.setStock(rs.getInt(3));
            theIngredient.setIngredientPrice(rs.getInt(4));
            theIngredient.setIngredientCalorie(rs.getInt(5));
            theIngredient.setIngredientCategory(rs.getInt(6));

            return theIngredient;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public Iterable findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT INGREDIENT_ID, INGREDIENT_NAME, STOCK, INGREDIENT_PRICE, INGREDIENT_CALORIE, INGREDIENT_CATEGORY FROM INGREDIENT";

        List<IngredientDTO> ingredientList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                IngredientDTO theIngredient = new IngredientDTO();

                theIngredient.setIngredientId(rs.getLong(1));
                theIngredient.setIngredientName(rs.getString(2));
                theIngredient.setStock(rs.getInt(3));
                theIngredient.setIngredientPrice(rs.getInt(4));
                theIngredient.setIngredientCalorie(rs.getInt(5));
                theIngredient.setIngredientCategory(rs.getInt(6));

                ingredientList.add(theIngredient);
            }

            return ingredientList;
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
        String sql = "SELECT COUNT(*) FROM INGREDIENT;";

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
    public void deleteById(Long ingredientId) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM INGREDIENT WHERE INGREDIENT_ID = " + ingredientId;

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
    public void delete(IngredientDTO dto) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM INGREDIENT WHERE INGREDIENT_ID = ?";

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, dto.getIngredientId());
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
        String sql = "DELETE FROM INGREDIENT";

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
    public Iterable<IngredientDTO> findByIngredientCategory(int ingredientCategory) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT INGREDIENT_ID, INGREDIENT_NAME, STOCK, INGREDIENT_PRICE, INGREDIENT_CALORIE, INGREDIENT_CATEGORY FROM INGREDIENT WHERE INGREDIENT_CATEGORY = "+ingredientCategory;

        List<IngredientDTO> ingredientList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                IngredientDTO theIngredient = new IngredientDTO();

                theIngredient.setIngredientId(rs.getLong(1));
                theIngredient.setIngredientName(rs.getString(2));
                theIngredient.setStock(rs.getInt(3));
                theIngredient.setIngredientPrice(rs.getInt(4));
                theIngredient.setIngredientCalorie(rs.getInt(5));
                theIngredient.setIngredientCategory(rs.getInt(6));

                ingredientList.add(theIngredient);
            }
            return ingredientList;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }



    @Override
    public IngredientDTO findByIngredientName(String ingredientName) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT INGREDIENT_ID, INGREDIENT_NAME, STOCK, INGREDIENT_PRICE, INGREDIENT_CALORIE, INGREDIENT_CATEGORY FROM INGREDIENT WHERE INGREDIENT_NAME = " + ingredientName;
        IngredientDTO theIngredient = new IngredientDTO();

        try {
            conn = DBManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            theIngredient.setIngredientId(rs.getLong(1));
            theIngredient.setIngredientName(rs.getString(2));
            theIngredient.setStock(rs.getInt(3));
            theIngredient.setIngredientPrice(rs.getInt(4));
            theIngredient.setIngredientCalorie(rs.getInt(5));
            theIngredient.setIngredientCategory(rs.getInt(6));

            return theIngredient;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, st, rs);
        }
    }

    @Override
    public IngredientDTO updateIngredientStockByIngredientId(Long ingredientId, int stock) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE INGREDIENT SET STOCK = ? WHERE INGREDIENT_ID = " + ingredientId;
        IngredientDTO theIngredient = new IngredientDTO();

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, stock);
            pstm.executeUpdate();

            return theIngredient;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public IngredientDTO updateIngredientStockByIngredientName(String ingredientName, int stock) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE INGREDIENT SET STOCK = ? WHERE INGREDIENT_NAME = " + ingredientName;
        IngredientDTO theIngredient = new IngredientDTO();

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, stock);
            pstm.executeUpdate();

            return theIngredient;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public IngredientDTO updateIngredientPriceByIngredientId(Long ingredientId, int price) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE INGREDIENT SET PRICE = ? WHERE INGREDIENT_ID = " + ingredientId;
        IngredientDTO theIngredient = new IngredientDTO();

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, price);
            pstm.executeUpdate();

            return theIngredient;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    @Override
    public IngredientDTO updateIngredientPriceByIngredientName(String ingredientName, int stock) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE INGREDIENT SET PRICE = ? WHERE INGREDIENT_NAME = " + ingredientName;
        IngredientDTO theIngredient = new IngredientDTO();

        try {
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, stock);
            pstm.executeUpdate();

            return theIngredient;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }


}

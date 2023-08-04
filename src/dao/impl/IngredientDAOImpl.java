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

    /**
     * 재료 DB에 저장
     * @param dto 저장하고자 하는 재료의 정보가 반영된 객체
     * @return DB에 저장된 재료의 정보가 반영된 객체
     * @param <S> IngredientDTO 를 상속받는 클래스
     */
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

    /**
     * DB에 저장된 재료 정보 수정
     * @param dto 수정하고자 하는 재료의 정보가 반영된 객체
     * @return 수정한 재료의 정보가 반영된 객체
     * @param <S> IngredientDTO 를 상속받는 클래스
     */
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

    /**
     * 재료의 Primary Key 값을 통해 DB에 저장된 재료 정보를 반환
     * @param ingredientId 재료의 Primary Key
     * @return 해당 PK를 지닌 재료의 정보를 담고있는 객체
     */
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

    /**
     * 모든 재료 목록을 반환
     * @return 모든 재료 목록
     */
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

    /**
     * DB에 저장되어 있는 모든 재료의 수를 반환
     * @return DB에 저장되어 있는 모든 재료의 수
     */
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

    /**
     * 재료의 Primary Key 값을 통해 DB에 저장된 재료 삭제
     * @param ingredientId 삭제하고자 하는 재료의 Primary Key
     */
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

    /**
     * 삭제하고자 하는 재료의 정보를 반영한 객체를 통해 삭제
     * @param dto 삭제하고자 하는 재료의 정보를 반영한 객체
     */
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

    /**
     * DB에 저장되어 있는 모든 재료 삭제
     */
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

    /**
     * 재료의 카테고리를 기준으로 재료 목록 반환
     * @param ingredientCategory 재료의 카테고리
     * @return 해당 카테고리에 포함되는 재료 목록
     */
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

    /**
     * 재료 이름을 통해 DB에 저장되어 있는 재료 정보 반환
     * @param ingredientName 정보를 찾고자 하는 재료의 이름
     * @return 파라미터 값과 동일한 이름을 가진 재료의 정보를 담은 객체
     */
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

    /**
     * 재료의 Primary Key 를 통해 특정 재료의 재고를 수정
     * @param ingredientId 재고를 수정하고자 하는 재료의 Primary Key
     * @param stock 새롭게 갱신하려고 하는 재고
     * @return 재고가 수정된 재료의 정보가 반영된 객체
     */
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

            theIngredient.setIngredientId(ingredientId);
            theIngredient.setStock(stock);

            return theIngredient;
        }catch (SQLException e) {
            throw new BaseException();
        }finally {
            DBManager.releaseConnection(conn, pstm);
        }
    }

    /**
     * 재료의 이름을 통해 특정 재료의 재고를 수정
     * @param ingredientName 재고를 수정하고자 하는 재료의 이름
     * @param stock 새롭게 갱신하려고 하는 재고
     * @return 재고가 수정된 재료의 정보가 반영된 객체
     */
    @Override
    public IngredientDTO updateIngredientStockByIngredientName(String ingredientName, int stock) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE INGREDIENT SET STOCK = ? WHERE INGREDIENT_NAME = '" + ingredientName + "'";

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

    /**
     * 재료의 Primary Key 를 통해 특정 재료의 가격을 수정
     * @param ingredientId 가격을 수정하고자 하는 재료의 Primary Key
     * @param price 새롭게 갱신하려고 하는 가격
     * @return 가격이 수정된 재료의 정보가 반영된 객체
     */
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

    /**
     * 재료의 이름을 통해 특정 재료의 가격을 수정
     * @param ingredientName 가격을 수정하고자 하는 재료의 이름
     * @param price 새롭게 갱신하려고 하는 가격
     * @return 가격이 수정된 재료의 정보가 반영된 객체
     */
    @Override
    public IngredientDTO updateIngredientPriceByIngredientName(String ingredientName, int price) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE INGREDIENT SET PRICE = ? WHERE INGREDIENT_NAME = " + ingredientName;
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


}

package dao;

import dto.IngredientDTO;

<<<<<<< HEAD
public interface IngredientDAO extends BaseDAO<IngredientDTO, Long> {
=======
public interface IngredientDAO<IngredientDTO, Long> extends BaseDAO<IngredientDTO, Long> {
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea

    /**
     * @method findByIngredientCategory : 재료 카테고리 별로 재료 정보 검색
     * @param ingredientCategory
     * @return Iterable<IngredientDTO>
     */
<<<<<<< HEAD
    Iterable<IngredientDTO> findByIngredientCategory(String ingredientCategory);
=======
    Iterable<IngredientDTO> findByIngredientCategory(int ingredientCategory);
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea

    /**


    /**
     * @method findByIngredientName : 재료 이름을 통해 재료 검색
     * @param ingredientName
<<<<<<< HEAD
     * @return Optional<IngredientDTO>
     */
    IngredientDTO findByIngredientName(String ingredientName);
=======
     * @return IngredientDTO
     * */
    IngredientDTO findByIngredientName(String ingredientName);

    /**
     * @method updateIngredientStockByIngredientId : 재료 Id 를 통해 재료의 재고만 업데이트
     * @param ingredientId
     * @param stock
     * @return
     */
    IngredientDTO updateIngredientStockByIngredientId(Long ingredientId, int stock);

    /**
     * @method updateIngredientStockByIngredientName : 재료 이름을 통해 재료의 재고만 업데이트
     * @param ingredientName
     * @param stock
     * @return
     */
    IngredientDTO updateIngredientStockByIngredientName(String ingredientName, int stock);

    /**
     * @method updateIngredientPriceByIngredientId : 재료 Id를 통해 재료의 가격만 업데이트
     * @param ingredientId
     * @param price
     * @return
     */
    IngredientDTO updateIngredientPriceByIngredientId(Long ingredientId, int price);

    /**
     * @method updateIngredientPriceByIngredientName : 재료 이름을 통해 재료의 가격만 업데이트
     * @param ingredientName
     * @param stock
     * @return
     */
    IngredientDTO updateIngredientPriceByIngredientName(String ingredientName, int stock);
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
}

package dao;

import dto.IngredientDTO;

public interface IngredientDAO<IngredientDTO, Long> extends BaseDAO<IngredientDTO, Long> {

    /**
     * @method findByIngredientCategory : 재료 카테고리 별로 재료 정보 검색
     * @param ingredientCategory
     * @return Iterable<IngredientDTO>
     */
    Iterable<IngredientDTO> findByIngredientCategory(int ingredientCategory);

    /**


    /**
     * @method findByIngredientName : 재료 이름을 통해 재료 검색
     * @param ingredientName
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
}

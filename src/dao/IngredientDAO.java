package dao;

import dto.IngredientDTO;

public interface IngredientDAO<IngredientDTO, Long> extends BaseDAO<IngredientDTO, Long> {

    /**
     * @method findByIngredientCategory : 재료 카테고리 별로 재료 정보 검색
     * @param ingredientCategory
     * @return Iterable<IngredientDTO>
     */
    Iterable<IngredientDTO> findByIngredientCategory(String ingredientCategory);

    /**


    /**
     * @method findByIngredientName : 재료 이름을 통해 재료 검색
     * @param ingredientName
     * @return Optional<IngredientDTO>
     */
    IngredientDTO findByIngredientName(String ingredientName);
}

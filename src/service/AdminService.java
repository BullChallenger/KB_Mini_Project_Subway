package service;

import dto.IngredientDTO;

import java.util.List;

public interface AdminService {

    /* Ingredient */
    IngredientDTO save(IngredientDTO dto);

    List<IngredientDTO> findAllIngredient();

    IngredientDTO findByIngredientId(Long ingredientId);

    IngredientDTO findByIngredientName(String ingredientName);

    IngredientDTO update(IngredientDTO dto);

    IngredientDTO updateStockByIngredientId(Long ingredientId, int stock);

    IngredientDTO updateStockByIngredientName(String ingredientName, int stock);

    IngredientDTO updatePriceByIngredientId(Long ingredientId, int price);

    IngredientDTO updatePriceByIngredientName(String ingredientName, int price);

    void deleteByIngredientId(Long ingredientId);

    void deleteByIngredientName(String ingredientName);

    /* Menu */

}

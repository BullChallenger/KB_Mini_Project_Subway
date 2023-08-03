package dao;

import dto.IngredientDTO;

import java.util.List;

public interface ComposeDAO {

<<<<<<< HEAD
=======


>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
    /**
     * 메뉴 Id를 통해 Ingredient 에 대한 정보를 받아온다
     * @param menuId
     * @return
     */
    List<IngredientDTO> findByMenuId(Long menuId);
<<<<<<< HEAD


=======
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea
}

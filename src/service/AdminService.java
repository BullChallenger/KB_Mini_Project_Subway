package service;

import dto.ComposeDTO;
import dto.IngredientDTO;
import dto.MenuDTO;

import java.util.List;

public interface AdminService {

    /**
     * @method saveIngredient : 재료 DB에 저장
     * @param dto
     * @return
     */
    IngredientDTO saveIngredient(IngredientDTO dto);

    /**
     * @method findAllIngredient : DB에 저장되어 있는 모든 재료 반환
     * @return
     */
    List<IngredientDTO> findAllIngredient();

    /**
     * @method findByIngredientId : 재료 Id 를 통해 DB에 저장되어 있는 재료 반환
     * @param ingredientId
     * @return
     */
    IngredientDTO findByIngredientId(Long ingredientId);

    /**
     * @method findByIngredientName : 재료 이름을 통해 DB에 저장되어 있는 재료 반환
     * @param ingredientName
     * @return
     */
    IngredientDTO findByIngredientName(String ingredientName);

    /**
     * @method updateIngredient : DB에 저장된 재료 정보 업데이트
     * @param dto
     * @return
     */
    IngredientDTO updateIngredient(IngredientDTO dto);

    /**
     * @method updateStockByIngredientId : DB에 저장된 재료의 재고를 id 값을 통해 갱신
     * @param ingredientId
     * @param stock
     * @return
     */
    IngredientDTO updateStockByIngredientId(Long ingredientId, int stock);

    /**
     * @method updateStockByIngredientName : DB에 저장된 재료의 재고를 이름을 통해 갱신
     * @param ingredientName
     * @param stock
     * @return
     */
    IngredientDTO updateStockByIngredientName(String ingredientName, int stock);

    /**
     * @method updatePriceByIngredientId : DB에 저장된 재료의 가격을 id 값을 통해 갱신
     * @param ingredientId
     * @param price
     * @return
     */
    IngredientDTO updatePriceByIngredientId(Long ingredientId, int price);

    /**
     * @method updatePriceByIngredientName : DB에 저장된 재료의 가격을 이름을 통해 갱신
     * @param ingredientName
     * @param price
     * @return
     */
    IngredientDTO updatePriceByIngredientName(String ingredientName, int price);

    /**
     * @method deleteByIngredientId : id를 통해 DB에 저장된 재료를 삭제
     * @param ingredientId
     */
    void deleteByIngredientId(Long ingredientId);

    /**
     * @method deleteByIngredientName : 이름을 통해 DB에 저장된 재료를 삭제
     * @param ingredientName
     */
    void deleteByIngredientName(String ingredientName);

    /* Menu */
    /**
     * @method saveMenu : 메뉴 DB에 저장
     * @param dto
     * @return
     */
    MenuDTO saveMenu(MenuDTO dto);

    /**
     * @method findAllMenu : DB에 저장되어 있는 모든 메뉴 반환
     * @return
     */
    List<MenuDTO> findAllMenu();

    /**
     * @method findByMenuId : 메뉴 Id 를 통해 DB에 저장되어 있는 메뉴 반환
     * @param menuId
     * @return
     */
    MenuDTO findByMenuId(Long menuId);

    /**
     * @method findByMenuName : 메뉴 이름을 통해 DB에 저장되어 있는 메뉴 반환
     * @param menuName
     * @return
     */
    MenuDTO findByMenuName(String menuName);

    /**
     * @method updateMenu : DB에 저장된 메뉴 정보 업데이트
     * @param dto
     * @return
     */
    MenuDTO updateMenu(MenuDTO dto);

    /**
     * @method updatePriceByMenuId : DB에 저장된 메뉴의 가격을 id 값을 통해 갱신
     * @param menuId
     * @param price
     * @return
     */
    MenuDTO updatePriceByMenuId(Long menuId, int price);

    /**
     * @method updatePriceByMenuName : DB에 저장된 메뉴의 가격을 이름을 통해 갱신
     * @param menuName
     * @param price
     * @return
     */
    MenuDTO updatePriceByMenuName(String menuName, int price);

    /**
     * @method deleteByMenuId : id를 통해 DB에 저장된 메뉴를 삭제
     * @param menuId
     */
    void deleteByMenuId(Long menuId);

    /**
     * @method deleteByMenuName : 이름을 통해 DB에 저장된 메뉴를 삭제
     * @param menuName
     */
    void deleteByMenuName(String menuName);

    /* Compose */

    /**
     * @method saveCompose : 구성 재료 정보 DB에 저장
     * @param dto
     * @return
     */
    ComposeDTO saveCompose(ComposeDTO dto);

    /**
     * @method updateCompose : 구성 재료 정보 갱신
     * @param dto
     * @return
     */
    ComposeDTO updateCompose(ComposeDTO dto);

    /**
     * @method findComposeByMenuId : 메뉴 id를 통해 구성 재료 정보 반환
     * @param menuId
     * @return
     */
    List<ComposeDTO> findComposeByMenuId(Long menuId);

    /**
     * @method findComposeByIngredientId : 재료 id를 통해 구성 재료 정보 반환
     * @param ingredientId
     * @return
     */
    List<ComposeDTO> findComposeByIngredientId(Long ingredientId);

    /**
     * @method deleteComposeByMenuId : 메뉴 id를 통해 구성 재료 삭제
     * @param menuId
     */
    void deleteComposeByMenuId(Long menuId);

    /**
     * @method deleteComposeByIngredientId : 재료 id를 통해 구성 재료 삭제
     * @param ingredientId
     */
    void deleteComposeByIngredientId(Long ingredientId);

    /**
     * @method deleteComposeByMenuIdAndIngredientId : 메뉴 id와 재료 id를 통해 구성 재료 삭제
     * @param menuId
     * @param ingredientId
     */
    void deleteComposeByMenuIdAndIngredientId(Long menuId, Long ingredientId);
}

package dao;

public interface MenuDAO<MenuDTO, Long> extends BaseDAO<MenuDTO, Long> {

    MenuDTO findMenuByMenuName(String menuName);

    int updatePriceByMenuId(Long menuId, int price);

    int updatePriceByMenuName(String menuName, int price);

    void deleteByMenuName(String menuName);
}

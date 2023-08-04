package service.impl;

import dao.IngredientDAO;
import dao.MenuDAO;
import dao.impl.IngredientDAOImpl;
import dao.impl.MenuDAOImpl;
import dto.ComposeDTO;
import dto.IngredientDTO;
import dto.MenuDTO;
import exception.base.BaseException;
import exception.admin.AdminException;
import service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private static final IngredientDAO ingredientDAO = IngredientDAOImpl.getInstance();
    private static final MenuDAO menuDAO = MenuDAOImpl.getInstance();

    private static final AdminServiceImpl instance = new AdminServiceImpl();

    private AdminServiceImpl() {}

    public static AdminServiceImpl getInstance() {return instance;}

    /**
     * method saveIngredient: IngredientDTO를 저장
     * @param dto 저장할 IngredientDTO
     * @return 저장된 IngredientDTO
     */
    @Override
    public IngredientDTO saveIngredient(IngredientDTO dto) {
        try {
            return (IngredientDTO) ingredientDAO.save(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method findAllIngredient(): 모든 Ingredient 조회
     * @return IngredientDTO Type List
     */
    @Override
    public List<IngredientDTO> findAllIngredient() {
        try {
            return (List<IngredientDTO>) ingredientDAO.findAll();
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method findByIngredientID: 재료 ID를 통해 IngredientDTO를 조회
     * @param ingredientId 조회할 재료 ID
     * @return
     */
    @Override
    public IngredientDTO findByIngredientId(Long ingredientId) {
        try {
            return (IngredientDTO) ingredientDAO.findById(ingredientId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method findByIngredientName: 재료명을 통해 IngredientDTO 조회
     * @param ingredientName 조회할 재료 명
     * @return 조회된 IngredientDTO
     */
    @Override
    public IngredientDTO findByIngredientName(String ingredientName) {
        try {
            return (IngredientDTO) ingredientDAO.findByIngredientName(ingredientName);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updateIngredient: 재료 정보를 수정
     * @param dto 수정할 정보가 담긴 재료 DTO
     * @return 수정된 재료 DTO
     */
    @Override
    public IngredientDTO updateIngredient(IngredientDTO dto) {
        try {
            return (IngredientDTO) ingredientDAO.update(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updateStockByIngredientId: 재료 ID를 통한 재료 재고 수정
     * @param ingredientId 재고 수정할 재료 ID
     * @param stock 수정할 재고량
     * @return 재고가 수정된 재료 DTO
     */
    @Override
    public IngredientDTO updateStockByIngredientId(Long ingredientId, int stock) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientStockByIngredientId(ingredientId, stock);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updateStockByIngredientName: 재료 이름을 통한 재료 재고 수정
     * @param ingredientName 수정할 재료명
     * @param stock 수정할 재고량
     * @return 재고가 수정된 재료 DTO
     */
    @Override
    public IngredientDTO updateStockByIngredientName(String ingredientName, int stock) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientStockByIngredientName(ingredientName, stock);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updatePriceByIngredientId: 재료 ID를 통해 가격 수정
     * @param ingredientId 가격을 수정할 재료 ID
     * @param price 수정할 가격
     * @return 수정된 재료 DTO
     */
    @Override
    public IngredientDTO updatePriceByIngredientId(Long ingredientId, int price) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientPriceByIngredientId(ingredientId, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updatePriceByIngredientName: 재료명을 통해 가격 수정
     * @param ingredientName 가격을 수정할 재료명
     * @param price 수정할 가격
     * @return 수정된 재료 DTO
     */
    @Override
    public IngredientDTO updatePriceByIngredientName(String ingredientName, int price) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientPriceByIngredientName(ingredientName, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method deleteByIngredientId: 재료 ID를 통해 재료 삭제
     * @param ingredientId 삭제할 재료 ID
     */
    @Override
    public void deleteByIngredientId(Long ingredientId) {
        try {
            ingredientDAO.deleteById(ingredientId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method deleteByIngredientName: 재료명으로 재료 삭제
     * @param ingredientName 삭제할 재료 명
     */
    @Override
    public void deleteByIngredientName(String ingredientName) {
        try {
            ingredientDAO.delete(ingredientDAO.findByIngredientName(ingredientName));
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method saveMenu: 메뉴 추가
     * @param dto 추가할 메뉴 DTO
     * @return 저장된 메뉴 DTO
     */
    @Override
    public MenuDTO saveMenu(MenuDTO dto) {
        try {
            return (MenuDTO) menuDAO.save(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method findAllMenu: 모든 메뉴 조호;
     * @return 메뉴 DTO 리스트
     */
    @Override
    public List<MenuDTO> findAllMenu() {
        try {
            return (List<MenuDTO>) menuDAO.findAll();
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method findByMenuId: 메뉴 ID를 통해 메뉴 조회
     * @param menuId 조회할 메뉴 ID
     * @return 조회한 메뉴 DTO
     */
    @Override
    public MenuDTO findByMenuId(Long menuId) {
        try {
            return (MenuDTO) menuDAO.findById(menuId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method findByMenuName: 메뉴명으로 메뉴 조회
     * @param menuName 조회할 메뉴명
     * @return 조회한 메뉴 DTO
     */
    @Override
    public MenuDTO findByMenuName(String menuName) {
        try {
            return (MenuDTO) menuDAO.findMenuByMenuName(menuName);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updateMenu: 메뉴 정보 수정
     * @param dto 수정할 메뉴 DTO
     * @return 수정된 메뉴 DTO
     */
    @Override
    public MenuDTO updateMenu(MenuDTO dto) {
        try {
            return (MenuDTO) menuDAO.update(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updatePriceByMenuId: 메뉴 ID를 통해 메뉴 가격 수정
     * @param menuId 가격 수정할 메뉴 ID
     * @param price 수정할 가격
     * @return 수정 성공 1, 수정 실패 0
     */
    @Override
    public int updatePriceByMenuId(Long menuId, int price) {
        try {
            return menuDAO.updatePriceByMenuId(menuId, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method updatePriceByMenuName: 메뉴명을 통해 메뉴 가격 수정
     * @param menuName 수정할 메뉴 이름
     * @param price 수정할 가격
     * @return 수정 성공 1, 수정 실패 0
     */
    @Override
    public int updatePriceByMenuName(String menuName, int price) {
        try {
            return menuDAO.updatePriceByMenuName(menuName, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method deleteByMenuId: 메뉴 ID를 통한 메뉴 삭제
     * @param menuId 삭제할 메뉴 ID
     */
    @Override
    public void deleteByMenuId(Long menuId) {
        try {
            menuDAO.deleteById(menuId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    /**
     * method deleteByMenuName: 메뉴 명을 통해 메뉴 삭제
     * @param menuName 삭제할 메뉴 명
     */
    @Override
    public void deleteByMenuName(String menuName) {
        try {
            menuDAO.deleteByMenuName(menuName);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    @Deprecated
    public ComposeDTO saveCompose(ComposeDTO dto) {
        return null;
    }

    @Override
    @Deprecated
    public ComposeDTO updateCompose(ComposeDTO dto) {
        return null;
    }

    @Override
    @Deprecated
    public List<ComposeDTO> findComposeByMenuId(Long menuId) {
        return null;
    }

    @Override
    @Deprecated
    public List<ComposeDTO> findComposeByIngredientId(Long ingredientId) {
        return null;
    }

    @Override
    @Deprecated
    public void deleteComposeByMenuId(Long menuId) {

    }

    @Override
    @Deprecated
    public void deleteComposeByIngredientId(Long ingredientId) {

    }

    @Override
    @Deprecated
    public void deleteComposeByMenuIdAndIngredientId(Long menuId, Long ingredientId) {

    }
}

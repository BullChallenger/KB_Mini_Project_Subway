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

    //get instance 생성
    private static final AdminServiceImpl instance = new AdminServiceImpl();

    private AdminServiceImpl() {}

    public static AdminServiceImpl getInstance() {return instance;}

    @Override
    public IngredientDTO saveIngredient(IngredientDTO dto) {
        try {
            return (IngredientDTO) ingredientDAO.save(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public List<IngredientDTO> findAllIngredient() {
        try {
            return (List<IngredientDTO>) ingredientDAO.findAll();
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public IngredientDTO findByIngredientId(Long ingredientId) {
        try {
            return (IngredientDTO) ingredientDAO.findById(ingredientId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public IngredientDTO findByIngredientName(String ingredientName) {
        try {
            return (IngredientDTO) ingredientDAO.findByIngredientName(ingredientName);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public IngredientDTO updateIngredient(IngredientDTO dto) {
        try {
            return (IngredientDTO) ingredientDAO.update(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public IngredientDTO updateStockByIngredientId(Long ingredientId, int stock) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientStockByIngredientId(ingredientId, stock);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public IngredientDTO updateStockByIngredientName(String ingredientName, int stock) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientStockByIngredientName(ingredientName, stock);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public IngredientDTO updatePriceByIngredientId(Long ingredientId, int price) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientPriceByIngredientId(ingredientId, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public IngredientDTO updatePriceByIngredientName(String ingredientName, int price) {
        try {
            return (IngredientDTO) ingredientDAO.updateIngredientPriceByIngredientName(ingredientName, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public void deleteByIngredientId(Long ingredientId) {
        try {
            ingredientDAO.deleteById(ingredientId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public void deleteByIngredientName(String ingredientName) {
        try {
            ingredientDAO.delete(ingredientDAO.findByIngredientName(ingredientName));
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public MenuDTO saveMenu(MenuDTO dto) {
        try {
            return (MenuDTO) menuDAO.save(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public List<MenuDTO> findAllMenu() {
        try {
            return (List<MenuDTO>) menuDAO.findAll();
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public MenuDTO findByMenuId(Long menuId) {
        try {
            return (MenuDTO) menuDAO.findById(menuId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public MenuDTO findByMenuName(String menuName) {
        try {
            return (MenuDTO) menuDAO.findMenuByMenuName(menuName);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public MenuDTO updateMenu(MenuDTO dto) {
        try {
            return (MenuDTO) menuDAO.update(dto);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public int updatePriceByMenuId(Long menuId, int price) {
        try {
            return menuDAO.updatePriceByMenuId(menuId, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public int updatePriceByMenuName(String menuName, int price) {
        try {
            return menuDAO.updatePriceByMenuName(menuName, price);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        try {
            menuDAO.deleteById(menuId);
        } catch (BaseException e) {
            throw new AdminException();
        }
    }

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

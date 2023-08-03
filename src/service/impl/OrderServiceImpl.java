package service.impl;

import dao.*;
import dao.impl.*;
import dto.AnonymousOrderDTO;
import dto.IngredientDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;
import service.OrderService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final MemberOrderDAO memberOrderDAO = MemberOrderDAOImpl.getInstance();
    private static final AnonymousOrderDAO anonymousOrderDAO = AnonymousOrderDAOImpl.getInstance();
    private static final MenuDAO menuDAO = MenuDAOImpl.getInstance();
    private static final IngredientDAO ingredientDAO = IngredientDAOImpl.getInstance();

    private static final OrderServiceImpl instance = new OrderServiceImpl();
    private OrderServiceImpl(){}
    public static OrderServiceImpl getInstance() { return instance; }
    @Override
    public MemberOrderDTO saveMemberOrder(MemberOrderDTO dto) throws RuntimeException{
        MemberOrderDTO saveMemberOrderDTO = (MemberOrderDTO) memberOrderDAO.save(dto);
        return saveMemberOrderDTO;
    }

    @Override
    public MemberOrderDTO updateMemberOrderStatus(char orderStatus) {
        return null;
    }

    @Override
    public Long countMemberOrderByOrderDate(String orderDate) {
        return null;
    }

    @Override
    public Long countMemberOrderByMenuId(Long menuId) {
        return null;
    }

    @Override
    public Long countMemberOrderByMemberId(Long memberId) {
        return null;
    }

    @Override
    public List<MemberOrderDTO> findMemberOrderByMemberId(Long memberId) {
        return null;
    }

    @Override
    public List<MemberOrderDTO> findMemberOrderByMenuId(Long menuId) {
        return null;
    }

    @Override
    public AnonymousOrderDTO saveAnonymousOrder(MemberOrderDTO dto) {
        return null;
    }

    @Override
    public Long countByAnonymousOrderDate(String orderDate) {
        return null;
    }

    @Override
    public Long countAnonymousOrderByMenuId(Long menuId) {
        return null;
    }

    @Override
    public List<AnonymousOrderDTO> findAnonymousOrderByMenuId(Long menuId) {
        return null;
    }

    @Override
    public List<MenuDTO> findAllMenu() throws RuntimeException {
        Iterable all = menuDAO.findAll();
        ArrayList<MenuDTO> list = new ArrayList<>();
        for (Object o : all) {
            list.add((MenuDTO) o);
        }
        return list;
    }

    @Override
    public List<IngredientDTO> findIngredientByIngredientCategory(int ingredientCategory) throws RuntimeException{
        Iterable ingredientList = ingredientDAO.findByIngredientCategory(ingredientCategory);
        ArrayList<IngredientDTO> list = new ArrayList<>();
        for (Object o : ingredientList) {
            list.add((IngredientDTO) o);
        }
        return list;
    }

    @Override
    public List<MemberOrderDTO> findAllOrderInfo() throws RuntimeException{
        Iterable orderDAOAll = memberOrderDAO.findAll();
        ArrayList<MemberOrderDTO> list = new ArrayList<>();
        for (Object o : orderDAOAll) {
            list.add((MemberOrderDTO) o);
        }
        return list;
    }

    @Override
    public MenuDTO findMenuByMenuId(Long id) throws RuntimeException{
        MenuDTO menuDTO = (MenuDTO) menuDAO.findById(id);
        return menuDTO;
    }
}

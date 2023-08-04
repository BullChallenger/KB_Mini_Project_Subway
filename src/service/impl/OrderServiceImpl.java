package service.impl;

import dao.*;
import dao.impl.*;
import dto.AnonymousOrderDTO;
import dto.IngredientDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;
import exception.base.BaseException;
import exception.order.OrderException;
import service.OrderService;

import java.util.ArrayList;
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
        MemberOrderDTO saveMemberOrderDTO = null;
        try {
            saveMemberOrderDTO = (MemberOrderDTO) memberOrderDAO.save(dto);
            return saveMemberOrderDTO;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    @Override
    public MemberOrderDTO updateMemberOrderStatus(char orderStatus) {
        try {
            return null;
        } catch (BaseException e) {
            throw new OrderException();
        }
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
        try {
            List<MenuDTO> all = (List<MenuDTO>) menuDAO.findAll();
            return all;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    @Override
    public List<IngredientDTO> findIngredientByIngredientCategory(int ingredientCategory) throws RuntimeException{
        Iterable ingredientList = null;
        try {
            ingredientList = ingredientDAO.findByIngredientCategory(ingredientCategory);
        } catch (BaseException e) {
            throw new OrderException();
        }

        ArrayList<IngredientDTO> list = new ArrayList<>();
        for (Object o : ingredientList) {
            list.add((IngredientDTO) o);
        }
        return list;
    }

    @Override
    public List<MemberOrderDTO> findAllOrderInfo() throws RuntimeException{
        try {
            List<MemberOrderDTO> orderDAOAll = (List<MemberOrderDTO>) memberOrderDAO.findAll();
            return orderDAOAll;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    @Override
    public MenuDTO findMenuByMenuId(Long id) throws RuntimeException{
        try {
            MenuDTO menuDTO = (MenuDTO) menuDAO.findById(id);
            return menuDTO;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    @Override
    public int updateOrderStatusByMemberOrderId(Long id) {
        try {
            return memberOrderDAO.updateOrderStatusByMemberOrderId(id);
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    @Override
    public MemberOrderDTO findHistoryByMemberMenuId(Long memberId, Long menuId) {
        try {
            return (MemberOrderDTO) memberOrderDAO.findByMenuIdAndMemberId(menuId, memberId);
        } catch (BaseException e) {
            throw new OrderException();
        }
    }
}

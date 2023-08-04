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

    /**
     * method saveMemberOrder: 멤버 주문 저장 
     * @param dto 저장할 멤버 DTO
     * @return 생성 및 저장된 멤버주문 DTO
     * @throws RuntimeException
     */
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

    /**
     * method updateMemberOrderStatus: 멤버주문의 상태를 수정
     * @param orderStatus 수정할 상태
     * @return 수정된 멤버주문 DTO
     */
    @Override
    public MemberOrderDTO updateMemberOrderStatus(char orderStatus) {
        try {
            return null;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    @Deprecated
    @Override
    public Long countMemberOrderByOrderDate(String orderDate) {
        return null;
    }

    @Deprecated
    @Override
    public Long countMemberOrderByMenuId(Long menuId) {
        return null;
    }

    @Deprecated
    @Override
    public Long countMemberOrderByMemberId(Long memberId) {
        return null;
    }

    @Deprecated
    @Override
    public List<MemberOrderDTO> findMemberOrderByMemberId(Long memberId) {
        return null;
    }

    @Deprecated
    @Override
    public List<MemberOrderDTO> findMemberOrderByMenuId(Long menuId) {
        return null;
    }

    @Deprecated
    @Override
    public AnonymousOrderDTO saveAnonymousOrder(MemberOrderDTO dto) {
        return null;
    }

    @Deprecated
    @Override
    public Long countByAnonymousOrderDate(String orderDate) {
        return null;
    }

    @Deprecated
    @Override
    public Long countAnonymousOrderByMenuId(Long menuId) {
        return null;
    }

    @Deprecated
    @Override
    public List<AnonymousOrderDTO> findAnonymousOrderByMenuId(Long menuId) {
        return null;
    }

    /**
     * method findAllMenu: 모든 메뉴 조회
     * @return 조회된 모든 메뉴 리스트
     * @throws RuntimeException
     */
    @Override
    public List<MenuDTO> findAllMenu() throws RuntimeException {
        try {
            List<MenuDTO> all = (List<MenuDTO>) menuDAO.findAll();
            return all;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    /**
     * method findIngredientByIngredientCategory: 재료 카테고리 바탕으로 재료 조회
     * @param ingredientCategory 재료 카테고리
     * @return 조회된 재료 DTO
     * @throws RuntimeException
     */
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

    /**
     * method findAllOrderInfo: 모든 멤버주문 조회
     * @return 조회된 모든 멤버주문 리스트
     * @throws RuntimeException
     */
    @Override
    public List<MemberOrderDTO> findAllOrderInfo() throws RuntimeException{
        try {
            List<MemberOrderDTO> orderDAOAll = (List<MemberOrderDTO>) memberOrderDAO.findAll();
            return orderDAOAll;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    /**
     * method findMenuByMenuId: 메뉴 ID를 통한 메뉴 조회
     * @param id 조회할 메뉴 ID
     * @return 조회된 메뉴 DTO
     * @throws RuntimeException
     */
    @Override
    public MenuDTO findMenuByMenuId(Long id) throws RuntimeException{
        try {
            MenuDTO menuDTO = (MenuDTO) menuDAO.findById(id);
            return menuDTO;
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    /**
     * method updateOrderStatusByMemberOrderId: 멤버주문 ID를 통한 주문상태 변경
     * @param id 수정할 멤버주문 ID
     * @return 수정성공 1, 수정실패 0
     */
    @Override
    public int updateOrderStatusByMemberOrderId(Long id) {
        try {
            return memberOrderDAO.updateOrderStatusByMemberOrderId(id);
        } catch (BaseException e) {
            throw new OrderException();
        }
    }

    /**
     * method findHistoryByMemberMenuId: 멤버, 메뉴의 ID를 통해 이전의 주문 기록을 조회
     * @param memberId 조회할 멤버 ID
     * @param menuId 조회할 메뉴 ID
     * @return 조회된 멤버주문 DTO
     */
    @Override
    public MemberOrderDTO findHistoryByMemberMenuId(Long memberId, Long menuId) {
        try {
            return (MemberOrderDTO) memberOrderDAO.findByMenuIdAndMemberId(menuId, memberId);
        } catch (BaseException e) {
            throw new OrderException();
        }
    }
}

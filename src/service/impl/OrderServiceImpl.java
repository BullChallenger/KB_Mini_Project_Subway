package service.impl;

import dao.MemberOrderDAO;
import dto.AnonymousOrderDTO;
import dto.MemberOrderDTO;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final OrderServiceImpl instance = new OrderServiceImpl();
    private OrderServiceImpl(){}
    public static OrderServiceImpl getInstance() { return instance; }
    @Override
    public MemberOrderDTO saveMemberOrder(MemberOrderDTO dto) {
        return null;
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
}

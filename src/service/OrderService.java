package service;

import dto.AnonymousOrderDTO;
import dto.MemberOrderDTO;

import java.util.List;

public interface OrderService {

    /* Member Order */
    MemberOrderDTO saveMemberOrder(MemberOrderDTO dto);

    MemberOrderDTO updateMemberOrderStatus(char orderStatus);

    Long countMemberOrderByOrderDate(String orderDate);

    Long countMemberOrderByMenuId(Long menuId);

    Long countMemberOrderByMemberId(Long memberId);

    List<MemberOrderDTO> findMemberOrderByMemberId(Long memberId);

    List<MemberOrderDTO> findMemberOrderByMenuId(Long menuId);

    /* Anonymous Order */
    AnonymousOrderDTO saveAnonymousOrder(MemberOrderDTO dto);

    Long countByAnonymousOrderDate(String orderDate);

    Long countAnonymousOrderByMenuId(Long menuId);

    List<AnonymousOrderDTO> findAnonymousOrderByMenuId(Long menuId);

}
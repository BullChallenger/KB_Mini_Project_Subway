package dao;

import dto.MemberOrderDTO;

import java.util.List;

public interface MemberOrderDAO extends BaseDAO<MemberOrderDTO, Long> {

    Long countByMemberOrderDate(String memberOrderDate);

    Long countByMenuId(Long menuId);

    Long countByMemberId(Long memberId);

    List<MemberOrderDTO> findByMemberId(Long memberId);

    List<MemberOrderDTO> findByMenuId(Long menuId);
}

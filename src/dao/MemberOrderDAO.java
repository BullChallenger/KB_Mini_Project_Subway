package dao;

import dto.MemberOrderDTO;

import java.util.List;

public interface MemberOrderDAO<MemberOrderDTO, Long> extends BaseDAO<MemberOrderDTO, Long> {

    Long countByMemberOrderDate(String memberOrderDate);

    Long countByMenuId(Long menuId);

    Long countByMemberId(Long memberId);

    List<MemberOrderDTO> findByMemberId(Long memberId);

    List<MemberOrderDTO> findByMenuId(Long menuId);

    // 반환할 시 orderDate 기준으로 정렬하여 최상단 DTO 하나만 반환해야 함
    MemberOrderDTO findByMenuIdAndMemberId(Long menuId, Long memberId);
}

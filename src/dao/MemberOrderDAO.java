package dao;

import dto.MemberOrderDTO;

import java.util.List;

<<<<<<< HEAD
public interface MemberOrderDAO extends BaseDAO<MemberOrderDTO, Long> {
=======
public interface MemberOrderDAO<MemberOrderDTO, Long> extends BaseDAO<MemberOrderDTO, Long> {
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea

    Long countByMemberOrderDate(String memberOrderDate);

    Long countByMenuId(Long menuId);

    Long countByMemberId(Long memberId);

    List<MemberOrderDTO> findByMemberId(Long memberId);

    List<MemberOrderDTO> findByMenuId(Long menuId);
}

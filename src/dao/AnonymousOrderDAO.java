package dao;

import dto.AnonymousOrderDTO;

import java.util.List;

public interface AnonymousOrderDAO extends BaseDAO<AnonymousOrderDTO, Long> {

    Long countByAnonymousOrderDate(String AnonymousOrderDate);

    Long countByMenuId(String AnonymousOrderDate);

    List<AnonymousOrderDTO> findByMenuId(Long menuId);
}

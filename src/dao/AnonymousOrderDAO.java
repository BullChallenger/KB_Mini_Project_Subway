package dao;

import dto.AnonymousOrderDTO;

import java.util.List;

public interface AnonymousOrderDAO<AnonymousOrderDTO, Long> extends BaseDAO<AnonymousOrderDTO, Long> {

    Long countByAnonymousOrderDate(String AnonymousOrderDate);

    Long countByMenuId(Long menuId);

    List<AnonymousOrderDTO> findByMenuId(Long menuId);

    List<AnonymousOrderDTO> findByAnonymousOrderId(Long anonymousorderId);
}

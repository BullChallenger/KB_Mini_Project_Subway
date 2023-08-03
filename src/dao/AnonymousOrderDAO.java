package dao;

import dto.AnonymousOrderDTO;

import java.util.List;

<<<<<<< HEAD
public interface AnonymousOrderDAO extends BaseDAO<AnonymousOrderDTO, Long> {
=======
public interface AnonymousOrderDAO<AnonymousOrderDTO, Long> extends BaseDAO<AnonymousOrderDTO, Long> {
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea

    Long countByAnonymousOrderDate(String AnonymousOrderDate);

    Long countByMenuId(String AnonymousOrderDate);

    List<AnonymousOrderDTO> findByMenuId(Long menuId);
}

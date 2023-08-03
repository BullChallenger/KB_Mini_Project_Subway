package dao.impl;
import dao.MenuDAO;
import dto.MenuDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuDAOImpl implements MenuDAO{
    @Override
    public <S extends MenuDTO> S save(S dto) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs= null;
        try{} catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public <S extends MenuDTO> S update(S dto) {
        return null;
    }

    @Override
    public MenuDTO findById(Long aLong) {
        return null;
    }

    @Override
    public Iterable<MenuDTO> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(MenuDTO dto) {

    }

    @Override
    public void deleteAll() {

    }
}

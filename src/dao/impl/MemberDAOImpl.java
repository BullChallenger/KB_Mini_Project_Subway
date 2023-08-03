package dao.impl;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public <S extends MemberDTO> S save(S dto) {
        return null;
    }

    @Override
    public <S extends MemberDTO> S update(S dto) {
        return null;
    }

    @Override
    public MemberDTO findById(Long aLong) {
        return null;
    }

    @Override
    public Iterable<MemberDTO> findAll() {
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
    public void delete(MemberDTO dto) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public MemberDTO findByPhoneNumber(String phoneNumber) {
        return null;
    }
}

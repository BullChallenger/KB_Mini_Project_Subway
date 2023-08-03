package service.impl;

import dao.MemberDAO;
import dao.impl.MemberDAOImpl;
import dto.MemberDTO;
import service.MemberService;

import java.util.List;

public class MemberServiceImpl implements MemberService {

    private static final MemberDAO memberDAO = MemberDAOImpl.getInstance();

    private static final MemberServiceImpl instance = new MemberServiceImpl();

    private MemberServiceImpl() {
    };

    public static MemberServiceImpl getInstance() {
        return instance;
    }

    @Override
    public MemberDTO save(MemberDTO dto) {
        return (MemberDTO) memberDAO.save(dto);
    }

    @Override
    public MemberDTO update(MemberDTO dto) {
        return (MemberDTO) memberDAO.update(dto);
    }

    @Override
    public MemberDTO findByMemberId(Long memberId) {
        return (MemberDTO) memberDAO.findById(memberId);
    }

    @Override
    public List<MemberDTO> findAll() {
        return (List<MemberDTO>) memberDAO.findAll();
    }

    @Override
    public MemberDTO findByPhoneNumber(String phoneNumber) {
        return (MemberDTO) memberDAO.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        memberDAO.deleteById(memberId);
    }

    @Override
    public void delete(MemberDTO dto) {
        memberDAO.delete(dto);
    }
}

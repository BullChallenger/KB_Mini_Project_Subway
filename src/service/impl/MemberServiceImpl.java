package service.impl;

import dao.MemberDAO;
import dao.impl.MemberDAOImpl;
import dto.MemberDTO;
import exception.base.BaseException;
import exception.member.MemberException;
import exception.member.NotMemberException;
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
        try {
            return (MemberDTO) memberDAO.save(dto);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    @Override
    public MemberDTO update(MemberDTO dto) {
        try {
            return (MemberDTO) memberDAO.update(dto);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    @Override
    public MemberDTO findByMemberId(Long memberId) {
        try {
            return (MemberDTO) memberDAO.findById(memberId);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    @Override
    public List<MemberDTO> findAll() {
        try {
            return (List<MemberDTO>) memberDAO.findAll();
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    @Override
    public MemberDTO findByPhoneNumber(String phoneNumber) throws NotMemberException {
        try {
            return (MemberDTO) memberDAO.findByPhoneNumber(phoneNumber);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        try {
            memberDAO.deleteById(memberId);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    @Override
    public void delete(MemberDTO dto) {
        try {
            memberDAO.delete(dto);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }
}

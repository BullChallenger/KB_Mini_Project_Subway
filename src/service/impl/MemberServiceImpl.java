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

    /**
     * method save: 멤버 저장
     * @param dto 저장할 멤버 DTO
     * @return 저장된 멤버 DTO
     */
    @Override
    public MemberDTO save(MemberDTO dto) {
        try {
            return (MemberDTO) memberDAO.save(dto);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    /**
     * method update: 멤버 정보 수정
     * @param dto 수정할 멤버 DTO
     * @return 수정된 멤버 DTO
     */
    @Override
    public MemberDTO update(MemberDTO dto) {
        try {
            return (MemberDTO) memberDAO.update(dto);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    /**
     * method findByMemberId: 멤버 ID를 통한 멤버 조회
     * @param memberId 조회할 멤버 ID
     * @return 조회된 멤버 DTO
     */
    @Override
    public MemberDTO findByMemberId(Long memberId) {
        try {
            return (MemberDTO) memberDAO.findById(memberId);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    /**
     * method findAll: 모든 멤버 조회
     * @return 모든 멤버 DTO Type 리스트
     */
    @Override
    public List<MemberDTO> findAll() {
        try {
            return (List<MemberDTO>) memberDAO.findAll();
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    /**
     * method findByPhoneNumber: 멤버의 전화번호를 통한 조회
     * @param phoneNumber 멤버의 전화번호
     * @return 조회된 멤버 DTO
     * @throws NotMemberException
     */
    @Override
    public MemberDTO findByPhoneNumber(String phoneNumber) throws NotMemberException {
        try {
            return (MemberDTO) memberDAO.findByPhoneNumber(phoneNumber);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    /**
     * method deleteByMemberId: 멤버 ID를 통한 삭제
     * @param memberId 삭제할 멤버 ID
     */
    @Override
    public void deleteByMemberId(Long memberId) {
        try {
            memberDAO.deleteById(memberId);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }

    /**
     * method delete: 멤버 삭제
     * @param dto 삭제할 멤버 DTO
     */
    @Override
    public void delete(MemberDTO dto) {
        try {
            memberDAO.delete(dto);
        } catch (BaseException e) {
            throw new MemberException();
        }
    }
}

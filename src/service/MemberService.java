package service;

import dto.MemberDTO;

import java.util.List;

public interface MemberService {

    MemberDTO save(MemberDTO dto);

    MemberDTO update(MemberDTO dto);

    MemberDTO findByMemberId(Long memberId);

    List<MemberDTO> findAll();

    MemberDTO findByPhoneNumber(String phoneNumber);

    void deleteByMemberId(Long memberId);

    void delete(MemberDTO dto);
}

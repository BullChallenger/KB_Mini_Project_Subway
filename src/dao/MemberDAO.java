package dao;

import dto.MemberDTO;

public interface MemberDAO extends BaseDAO<MemberDTO, Long> {

    /**
     * 핸드폰 번호를 통해 회원 정보 조회
     * @method findByPhoneNumber
     * @param phoneNumber
     * @return
     */
    MemberDTO findByPhoneNumber(String phoneNumber);
}

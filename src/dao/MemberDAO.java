package dao;

import dto.MemberDTO;

<<<<<<< HEAD
public interface MemberDAO extends BaseDAO<MemberDTO, Long> {
=======
public interface MemberDAO<MemberDTO, Long> extends BaseDAO<MemberDTO, Long> {
>>>>>>> ff887acc8aa7f679d7446665769dca3d0a3eedea

    /**
     * 핸드폰 번호를 통해 회원 정보 조회
     * @method findByPhoneNumber
     * @param phoneNumber
     * @return
     */
    MemberDTO findByPhoneNumber(String phoneNumber);
}

package dao;

/**
 * @param <T> : 상속받은 DAO 의 DTO ex) MenuDTO, MemberDTO ...
 * @param <ID> : 상속받은 DAO 의 ID 타입 ex) Long, Integer ...
 */
public interface BaseDAO<T, ID> {

    /**
     * DB 에 추가
     * @param dto
     * @return
     * @param <S>
     */
    <S extends T> S save(S dto);

    /**
     * DB에 적재된 Entity 수정
     * @param dto
     * @return
     * @param <S>
     */
    <S extends T> S update(S dto);

    /**
     * Id 를 통해 특정 Entity 조회
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 모든 Entity 조회
     * @return
     */
    Iterable<T> findAll();

    /**
     * DB 에 적재되어 있는 Entity 의 수 조회
     * @return
     */
    long count();

    /**
     * Id 를 통해 특정 Entity 삭제
     * @param id
     */
    void deleteById(ID id);

    /**
     * dto 정보를 통해 특정 Entity 삭제
     * @param dto
     */
    void delete(T dto);

    /**
     * 모든 Entity 삭제
     */
    void deleteAll();
}

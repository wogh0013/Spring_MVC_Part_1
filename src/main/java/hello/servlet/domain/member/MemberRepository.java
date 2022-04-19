package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤
    private static final MemberRepository instance = new MemberRepository();

    //무조건 이 메서드로 조회해야 함
    public static MemberRepository getInstance(){

        return instance;
    }

    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){

        return store.get(id);
    }

    public List<Member> findAll(){
        //store의 있는 모든 값들을 꺼내서 새로운 ArrayList<>에 넣어준다.
        return new ArrayList<>(store.values());
    }

    //테스트할 때 사용, store를 다 날려버림
    public void clearStore(){

        store.clear();
    }
}

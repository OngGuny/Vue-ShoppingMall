package dumy.shopping.mall.backend.repository;

import dumy.shopping.mall.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findByEmailAndPassword(String email, String password);
}

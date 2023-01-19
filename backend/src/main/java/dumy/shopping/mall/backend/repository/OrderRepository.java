package dumy.shopping.mall.backend.repository;

import dumy.shopping.mall.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    //역순으로 가져오는 메소드 추가
    List<Order> findByMemberIdOrderByIdDesc(int memberId);
}

package dumy.shopping.mall.backend.repository;

import dumy.shopping.mall.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByMemberId(int memberId);
    Cart findByMemberIdAndItemId(int memberId, int ItemId);
    void deleteByMemberId(int memberId);
}

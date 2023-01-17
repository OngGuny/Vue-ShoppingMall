package dumy.shopping.mall.backend.repository;

import dumy.shopping.mall.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}

package dumy.shopping.mall.backend.repository;

import dumy.shopping.mall.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    //아래의 'In' 을 붙여서 리스트를 파라미터로 받을 수 있다............!!!! ㄷㄷㄷ
    List<Item> findByIdIn(List<Integer> ids);

}

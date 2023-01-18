package dumy.shopping.mall.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "carts")
@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //generationtype 차이는 insert 쿼리 실행과 flush 순서, autoincrement 값 가져오기 차이

    @Column
    private int memberId;

    @Column
    private int itemId;
}

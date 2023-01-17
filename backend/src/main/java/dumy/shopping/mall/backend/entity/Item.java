package dumy.shopping.mall.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "items")
@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //generationtype 차이는 insert 쿼리 실행과 flush 순서, autoincrement 값 가져오기 차이
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200)
    private String imgPath;

    @Column
    private int price;

    @Column
    private int discountPer;
}

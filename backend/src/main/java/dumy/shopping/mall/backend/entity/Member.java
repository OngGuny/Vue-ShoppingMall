package dumy.shopping.mall.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "members")
@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //generationtype 차l이는 insert 쿼리 실행과 flush 순서, autoincrement 값 가져오기 차이
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

}

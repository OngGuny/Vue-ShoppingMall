package dumy.shopping.mall.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
     String getToken(String key, Object value);

     Claims getClaims(String token);// 이렇게 API 를 만든거다.

     //주문한 사람이 문제가 없는 사람인지 검증하는 api 하나 추가
     boolean isValid(String token);

     //토큰 정보에서 사용자 아이디 가져오는 메소드도 추가
     int getId(String token);

}

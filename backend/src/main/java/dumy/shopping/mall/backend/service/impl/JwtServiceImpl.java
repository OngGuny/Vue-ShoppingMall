package dumy.shopping.mall.backend.service.impl;

import dumy.shopping.mall.backend.service.JwtService;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("JwtService")
public class JwtServiceImpl implements JwtService {

    private String secretKey = "mudameu2309a!fiut@$dhfkdhrkdfkwkefbaRLditghdn#$@#%efwjnrfwk231$flk23$%@";
    //입력받은 키와 밸류를 시크릿 키를 이용해서 토큰으로 만들어주는 메소드...
    @Override
    public String getToken(String key, Object value) {
        Date expTime = new Date();
        expTime.setTime(expTime.getTime()+1000*60*5);// 이럼 5분.
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();

    }

    @Override // api 구현
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token)){
            try{
                byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
                Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
                Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
                return claims;
            }catch(ExpiredJwtException e){
                //만료 시.
            }catch (JwtException e) {
                //유효하지 않을 떄.
            }
        }
        return null;
    }

    @Override//장바구니 기능 추가하다가 사용자가 증명을 위해 만든 메소드. frontend 에서 하다가 이제 백엔드로 뺸거.
    public boolean isValid(String token) {
        //겟 클레임 메소드 자체가 문제있을 때 null 리턴 하니까 이정도만 해도 괜춘.
        return this.getClaims(token) != null;
    }


    @Override//token 에서 아이디 가져오려고 만들어줌
    public int getId(String token) {
        Claims claims = this.getClaims(token);
        if(claims != null){
            return Integer.parseInt(claims.get("id").toString());
        }
        return 0;
    }
}
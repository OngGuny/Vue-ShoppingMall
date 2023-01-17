package dumy.shopping.mall.backend.service.impl;

import dumy.shopping.mall.backend.service.JwtService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeFactory;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
}
package dumy.shopping.mall.backend.contoller;

import dumy.shopping.mall.backend.entity.Member;
import dumy.shopping.mall.backend.repository.MemberRepository;
import dumy.shopping.mall.backend.service.JwtService;
import dumy.shopping.mall.backend.service.impl.JwtServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
                                        HttpServletResponse res) {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if (member != null) { //로그인 한 멤버가 있으면
            //로그인 아이디를 토큰화 해준다
            int id = member.getId();
            String token = jwtService.getToken("id", id);

            //보안상의 이유로 서버상에서 관리할것이다. 쿠키 사용함
            //저 토큰을 쿠키로 또 만들어서
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            //응답값에 추가해준다.
            res.addCookie(cookie);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/account/check") //api 세팅.
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token){
        Claims claims = jwtService.getClaims(token);

        if(claims != null){
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
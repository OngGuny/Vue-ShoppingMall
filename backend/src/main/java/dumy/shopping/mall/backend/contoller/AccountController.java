package dumy.shopping.mall.backend.contoller;

import dumy.shopping.mall.backend.entity.Item;
import dumy.shopping.mall.backend.entity.Member;
import dumy.shopping.mall.backend.repository.ItemRepository;
import dumy.shopping.mall.backend.repository.MemberRepository;
import dumy.shopping.mall.backend.service.JwtService;
import dumy.shopping.mall.backend.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public String login(@RequestBody Map<String, String> params) {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if (member != null) {
            //단순히 아이디 말고 토큰화 해준다
            JwtService jwtService = new JwtServiceImpl();
            int id = member.getId();
            String token = jwtService.getToken("id", id);

            return token;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
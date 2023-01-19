package dumy.shopping.mall.backend.contoller;

import dumy.shopping.mall.backend.dto.OrderDto;
import dumy.shopping.mall.backend.entity.Cart;
import dumy.shopping.mall.backend.entity.Item;
import dumy.shopping.mall.backend.entity.Order;
import dumy.shopping.mall.backend.repository.CartRepository;
import dumy.shopping.mall.backend.repository.ItemRepository;
import dumy.shopping.mall.backend.repository.OrderRepository;
import dumy.shopping.mall.backend.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;


    @GetMapping("/api/orders")
    public ResponseEntity getOrder(
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);

        List<Order> orders = orderRepository.findByMemberIdOrderByIdDesc(memberId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/api/orders") // 아이템 아이디를 변수로받겠다는것.
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token
    ) {
        //장바구니 담을 떄 권한 확인
        if(!jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        Order newOrder = new Order();
        newOrder.setMemberId(memberId);
        newOrder.setName(dto.getName());//유효성 검사, 등 해주면 좋지.
        newOrder.setAddress(dto.getAddress());
        newOrder.setPayment(dto.getPayment());
        newOrder.setCardNumber(dto.getCardNumber());
        newOrder.setItems(dto.getItems());

        orderRepository.save(newOrder);
        //구매 했으면 카트가 비워져야지.
        cartRepository.deleteByMemberId(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }//postmapping



}//controller
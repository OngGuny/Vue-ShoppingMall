package dumy.shopping.mall.backend.contoller;

import dumy.shopping.mall.backend.entity.Cart;
import dumy.shopping.mall.backend.entity.Item;
import dumy.shopping.mall.backend.repository.CartRepository;
import dumy.shopping.mall.backend.repository.ItemRepository;
import dumy.shopping.mall.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/api/cart/items")//카트 가져오기
    public ResponseEntity getCartItems(
            @CookieValue(value = "token", required = false) String token
    ) {
        if(!jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);
        //카트를 가져오고 나서 어떤 아이템들을 담았는지. 추출해줘야지. 위에는 카트들을 담고있는 리스트. 아래는 카트안에 있는 상품들을 리스트화 한것
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
        //  람다식 '::'  : 메소드 참조 표현식 , 람다식에서 파라미터를 중복해서 쓰기 싫을때.
        // x-> method(x) 이렇게 되있는걸.
        // 객체 :: method  이렇게 씀. -> 람다식이 건내는 x 와 받는 메소드의 파라미터가 동일 할 떄 가능
        List<Item> items = itemRepository.findByIdIn(itemIds);

        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @PostMapping("/api/cart/items/{itemId}") // 아이템 아이디를 변수로받겠다는것.
    public ResponseEntity pushCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {
        //장바구니 담을 떄 권한 확인
        if(!jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
        //카트가 없을 떄, 새로운 카트를 하나 만들어주는것.
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setItemId(itemId);
            newCart.setMemberId(memberId);
            cartRepository.save(newCart);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
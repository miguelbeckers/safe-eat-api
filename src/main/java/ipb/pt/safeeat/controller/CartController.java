package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Cart;
import ipb.pt.safeeat.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(cartService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Cart cart) {
        return ResponseEntity.ok().body(cartService.update(cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        cartService.delete(id);
        return ResponseEntity.ok().build();
    }
}

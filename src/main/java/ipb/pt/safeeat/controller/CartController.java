package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Cart;
import ipb.pt.safeeat.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Cart cart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.create(cart));
    }

    @PostMapping("/many")
    public ResponseEntity<Object> createMany(@Valid @RequestBody List<Cart> carts) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createMany(carts));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Cart cart) {
        return ResponseEntity.ok().body(cartService.update(cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        cartService.delete(id);
        return ResponseEntity.ok().build();
    }
}

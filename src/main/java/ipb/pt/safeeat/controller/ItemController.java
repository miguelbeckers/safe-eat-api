package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(itemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(item));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Item item) {
        return ResponseEntity.ok().body(itemService.update(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }
}

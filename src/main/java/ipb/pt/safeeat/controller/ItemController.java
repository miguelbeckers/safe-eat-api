package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.service.ItemService;
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
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(itemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(item));
    }

    @PostMapping("/many")
    public ResponseEntity<Object> createMany(@Valid @RequestBody List<Item> items) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createMany(items));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Item item) {
        return ResponseEntity.ok().body(itemService.update(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }
}

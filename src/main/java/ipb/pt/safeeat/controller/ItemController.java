package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

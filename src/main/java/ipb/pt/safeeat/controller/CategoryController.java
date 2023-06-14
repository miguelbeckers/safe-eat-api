package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Category;
import ipb.pt.safeeat.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(category));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        return ResponseEntity.ok().build();
    }
}

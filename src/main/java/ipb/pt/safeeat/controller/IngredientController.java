package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Ingredient;
import ipb.pt.safeeat.service.IngredientService;
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
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.create(ingredient));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok().body(ingredientService.update(ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        ingredientService.delete(id);
        return ResponseEntity.ok().build();
    }
}

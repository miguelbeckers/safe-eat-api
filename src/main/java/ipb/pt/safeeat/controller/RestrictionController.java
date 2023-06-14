package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Restriction;
import ipb.pt.safeeat.service.RestrictionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/restrictions")
public class RestrictionController {

    @Autowired
    private RestrictionService restrictionService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(restrictionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        return ResponseEntity.ok(restrictionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Restriction restriction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restrictionService.create(restriction));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Restriction restriction) {
        return ResponseEntity.ok().body(restrictionService.update(restriction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        restrictionService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Restriction;
import ipb.pt.safeeat.service.RestrictionService;
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
@RequestMapping("/restrictions")
public class RestrictionController {

    @Autowired
    private RestrictionService restrictionService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(restrictionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(restrictionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Restriction restriction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restrictionService.create(restriction));
    }

    @PostMapping("/many")
    public ResponseEntity<Object> createMany(@Valid @RequestBody List<Restriction> restrictions) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restrictionService.createMany(restrictions));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Restriction restriction) {
        return ResponseEntity.ok().body(restrictionService.update(restriction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        restrictionService.delete(id);
        return ResponseEntity.ok().build();
    }
}

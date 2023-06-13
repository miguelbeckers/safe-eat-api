package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Delivery;
import ipb.pt.safeeat.service.DeliveryService;
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
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(deliveryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(deliveryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Delivery delivery) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.create(delivery));
    }

    @PostMapping("/many")
    public ResponseEntity<Object> createMany(@Valid @RequestBody List<Delivery> deliveries) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.createMany(deliveries));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Delivery delivery) {
        return ResponseEntity.ok().body(deliveryService.update(delivery));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        deliveryService.delete(id);
        return ResponseEntity.ok().build();
    }
}

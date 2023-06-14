package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Advertisement;
import ipb.pt.safeeat.service.AdvertisementService;
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
@RequestMapping("/advertisements")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(advertisementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(advertisementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Advertisement advertisement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(advertisementService.create(advertisement));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Advertisement advertisement) {
        return ResponseEntity.ok().body(advertisementService.update(advertisement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        advertisementService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Home;
import ipb.pt.safeeat.service.HomeService;
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
@RequestMapping("/homes")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(homeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(homeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Home home) {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.create(home));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Home home) {
        return ResponseEntity.ok().body(homeService.update(home));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        homeService.delete(id);
        return ResponseEntity.ok().build();
    }
}

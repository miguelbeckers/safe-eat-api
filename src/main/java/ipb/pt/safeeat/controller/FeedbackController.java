package ipb.pt.safeeat.controller;

import ipb.pt.safeeat.model.Feedback;
import ipb.pt.safeeat.service.FeedbackService;
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
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(feedbackService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(feedbackService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Feedback feedback) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.create(feedback));
    }

    @PostMapping("/many")
    public ResponseEntity<Object> createMany(@Valid @RequestBody List<Feedback> feedbacks) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.createMany(feedbacks));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody Feedback feedback) {
        return ResponseEntity.ok().body(feedbackService.update(feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        feedbackService.delete(id);
        return ResponseEntity.ok().build();
    }
}

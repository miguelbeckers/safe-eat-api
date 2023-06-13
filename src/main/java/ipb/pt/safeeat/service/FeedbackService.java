package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.*;
import ipb.pt.safeeat.repository.FeedbackRepository;
import ipb.pt.safeeat.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    public Feedback findById(UUID id) {
        return feedbackRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.FEEDBACK_NOT_FOUND));
    }

    public Feedback create(Feedback feedback) {
        Order order = orderRepository.findById(feedback.getOrder().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ORDER_NOT_FOUND));

        Feedback created = feedbackRepository.save(feedback);

        order.setFeedback(created);
        orderRepository.save(order);
        return created;
    }

    public List<Feedback> createMany(List<Feedback> feedbacks) {
        for (Feedback feedback : feedbacks) {
            Order order = orderRepository.findById(feedback.getOrder().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ORDER_NOT_FOUND));
            feedback.setOrder(order);
        }

        List<Feedback> created = feedbackRepository.saveAll(feedbacks);

        for (Feedback feedback : created) {
            Order order = feedback.getOrder();
            order.setFeedback(feedback);
            orderRepository.save(order);
        }

        return created;
    }

    public Feedback update(Feedback feedback) {
        Feedback old = feedbackRepository.findById(feedback.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.FEEDBACK_NOT_FOUND));

        BeanUtils.copyProperties(feedback, old);
        return feedbackRepository.save(feedback);
    }

    public void delete(UUID id) {
        feedbackRepository.deleteById(id);
    }
}

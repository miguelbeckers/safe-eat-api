package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Payment;
import ipb.pt.safeeat.model.ProductSection;
import ipb.pt.safeeat.repository.PaymentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(UUID id) {
        return paymentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.PAYMENT_NOT_FOUND));
    }

    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> createMany(List<Payment> payments) {
        return paymentRepository.saveAll(payments);
    }

    public Payment update(Payment payment) {
        Payment old = paymentRepository.findById(payment.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.PAYMENT_NOT_FOUND));

        BeanUtils.copyProperties(payment, old);
        return paymentRepository.save(payment);
    }

    public void delete(UUID id) {
        paymentRepository.deleteById(id);
    }
}

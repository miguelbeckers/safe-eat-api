package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.DeliveryConstants;
import ipb.pt.safeeat.model.Delivery;
import ipb.pt.safeeat.repository.DeliveryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public Delivery findById(String id) {
        return deliveryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, DeliveryConstants.NOT_FOUND));
    }

    public Delivery create(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery update(Delivery delivery) {
        Delivery old = deliveryRepository.findById(delivery.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, DeliveryConstants.NOT_FOUND));

        BeanUtils.copyProperties(delivery, old);
        return deliveryRepository.save(delivery);
    }

    public void delete(String id) {
        deliveryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, DeliveryConstants.NOT_FOUND));
                
        deliveryRepository.deleteById(id);
    }
}

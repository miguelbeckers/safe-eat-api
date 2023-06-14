package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.*;
import ipb.pt.safeeat.model.Restaurant;
import ipb.pt.safeeat.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(String id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant old = restaurantRepository.findById(restaurant.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));

        BeanUtils.copyProperties(restaurant, old);
        return restaurantRepository.save(restaurant);
    }

    public void delete(String id) {
        restaurantRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));

        restaurantRepository.deleteById(id);
    }
}
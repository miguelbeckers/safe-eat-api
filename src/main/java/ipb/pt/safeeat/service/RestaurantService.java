package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Restaurant;
import ipb.pt.safeeat.model.Restriction;
import ipb.pt.safeeat.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(UUID id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_NOT_FOUND));
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> createMany(List<Restaurant> restaurants) {
        return restaurantRepository.saveAll(restaurants);
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant old = restaurantRepository.findById(restaurant.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_NOT_FOUND));

        BeanUtils.copyProperties(restaurant, old);
        return restaurantRepository.save(restaurant);
    }

    public void delete(UUID id) {
        restaurantRepository.deleteById(id);
    }
}

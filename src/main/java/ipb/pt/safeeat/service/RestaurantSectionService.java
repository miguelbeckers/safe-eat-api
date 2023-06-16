package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.RestaurantConstants;
import ipb.pt.safeeat.constants.RestaurantSectionConstants;
import ipb.pt.safeeat.model.Restaurant;
import ipb.pt.safeeat.model.RestaurantSection;
import ipb.pt.safeeat.repository.RestaurantRepository;
import ipb.pt.safeeat.repository.RestaurantSectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantSectionService {
    @Autowired
    private RestaurantSectionRepository restaurantSectionRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantSection> getAll() {
        return restaurantSectionRepository.findAll();
    }

    public RestaurantSection findById(String id) {
        return restaurantSectionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantSectionConstants.NOT_FOUND));
    }

    public RestaurantSection create(RestaurantSection restaurantSection) {
        checkDependencies(restaurantSection);
        return restaurantSectionRepository.save(restaurantSection);
    }

    private void checkDependencies(RestaurantSection restaurantSection) {
        for(Restaurant restaurant: restaurantSection.getRestaurants()){
            restaurantRepository.findById(restaurant.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));
        }
    }

    public RestaurantSection update(RestaurantSection restaurantSection) {
        RestaurantSection old = restaurantSectionRepository.findById(restaurantSection.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantSectionConstants.NOT_FOUND));

        checkDependencies(restaurantSection);
        BeanUtils.copyProperties(restaurantSection, old);
        return restaurantSectionRepository.save(restaurantSection);
    }

    public void delete(String id) {
        restaurantSectionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantSectionConstants.NOT_FOUND));

        restaurantSectionRepository.deleteById(id);
    }
}

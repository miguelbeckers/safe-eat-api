package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Restaurant;
import ipb.pt.safeeat.model.RestaurantSection;
import ipb.pt.safeeat.repository.RestaurantSectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantSectionService {
    @Autowired
    private RestaurantSectionRepository restaurantSectionRepository;

    public List<RestaurantSection> getAll() {
        return restaurantSectionRepository.findAll();
    }

    public RestaurantSection findById(UUID id) {
        return restaurantSectionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_SECTION_NOT_FOUND));
    }

    public RestaurantSection create(RestaurantSection restaurantSection) {
        return restaurantSectionRepository.save(restaurantSection);
    }

    public List<RestaurantSection> createMany(List<RestaurantSection> restaurantSections) {
        return restaurantSectionRepository.saveAll(restaurantSections);
    }

    public RestaurantSection update(RestaurantSection restaurantSection) {
        RestaurantSection old = restaurantSectionRepository.findById(restaurantSection.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_SECTION_NOT_FOUND));

        BeanUtils.copyProperties(restaurantSection, old);
        return restaurantSectionRepository.save(restaurantSection);
    }

    public void delete(UUID id) {
        restaurantSectionRepository.deleteById(id);
    }
}

package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Advertisement;
import ipb.pt.safeeat.model.Restaurant;
import ipb.pt.safeeat.repository.AdvertisementRepository;
import ipb.pt.safeeat.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Advertisement findById(UUID id) {
        return advertisementRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ADVERTISEMENT_NOT_FOUND));
    }

    public Advertisement create(Advertisement advertisement) {
        Restaurant restaurant = restaurantRepository.findById(advertisement.getRestaurant().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_NOT_FOUND));

        Advertisement created = advertisementRepository.save(advertisement);

        restaurant.getAdvertisements().add(created);
        restaurantRepository.save(restaurant);
        return created;
    }

    public List<Advertisement> createMany(List<Advertisement> advertisements) {
        for (Advertisement advertisement : advertisements) {
            Restaurant restaurant = restaurantRepository.findById(advertisement.getRestaurant().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_NOT_FOUND));
            advertisement.setRestaurant(restaurant);
        }

        List<Advertisement> created = advertisementRepository.saveAll(advertisements);

        for (Advertisement advertisement : created) {
            Restaurant restaurant = advertisement.getRestaurant();
            restaurant.getAdvertisements().add(advertisement);
            restaurantRepository.save(restaurant);
        }

        return created;
    }

    public Advertisement update(Advertisement advertisement) {
        Advertisement old = advertisementRepository.findById(advertisement.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ADVERTISEMENT_NOT_FOUND));

        BeanUtils.copyProperties(advertisement, old);
        return advertisementRepository.save(advertisement);
    }

    public void delete(UUID id) {
        advertisementRepository.deleteById(id);
    }
}


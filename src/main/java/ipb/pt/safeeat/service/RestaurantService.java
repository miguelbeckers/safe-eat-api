package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.*;
import ipb.pt.safeeat.model.*;
import ipb.pt.safeeat.repository.*;
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
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSectionRepository productSectionRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Restaurant> getAll() {
        try {
            return restaurantRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Restaurant findById(String id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));
    }

    public List<Restaurant> findByOwner(String id) {
        User owner = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        return owner.getRestaurants();
    }

    public Restaurant create(Restaurant restaurant) {
        if(restaurant.getOwner() == null || restaurant.getOwner().getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The owner is required.");
        }

        if(restaurant.getDeliveries() != null && !restaurant.getDeliveries().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deliveries are not accepted. Use create delivery instead.");
        }

        if(restaurant.getProducts() != null && !restaurant.getProducts().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Products are not accepted. Use create product instead.");
        }

        if(restaurant.getProductSections() != null && !restaurant.getProductSections().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product sections are not accepted. Use create product section instead.");
        }

        if(restaurant.getAdvertisements() != null && !restaurant.getAdvertisements().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Advertisements are not accepted. Use create advertisement instead.");
        }

        if(restaurant.getOrders() != null && !restaurant.getOrders().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Orders are not accepted. Use create order instead.");
        }

        if(restaurant.getCategories() != null && !restaurant.getCategories().isEmpty()) {
            for (Category category : restaurant.getCategories()) {
                categoryRepository.findById(category.getId()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CategoryConstants.NOT_FOUND));
            }
        }

        User owner = userRepository.findById(restaurant.getOwner().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        Restaurant created = restaurantRepository.save(restaurant);
        owner.getRestaurants().add(created);
        userRepository.save(owner);

        return created;
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
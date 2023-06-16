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
        return restaurantRepository.findAll();
    }

    public Restaurant findById(String id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));
    }

    public Restaurant create(Restaurant restaurant) {
        if(restaurant.getDeliveries() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, RestaurantConstants.NOT_ACCEPTED);
        }

        if(restaurant.getProducts() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ProductConstants.NOT_ACCEPTED);
        }

        if(restaurant.getProductSections() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, RestaurantSectionConstants.NOT_ACCEPTED);
        }

        if(restaurant.getAdvertisements() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AdvertisementConstants.NOT_ACCEPTED);
        }

        if(restaurant.getOrders() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, OrderConstants.NOT_ACCEPTED);
        }

        checkDependencies(restaurant);

        User owner = userRepository.findById(restaurant.getOwner().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        Restaurant created = restaurantRepository.save(restaurant);
        owner.getRestaurants().add(created);
        userRepository.save(owner);

        return created;
    }

    private void checkDependencies(Restaurant restaurant) {
        for(Category category: restaurant.getCategories()) {
            categoryRepository.findById(category.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CategoryConstants.NOT_FOUND));
        }
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant old = restaurantRepository.findById(restaurant.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));

        if(old.getOwner() != restaurant.getOwner()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserConstants.CHANGED);
        }

        checkDependencies(restaurant);

        BeanUtils.copyProperties(restaurant, old);
        return restaurantRepository.save(restaurant);
    }

    public void delete(String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));

        for(Delivery delivery : restaurant.getDeliveries()) {
            deliveryRepository.deleteById(delivery.getId());
        }

        for(Product product : restaurant.getProducts()) {
            productRepository.deleteById(product.getId());
        }

        for(ProductSection productSection : restaurant.getProductSections()) {
            productSectionRepository.deleteById(productSection.getId());
        }

        for(Advertisement advertisement : restaurant.getAdvertisements()) {
            advertisementRepository.deleteById(advertisement.getId());
        }

        //TODO: delete the restaurant form all the restaurant sections

        restaurantRepository.deleteById(id);
    }
}
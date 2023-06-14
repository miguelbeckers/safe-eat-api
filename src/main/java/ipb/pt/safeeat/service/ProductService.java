package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.CategoryConstants;
import ipb.pt.safeeat.constants.IngredientConstants;
import ipb.pt.safeeat.constants.ProductConstants;
import ipb.pt.safeeat.constants.RestaurantConstants;
import ipb.pt.safeeat.model.*;
import ipb.pt.safeeat.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    //TODO change the isRestrict property according to the current user
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductConstants.NOT_FOUND));
    }

    public Product create(Product product) {
        checkDependencies(product);

        Restaurant restaurant = restaurantRepository.findById(product.getRestaurant().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));

        Product created = productRepository.save(product);
        restaurant.getProducts().add(created);
        restaurantRepository.save(restaurant);

        return created;
    }

    private void checkDependencies(Product product) {
        for(Ingredient ingredient : product.getIngredients()) {
            ingredientRepository.findById(ingredient.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, IngredientConstants.NOT_FOUND));
        }

        for(Category category : product.getCategories()) {
            categoryRepository.findById(category.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CategoryConstants.NOT_FOUND));
        }
    }

    public Product update(Product product) {
        Product old = productRepository.findById(product.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductConstants.NOT_FOUND));

        checkDependencies(product);
        BeanUtils.copyProperties(product, old);
        return productRepository.save(product);
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}

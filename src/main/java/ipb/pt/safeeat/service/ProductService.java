package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Product;
import ipb.pt.safeeat.model.RestaurantSection;
import ipb.pt.safeeat.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.PRODUCT_NOT_FOUND));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> createMany(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product update(Product product) {
        Product old = productRepository.findById(product.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.PRODUCT_NOT_FOUND));

        BeanUtils.copyProperties(product, old);
        return productRepository.save(product);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}

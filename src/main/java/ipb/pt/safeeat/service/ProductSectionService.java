package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ProductConstants;
import ipb.pt.safeeat.constants.ProductSectionConstants;
import ipb.pt.safeeat.model.Product;
import ipb.pt.safeeat.model.ProductSection;
import ipb.pt.safeeat.repository.ProductRepository;
import ipb.pt.safeeat.repository.ProductSectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductSectionService {
    @Autowired
    private ProductSectionRepository productSectionRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductSection> getAll() {
        return productSectionRepository.findAll();
    }

    public ProductSection findById(UUID id) {
        return productSectionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductSectionConstants.INVALID));
    }

    public ProductSection create(ProductSection productSection) {
        checkDependencies(productSection);

        return productSectionRepository.save(productSection);
    }

    private void checkDependencies(ProductSection productSection) {
        for(Product product : productSection.getProducts()) {
            productRepository.findById(product.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductConstants.NOT_FOUND));
        }
    }

    public ProductSection update(ProductSection productSection) {
        ProductSection old = productSectionRepository.findById(productSection.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductSectionConstants.NOT_FOUND));

        checkDependencies(productSection);
        BeanUtils.copyProperties(productSection, old);
        return productSectionRepository.save(productSection);
    }

    public void delete(UUID id) {
        productSectionRepository.deleteById(id);
    }
}

package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Product;
import ipb.pt.safeeat.model.ProductSection;
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

    public List<ProductSection> getAll() {
        return productSectionRepository.findAll();
    }

    public ProductSection findById(UUID id) {
        return productSectionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.PRODUCT_SECTION_NOT_FOUND));
    }

    public ProductSection create(ProductSection productSection) {
        return productSectionRepository.save(productSection);
    }

    public List<ProductSection> createMany(List<ProductSection> productSections) {
        return productSectionRepository.saveAll(productSections);
    }

    public ProductSection update(ProductSection productSection) {
        ProductSection old = productSectionRepository.findById(productSection.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.PRODUCT_SECTION_NOT_FOUND));

        BeanUtils.copyProperties(productSection, old);
        return productSectionRepository.save(productSection);
    }

    public void delete(UUID id) {
        productSectionRepository.deleteById(id);
    }
}

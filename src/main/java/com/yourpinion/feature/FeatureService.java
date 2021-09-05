package com.yourpinion.feature;

import com.yourpinion.product.Product;
import com.yourpinion.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FeatureRepository featureRepository;

    public Feature createFeature(Long productId) {
        Feature feature = new Feature();

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            feature.setProduct(product);
            product.getFeatures().add(feature);

            feature.setStatus(Status.NEW);

            return featureRepository.save(feature);
        }
        return feature;
    }

    public Feature save(Feature feature) {
        return featureRepository.save(feature);
    }

    public Optional<Feature> findById(Long featureId) {
        return featureRepository.findById(featureId);
    }
}

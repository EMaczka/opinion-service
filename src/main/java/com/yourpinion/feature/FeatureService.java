package com.yourpinion.feature;

import com.yourpinion.product.Product;
import com.yourpinion.product.ProductRepository;
import com.yourpinion.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FeatureRepository featureRepository;

    public Feature createFeature(Long productId, User user) {
        Feature feature = new Feature();

        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            feature.setProduct(product);
            product.getFeatures().add(feature);

            feature.setUser(user);
            user.getFeatures().add(feature);

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

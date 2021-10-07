package com.yourpinion.feature;

import com.yourpinion.comment.Comment;
import com.yourpinion.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    Logger log = LoggerFactory.getLogger(FeatureController.class);

    @Autowired
    private FeatureService featureService;

    @PostMapping("")
    public String createFeature(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        Feature feature = featureService.createFeature(productId, user);

        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }

    @GetMapping("{featureId}")
    public String getFeature(@AuthenticationPrincipal User user, ModelMap modelMap, @PathVariable Long productId, @PathVariable Long featureId) {
        Optional<Feature> featureOpt = featureService.findById(featureId);
        if (featureOpt.isPresent()) {
            Feature feature = featureOpt.get();
            modelMap.put("feature", feature);
            SortedSet<Comment> commentsWithoutDuplicates = getCommentsWithoutDuplicates(0, new HashSet<Long>(), feature.getComments());
            modelMap.put("thread", commentsWithoutDuplicates);
            modelMap.put("comment", new Comment());
        }
        modelMap.put("user", user);

        return "feature";
    }

    private SortedSet<Comment> getCommentsWithoutDuplicates(int page, Set<Long> visitedComments, SortedSet<Comment> comments) {
        page++;
        Iterator<Comment> iterator = comments.iterator();
        while (iterator.hasNext()) {
            Comment comment = iterator.next();
            boolean addedToVisitedComments = visitedComments.add(comment.getId());
            if (!addedToVisitedComments) {
                iterator.remove();
                if (page != 1)
                    return comments;
            }
            if (addedToVisitedComments && !comment.getComments().isEmpty())
                getCommentsWithoutDuplicates(page, visitedComments, comment.getComments());
        }

        return comments;
    }

    @PostMapping("{featureId}")
    public String updateFeature(@AuthenticationPrincipal User user, Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
        feature.setUser(user);
        feature = featureService.save(feature);
        String encodedProductName;
        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("Unable to encode the URL string: " + feature.getProduct().getName() + ", redirecting to dashboard");
            return "redirect:/dashboard";
        }

        return "redirect:/p/" + encodedProductName;
    }
}

package com.yourpinion.comment;

import com.yourpinion.feature.Feature;
import com.yourpinion.feature.FeatureRepository;
import com.yourpinion.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features/{featureId}/comments")
public class CommentController {
    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public FeatureRepository featureRepository;

    @GetMapping("")
    @ResponseBody
    public List<Comment> getComments(@PathVariable Long featureId) {

        List<Comment> findByFeatureId = commentRepository.findByFeatureId(featureId);
        System.out.println(findByFeatureId);
        return findByFeatureId;
    }

    @PostMapping("")
    public String postComment(@AuthenticationPrincipal User user, @PathVariable Long productId,
                              @PathVariable Long featureId, Comment comment) {
        Optional<Feature> featureOpt = featureRepository.findById(featureId);

        if (featureOpt.isPresent())
            comment.setFeature(featureOpt.get());
        comment.setUser(user);
        comment.setCreatedDate(new Date());

        commentRepository.save(comment);

        return "redirect:/products/" + productId + "/features/" + featureId;
    }
}

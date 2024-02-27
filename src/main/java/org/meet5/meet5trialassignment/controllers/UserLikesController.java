package org.meet5.meet5trialassignment.controllers;

import org.meet5.meet5trialassignment.repositories.UserProfilesRepository;
import org.meet5.meet5trialassignment.services.ProfileVisitsService;
import org.meet5.meet5trialassignment.services.UserLikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class UserLikesController {

    private final UserLikesService userLikesService;

    public UserLikesController(UserLikesService userLikesService) {
        this.userLikesService = userLikesService;
    }

    @PostMapping("/{visitorId}/to/{likedProfileId}")
    public ResponseEntity<String> recordUserLikes(
            @PathVariable("visitorId") Integer visitorId,
            @PathVariable("likedProfileId") Integer likedProfileId) {
        userLikesService.recordUserLikes(visitorId, likedProfileId);
        return ResponseEntity.ok("User Likes recorded successfully.");
    }
}

package org.meet5.meet5trialassignment.controllers;
import org.meet5.meet5trialassignment.models.ProfileVisits;
import org.meet5.meet5trialassignment.services.ProfileVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class ProfileVisitsController {

    private final ProfileVisitsService profileVisitsService;

    @Autowired
    public ProfileVisitsController(ProfileVisitsService profileVisitsService) {
        this.profileVisitsService = profileVisitsService;
    }

    @GetMapping("/user/{userId}")
    public List<ProfileVisits> getProfileVisitsByUserIdSorted(@PathVariable Integer userId) {
        return profileVisitsService.getProfileVisitsByUserIdSorted(userId);
    }


    @PostMapping("/{visitorId}/to/{visitedProfileId}")
    public ResponseEntity<String> recordProfileVisit(
            @PathVariable("visitorId") Integer visitorId,
            @PathVariable("visitedProfileId") Integer visitedProfileId) {
        profileVisitsService.recordProfileVisit(visitorId, visitedProfileId);
        return ResponseEntity.ok("Profile visit recorded successfully.");
    }
}

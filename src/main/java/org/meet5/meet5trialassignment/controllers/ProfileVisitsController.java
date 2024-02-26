package org.meet5.meet5trialassignment.controllers;
import org.meet5.meet5trialassignment.models.ProfileVisits;
import org.meet5.meet5trialassignment.services.ProfileVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile-visits")
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
}

package org.meet5.meet5trialassignment.services;
import org.meet5.meet5trialassignment.models.ProfileVisits;
import org.meet5.meet5trialassignment.models.UserProfiles;
import org.meet5.meet5trialassignment.repositories.ProfileVisitsRepository;
import org.meet5.meet5trialassignment.repositories.UserProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProfileVisitsService {

    private final ProfileVisitsRepository profileVisitsRepository;
    private final UserProfilesRepository userProfilesRepository;

    @Autowired
    public ProfileVisitsService(ProfileVisitsRepository profileVisitsRepository, UserProfilesRepository userProfilesRepository) {
        this.profileVisitsRepository = profileVisitsRepository;
        this.userProfilesRepository = userProfilesRepository;
    }

    public List<ProfileVisits> getProfileVisitsByUserIdSorted(Integer userId) {
        return profileVisitsRepository.getProfileVisitsByUserIdSorted(userId);
    }

    public void recordProfileVisit(Integer visitorId, Integer visitedProfileId) {

        boolean visitorExists = userProfilesRepository.userExistsById(visitorId);
        boolean visitedProfileExists = userProfilesRepository.userExistsById(visitedProfileId);

        if (visitorExists && visitedProfileExists) {
            profileVisitsRepository.saveProfileVisit(visitorId, visitedProfileId);;
        } else {
            throw new IllegalArgumentException("Invalid visitorId or visitedProfileId");
        }
    }
}
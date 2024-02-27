package org.meet5.meet5trialassignment.services;

import org.meet5.meet5trialassignment.repositories.ProfileVisitsRepository;
import org.meet5.meet5trialassignment.repositories.UserLikesRepository;
import org.meet5.meet5trialassignment.repositories.UserProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLikesService {

    private final UserLikesRepository userLikesRepository;
    private final UserProfilesRepository userProfilesRepository;
    private final FraudDetectionService fraudDetectionService;



    @Autowired
    public UserLikesService(UserProfilesRepository userProfilesRepository, UserLikesRepository userLikesRepository, FraudDetectionService fraudDetectionService) {
        this.userProfilesRepository = userProfilesRepository;
        this.userLikesRepository = userLikesRepository;
        this.fraudDetectionService = fraudDetectionService;
    }
    public void recordUserLikes(Integer visitorId, Integer likedUserId) {

        boolean visitorExists = userProfilesRepository.userExistsById(visitorId);
        boolean likedUserExists = userProfilesRepository.userExistsById(likedUserId);

        if (visitorExists && likedUserExists) {
            if(!fraudDetectionService.isFraudulentActivity(visitorId, "user_id","UserLikes", "liked_at", 10, 100))
            {
                userLikesRepository.saveUserLikes(visitorId, likedUserId);

            } else {
                    throw new IllegalArgumentException("Fraud Alert.");
                }
        } else {
            throw new IllegalArgumentException("Invalid visitorId or likedProfileId");
        }
    }
}

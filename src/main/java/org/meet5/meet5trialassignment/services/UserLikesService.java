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


    @Autowired
    public UserLikesService(UserProfilesRepository userProfilesRepository, UserLikesRepository userLikesRepository) {
        this.userProfilesRepository = userProfilesRepository;
        this.userLikesRepository = userLikesRepository;
    }
    public void recordUserLikes(Integer visitorId, Integer likedUserId) {

        boolean visitorExists = userProfilesRepository.userExistsById(visitorId);
        boolean likedUserExists = userProfilesRepository.userExistsById(likedUserId);

        if (visitorExists && likedUserExists) {
            userLikesRepository.saveUserLikes(visitorId, likedUserId);;
        } else {
            throw new IllegalArgumentException("Invalid visitorId or likedProfileId");
        }
    }
}

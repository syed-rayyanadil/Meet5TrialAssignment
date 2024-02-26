package org.meet5.meet5trialassignment.services;
import org.meet5.meet5trialassignment.models.ProfileVisits;
import org.meet5.meet5trialassignment.repositorys.ProfileVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileVisitsService {

    private final ProfileVisitsRepository profileVisitsRepository;

    @Autowired
    public ProfileVisitsService(ProfileVisitsRepository profileVisitsRepository) {
        this.profileVisitsRepository = profileVisitsRepository;
    }

    public List<ProfileVisits> getProfileVisitsByUserIdSorted(Integer userId) {
        return profileVisitsRepository.getProfileVisitsByUserIdSorted(userId);
    }
}
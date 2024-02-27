package org.meet5.meet5trialassignment.services;

import org.meet5.meet5trialassignment.repositories.FraudDetectionRepository;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionService {

    private final FraudDetectionRepository fraudDetectionRepository;

    public FraudDetectionService(FraudDetectionRepository fraudDetectionRepository) {
        this.fraudDetectionRepository = fraudDetectionRepository;
    }

    public boolean isFraudulentActivity(Integer userId, String idColumnName, String tableName, String timestampColumn, int thresholdTimeInMinutes, int thresholdCount) {
        int count = fraudDetectionRepository.countFraudActivitiesWithinTimePeriod(userId, idColumnName, tableName, timestampColumn, thresholdTimeInMinutes);
        return count >= thresholdCount;
    }
}

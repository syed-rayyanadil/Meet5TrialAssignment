package org.meet5.meet5trialassignment.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FraudDetectionRepository {

    private final JdbcTemplate jdbcTemplate;
    public FraudDetectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countFraudActivitiesWithinTimePeriod(Integer userId, String idColumnName,  String tableName, String timestampColumn, int thresholdTimeInMinutes) {

        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + idColumnName+ " = ? AND " + timestampColumn + " >= NOW() - INTERVAL ? MINUTE";

        return jdbcTemplate.queryForObject(sql, Integer.class, userId, thresholdTimeInMinutes);
    }

}

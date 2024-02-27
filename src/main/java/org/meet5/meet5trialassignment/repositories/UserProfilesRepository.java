package org.meet5.meet5trialassignment.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfilesRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserProfilesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean userExistsById (Integer id){
            String sql = "SELECT COUNT(*) > 0 FROM UserProfiles WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }
}

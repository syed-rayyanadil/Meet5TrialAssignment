package org.meet5.meet5trialassignment.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserLikesRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserLikesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUserLikes(Integer visitorId, Integer likedUserId){
        String sql = "INSERT INTO UserLikes (user_id, liked_profile_id, liked_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, visitorId, likedUserId, new Date());
    }

}

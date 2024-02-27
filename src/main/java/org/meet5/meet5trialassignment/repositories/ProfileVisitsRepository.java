package org.meet5.meet5trialassignment.repositories;
import org.meet5.meet5trialassignment.models.ProfileVisits;
import org.meet5.meet5trialassignment.models.UserProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class ProfileVisitsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileVisitsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProfileVisits> getProfileVisitsByUserIdSorted(Integer userId) {
        String sql = "SELECT pv.id, pv.visitor_id, pv.visited_profile_id, pv.visited_at, " +
                "up_visitor.first_name AS visitor_first_name, up_visitor.last_name AS visitor_last_name, " +
                "up_visitor.age AS visitor_age, up_visitor.email AS visitor_email, up_visitor.date_of_birth AS visitor_date_of_birth " +
                "FROM ProfileVisits pv " +
                "JOIN UserProfiles up_visitor ON pv.visitor_id = up_visitor.id " +
                "WHERE pv.visited_profile_id = ? " +
                "ORDER BY pv.visited_at DESC";


        return jdbcTemplate.query(sql, new Object[]{userId}, profileVisitsRowMapper());
    }

    private RowMapper<ProfileVisits> profileVisitsRowMapper() {
        return new RowMapper<ProfileVisits>() {
            @Override
            public ProfileVisits mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                ProfileVisits profileVisits = new ProfileVisits();
                profileVisits.setId(resultSet.getInt("id"));

                UserProfiles visitor = new UserProfiles();
                visitor.setId(resultSet.getInt("visitor_id"));
                visitor.setFirstName(resultSet.getString("visitor_first_name"));
                visitor.setLastName(resultSet.getString("visitor_last_name"));
                visitor.setAge(resultSet.getInt("visitor_age"));
                visitor.setEmail(resultSet.getString("visitor_email"));
                visitor.setDateOfBirth(resultSet.getDate("visitor_date_of_birth"));

                // No need to retrieve details for the visited profile

                profileVisits.setVisitor(visitor);
                // No need to set details for the visited profile
                profileVisits.setVisitedAt(resultSet.getTimestamp("visited_at"));

                return profileVisits;
            }
        };
    }

    public void saveProfileVisit(Integer visitorId, Integer visitedProfileId){
        String sql = "INSERT INTO ProfileVisits (visitor_id, visited_profile_id, visited_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, visitorId, visitedProfileId, new Date());
    }
}
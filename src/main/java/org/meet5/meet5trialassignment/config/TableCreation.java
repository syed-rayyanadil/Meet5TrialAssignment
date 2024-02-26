package org.meet5.meet5trialassignment.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.json.*;


@Component
public class TableCreation {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void createTables() {

//         Create UserProfiles table
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS UserProfiles (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "first_name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "age INT, " +
                "email VARCHAR(255), " +
                "date_of_birth DATE, " +
                "user_defined_fields JSON)");

        // Create ProfileVisits table
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ProfileVisits (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "visitor_id INT, " +
                "visited_profile_id INT, " +
                "visited_at TIMESTAMP, " +
                "FOREIGN KEY (visited_profile_id) REFERENCES UserProfiles(id))");

        // Create UserLikes table
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS UserLikes (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "user_id INT, " +
                "liked_profile_id INT, " +
                "liked_at TIMESTAMP, " +
                "FOREIGN KEY (liked_profile_id) REFERENCES UserProfiles(id))");
    }

    public void generateDummyData(int numberOfUsers) {
        Random random = new Random();

        // Generate UserProfiles data first
        for (int i = 1; i <= numberOfUsers; i++) {
            String firstName = "User" + i;
            String lastName = "Lastname" + i;
            int age = random.nextInt(50) + 18;
            String email = "user" + i + "@example.com";
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -age);
            Date dateOfBirth = cal.getTime();

            String[] fieldNames = {"Country", "CountryCode", "PerHourSalary", "PhoneNumber"};
            Object[] data = {"Country" + i, random.nextInt(1000), 10.0f + random.nextFloat() * 90.0f,
                    1000000000L + random.nextInt(900000000)};
            String[] dataTypes = {"String", "int", "float", "long"};

            JSONArray userDefinedFieldsJson = createUserDefinedFieldsJson(fieldNames, data, dataTypes);


            jdbcTemplate.update(
                    "INSERT INTO UserProfiles (first_name, last_name, age, email, date_of_birth, user_defined_fields) VALUES (?, ?, ?, ?, ?, ?)",
                    firstName, lastName, age, email, dateOfBirth, userDefinedFieldsJson.toString()
            );
        }

        int minUserId = jdbcTemplate.queryForObject("SELECT MIN(id) FROM UserProfiles", Integer.class);
        int maxUserId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM UserProfiles", Integer.class);

        // Generate random profile visits and likes
        for (int i = 1; i <= numberOfUsers; i++) {
            int visitorId = minUserId + i;  // Assign a unique visitor ID
            for (int j = 0; j < random.nextInt(10); j++) {
                int visitedProfileId = random.nextInt(maxUserId - minUserId + 1) + minUserId;
                boolean isValidVisitedProfileId = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM UserProfiles WHERE id = ?",
                        Integer.class,
                        visitedProfileId) > 0;

                if (isValidVisitedProfileId) {
                    Timestamp visitedAt = new Timestamp(System.currentTimeMillis() - random.nextInt(30) * 24 * 60 * 60 * 1000);

                    // Insert visit data into ProfileVisits table
                    jdbcTemplate.update(
                            "INSERT INTO ProfileVisits (visitor_id, visited_profile_id, visited_at) VALUES (?, ?, ?)",
                            visitorId, visitedProfileId, visitedAt
                    );

                    // Insert like data into UserLikes table
                    if (random.nextBoolean()) {
                        int likedProfileId = random.nextInt(maxUserId - minUserId + 1) + minUserId;
                        boolean isValidLikedProfileId = jdbcTemplate.queryForObject(
                                "SELECT COUNT(*) FROM UserProfiles WHERE id = ?",
                                Integer.class,
                                likedProfileId) > 0;

                        if (isValidLikedProfileId) {
                            Timestamp likedAt = new Timestamp(System.currentTimeMillis() - random.nextInt(30) * 24 * 60 * 60 * 1000);

                            // Insert like data into UserLikes table
                            jdbcTemplate.update(
                                    "INSERT INTO UserLikes (user_id, liked_profile_id, liked_at) VALUES (?, ?, ?)",
                                    visitorId, likedProfileId, likedAt
                            );
                        }
                    }
                }
            }
        }
    }


    private JSONArray createUserDefinedFieldsJson(String[] fieldNames, Object[] data, String[] dataTypes) {
        JSONArray userDefinedFieldsArray = new JSONArray();

        for (int i = 0; i < fieldNames.length; i++) {
            JSONObject fieldJson = new JSONObject();
            fieldJson.put("fieldName", fieldNames[i]);
            fieldJson.put("data", data[i]);
            fieldJson.put("dataType", dataTypes[i]);

            userDefinedFieldsArray.put(fieldJson);
        }

        return userDefinedFieldsArray;
    }
}

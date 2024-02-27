package org.meet5.meet5trialassignment.models;
import java.util.Date;


public class UserLikes {
    private Integer id;
    private UserProfiles user;
    private UserProfiles likedProfile;
    private Date likedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserProfiles getUser() {
        return user;
    }

    public void setUser(UserProfiles user) {
        this.user = user;
    }

    public UserProfiles getLikedProfile() {
        return likedProfile;
    }

    public void setLikedProfile(UserProfiles likedProfile) {
        this.likedProfile = likedProfile;
    }

    public Date getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Date likedAt) {
        if (likedAt != null && likedAt.before(new Date())) {
            this.likedAt = likedAt;
        } else {
            throw new IllegalArgumentException("LikedAt date cannot be in the future");
        }
    }
}

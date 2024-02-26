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
        this.likedAt = likedAt;
    }
}

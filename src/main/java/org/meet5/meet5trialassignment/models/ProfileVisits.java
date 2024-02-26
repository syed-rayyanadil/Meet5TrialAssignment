package org.meet5.meet5trialassignment.models;
import java.util.Date;

public class ProfileVisits {
    private Integer id;
    private UserProfiles visitor;
    private UserProfiles visitedProfile;
    private Date visitedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserProfiles getVisitor() {
        return visitor;
    }

    public void setVisitor(UserProfiles visitor) {
        this.visitor = visitor;
    }

    public UserProfiles getVisitedProfile() {
        return visitedProfile;
    }

    public void setVisitedProfile(UserProfiles visitedProfile) {
        this.visitedProfile = visitedProfile;
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Date visitedAt) {
        this.visitedAt = visitedAt;
    }
}
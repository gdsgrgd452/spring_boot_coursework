package co2123.streetfood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VendorProfile {
    @Id
    private int id;
    private String bio;
    private String socialMediaHandle;
    private String website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSocialMediaHandle() {
        return socialMediaHandle;
    }

    public void setSocialMediaHandle(String instagramHandle) {
        this.socialMediaHandle = instagramHandle;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}


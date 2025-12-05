package co2123.streetfood.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vendor {
    @Id
    private int id;
    private String name;
    private String location;
    private String cuisineType;

    @OneToMany(mappedBy = "dishes")
    private List<Dish> dishes;

    @ManyToOne
    private VendorProfile profile;

    @OneToMany
    @JoinColumn(name = "vendor_photos")
    private List<Photo> photos;

    @OneToMany
    @JoinColumn(name = "vendor_awards")
    private List<Award> awards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public VendorProfile getProfile() {
        return profile;
    }

    public void setProfile(VendorProfile profile) {
        this.profile = profile;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }
}


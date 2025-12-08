package co2123.streetfood.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vendor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private String cuisineType;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes;

    @ManyToOne(cascade = CascadeType.ALL)
    private VendorProfile profile;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.REMOVE, orphanRemoval = true)
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


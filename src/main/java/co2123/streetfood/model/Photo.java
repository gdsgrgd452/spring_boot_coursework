package co2123.streetfood.model;

import jakarta.persistence.*;

@Entity
public class Photo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String description;

    @ManyToOne
    @JoinColumn
    private Vendor vendor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}


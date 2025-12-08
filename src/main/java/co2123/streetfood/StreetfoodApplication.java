package co2123.streetfood;

import co2123.streetfood.model.*;
import co2123.streetfood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StreetfoodApplication implements CommandLineRunner {

    @Autowired
    public AwardRepository awardRepo;

    @Autowired
    public PhotoRepository photoRepo;

    @Autowired
    public ReviewRepository reviewRepo;

    @Autowired
    public TagRepository tagRepo;

    @Autowired
    public VendorRepository vendorRepo;

    public static void main(String[] args) {
        SpringApplication.run(StreetfoodApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Creating 1 VendorProfile
        VendorProfile profile = new VendorProfile();
        profile.setBio("Serving the best street food since 2010.");
        profile.setSocialMediaHandle("@tastyvendorfakewebsite");
        profile.setWebsite("http://tastyvendor.fakewebsite.com");
        //vendorprofileList.add(profile);

        //Creating 1 Vendor
        Vendor vendor = new Vendor();
        vendor.setName("Tasty Vendor");
        vendor.setLocation("University Road");
        vendor.setCuisineType("Fusion");
        vendor.setProfile(profile);

        //Creating 2 Tags
        Tag spicy = new Tag();
        spicy.setName("Spicy Challenge");
        Tag hiddenGem = new Tag();
        hiddenGem.setName("Hidden Gem");
        spicy = tagRepo.save(spicy);
        hiddenGem = tagRepo.save(hiddenGem);

        //Creating 2 Dishes then saving the vendor with dishes
        Dish noodles = new Dish();
        noodles.setName("Fire Noodles");
        noodles.setDescription("Extremely spicy noodles for the brave.");
        noodles.setSpiceLevel(5);
        noodles.setPrice(8.99);
        noodles.setTags(new ArrayList<>());
        noodles.getTags().add(spicy);
        noodles.setVendor(vendor);

        Dish dumplings = new Dish();
        dumplings.setName("Secret Dumplings");
        dumplings.setDescription("Delicate dumplings with a secret filling.");
        dumplings.setSpiceLevel(2);
        dumplings.setPrice(6.55);
        dumplings.setTags(new ArrayList<>());
        dumplings.getTags().add(hiddenGem);
        dumplings.setVendor(vendor);

        vendor.setDishes(new ArrayList<>());
        vendor.getDishes().add(noodles);
        vendor.getDishes().add(dumplings);
        vendor = vendorRepo.save(vendor);

        //Creating 2 reviews
        Review review1 = new Review();
        review1.setReviewerName("Sofia");
        review1.setRating(5);
        review1.setComment("So spicy, so good!");
        review1.setReviewDate(LocalDateTime.now());
        review1.setDish(noodles);

        Review review2 = new Review();
        review2.setReviewerName("Jamie");
        review2.setRating(4);
        review2.setComment("Loved the dumplings!");
        review2.setReviewDate(LocalDateTime.now());
        review2.setDish(dumplings);

        review1 = reviewRepo.save(review1);
        review2 = reviewRepo.save(review2);

        //Creating 2 Photos
        Photo photo1 = new Photo();
        photo1.setUrl("noodles.jpg");
        photo1.setDescription("A bowl of fire noodles.");
        photo1.setVendor(vendor);

        Photo photo2 = new Photo();
        photo2.setUrl("dumplings.jpg");
        photo2.setDescription("Steaming hot dumplings.");
        photo2.setVendor(vendor);

        photo1 = photoRepo.save(photo1);
        photo2 = photoRepo.save(photo2);

        //Creating 2 Awards
        Award award1 = new Award();
        award1.setTitle("Best Street Food 2024");
        award1.setYear(2024);
        award1.setVendor(vendor);

        Award award2 = new Award();
        award2.setTitle("Customer Favorite");
        award2.setYear(2023);
        award2.setVendor(vendor);

        award1 = awardRepo.save(award1);
        award2 = awardRepo.save(award2);

        //Creating another vendor to populate the database
        VendorProfile profile2 = new VendorProfile();
        profile2.setBio("Family-run, celebrating local and global tastes.");
        profile2.setSocialMediaHandle("@nicefoodfakewebsite");
        profile2.setWebsite("http://nicefood.fakewebsite.com");
        //vendorprofileList.add(profile2);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Nice Food");
        vendor2.setLocation("Leicester Market");
        vendor2.setCuisineType("Fusion");
        vendor2.setProfile(profile2);

        Tag localLegend = new Tag();
        localLegend.setName("Local Legend");
        Tag vegetarian = new Tag();
        vegetarian.setName("Vegetarian");
        localLegend = tagRepo.save(localLegend);
        vegetarian = tagRepo.save(vegetarian);

        Dish samosa = new Dish();
        samosa.setName("Spicy Samosa Chaat");
        samosa.setDescription("Crisp samosas topped with chickpeas, yogurt, chutneys, and fresh coriander.");
        samosa.setSpiceLevel(3);
        samosa.setPrice(4.99);
        samosa.setTags(new ArrayList<>());
        samosa.getTags().add(spicy);
        samosa.getTags().add(vegetarian);
        samosa.setVendor(vendor2);

        Dish porkPie = new Dish();
        porkPie.setName("Melton Mowbray Pork Pie Bites");
        porkPie.setDescription("Mini versions of the classic pork pie, served warm with tangy chutney.");
        porkPie.setSpiceLevel(1);
        porkPie.setPrice(2.99);
        porkPie.setTags(new ArrayList<>());
        porkPie.getTags().add(localLegend);
        porkPie.setVendor(vendor2);

        Dish toastie = new Dish();
        toastie.setName("Red Leicester Cheese Toastie");
        toastie.setDescription("Thick slices of Red Leicester cheese melted in artisan bread, served with a side of chutney.");
        toastie.setSpiceLevel(1);
        toastie.setPrice(3.75);
        toastie.setTags(new ArrayList<>());
        toastie.getTags().add(vegetarian);
        toastie.setVendor(vendor2);

        vendor2.setDishes(new ArrayList<>());
        vendor2.getDishes().add(samosa);
        vendor2.getDishes().add(porkPie);
        vendor2.getDishes().add(toastie);
        vendor2 = vendorRepo.save(vendor2);

        Review review3 = new Review();
        review3.setReviewerName("Tom");
        review3.setRating(5);
        review3.setComment("Tangy and delicious!");
        review3.setReviewDate(LocalDateTime.now());
        review3.setDish(samosa);

        Review review4 = new Review();
        review4.setReviewerName("Ayesha");
        review4.setRating(4);
        review4.setComment("Perfect snack for a market stroll.");
        review4.setReviewDate(LocalDateTime.now());
        review4.setDish(porkPie);

        Review review5 = new Review();
        review5.setReviewerName("Pierre");
        review5.setRating(5);
        review5.setComment("Loved it! Will be back!");
        review5.setReviewDate(LocalDateTime.now());
        review5.setDish(toastie);

        review3 = reviewRepo.save(review3);
        review4 = reviewRepo.save(review4);
        review5 = reviewRepo.save(review5);

        Photo photo3 = new Photo();
        photo3.setUrl("samosa.JPG");
        photo3.setDescription("Samosa chaat with toppings.");
        photo3.setVendor(vendor2);

        Photo photo4 = new Photo();
        photo4.setUrl("pies.jpg");
        photo4.setDescription("Mini Melton Mowbray pork pies.");
        photo4.setVendor(vendor2);

        Photo photo5 = new Photo();
        photo5.setUrl("toastie.jpg");
        photo5.setDescription("Red Leicester cheese toastie");
        photo5.setVendor(vendor2);

        photo3 = photoRepo.save(photo3);
        photo4 = photoRepo.save(photo4);
        photo5 = photoRepo.save(photo5);

        Award award3 = new Award();
        award3.setTitle("Leicester Market Favourite");
        award3.setYear(2025);
        award3.setVendor(vendor2);

        award3 = awardRepo.save(award3);

        //Another vendor
        VendorProfile profile3 = new VendorProfile();
        profile3.setBio("Delicate French desserts, crafted with passion and tradition.");
        profile3.setSocialMediaHandle("@bonsdessertsfakewebsite");
        profile3.setWebsite("http://bonsdesserts.fakewebsite.com");
        //vendorprofileList.add(profile3);

        Vendor vendor3 = new Vendor();
        vendor3.setName("Bons Desserts");
        vendor3.setLocation("New Walk");
        vendor3.setCuisineType("French Desserts");
        vendor3.setProfile(profile3);

        Tag sweet = new Tag();
        sweet.setName("Sweet");
        Tag classic = new Tag();
        classic.setName("Classic");
        classic = tagRepo.save(classic);
        sweet = tagRepo.save(sweet);

        Dish eclair = new Dish();
        eclair.setName("Éclair au Chocolat");
        eclair.setDescription("Choux pastry filled with rich chocolate cream and topped with chocolate glaze.");
        eclair.setSpiceLevel(0);
        eclair.setPrice(3.15);
        eclair.setTags(new ArrayList<>());
        eclair.getTags().add(sweet);
        eclair.getTags().add(classic);
        eclair.setVendor(vendor3);

        Dish tarteCitron = new Dish();
        tarteCitron.setName("Tarte au Citron");
        tarteCitron.setDescription("Tangy lemon tart with a buttery pastry base and toasted meringue.");
        tarteCitron.setSpiceLevel(0);
        tarteCitron.setPrice(3.85);
        tarteCitron.setTags(new ArrayList<>());
        tarteCitron.getTags().add(sweet);
        tarteCitron.setVendor(vendor3);

        Dish madeleine = new Dish();
        madeleine.setName("Madeleine");
        madeleine.setDescription("Soft, shell-shaped sponge cakes with a hint of lemon.");
        madeleine.setSpiceLevel(0);
        madeleine.setPrice(1.45);
        madeleine.setTags(new ArrayList<>());
        madeleine.getTags().add(classic);
        madeleine.setVendor(vendor3);


        vendor3.setDishes(new ArrayList<>());
        vendor3.getDishes().add(eclair);
        vendor3.getDishes().add(tarteCitron);
        vendor3.getDishes().add(madeleine);

        vendor3 = vendorRepo.save(vendor3);

        Review review6 = new Review();
        review6.setReviewerName("Lucie");
        review6.setRating(5);
        review6.setComment("The éclair was delicious!");
        review6.setReviewDate(LocalDateTime.now());
        review6.setDish(eclair);

        Review review7 = new Review();
        review7.setReviewerName("Priya");
        review7.setRating(4);
        review7.setComment("Loved the tart, so zesty and fresh.");
        review7.setReviewDate(LocalDateTime.now());
        review7.setDish(tarteCitron);

        Review review8 = new Review();
        review8.setReviewerName("Harriet");
        review8.setRating(3);
        review8.setComment("It was okay.");
        review8.setReviewDate(LocalDateTime.now());
        review8.setDish(tarteCitron);

        review6 = reviewRepo.save(review6);
        review7 = reviewRepo.save(review7);
        review8 = reviewRepo.save(review8);

        Photo photo6 = new Photo();
        photo6.setUrl("eclair.jpg");
        photo6.setDescription("Chocolate éclair with glossy glaze.");
        photo6.setVendor(vendor3);

        Photo photo7 = new Photo();
        photo7.setUrl("tarte.jpg");
        photo7.setDescription("Lemon tart with toasted meringue.");
        photo7.setVendor(vendor3);

        photo6 = photoRepo.save(photo6);
        photo7 = photoRepo.save(photo7);

        Award award4 = new Award();
        award4.setTitle("Best Dessert Stall");
        award4.setYear(2025);
        award4.setVendor(vendor3);
        award4 = awardRepo.save(award4);

    }
}

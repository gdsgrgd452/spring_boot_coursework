package co2123.streetfood;

import co2123.streetfood.model.*;
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


    public static List<Award> awardList = new ArrayList<>();
    public static List<Dish> dishList = new ArrayList<>();
    public static List<Photo> photoList = new ArrayList<>();
    public static List<Review> reviewList = new ArrayList<>();
    public static List<Tag> tagList = new ArrayList<>();
    public static List<Vendor> vendorList = new ArrayList<>();
    public static List<VendorProfile> vendorprofileList = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(StreetfoodApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Creating 1 VendorProfile
        VendorProfile profile = new VendorProfile();
        profile.setId(1);
        profile.setBio("Serving the best street food since 2010.");
        profile.setSocialMediaHandle("@tastyvendorfakewebsite");
        profile.setWebsite("http://tastyvendor.fakewebsite.com");
        vendorprofileList.add(profile);

        //Creating 1 Vendor
        Vendor vendor = new Vendor();
        vendor.setId(1);
        vendor.setName("Tasty Vendor");
        vendor.setLocation("University Road");
        vendor.setCuisineType("Fusion");
        vendor.setProfile(profile);

        //Creating 2 Tags
        Tag spicy = new Tag();
        spicy.setId(1);
        spicy.setName("Spicy Challenge");
        Tag hiddenGem = new Tag();
        hiddenGem.setId(2);
        hiddenGem.setName("Hidden Gem");
        tagList.add(spicy);
        tagList.add(hiddenGem);

        //Creating 2 Dishes then saving the vendor with dishes
        Dish noodles = new Dish();
        noodles.setId(1);
        noodles.setName("Fire Noodles");
        noodles.setDescription("Extremely spicy noodles for the brave.");
        noodles.setSpiceLevel(5);
        noodles.setPrice(8.99);
        noodles.setTags(new ArrayList<>());
        noodles.getTags().add(spicy);
        noodles.setVendor(vendor);
        dishList.add(noodles);

        Dish dumplings = new Dish();
        dumplings.setId(2);
        dumplings.setName("Secret Dumplings");
        dumplings.setDescription("Delicate dumplings with a secret filling.");
        dumplings.setSpiceLevel(2);
        dumplings.setPrice(6.55);
        dumplings.setTags(new ArrayList<>());
        dumplings.getTags().add(hiddenGem);
        dumplings.setVendor(vendor);
        dishList.add(dumplings);

        vendor.setDishes(new ArrayList<>());
        vendor.getDishes().add(noodles);
        vendor.getDishes().add(dumplings);
        vendorList.add(vendor);

        //Creating 2 reviews
        Review review1 = new Review();
        review1.setId(1);
        review1.setReviewerName("Sofia");
        review1.setRating(5);
        review1.setComment("So spicy, so good!");
        review1.setReviewDate(LocalDateTime.now());
        review1.setDish(noodles);

        Review review2 = new Review();
        review2.setId(2);
        review2.setReviewerName("Jamie");
        review2.setRating(4);
        review2.setComment("Loved the dumplings!");
        review2.setReviewDate(LocalDateTime.now());
        review2.setDish(dumplings);

        reviewList.add(review1);
        reviewList.add(review2);

        //Creating 2 Photos
        Photo photo1 = new Photo();
        photo1.setId(1);
        photo1.setUrl("noodles.jpg");
        photo1.setDescription("A bowl of fire noodles.");
        photo1.setVendor(vendor);

        Photo photo2 = new Photo();
        photo2.setId(2);
        photo2.setUrl("dumplings.jpg");
        photo2.setDescription("Steaming hot dumplings.");
        photo2.setVendor(vendor);

        photoList.add(photo1);
        photoList.add(photo2);

        //Creating 2 Awards
        Award award1 = new Award();
        award1.setId(1);
        award1.setTitle("Best Street Food 2024");
        award1.setYear(2024);
        award1.setVendor(vendor);

        Award award2 = new Award();
        award2.setId(2);
        award2.setTitle("Customer Favorite");
        award2.setYear(2023);
        award2.setVendor(vendor);

        awardList.add(award1);
        awardList.add(award2);

        //Creating another vendor to populate the database
        VendorProfile profile2 = new VendorProfile();
        profile2.setId(2);
        profile2.setBio("Family-run, celebrating local and global tastes.");
        profile2.setSocialMediaHandle("@nicefoodfakewebsite");
        profile2.setWebsite("http://nicefood.fakewebsite.com");
        vendorprofileList.add(profile2);

        Vendor vendor2 = new Vendor();
        vendor2.setId(2);
        vendor2.setName("Nice Food");
        vendor2.setLocation("Leicester Market");
        vendor2.setCuisineType("Fusion");
        vendor2.setProfile(profile2);

        Tag localLegend = new Tag();
        localLegend.setId(3);
        localLegend.setName("Local Legend");
        Tag vegetarian = new Tag();
        vegetarian.setId(4);
        vegetarian.setName("Vegetarian");
        tagList.add(localLegend);
        tagList.add(vegetarian);

        Dish samosa = new Dish();
        samosa.setId(3);
        samosa.setName("Spicy Samosa Chaat");
        samosa.setDescription("Crisp samosas topped with chickpeas, yogurt, chutneys, and fresh coriander.");
        samosa.setSpiceLevel(3);
        samosa.setPrice(4.99);
        samosa.setTags(new ArrayList<>());
        samosa.getTags().add(spicy);
        samosa.getTags().add(vegetarian);
        samosa.setVendor(vendor2);
        dishList.add(samosa);

        Dish porkPie = new Dish();
        porkPie.setId(4);
        porkPie.setName("Melton Mowbray Pork Pie Bites");
        porkPie.setDescription("Mini versions of the classic pork pie, served warm with tangy chutney.");
        porkPie.setSpiceLevel(1);
        porkPie.setPrice(2.99);
        porkPie.setTags(new ArrayList<>());
        porkPie.getTags().add(localLegend);
        porkPie.setVendor(vendor2);
        dishList.add(porkPie);

        Dish toastie = new Dish();
        toastie.setId(5);
        toastie.setName("Red Leicester Cheese Toastie");
        toastie.setDescription("Thick slices of Red Leicester cheese melted in artisan bread, served with a side of chutney.");
        toastie.setSpiceLevel(1);
        toastie.setPrice(3.75);
        toastie.setTags(new ArrayList<>());
        toastie.getTags().add(vegetarian);
        toastie.setVendor(vendor2);
        dishList.add(toastie);

        vendor2.setDishes(new ArrayList<>());
        vendor2.getDishes().add(samosa);
        vendor2.getDishes().add(porkPie);
        vendor2.getDishes().add(toastie);
        vendorList.add(vendor2);

        Review review3 = new Review();
        review3.setId(3);
        review3.setReviewerName("Tom");
        review3.setRating(5);
        review3.setComment("Tangy and delicious!");
        review3.setReviewDate(LocalDateTime.now());
        review3.setDish(samosa);

        Review review4 = new Review();
        review4.setId(4);
        review4.setReviewerName("Ayesha");
        review4.setRating(4);
        review4.setComment("Perfect snack for a market stroll.");
        review4.setReviewDate(LocalDateTime.now());
        review4.setDish(porkPie);

        Review review5 = new Review();
        review5.setId(5);
        review5.setReviewerName("Pierre");
        review5.setRating(5);
        review5.setComment("Loved it! Will be back!");
        review5.setReviewDate(LocalDateTime.now());
        review5.setDish(toastie);

        reviewList.add(review3);
        reviewList.add(review4);
        reviewList.add(review5);

        Photo photo3 = new Photo();
        photo3.setId(3);
        photo3.setUrl("samosa.JPG");
        photo3.setDescription("Samosa chaat with toppings.");
        photo3.setVendor(vendor2);

        Photo photo4 = new Photo();
        photo4.setId(4);
        photo4.setUrl("pies.jpg");
        photo4.setDescription("Mini Melton Mowbray pork pies.");
        photo4.setVendor(vendor2);

        Photo photo5 = new Photo();
        photo5.setId(5);
        photo5.setUrl("toastie.jpg");
        photo5.setDescription("Red Leicester cheese toastie");
        photo5.setVendor(vendor2);

        photoList.add(photo3);
        photoList.add(photo4);
        photoList.add(photo5);

        Award award3 = new Award();
        award3.setId(3);
        award3.setTitle("Leicester Market Favourite");
        award3.setYear(2025);
        award3.setVendor(vendor2);

        awardList.add(award3);

        //Another vendor
        VendorProfile profile3 = new VendorProfile();
        profile3.setId(3);
        profile3.setBio("Delicate French desserts, crafted with passion and tradition.");
        profile3.setSocialMediaHandle("@bonsdessertsfakewebsite");
        profile3.setWebsite("http://bonsdesserts.fakewebsite.com");
        vendorprofileList.add(profile3);

        Vendor vendor3 = new Vendor();
        vendor3.setId(3);
        vendor3.setName("Bons Desserts");
        vendor3.setLocation("New Walk");
        vendor3.setCuisineType("French Desserts");
        vendor3.setProfile(profile3);

        Tag sweet = new Tag();
        sweet.setId(5);
        sweet.setName("Sweet");
        Tag classic = new Tag();
        classic.setId(6);
        classic.setName("Classic");
        tagList.add(classic);
        tagList.add(sweet);

        Dish eclair = new Dish();
        eclair.setId(6);
        eclair.setName("Éclair au Chocolat");
        eclair.setDescription("Choux pastry filled with rich chocolate cream and topped with chocolate glaze.");
        eclair.setSpiceLevel(0);
        eclair.setPrice(3.15);
        eclair.setTags(new ArrayList<>());
        eclair.getTags().add(sweet);
        eclair.getTags().add(classic);
        eclair.setVendor(vendor3);

        Dish tarteCitron = new Dish();
        tarteCitron.setId(7);
        tarteCitron.setName("Tarte au Citron");
        tarteCitron.setDescription("Tangy lemon tart with a buttery pastry base and toasted meringue.");
        tarteCitron.setSpiceLevel(0);
        tarteCitron.setPrice(3.85);
        tarteCitron.setTags(new ArrayList<>());
        tarteCitron.getTags().add(sweet);
        tarteCitron.setVendor(vendor3);

        Dish madeleine = new Dish();
        madeleine.setId(8);
        madeleine.setName("Madeleine");
        madeleine.setDescription("Soft, shell-shaped sponge cakes with a hint of lemon.");
        madeleine.setSpiceLevel(0);
        madeleine.setPrice(1.45);
        madeleine.setTags(new ArrayList<>());
        madeleine.getTags().add(classic);
        madeleine.setVendor(vendor3);

        dishList.add(tarteCitron);
        dishList.add(madeleine);
        dishList.add(eclair);

        vendor3.setDishes(new ArrayList<>());
        vendor3.getDishes().add(eclair);
        vendor3.getDishes().add(tarteCitron);
        vendor3.getDishes().add(madeleine);

        vendorList.add(vendor3);

        Review review6 = new Review();
        review6.setId(6);
        review6.setReviewerName("Lucie");
        review6.setRating(5);
        review6.setComment("The éclair was delicious!");
        review6.setReviewDate(LocalDateTime.now());
        review6.setDish(eclair);

        Review review7 = new Review();
        review7.setId(7);
        review7.setReviewerName("Priya");
        review7.setRating(4);
        review7.setComment("Loved the tart, so zesty and fresh.");
        review7.setReviewDate(LocalDateTime.now());
        review7.setDish(tarteCitron);

        Review review8 = new Review();
        review8.setId(8);
        review8.setReviewerName("Harriet");
        review8.setRating(3);
        review8.setComment("It was okay.");
        review8.setReviewDate(LocalDateTime.now());
        review8.setDish(tarteCitron);

        reviewList.add(review6);
        reviewList.add(review7);
        reviewList.add(review8);

        Photo photo6 = new Photo();
        photo6.setId(6);
        photo6.setUrl("eclair.jpg");
        photo6.setDescription("Chocolate éclair with glossy glaze.");
        photo6.setVendor(vendor3);

        Photo photo7 = new Photo();
        photo7.setId(7);
        photo7.setUrl("tarte.jpg");
        photo7.setDescription("Lemon tart with toasted meringue.");
        photo7.setVendor(vendor3);

        photoList.add(photo6);
        photoList.add(photo7);

        Award award4 = new Award();
        award4.setId(4);
        award4.setTitle("Best Dessert Stall");
        award4.setYear(2025);
        award4.setVendor(vendor3);
        awardList.add(award4);

        //Additional code for ArrayLists
        noodles.setReviews(new ArrayList<>(List.of(review1)));
        dumplings.setReviews(new ArrayList<>(List.of(review2)));
        samosa.setReviews(new ArrayList<>(List.of(review3)));
        porkPie.setReviews(new ArrayList<>(List.of(review4)));
        toastie.setReviews(new ArrayList<>(List.of(review5)));
        eclair.setReviews(new ArrayList<>(List.of(review6)));
        tarteCitron.setReviews(new ArrayList<>(Arrays.asList(review7, review8)));

        vendor.setPhotos(new ArrayList<>(Arrays.asList(photo1,photo2)));
        vendor2.setPhotos(new ArrayList<>(Arrays.asList(photo3,photo4,photo5)));
        vendor3.setPhotos(new ArrayList<>(Arrays.asList(photo6,photo7)));

        vendor.setAwards(new ArrayList<>(Arrays.asList(award1,award2)));
        vendor2.setAwards(new ArrayList<>(List.of(award3)));
        vendor3.setAwards(new ArrayList<>(List.of(award4)));
    }
}

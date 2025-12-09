package co2123.streetfood;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StreetfoodApplicationTests {

    //Does the project compile?
    @Test
    void contextLoads() {
    }

    //TASK 1 tests below ---------------------------------------

    //Checking application.properties
    @Test
    public void checkProperties() throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("src/main/resources/application.properties"));
        assertTrue("Folder for JSPs not defined.", "/WEB-INF/views/".equals(p.getProperty("spring.mvc.view.prefix")));
        assertTrue("JSP extension not defined.", ".jsp".equals(p.getProperty("spring.mvc.view.suffix")));
        assertTrue("URl for the database not defined", p.getProperty("spring.datasource.url") != null);
        assertTrue("Username for the database not defined", p.getProperty("spring.datasource.username") != null);
        assertTrue("Password for the database not defined", p.getProperty("spring.datasource.password") != null);
        assertTrue("Missing/Incomplete configuration for printing out SQL to the command line with spring.jpa.show-sql.",p.getProperty("spring.jpa.show-sql").equals("true"));

        String s = p.getProperty("logging.level.org.hibernate.SQL");
        if(s != null){
            s = s.toLowerCase();
            assertTrue("Missing/Incomplete configuration for printing out SQL to the command line with logging.level.org.hibernate.SQL.",s.contains("debug"));
        } else {
            assertTrue("Missing/Incomplete configuration for printing out SQL to the command line with logging.level.org.hibernate.SQL.",p.getProperty("logging.level.org.hibernate.SQL").contains("debug"));
        }
    }

    //Checking dependencies in build.gradle
    @Test
    public void dependencies() throws Exception {
        String content = Files.readString(Paths.get("build.gradle"));
        assertTrue("Jasper libraries not loaded for JSPs.", content.contains("tomcat-embed-jasper"));
        assertTrue("JSTL library not included in build.gradle.", content.contains("jstl"));
        assertTrue("Missing a dependency for connecting to the database.", content.contains("mysql-connector"));
        assertTrue("Missing a dependency for connecting to the database.", content.contains("spring-boot-starter-data-jpa"));
    }

    //TASK 2 - Checking that the tables and rows exist
    //NOTE: These tests will not work until Task 1 has been successfully completed

    /*
    @Autowired
    private EntityManager entityManager;

    //Helper method to check table existence after Task 2
    private void assertTableExists(String tableName) {
        Query query = entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM information_schema.tables " +
                        "WHERE table_schema = 'co2123db' AND table_name = '" + tableName + "'"
        );
        Number count = (Number) query.getSingleResult();
        Assertions.assertTrue(count.intValue() > 0, "Table '" + tableName + "' should exist in schema 'co2123db'");
    }

    //Helper method to check columns after Task 2
    private void assertColumnsExist(String tableName, String[] columns) {
        for (String column : columns) {
            Query query = entityManager.createNativeQuery(
                    "SELECT COUNT(*) FROM information_schema.columns " +
                            "WHERE table_schema = 'co2123db' AND table_name = '" + tableName + "' AND column_name = '" + column + "'"
            );
            Number count = (Number) query.getSingleResult();
            Assertions.assertTrue(count.intValue() > 0,
                    "Column '" + column + "' should exist in table '" + tableName + "'");
        }
    }

    //Tests that each table exists with the correct columns after Task 2

    @Test
    void awardTableExistsAndHasColumns() {
        assertTableExists("award");
        assertColumnsExist("award", new String[]{"id", "vendor_id", "year", "title"});
    }

    @Test
    void dishTableExistsAndHasColumns() {
        assertTableExists("dish");
        assertColumnsExist("dish", new String[]{"id", "price", "spice_level", "vendor_id", "description", "name"});
    }

    //Fails after Task 4 (expected)
    @Test
    void dishReviewsTableExistsAndHasColumns() {
        assertTableExists("dish_reviews");
        assertColumnsExist("dish_reviews", new String[]{"dish_id", "reviews_id"});
    }

    //Fails after Task 4 (expected) - table stays but variable names change
    @Test
    void dishTagsTableExistsAndHasColumns() {
        assertTableExists("dish_tags");
        assertColumnsExist("dish_tags", new String[]{"dish_id", "tags_id"});
    }

    @Test
    void photoTableExistsAndHasColumns() {
        assertTableExists("photo");
        assertColumnsExist("photo", new String[]{"id", "vendor_id", "description", "url"});
    }

    @Test
    void reviewTableExistsAndHasColumns() {
        assertTableExists("review");
        assertColumnsExist("review", new String[]{"dish_id", "id", "rating", "review_date", "comment", "reviewer_name"});
    }

    @Test
    void tagTableExistsAndHasColumns() {
        assertTableExists("tag");
        assertColumnsExist("tag", new String[]{"id", "name"});
    }

    @Test
    void vendorTableExistsAndHasColumns() {
        assertTableExists("vendor");
        assertColumnsExist("vendor", new String[]{"id", "profile_id", "cuisine_type", "location", "name"});
    }

    //Fails after Task 4 (expected)
    @Test
    void vendorAwardsTableExistsAndHasColumns() {
        assertTableExists("vendor_awards");
        assertColumnsExist("vendor_awards", new String[]{"awards_id", "vendor_id"});
    }

    //Fails after Task 4 (expected)
    @Test
    void vendorDishesTableExistsAndHasColumns() {
        assertTableExists("vendor_dishes");
        assertColumnsExist("vendor_dishes", new String[]{"dishes_id", "vendor_id"});
    }

    //Fails after Task 4 (expected)
    @Test
    void vendorPhotosTableExistsAndHasColumns() {
        assertTableExists("vendor_photos");
        assertColumnsExist("vendor_photos", new String[]{"photos_id", "vendor_id"});
    }

    @Test
    void vendorProfileTableExistsAndHasColumns() {
        assertTableExists("vendor_profile");
        assertColumnsExist("vendor_profile", new String[]{"id", "bio", "social_media_handle", "website"});
    }

    */

    //TASK 3 -------------------------------------------------

    //Helper method to find files
    private boolean classExists(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    //Checking if each repository exists

    @Test
    void awardRepositoryExists() {
        boolean exists = classExists("co2123.streetfood.repository.AwardRepository") ||
                classExists("co2123.streetfood_solution.repository.AwardRepository");
        Assertions.assertTrue(exists, "AwardRepository should exist in either 'streetfood' or 'streetfood_solution' package");
    }

    @Test
    void photoRepositoryExists() {
        boolean exists = classExists("co2123.streetfood.repository.PhotoRepository") ||
                classExists("co2123.streetfood_solution.repository.PhotoRepository");
        Assertions.assertTrue(exists, "PhotoRepository should exist in either package");
    }

    @Test
    void reviewRepositoryExists() {
        boolean exists = classExists("co2123.streetfood.repository.ReviewRepository") ||
                classExists("co2123.streetfood_solution.repository.ReviewRepository");
        Assertions.assertTrue(exists, "ReviewRepository should exist in either package");
    }

    @Test
    void tagRepositoryExists() {
        boolean exists = classExists("co2123.streetfood.repository.TagRepository") ||
                classExists("co2123.streetfood_solution.repository.TagRepository");
        Assertions.assertTrue(exists, "TagRepository should exist in either package");
    }

    @Test
    void vendorRepositoryExists() {
        boolean exists = classExists("co2123.streetfood.repository.VendorRepository") ||
                classExists("co2123.streetfood_solution.repository.VendorRepository");
        Assertions.assertTrue(exists, "VendorRepository should exist in either package");
    }

    //TASK 4 -------------------------------------------------

    /*
    @Autowired
    private EntityManager entityManager;

    //Helper method to check table existence after Task 4
    private void assertTableExists(String tableName) {
        Query query = entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM information_schema.tables " +
                        "WHERE table_schema = 'co2123db' AND table_name = '" + tableName + "'"
        );
        Number count = (Number) query.getSingleResult();
        Assertions.assertTrue(count.intValue() > 0, "Table '" + tableName + "' should exist in schema 'co2123db'");
    }

    //Helper method to check columns after Task 4
    private void assertColumnsExist(String tableName, String[] columns) {
        for (String column : columns) {
            Query query = entityManager.createNativeQuery(
                    "SELECT COUNT(*) FROM information_schema.columns " +
                            "WHERE table_schema = 'co2123db' AND table_name = '" + tableName + "' AND column_name = '" + column + "'"
            );
            Number count = (Number) query.getSingleResult();
            Assertions.assertTrue(count.intValue() > 0,
                    "Column '" + column + "' should exist in table '" + tableName + "'");
        }
    }

    //Tests that each table exists with the correct columns after Task 4

    @Test
    void awardTableExistsAndHasColumnsNew() {
        assertTableExists("award");
        assertColumnsExist("award", new String[]{"id", "vendor_id", "year", "title"});
    }

    @Test
    void dishTableExistsAndHasColumnsNew() {
        assertTableExists("dish");
        assertColumnsExist("dish", new String[]{"id", "price", "spice_level", "vendor_id", "description", "name"});
    }

    @Test
    void dishTagsTableExistsAndHasColumnsNew() {
        assertTableExists("dish_tags");
        assertColumnsExist("dish_tags", new String[]{"dishes_id", "tags_id"});
    }

    @Test
    void photoTableExistsAndHasColumnsNew() {
        assertTableExists("photo");
        assertColumnsExist("photo", new String[]{"id", "vendor_id", "description", "url"});
    }

    @Test
    void reviewTableExistsAndHasColumnsNew() {
        assertTableExists("review");
        assertColumnsExist("review", new String[]{"dish_id", "id", "rating", "review_date", "comment", "reviewer_name"});
    }

    @Test
    void tagTableExistsAndHasColumnsNew() {
        assertTableExists("tag");
        assertColumnsExist("tag", new String[]{"id", "name"});
    }

    @Test
    void vendorTableExistsAndHasColumnsNew() {
        assertTableExists("vendor");
        assertColumnsExist("vendor", new String[]{"id", "profile_id", "cuisine_type", "location", "name"});
    }

    @Test
    void vendorProfileTableExistsAndHasColumnsNew() {
        assertTableExists("vendor_profile");
        assertColumnsExist("vendor_profile", new String[]{"id", "bio", "social_media_handle", "website"});
    }
    */

    //TASKS 4 & 6 -------------------------------------------------


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int rowCountChecker(String table){
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + table, Integer.class);
        return count;
    }

    @Test
    void testAwardTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("award");
        Assertions.assertTrue(count != null && count > 0, "Award table should have at least one row");
    }

    @Test
    void testDishTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("dish");
        Assertions.assertTrue(count != null && count > 0, "Dish table should have at least one row");
    }

    @Test
    void testDishTagsTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("dish_tags");
        Assertions.assertTrue(count != null && count > 0, "Dish_tags table should have at least one row");
    }

    @Test
    void testPhotoTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("photo");
        Assertions.assertTrue(count != null && count > 0, "Photo table should have at least one row");
    }

    @Test
    void testReviewTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("review");
        Assertions.assertTrue(count != null && count > 0, "Review table should have at least one row");
    }

    @Test
    void testTagTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("tag");
        Assertions.assertTrue(count != null && count > 0, "Tag table should have at least one row");
    }

    @Test
    void testVendorTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("vendor");
        Assertions.assertTrue(count != null && count > 0, "Vendor table should have at least one row");
    }

    @Test
    void testVendorProfileTableHasAtLeastOneRow() {
        Integer count = rowCountChecker("vendor_profile");
        Assertions.assertTrue(count != null && count > 0, "Vendor_profile table should have at least one row");
    }



}

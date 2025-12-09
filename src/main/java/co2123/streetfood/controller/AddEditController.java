package co2123.streetfood.controller;

import co2123.streetfood.model.*;
import co2123.streetfood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class AddEditController {

    @Autowired
    private VendorRepository vendorRepo;
    @Autowired
    private AwardRepository awardRepo;
    @Autowired
    private PhotoRepository photoRepo;
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private TagRepository tagRepo;

    @RequestMapping("editVendor")
    public String editVendorForm(@RequestParam Integer id, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(id);

        if(foundVendor.isEmpty()) {
            return "redirect:/admin";
        }
        model.addAttribute("vendor", foundVendor.get());
        return "forms/editVendor";
    }


    @RequestMapping("editedVendor")
    public String submittedEditForm(@RequestParam Integer id, @ModelAttribute Vendor vendor, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(id);

        if(foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        foundVendor.get().setName(vendor.getName());
        foundVendor.get().setLocation(vendor.getLocation());
        foundVendor.get().setCuisineType(vendor.getCuisineType());

        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + id;
    }



    @RequestMapping("editVendorProfile")
    public String editVendorProfileForm(@RequestParam Integer id, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(id);

        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        VendorProfile profile = foundVendor.get().getProfile();

        model.addAttribute("profile", profile);
        model.addAttribute("vendor", id);
        return "forms/editVendorProfile";
    }

    @RequestMapping("editedVendorProfile")
    public String submittedProfileEditForm(@RequestParam Integer id, @ModelAttribute VendorProfile profile, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(id);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        if(foundVendor.get().getProfile() == null){
            foundVendor.get().setProfile(profile);
        } else {
            foundVendor.get().getProfile().setBio(profile.getBio());
            foundVendor.get().getProfile().setSocialMediaHandle(profile.getSocialMediaHandle());
            foundVendor.get().getProfile().setWebsite(profile.getWebsite());
        }
        vendorRepo.save(foundVendor.get());
        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + id;
    }


    @RequestMapping("newDish")
    public String newDishForm(@RequestParam Integer id, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(id);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        model.addAttribute("dish", new Dish());
        model.addAttribute("tags", tagRepo.findAll());
        return "forms/newDish";
    }

    @RequestMapping("addDish")
    public String addDish(@RequestParam Integer vendorid, @RequestParam(required = false) List<Integer> tagIds , @ModelAttribute Dish dish, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

//        if(foundVendor.get().getDishes().isEmpty()){
//            foundVendor.get().setDishes(new ArrayList<>());
//        }
        if(tagIds != null) {
            List<Tag> tags = (List<Tag>) tagRepo.findAllById(tagIds);
            dish.setTags(tags);
        }



        dish.setReviews(new ArrayList<>());
        dish.setVendor(foundVendor.get());
        foundVendor.get().getDishes().add(dish);
        vendorRepo.save(foundVendor.get());

        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + vendorid;
    }



    @RequestMapping("newReview")
    public String newReview(@RequestParam Integer vendorid, @RequestParam Integer dishid, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        model.addAttribute("dishid",dishid);
        model.addAttribute("review", new Review());
        return "forms/newReview";
    }

    @RequestMapping("addReview")
    public String addReview(@RequestParam Integer vendorid, @RequestParam Integer dishid, @ModelAttribute Review review, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        Dish foundDish = null;
        for (Dish dish : foundVendor.get().getDishes()) {
            if (dish.getId() == dishid) {
                foundDish = dish;
                break;
            }
        }

        if (foundDish == null) {
            return "redirect:/admin";
        }

        review.setReviewDate(LocalDateTime.now());
        review.setDish(foundDish);
        review = reviewRepo.save(review);

        if(foundDish.getReviews().isEmpty()){
            foundDish.setReviews(new ArrayList<>());
        }
        foundDish.getReviews().add(review);

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("newPhoto")
    public String newPhoto(@RequestParam Integer vendorid, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        model.addAttribute("photo", new Photo());
        return "forms/newPhoto";
    }

    @RequestMapping("addPhoto")
    public String addPhoto(@RequestParam Integer vendorid, @ModelAttribute Photo photo, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        photo.setVendor(foundVendor.get());
        photo = photoRepo.save(photo);

        foundVendor.get().getPhotos().add(photo);

        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("newAward")
    public String newAward(@RequestParam Integer vendorid, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        model.addAttribute("award", new Award());
        return "forms/newAward";
    }

    @RequestMapping("addAward")
    public String addAward(@RequestParam Integer vendorid, @ModelAttribute Award award, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        for (Award a : awardRepo.findAll()) {
            if (a.getYear() == award.getYear() && Objects.equals(a.getTitle(), award.getTitle())) {
                return "redirect:/vendor?id=" + vendorid;
            }
        }

        award.setVendor(foundVendor.get());
        award = awardRepo.save(award);

        foundVendor.get().getAwards().add(award);
        vendorRepo.save(foundVendor.get());

        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + vendorid;
    }



    @RequestMapping("editDish")
    public String editDishForm(@RequestParam Integer vendorid, @RequestParam Integer dishid, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        Dish foundDish = null;
        for (Dish dish : foundVendor.get().getDishes()) {
            if (dish.getId() == dishid) {
                foundDish = dish;
                break;
            }
        }

        if (foundDish == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        model.addAttribute("dish", foundDish);
        model.addAttribute("tags", tagRepo.findAll());
        return "forms/editDish";
    }
    @RequestMapping("editedDish")
    public String submittedEditDishForm(@RequestParam Integer vendorid, @RequestParam Integer dishid, @RequestParam(required = false) List<Integer> tagIds, @ModelAttribute Dish dish, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorid);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        Dish foundDish = null;
        for (Dish d : foundVendor.get().getDishes()) {
            if (d.getId() == dishid) {
                foundDish = d;
                break;
            }
        }

        if (foundDish == null) {
            return "redirect:/admin";
        }

        foundDish.setName(dish.getName());
        foundDish.setPrice(dish.getPrice());
        foundDish.setTags(dish.getTags());
        foundDish.setDescription(dish.getDescription());
        foundDish.setSpiceLevel(dish.getSpiceLevel());

        if(foundDish.getTags() == null){
            foundDish.setTags(new ArrayList<>());
        } else {
            foundDish.getTags().clear();
        }

        for(Integer tagId : tagIds){
            Optional<Tag> foundTag = tagRepo.findById(tagId);
            if (foundTag.isPresent()) {
                foundDish.getTags().add(foundTag.get());
            }
        }

        vendorRepo.save(foundVendor.get());
        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("editReview")
    public String editReview(@RequestParam Integer vendorId, @RequestParam Integer reviewId, Model model) {
        Optional<Vendor> foundVendor = vendorRepo.findById(vendorId);

        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        Optional<Review> foundReview = reviewRepo.findById(reviewId);

        if (foundReview.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        model.addAttribute("review", foundReview.get());
        return "forms/editReview";
    }

    @RequestMapping("editedReview")
    public String editedReview(@RequestParam Integer vendorId, @RequestParam Integer reviewId, @ModelAttribute Review review, Model model) {
        Optional<Review> foundReview = reviewRepo.findById(reviewId);

        if (foundReview.isEmpty()) {
            return "redirect:/admin";
        }

        foundReview.get().setReviewerName(review.getReviewerName());
        foundReview.get().setComment(review.getComment());
        foundReview.get().setRating(review.getRating());
        reviewRepo.save(foundReview.get());

        Optional<Vendor> foundVendor = vendorRepo.findById(vendorId);
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + vendorId;
    }

    @RequestMapping("editPhoto")
    public String editPhoto(@RequestParam Integer photoId, Model model) {
        Optional<Photo> foundPhoto = photoRepo.findById(photoId);

        if (foundPhoto.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("photo", foundPhoto.get());
        return "forms/editPhoto";
    }

    @RequestMapping("editedPhoto")
    public String editedPhoto(@RequestParam Integer photoId, @ModelAttribute Photo photo, Model model) {
        Optional<Photo> foundPhoto = photoRepo.findById(photoId);

        if (foundPhoto.isEmpty()) {
            return "redirect:/admin";
        }

        foundPhoto.get().setDescription(photo.getDescription());
        foundPhoto.get().setUrl(photo.getUrl());

        photoRepo.save(foundPhoto.get());

        Optional<Vendor> foundVendor = vendorRepo.findById(foundPhoto.get().getVendor().getId());
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + foundVendor.get().getId();
    }

    @RequestMapping("editAward")
    public String editAward(@RequestParam Integer awardId, Model model) {
        Optional<Award> foundAward = awardRepo.findById(awardId);

        if (foundAward.isEmpty()) {
            return "redirect:/admin";
        }

        model.addAttribute("award", foundAward.get());
        return "forms/editAward";
    }

    @RequestMapping("editedAward")
    public String editedAward(@RequestParam Integer awardId, @ModelAttribute Award award, Model model) {
        Optional<Award> foundAward = awardRepo.findById(awardId);

        if (foundAward.isEmpty()) {
            return "redirect:/admin";
        }

        Optional<Vendor> foundVendor = vendorRepo.findById(foundAward.get().getVendor().getId());
        if (foundVendor.isEmpty()) {
            return "redirect:/admin";
        }

        for (Award a : awardRepo.findAll()) {
            if (a.getYear() == award.getYear() && Objects.equals(a.getTitle(), award.getTitle())) {
                return "redirect:/vendor?id=" + foundVendor.get().getId();
            }
        }

        foundAward.get().setTitle(award.getTitle());
        foundAward.get().setYear(award.getYear());


        awardRepo.save(foundAward.get());

        model.addAttribute("vendor", foundVendor.get());
        return "redirect:/vendor?id=" + foundVendor.get().getId();
    }

}

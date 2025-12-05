package co2123.streetfood.controller;

import co2123.streetfood.StreetfoodApplication;
import co2123.streetfood.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddEditController {

    public static Vendor findVendor(int id){
        Vendor foundVendor = null;
        for(Vendor a :  StreetfoodApplication.vendorList) {
            if(a.getId() == id) {
                foundVendor = a;
            }
        }
        return foundVendor;
    }

    @RequestMapping("editVendor")
    public String editVendorForm(@RequestParam Integer id, Model model) {
        Vendor foundVendor = findVendor(id);

        if(foundVendor == null) {
            return "redirect:/admin";
        }
        model.addAttribute("vendor", foundVendor);
        return "forms/editVendor";
    }


    @RequestMapping("editedVendor")
    public String submittedEditForm(@RequestParam Integer id, @ModelAttribute Vendor vendor, Model model) {
        Vendor foundVendor = findVendor(id);

        foundVendor.setName(vendor.getName());
        foundVendor.setLocation(vendor.getLocation());
        foundVendor.setCuisineType(vendor.getCuisineType());

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + id;
    }



    @RequestMapping("editVendorProfile")
    public String editVendorProfileForm(@RequestParam Integer id, Model model) {
        Vendor foundVendor = findVendor(id);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        VendorProfile profile = foundVendor.getProfile();
        model.addAttribute("profile", profile);
        model.addAttribute("vendor", id);
        return "forms/editVendorProfile";
    }

    @RequestMapping("editedVendorProfile")
    public String submittedProfileEditForm(@RequestParam Integer id, @ModelAttribute VendorProfile profile, Model model) {
        Vendor foundVendor = findVendor(id);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        if(foundVendor.getProfile() == null){
            profile.setId(StreetfoodApplication.vendorprofileList.size()+1);
            foundVendor.setProfile(profile);
            StreetfoodApplication.vendorprofileList.add(profile);
        } else {
            StreetfoodApplication.vendorprofileList.remove(foundVendor.getProfile());
            StreetfoodApplication.vendorprofileList.add(profile);
            foundVendor.getProfile().setBio(profile.getBio());
            foundVendor.getProfile().setSocialMediaHandle(profile.getSocialMediaHandle());
            foundVendor.getProfile().setWebsite(profile.getWebsite());
        }

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + id;
    }


    @RequestMapping("newDish")
    public String newDishForm(@RequestParam Integer id, Model model) {
        Vendor foundVendor = findVendor(id);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        model.addAttribute("dish", new Dish());
        model.addAttribute("tags", StreetfoodApplication.tagList);
        return "forms/newDish";
    }

    @RequestMapping("addDish")
    public String addDish(@RequestParam Integer vendorid, @RequestParam List<Integer> tagIds, @ModelAttribute Dish dish, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        if(foundVendor.getDishes().isEmpty()){
            foundVendor.setDishes(new ArrayList<>());
        }

        dish.setTags(new ArrayList<>());
        for(Integer tagId : tagIds){
            dish.getTags().add(StreetfoodApplication.tagList.get(tagId-1));
        }

        dish.setReviews(new ArrayList<>());
        dish.setVendor(foundVendor);
        dish.setId(StreetfoodApplication.dishList.size()+1);
        StreetfoodApplication.dishList.add(dish);

        foundVendor.getDishes().add(dish);

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + vendorid;
    }



    @RequestMapping("newReview")
    public String newReview(@RequestParam Integer vendorid, @RequestParam Integer dishid, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        model.addAttribute("dishid",dishid);
        model.addAttribute("review", new Review());
        return "forms/newReview";
    }

    @RequestMapping("addReview")
    public String addReview(@RequestParam Integer vendorid, @RequestParam Integer dishid, @ModelAttribute Review review, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        Dish foundDish = null;
        for (Dish dish : foundVendor.getDishes()) {
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
        review.setId(StreetfoodApplication.reviewList.size()+1);
        StreetfoodApplication.reviewList.add(review);

        if(foundDish.getReviews().isEmpty()){
            foundDish.setReviews(new ArrayList<>());
        }
        foundDish.getReviews().add(review);

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("newPhoto")
    public String newPhoto(@RequestParam Integer vendorid, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        model.addAttribute("photo", new Photo());
        return "forms/newPhoto";
    }

    @RequestMapping("addPhoto")
    public String addPhoto(@RequestParam Integer vendorid, @ModelAttribute Photo photo, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        photo.setVendor(foundVendor);
        photo.setId(StreetfoodApplication.photoList.size()+1);
        StreetfoodApplication.photoList.add(photo);

        foundVendor.getPhotos().add(photo);

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("newAward")
    public String newAward(@RequestParam Integer vendorid, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        model.addAttribute("award", new Award());
        return "forms/newAward";
    }

    @RequestMapping("addAward")
    public String addAward(@RequestParam Integer vendorid, @ModelAttribute Award award, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        award.setVendor(foundVendor);
        award.setId(StreetfoodApplication.awardList.size()+1);
        StreetfoodApplication.awardList.add(award);

        foundVendor.getAwards().add(award);

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + vendorid;
    }



    @RequestMapping("editDish")
    public String editDishForm(@RequestParam Integer vendorid, @RequestParam Integer dishid, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        Dish foundDish = null;
        for (Dish dish : foundVendor.getDishes()) {
            if (dish.getId() == dishid) {
                foundDish = dish;
                break;
            }
        }

        if (foundDish == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        model.addAttribute("dish", foundDish);
        model.addAttribute("tags", StreetfoodApplication.tagList);
        return "forms/editDish";
    }
    @RequestMapping("editedDish")
    public String submittedEditDishForm(@RequestParam Integer vendorid, @RequestParam Integer dishid, @RequestParam(required = false) List<Integer> tagIds, @ModelAttribute Dish dish, Model model) {
        Vendor foundVendor = findVendor(vendorid);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        Dish foundDish = null;
        for (Dish d : foundVendor.getDishes()) {
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
            foundDish.getTags().add(StreetfoodApplication.tagList.get(tagId-1));
        }

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("editReview")
    public String editReview(@RequestParam Integer vendorId, @RequestParam Integer reviewId, Model model) {
        Vendor foundVendor = findVendor(vendorId);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        Review foundReview = null;
        for(Review r : StreetfoodApplication.reviewList){
            if(r.getId() == reviewId){
                foundReview = r;
            }
        }

        if (foundReview == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        model.addAttribute("review", foundReview);
        return "forms/editReview";
    }

    @RequestMapping("editedReview")
    public String editedReview(@RequestParam Integer vendorId, @RequestParam Integer reviewId, @ModelAttribute Review review, Model model) {
        Review foundReview = null;
        for(Review r : StreetfoodApplication.reviewList){
            if(r.getId() == reviewId){
                foundReview = r;
            }
        }

        if (foundReview == null) {
            return "redirect:/admin";
        }

        foundReview.setReviewerName(review.getReviewerName());
        foundReview.setComment(review.getComment());
        foundReview.setRating(review.getRating());

        Vendor foundVendor = findVendor(vendorId);
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + vendorId;
    }

    @RequestMapping("editPhoto")
    public String editPhoto(@RequestParam Integer photoId, Model model) {
        Photo foundPhoto = null;
        for(Photo p : StreetfoodApplication.photoList){
            if(p.getId() == photoId){
                foundPhoto = p;
            }
        }

        if (foundPhoto == null) {
            return "redirect:/admin";
        }

        model.addAttribute("photo", foundPhoto);
        return "forms/editPhoto";
    }

    @RequestMapping("editedPhoto")
    public String editedPhoto(@RequestParam Integer photoId, @ModelAttribute Photo photo, Model model) {
        Photo foundPhoto = null;
        for(Photo p : StreetfoodApplication.photoList){
            if(p.getId() == photoId){
                foundPhoto = p;
            }
        }

        if (foundPhoto == null) {
            return "redirect:/admin";
        }

        foundPhoto.setDescription(photo.getDescription());
        foundPhoto.setUrl(photo.getUrl());

        Vendor foundVendor = findVendor(foundPhoto.getVendor().getId());
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + foundVendor.getId();
    }

    @RequestMapping("editAward")
    public String editAward(@RequestParam Integer awardId, Model model) {
        Award foundAward = null;
        for(Award a : StreetfoodApplication.awardList){
            if(a.getId() == awardId){
                foundAward = a;
            }
        }

        if (foundAward == null) {
            return "redirect:/admin";
        }

        model.addAttribute("award", foundAward);
        return "forms/editAward";
    }

    @RequestMapping("editedAward")
    public String editedAward(@RequestParam Integer awardId, @ModelAttribute Award award, Model model) {
        Award foundAward = null;
        for(Award a : StreetfoodApplication.awardList){
            if(a.getId() == awardId){
                foundAward = a;
            }
        }

        if (foundAward == null) {
            return "redirect:/admin";
        }

        foundAward.setTitle(award.getTitle());
        foundAward.setYear(award.getYear());

        Vendor foundVendor = findVendor(foundAward.getVendor().getId());
        if (foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        return "redirect:/vendor?id=" + foundVendor.getId();
    }

}

package co2123.streetfood.controller;

import co2123.streetfood.model.*;
import co2123.streetfood.repository.AwardRepository;
import co2123.streetfood.repository.PhotoRepository;
import co2123.streetfood.repository.ReviewRepository;
import co2123.streetfood.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DeleteController {

    @Autowired
    private VendorRepository vendorRepo;
    @Autowired
    private AwardRepository awardRepo;
    @Autowired
    private PhotoRepository photoRepo;
    @Autowired
    private ReviewRepository reviewRepo;

    @RequestMapping("/deleteVendor")
    public String deleteVendor(@RequestParam("id") Integer id) {
        Optional<Vendor> foundVendor = vendorRepo.findById(id);
        if(foundVendor.isPresent()) {
            vendorRepo.deleteById(id);
        }
        return "redirect:/admin";
    }

    @RequestMapping("/deleteDish")
    public String deleteDish(@RequestParam Integer vendorid, @RequestParam Integer dishid) {
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

        foundVendor.get().getDishes().remove(foundDish);
        vendorRepo.save(foundVendor.get());

        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("/deleteReview")
    public String deleteReview(@RequestParam Integer vendorId, @RequestParam Integer reviewId) {
        Optional<Review> foundReview = reviewRepo.findById(reviewId);
        if(foundReview.isPresent()){
            reviewRepo.deleteById(reviewId);
        } else {
            return "redirect:/admin";
        }

        Optional<Vendor> foundVendor = vendorRepo.findById(vendorId);
        if(foundVendor.isEmpty()){
            return "redirect:/admin";
        }

        for(Dish d : foundVendor.get().getDishes()){
            if(d.getReviews().contains(foundReview.get())){
                d.getReviews().remove(foundReview.get());
                break;
            }
        }

        return "redirect:/vendor?id=" + vendorId;
    }

    @RequestMapping("/deletePhoto")
    public String deletePhoto(@RequestParam Integer photoId) {
        Optional<Photo> foundPhoto = photoRepo.findById(photoId);

        if(foundPhoto.isPresent()){
            photoRepo.delete(foundPhoto.get());
        } else {
            return "redirect:/admin";
        }

        Optional<Vendor> foundVendor = vendorRepo.findById(foundPhoto.get().getVendor().getId());
        if(foundVendor.isEmpty()){
            return "redirect:/admin";
        }
        foundVendor.get().getPhotos().remove(foundPhoto.get());
        return "redirect:/vendor?id=" + foundVendor.get().getId();
    }

    @RequestMapping("/deleteAward")
    public String deleteAward(@RequestParam Integer awardId) {
        Optional<Award> foundAward = awardRepo.findById(awardId);
        if(foundAward.isPresent()){
            awardRepo.deleteById(awardId);
        } else {
            return "redirect:/admin";
        }

        Optional<Vendor> foundVendor = vendorRepo.findById(foundAward.get().getVendor().getId());
        if(foundVendor.isEmpty()){
            return "redirect:/admin";
        }
        foundVendor.get().getAwards().remove(foundAward.get());
        return "redirect:/vendor?id=" + foundVendor.get().getId();
    }

}

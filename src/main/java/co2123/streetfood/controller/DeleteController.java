package co2123.streetfood.controller;

import co2123.streetfood.StreetfoodApplication;
import co2123.streetfood.model.*;
import co2123.streetfood.repository.AwardRepository;
import co2123.streetfood.repository.PhotoRepository;
import co2123.streetfood.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private AwardRepository awardRepo;

    @Autowired
    private PhotoRepository photoRepo;

    @RequestMapping("/deleteVendor")
    public String deleteVendor(@RequestParam("id") Integer id) {
        Vendor foundVendor = vendorRepo.findById(id).get();
        if(foundVendor != null){
            vendorRepo.delete(foundVendor);
        }
        return "redirect:/admin";
    }

    @RequestMapping("/deleteDish")
    public String deleteDish(@RequestParam Integer vendorid, @RequestParam Integer dishid) {
        Vendor foundVendor = vendorRepo.findById(vendorid).get();
        if (foundVendor==null) {
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

        foundVendor.getDishes().remove(foundDish);
        StreetfoodApplication.dishList.remove(foundDish);

        return "redirect:/vendor?id=" + vendorid;
    }

    @RequestMapping("/deleteReview")
    public String deleteReview(@RequestParam Integer vendorId, @RequestParam Integer reviewId) {
        Review foundReview = null;
        for(Review r : StreetfoodApplication.reviewList){
            if(r.getId() == reviewId){
                foundReview = r;
            }
        }
        if(foundReview != null){
            StreetfoodApplication.reviewList.remove(foundReview);
        }

        Vendor foundVendor = vendorRepo.findById(vendorId).get();
        if(foundVendor == null){
            return "redirect:/admin";
        }

        for(Dish d : foundVendor.getDishes()){
            if(d.getReviews().contains(foundReview)){
                d.getReviews().remove(foundReview);
                break;
            }
        }

        return "redirect:/vendor?id=" + vendorId;
    }

    @RequestMapping("/deletePhoto")
    public String deletePhoto(@RequestParam Integer photoId) {
        Photo foundPhoto = null;
        for(Photo p : photoRepo.findAll()){
            if(p.getId() == photoId){
                foundPhoto = p;
            }
        }
        if(foundPhoto != null){
            photoRepo.delete(foundPhoto);
        } else {
            return "redirect:/admin";
        }

        Vendor foundVendor = vendorRepo.findById(foundPhoto.getVendor().getId()).get();
        if(foundVendor == null){
            return "redirect:/admin";
        }
        foundVendor.getPhotos().remove(foundPhoto);
        return "redirect:/vendor?id=" + foundVendor.getId();
    }

    @RequestMapping("/deleteAward")
    public String deleteAward(@RequestParam Integer awardId) {
        Award foundAward = null;
        for(Award a : awardRepo.findAll()){
            if(a.getId() == awardId){
                foundAward = a;
            }
        }
        if(foundAward != null){
            awardRepo.delete(foundAward);
        } else {
            return "redirect:/admin";
        }

        Vendor foundVendor = vendorRepo.findById(foundAward.getVendor().getId()).get();
        if(foundVendor == null){
            return "redirect:/admin";
        }
        foundVendor.getAwards().remove(foundAward);
        return "redirect:/vendor?id=" + foundVendor.getId();
    }

}

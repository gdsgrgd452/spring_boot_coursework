package co2123.streetfood.controller;

import co2123.streetfood.StreetfoodApplication;
import co2123.streetfood.model.Vendor;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new VendorValidator());
    }

    @RequestMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("vendors", StreetfoodApplication.vendorList);
        return "admin";
    }


    @RequestMapping("/newVendor")
    public String newVendor(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "forms/newVendor";
    }


    @RequestMapping("/addVendor")
    public String addVendor(@Valid @ModelAttribute Vendor vendor, BindingResult result) {
        if (result.hasErrors()) {
            return "forms/newVendor";
        }
        System.out.println(StreetfoodApplication.vendorList.size()+1);
        vendor.setId(StreetfoodApplication.vendorList.size());
        StreetfoodApplication.vendorList.add(vendor);
        return "redirect:/admin";
    }

    @RequestMapping("/vendor")
    public String listVendor(@RequestParam Integer id, Model model) {

        Vendor foundVendor = null;
        for(Vendor a :  StreetfoodApplication.vendorList) {
            if(a.getId() == id) {
                foundVendor = a;
            }
        }

        if(foundVendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", foundVendor);
        return "vendorSummary";
    }



}

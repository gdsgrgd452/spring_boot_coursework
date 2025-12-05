package co2123.streetfood.controller;

import co2123.streetfood.StreetfoodApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VendorController {

    @RequestMapping("/vendors")
    public String showVendors(Model model) {
        model.addAttribute("vendors", StreetfoodApplication.vendorList);
        return "vendors";
    }
}


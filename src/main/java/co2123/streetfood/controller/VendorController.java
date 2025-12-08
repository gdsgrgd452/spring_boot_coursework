package co2123.streetfood.controller;

import co2123.streetfood.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VendorController {

    @Autowired
    private VendorRepository vendorRepo;

    @RequestMapping("/vendors")
    public String showVendors(Model model) {
        model.addAttribute("vendors", vendorRepo.findAll());
        return "vendors";
    }
}


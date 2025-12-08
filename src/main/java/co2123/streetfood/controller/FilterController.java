package co2123.streetfood.controller;

import co2123.streetfood.model.Vendor;
import co2123.streetfood.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilterController {

    @Autowired
    private VendorRepository vendorRepo;

    public List<Vendor> method1(String name) {
        return List.of(vendorRepo.findByNameContaining(name));
    }

    public List<Vendor> method2(String name) {
        return vendorRepo.findByDishesNameContaining(name);
    }

    @GetMapping("/search1")
    public String search1(@RequestParam String vendor, Model model) {
        List<Vendor> list = method1(vendor);
        if(list.isEmpty()){
            model.addAttribute("vendors", vendorRepo.findAll());
        } else {
            model.addAttribute("vendors", list);
        }
        return "vendors";
    }


    @GetMapping("/search2")
    public String search2(@RequestParam String dish, Model model) {
        List<Vendor> list = method2(dish);
        if(list.isEmpty()){
            model.addAttribute("vendors", vendorRepo.findAll());
        } else {
            model.addAttribute("vendors", list);
        }
        return "vendors";
    }

}

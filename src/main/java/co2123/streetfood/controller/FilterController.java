package co2123.streetfood.controller;

import co2123.streetfood.StreetfoodApplication;
import co2123.streetfood.model.Dish;
import co2123.streetfood.model.Vendor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterController {

    public List<Vendor> method1(String search){
        List<Vendor> vendors = new ArrayList<Vendor>();
        for(Vendor a : StreetfoodApplication.vendorList){
            if(a.getName().contains(search)){
                vendors.add(a);
            }
        }
        return vendors;
    }

    @GetMapping("/search1")
    public String search1(@RequestParam String vendor, Model model) {
        List<Vendor> list = method1(vendor);
        if(list.isEmpty()){
            model.addAttribute("vendors", StreetfoodApplication.vendorList);
        } else {
            model.addAttribute("vendors", list);
        }
        return "vendors";
    }

    public List<Vendor> method2(String search){
        List<Vendor> vendors = new ArrayList<>();
        for(Vendor a : StreetfoodApplication.vendorList){
            for(Dish d : a.getDishes()){
                if(d.getName().contains(search) && !vendors.contains(a)){
                    vendors.add(a);

                }
            }
        }
        return vendors;
    }

    @GetMapping("/search2")
    public String search2(@RequestParam String dish, Model model) {
        List<Vendor> list = method2(dish);
        if(list.isEmpty()){
            model.addAttribute("vendors", StreetfoodApplication.vendorList);
        } else {
            model.addAttribute("vendors", list);
        }
        return "vendors";
    }

}

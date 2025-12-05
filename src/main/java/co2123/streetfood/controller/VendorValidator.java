package co2123.streetfood.controller;

import co2123.streetfood.StreetfoodApplication;
import co2123.streetfood.model.Vendor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class VendorValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Vendor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vendor vendor = (Vendor) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Your vendor needs a name!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "", "Your vendor needs a location!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cuisineType", "", "Your vendor needs a cuisine type!");

        if (vendor.getCuisineType() != null && vendor.getCuisineType().length() > 50) {
            errors.rejectValue("cuisineType", "", "Cuisine type must be 50 characters or fewer.");
        }

        for(Vendor a : StreetfoodApplication.vendorList){
            if(a.getName().equals(vendor.getName())){
                errors.rejectValue("name", "", "Vendor name already exists.");
            }
        }



    }
}

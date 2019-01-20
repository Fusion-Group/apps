package com.fusion.apps.controller;

import com.fusion.apps.model.Customer;
import com.fusion.apps.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("cus", customerRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createCustomer(@Valid Customer customer, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "creare";
        }else {
            customerRepository.save(customer);
        }
        return "index";
    }

    @GetMapping("/create")
    public String fornCreate(Map<String, Object> model){
        Customer customer = new Customer();
        model.put("cus",customer);
        return "create";
    }


}

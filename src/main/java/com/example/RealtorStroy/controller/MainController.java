package com.example.RealtorStroy.controller;

import com.example.RealtorStroy.model.Estate;
import com.example.RealtorStroy.model.Role;
import com.example.RealtorStroy.model.User;
import com.example.RealtorStroy.model.repository.EstateRepo;
import com.example.RealtorStroy.model.repository.UserRepo;
import com.example.RealtorStroy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    EstateRepo estateRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    Role role_user= new Role(("ROLE_USER"));

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE,
            path = "estate-img/{estateId}"
    )
    @ResponseBody
    public byte[] getImage(@PathVariable("estateId") Long blogId, HttpServletResponse response) {
        return estateRepo.findById(blogId).orElseThrow().getPhoto();
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("estates",estateRepo.findAll());
        return "index";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("products")
    public String products(Model model) {
        model.addAttribute("estates",estateRepo.findAll());
        return "products";
    }

    @GetMapping("users")
    public String users(Model model) {
        model.addAttribute("users",userRepo.findAll());
        return "users";
    }


    @GetMapping("estate/{id}")
    public String estate(@PathVariable("id") Long id, Model model) {
        Estate estate = estateRepo.findById(id).orElseThrow();
        model.addAttribute(estate);

        return "post";
    }

    @GetMapping
    public String home(Model model) {
        //model.addAttribute("estates",estateRepo.findAll());
        return "index";
    }
    @GetMapping("addproducts")
    public String addProducts(Model model) {
        Estate newEstate = new Estate();
        model.addAttribute("newEstate",newEstate);
        return "addproducts";
    }

    @GetMapping("addusers")
    public String addUser(Model model) {
        User newUser = new User();
        model.addAttribute("newUser",newUser);
        return "adduser";
    }


    @PostMapping(value = "savepost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String savePost(
            @ModelAttribute Estate estate,
            @RequestPart("photofile") MultipartFile photo,
            Principal principal
    ) {
        try {
            estate.setPhoto(photo.getBytes());
            estateRepo.save(estate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/products";
    }

    @PostMapping(value = "saveuser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveUsers(
            @ModelAttribute User user,
            Principal principal
    ) {
        user.setRoles(Set.of(role_user));
        userService.saveUser(user);

        return "redirect:/users";
    }
}

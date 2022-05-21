package com.example.RealtorStroy.controller;

import com.example.RealtorStroy.model.Estate;
import com.example.RealtorStroy.model.repository.EstateRepo;
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

@Controller
public class MainController {

    @Autowired
    EstateRepo estateRepo;

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


    @GetMapping("estate/{id}")
    public String estate(@PathVariable("id") Long id, Model model) {
        Estate estate = estateRepo.findById(id).orElseThrow();
        model.addAttribute(estate);

        return "post";
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("estates",estateRepo.findAll());
        return "index";
    }
    @GetMapping("addproducts")
    public String createPost(Model model) {
        Estate newEstate = new Estate().setCreatedDate(LocalDateTime.now());
        model.addAttribute("newEstate",newEstate);
        return "addproducts";
    }

    @PostMapping(value = "savepost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String savePost(
            @ModelAttribute Estate estate,
            @RequestPart("photofile") MultipartFile photo,
            Principal principal
    ) {
        estateRepo.save(estate);
        try {
            estate.setPhoto(photo.getBytes());
            estateRepo.save(estate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/products";
    }

}

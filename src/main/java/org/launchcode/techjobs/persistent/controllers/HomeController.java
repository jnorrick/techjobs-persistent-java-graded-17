package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }
    @GetMapping("add")
    public String displayAddJobForm(@RequestParam(required = false) Integer employerId,Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());

//        model.addAttribute("skills", skillsRepository.findAll())? this is not in part 3 that I can tell but is in the tests for part 3?
        return "add";
    }

//    if (categoryId == null) {
//        model.addAttribute("title", "All Events");
//        model.addAttribute("events", eventRepository.findAll());
//    } else {
//        Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
//        if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Category ID: " + categoryId);
//        } else {
//            EventCategory category = result.get();
//            model.addAttribute("title", "Events in category: " + category.getName());
//            model.addAttribute("events", category.getEvents());
//        }
//    }
//        return "events/index";
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            return "add";
        }

        employerRepository.findById(employerId);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}

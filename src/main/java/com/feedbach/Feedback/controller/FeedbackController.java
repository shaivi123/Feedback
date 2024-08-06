package com.feedbach.Feedback.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.feedbach.Feedback.service.FeedbackService;
import com.feedbach.Feedback.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "index";
    }

    @GetMapping("/getting")
    public String getAllData(Model model){
        List<Feedback> feedbackList = feedbackService.getAllData();
        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("feedback", new Feedback());
        return "getting";
    }

    @PostMapping("sendFeedback")
    public String sendFeedback(@ModelAttribute Feedback feedback, Model model){
        feedbackService.sendFeedback(feedback);
        model.addAttribute("feedback", feedback);
        return "result";
    }

    @GetMapping("/get/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        model.addAttribute("feedback", feedback);
        return "update";
    }

    //note if we use thymleaf and we use @ModelAttribute so this is not executable from postman so we can replace with @RequestBody for testing
    @GetMapping("/update/{id}")
    public String updateFeedbackGet(@ModelAttribute Feedback feedback, @PathVariable int id, Model model) throws Exception {
        Feedback feedback1 = feedbackService.updateFeedback(feedback, id);
        model.addAttribute("feedback1", feedback1);
         return  "redirect:/getting";
    }


    //    @PutMapping("update/{id}")
//    public String updateFeedback(@ModelAttribute Feedback feedback, @PathVariable int id, Model model) throws Exception {
//        try {
//            feedbackService.updateFeedback(feedback, id);
//            model.addAttribute("feedback", feedback);
//            model.addAttribute("id", id);
//            return "redirect:/getting";
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Failed to update feedback: " + e.getMessage());
//            return "error";
//        }
//    }


    //    @PutMapping("/update/{id}")
//    public String updateFeedback(@ModelAttribute Feedback feedback, @PathVariable("id") int id, Model model) {
//        try {
//            feedbackService.updateFeedback(feedback, id);
//            model.addAttribute("feedback", feedback);
//            model.addAttribute("id", id);
//            return "redirect:/getting";
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Failed to update feedback: " + e.getMessage());
//            return "error";
//        }
//    }

}

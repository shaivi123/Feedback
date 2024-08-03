package com.feedbach.Feedback.controller;
import org.springframework.ui.Model;

import com.feedbach.Feedback.service.FeedbackService;
import com.feedbach.Feedback.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "index";
    }

    @PostMapping("sendFeedback")
    public String sendFeedback(@ModelAttribute Feedback feedback, Model model){
        feedbackService.sendFeedback(feedback);
        model.addAttribute("feedback", feedback);
        return "result";
    }
}

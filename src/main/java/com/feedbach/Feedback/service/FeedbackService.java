package com.feedbach.Feedback.service;

import com.feedbach.Feedback.repository.FeedbackRepo;
import com.feedbach.Feedback.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public Feedback sendFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    public Feedback updateFeedback(Feedback feedback, int id) throws Exception {
     return feedbackRepo.findById(id)
             .map(fd -> {
                 fd.setDescription(feedback.getDescription());
                 fd.setEmail(feedback.getEmail());
                 fd.setName(feedback.getName());
                 return feedbackRepo.save(fd);
             }).orElseThrow(() -> new Exception(String.valueOf(id)));
    }

    public List<Feedback> getAllData() {
        return feedbackRepo.findAll();
    }


    public Feedback getFeedbackById(int id) {
        Optional<Feedback> optionalFeedback = feedbackRepo.findById(id);
        if (optionalFeedback.isPresent()) {
            return optionalFeedback.get();
        } else {
            throw new RuntimeException("Feedback not found for id :: " + id);
        }
    }
}

package com.feedbach.Feedback.service;

import com.feedbach.Feedback.repository.FeedbackRepo;
import com.feedbach.Feedback.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public Feedback sendFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }
}

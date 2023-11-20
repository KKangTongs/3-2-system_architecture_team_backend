package kkangtongs.funfactservice.controller;


import kkangtongs.funfactservice.DTO.SubscriptionRequest;
import kkangtongs.funfactservice.domain.FunFact;
import kkangtongs.funfactservice.domain.Subscription;
import kkangtongs.funfactservice.service.FunFactService;
import kkangtongs.funfactservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/funfacts")
public class FunFactController {

    private final FunFactService funFactService;
    private final SubscriptionService subscriptionService;


    @Autowired
    public FunFactController(FunFactService funFactService, SubscriptionService subscriptionService) {
        this.funFactService = funFactService;
        this.subscriptionService = subscriptionService;
    }

    // Subscribe to a topic
    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriptionRequest request) {
        Long userId = request.getUserId();
        String topic = request.getTopic();
        Subscription subscription = subscriptionService.subscribe(userId, topic);
        return ResponseEntity.ok(subscription);
    }

    // Get subscription by user ID
    
    @GetMapping("/subscriptions/{userId}")
    public ResponseEntity<?> getSubscriptions(@PathVariable Long userId) {
        return subscriptionService.getSubscriptionByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Unsubscribe from a topic
    @DeleteMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestBody SubscriptionRequest request) {
        Long userId = request.getUserId();
        String topic = request.getTopic();
        subscriptionService.unsubscribe(userId, topic);
        return ResponseEntity.ok().build();
    }

}

package kkangtongs.funfactservice.service;

import kkangtongs.funfactservice.domain.Subscription;
import kkangtongs.funfactservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription subscribe(Long userId, String topic) {
        Optional<Subscription> existingSubscriptionOpt = subscriptionRepository.findByUserId(userId);

        Subscription subscription;
        if (existingSubscriptionOpt.isPresent()) {
            subscription = existingSubscriptionOpt.get();
            if (!subscription.getTopics().contains(topic)) {
                subscription.getTopics().add(topic);
            }
        } else {
            subscription = new Subscription(userId);
            subscription.setTopics(new ArrayList<>(Collections.singletonList(topic)));
        }
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscriptionByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public void unsubscribe(Long userId, String topic) {
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findByUserId(userId);
        subscriptionOpt.ifPresent(subscription -> {
            subscription.getTopics().remove(topic);
            subscriptionRepository.save(subscription);
        });
    }
}

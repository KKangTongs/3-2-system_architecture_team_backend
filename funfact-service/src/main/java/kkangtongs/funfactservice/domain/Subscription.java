package kkangtongs.funfactservice.domain;

import jakarta.persistence.*;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    private List<String> topics = new ArrayList<>();

    // 생성자, 게터, 세터, 기타 필요한 메소드들...
    public Subscription() {
    }

    public Subscription(Long userId) {
        this.userId = userId;
    }

    // getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getTopics() {
        return topics;
    }

    public Subscription(Long id, Long userId, List<String> topics) {
        this.id = id;
        this.userId = userId;
        this.topics = topics;
    }
}

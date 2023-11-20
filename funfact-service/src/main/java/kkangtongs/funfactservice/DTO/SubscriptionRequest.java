package kkangtongs.funfactservice.DTO;

public class SubscriptionRequest {
    private Long userId;
    private String topic;
    // getters and setters


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

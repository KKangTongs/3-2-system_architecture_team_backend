package kkangtongs.funfactservice.domain;
import jakarta.persistence.*;

@Entity
public class FunFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // 'fact' 필드를 'content'로 변경합니다.

    public FunFact() {
        // 기본 생성자
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

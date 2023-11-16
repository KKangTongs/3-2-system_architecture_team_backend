package kkangtongs.authservice.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TokenDTO {
    private String accessToken;
    @Builder
    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}

package kkangtongs.authservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthInfoDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String accessToken;
}

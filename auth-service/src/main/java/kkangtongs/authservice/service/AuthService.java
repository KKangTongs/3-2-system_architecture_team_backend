package kkangtongs.authservice.service;


import jakarta.transaction.Transactional;
import kkangtongs.authservice.DTO.AuthInfoDTO;
import kkangtongs.authservice.DTO.ProfileDTO;
import kkangtongs.authservice.domain.AuthInfo;
import kkangtongs.authservice.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    @Autowired
    private AuthRepository authRepository;
    @Transactional
    public void signup(AuthInfoDTO authInfoDTO) {
        if (authRepository.existsByEmail(authInfoDTO.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        AuthInfo newMember = AuthInfo.builder()
                .email(authInfoDTO.getEmail())
                .name(authInfoDTO.getName())
                .password(authInfoDTO.getPassword())
                .build();
        authRepository.save(newMember);
    }

//    public AuthInfo createAuthInfo(AuthInfo authInfo) {
//        if (authInfo == null || authInfo.getEmail() == null) {
//            throw new RuntimeException("Invalid arguments");
//        }
//        String email = authInfo.getEmail();
//        if (authRepository.existsByEmail(email)) {
//            throw new RuntimeException("Email already exists");
//        }
//        return authRepository.save(authInfo);
//    }

    public AuthInfo getByCredentials(final String email, final String password) {
        return authRepository.findByEmailAndPassword(email, password);
    }

    public ProfileDTO getUser(Long id) {
        AuthInfo user = authRepository.findById(id);
        ProfileDTO profileDTO = ProfileDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
        return profileDTO;
    }

}

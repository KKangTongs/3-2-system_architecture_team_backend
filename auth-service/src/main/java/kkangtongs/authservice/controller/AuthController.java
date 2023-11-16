package kkangtongs.authservice.controller;

import io.jsonwebtoken.*;
import kkangtongs.authservice.DTO.AuthInfoDTO;
import kkangtongs.authservice.DTO.ProfileDTO;
import kkangtongs.authservice.common.Result;
import kkangtongs.authservice.domain.AuthInfo;
import kkangtongs.authservice.repository.AuthRepository;
import kkangtongs.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthRepository authRepository;


    /**
     * 토큰 확인
     * @RequestBody token
     */
    @PostMapping("/tokenCheck")
    public ResponseEntity<AuthInfoDTO> authenticate(@RequestBody String token) {


        // 토큰 인증후 email 가져오기
        String email = verifyJwtToken(token);

        AuthInfo user = authRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        AuthInfoDTO responseAuthInfoDTO = AuthInfoDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .accessToken(token)
                .build();

        return ResponseEntity.ok().body(responseAuthInfoDTO);
    }

    private String generateJwtToken(String email) {
        //
        String tokenId = UUID.randomUUID().toString();

        // JWT 토큰 생성
        Claims claims = Jwts.claims()
                .setSubject(email)
                .setId(tokenId);
        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "49b99b47afdf1f340d499571ede46906ba266746f4a5fa1db3ab7120ec88ad8f74f8ca0be38d286d23b2b709de8f181b694decc6a74db83c7822dd46dbdd8a39") // 비밀키 설정
                .compact();

        return jwtToken;
    }

    // 토큰 검증
    public String verifyJwtToken(String jwtToken) {
        try {
            // 토큰 검증
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey("49b99b47afdf1f340d499571ede46906ba266746f4a5fa1db3ab7120ec88ad8f74f8ca0be38d286d23b2b709de8f181b694decc6a74db83c7822dd46dbdd8a39") // 발급할 때 사용한 비밀 키와 동일한 비밀 키를 사용
                    .build()
                    .parseClaimsJws(jwtToken);

            String email = claimsJws.getBody().getSubject();

            // 추후에 보안을 신경 쓸꺼면 쓰면됨
            String tokenId = claimsJws.getBody().getId();

            return email;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 회원가입
     *
     * @RequestBody authInfoDTO
     */
    @PostMapping("/signup")
    public ResponseEntity<Result> registerUser(@RequestBody AuthInfoDTO authInfoDTO) {
        try {
            authService.signup(authInfoDTO);

            Result result = Result.builder()
                    .isSuccess(true)
                    .message("회원가입 요청 성공")
                    .build();

            return ResponseEntity.ok().body(result);

        } catch (Exception exception) {
            Result result = Result.builder()
                    .isSuccess(false)
                    .message("일시적인 회원가입 오류가 발생했습니다.")
                    .build();

            return ResponseEntity.badRequest().body(result);

        }
    }

    /**
     * 로그인
     *
     * @RequestBody authInfoDTO
     */
    @PostMapping("/login")
    public ResponseEntity<Result> authenticate(@RequestBody AuthInfoDTO authInfoDTO) {
        AuthInfo authInfo = authService.getByCredentials(authInfoDTO.getEmail(), authInfoDTO.getPassword());

        if (authInfo == null) {
            Result result = Result.builder()
                    .isSuccess(false)
                    .message("로그인 실패")
                    .build();
            return ResponseEntity.ok().body(result);
        } else {
            String jwtToken = generateJwtToken(authInfo.getEmail());

            AuthInfoDTO responseAuthInfoDTO = AuthInfoDTO.builder()
                    .id(authInfo.getId())
                    .email(authInfo.getEmail())
                    .name(authInfo.getName())
                    .accessToken(jwtToken)
                    .build();

            Result result = Result.builder()
                    .isSuccess(true)
                    .message("로그인 성공")
                    .result(responseAuthInfoDTO)
                    .build();
            return ResponseEntity.ok().body(result);
        }
    }

    /**
     * 사용자 정보 조회
     *
     * @PathVariable member_id
     */
    @GetMapping("/profile/{member_id}")
    public ResponseEntity<Result> getProfile(@PathVariable Long member_id) {
        ProfileDTO userprofile = authService.getUser(member_id);

        if (userprofile == null) {
            Result result = Result.builder()
                    .isSuccess(false)
                    .message("사용자 정보 조회 실패")
                    .build();
            return ResponseEntity.badRequest().body(result);
        } else {
            Result result = Result.builder()
                    .isSuccess(true)
                    .message("사용자 정보 조회 성공")
                    .result(userprofile)
                    .build();
            return ResponseEntity.ok().body(result);
        }
    }
}

package kkangtongs.authservice.repository;

import kkangtongs.authservice.domain.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "auth", path="auth")
public interface AuthRepository extends JpaRepository<AuthInfo, String> {
    AuthInfo findByEmail(String email); // 이메일로 사용자 정보 찾기
    boolean existsByEmail(String email); // 이메일 중복 확인
    AuthInfo findByEmailAndPassword(String email, String password); // 이메일과 비밀번호로 사용자 정보 찾기
    AuthInfo findById(Long id); // 사용자 아이디로 정보 가져오기
}

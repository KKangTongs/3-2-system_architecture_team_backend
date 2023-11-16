package kkangtongs.funfactservice.repository;

import kkangtongs.funfactservice.domain.FunFact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FunFactRepository extends JpaRepository<FunFact, Long> {
    // 필요한 경우, 여기에 추가적인 쿼리 메소드를 정의할 수 있습니다.
    // 예: List<FunFact> findByFactContaining(String keyword);
    List<FunFact> findByContentContaining(String content); // 메서드 이름을 변경합니다.


     Optional<FunFact> findById(Long id);
     FunFact save(FunFact funFact);
     void deleteById(Long id);



}

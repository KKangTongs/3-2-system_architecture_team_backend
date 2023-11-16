package kkangtongs.funfactservice.service;

import kkangtongs.funfactservice.domain.FunFact;
import kkangtongs.funfactservice.repository.FunFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunFactService {

    private final FunFactRepository funFactRepository;

    @Autowired
    public FunFactService(FunFactRepository funFactRepository) {
        this.funFactRepository = funFactRepository;
    }

    // 모든 흥미로운 사실 조회
    public List<FunFact> getAllFunFacts() {
        return funFactRepository.findAll();
    }

    // 흥미로운 사실 추가
    public FunFact addFunFact(FunFact funFact) {
        return funFactRepository.save(funFact);
    }

    // 특정 흥미로운 사실 조회
    public FunFact getFunFactById(Long id) {
        return funFactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FunFact not found with id " + id));
    }

    // 흥미로운 사실 업데이트
    public FunFact updateFunFact(Long id, FunFact updatedFunFact) {
        FunFact funFact = funFactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FunFact not found with id " + id));

        funFact.setContent(updatedFunFact.getContent());
        return funFactRepository.save(funFact);
    }

    // 흥미로운 사실 삭제
    public void deleteFunFact(Long id) {
        funFactRepository.deleteById(id);
    }
}

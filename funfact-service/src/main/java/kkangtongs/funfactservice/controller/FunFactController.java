package kkangtongs.funfactservice.controller;


import kkangtongs.funfactservice.domain.FunFact;
import kkangtongs.funfactservice.service.FunFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/funfacts")
public class FunFactController {

    private final FunFactService funFactService;

    @Autowired
    public FunFactController(FunFactService funFactService) {
        this.funFactService = funFactService;
    }

    // 모든 흥미로운 사실을 조회
    @GetMapping
    public List<FunFact> getAllFunFacts() {
        return funFactService.getAllFunFacts();
    }

    // 새로운 흥미로운 사실을 추가
    @PostMapping
    public FunFact addFunFact(@RequestBody FunFact funFact) {
        return funFactService.addFunFact(funFact);
    }

    // 특정 흥미로운 사실을 조회
    @GetMapping("/{id}")
    public FunFact getFunFactById(@PathVariable Long id) {
        return funFactService.getFunFactById(id);
    }

    // 흥미로운 사실 업데이트
    @PutMapping("/{id}")
    public FunFact updateFunFact(@PathVariable Long id, @RequestBody FunFact funFact) {
        return funFactService.updateFunFact(id, funFact);
    }

    // 흥미로운 사실 삭제
    @DeleteMapping("/{id}")
    public void deleteFunFact(@PathVariable Long id) {
        funFactService.deleteFunFact(id);
    }
}

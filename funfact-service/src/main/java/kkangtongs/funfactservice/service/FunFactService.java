package kkangtongs.funfactservice.service;

import kkangtongs.funfactservice.domain.FunFact;
import kkangtongs.funfactservice.repository.FunFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class FunFactService {


    private final FunFactRepository funFactRepository;

    private final Map<String, List<String>> facts = null;

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


    public Map<String, List<Map<String, String>>> getFactByTopic(String topic) {

        List<Map<String, String>> topicFacts = new ArrayList<>();
        if (facts.containsKey(topic)) {
            for (String fact : facts.get(topic)) {
                Map<String, String> factMap = new HashMap<>();
                factMap.put("fact", fact);
                topicFacts.add(factMap);
            }
        }
        Map<String, List<Map<String, String>>> response = new HashMap<>();
        response.put(topic, topicFacts);
        return response;
    }

    public Map<String, List<Map<String, String>>> getAllFacts() {
        Map<String, List<Map<String, String>>> allFacts = new HashMap<>();
        for (String topic : facts.keySet()) {
            allFacts.put(topic, getFactByTopic(topic).get(topic));
        }
        return allFacts;
    }

    public Map<String, String> generateTextByTopic(String topic) {
        Map<String, String> text = new HashMap<>();
        if (facts.containsKey(topic)) {
            Random random = new Random();
            List<String> topicFacts = facts.get(topic);
            String selectedFact = topicFacts.get(random.nextInt(topicFacts.size()));
            text.put("text", selectedFact);
        }
        return text;
    }

    String openAiApiKey = "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";

    // OpenAI를 사용하여 주제에 따른 재미있는 사실을 생성합니다.
    public String getRandomFactByTopic(String topic) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openAiApiKey);

        // 메시지 리스트를 생성합니다.
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> messageContent = new HashMap<>();
        messageContent.put("role", "user");
        messageContent.put("content", topic + "분야에서 재밌는 사실 알려줘");
        messages.add(messageContent);

        // 요청 본문을 구성합니다.
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        Map<String, Object> response = restTemplate.postForObject(
                "https://api.openai.com/v1/chat/completions", entity, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        Map<String, String> message = (Map<String, String>) choices.get(0).get("message");
        String generatedText = message.get("content");

        return generatedText;
    }
}
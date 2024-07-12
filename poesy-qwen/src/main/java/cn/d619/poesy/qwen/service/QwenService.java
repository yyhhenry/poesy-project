package cn.d619.poesy.qwen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.d619.poesy.qwen.pojo.dto.OllamaRequest;
import cn.d619.poesy.qwen.pojo.dto.OllamaResponse;

@Service
public class QwenService {

    @Autowired
    private RestTemplate restTemplate;

    public OllamaResponse accessOllama(OllamaRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OllamaRequest> entity = new HttpEntity<>(request, headers);
        String ollamaUrl = "http://localhost:11434/api/generate";

        return restTemplate.postForObject(ollamaUrl, entity, OllamaResponse.class);
    }
}

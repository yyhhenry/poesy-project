package cn.d619.poesy.qwen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cn.d619.poesy.qwen.pojo.dto.OllamaRequest;
import cn.d619.poesy.qwen.pojo.dto.OllamaResponse;
import reactor.core.publisher.Flux;

@Service
public class QwenService {

    @Value("${OLLAMA_URL}")
    private String ollamaUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Flux<OllamaResponse> accessOllama(OllamaRequest request) {
        WebClient webClient = webClientBuilder.baseUrl(ollamaUrl).build();
        return webClient.post()
                .uri("/api/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(OllamaResponse.class);
    }
}

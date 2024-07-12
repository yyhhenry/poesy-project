package cn.d619.poesy.qwen;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import cn.d619.poesy.qwen.exception.HttpException;
import cn.d619.poesy.qwen.pojo.dto.OllamaRequest;
import cn.d619.poesy.qwen.pojo.dto.OllamaResponse;
import cn.d619.poesy.qwen.pojo.dto.QwenRequest;
import cn.d619.poesy.qwen.pojo.dto.QwenResponse;
import cn.d619.poesy.qwen.service.QwenService;
import cn.d619.poesy.qwen.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class QwenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private QwenService qwenService;

    @PostMapping("/api/qwen/answer")
    public QwenResponse chat(@RequestBody QwenRequest request,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        OllamaRequest ollamaRequest = new OllamaRequest(request.getPrompt());

        OllamaResponse ollamaResponse = qwenService.accessOllama(ollamaRequest);
        QwenResponse response = new QwenResponse(ollamaResponse.getResponse());

        return response;
    }

}

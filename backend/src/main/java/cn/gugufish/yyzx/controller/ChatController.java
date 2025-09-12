package cn.gugufish.yyzx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {
    private final ChatClient chatClient;
    @RequestMapping("/chat")
    public String chat(String message) {
        return chatClient.prompt().
                user(message).call().content();
    }
    @RequestMapping(value = "/flux",produces = "text/event-stream")
    public Flux<String> flux(String message) {
        return chatClient.prompt().
                user(message).stream().content();
    }
}

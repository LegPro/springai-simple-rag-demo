package com.example.ragdemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    ChatClient chatClient;

    public AppController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        chatClient = chatClientBuilder.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults())).build();
    }

    @GetMapping("/newproducts")
    public String getResponse(@RequestParam(value = "message", defaultValue = "Give me Information about Bank of America") String message) {
        return chatClient.prompt().user(message)
                .call()
                .chatResponse().getResult().getOutput().getContent();

    }
}

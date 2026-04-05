package dev.backend.product_info_parser.service;

import dev.backend.product_info_parser.gemini_client.Gemini;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeminiService {
    private final Gemini client;
    public GeminiService(Gemini geminiClient) {
        this.client = geminiClient;
    }

    public List<String> generateSubTask(String task) {
        try
        {
            String steps = client.breakDownTask(task);
            return client.parseResponse(steps);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

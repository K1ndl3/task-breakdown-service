package dev.backend.product_info_parser.gemini_client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.genai.Client;

@Component
public class Gemini {

    private final Client client;

   public Gemini(@Value("${API_KEY}") String apiKey) {
        this.client = Client.builder()
                            .apiKey(apiKey)
                            .build();
    }
    
    public String breakDownTask(String userTask) {
        String prompt = "Break down the task '" + userTask + "' into a simple JSON array of strings. " +
                        "Each string should be a single actionable step. Return ONLY the raw JSON array, no markdown fences. "
                        + "Keep the steps less than 7.";

        try {
            // Updated to the stable 2026 model: gemini-3-flash
            var response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null
            );
            
            return response.text();
            
        } catch (Exception e) {
            return "[\"Error fetching steps: " + e.getMessage() + "\"]";
        }
    }

    public List<String> parseResponse(String response) {
        if (response == null || response.isBlank()) return new ArrayList<>();

        String clean = response.trim();
        
        // Basic JSON cleanup logic
        if (clean.startsWith("[") && clean.endsWith("]")) {
            clean = clean.substring(1, clean.length() - 1);
        }

        // Split by "," but only if it's between quotes
        String[] parts = clean.split("\",\\s*\"");

        List<String> result = new ArrayList<>();
        for (String part : parts) {
            result.add(part.replace("\"", "").trim());
        }

        return result;
    }
}
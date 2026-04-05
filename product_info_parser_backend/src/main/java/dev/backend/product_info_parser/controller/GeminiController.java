package dev.backend.product_info_parser.controller;

import dev.backend.product_info_parser.DTO.RequestDTO;
import dev.backend.product_info_parser.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class GeminiController {
    private final GeminiService service;

    public GeminiController(GeminiService service) {
        this.service = service;
    }

    @PostMapping("/api")
    public ResponseEntity<List<String>> getStep(@RequestBody RequestDTO rq) {
        String taskDetail = rq.getRequest();
        try
        {
            return ResponseEntity.ok(service.generateSubTask(taskDetail));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}


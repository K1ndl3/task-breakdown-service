package dev.backend.product_info_parser.secret;

import org.springframework.stereotype.Service;

import com.google.api.client.util.Value;
@Service
public class MyApiService {
    @Value("${API_KEY}")
    private String API_KEY;

    public String fetch() {
        return this.API_KEY;
    }
}

package dev.backend.product_info_parser.DTO;

import lombok.Data;

@Data

public class RequestDTO {
    private String request;

    public RequestDTO() {}

    public RequestDTO(String request) {
        this.request =request;
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String req) {
        this.request = req;
    }
}

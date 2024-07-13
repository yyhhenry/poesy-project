package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

@Data
public class PaginationRequest {
    private int page;
    private int size;

    public PaginationRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}

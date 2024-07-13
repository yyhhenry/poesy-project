package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

@Data
public class PaginationRequest {
    private Long offset;
    private Long size;

    public PaginationRequest(Long offset, Long size) {
        this.offset = offset;
        this.size = size;
    }

    public PaginationRequest(Long offset) {
        this.offset = offset;
        this.size = 6L;
    }
}

package cn.d619.poesy.article.pojo.dto;

import lombok.Data;

@Data
public class PaginationRequest {
    private int offset;
    private int size;

    public PaginationRequest(int offset, int size) {
        this.offset = offset;
        this.size = size;
    }
}

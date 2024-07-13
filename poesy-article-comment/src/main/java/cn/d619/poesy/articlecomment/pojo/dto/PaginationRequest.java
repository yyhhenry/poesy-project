package cn.d619.poesy.articlecomment.pojo.dto;

import lombok.Data;

@Data
public class PaginationRequest {
    private int limit;
    private int start;
}

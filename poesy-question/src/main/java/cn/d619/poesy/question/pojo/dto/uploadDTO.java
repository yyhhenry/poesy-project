package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

@Data
public class uploadDTO {
    String id;

    public String getId() {
        return id;
    }

    public uploadDTO() {
    }

    public uploadDTO(String id) {
        this.id = id;
    }

}

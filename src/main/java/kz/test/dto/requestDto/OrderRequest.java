package kz.test.dto.requestDto;

import lombok.Data;

@Data
public class OrderRequest {
    private String title;

    private Integer quantity;

    private Float amount;
}

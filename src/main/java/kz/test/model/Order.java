package kz.test.model;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String title;
    private Integer quantity;
    private Float amount;
}

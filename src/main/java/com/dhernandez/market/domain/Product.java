package com.dhernandez.market.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Product {

    private Long productId;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private Integer stock;
    private Boolean active;
    private Category category;
}

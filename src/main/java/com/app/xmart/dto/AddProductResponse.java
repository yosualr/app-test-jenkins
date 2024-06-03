package com.app.xmart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductResponse {
    private Integer rfid;
    private String productName;
    private Integer productPrice;
}

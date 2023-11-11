package com.Mohamed.springService;

public class product {
    private String productName;
    private Long productId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public product(String productName, Long productId) {
        this.productName = productName;
        this.productId = productId;
    }
}

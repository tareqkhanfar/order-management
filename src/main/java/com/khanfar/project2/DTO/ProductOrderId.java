package com.khanfar.project2.DTO;

import java.io.Serializable;
import java.util.Objects;

public class ProductOrderId implements Serializable {

    private Integer productId;
    private Integer orderId;

    public ProductOrderId() {
    }

    public ProductOrderId(Integer productId, Integer orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    // Implement equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderId that = (ProductOrderId) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}

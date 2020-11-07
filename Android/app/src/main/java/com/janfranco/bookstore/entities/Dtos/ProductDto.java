package com.janfranco.bookstore.entities.Dtos;

public class ProductForAddDto {

    private final String productId;
    private final String cartId;

    public ProductForAddDto(String productId, String cartId) {
        this.productId = productId;
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public String getCartId() {
        return cartId;
    }

}

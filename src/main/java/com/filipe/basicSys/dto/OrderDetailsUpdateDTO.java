package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.model.OrderDetails;
import com.filipe.basicSys.model.Product;
import jakarta.validation.constraints.Min;

public record OrderDetailsUpdateDTO(
        Integer orderId,
        Integer productId,
        @Min(value = 0, message = "A quantidade deve ser maior que zero.")
        Integer quantity
) {
    public OrderDetails mapperToOrderDetails() {
        OrderDetails orderDetails = new OrderDetails();

        if (this.orderId != null) {
            Order order = new Order();
            order.setId(this.orderId);
            orderDetails.setOrder(order);
        }

        if (this.productId != null) {
            Product product = new Product();
            product.setId(this.productId);
            orderDetails.setProduct(product);
        }

        if (this.quantity != null) {
            orderDetails.setQuntity(this.quantity);
        }
        return orderDetails;
    }
}

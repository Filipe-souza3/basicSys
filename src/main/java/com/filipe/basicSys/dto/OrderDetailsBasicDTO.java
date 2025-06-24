package com.filipe.basicSys.dto;

import com.filipe.basicSys.model.Order;
import com.filipe.basicSys.model.OrderDetails;
import com.filipe.basicSys.model.Product;

public record OrderDetailsBasicDTO(
        Integer id,
        Integer orderId,
        Integer productId,
        Integer quantity
) {

    public OrderDetails mapperToOrderDetails(){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(this.id);
        orderDetails.setQuntity(this.quantity);

        Order order = new Order();
        order.setId(this.orderId);

        Product product = new Product();
        product.setId(this.productId);

        return orderDetails;
    }

    public static OrderDetailsBasicDTO mapperOrderDetailsToBasic(OrderDetails orderDetails){
        return new OrderDetailsBasicDTO(
                orderDetails.getId(),
                orderDetails.getOrder().getId(),
                orderDetails.getProduct().getId(),
                orderDetails.getQuntity()
        );
    }
}

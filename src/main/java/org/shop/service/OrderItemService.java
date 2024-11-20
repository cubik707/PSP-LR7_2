package org.shop.service;
import org.shop.dao.OrderItemDAO;
import org.shop.model.OrderItem;

import java.util.List;

public class OrderItemService {

    private final OrderItemDAO orderItemDAO;

    public OrderItemService() {
        this.orderItemDAO = new OrderItemDAO();
    }

    public void saveOrderItem(OrderItem orderItem) {
        validateOrderItem(orderItem);
        orderItemDAO.save(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemDAO.getOrderItemsByOrderId(orderId);
    }

    private void validateOrderItem(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("Order item cannot be null.");
        }

        if (orderItem.getOrder() == null) {
            throw new IllegalArgumentException("Order associated with the item is required.");
        }

        if (orderItem.getProduct() == null) {
            throw new IllegalArgumentException("Product associated with the item is required.");
        }

        if (orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
    }
}

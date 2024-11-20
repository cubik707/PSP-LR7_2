package org.shop.service;

import org.shop.dao.ShopOrderDAO;
import org.shop.model.ShopOrder;

import java.util.Date;
import java.util.List;

public class ShopOrderService {

    private final ShopOrderDAO shopOrderDAO;

    public ShopOrderService() {
        this.shopOrderDAO = new ShopOrderDAO();
    }

    public void saveOrder(ShopOrder order) {
        validateOrder(order);
        shopOrderDAO.save(order);
    }

    public ShopOrder getOrderById(Long id) {
        return shopOrderDAO.getOrderById(id);
    }

    public List<ShopOrder> getAllOrders() {
        return shopOrderDAO.getAllOrders();
    }

    public List<ShopOrder> getOrdersByProductId(Long productId) {
        return shopOrderDAO.getOrdersByProductId(productId);
    }

    private void validateOrder(ShopOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        if (order.getOrderNumber() == null || order.getOrderNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Order number is required.");
        }

        if (order.getArrivalDate() == null || order.getArrivalDate().before(new Date())) {
            throw new IllegalArgumentException("Arrival date cannot be null or in the past.");
        }

        if (order.getUser() == null) {
            throw new IllegalArgumentException("User associated with the order is required.");
        }

        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }
    }
}

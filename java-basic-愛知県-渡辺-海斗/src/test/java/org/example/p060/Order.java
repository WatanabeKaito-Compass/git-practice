package org.example.p060;

import java.util.List;

/**
 * 注文クラス
 * flatMapの演習用
 */
public class Order {
    private String orderId;
    private List<Product> items;
    private String customerName;

    public Order(String orderId, List<Product> items, String customerName) {
        this.orderId = orderId;
        this.items = items;
        this.customerName = customerName;
    }

    // ゲッター
    public String getOrderId() {
        return orderId;
    }

    public List<Product> getItems() {
        return items;
    }

    public String getCustomerName() {
        return customerName;
    }

    // セッター
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return String.format("Order{orderId='%s', items=%s, customerName='%s'}", 
                           orderId, items, customerName);
    }
}

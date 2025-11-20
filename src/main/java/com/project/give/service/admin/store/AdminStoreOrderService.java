package com.project.give.service.admin.store;

import java.util.Map;

public interface AdminStoreOrderService {
    Map<String, Object> getOrderList(int page, int size);
    Object getOrderDetail(int orderId);
    void updateOrderStatus(int orderId, String status);
    void deleteOrder(int orderId);
}

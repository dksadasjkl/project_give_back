package com.project.give.service.admin.store;

import com.project.give.entity.StoreOrder;
import com.project.give.repository.StoreOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminStoreOrderServiceImpl implements AdminStoreOrderService {

    @Autowired
    private StoreOrderMapper storeOrderMapper;

    @Override
    public Map<String, Object> getOrderList(int page, int size) {
        int offset = (page - 1) * size;

        List<StoreOrder> list = storeOrderMapper.selectOrdersByUserPagedForAdmin(offset, size);
        int total = storeOrderMapper.countAllOrders();

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);
        return map;
    }

    @Override
    public StoreOrder getOrderDetail(int orderId) {
        return storeOrderMapper.selectOrderDetailAdmin(orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        storeOrderMapper.updateOrderStatusAdmin(orderId, status);
    }

    @Override
    public void deleteOrder(int orderId) {
        storeOrderMapper.deleteStoreOrderAdmin(orderId);
    }
}

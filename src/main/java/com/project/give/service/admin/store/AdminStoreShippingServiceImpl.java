package com.project.give.service.admin.store.impl;

import com.project.give.entity.StoreShipping;
import com.project.give.repository.StoreShippingMapper;
import com.project.give.service.admin.store.AdminStoreShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminStoreShippingServiceImpl implements AdminStoreShippingService {

    @Autowired
    private StoreShippingMapper shippingMapper;

    @Override
    public Map<String, Object> getShippingList(int page, int size) {
        int offset = (page - 1) * size;

        List<StoreShipping> list = shippingMapper.selectShippingListAdmin(offset, size);
        int total = shippingMapper.countAllShippingAdmin();

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    @Override
    public StoreShipping getShippingDetail(int shippingId) {
        return shippingMapper.selectShippingDetailAdmin(shippingId);
    }

    @Override
    public void updateShippingStatus(int shippingId, String status) {
        shippingMapper.updateShippingStatusAdmin(shippingId, status);
    }

    @Override
    public void updateTrackingNumber(int shippingId, String trackingNumber) {
        shippingMapper.updateTrackingNumberAdmin(shippingId, trackingNumber);
    }

    @Override
    public void deleteShipping(int shippingId) {
        shippingMapper.deleteShippingAdmin(shippingId);
    }
}

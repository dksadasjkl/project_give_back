package com.project.give.service.admin.store;

import com.project.give.entity.StorePayment;
import com.project.give.repository.StorePaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminStorePaymentServiceImpl implements AdminStorePaymentService {

    @Autowired
    private StorePaymentMapper storePaymentMapper;

    @Override
    public Map<String, Object> getPaymentList(int page, int size) {
        int offset = (page - 1) * size;

        List<StorePayment> list = storePaymentMapper.selectPaymentsPagedAdmin(offset, size);
        int total = storePaymentMapper.countAllPaymentsAdmin();

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);
        return map;
    }

    @Override
    public StorePayment getPaymentDetail(int paymentId) {
        return storePaymentMapper.selectPaymentDetailAdmin(paymentId);
    }

    @Override
    public void updatePaymentStatus(int paymentId, String status) {
        storePaymentMapper.updatePaymentStatusAdmin(paymentId, status);
    }

    @Override
    public void deletePayment(int paymentId) {
        storePaymentMapper.deletePaymentAdmin(paymentId);
    }
}

package com.project.give.service.admin.store;

import com.project.give.entity.StorePayment;

import java.util.Map;

public interface AdminStorePaymentService {

    Map<String, Object> getPaymentList(int page, int size);

    StorePayment getPaymentDetail(int paymentId);

    void updatePaymentStatus(int paymentId, String status);

    void deletePayment(int paymentId);
}
package com.project.give.service.admin.store;

import com.project.give.entity.StoreShipping;
import java.util.Map;

public interface AdminStoreShippingService {

    Map<String, Object> getShippingList(int page, int size);

    StoreShipping getShippingDetail(int shippingId);

    void updateShippingStatus(int shippingId, String status);

    void updateTrackingNumber(int shippingId, String trackingNumber);

    void deleteShipping(int shippingId);
}

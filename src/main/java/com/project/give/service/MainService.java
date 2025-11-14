package com.project.give.service;

import com.project.give.repository.DonationProjectMapper;
import com.project.give.repository.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MainService {

    @Autowired
    private DonationProjectMapper donationProjectMapper;

    @Autowired
    private StoreProductMapper storeProductMapper;

    // 🔹 배너용(단일 1개씩)
    public Map<String, Object> getMainTopItems() {

        var topDonation = donationProjectMapper.selectTopDonationProject();
        var topProduct = storeProductMapper.selectTopStoreProduct();

        return Map.of(
                "bannerDonation", topDonation,
                "bannerProduct", topProduct
        );
    }

    // 🔹 메인 추천용 (TOP 3)
    public Map<String, Object> getMainRecommend() {

        var donations = donationProjectMapper.selectTopDonationProjects(3);
        var products = storeProductMapper.selectTopStoreProducts(3);

        return Map.of(
                "donations", donations,
                "products", products
        );
    }
}

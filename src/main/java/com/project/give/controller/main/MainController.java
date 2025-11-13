package com.project.give.controller.main;

import com.project.give.repository.DonationProjectMapper;
import com.project.give.repository.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private DonationProjectMapper donationProjectMapper;

    @Autowired
    private StoreProductMapper storeProductMapper;

    @GetMapping("/top")
    public ResponseEntity<?> getMainTopItems() {

        var topDonation = donationProjectMapper.selectTopDonationProject();
        var topProduct = storeProductMapper.selectTopStoreProduct();

        return ResponseEntity.ok(Map.of(
                "bannerDonation", topDonation,
                "bannerProduct", topProduct
        ));
    }
}
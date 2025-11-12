package com.project.give.service;

import com.project.give.dto.store.response.GetStoreWishlistResponseDto;
import com.project.give.entity.StoreWishlist;
import com.project.give.repository.StoreWishlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreWishlistService {

    @Autowired
    private StoreWishlistMapper storeWishlistMapper;

    // 찜 추가
    public void addWishlist(int userId, int productId) {
        if (storeWishlistMapper.existsWishlist(userId, productId)) {
            throw new RuntimeException("이미 찜한 상품입니다.");
        }
        storeWishlistMapper.insertWishlist(userId, productId);
    }

    // 찜 삭제
    public int removeWishlist(int userId, int productId) {
        int result = storeWishlistMapper.deleteWishlist(userId, productId);
        return result;
    }

    // 내 찜 목록 조회
    public List<GetStoreWishlistResponseDto> getMyWishlist(int userId) {
        return storeWishlistMapper.selectWishlistByUser(userId)
                .stream()
                .map(StoreWishlist::toResponseDto)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getMyWishlistPaged(int userId, int page, int size) {
        int offset = (page - 1) * size;
        List<StoreWishlist> wishlists = storeWishlistMapper.selectWishlistByUserPaged(userId, offset, size);
        int totalCount = storeWishlistMapper.countWishlistByUser(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("wishlists", wishlists.stream()
                .map(StoreWishlist::toResponseDto)
                .collect(Collectors.toList()));
        result.put("totalCount", totalCount);
        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
        return result;
    }
}

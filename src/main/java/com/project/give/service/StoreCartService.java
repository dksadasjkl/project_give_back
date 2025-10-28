package com.project.give.service;

import com.project.give.dto.store.request.PostStoreCartRequestDto;
import com.project.give.dto.store.response.GetStoreCartResponseDto;
import com.project.give.entity.StoreCart;
import com.project.give.repository.StoreCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreCartService {

    @Autowired
    private StoreCartMapper storeCartMapper;

    //  장바구니 추가
    public void addToCart(int userId, PostStoreCartRequestDto dto) {
        if (storeCartMapper.existsCartItem(userId, dto.getProductId())) {
            throw new RuntimeException("이미 장바구니에 담긴 상품입니다.");
        }
        storeCartMapper.insertCart(dto.toEntity(userId));
    }

    //  내 장바구니 조회
    public List<GetStoreCartResponseDto> getMyCart(int userId) {
        return storeCartMapper.selectCartByUser(userId)
                .stream()
                .map(StoreCart::toResponseDto)
                .collect(Collectors.toList());
    }

    //  수량 수정
    public void updateQuantity(int cartId, int quantity) {
        storeCartMapper.updateCartQuantity(cartId, quantity);
    }

    //  상품 삭제
    public void deleteCartItem(int cartId) {
        storeCartMapper.deleteCart(cartId);
    }
}
package com.project.give.dto.store.request;

import com.project.give.entity.StoreProductQna;
import lombok.Data;

@Data
public class PostStoreProductQnaRequestDto {
    private int productId;
    private String questionTitle;
    private String questionContent;
    private boolean secret;

    public StoreProductQna toEntity(int userId) {
        return StoreProductQna.builder()
                .productId(productId)
                .userId(userId)
                .questionTitle(questionTitle)
                .questionContent(questionContent)
                .secret(secret)
                .build();
    }
}
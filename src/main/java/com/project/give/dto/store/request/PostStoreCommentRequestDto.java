package com.project.give.dto.store.request;

import com.project.give.entity.StoreComment;
import lombok.Data;

@Data
public class PostStoreCommentRequestDto {
    private int productId;
    private String commentText;

    public StoreComment toEntity(int userId) {
        return StoreComment.builder()
                .productId(productId)
                .userId(userId)
                .commentText(commentText)
                .build();
    }
}

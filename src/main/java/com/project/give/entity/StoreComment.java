package com.project.give.entity;


import java.time.LocalDateTime;

import com.project.give.dto.store.response.GetStoreCommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreComment {
    private int commentId;
    private int productId;
    private int userId;
    private String commentText;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public GetStoreCommentResponseDto toResponseDto() {
        return GetStoreCommentResponseDto.builder()
                .commentId(commentId)
                .productId(productId)
                .userId(userId)
                .commentText(commentText)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}

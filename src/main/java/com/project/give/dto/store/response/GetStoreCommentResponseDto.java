package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreCommentResponseDto {
    private int commentId;
    private int productId;
    private int userId;
    private String commentText;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

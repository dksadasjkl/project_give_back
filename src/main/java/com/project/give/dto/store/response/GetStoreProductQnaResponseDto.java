package com.project.give.dto.store.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStoreProductQnaResponseDto {
    private int qnaId;
    private int productId;
    private int userId;
    private String questionTitle;
    private String questionContent;
    private boolean secret;
    private String answerContent;
    private LocalDateTime answerDate;
    private LocalDateTime createDate;
}

package com.project.give.entity;

import com.project.give.dto.store.response.GetStoreProductQnaResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductQna {
    private int qnaId;
    private int productId;
    private int userId;
    private String questionTitle;
    private String questionContent;
    private boolean secret;
    private String answerContent;
    private LocalDateTime answerDate;
    private LocalDateTime createDate;

    private String username;
    private String nickname;

    private String productName;

    public GetStoreProductQnaResponseDto toResponseDto() {
        return GetStoreProductQnaResponseDto.builder()
                .qnaId(qnaId)
                .productId(productId)
                .userId(userId)
                .questionTitle(questionTitle)
                .questionContent(questionContent)
                .secret(secret)
                .answerContent(answerContent)
                .answerDate(answerDate)
                .createDate(createDate)
                .username(username)
                .nickname(nickname)
                .productName(productName)
                .build();
    }
}
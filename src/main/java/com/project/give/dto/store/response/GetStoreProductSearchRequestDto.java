package com.project.give.dto.store.response;

import lombok.Data;

@Data
public class GetStoreProductSearchRequestDto {
    private int startIndex;
    private int count;
    private int categoryId;
    private int searchTypeId;
}
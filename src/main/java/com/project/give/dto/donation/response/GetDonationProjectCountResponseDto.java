package com.project.give.dto.donation.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetDonationProjectCountResponseDto {
    private int totalCount;  // 전체 프로젝트 개수
    private int totalLoadCount; // 더보기 클릭 시 전체 페이지 수
}

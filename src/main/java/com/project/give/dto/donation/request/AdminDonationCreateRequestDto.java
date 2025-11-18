package com.project.give.dto.donation.request;

import com.project.give.entity.DonationProject;
import com.project.give.entity.DonationProjectDetail;
import com.project.give.entity.FundingProjectReward;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AdminDonationCreateRequestDto {

    private String donationProjectTitle;
    private String donationProjectOrganization;
    private String donationProjectOrganizationImageUrl;
    private String donationProjectImageUrl;
    private int donationCategoryId;
    private int donationProjectTargetAmount;
    private String donationProjectStartDate;
    private String donationProjectEndDate;
    private String donationProjectType;

    private List<Detail> details;
    private List<Reward> rewards;

    @Data
    public static class Detail {
        private String donationProjectDetailSubtitle;
        private String donationProjectDetailContent;
        private String donationProjectDetailImageUrl;
        private int donationProjectDetailOrderNo;

        public DonationProjectDetail toEntity(int projectId) {
            return DonationProjectDetail.builder()
                    .donationProjectId(projectId)
                    .donationProjectDetailSubtitle(donationProjectDetailSubtitle)
                    .donationProjectDetailContent(donationProjectDetailContent)
                    .donationProjectDetailImageUrl(donationProjectDetailImageUrl)
                    .donationProjectDetailOrderNo(donationProjectDetailOrderNo)
                    .build();
        }
    }

    @Data
    public static class Reward {
        private String fundingProjectRewardTitle;
        private int fundingProjectRewardPrice;
        private int fundingProjectRewardQuantity;

        public FundingProjectReward toEntity(int projectId) {
            return FundingProjectReward.builder()
                    .donationProjectId(projectId)
                    .fundingProjectRewardTitle(fundingProjectRewardTitle)
                    .fundingProjectRewardPrice(fundingProjectRewardPrice)
                    .fundingProjectRewardQuantity(fundingProjectRewardQuantity)
                    .fundingProjectRewardRemaining(fundingProjectRewardQuantity)
                    .build();
        }
    }

    public DonationProject toProjectEntity() {
        return DonationProject.builder()
                .donationProjectTitle(donationProjectTitle)
                .donationProjectOrganization(donationProjectOrganization)
                .donationProjectOrganizationImageUrl(donationProjectOrganizationImageUrl)
                .donationProjectImageUrl(donationProjectImageUrl)
                .donationCategoryId(donationCategoryId)
                .donationProjectTargetAmount(donationProjectTargetAmount)
                .donationProjectStartDate(LocalDate.parse(donationProjectStartDate))
                .donationProjectEndDate(LocalDate.parse(donationProjectEndDate))
                .donationProjectType(donationProjectType)
                .build();
    }
}
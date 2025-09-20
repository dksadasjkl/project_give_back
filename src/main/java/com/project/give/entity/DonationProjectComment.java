package com.project.give.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationProjectComment {
    private int donationProjectCommentId;
    private int donationProjectId;
    private int userId;
    private String donationProjectCommentText;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

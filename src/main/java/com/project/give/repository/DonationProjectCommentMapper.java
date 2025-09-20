package com.project.give.repository;

import com.project.give.entity.DonationProjectComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonationProjectCommentMapper {
    public int insertDonationProjectComment(DonationProjectComment donationProjectComment);
}

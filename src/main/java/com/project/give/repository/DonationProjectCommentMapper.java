package com.project.give.repository;

import com.project.give.entity.DonationProjectComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationProjectCommentMapper {
    public int insertDonationProjectComment(DonationProjectComment donationProjectComment);
    public List<DonationProjectComment> selectCommentsByProjectId(@Param("donationProjectId") int donationProjectId);
    public int updateDonationProjectComment(DonationProjectComment donationProjectComment);
    public int deleteDonationProjectComment(int donationProjectCommentId);
}

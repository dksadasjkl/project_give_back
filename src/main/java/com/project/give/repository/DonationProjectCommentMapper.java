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
    List<DonationProjectComment> selectCommentsWithPaging(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("donationProjectId") int donationProjectId
    );
    int selectCommentCount(@Param("donationProjectId") int donationProjectId);
    List<DonationProjectComment> selectCommentsByUserId(@Param("userId") int userId);

    // ✅ 추가: 마이페이지 댓글 페이징용 쿼리
    List<DonationProjectComment> findMyDonationCommentsPaged(
            @Param("userId") int userId,
            @Param("offset") int offset,
            @Param("size") int size
    );

    // ✅ 추가: 마이페이지 댓글 전체 개수
    int countMyDonationComments(@Param("userId") int userId);

}

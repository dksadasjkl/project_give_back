package com.project.give.service;

import com.project.give.dto.donation.request.GetDonationCommentSearchRequestDto;
import com.project.give.dto.donation.request.PostDonationProjectCommentRequestDto;
import com.project.give.dto.donation.request.PutDonationProjectCommentRequestDto;
import com.project.give.dto.donation.response.GetDonationProjectCommentsResponseDto;
import com.project.give.dto.donation.response.GetDonationProjectCountResponseDto;
import com.project.give.entity.DonationProjectComment;
import com.project.give.entity.PrincipalUser;
import com.project.give.exception.DataNotFoundException;
import com.project.give.exception.DataSaveException;
import com.project.give.repository.DonationProjectCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationProjectCommentService {

    @Autowired
    private DonationProjectCommentMapper donationProjectCommentMapper;

    // 댓글 작성
    public void createDonationProjectComment(PostDonationProjectCommentRequestDto postDonationProjectCommentRequestDto) {
        DonationProjectComment donationProjectComment = postDonationProjectCommentRequestDto.toEntity();
        int affectedRows = donationProjectCommentMapper.insertDonationProjectComment(donationProjectComment);
        if (affectedRows != 1) {
            throw new DataSaveException("댓글 작성 실패");
        }
    }

    public List<GetDonationProjectCommentsResponseDto> getCommentsByProjectId(int donationProjectId) {
        List<DonationProjectComment> donationProjectComments = donationProjectCommentMapper.selectCommentsByProjectId(donationProjectId);
        if (donationProjectComments == null || donationProjectComments.isEmpty()) {
            throw new DataNotFoundException("댓글이 없습니다.");
        }

        return donationProjectComments.stream()
                .map(DonationProjectComment::toGetDonationProjectCommentsResponseDto)
                .collect(Collectors.toList());
    }

    // 댓글 수정
    public void updateDonationProjectComment(int donationProjectCommentId , PutDonationProjectCommentRequestDto putDonationProjectCommentRequestDto) {
        DonationProjectComment donationProjectComment = putDonationProjectCommentRequestDto.toEntity(donationProjectCommentId);
        int affectedRows = donationProjectCommentMapper.updateDonationProjectComment(donationProjectComment);
        if (affectedRows != 1) {
            throw new DataSaveException("댓글 수정 실패");
        }
    }
    public void deleteDonationProjectComment(int donationProjectCommentId) {
        donationProjectCommentMapper.deleteDonationProjectComment(donationProjectCommentId);
    }


    public List<GetDonationProjectCommentsResponseDto> loadMoreComments(GetDonationCommentSearchRequestDto dto) {
        List<DonationProjectComment> comments = donationProjectCommentMapper.selectCommentsWithPaging(
                dto.getStartIndex(),
                dto.getCount(),
                dto.getDonationProjectId()
        );

        return comments.stream()
                .map(DonationProjectComment::toGetDonationProjectCommentsResponseDto)
                .collect(Collectors.toList());
    }

    public GetDonationProjectCountResponseDto totalLoadCommentCount(GetDonationCommentSearchRequestDto dto) {
        int totalCount = donationProjectCommentMapper.selectCommentCount(dto.getDonationProjectId());
        int totalLoadCount = (int) Math.ceil(((double) totalCount) / dto.getCount());

        return GetDonationProjectCountResponseDto.builder()
                .totalCount(totalCount)
                .totalLoadCount(totalLoadCount)
                .build();
    }

    public List<GetDonationProjectCommentsResponseDto> getMyDonationComments(PrincipalUser principalUser) {
        List<DonationProjectComment> comments = donationProjectCommentMapper.selectCommentsByUserId(principalUser.getUserId());

        return comments.stream()
                .map(DonationProjectComment::toGetDonationProjectCommentsResponseDto)
                .collect(Collectors.toList());
    }
}

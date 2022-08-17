package com.woowacourse.smody.domain;

import com.woowacourse.smody.exception.BusinessException;
import com.woowacourse.smody.exception.ExceptionData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @JoinColumn(name = "cycle_detail_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CycleDetail cycleDetail;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false)
    private String content;

    public Comment(CycleDetail cycleDetail, Member member, String content) {
        validateContent(content);
        this.cycleDetail = cycleDetail;
        this.member = member;
        this.content = content;
    }

    private void validateContent(String content) {
        if (content.isBlank() || content.length() > 255) {
            throw new BusinessException(ExceptionData.INVALID_COMMENT_CONTENT);
        }
    }

    public boolean isCommentByMemberId(Long memberId) {
        return this.member.getId().equals(memberId);
    }

    public void updateContent(String content) {
        validateContent(content);
        this.content = content;
    }
}
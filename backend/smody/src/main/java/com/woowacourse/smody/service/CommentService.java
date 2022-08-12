package com.woowacourse.smody.service;

import com.woowacourse.smody.domain.Member;
import com.woowacourse.smody.dto.CommentRequest;
import com.woowacourse.smody.dto.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;

    @Transactional
    public Long create(TokenPayload tokenPayload, long cycleDetailId, CommentRequest commentRequest) {
        Member member = memberService.search(tokenPayload);
        return 1L;
    }
}

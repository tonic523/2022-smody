package com.woowacourse.smody.cycle.dto;

import com.woowacourse.smody.cycle.domain.CycleDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CycleDetailResponse {

    private LocalDateTime progressTime;
    private String progressImage;
    private String description;

    public CycleDetailResponse(CycleDetail cycleDetail) {
        this.progressImage = cycleDetail.getProgressImage();
        this.progressTime = cycleDetail.getProgressTime();
        this.description = cycleDetail.getDescription();
    }
}

package com.woowacourse.smody.cycle.repository;

import static com.woowacourse.smody.support.ResourceFixture.미라클_모닝_ID;
import static com.woowacourse.smody.support.ResourceFixture.스모디_방문하기_ID;
import static com.woowacourse.smody.support.ResourceFixture.조조그린_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.woowacourse.smody.cycle.domain.Cycle;
import com.woowacourse.smody.cycle.domain.CycleDetail;
import com.woowacourse.smody.image.domain.Image;
import com.woowacourse.smody.support.ResourceFixture;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;

@DataJpaTest(showSql = false)
@Import(ResourceFixture.class)
class CycleRepositoryTest {

    @Autowired
    private CycleRepository cycleRepository;

    @Autowired
    private ResourceFixture fixture;

    @Autowired
    private EntityManager em;

    private final Image progressImage = new Image(
            new MockMultipartFile("progressImage", "image".getBytes()), image -> "fakeUrl"
    );

    @DisplayName("startTime 이 기준시간 이후인 사이클을 조회한다.")
    @Test
    void findAllByStartTimeIsAfter() {
        // given
        LocalDateTime today = LocalDateTime.of(2022, 1, 4, 0, 0);
        Cycle cycle1 = fixture.사이클_생성_FIRST(조조그린_ID, 스모디_방문하기_ID, today.minusDays(1L));
        fixture.사이클_생성_SUCCESS(조조그린_ID, 미라클_모닝_ID, today.minusDays(3L));

        // when
        List<Cycle> cycles = cycleRepository.findAllByStartTimeIsAfter(today.minusDays(3L));

        // then
        assertAll(
                () -> assertThat(cycles.size()).isEqualTo(1),
                () -> assertThat(cycles.get(0).getId()).isEqualTo(cycle1.getId())
        );
    }

    @DisplayName("인증이 일어났을 경우 CycleDetail 이 DB 에 저장된다.")
    @Test
    void increaseProgress() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Cycle cycle = fixture.사이클_생성_NOTHING(조조그린_ID, 미라클_모닝_ID, now);

        // when
        cycle.increaseProgress(now.plusSeconds(60L), progressImage, "인증 완료");
        em.flush();
        em.clear();

        // then
        List<CycleDetail> cycleDetails = cycleRepository.findById(cycle.getId()).get().getCycleDetails();
        assertAll(
                () -> assertThat(cycleDetails.size()).isEqualTo(1),
                () -> assertThat(cycleDetails.get(0).getProgressImage()).isEqualTo("fakeUrl"),
                () -> assertThat(cycleDetails.get(0).getDescription()).isEqualTo("인증 완료")
        );
    }
}
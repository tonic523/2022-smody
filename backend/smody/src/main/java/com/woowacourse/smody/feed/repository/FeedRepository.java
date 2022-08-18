package com.woowacourse.smody.feed.repository;

import com.woowacourse.smody.cycle.domain.CycleDetail;
import com.woowacourse.smody.feed.domain.Feed;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedRepository extends JpaRepository<CycleDetail, Long> {

    @Query("select new com.woowacourse.smody.feed.domain.Feed("
            + "cd.id, cd.progressImage, cd.description, cd.progressTime, "
            + "m.id, m.picture, m.nickname, "
            + "ch.id, ch.name, cd.comments.size) "
            + "from CycleDetail cd "
            + "join cd.cycle c "
            + "join c.challenge ch "
            + "join c.member m "
            + "where (:time is null or cd.progressTime <= :time) and "
            + "(:cycleDetailId is null or cd.id <> :cycleDetailId) ")
    List<Feed> findAll(@Param("cycleDetailId") Long cycleDetailId, @Param("time") LocalDateTime time,
                       Pageable pageable);

    @Query("select new com.woowacourse.smody.feed.domain.Feed("
            + "cd.id, cd.progressImage, cd.description, cd.progressTime, "
            + "m.id, m.picture, m.nickname, "
            + "ch.id, ch.name, cd.comments.size) "
            + "from CycleDetail cd "
            + "join cd.cycle c "
            + "join c.challenge ch "
            + "join c.member m "
            + "where cd.id = :cycleDetailId ")
    Optional<Feed> findByCycleDetailId(@Param("cycleDetailId") Long cycleDetailId);
}
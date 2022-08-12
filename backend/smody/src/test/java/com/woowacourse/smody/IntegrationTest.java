package com.woowacourse.smody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.woowacourse.smody.image.ImageStrategy;
import com.woowacourse.smody.service.WebPushService;

@SpringBootTest
@Transactional
public class IntegrationTest {

	@MockBean
	protected ImageStrategy imageStrategy;

	@MockBean
	protected WebPushService webPushService;

	@Autowired
	protected ResourceFixture fixture;
}
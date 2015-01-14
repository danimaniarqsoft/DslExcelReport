package com.danimaniarqsoft.report.chart.config;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(value = Parameterized.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public abstract class AbstractParameterizedTest {
	protected static final Logger LOG = LoggerFactory
			.getLogger(AbstractParameterizedTest.class);

	@Configuration
	static class ContextConfiguration {

	}

	private TestContextManager testContextManager;

	/**
	 * this is where the magic happens, we actually do "by hand" what the spring
	 * runner would do for us, read the JavaDoc for the class bellow to know
	 * exactly what it does, the method names are quite accurate though
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUpContext() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);
	}
}

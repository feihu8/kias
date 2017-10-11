package kias;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner  
@RunWith(SpringJUnit4ClassRunner.class) 
public abstract class TestBaseCase  extends AbstractJUnit4SpringContextTests{
	Logger logger = LogManager.getLogger(getClass());
}

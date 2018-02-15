package kriss0101.cvdb;

import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.googlecode.junittoolbox.ParallelSuite;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses("**/*IT.class")
public class IntegrationTestsSuite {

}

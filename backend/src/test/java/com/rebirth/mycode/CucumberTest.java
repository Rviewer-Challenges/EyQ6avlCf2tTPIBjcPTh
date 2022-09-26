package com.rebirth.mycode;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

@Suite
@SelectClasspathResource("com/rebirth/mycode/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.rebirth.mycode")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class CucumberTest {
}

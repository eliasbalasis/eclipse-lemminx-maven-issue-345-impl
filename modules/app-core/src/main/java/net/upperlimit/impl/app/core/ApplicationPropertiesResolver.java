package net.upperlimit.impl.app.core;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public final class ApplicationPropertiesResolver {

	private static final ApplicationPropertiesResolver singleton = new ApplicationPropertiesResolver();

	private Properties properties = null;

	public static ApplicationPropertiesResolver getDefault() {
		return singleton;
	}

	private Properties loadProperties() throws IOException {
		if (properties == null) {
			final String propertiesContent = IOUtils.resourceToString("myapplication.properties",
					StandardCharsets.ISO_8859_1, ApplicationPropertiesResolver.class.getClassLoader());
			final Reader propertiesReader = new StringReader(propertiesContent);
			final Properties properties = new Properties();
			properties.load(propertiesReader);
			this.properties = properties;
		}
		return this.properties;
	}

	public String resolve(final String property) throws IOException {
		return loadProperties().getProperty(property);
	}

	private ApplicationPropertiesResolver() {
	}
}

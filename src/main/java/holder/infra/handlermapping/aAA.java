package holder.infra.handlermapping;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import org.springframework.web.servlet.mvc.support.AbstractControllerUrlHandlerMapping;

//@Component
public class aAA extends AbstractUrlHandlerMapping {

	private final Map<String, Object> urlMap = new HashMap<String, Object>();

	public void setMappings(Properties mappings) {
		CollectionUtils.mergePropertiesIntoMap(mappings, this.urlMap);
	}

	public void setUrlMap(Map<String, ?> urlMap) {
		this.urlMap.putAll(urlMap);
	}

	public Map<String, ?> getUrlMap() {
		return this.urlMap;
	}

	@Override
	public void initApplicationContext() throws BeansException {
		super.initApplicationContext();
		registerHandlers(this.urlMap);
	}

	protected void registerHandlers(Map<String, Object> urlMap) throws BeansException {
		if (urlMap.isEmpty()) {
			logger.warn("Neither 'urlMap' nor 'mappings' set on SimpleUrlHandlerMapping");
		}
		else {
			for (Map.Entry<String, Object> entry : urlMap.entrySet()) {
				String url = entry.getKey();
				Object handler = entry.getValue();
				// Prepend with slash if not already present.
				if (!url.startsWith("/")) {
					url = "/" + url;
				}
				// Remove whitespace from handler bean name.
				if (handler instanceof String) {
					handler = ((String) handler).trim();
				}
				registerHandler(url, handler);
			}
		}
	}

}

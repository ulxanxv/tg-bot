package ru.bsc.telegram.bot.main.registrar.anno;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.Assert;
import ru.bsc.telegram.bot.main.config.TelegramBotProperties;
import ru.bsc.telegram.bot.main.registrar.type.UpdateRegistrarType;

import java.util.Map;

public class UpdateRegistrarComponentCondition implements ConfigurationCondition {

	private static final String VALUE_ATTR_NAME = "value";

	@Override
	public ConfigurationPhase getConfigurationPhase() {
		return ConfigurationPhase.PARSE_CONFIGURATION;
	}

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		final TelegramBotProperties properties = Binder.get(context.getEnvironment())
				.bind(TelegramBotProperties.PROPERTIES_PREFIX, TelegramBotProperties.class)
				.get();

		final Map<String, Object> attrs = metadata.getAnnotationAttributes(UpdateRegistrarComponent.class.getName());
		Assert.notNull(attrs, "Annotation attributes must not be null");

		final UpdateRegistrarType registrarType = (UpdateRegistrarType) attrs.get(VALUE_ATTR_NAME);
		return registrarType.equals(properties.getRegistrarType());
	}
}

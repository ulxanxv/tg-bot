package ru.bsc.telegram.bot.main.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.bsc.telegram.bot.main.registrar.type.UpdateRegistrarType;

import static ru.bsc.telegram.bot.main.config.TelegramBotProperties.PROPERTIES_PREFIX;

@Getter
@Setter
@Configuration
@ConfigurationProperties(PROPERTIES_PREFIX)
public class TelegramBotProperties {

	public static final String PROPERTIES_PREFIX = "telegram";

	private String name;
	private String token;
	private UpdateRegistrarType registrarType;
}

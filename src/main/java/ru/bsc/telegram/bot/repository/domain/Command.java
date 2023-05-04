package ru.bsc.telegram.bot.repository.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "command")
public class Command {

	public static final String START_CODE = "START";
	public static final String DEFAULT_CODE = "DEFAULT";

	@Id
	private String code;

	@Column(nullable = false)
	private String path;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Column(nullable = false, length = 16384)
	private String description;
}

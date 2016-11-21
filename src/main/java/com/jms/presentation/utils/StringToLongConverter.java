package com.jms.presentation.utils;

import org.springframework.core.convert.converter.Converter;

public class StringToLongConverter implements Converter<String, Long> {

	public Long convert(String source) {

		return Long.parseLong(source);
	}
	
}

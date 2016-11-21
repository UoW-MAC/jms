package com.jms.presentation.utils;

import java.beans.PropertyEditorSupport;


public class CustomLongEditor extends PropertyEditorSupport {
	 
	public String getAsText() { 
		
		Long l = (Long) getValue();
		return l.toString();
	} 

	public void setAsText(String str) { 
			
		setValue(Long.parseLong(str)); 
	} 
 
}

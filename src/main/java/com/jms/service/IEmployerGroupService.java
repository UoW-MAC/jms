package com.jms.service;

import java.util.List;

import com.jms.domain.entities.EmployerGroup;

/**
 * 
 * @author williamzhang
 *
 */
public interface IEmployerGroupService {
	
    public List<EmployerGroup> employerGroupObtain() throws Exception;
}

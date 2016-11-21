package com.jms.service;

import java.util.List;

import com.jms.domain.entities.PositionGroup;

/**
 * 
 * @author williamzhang
 *
 */
public interface IPositionGroupService {
	
    public List<PositionGroup> positionGroupObtain() throws Exception;
}

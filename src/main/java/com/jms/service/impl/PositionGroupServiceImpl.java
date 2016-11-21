package com.jms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.entities.PositionGroup;
import com.jms.domain.repositories.PositionGroupRepository;
import com.jms.service.IPositionGroupService;



@Service
@Transactional(rollbackFor = Exception.class)
public class PositionGroupServiceImpl implements IPositionGroupService {

    @Autowired
    private PositionGroupRepository positionGroupRepository;
    
    public List<PositionGroup> positionGroupObtain() throws Exception {
        try {
        	List<PositionGroup> positionGroup = positionGroupRepository.selectPositionGroupInfo();
            return positionGroup;
        } catch (Exception e) {
            throw e;
        }
    }

   
}

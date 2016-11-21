package com.jms.service;

import java.util.List;

import com.jms.domain.entities.Application;
import com.jms.domain.entities.Position;
import com.jms.domain.entities.Candidate;

/**
 * 
 * @author williamzhang
 *
 */
public interface IPositionService {

	public boolean positionSubmit(Position position) throws Exception;

	public boolean positionDelete(Long positionId) throws Exception;
	
	public boolean applicationDelete(Long applicationId) throws Exception;

	public List<Position> positionObtain() throws Exception;

	public boolean positionUpdate(Position position) throws Exception;

	public List<Application> getPositionStatusList() throws Exception;

	public void positionApply(String positionId) throws Exception;

	public Position getPositionInfo(int positionId) throws Exception;

	public List<Application> candidateObtain(int positionId) throws Exception;

	public Candidate getCandidateDetail(int candidateId) throws Exception;

	public void acceptCandidate(int applicationId) throws Exception;

	public void rejectCandidate(int applicationId) throws Exception;
	
	public List<Position> exportPositionInfo(String keyword) throws Exception;
	
}

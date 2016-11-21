package com.jms.service;

import java.util.List;

import com.jms.domain.entities.Application;
import com.jms.domain.entities.Candidate;

/**
 * 
 * @author williamzhang
 *
 */
public interface ICandidateService {

	void candidateInfoSubmit(Candidate candidate) throws Exception;

	void candidateInfoUpdate(Candidate candidate) throws Exception;

	Candidate getCandidateInfo(int candidateId) throws Exception;

	List<Candidate> getAdminCandidateInfo(String keyword) throws Exception;

	List<Application> exportApplicationInfo(String keyword) throws Exception;

	boolean candidateDelete(Long candidateId) throws Exception;

	boolean isCandidateNumberExisted(Long candidateNo) throws Exception;
}

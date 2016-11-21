package com.jms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.entities.Application;
import com.jms.domain.entities.Education;
import com.jms.domain.entities.Candidate;
import com.jms.domain.entities.User;
import com.jms.domain.entities.Work;
import com.jms.domain.repositories.ApplicationRepository;
import com.jms.domain.repositories.EducationRepository;
import com.jms.domain.repositories.CandidateRepository;
import com.jms.domain.repositories.WorkRepository;
import com.jms.service.ICandidateService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CandidateServiceImpl implements ICandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;

	public void candidateInfoSubmit(Candidate candidate) throws Exception {
		
		// step 1:
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		candidate.setCreateTime(createTime);
		candidate.setUpdateTime(createTime);
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userDetails.getUsername();
		User user = new User();
		user.setUserName(userName);
		candidate.setUser(user);
		
		candidateRepository.addCandidateInfo(candidate);

		// step 2:
		for (Work work : candidate.getWorkList()) {
			work.setCandidate(candidate);
			work.setCreateTime(createTime);
			work.setUpdateTime(createTime);
		}
		
		workRepository.addWorkInfo(candidate.getWorkList());

		// step 3:
		for (Education education : candidate.getEducationList()) {
			education.setCandidate(candidate);
			education.setCreateTime(createTime);
			education.setUpdateTime(createTime);
		}
		educationRepository.addEducationInfo(candidate.getEducationList());

	}

	public void candidateInfoUpdate(Candidate candidate) throws Exception {
		
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		List<Work> updateWorkList = new ArrayList<Work>();
		List<Work> insertWorkList = new ArrayList<Work>();
		List<Education> UpdateEducationList = new ArrayList<Education>();
		List<Education> insertEducationList = new ArrayList<Education>();
		
		candidate.setUpdateTime(updateTime);
		candidateRepository.updateCandidateInfo(candidate);
		
		for (Education education : candidate.getEducationList()) {
			education.setUpdateTime(updateTime);
			
			if (education.getEducationId() != null) {
				UpdateEducationList.add(education);
			}else {
				Candidate newcandidate = new Candidate();
				newcandidate.setCandidateId(candidate.getCandidateId());
				education.setCandidate(newcandidate);
				education.setCreateTime(updateTime);
				insertEducationList.add(education);
			}
		}
		
		for (Work work : candidate.getWorkList()) {
			work.setUpdateTime(updateTime);
			
			if (work.getWorkId() != null) {
				updateWorkList.add(work);
			}else {
				Candidate newcandidate = new Candidate();
				newcandidate.setCandidateId(candidate.getCandidateId());
				work.setCandidate(newcandidate);
				work.setCreateTime(updateTime);
				insertWorkList.add(work);
			}
		}
        
		if (insertWorkList != null && insertEducationList.size() > 0) {
			workRepository.addWorkInfo(insertWorkList);
		}
		if (insertEducationList != null && insertEducationList.size() > 0) {
			educationRepository.addEducationInfo(insertEducationList);
		}
		
		workRepository.updateWorkInfo(updateWorkList);
		educationRepository.updateEducationInfo(UpdateEducationList);
	}


	@Override
	public List<Candidate> getAdminCandidateInfo(String keyword) throws Exception {
		try {
			Map<String,String> map=new HashMap<String,String>();
			map.put("keyword", "%"+keyword+"%");
			List<Candidate> candidate = candidateRepository.adminCandidateInfo(map);
			return candidate;
		} catch (Exception e) {
			throw e;
		}
	}
	public boolean candidateDelete(Long candidateId) throws Exception {
		try {
			candidateRepository.deleteCandidateInfo(candidateId);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	public List<Application> exportApplicationInfo(String keyword) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("keyword", "%"+keyword+"%");
		try {
			return applicationRepository.exportApplicationInfo(map);
		} catch (Exception e) {
			 return null;
		}
	}


	@Override
	public boolean isCandidateNumberExisted(Long candidateNo) throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userDetails.getUsername();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userName",userName);
		map.put("candidateNo",candidateNo);
		Candidate candidate = candidateRepository.getCandidateByCandidateNo(map);
		Boolean isUserNameExisted = false;
		
		if (candidate != null) {
			isUserNameExisted = true;
		}
		
		return isUserNameExisted;
	}


	@Override
	public Candidate getCandidateInfo(int candidateId) throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userDetails.getUsername();
		Map<String,Object> map = new HashMap<String, Object>();
		Candidate candidate;
		try {
			map.put("userName", userName);
			map.put("candidateId", candidateId);
			candidate = candidateRepository.getCandidateInfo(map);
			return candidate;
		} catch (Exception e) {
			return null;
		}
	}
	
}

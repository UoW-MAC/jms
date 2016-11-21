package com.jms.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.entities.Employer;
import com.jms.domain.entities.User;
import com.jms.domain.repositories.EmployerRepository;
import com.jms.service.IEmployerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployerServiceImpl implements IEmployerService {

	@Autowired
	private EmployerRepository employerRepository;

	public boolean employerInfoSubmit(Employer employer) throws Exception {
		try {
		// step 1:
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		employer.setCreateTime(createTime);
		employer.setUpdateTime(createTime);
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userDetails.getUsername();
		User user = new User();
		user.setUserName(userName);
		employer.setUser(user);
		employerRepository.addEmployerInfo(employer);
		// step 2:
		/*for (Work work : student.getWorkList()) {
			work.setStudent(student);
			work.setCreateTime(createTime);
			work.setUpdateTime(createTime);
		}*/
		
		// step 3:
		  return true;
        } catch (Exception e) {
            throw e;
        }
	}


	public boolean employerInfoUpdate(Employer employer) throws Exception {
		try {
			Timestamp updateTime = new Timestamp(System.currentTimeMillis());
			employer.setUpdateTime(updateTime);
			employerRepository.updateEmployerInfo(employer);
		return true;
		} catch (Exception e) {
			throw e;
		}
	}


	
	public Map<String, Employer> employerObtain() throws Exception {
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userName = userDetails.getUsername();
			Map<String, Object> para = new HashMap<String,Object>();
			para.put("userName", userName);
			para.put("employerId", 0);
			Employer employer = employerRepository.showEmployerInfo(para);
			Map<String, Employer> employerMap = new HashMap<String, Employer>();
			employerMap.put("employer", employer);
			return employerMap;
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public List<Employer> getAdminEmployerInfo() throws Exception {
		try {
			List<Employer> employer = employerRepository.getAdminEmployerInfo();
			return employer;
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public Employer getAdminEmployerDetail(int employerId) throws Exception {
		Map<String, Object> para = new HashMap<String,Object>();
		para.put("userName", "admin");
		para.put("employerId", employerId);
		try {
			Employer employer = employerRepository.showEmployerInfo(para);
			return employer;
		}catch (Exception e) {
			throw e;
		}
	}


	@Override
	public boolean employerDelete(Long employerId) throws Exception {
		try {
			employerRepository.deleteEmployerInfo(employerId);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employer> exportAdminEmployerInfo(String keyword) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("keyword", "%"+keyword+"%");
		try {
			List<Employer> employer = employerRepository.exportAdminEmployerInfo(map);
			return employer;
		} catch (Exception e) {
			throw e;
		}
	}
	public boolean isEmployerNameExisted(String userName) throws Exception {
		Employer employer = employerRepository.getEmployerByEmployerName(userName);
		Boolean isEmployerNameExisted = false;
		
		if (employer != null) {
			isEmployerNameExisted = true;
		}
		
		return isEmployerNameExisted;
	}
}

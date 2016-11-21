package com.jms.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jms.domain.entities.Employer;
import com.jms.domain.entities.EmployerGroup;
import com.jms.domain.valuetypes.ResponseStatus;
import com.jms.presentation.model.Response;
import com.jms.service.IEmployerGroupService;
import com.jms.service.IEmployerService;

/**
 * 
 * @author williamzhang
 *
 */
@Controller
public class EmployerController {
	private static final Logger logger = Logger.getLogger(EmployerController.class);
	private static final String EMPLOYER_PAGE = "employer";
	private static final String EMPLOYER_DETAIL_PAGE="employer-detail";
    private static final String ERROR_PAGE = "error";

	@Resource
	private IEmployerService employerService;
	@Resource
	private IEmployerGroupService employerGroupService;

	@RequestMapping(value = "employer", method = RequestMethod.GET)
	public ModelAndView showEmployerPage() {
		// Map<String, Object> models = new HashMap<String, Object>();
		return new ModelAndView(EMPLOYER_PAGE);
	}

	@RequestMapping(value = "getEmployerGroup", method = RequestMethod.GET)
	public @ResponseBody List<EmployerGroup> handleEmployerGroupObtain(HttpServletRequest request) {
		List<EmployerGroup> employerGroupList = null;
		try {
			employerGroupList = employerGroupService.employerGroupObtain();
		} catch (Exception ex) {
			return null;
		}

		return employerGroupList;
	}
	@RequestMapping(value = "showEmployer", method = RequestMethod.GET)
	public @ResponseBody Map<String,Employer> handleEmployerObtain(HttpServletRequest request) {
		Map<String,Employer> employerMap = null;
		try {
			employerMap  = employerService.employerObtain();
		} catch (Exception ex) {
			return null;
		}
		return employerMap;
	}
	@RequestMapping(value = "makeEmployer", method = RequestMethod.POST)
	public @ResponseBody Response makeApplication(@ModelAttribute("employerForm") @Valid Employer employer, BindingResult result) {
		int statusCode;
        String statusDescription;

        if (result.hasErrors()) {
        	statusCode = ResponseStatus.FAILURE.getStatusCode();
    		statusDescription = ResponseStatus.FAILURE.getStatusDescription();
    		return new Response(statusCode, statusDescription);
        }
		try {
			if (employer.getEmployerId()==null) {
				employerService.employerInfoSubmit(employer);
			} else {
				employerService.employerInfoUpdate(employer);
			}
			statusCode = ResponseStatus.SUCCESS.getStatusCode();
    		statusDescription = ResponseStatus.SUCCESS.getStatusDescription();
		} catch (Exception exception) {
			statusCode = ResponseStatus.FAILURE.getStatusCode();
    		statusDescription = ResponseStatus.FAILURE.getStatusDescription();
		}
		return new Response(statusCode, statusDescription);
	}
	@RequestMapping(value = "showAdminEmployer", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> handlePositionObtain(HttpServletRequest request) {
		Map<String,Object> employerMap = null;
		List<Employer> employerList=null;
		try {
			employerList  = employerService.getAdminEmployerInfo();
			employerMap = new HashMap<String,Object>();
			employerMap.put("data", employerList);
			return employerMap;
		} catch (Exception ex) {
			return null;
		}
	
	}
	 @RequestMapping(value = "employerDetail", method = RequestMethod.GET)
	    public ModelAndView showPositionDetailInfo(HttpServletRequest request) {
	    	
	    	String employerId = request.getParameter("employerId");
	    	Employer employer = null;
	        Map<String, Object> models = null;
	        String returnPage = null;
	        try {
	        	employer = employerService.getAdminEmployerDetail(Integer.parseInt(employerId));
	        	models = new HashMap<String, Object>();
	        	models.put("employerDetail", employer);
	    		returnPage = EMPLOYER_DETAIL_PAGE;
				
			} catch (Exception e) {
	    		returnPage = ERROR_PAGE;
			}
	        
	        return new ModelAndView(returnPage, models);
	    }
	 @RequestMapping(value = "deleteEmployer", method = RequestMethod.GET)
		public @ResponseBody Response handleDeleteEmployer(HttpServletRequest request) {
			String employerId = request.getParameter("employerId");
			Response rs = new Response(ResponseStatus.SUCCESS.getStatusCode(),ResponseStatus.SUCCESS.getStatusDescription());
			try {
				employerService.employerDelete(Long.parseLong(employerId));
			} catch (Exception e) {
				return null;
			}
			return rs;
		} 
//	  @RequestMapping(value = "exportEmployerCSV", method = RequestMethod.POST)
//	    public @ResponseBody Response exportEmployerAll(HttpServletRequest request) throws Exception {
//
//	    	Response response = new Response();
//	    	Map<String, Object> models = new HashMap<String, Object>();
//	    	String keyword = request.getParameter("keyword");
//	    	List<Employer> employerList = employerService.exportAdminEmployerInfo(keyword);
//	      
//	        String filePath = CsvExporter.export(employerList);
//
//	        String[] filePathSplit = filePath.split("/");
//	        String fileName = filePathSplit[3];
//
//	        models.put("fileName", fileName);
//	        response.setModels(models);
//	        
//	        return response;
//	    }
	  @RequestMapping(value = "employerNameValidation", method = RequestMethod.POST)
		public @ResponseBody boolean userNameValidation(HttpServletRequest request) {
		  Boolean employerNameValidation = null;
		  if (request.getParameter("address") != null){
			  employerNameValidation = true;
		  }else{
			try {
				 if (employerService.isEmployerNameExisted(request.getParameter("employerName"))) {
					 employerNameValidation = false;
				 }
			} catch (Exception e) {
				// TODO: handle exception
			}
		  }
			return employerNameValidation;
		}
}

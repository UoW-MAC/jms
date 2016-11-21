package com.jms.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jms.domain.entities.Application;
import com.jms.domain.entities.Candidate;
import com.jms.domain.entities.Position;
import com.jms.domain.entities.PositionGroup;
import com.jms.domain.valuetypes.ResponseStatus;
import com.jms.presentation.model.Response;
import com.jms.service.ICandidateService;
import com.jms.service.IPositionGroupService;
import com.jms.service.IPositionService;

/**
 * 
 * @author williamzhang
 *
 */
@Controller
public class PositionController {
	private static final Logger logger = Logger.getLogger(PositionController.class);
	private static final String POSITION_PAGE = "position";
	private static final String USER_CENTER_PAGE = "user-center";
	private static final String CANDIDATE_DETAIL_PAGE = "applicant-detail";
	private static final String APPLICANT_PAGE = "applicant";
	private static final String ERROR_PAGE = "error";
	private static final String ADMIN_POSITION_DETAIL_PAGE = "admin-position-detail";

	@Resource
	private IPositionService positionService;
	@Resource
	private IPositionGroupService positionGroupService;
	@Resource
	private ICandidateService candidateService;

	@RequestMapping(value = "position", method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView(POSITION_PAGE);
	}

	@RequestMapping(value = "getPositionGroup", method = RequestMethod.GET)
	public @ResponseBody List<PositionGroup> handlePositionGroupObtain(HttpServletRequest request) {
		List<PositionGroup> positonGroupList = null;
		try {
			positonGroupList = positionGroupService.positionGroupObtain();
		} catch (Exception ex) {
			return null;
		}

		return positonGroupList;
	}

	@RequestMapping(value = "makePosition", method = RequestMethod.POST)
	public ModelAndView makeApplication(@ModelAttribute("positionForm") Position position) throws Exception {
		positionService.positionSubmit(position);
		Map<String, String> model = new HashMap<String, String>();
		model.put("menu", "adminPosition");
		return new ModelAndView(USER_CENTER_PAGE, model);
	}

	@RequestMapping(value = "deletePosition", method = RequestMethod.GET)
	public @ResponseBody Response handleDeletePosition(HttpServletRequest request) {
		String positionId = request.getParameter("positionId");
		Response rs = new Response(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription());
		try {
			positionService.positionDelete(Long.parseLong(positionId));
		} catch (Exception e) {
			return null;
		}
		return rs;
	}

	@RequestMapping(value = "showPosition", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> handlePositionObtain(HttpServletRequest request) {
		Map<String, Object> positionMap = null;
		try {
			positionMap = new HashMap<String, Object>();
			positionMap.put("data", positionService.positionObtain());
		} catch (Exception ex) {
			return null;
		}
		return positionMap;
	}

	@RequestMapping(value = "updatePosition", method = RequestMethod.POST)
	public @ResponseBody Response updateApplication(@ModelAttribute("updatePositionForm") Position position)
			throws Exception {
		positionService.positionUpdate(position);
		Response rs = new Response(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription());
		return rs;
	}

	@RequestMapping(value = "canditateDetail", method = RequestMethod.GET)
	public ModelAndView showCandidateDetailInfo(HttpServletRequest request) {

		String applicationId = request.getParameter("applicationId");
		String candidateId = request.getParameter("candidateId");
		String positionId = request.getParameter("positionId");
		Candidate candidate = null;
		Map<String, Object> models = null;
		String returnPage = null;

		try {
			candidate = positionService.getCandidateDetail(Integer.parseInt(candidateId));
			models = new HashMap<String, Object>();
			models.put("candidateDetail", candidate);
			models.put("applicationId", applicationId);
			models.put("positionId", positionId);
			returnPage = CANDIDATE_DETAIL_PAGE;

		} catch (Exception e) {
			returnPage = ERROR_PAGE;
		}

		return new ModelAndView(returnPage, models);
	}

	@RequestMapping(value = "getCandidateInfoList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCandidateInfoList(HttpServletRequest request) {

		String positionId = request.getParameter("positionId");
		List<Application> candidate = null;
		Map<String, Object> data = null;
		try {
			candidate = positionService.candidateObtain(Integer.parseInt(positionId));
			data = new HashMap<String, Object>();
			data.put("data", candidate);
		} catch (Exception e) {
			return null;
		}
		return data;
	}

	@RequestMapping(value = "candidate", method = RequestMethod.GET)
	public ModelAndView getCandidatesByPositionId(HttpServletRequest request) {
		String positionId = request.getParameter("positionId");
		Map<String, Object> models = null;
		String returnPage = null;
		try {
			models = new HashMap<String, Object>();
			models.put("positionId", positionId);
			returnPage = APPLICANT_PAGE;
		} catch (Exception e) {
			returnPage = ERROR_PAGE;
		}

		return new ModelAndView(returnPage, models);
	}

	@RequestMapping(value = "candidateAccept", method = RequestMethod.GET)
	public @ResponseBody int candidateAccept(HttpServletRequest request) throws Exception {
		String applicationId = request.getParameter("applicationId");
		int positionId = Integer.parseInt(request.getParameter("positionId"));

		try {
			positionService.acceptCandidate(Integer.parseInt(applicationId));

		} catch (Exception e) {
			throw e;
		}
		return positionId;
	}

	@RequestMapping(value = "candidateReject", method = RequestMethod.GET)
	public @ResponseBody int candidateReject(HttpServletRequest request) throws Exception {
		String applicationId = request.getParameter("applicationId");
		int positionId = Integer.parseInt(request.getParameter("positionId"));

		try {
			positionService.rejectCandidate(Integer.parseInt(applicationId));

		} catch (Exception e) {
			throw e;
		}
		return positionId;
	}

	@RequestMapping(value = "deleteApplication", method = RequestMethod.GET)
	public @ResponseBody Response handleDeleteApplication(HttpServletRequest request) {
		String applicationId = request.getParameter("applicationId");
		Response rs = new Response(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription());
		try {
			positionService.applicationDelete(Long.parseLong(applicationId));
		} catch (Exception e) {
			return null;
		}
		return rs;
	}

//	@RequestMapping(value = "exportPositionCSV", method = RequestMethod.POST)
//	public @ResponseBody Response exportPositionAll(HttpServletRequest request) throws Exception {
//
//		Response response = new Response();
//		Map<String, Object> models = new HashMap<String, Object>();
//		String keyword = request.getParameter("keyword");
//		List<Position> positionList = positionService.exportPositionInfo(keyword);
//
//		String filePath = CsvExporter.export(positionList);
//
//		String[] filePathSplit = filePath.split("/");
//		String fileName = filePathSplit[3];
//
//		models.put("fileName", fileName);
//		response.setModels(models);
//
//		return response;
//	}

	@RequestMapping(value = "adminPositionDetail", method = RequestMethod.GET)
	public ModelAndView showPositionDetailInfo(HttpServletRequest request) {

		String positionId = request.getParameter("positionId");
		Position position = null;
		Map<String, Object> models = null;
		String returnPage = null;

		try {
			position = positionService.getPositionInfo(Integer.parseInt(positionId));

			models = new HashMap<String, Object>();
			models.put("positionDetail", position);

			returnPage = ADMIN_POSITION_DETAIL_PAGE;

		} catch (Exception e) {
			returnPage = ERROR_PAGE;
		}

		return new ModelAndView(returnPage, models);
	}
}

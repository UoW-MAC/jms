package com.jms.presentation.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jms.domain.valuetypes.ResponseStatus;
import com.jms.presentation.model.Response;
import com.jms.service.ICandidateService;
import com.jms.service.IPositionService;

/**
 * @author william zhang
 */
@Controller
public class CandidateController {
	private static final Logger logger = Logger.getLogger(CandidateController.class);
	private static final String CANDIDATE_PAGE = "candidate";
	private static final String POSITION_DETAIL_PAGE = "position-detail";
	private static final String ERROR_PAGE = "error";
	private static final String CANDIDATE_DETAIL_PAGE = "candidate-detail";

	@Resource
	private ICandidateService candidateService;

	@Resource
	private IPositionService positionService;

//	@RequestMapping(value = "candidate", method = RequestMethod.GET)
//	public ModelAndView showCandidatePage() {
//		Map<String, Object> models = new HashMap<String, Object>();
//		return new ModelAndView(CANDIDATE_PAGE, models);
//	}

	@RequestMapping(value = "addOrUpdCandidate", method = RequestMethod.POST)
	public @ResponseBody Response makeApplication(@ModelAttribute("candidateForm") Candidate candidate) {
		int statusCode;
		String statusDescription;

		try {
			if (candidate.getCandidateId() == null || candidate.getCandidateId().equals("")) {
				candidateService.candidateInfoSubmit(candidate);
			} else {
				candidateService.candidateInfoUpdate(candidate);
			}

			statusCode = ResponseStatus.SUCCESS.getStatusCode();
			statusDescription = ResponseStatus.SUCCESS.getStatusDescription();
		} catch (Exception exception) {
			statusCode = ResponseStatus.FAILURE.getStatusCode();
			statusDescription = ResponseStatus.FAILURE.getStatusDescription();
		}

		return new Response(statusCode, statusDescription);
	}

	@RequestMapping(value = "getPostionStatusList", method = RequestMethod.POST)
	public @ResponseBody Map<String, List<Application>> getPostionStatusList(HttpServletRequest request) {

		Map<String, List<Application>> positionStatusMap = null;

		try {
			List<Application> positionStatusList = positionService.getPositionStatusList();

			positionStatusMap = new HashMap<String, List<Application>>();
			positionStatusMap.put("data", positionStatusList);
		} catch (Exception exception) {
			return null;
		}

		return positionStatusMap;
	}

	@RequestMapping(value = "positionApply", method = RequestMethod.POST)
	public @ResponseBody Response positionApply(HttpServletRequest request) {

		int statusCode;
		String statusDescription;

		try {
			positionService.positionApply(request.getParameter("positionId"));

			statusCode = ResponseStatus.SUCCESS.getStatusCode();
			statusDescription = ResponseStatus.SUCCESS.getStatusDescription();

		} catch (Exception exception) {
			statusCode = ResponseStatus.FAILURE.getStatusCode();
			statusDescription = ResponseStatus.FAILURE.getStatusDescription();
		}

		return new Response(statusCode, statusDescription);
	}

	@RequestMapping(value = "position-detail", method = RequestMethod.GET)
	public ModelAndView showPositionDetailInfo(HttpServletRequest request) {

		String positionId = request.getParameter("positionId");
		Position position = null;
		Map<String, Object> models = null;
		String returnPage = null;

		try {
			position = positionService.getPositionInfo(Integer.parseInt(positionId));

			models = new HashMap<String, Object>();
			models.put("positionDetail", position);

			returnPage = POSITION_DETAIL_PAGE;

		} catch (Exception e) {
			returnPage = ERROR_PAGE;
		}

		return new ModelAndView(returnPage, models);
	}

	@RequestMapping(value = "getCandidateInfo", method = RequestMethod.POST)
	public @ResponseBody Response getAdmincandidateInfoPost(HttpServletRequest request) {
		int candidateId = Integer.parseInt(request.getParameter("candidateId"));
		int statusCode = 0;
		String statusDescription;
		Map<String, Object> models = null;
		try {
			Candidate candidate = candidateService.getCandidateInfo(candidateId);

			if (candidate != null) {
				models = new HashMap<String, Object>();
				models.put("candidateInfo", candidate);
			}
			statusCode = ResponseStatus.SUCCESS.getStatusCode();
			statusDescription = ResponseStatus.SUCCESS.getStatusDescription();

		} catch (Exception exception) {
			statusCode = ResponseStatus.FAILURE.getStatusCode();
			statusDescription = ResponseStatus.FAILURE.getStatusDescription();
		}

		return new Response(statusCode, statusDescription, models);
	}

	@RequestMapping(value = "getcandidateInfo", method = RequestMethod.GET)
	public @ResponseBody Response getAdmincandidateInfo(HttpServletRequest request) {
		return getAdmincandidateInfoPost(request);
	}

	@RequestMapping(value = "adminCandidateInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> handlecandidateObtain(HttpServletRequest request) {
		Map<String, Object> candidateMap = null;
		List<Candidate> candidateList = null;
		String keyword = null;
		try {
			candidateList = candidateService.getAdminCandidateInfo(keyword);
			candidateMap = new HashMap<String, Object>();
			candidateMap.put("data", candidateList);
		} catch (Exception ex) {

		}
		return candidateMap;
	}

	@RequestMapping(value = "deletecandidate", method = RequestMethod.GET)
	public @ResponseBody Response handleDeletecandidate(HttpServletRequest request) {
		String candidateId = request.getParameter("candidateId");
		Response rs = new Response(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription());
		try {
			candidateService.candidateDelete(Long.parseLong(candidateId));
		} catch (Exception e) {
			return null;
		}
		return rs;
	}

//	@RequestMapping(value = "exportCSV", method = RequestMethod.POST)
//	public @ResponseBody Response exportAll(HttpServletRequest request) throws Exception {
//
//		Response response = new Response();
//		Map<String, Object> models = new HashMap<String, Object>();
//
//		List<Application> positionStatusList = positionService.getPositionStatusList();
//
//		String filePath = CsvExporter.export(positionStatusList);
//
//		String[] filePathSplit = filePath.split("/");
//		String fileName = filePathSplit[3];
//
//		models.put("fileName", fileName);
//		response.setModels(models);
//
//		return response;
//	}

	@RequestMapping(value = "downloadCsv", method = RequestMethod.GET)
	public void downloadCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String fileName = request.getParameter("csvFileName");

		java.io.BufferedInputStream bufferInputStream = null;
		java.io.BufferedOutputStream bufferOutputStream = null;

		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "csvfiles/";
		String downLoadPath = ctxPath + fileName;

		try {
			long fileLength = new File(downLoadPath).length();

			response.setContentType("text/csv");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bufferInputStream = new BufferedInputStream(new FileInputStream(downLoadPath));
			bufferOutputStream = new BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bufferInputStream.read(buff, 0, buff.length))) {
				bufferOutputStream.write(buff, 0, bytesRead);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (bufferInputStream != null) {
				bufferInputStream.close();
			}
			if (bufferOutputStream != null) {
				bufferOutputStream.close();
			}
		}
	}

	@RequestMapping(value = "getAdmincandidateInfo", method = RequestMethod.GET)
	public ModelAndView showcandidateDetailInfo(HttpServletRequest request) {
		String returnPage;
		String candidateId = request.getParameter("candidateId");
		Map<String, Integer> model = new HashMap<String, Integer>();
		model.put("candidateId", Integer.parseInt(candidateId));
		try {
			returnPage = CANDIDATE_DETAIL_PAGE;

		} catch (Exception e) {
			returnPage = ERROR_PAGE;
		}

		return new ModelAndView(returnPage, model);
	}

//	@RequestMapping(value = "exportcandidateCSV", method = RequestMethod.POST)
//	public @ResponseBody Response exportcandidateAll(HttpServletRequest request) throws Exception {
//
//		Response response = new Response();
//		Map<String, Object> models = new HashMap<String, Object>();
//		String keyword = request.getParameter("keyword");
//		List<Candidate> candidateList = candidateService.getAdminCandidateInfo(keyword);
//
//		String filePath = CsvExporter.export(candidateList);
//
//		String[] filePathSplit = filePath.split("/");
//		String fileName = filePathSplit[3];
//
//		models.put("fileName", fileName);
//		response.setModels(models);
//
//		return response;
//
//	}

//	@RequestMapping(value = "exportApplicationCSV", method = RequestMethod.POST)
//	public @ResponseBody Response exportApplicationAll(HttpServletRequest request) throws Exception {
//
//		Response response = new Response();
//		Map<String, Object> models = new HashMap<String, Object>();
//		String keyword = request.getParameter("keyword");
//		try {
//			List<Application> applicationList = candidateService.exportApplicationInfo(keyword);
//			String filePath = CsvExporter.export(applicationList);
//			String[] filePathSplit = filePath.split("/");
//			String fileName = filePathSplit[3];
//			models.put("fileName", fileName);
//			response.setModels(models);
//			return response;
//		} catch (Exception e) {
//			return null;
//		}
//	}

	@RequestMapping(value = "candidateNumberValidation", method = RequestMethod.POST)
	public @ResponseBody boolean candidateNumberValidation(HttpServletRequest request) {
		Boolean candidateNumberValidation = true;

		try {
			if (candidateService.isCandidateNumberExisted(Long.parseLong(request.getParameter("candidateNo")))) {
				candidateNumberValidation = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return candidateNumberValidation;
	}
}

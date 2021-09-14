package com.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.constants.GeneticConstants;
import com.controller.inter.SalesProControllerIF;
import com.delegate.inter.SalesProDelegateIF;
import com.model.AJAXResponse;
import com.model.AssociationRuleMiningResults;
import com.model.BankInfo;
import com.model.BookPackModel;
import com.model.BookRating;
import com.model.BudgetVO;
import com.model.ClassifyCustomerOutput;
import com.model.ClassifyCustomerVO;
import com.model.ClassifyOutput;
import com.model.FullBookModelForUserId;
import com.model.HabitatFileVO;
import com.model.KMeansExtendedOutput;
import com.model.LicenseInfoForUserUI;
import com.model.Message;
import com.model.MontlyBudget;
import com.model.OrderInfo;
import com.model.PaginationConfigVO;
import com.model.RFMModelVO;
import com.model.RegisterUser;
import com.model.StatusInfo;
import com.model.UserInfo;
import com.utils.EmailUtil;

@Controller
public class SalesProControllerImpl implements SalesProControllerIF {

	@Autowired
	private SalesProDelegateIF salesDelegate;

	public SalesProDelegateIF getSalesDelegate() {
		return salesDelegate;
	}

	public void setSalesDelegate(SalesProDelegateIF salesDelegate) {
		this.salesDelegate = salesDelegate;
	}

	@Override
	@RequestMapping(value = "/specBook.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView retriveBooksForCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String id) {

		StatusInfo statusInfo = new StatusInfo();
		Long startTime = System.currentTimeMillis();

		HttpSession session = request.getSession(false);

		session.setAttribute(GeneticConstants.TIME_START, startTime);

		session.setAttribute(GeneticConstants.PAGE_ID, id);

		String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

		if (null == loginId || loginId.isEmpty()) {

			AJAXResponse ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);

		}

		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			List<BookPackModel> booksList = salesDelegate.retriveBooksForCategory(Integer.parseInt(id));

			FullBookModelForUserId fullBookModel = new FullBookModelForUserId();

			fullBookModel.setBooksList(booksList);

			ajaxRes.setModel(fullBookModel);
			ajaxRes.setStatus(true);

			statusInfo = insertHabitatCategoryBased(request, session, loginId, Integer.parseInt(id));

			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxRes);
			}

			storeLicense(session, ajaxRes);

			return new ModelAndView(GeneticConstants.Views.VIEW_BOOKS_OUTPUT, GeneticConstants.Keys.OBJ, ajaxRes);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_BOOKS_INPUT, GeneticConstants.Keys.OBJ, ajaxRes);

		}
	}

	private void storeLicense(HttpSession session, AJAXResponse ajaxRes) {
		// Store License Info
		LicenseInfoForUserUI licenseInfoForUserUI = (LicenseInfoForUserUI) session
				.getAttribute(GeneticConstants.Keys.LICENSE_INFO);

		ajaxRes.setLicenseInfo(licenseInfoForUserUI);
	}

	private StatusInfo insertHabitatCategoryBased(HttpServletRequest request, HttpSession session, String loginId,
			int pageId) {
		// Habitat Transaction
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);

		if (pageId == 1) {
			habitatFileVO.setActionName(GeneticConstants.Action.SPORTS_PAGE);
		} else if (pageId == 2) {
			habitatFileVO.setActionName(GeneticConstants.Action.POLITICS_PAGE);
		} else if (pageId == 3) {
			habitatFileVO.setActionName(GeneticConstants.Action.FLIMFARE_PAGE);
		} else if (pageId == 4) {
			habitatFileVO.setActionName(GeneticConstants.Action.ANALYTICS_PAGE);
		} else if (pageId == 5) {
			habitatFileVO.setActionName(GeneticConstants.Action.PROGRAMMING_PAGE);
		} else if (pageId == 6) {
			habitatFileVO.setActionName(GeneticConstants.Action.GREETINGS_PAGE);
		} else if (pageId == 7) {
			habitatFileVO.setActionName(GeneticConstants.Action.ADV_PAGE);
		}

		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(loginId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);

		return statusInfo;
	}

	private StatusInfo insertHabitatCategoryBasedForButton(HttpServletRequest request, HttpSession session,
			String loginId, int pageId) {
		// Habitat Transaction
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.BUTTON_TYPE_ACTION);

		if (pageId == 1) {
			habitatFileVO.setActionName(GeneticConstants.Action.BUY_BUTTON_SPORTS);
		} else if (pageId == 2) {
			habitatFileVO.setActionName(GeneticConstants.Action.BUY_BUTTON_POLITICS);
		} else if (pageId == 3) {
			habitatFileVO.setActionName(GeneticConstants.Action.BUY_BUTTON_FLIMFARE);
		} else if (pageId == 4) {
			habitatFileVO.setActionName(GeneticConstants.Action.BUY_BUTTON_ANALYTICS);
		} else if (pageId == 5) {
			habitatFileVO.setActionName(GeneticConstants.Action.BUY_BUTTON_PROGRAMMING);
		} else if (pageId == 6) {
			habitatFileVO.setActionName(GeneticConstants.Action.BUY_BUTTON_GREETINGS);
		} else if (pageId == 7) {
			habitatFileVO.setActionName(GeneticConstants.Action.ADV_PAGE);
		}

		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(loginId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);

		return statusInfo;
	}

	@Override
	@RequestMapping(value = "/books.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse retriveAllBooks(HttpServletRequest request) {

		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			List<BookPackModel> booksPackModel = salesDelegate.retriveAllBooksPack();

			if (null == booksPackModel) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.BOOKPACKLIST_EMPTY);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

			ajaxRes.setStatus(true);
			ajaxRes.setModel(booksPackModel);
			return ajaxRes;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/registerUser.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView registerUserInfo(@ModelAttribute RegisterUser registerUser, HttpServletRequest request) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			String firstName = registerUser.getFirstName();
			if (null == firstName || firstName.isEmpty() || firstName.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.FIRSTNAME_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);

			}
			String lastName = registerUser.getLastName();
			if (null == lastName || lastName.isEmpty() || lastName.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.LASTNAME_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String userId = registerUser.getUserId();

			if (null == userId || userId.isEmpty() || userId.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.USERID_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String email = registerUser.getEmailId();

			if (null == email || email.isEmpty() || email.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.EMAIL_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			EmailUtil emailUtil = new EmailUtil();

			boolean isvalid = emailUtil.validate(email);

			if (!isvalid) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.EMAIL_INCORRECT_FORMAT);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);

				return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String password = registerUser.getUserPassword();

			if (null == password || password.isEmpty() || password.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.PASSWORD_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			StatusInfo statusInfo = salesDelegate.doRegistration(registerUser);

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(statusInfo.getErrMsg());
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			HttpSession session = request.getSession(true);

			session.setAttribute(GeneticConstants.Keys.LOGINID_SESSION, registerUser.getUserId());
			session.setAttribute(GeneticConstants.Keys.LOGINTYPE_SESSION, statusInfo.getType());

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.USERREGISTERED_SUCESS_MSG);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_REGISTER_INPUT, GeneticConstants.Keys.OBJ,
					ajaxResponse);
		}

	}

	@Override
	@RequestMapping(value = "/login.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doLogin(HttpServletRequest request, @ModelAttribute RegisterUser registerUser) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			String userId = registerUser.getUserId();

			if (null == userId || userId.isEmpty() || userId.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.USERID_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String password = registerUser.getUserPassword();

			if (null == password || password.isEmpty() || password.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.PASSWORD_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			StatusInfo statusInfo = salesDelegate.checkLogin(registerUser);

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(statusInfo.getErrMsg());
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			} else if (statusInfo.isStatus()) {

				HttpSession session = request.getSession(true);

				session.setAttribute(GeneticConstants.Keys.LOGINID_SESSION, registerUser.getUserId());
				session.setAttribute(GeneticConstants.Keys.LOGINTYPE_SESSION, statusInfo.getType());

				if (statusInfo.getType() == GeneticConstants.Keys.ADMIN_TYPE) {
					ajaxResponse = new AJAXResponse();
					ajaxResponse.setStatus(true);
					Message msg = new Message();
					msg.setMsg(GeneticConstants.Message.USERREGISTERED_SUCESS_MSG);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					return new ModelAndView(GeneticConstants.Views.VIEW_ADMIN_WELCOMEPAGE, GeneticConstants.Keys.OBJ,
							ajaxResponse);
				} else {

					statusInfo = insertHomePageHabitat(request, registerUser.getUserId(), session);

					if (!statusInfo.isStatus()) {
						ajaxResponse = new AJAXResponse();
						ajaxResponse.setStatus(true);
						Message msg = new Message();
						msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
						List<Message> ebErrors = new ArrayList<Message>();
						ebErrors.add(msg);
						ajaxResponse.setEbErrors(ebErrors);
						return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
								ajaxResponse);
					}
					// Obtain Licenses for Customer
					LicenseInfoForUserUI licenseInfoForUserUI = salesDelegate.obtainLicenseInfoForCustomer(userId);
					session.setAttribute(GeneticConstants.Keys.LICENSE_INFO, licenseInfoForUserUI);
					ajaxResponse = new AJAXResponse();
					ajaxResponse.setStatus(true);
					ajaxResponse.setLicenseInfo(licenseInfoForUserUI);
					Message msg = new Message();
					msg.setMsg(GeneticConstants.Message.USERREGISTERED_SUCESS_MSG);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					return new ModelAndView(GeneticConstants.Views.VIEW_CUSTOMER_WELCOMEPAGE, GeneticConstants.Keys.OBJ,
							ajaxResponse);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);
		}
		return null;

	}

	private StatusInfo insertHomePageHabitat(HttpServletRequest request, String userId, HttpSession session) {
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
		habitatFileVO.setActionName(GeneticConstants.Action.HOME_PAGE);
		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(userId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);
		return statusInfo;
	}

	private StatusInfo insertBudgetBtnHabitat(HttpServletRequest request, String userId, HttpSession session) {
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.BUTTON_TYPE_ACTION);
		habitatFileVO.setActionName(GeneticConstants.Action.BUTTON_BUDGET_SETTINGS);
		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(userId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);
		return statusInfo;
	}

	private StatusInfo insertAssociationRuleMiningPageHabitat(HttpServletRequest request, String userId,
			HttpSession session) {
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
		habitatFileVO.setActionName(GeneticConstants.Action.ASSOCIATION_RULE_PAGE);
		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(userId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);
		return statusInfo;
	}

	private StatusInfo insertPersonalSettingsPageHabitat(HttpServletRequest request, String loginId,
			HttpSession session) {
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
		habitatFileVO.setActionName(GeneticConstants.Action.PERSONALSETTINGS_PAGE);
		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(loginId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);
		return statusInfo;
	}

	private StatusInfo insertLogoutPageHabitat(HttpServletRequest request, String loginId, HttpSession session) {
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.LINK_TYPE_ACTION);
		habitatFileVO.setActionName(GeneticConstants.Action.LOGOUT_BUTTON);
		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(loginId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);
		return statusInfo;
	}

	@Override
	@RequestMapping(value = "/logout.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doLogout(HttpServletRequest request) {
		try {
			AJAXResponse ajaxResponse = null;

			Long stopTime = System.currentTimeMillis();

			int contentLength = request.getContentLength();

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

			if (null == loginId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			Long startTime = (Long) session.getAttribute(GeneticConstants.TIME_START);

			String pageIdStr = (String) session.getAttribute(GeneticConstants.PAGE_ID);
			Integer pageId = Integer.parseInt(pageIdStr);

			StatusInfo statusInfo = salesDelegate.storeStatistics(startTime, pageId, stopTime, contentLength, loginId);

			statusInfo = insertLogoutPageHabitat(request, loginId, session);
			session.invalidate();
			return new ModelAndView(GeneticConstants.Views.APPLICATION_WELCOME_PAGE, GeneticConstants.Keys.OBJ, null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return new ModelAndView(GeneticConstants.Views.APPLICATION_WELCOME_PAGE, GeneticConstants.Keys.OBJ, null);
		}
	}

	@Override
	@RequestMapping(value = "/viewStatistics.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewUserStatistics(HttpServletRequest request, HttpServletResponse response) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<UserInfo> historyRatings = salesDelegate.viewStatistics();

			if (null == historyRatings || historyRatings.isEmpty() || historyRatings.size() == 0) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				ajaxResponse.setMessage(GeneticConstants.Message.VIEWSTATS_LIST_EMPTY);
				return ajaxResponse;
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(historyRatings);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}

	}

	@Override
	@RequestMapping(value = "/viewUsers.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewUsers(HttpServletRequest request, HttpServletResponse response) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<UserInfo> userList = salesDelegate.viewUsers();

			if (null == userList || userList.isEmpty() || userList.size() == 0) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				ajaxResponse.setMessage(GeneticConstants.Message.VIEWSTATS_LIST_EMPTY);
				return ajaxResponse;
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(userList);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}

	}

	@Override
	@RequestMapping(value = "/buyBook.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView redirectBankPage(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute OrderInfo orderInfo) {

		AJAXResponse ajaxResponse = null;
		try {

			Long stopTime = System.currentTimeMillis();

			int contentLength = request.getContentLength();

			ajaxResponse = new AJAXResponse();

			int bookId = orderInfo.getBookId();

			if (bookId <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.BOOKID_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_CUSTWELCOME_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			int noOfBooks = orderInfo.getNoOfBooks();

			if (noOfBooks <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.NOOFBOOKS_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_CUSTWELCOME_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			double bookPrice = orderInfo.getBookPrice();
			if (bookPrice <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.BOOKPRICE_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_CUSTWELCOME_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

			if (null == loginId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			orderInfo.setLoginId(loginId);

			// Now Redirect to Bank Page

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(orderInfo);

			session.setAttribute(GeneticConstants.Session.ORDERINFO_SESSION_KEY, orderInfo);

			Long startTime = (Long) session.getAttribute(GeneticConstants.TIME_START);

			String pageIdStr = (String) session.getAttribute(GeneticConstants.PAGE_ID);
			Integer pageId = Integer.parseInt(pageIdStr);

			// STore the Staticstics
			StatusInfo statusInfo = salesDelegate.storeStatistics(startTime, pageId, stopTime, contentLength, loginId);

			MontlyBudget montlyBudget = salesDelegate.findBudgetForUser(loginId, bookPrice);

			//
			statusInfo = insertHabitatCategoryBasedForButton(request, session, loginId, pageId);

			if (!statusInfo.isStatus()) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				storeLicense(session, ajaxResponse);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			if (montlyBudget != null) {

				boolean isExceeded = montlyBudget.isStatus();

				if (isExceeded) {

					HabitatFileVO habitatFileVO = new HabitatFileVO();

					habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
					habitatFileVO.setActionName(GeneticConstants.Action.BUDGETWARNING_PAGE);
					habitatFileVO.setSessionName(session.getId());
					habitatFileVO.setUserName(loginId);

					String ipAddress = request.getHeader("X-FORWARDED-FOR");
					if (ipAddress == null) {
						ipAddress = request.getRemoteAddr();

					}
					habitatFileVO.setIpAddress(ipAddress);

					statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);

					if (!statusInfo.isStatus()) {

						ajaxResponse = new AJAXResponse();
						ajaxResponse.setStatus(true);
						Message msg = new Message();
						msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
						List<Message> ebErrors = new ArrayList<Message>();
						ebErrors.add(msg);
						ajaxResponse.setEbErrors(ebErrors);
						storeLicense(session, ajaxResponse);
						return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
								ajaxResponse);
					}

					ajaxResponse.setModel(montlyBudget);
					storeLicense(session, ajaxResponse);
					return new ModelAndView(GeneticConstants.Views.VIEW_BUDGET_WARNING, GeneticConstants.Keys.OBJ,
							ajaxResponse);

				}

			}

			// Now Redirect to Bank Page

			HabitatFileVO habitatFileVO = new HabitatFileVO();

			habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
			habitatFileVO.setActionName(GeneticConstants.Action.BANK_PAGE);
			habitatFileVO.setSessionName(session.getId());
			habitatFileVO.setUserName(loginId);

			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();

			}
			habitatFileVO.setIpAddress(ipAddress);

			statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);

			if (!statusInfo.isStatus()) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				storeLicense(session, ajaxResponse);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(orderInfo);
			storeLicense(session, ajaxResponse);
			return new ModelAndView(GeneticConstants.Views.VIEW_BANK_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);
		}

	}

	@Override
	@RequestMapping(value = "/banking.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView completeBuyingTransaction(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute BankInfo bankInfo) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			if (null == session.getAttribute(GeneticConstants.Session.ORDERINFO_SESSION_KEY)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			OrderInfo orderInfo = (OrderInfo) session.getAttribute(GeneticConstants.Session.ORDERINFO_SESSION_KEY);

			if (null == orderInfo) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			int bookId = orderInfo.getBookId();

			if (bookId <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.BOOKID_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_CUSTWELCOME_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			int noOfBooks = orderInfo.getNoOfBooks();

			if (noOfBooks <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.NOOFBOOKS_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_CUSTWELCOME_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			double bookPrice = orderInfo.getBookPrice();
			if (bookPrice <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.BOOKPRICE_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_CUSTWELCOME_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String loginId = orderInfo.getLoginId();

			if (null == loginId || (loginId != null && loginId.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String accountNo = bankInfo.getAccountNo();
			if (null == accountNo || (accountNo != null && accountNo.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.ACCOUNTNO_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_BANK_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String password = bankInfo.getPassword();
			if (null == password || (password != null && password.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.IPIN_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_BANK_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String address = bankInfo.getAddress();

			if (null == address || (address != null && address.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.ADDRESS_EMPTY_ERR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_BANK_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			bankInfo.setBookId(bookId);
			bankInfo.setBookPrice(bookPrice);
			bankInfo.setLoginId(loginId);
			bankInfo.setNoOfBooks(noOfBooks);

			// Maintaining the Habitat Log
			HabitatFileVO habitatFileVO = new HabitatFileVO();

			habitatFileVO.setActionType(GeneticConstants.ActionType.BUTTON_TYPE_ACTION);
			habitatFileVO.setActionName(GeneticConstants.Action.BUTTON_PROCEED_BANK);
			habitatFileVO.setSessionName(session.getId());
			habitatFileVO.setUserName(loginId);

			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();

			}
			habitatFileVO.setIpAddress(ipAddress);

			StatusInfo statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				storeLicense(session, ajaxResponse);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			statusInfo = salesDelegate.completeBuyingTransaction(bankInfo);

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();

				ajaxResponse.setStatus(false);
				List<Message> ebErrors = new ArrayList<Message>();
				Message m = new Message();
				if (statusInfo.getErrMsg() != null) {
					m.setMsg(statusInfo.getErrMsg());
				} else {
					m.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				}
				ebErrors.add(m);
				ajaxResponse.setEbErrors(ebErrors);
				storeLicense(session, ajaxResponse);

				return new ModelAndView(GeneticConstants.Views.VIEW_BANK_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);

			}

			// Maintaining the Habitat Log
			habitatFileVO = new HabitatFileVO();

			habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
			habitatFileVO.setActionName(GeneticConstants.Action.TRANSACTION_PAGE);
			habitatFileVO.setSessionName(session.getId());
			habitatFileVO.setUserName(loginId);

			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();

			}
			habitatFileVO.setIpAddress(ipAddress);

			statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				storeLicense(session, ajaxResponse);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setMessage(GeneticConstants.Views.TRANSACTION_SUCESS + "   "
					+ "The Delivery will be made for Address:" + bankInfo.getAddress());
			storeLicense(session, ajaxResponse);
			return new ModelAndView(GeneticConstants.Views.SUCESS_PAGE, GeneticConstants.Keys.OBJ, ajaxResponse);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);
		}
	}

	//

	@Override
	@RequestMapping(value = "/logoutadmin.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doLogoutForAdmin(HttpServletRequest request) {
		try {
			AJAXResponse ajaxResponse = null;

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

			if (null == loginId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			session.invalidate();
			return new ModelAndView(GeneticConstants.Views.APPLICATION_WELCOME_PAGE, GeneticConstants.Keys.OBJ, null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return new ModelAndView(GeneticConstants.Views.APPLICATION_WELCOME_PAGE, GeneticConstants.Keys.OBJ, null);
		}
	}

	@Override
	@RequestMapping(value = "/custwelcome.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		HttpSession session = request.getSession(false);
		try {
			Long stopTime = System.currentTimeMillis();

			int contentLength = request.getContentLength();

			if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

			if (null == loginId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			if (session.getAttribute(GeneticConstants.TIME_START) != null
					&& session.getAttribute(GeneticConstants.PAGE_ID) != null) {

				Long startTime = (Long) session.getAttribute(GeneticConstants.TIME_START);
				String pageIdStr = null;
				try {
					pageIdStr = (String) session.getAttribute(GeneticConstants.PAGE_ID);

					if (null == pageIdStr) {
						pageIdStr = "7";
					}
				} catch (Exception e) {
					pageIdStr = "7";
				}

				Integer pageId = Integer.parseInt(pageIdStr);

				if (contentLength <= 0) {
					contentLength = 30;
				}

				// STore the Staticstics
				StatusInfo statusInfo = salesDelegate.storeStatistics(startTime, pageId, stopTime, contentLength,
						loginId);

				insertHomePageHabitat(request, loginId, session);

				if (statusInfo != null && statusInfo.isStatus()) {
					storeLicense(session, ajaxResponse);
					return new ModelAndView(GeneticConstants.Views.VIEW_CUSTOMER_WELCOMEPAGE, GeneticConstants.Keys.OBJ,
							ajaxResponse);
				} else {
					Message msg = new Message();
					msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					storeLicense(session, ajaxResponse);
					return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
							ajaxResponse);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			AJAXResponse ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_BOOKS_INPUT, GeneticConstants.Keys.OBJ, ajaxRes);

		}
		storeLicense(session, ajaxResponse);
		return new ModelAndView(GeneticConstants.Views.VIEW_CUSTOMER_WELCOMEPAGE, GeneticConstants.Keys.OBJ,
				ajaxResponse);
	}

	@Override
	@RequestMapping(value = "/budget.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView budgetForCustomer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String budget) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			if (null == budget || budget.isEmpty() || budget.trim().length() == 0) {
				AJAXResponse ajax = new AJAXResponse();
				ajax.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.BUDGET_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajax.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.BUDGET_INPUT_PAGE, GeneticConstants.Keys.OBJ, ajax);
			}

			HttpSession session = request.getSession(false);
			String userName = null;
			StatusInfo statusInfo = null;
			if (session != null) {
				userName = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);
				if (userName != null && !userName.isEmpty()) {

					BudgetVO budgetVO = new BudgetVO();

					budgetVO.setBudget(new Double(budget));
					budgetVO.setUserId(userName);

					statusInfo = salesDelegate.addBudget(budgetVO);

					if (!statusInfo.isStatus()) {
						AJAXResponse ajax = new AJAXResponse();
						ajax.setStatus(false);
						Message msg = new Message();
						msg.setMsg(statusInfo.getErrMsg());
						List<Message> ebErrors = new ArrayList<Message>();
						ebErrors.add(msg);
						ajax.setEbErrors(ebErrors);
						storeLicense(session, ajax);
						return new ModelAndView(GeneticConstants.Views.BUDGET_INPUT_PAGE, GeneticConstants.Keys.OBJ,
								ajax);
					} else {

						statusInfo = new StatusInfo();

						statusInfo = insertBudgetBtnHabitat(request, userName, session);

						if (!statusInfo.isStatus()) {
							ajaxResponse = new AJAXResponse();
							ajaxResponse.setStatus(false);
							Message msg = new Message();
							msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
							List<Message> ebErrors = new ArrayList<Message>();
							ebErrors.add(msg);
							ajaxResponse.setEbErrors(ebErrors);
							return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
									ajaxResponse);
						}

						ajaxResponse.setStatus(true);
						ajaxResponse.setMessage("Budget Stored Sucessfully");
						storeLicense(session, ajaxResponse);
						return new ModelAndView(GeneticConstants.Views.VIEW_SUCESS_PAGE, GeneticConstants.Keys.OBJ,
								ajaxResponse);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return new ModelAndView(GeneticConstants.Views.BUDGET_INPUT_PAGE, GeneticConstants.Keys.OBJ, ajaxResponse);
		}
		return null;

	}

	@Override
	@RequestMapping(value = "/budgetMove.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView redirectDecision(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String decision) {

		AJAXResponse ajaxResponse = null;

		HttpSession session = request.getSession(false);

		if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);
		}

		String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

		if (null == loginId) {
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);
		}
		try {
			ajaxResponse = new AJAXResponse();

			String pageIdStr = (String) session.getAttribute(GeneticConstants.PAGE_ID);

			if (pageIdStr != null) {

				HabitatFileVO habitatFileVO = new HabitatFileVO();

				habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
				habitatFileVO.setActionName(GeneticConstants.Action.BANK_PAGE);
				habitatFileVO.setSessionName(session.getId());
				habitatFileVO.setUserName(loginId);

				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();

				}
				habitatFileVO.setIpAddress(ipAddress);

				StatusInfo statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);

				if (!statusInfo.isStatus()) {

					ajaxResponse = new AJAXResponse();
					ajaxResponse.setStatus(true);
					Message msg = new Message();
					msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
							ajaxResponse);

				}

			}

			if (decision.equals("YES")) {
				storeLicense(session, ajaxResponse);
				return new ModelAndView(GeneticConstants.Views.VIEW_BANK_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);

			} else {
				storeLicense(session, ajaxResponse);
				return new ModelAndView(GeneticConstants.Views.VIEW_CUSTOMER_WELCOMEPAGE, GeneticConstants.Keys.OBJ,
						ajaxResponse);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);
		}

	}

	@Override
	@RequestMapping(value = "/perSettingsNav.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView navPersonalSettings(HttpServletRequest request) {
		try {
			AJAXResponse ajaxResponse = new AJAXResponse();

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

			if (null == loginId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			StatusInfo statusInfo = insertPersonalSettingsPageHabitat(request, loginId, session);

			if (!statusInfo.isStatus()) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);

			}
			storeLicense(session, ajaxResponse);
			return new ModelAndView(GeneticConstants.Views.PERSONAL_SETTINGS_PAGE, GeneticConstants.Keys.OBJ, null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return new ModelAndView(GeneticConstants.Views.APPLICATION_WELCOME_PAGE, GeneticConstants.Keys.OBJ, null);
		}
	}

	public StatusInfo insertBudgetPageHabitat(HttpServletRequest request, String userId, HttpSession session) {
		StatusInfo statusInfo;
		// Habitat Transaction
		HabitatFileVO habitatFileVO = new HabitatFileVO();

		habitatFileVO.setActionType(GeneticConstants.ActionType.PAGE_TYPE_ACTION);
		habitatFileVO.setActionName(GeneticConstants.Action.BUDGET_PAGE);
		habitatFileVO.setSessionName(session.getId());
		habitatFileVO.setUserName(userId);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();

		}
		habitatFileVO.setIpAddress(ipAddress);

		statusInfo = salesDelegate.insertHabitatFileVO(habitatFileVO);
		return statusInfo;
	}

	@Override
	@RequestMapping(value = "/budgetNav.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView navBudgetPageHabitat(HttpServletRequest request) {
		try {
			AJAXResponse ajaxResponse = new AJAXResponse();

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

			if (null == loginId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);
			}

			StatusInfo statusInfo = insertBudgetPageHabitat(request, loginId, session);

			if (!statusInfo.isStatus()) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ,
						ajaxResponse);

			}
			storeLicense(session, ajaxResponse);
			return new ModelAndView(GeneticConstants.Views.BUDGET_INPUT_PAGE, GeneticConstants.Keys.OBJ, null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return new ModelAndView(GeneticConstants.Views.APPLICATION_WELCOME_PAGE, GeneticConstants.Keys.OBJ, null);
		}
	}

	@Override
	@RequestMapping(value = "/applyLicense.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse applyLicense(HttpServletRequest request, HttpServletResponse response,
			@RequestBody LicenseInfoForUserUI license) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();
			StatusInfo statusInfo = salesDelegate.applyLicense(license);

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				ajaxResponse.setMessage(statusInfo.getErrMsg());
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(GeneticConstants.Message.APPLY_LICENSE_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;
		}

	}

	@Override
	@RequestMapping(value = "/viewSessionsForUser.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewSessionForUsers(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String userId) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<HabitatFileVO> sessionIdList = salesDelegate.viewSessionForUsers(userId);
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(sessionIdList);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}
	}

	@Override
	@RequestMapping(value = "/viewHabitatFileForUserIdAndSessionId.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse viewHabitatLogForUserIdAndSessionId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String userId, @RequestParam String sessionId) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<HabitatFileVO> sessionIdList = salesDelegate.viewHabitatFileForUserIdAndSessionId(userId, sessionId);
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(sessionIdList);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}
	}

	@Override
	@RequestMapping(value = "/executekmeans.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse doKMeans(HttpServletRequest request) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			StatusInfo statusInfo = new StatusInfo();

			statusInfo = salesDelegate.doKMeans();

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);

				List<Message> ebErrors = new ArrayList<Message>();

				Message message = new Message();
				message.setMsg(statusInfo.getErrMsg());
				ebErrors.add(message);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setMessage(GeneticConstants.Message.KMEANS_EXECUTION_SUCESSFUL);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}

	}

	@Override
	@RequestMapping(value = "/viewKmeans.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewKMeansClustering(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			StatusInfo statusInfo = salesDelegate.viewKMeansClustering(paginationConfigVO);

			if (!statusInfo.isStatus()) {
				List<Message> ebErrors = new ArrayList<Message>();
				Message msg = new Message();
				msg.setMsg("Could not Retrive RFM List");
				ebErrors.add(msg);
				ajaxResponse.setStatus(false);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(statusInfo.getModel());
			ajaxResponse.setTotal(statusInfo.getTotal());
			return ajaxResponse;
		

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}

	}

	@Override
	@RequestMapping(value = "/viewKMeansGraph.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewClassifier(HttpServletRequest request, HttpServletResponse response) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<ClassifyOutput> classifyOutputList = salesDelegate.retriveClassification();

			if (null == classifyOutputList || classifyOutputList.isEmpty() || classifyOutputList.size() == 0) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				ajaxResponse.setMessage(GeneticConstants.Message.VIEWSTATS_LIST_EMPTY);
				return ajaxResponse;
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(classifyOutputList);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}

	}

	@Override
	@RequestMapping(value = "/executeRFM.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse performRFM(HttpServletRequest request) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			StatusInfo statusInfo = new StatusInfo();

			statusInfo = salesDelegate.performRFM();

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);

				List<Message> ebErrors = new ArrayList<Message>();

				Message message = new Message();
				message.setMsg(statusInfo.getErrMsg());
				ebErrors.add(message);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setMessage(GeneticConstants.Message.RFM_EXECUTION_SUCESSFUL);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}

	}

	@Override
	@RequestMapping(value = "/viewRFM.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewRFMClustering(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			StatusInfo statusInfo = salesDelegate.viewRFMClustering(paginationConfigVO);

			if (!statusInfo.isStatus()) {
				List<Message> ebErrors = new ArrayList<Message>();
				Message msg = new Message();
				msg.setMsg("Could not Retrive RFM List");
				ebErrors.add(msg);
				ajaxResponse.setStatus(false);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(statusInfo.getModel());
			ajaxResponse.setTotal(statusInfo.getTotal());
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}

	}

	@Override
	@RequestMapping(value = "/classifyCustomer.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse clasifyCustomer(HttpServletRequest request) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			StatusInfo statusInfo = new StatusInfo();

			statusInfo = salesDelegate.classifyCustomer();

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);

				List<Message> ebErrors = new ArrayList<Message>();

				Message message = new Message();
				message.setMsg(statusInfo.getErrMsg());
				ebErrors.add(message);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setMessage(GeneticConstants.Message.CLASSIFY_CUSTOMER_EXECUTION_SUCESSFUL);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}

	}

	@Override
	@RequestMapping(value = "/viewCountsForClassifier.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewRFMClassifierGraphs(HttpServletRequest request,
			HttpServletResponse response) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<ClassifyCustomerOutput> rfmClassifierGraphList = salesDelegate.viewRFMClassifierGraphs();

			if (null == rfmClassifierGraphList || rfmClassifierGraphList.isEmpty()
					|| rfmClassifierGraphList.size() == 0) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				ajaxResponse.setMessage(GeneticConstants.Message.CUSTOMER_GRAPH_LIST_EMPTY);
				return ajaxResponse;
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(rfmClassifierGraphList);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}

	}

	@Override
	@RequestMapping(value = "/viewCustomerClusterOutput.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewClassifyCustomer(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			StatusInfo stat = salesDelegate.viewClassifyCustomer(paginationConfigVO);

			if (!stat.isStatus()) {
				ajaxResponse.setStatus(false);
				List<Message> ebErrors = new ArrayList<Message>();
				Message msg = new Message();
				msg.setMsg(stat.getErrMsg());
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(stat.getModel());
			ajaxResponse.setTotal(stat.getTotal());
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			ajaxResponse.setMessage(e.getMessage());
			return ajaxResponse;

		}

	}

	@Override
	@RequestMapping(value = "/advRecommend.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView retriveBooksBasedOnClassification(HttpServletRequest request, HttpServletResponse response) {

		StatusInfo statusInfo = new StatusInfo();
		Long startTime = System.currentTimeMillis();

		HttpSession session = request.getSession(false);

		session.setAttribute(GeneticConstants.TIME_START, startTime);

		session.setAttribute(GeneticConstants.PAGE_ID, "7");

		String loginId = (String) session.getAttribute(GeneticConstants.Keys.LOGINID_SESSION);

		if (null == loginId || loginId.isEmpty()) {

			AJAXResponse ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			Message msg = new Message();
			msg.setMsg(GeneticConstants.Message.SESSION_INVALID);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_LOGIN_INPUT, GeneticConstants.Keys.OBJ, ajaxResponse);

		}

		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			statusInfo = salesDelegate.retriveBooksForCategoryAdvCategory(loginId);

			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(statusInfo.getErrMsg());
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_BOOKS_OUTPUT, GeneticConstants.Keys.OBJ, ajaxRes);
			}

			FullBookModelForUserId fullBookModel = (FullBookModelForUserId) statusInfo.getModel();

			ajaxRes.setModel(fullBookModel);
			ajaxRes.setStatus(true);

			statusInfo = insertHabitatCategoryBased(request, session, loginId, Integer.parseInt("7"));

			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				Message msg = new Message();
				msg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(GeneticConstants.Views.VIEW_BOOKS_OUTPUT, GeneticConstants.Keys.OBJ, ajaxRes);
			}

			storeLicense(session, ajaxRes);

			return new ModelAndView(GeneticConstants.Views.VIEW_BOOKS_OUTPUT, GeneticConstants.Keys.OBJ, ajaxRes);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(GeneticConstants.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(GeneticConstants.Views.VIEW_BOOKS_OUTPUT, GeneticConstants.Keys.OBJ, ajaxRes);

		}
	}
}

package com.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.constants.GeneticConstants;
import com.dao.inter.SalesProDaoIF;
import com.model.AssociationRuleMiningResults;
import com.model.BankInfo;
import com.model.BankModel;
import com.model.BookPackModel;
import com.model.BookRating;
import com.model.BudgetVO;
import com.model.ClassifyCustomerOutput;
import com.model.ClassifyCustomerVO;
import com.model.ClassifyOutput;
import com.model.FullBookModelForUserId;
import com.model.HabitatFileVO;
import com.model.IntruderInfo;
import com.model.KMeansExtendedOutput;
import com.model.KMeansOutput;
import com.model.RFMOutput;
import com.model.LicenseInfoForUserUI;
import com.model.LicenseInfoStoreBackend;
import com.model.MontlyBudget;
import com.model.OrderDetailsModel;
import com.model.OrderInfoDB;
import com.model.PaginationConfigVO;
import com.model.RFMModelVO;
import com.model.RegisterUser;
import com.model.StatusInfo;
import com.model.TransactionModel;
import com.model.UserInfo;
import com.service.inter.SalesProServiceIF;
import com.utils.DateUtils;
import com.utils.FormulaUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class SalesProServiceImpl implements SalesProServiceIF {

	@Autowired
	private SalesProDaoIF salesDao;

	public SalesProDaoIF getSalesDao() {
		return salesDao;
	}

	public void setSalesDao(SalesProDaoIF salesDao) {
		this.salesDao = salesDao;
	}

	@Override
	public StatusInfo doRegistration(RegisterUser registerUser) {

		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> userIdList = salesDao.retriveUserIds();
			if (null == userIdList) {
				statusInfo = salesDao.insertLogin(registerUser);

				if (!statusInfo.isStatus()) {
					statusInfo.setErrMsg(GeneticConstants.Message.INTERNAL_ERROR);
					statusInfo.setStatus(false);
					return statusInfo;
				} else {
					return statusInfo;
				}

			}

			if (userIdList.contains(registerUser.getUserId())) {
				statusInfo.setErrMsg(GeneticConstants.Message.USERALREADY_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = salesDao.insertLogin(registerUser);

				if (!statusInfo.isStatus()) {
					statusInfo.setErrMsg(GeneticConstants.Message.INTERNAL_ERROR);
					statusInfo.setStatus(false);
					return statusInfo;
				} else {
					return statusInfo;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(GeneticConstants.Message.INTERNAL_ERROR);
			statusInfo.setStatus(false);
			return statusInfo;

		}

	}

	@Override
	public StatusInfo checkLogin(RegisterUser registerUser) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			System.out.println("Inside Verify Login Service");
			boolean status = checkUserInformation(registerUser.getUserId());
			if (!status) {
				statusInfo.setErrMsg(GeneticConstants.Message.NO_USER_EXISTS);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				String password = salesDao.retrivePassword(registerUser.getUserId());

				if (null == password || password.isEmpty()) {
					statusInfo.setErrMsg(GeneticConstants.Message.PASSWORD_WRONG);
					statusInfo.setStatus(false);
					return statusInfo;
				}
				if (!password.equals(registerUser.getUserPassword())) {
					statusInfo.setErrMsg(GeneticConstants.Message.PASSWORD_WRONG);
					statusInfo.setStatus(false);
					return statusInfo;
				}
				if (password.equals(registerUser.getUserPassword())) {
					statusInfo.setErrMsg(GeneticConstants.Message.USER_LOGIN_SUCESS);
					statusInfo.setStatus(true);
					// Now retrieve the login type
					int loginTypeDB = salesDao.retriveLoginType(registerUser.getUserId());
					statusInfo.setType(loginTypeDB);
					return statusInfo;
				}
			}
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
		return statusInfo;
	}

	private boolean checkUserInformation(String registerUser) {
		try {
			List<String> userNameList = salesDao.retriveUserIds();

			System.out.println("LIST" + userNameList);
			if (null == userNameList || userNameList.isEmpty() || userNameList.size() == 0) {
				return false;
			}
			if (userNameList.contains(registerUser)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return true;
		}
	}

	@Override
	public StatusInfo completePackageTransaction(BankModel bankModel) {

		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			// double cost = computeCostForPackage(bankModel.getPackId());

			double cost = 0;

			// Retrive List of Account Nos
			List<String> accountNoList = salesDao.retriveAccountNoList();
			if (null == accountNoList) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.ACCNOLSIT_EMPTY);
			}

			if (!accountNoList.contains(bankModel.getAccountNo())) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.ACCNOLSIT_EMPTY);
			} else {
				String ipinDb = salesDao.retriveIPINForAccNo(bankModel.getAccountNo());
				if (null == ipinDb) {
					statusInfo.setStatus(false);
					statusInfo.setErrMsg(GeneticConstants.Message.IPIN_EMPTY);
				}

				if (!ipinDb.equals(bankModel.getiPin())) {
					statusInfo.setStatus(false);
					statusInfo.setErrMsg(GeneticConstants.Message.INVALID_CREDENTIALS);
				} else {
					double accountBalance = salesDao.retriveBalanceForAccount(bankModel.getAccountNo());

					if (accountBalance <= 0) {
						statusInfo.setStatus(false);
						statusInfo.setErrMsg(GeneticConstants.Message.INSUFFICENT_FUNDS);
					} else {
						if (accountBalance < cost) {
							statusInfo.setStatus(false);
							statusInfo.setErrMsg(GeneticConstants.Message.INSUFFICENT_FUNDS);
						} else {
							double newBalance = accountBalance - cost;

							statusInfo = salesDao.updateBalance(bankModel.getAccountNo(), newBalance);
							if (!statusInfo.isStatus()) {
								statusInfo.setErrMsg(GeneticConstants.Message.BALANCE_UPDATE_FAILED);
								statusInfo.setStatus(false);
								return statusInfo;
							} else {
								TransactionModel transactionModel = new TransactionModel();

								transactionModel.setPackId(bankModel.getPackId());
								transactionModel.setCustomerId(bankModel.getCustomerId());

								statusInfo = salesDao.insertTransaction(transactionModel);

								if (!statusInfo.isStatus()) {
									statusInfo.setErrMsg(GeneticConstants.Message.INSERT_TRANS_FAILED);
									statusInfo.setStatus(false);
									return statusInfo;
								} else {
									statusInfo.setStatus(true);

									return statusInfo;

								}

							}

						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION " + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;

	}

	@Override
	public List<BookPackModel> retriveAllBooksPack() {
		List<BookPackModel> bookModelList = null;
		try {
			bookModelList = salesDao.retriveAllBooksPack();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e.getMessage());
			return null;
		}
		return bookModelList;

	}

	@Override
	public List<BookPackModel> retriveBooksForCategory(int catId) {

		List<BookPackModel> bookModelList = null;
		try {
			bookModelList = salesDao.retriveAllBooksPackForCat(catId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e.getMessage());
			return null;
		}
		return bookModelList;

	}

	@Override
	@Transactional
	public StatusInfo completeBuyingTransaction(BankInfo bankInfo) {
		StatusInfo statusInfo = null;
		try {

			statusInfo = new StatusInfo();

			// double cost = computeCostForPackage(bankModel.getPackId());

			double cost = bankInfo.getBookPrice() * bankInfo.getNoOfBooks();

			// Retrive List of Account Nos
			List<String> accountNoList = salesDao.retriveAccountNoList();
			if (null == accountNoList) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.ACCNOLSIT_EMPTY);
				return statusInfo;
			}

			if (!accountNoList.contains(bankInfo.getAccountNo())) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.ACCNOLSIT_EMPTY);
				return statusInfo;
			}

			String ipinDb = salesDao.retriveIPINForAccNo(bankInfo.getAccountNo());
			if (null == ipinDb) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.IPIN_EMPTY);
				return statusInfo;
			}

			if (!ipinDb.equals(bankInfo.getPassword())) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.INVALID_CREDENTIALS);
				return statusInfo;
			}

			double accountBalance = salesDao.retriveBalanceForAccount(bankInfo.getAccountNo());

			if (accountBalance <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.INSUFFICENT_FUNDS);
				return statusInfo;
			}
			if (accountBalance < cost) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.INSUFFICENT_FUNDS);
				return statusInfo;
			}

			double newBalance = accountBalance - cost;

			statusInfo = salesDao.updateBalance(bankInfo.getAccountNo(), newBalance);
			if (!statusInfo.isStatus()) {
				statusInfo.setErrMsg(GeneticConstants.Message.BALANCE_UPDATE_FAILED);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {

				// Creating the Order Detail Object
				OrderDetailsModel orderDetails = new OrderDetailsModel();
				orderDetails.setBookId(bankInfo.getBookId());
				orderDetails.setQuantity(bankInfo.getNoOfBooks());

				// Creating the Order Info Object

				OrderInfoDB orderInfoDB = new OrderInfoDB();
				orderInfoDB.setDate(new java.sql.Date(System.currentTimeMillis()));

				String emailFromLogin = salesDao.retriveEmailForUserId(bankInfo.getLoginId());
				orderInfoDB.setEmail(emailFromLogin);
				orderInfoDB.setLoginId(bankInfo.getLoginId());
				orderInfoDB.setTotalAmount(cost);

				statusInfo = salesDao.insertOrderInfo(orderInfoDB);

				statusInfo = salesDao.insertOrderDetails(orderDetails);

				if (!statusInfo.isStatus()) {
					statusInfo.setErrMsg(GeneticConstants.Message.INSERT_TRANS_FAILED);
					statusInfo.setStatus(false);
					return statusInfo;
				} else {
					statusInfo.setStatus(true);

					return statusInfo;

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
	}

	private List<Integer> readBookIdsForContentBased(String loginId) {

		// Map of Book ID and COunt of Book IDS
		List<Integer> recommendedBookIds = new ArrayList<Integer>();

		List<String> userIds = salesDao.retriveUsersFromSettings();
		if (null == userIds || userIds.isEmpty()) {
			return null;
		}

		if (!userIds.contains(loginId)) {
			return null;
		}

		// Now User Id is there in Settings

		int settings = salesDao.retriveSettingsForUser(loginId);
		if (settings <= 0) {
			return null;
		}

		// Now Get From the Transactions of Login Id

		List<Integer> distinctBookIds = salesDao.retriveDistinctsBooksFromOrderDetails(loginId);

		if (null == distinctBookIds || (distinctBookIds != null && distinctBookIds.isEmpty())) {
			return null;
		}

		for (Integer bookId : distinctBookIds) {

			int countId = salesDao.retriveBookCountForBookIdAndLoginId(bookId, loginId);

			if (countId >= settings) {
				recommendedBookIds.add(bookId);
			}
		}

		if (recommendedBookIds.isEmpty()) {
			return null;
		}
		return recommendedBookIds;
	}

	@Override
	public StatusInfo storeStatistics(Long startTime, Integer pageId, Long stopTime, int contentLength,
			String loginId) {

		StatusInfo statusInfo = new StatusInfo();
		try {

			long timeOfStay = stopTime - startTime;

			double timeOfStayInSeconds = (double) ((double) timeOfStay / (double) 1000);

			UserInfo userInfo = new UserInfo();
			userInfo.setLoginId(loginId);
			// Obtain the No of Advitisements for the User in the Adv Table

			userInfo.setNoOfBytes(contentLength);
			userInfo.setPageId(pageId);
			userInfo.setTimeOfStay(timeOfStayInSeconds);

			// INSERT

			StatusInfo statusInfo2 = salesDao.insertStatistics(userInfo);

			if (statusInfo2.isStatus()) {
				statusInfo.setStatus(true);
			} else {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.STATISTICS_INSERT_FAILED);
			}

		} catch (Exception e) {
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
		}
		return statusInfo;
	}

	@Override
	public List<UserInfo> viewStatistics() {

		List<UserInfo> userInfoList = null;
		try {
			userInfoList = new ArrayList<UserInfo>();

			List<String> userIds = salesDao.retriveDistinctUserIdsFromStats();

			if (userIds != null && !userIds.isEmpty()) {

				for (String userId : userIds) {
					// Page Ids for User Id
					List<Integer> pageIds = salesDao.retriveDistinctPageIdsFromStatsForUserId(userId);

					// Now get the User Info

					for (Integer pageIdTemp : pageIds) {
						UserInfo userInfo = salesDao.retriveTotalUserInfoForUserIdAndPageId(userId, pageIdTemp);
						int accessFreq = salesDao.retriveCountForPageIdAndUserId(userId, pageIdTemp);

						userInfo.setFrequency(accessFreq);

						userInfo.setLoginId(userId);
						userInfo.setPageId(pageIdTemp);

						userInfoList.add(userInfo);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Message" + e.getMessage());
		}

		return userInfoList;

	}

	@Override
	public String retriveAdvForUserIdAndPageId(String loginId, String pageId) {

		String adv = null;
		try {

			adv = salesDao.retriveAdvForUserIdAndPageId(loginId, pageId);

		} catch (Exception e) {
			return null;
		}
		return adv;

	}

	@Override
	public StatusInfo addBudget(BudgetVO budgetVO) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> userIdList = salesDao.retriveUserIdsFromBudget();

			if (null == userIdList || userIdList.isEmpty()) {
				statusInfo = salesDao.insertBudget(budgetVO);
				statusInfo.setStatus(true);
				return statusInfo;
			}

			if (userIdList.contains(budgetVO.getUserId())) {
				statusInfo = salesDao.updateBudget(budgetVO);
				statusInfo.setStatus(true);
				return statusInfo;
			} else {
				statusInfo = salesDao.insertBudget(budgetVO);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public MontlyBudget findBudgetForUser(String loginId, double costProduct) {
		MontlyBudget montlyBudget = new MontlyBudget();
		try {

			double totalAmountPurchase = salesDao.retriveTotalPurchase(loginId);

			if (totalAmountPurchase < 0) {
				montlyBudget.setStatus(false);
				return montlyBudget;
			}

			double actualUsed = totalAmountPurchase + costProduct;

			double budget = salesDao.retriveBudgetForLoginId(loginId);

			if (budget < 0) {
				montlyBudget.setStatus(false);
				return montlyBudget;
			}

			int value = Double.compare(actualUsed, budget);

			if (value == 1) {
				montlyBudget.setStatus(true);
				montlyBudget.setBudget(budget);
				montlyBudget.setUsedBudget(totalAmountPurchase);
				montlyBudget.setCostProduct(costProduct);
			}

		} catch (Exception e) {

			MontlyBudget montlyBudget2 = new MontlyBudget();
			montlyBudget.setStatus(false);
			return montlyBudget2;
		}
		return montlyBudget;
	}

	@Override
	public StatusInfo insertHabitatFileVO(HabitatFileVO habitatFileVO) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			String date = DateUtils.obtainCurrentDate();

			habitatFileVO.setDate(date);

			statusInfo = salesDao.insertHabitatFileVO(habitatFileVO);

		} catch (Exception e) {

			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());

			return statusInfo;

		}
		return statusInfo;

	}

	@Override
	public List<UserInfo> viewUsers() {

		List<UserInfo> userInfoList = null;
		try {

			userInfoList = salesDao.retriveUserList();

		} catch (Exception e) {
			return null;
		}
		return userInfoList;
	}

	@Override
	public StatusInfo applyLicense(LicenseInfoForUserUI license) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			statusInfo = salesDao.deleteOldLicenseInfo(license.getLoginId());

			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.DELETE_LICENSE_FAILED);
				return statusInfo;
			}

			List<LicenseInfoStoreBackend> licenseStoreBackendList = new ArrayList<LicenseInfoStoreBackend>();

			if (license.isSports()) {
				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.SPORTS);
				licenseInfoStoreBackend.setLicenseId(1);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isPolitics()) {

				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.POLITICS);
				licenseInfoStoreBackend.setLicenseId(2);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isFlimfare()) {

				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.FLIMFARE);
				licenseInfoStoreBackend.setLicenseId(3);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isAnalytics()) {

				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.ANALYTICS);
				licenseInfoStoreBackend.setLicenseId(4);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isProgramming()) {

				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.PROGRAMMING);
				licenseInfoStoreBackend.setLicenseId(5);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isGreetings()) {

				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.GREETINGS);
				licenseInfoStoreBackend.setLicenseId(6);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isSettings()) {

				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.SETTINGS);
				licenseInfoStoreBackend.setLicenseId(7);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isRankbooks()) {

				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.RANKBOOKS);
				licenseInfoStoreBackend.setLicenseId(8);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (license.isBudgetset()) {
				LicenseInfoStoreBackend licenseInfoStoreBackend = new LicenseInfoStoreBackend();
				licenseInfoStoreBackend.setUserId(license.getLoginId());
				licenseInfoStoreBackend.setLicenseName(GeneticConstants.LicenseType.BUDGETSET);
				licenseInfoStoreBackend.setLicenseId(9);
				licenseStoreBackendList.add(licenseInfoStoreBackend);
			}

			if (licenseStoreBackendList.isEmpty()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.PLEASE_SELECT_LICENSES);
			}

			statusInfo = salesDao.insertBlockLicensesForUser(licenseStoreBackendList);

			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.LICENSE_FAILED);
				return statusInfo;
			}

		} catch (Exception e) {

			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());

			return statusInfo;

		}
		statusInfo.setStatus(true);
		return statusInfo;
	}

	@Override
	public LicenseInfoForUserUI obtainLicenseInfoForCustomer(String userId) {

		LicenseInfoForUserUI licenseInfoForUserUI = null;
		try {

			List<String> backEndLicenseList = salesDao.retriveLicenseNamesForUser(userId);

			if (null == backEndLicenseList) {
				return null;
			}

			licenseInfoForUserUI = new LicenseInfoForUserUI();
			licenseInfoForUserUI.setLoginId(userId);

			for (String licenseName : backEndLicenseList) {
				if (GeneticConstants.LicenseType.ANALYTICS.equals(licenseName)) {
					licenseInfoForUserUI.setAnalytics(true);
				} else if (GeneticConstants.LicenseType.BUDGETSET.equals(licenseName)) {
					licenseInfoForUserUI.setBudgetset(true);
				} else if (GeneticConstants.LicenseType.FLIMFARE.equals(licenseName)) {
					licenseInfoForUserUI.setFlimfare(true);
				} else if (GeneticConstants.LicenseType.GREETINGS.equals(licenseName)) {
					licenseInfoForUserUI.setGreetings(true);
				} else if (GeneticConstants.LicenseType.POLITICS.equals(licenseName)) {
					licenseInfoForUserUI.setPolitics(true);
				} else if (GeneticConstants.LicenseType.PROGRAMMING.equals(licenseName)) {
					licenseInfoForUserUI.setProgramming(true);
				} else if (GeneticConstants.LicenseType.RANKBOOKS.equals(licenseName)) {
					licenseInfoForUserUI.setRankbooks(true);
				} else if (GeneticConstants.LicenseType.SETTINGS.equals(licenseName)) {
					licenseInfoForUserUI.setSettings(true);
				} else if (GeneticConstants.LicenseType.SPORTS.equals(licenseName)) {
					licenseInfoForUserUI.setSports(true);
				}
			}

		} catch (Exception e) {
			return null;
		}

		return licenseInfoForUserUI;

	}

	@Override
	public List<HabitatFileVO> viewSessionForUsers(String userId) {
		List<HabitatFileVO> userInfoList = null;
		try {

			userInfoList = salesDao.retriveSessionForUsers(userId);

		} catch (Exception e) {
			return null;
		}
		return userInfoList;
	}

	@Override
	public List<HabitatFileVO> viewHabitatFileForUserIdAndSessionId(String userId, String sessionId) {
		List<HabitatFileVO> userInfoList = null;
		try {

			userInfoList = salesDao.viewHabitatFileForUserIdAndSessionId(userId, sessionId);

		} catch (Exception e) {
			return null;
		}
		return userInfoList;
	}

	@Override
	public StatusInfo performKMeans(String loginId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusInfo doKMeans() {

		Long startTime = System.currentTimeMillis();
		StatusInfo statusInfo = new StatusInfo();
		try {

			statusInfo = salesDao.deleteFromKMeans();

			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg("Could not delete K Means");

				return statusInfo;
			}

			List<Integer> productIds = salesDao.retriveUniqueProductIdsFromApplication();

			List<Double> freqProductIdsList = new ArrayList<Double>();

			List<Double> noOfDaysList = new ArrayList<Double>();

			List<KMeansOutput> kMeansOutputs = new ArrayList<KMeansOutput>();

			if (productIds != null && !productIds.isEmpty()) {

				for (Integer productId : productIds) {

					double frequency = salesDao.retriveCountFromOrderDetailsForBookId(productId);

					freqProductIdsList.add(frequency);

					double noOfDays = salesDao.retriveNoOfDaysFromOrderDetailsForBookId(productId);

					noOfDaysList.add(noOfDays);

					KMeansOutput kMeansOutput = new KMeansOutput();

					kMeansOutput.setProductId(productId);
					kMeansOutput.setFrequency(frequency);
					kMeansOutput.setNoOfDays(noOfDays);

					kMeansOutputs.add(kMeansOutput);

				}

			}

			// Maximum Value of Frequency
			double frequencyMax = Collections.max(freqProductIdsList);

			double noOfDaysMax = Collections.max(noOfDaysList);

			double frequencyCenter1 = (double) frequencyMax / (double) 2;

			double noOfDaysCenter1 = (double) noOfDaysMax / (double) 2;

			double frequencyCenter2Temp = (double) frequencyMax / (double) 3;

			double noOfDaysCenter2Temp = (double) noOfDaysMax / (double) 3;
			
			double frequencyCenter2 = frequencyMax-frequencyCenter2Temp;
			
			double noOfDaysCenter2 =noOfDaysMax-noOfDaysCenter2Temp;

			double frequencyCenter3Temp = (double) frequencyMax / (double) 5;

			double noOfDaysCenter3Temp = (double) noOfDaysMax / (double) 5;

			double frequencyCenter3 = frequencyMax - frequencyCenter3Temp;

			double noOfDaysCenter3 = noOfDaysMax - noOfDaysCenter3Temp;

			// Now Find the
			FormulaUtil formulaUtil = new FormulaUtil();

			List<KMeansExtendedOutput> kMeansExtendedOutputs = new ArrayList<KMeansExtendedOutput>();

			for (KMeansOutput kMeansOutput : kMeansOutputs) {

				double freq = kMeansOutput.getFrequency();

				double noOfDays = kMeansOutput.getNoOfDays();

				double distanceCluster1 = formulaUtil.computeDistance(frequencyCenter1, freq, noOfDays,
						noOfDaysCenter1);

				double distanceCluster2 = formulaUtil.computeDistance(frequencyCenter2, freq, noOfDays,
						noOfDaysCenter2);

				double distanceCluster3 = formulaUtil.computeDistance(frequencyCenter3, freq, noOfDays,
						noOfDaysCenter3);

				Map<Double, Integer> distanceClusterNo = new HashMap<Double, Integer>();

				distanceClusterNo.put(distanceCluster1, 1);
				distanceClusterNo.put(distanceCluster2, 2);
				distanceClusterNo.put(distanceCluster3, 3);

				List<Double> listDistance = new ArrayList<Double>();

				listDistance.add(distanceCluster1);
				listDistance.add(distanceCluster2);
				listDistance.add(distanceCluster3);

				double distanceMin = Collections.min(listDistance);

				int clusterNo = distanceClusterNo.get(distanceMin);

				KMeansExtendedOutput kMeansExtendedOutput = new KMeansExtendedOutput();

				kMeansExtendedOutput.setClusterNo(clusterNo);
				kMeansExtendedOutput.setDistanceC1(distanceCluster1);
				kMeansExtendedOutput.setDistanceC2(distanceCluster2);
				kMeansExtendedOutput.setDistanceC3(distanceCluster3);
				kMeansExtendedOutput.setFrequency(freq);
				kMeansExtendedOutput.setMinDistance(distanceMin);
				kMeansExtendedOutput.setProductId(kMeansOutput.getProductId());
				kMeansExtendedOutput.setNoOfDays(noOfDays);

				kMeansExtendedOutputs.add(kMeansExtendedOutput);

			}

			if (kMeansExtendedOutputs != null && !kMeansExtendedOutputs.isEmpty()) {

				statusInfo = salesDao.insertBlockKMeansExtendOutput(kMeansExtendedOutputs);

				if (!statusInfo.isStatus()) {
					statusInfo.setStatus(false);
					statusInfo.setErrMsg("Could not Insert the K Means Extended Output");
					return statusInfo;
				}

			}

			Long stopTime = System.currentTimeMillis();

			Long timeDiff = stopTime - startTime;

			double timeTaken = (double) timeDiff / (double) 1000;
			System.out.println("=====================================");
			System.out.println("Time Taken K MEANS" + timeTaken);
		} catch (Exception e) {

			statusInfo.setStatus(false);
			statusInfo.setErrMsg("Could not Execute K Means due to Some Critical Condition");
			return statusInfo;
		}

		statusInfo.setStatus(true);
		return statusInfo;
	}

	@Override
	public StatusInfo viewKMeansClustering(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = null;
		try {

			statusInfo = salesDao.viewKMeansClustering(paginationConfigVO);

			List<KMeansExtendedOutput> kMeansExtendedOutputList = (List<KMeansExtendedOutput>) statusInfo.getModel();
			if (kMeansExtendedOutputList != null && !kMeansExtendedOutputList.isEmpty()) {

				for (KMeansExtendedOutput kMeansExtendedOutput : kMeansExtendedOutputList) {

					BookPackModel booKPackModel = salesDao
							.retriveBookDetailsForBookId(kMeansExtendedOutput.getProductId());

					String productName = booKPackModel.getBookName();

					kMeansExtendedOutput.setProductName(productName);

				}
			}
			statusInfo.setModel(kMeansExtendedOutputList);

		} catch (Exception e) {
			return null;
		}
		return statusInfo;
	}

	@Override
	public List<ClassifyOutput> retriveClassification() {

		List<ClassifyOutput> classifyOutputList = new ArrayList<ClassifyOutput>();
		try {

			int countCluster1 = salesDao.retriveCountForClusterNo(1);

			ClassifyOutput classifyOutput = new ClassifyOutput();

			if (countCluster1 > 0) {
				classifyOutput.setCatName("Cluster1");
				classifyOutput.setNoOfProducts(countCluster1);

				classifyOutputList.add(classifyOutput);

			}
			int countCluster2 = salesDao.retriveCountForClusterNo(2);

			if (countCluster2 > 0) {

				classifyOutput = new ClassifyOutput();

				classifyOutput.setCatName("Cluster2");
				classifyOutput.setNoOfProducts(countCluster2);

				classifyOutputList.add(classifyOutput);

			}
			int countCluster3 = salesDao.retriveCountForClusterNo(3);

			if (countCluster3 > 0) {

				classifyOutput = new ClassifyOutput();

				classifyOutput.setCatName("Cluster3");
				classifyOutput.setNoOfProducts(countCluster3);

				classifyOutputList.add(classifyOutput);
			}

		} catch (Exception e) {

			return null;
		}

		return classifyOutputList;

	}

	@Override
	public StatusInfo performRFM() {

		Long startTime = System.currentTimeMillis();

		StatusInfo statusInfo = new StatusInfo();
		try {

			statusInfo = salesDao.deleteFromRFM();

			if (!statusInfo.isStatus()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.COULD_NOT_REMOVE_OLD_RFM);
				return statusInfo;
			}

			List<String> uniqueCustomerIds = salesDao.retriveDistinctUserIdsFromLoginForLoginType(1);

			List<RFMModelVO> rfmModelList = new ArrayList<RFMModelVO>();

			if (uniqueCustomerIds != null && !uniqueCustomerIds.isEmpty()) {

				for (String userId : uniqueCustomerIds) {

					System.out.println("Doing for User Id  =" + userId);

					// Compute the Frequency of Purchase
					double count = salesDao.retriveFrequencyOfPurchaseForUser(userId);

					if (count <= 0) {
						count = 0;
					}

					// Compute the Monitory of purchase
					double monetory = salesDao.retriveMonitoryOfPurchaseForUser(userId);

					if (monetory <= 0) {
						monetory = 0;
					}

					// Compute the Recency of the Customer

					double recency = salesDao.retriveRecencyForUser(userId);

					if (recency <= 0) {
						recency = 0;
					}

					RFMModelVO rfmModel = new RFMModelVO();

					rfmModel.setFrequency(count);
					rfmModel.setMonetory(monetory);
					rfmModel.setRecency(recency);
					rfmModel.setUserId(userId);
					rfmModelList.add(rfmModel);

				}

			}

			//
			if (rfmModelList != null && !rfmModelList.isEmpty()) {

				statusInfo = salesDao.insertBlockRFMModel(rfmModelList);

				if (!statusInfo.isStatus()) {
					statusInfo.setStatus(false);
					statusInfo.setErrMsg("Could not save RFM Model");

					return statusInfo;
				}

				// Now Do the Sorting and Get the RFM Values into the Temp
				// Object

				List<String> userIdSorted = new ArrayList<String>();

				// Frequency Weight Values
				userIdSorted = salesDao.sortBasedOnFrequencyRFM();

				for (RFMModelVO rfmModel : rfmModelList) {

					String userId = rfmModel.getUserId();
					int count = 1;
					for (String userIdTemp : userIdSorted) {

						if (userId.equals(userIdTemp)) {
							rfmModel.setFreqWeight(count);
							break;
						}
						count = count + 1;
					}

				}

				// Sorting the Recency and Assigning the weight

				userIdSorted = new ArrayList<String>();

				// Recency Weight Values
				userIdSorted = salesDao.sortBasedOnRecencyRFM();

				for (RFMModelVO rfmModel : rfmModelList) {

					String userId = rfmModel.getUserId();
					int count = 1;
					for (String userIdTemp : userIdSorted) {

						if (userId.equals(userIdTemp)) {
							rfmModel.setRecencyWeight(count);
							break;
						}
						count = count + 1;
					}

				}

				// Sorting the Recency and Assigning the weight

				userIdSorted = new ArrayList<String>();

				// Recency Weight Values
				userIdSorted = salesDao.sortBasedOnMonetoryRFM();

				for (RFMModelVO rfmModel : rfmModelList) {

					String userId = rfmModel.getUserId();
					int count = 1;
					for (String userIdTemp : userIdSorted) {

						if (userId.equals(userIdTemp)) {
							rfmModel.setMonetoryWeight(count);
							break;
						}
						count = count + 1;
					}

				}
			}

			if (rfmModelList != null && !rfmModelList.isEmpty()) {

				for (RFMModelVO rfmModelVO : rfmModelList) {

					StringBuffer buffer = new StringBuffer();

					Double recencyW = rfmModelVO.getRecencyWeight();

					int recencyWInt = recencyW.intValue();

					buffer.append(recencyWInt);

					Double freqW = rfmModelVO.getFreqWeight();

					int freqWInt = freqW.intValue();

					buffer.append(freqWInt);

					Double monitoryW = rfmModelVO.getMonetoryWeight();

					int monitoryInt = monitoryW.intValue();

					buffer.append(monitoryInt);

					Double rfm = new Double(buffer.toString());

					rfmModelVO.setRfmWeight(rfm);

				}

			}

			// Delete from RFM Weight
			statusInfo = salesDao.deleteFromRFMWeightModel();

			if (!statusInfo.isStatus()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.RFM_WEIGHT_DELETE_ERROR);
				return statusInfo;
			}

			// Insertion of RFM Weight
			statusInfo = salesDao.insertBlockRFMWeightModel(rfmModelList);

			if (!statusInfo.isStatus()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.RFM_WEIGHT_SAVE_ERROR);
				return statusInfo;
			}

			Long endTime = System.currentTimeMillis();

			double timeT = endTime - startTime;

			double time = (double) timeT / 1000;

			System.out.println("Time Taken for RFM Execution =" + time);

		} catch (Exception e) {

			statusInfo.setStatus(false);
			statusInfo.setErrMsg(GeneticConstants.Message.RFM_CRITICAL_ERROR);

			return statusInfo;
		}
		statusInfo.setStatus(true);

		return statusInfo;
	}

	@Override
	public StatusInfo viewRFMClustering(PaginationConfigVO paginationConfigVO) {
		StatusInfo rfmClusteringOutputList = null;
		try {

			rfmClusteringOutputList = salesDao.viewRFMClustering(paginationConfigVO);

		} catch (Exception e) {
			return null;
		}
		return rfmClusteringOutputList;
	}

	@Override
	public StatusInfo classifyCustomer() {

		StatusInfo statusInfo = new StatusInfo();
		try {

			statusInfo = salesDao.deleteClassifyRFMInfo();

			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.DELETE_CLASSIFY_RFM_FAILED);
				return statusInfo;
			}

			List<RFMModelVO> rfmModelVO = salesDao.retriveRFMClusteringOrderBy();

			if (null == rfmModelVO) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.RFM_EMPTY);
				return statusInfo;
			}

			int count = rfmModelVO.size();

			Double threshold1 = (double) count / (double) 3;

			int threshold1Int = threshold1.intValue();

			Double threshold2 = (double) count / (double) 2;

			int threshold2Int = threshold2.intValue();

			List<ClassifyCustomerVO> classifyCustomerVOList = new ArrayList<ClassifyCustomerVO>();

			if (rfmModelVO != null && !rfmModelVO.isEmpty()) {

				int index = 1;
				for (RFMModelVO rfmModelVO2 : rfmModelVO) {

					if (index <= threshold1Int) {

						ClassifyCustomerVO classifyCustomerVO = new ClassifyCustomerVO();

						classifyCustomerVO.setCustomer(rfmModelVO2.getUserId());
						classifyCustomerVO.setClusterNo(1);
						classifyCustomerVO.setRfm(rfmModelVO2.getRfmWeight());
						classifyCustomerVO.setClusterName(GeneticConstants.Keys.HIGHLY_LOYAL_CUST);

						classifyCustomerVOList.add(classifyCustomerVO);
					}

					if (index > threshold1Int && index <= threshold2Int) {

						ClassifyCustomerVO classifyCustomerVO = new ClassifyCustomerVO();

						classifyCustomerVO.setCustomer(rfmModelVO2.getUserId());
						classifyCustomerVO.setClusterNo(2);
						classifyCustomerVO.setRfm(rfmModelVO2.getRfmWeight());
						classifyCustomerVO.setClusterName(GeneticConstants.Keys.MEDIUM_LOYAL_CUST);

						classifyCustomerVOList.add(classifyCustomerVO);
					}

					if (index > threshold2Int) {

						ClassifyCustomerVO classifyCustomerVO = new ClassifyCustomerVO();

						classifyCustomerVO.setCustomer(rfmModelVO2.getUserId());
						classifyCustomerVO.setClusterNo(3);
						classifyCustomerVO.setRfm(rfmModelVO2.getRfmWeight());
						classifyCustomerVO.setClusterName(GeneticConstants.Keys.LOW_LOYAL_CUST);

						classifyCustomerVOList.add(classifyCustomerVO);
					}

					index = index + 1;

				}

				// Store the Customer Classification Information

				statusInfo = salesDao.insertBlockCustomerClassification(classifyCustomerVOList);

				if (!statusInfo.isStatus()) {

					statusInfo.setStatus(false);
					statusInfo.setErrMsg(GeneticConstants.Message.SAVE_CLASSIFICATION_FAILED);
					return statusInfo;
				}

			}

		} catch (Exception e) {
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(GeneticConstants.Message.CLASSIFICATION_CUSTOMER_FAILED);
			return statusInfo;
		}
		statusInfo.setStatus(true);
		return statusInfo;

	}

	@Override
	public List<ClassifyCustomerOutput> viewRFMClassifierGraphs() {
		List<ClassifyCustomerOutput> classifyOutputList = new ArrayList<ClassifyCustomerOutput>();
		try {

			int countCluster1 = salesDao.retriveCountForClusterNoForRFM(1);

			ClassifyCustomerOutput classifyOutput = new ClassifyCustomerOutput();

			if (countCluster1 > 0) {
				classifyOutput.setCatName(GeneticConstants.Keys.HIGHLY_LOYAL_CUST);
				classifyOutput.setNoOfUsers(countCluster1);

				classifyOutputList.add(classifyOutput);

			}
			int countCluster2 = salesDao.retriveCountForClusterNoForRFM(2);

			if (countCluster2 > 0) {

				classifyOutput = new ClassifyCustomerOutput();

				classifyOutput.setCatName(GeneticConstants.Keys.MEDIUM_LOYAL_CUST);
				classifyOutput.setNoOfUsers(countCluster2);

				classifyOutputList.add(classifyOutput);

			}
			int countCluster3 = salesDao.retriveCountForClusterNoForRFM(3);

			if (countCluster3 > 0) {

				classifyOutput = new ClassifyCustomerOutput();

				classifyOutput.setCatName(GeneticConstants.Keys.HIGHLY_LOYAL_CUST);
				classifyOutput.setNoOfUsers(countCluster3);

				classifyOutputList.add(classifyOutput);
			}

		} catch (Exception e) {

			return null;
		}

		return classifyOutputList;
	}

	@Override
	public List<ClassifyCustomerVO> viewClassifyCustomer() {
		List<ClassifyCustomerVO> classifyCustomerOutputList = null;
		try {

			classifyCustomerOutputList = salesDao.viewClassifyCustomer();

		} catch (Exception e) {
			return null;
		}
		return classifyCustomerOutputList;
	}

	@Override
	public StatusInfo retriveBooksForCategoryAdvCategory(String loginId) {
		StatusInfo statusInfo = new StatusInfo();
		try {

			int clusterNoUserBelong = salesDao.retriveClusterNoForUser(loginId);

			if (clusterNoUserBelong < 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.NO_CLUSTER_FOUND_ADMIN_SHOULD_EXECUTE);
				return statusInfo;
			}

			if (clusterNoUserBelong == 3) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(GeneticConstants.Message.CLUSTER_3_FOUND);
				return statusInfo;
			}

			if (clusterNoUserBelong == 1) {

				List<Integer> productIds = salesDao
						.retriveDistinctProductIdsForClusterNo(GeneticConstants.Keys.MEDIUM_STOCK);

				if (null == productIds) {

					statusInfo.setStatus(false);
					statusInfo.setErrMsg(GeneticConstants.Message.NO_PRODUCTS_FOUND_OF_MEDIUM_STOCK);
					return statusInfo;
				}

				List<BookPackModel> bookPackModelList = salesDao.retriveBooksForProduct(productIds);

				if (null == bookPackModelList) {
					statusInfo.setStatus(false);
					statusInfo.setErrMsg(GeneticConstants.Message.NO_PRODUCTS_FOUND_OF_MEDIUM_STOCK);
					return statusInfo;
				}

				FullBookModelForUserId fullBookModelForUserId = new FullBookModelForUserId();
				fullBookModelForUserId.setBooksList(bookPackModelList);

				statusInfo.setStatus(true);
				statusInfo.setModel(fullBookModelForUserId);

				return statusInfo;
			}

			if (clusterNoUserBelong == 2) {

				List<Integer> productIds = salesDao
						.retriveDistinctProductIdsForClusterNo(GeneticConstants.Keys.LOW_STOCK);

				if (null == productIds) {

					statusInfo.setStatus(false);
					statusInfo.setErrMsg(GeneticConstants.Message.NO_PRODUCTS_FOUND_OF_LOW_STOCK);
					return statusInfo;
				}

				List<BookPackModel> bookPackModelList = salesDao.retriveBooksForProduct(productIds);

				if (null == bookPackModelList) {
					statusInfo.setStatus(false);
					statusInfo.setErrMsg(GeneticConstants.Message.NO_PRODUCTS_FOUND_OF_LOW_STOCK);
					return statusInfo;
				}

				FullBookModelForUserId fullBookModelForUserId = new FullBookModelForUserId();
				fullBookModelForUserId.setBooksList(bookPackModelList);

				statusInfo.setStatus(true);
				statusInfo.setModel(fullBookModelForUserId);

				return statusInfo;

			}

		} catch (Exception e) {

			statusInfo.setStatus(false);
			statusInfo.setErrMsg(GeneticConstants.Message.CRTICAL_ERROR_BOOKS);

		}
		statusInfo.setStatus(true);

		return statusInfo;
	}

	@Override
	public StatusInfo viewClassifyCustomer(PaginationConfigVO paginationConfigVO) {
		StatusInfo classifyCustomerOutputList = null;
		try {

			classifyCustomerOutputList = salesDao.viewClassifyCustomer(paginationConfigVO);

		} catch (Exception e) {
			return null;
		}
		return classifyCustomerOutputList;
	}
}

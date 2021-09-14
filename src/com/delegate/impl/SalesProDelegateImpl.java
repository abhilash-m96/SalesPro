package com.delegate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delegate.inter.SalesProDelegateIF;
import com.model.AdvitisementModel;
import com.model.AssociationRuleMiningResults;
import com.model.BankInfo;
import com.model.BankModel;
import com.model.BookPackModel;
import com.model.BookRating;
import com.model.BudgetVO;
import com.model.ClassifyCustomerOutput;
import com.model.ClassifyCustomerVO;
import com.model.ClassifyOutput;
import com.model.CostConstantsModel;
import com.model.HabitatFileVO;
import com.model.KMeansExtendedOutput;
import com.model.LicenseInfoForUserUI;
import com.model.MontlyBudget;
import com.model.PaginationConfigVO;
import com.model.RFMModelVO;
import com.model.RegisterUser;
import com.model.StatusInfo;
import com.model.UserInfo;
import com.service.inter.SalesProServiceIF;

public class SalesProDelegateImpl implements SalesProDelegateIF {

	@Autowired
	private SalesProServiceIF salesService;

	public SalesProServiceIF getSalesService() {
		return salesService;
	}

	public void setSalesService(SalesProServiceIF salesService) {
		this.salesService = salesService;
	}

	@Override
	public StatusInfo doRegistration(RegisterUser registerUser) {
		return salesService.doRegistration(registerUser);
	}

	@Override
	public StatusInfo checkLogin(RegisterUser registerUser) {
		return salesService.checkLogin(registerUser);
	}

	@Override
	public StatusInfo completePackageTransaction(BankModel bankModel) {
		return salesService.completePackageTransaction(bankModel);
	}

	@Override
	public List<BookPackModel> retriveAllBooksPack() {
		return salesService.retriveAllBooksPack();
	}

	@Override
	public List<BookPackModel> retriveBooksForCategory(int catId) {
		return salesService.retriveBooksForCategory(catId);
	}

	@Override
	public StatusInfo completeBuyingTransaction(BankInfo bankInfo) {
		return salesService.completeBuyingTransaction(bankInfo);
	}

	@Override
	public StatusInfo storeStatistics(Long startTime, Integer pageId, Long stopTime, int contentLength,
			String loginId) {
		return salesService.storeStatistics(startTime, pageId, stopTime, contentLength, loginId);
	}

	@Override
	public List<UserInfo> viewStatistics() {
		return salesService.viewStatistics();
	}

	@Override
	public String retriveAdvForUserIdAndPageId(String loginId, String pageId) {
		return salesService.retriveAdvForUserIdAndPageId(loginId, pageId);
	}

	@Override
	public StatusInfo addBudget(BudgetVO budgetVO) {
		return salesService.addBudget(budgetVO);
	}

	@Override
	public MontlyBudget findBudgetForUser(String loginId, double bookPrice) {
		return salesService.findBudgetForUser(loginId, bookPrice);
	}

	public StatusInfo insertHabitatFileVO(HabitatFileVO habitatFileVO) {
		return salesService.insertHabitatFileVO(habitatFileVO);
	}

	@Override
	public List<UserInfo> viewUsers() {
		return salesService.viewUsers();
	}

	@Override
	public StatusInfo applyLicense(LicenseInfoForUserUI license) {
		return salesService.applyLicense(license);
	}

	@Override
	public LicenseInfoForUserUI obtainLicenseInfoForCustomer(String userId) {
		return salesService.obtainLicenseInfoForCustomer(userId);
	}

	@Override
	public List<HabitatFileVO> viewSessionForUsers(String userId) {
		return salesService.viewSessionForUsers(userId);
	}

	@Override
	public List<HabitatFileVO> viewHabitatFileForUserIdAndSessionId(String userId, String sessionId) {
		return salesService.viewHabitatFileForUserIdAndSessionId(userId, sessionId);
	}

	@Override
	public StatusInfo doKMeans() {
		return salesService.doKMeans();
	}

	@Override
	public StatusInfo viewKMeansClustering(PaginationConfigVO paginationConfigVO) {
		return salesService.viewKMeansClustering(paginationConfigVO);
	}

	@Override
	public List<ClassifyOutput> retriveClassification() {
		return salesService.retriveClassification();
	}

	@Override
	public StatusInfo performRFM() {
		return salesService.performRFM();
	}

	@Override
	public StatusInfo viewRFMClustering(PaginationConfigVO paginationConfigVO) {
		return salesService.viewRFMClustering(paginationConfigVO);
	}

	@Override
	public StatusInfo classifyCustomer() {
		return salesService.classifyCustomer();
	}

	@Override
	public List<ClassifyCustomerOutput> viewRFMClassifierGraphs() {
		return salesService.viewRFMClassifierGraphs();
	}

	@Override
	public StatusInfo viewClassifyCustomer(PaginationConfigVO paginationConfigVO) {
		return salesService.viewClassifyCustomer(paginationConfigVO);
	}

	@Override
	public StatusInfo retriveBooksForCategoryAdvCategory(String loginId) {
		return salesService.retriveBooksForCategoryAdvCategory(loginId);
	}

}

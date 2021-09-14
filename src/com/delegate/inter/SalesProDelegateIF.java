package com.delegate.inter;

import java.util.List;

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
import com.model.CrossOverObj;
import com.model.HabitatFileVO;
import com.model.KMeansExtendedOutput;
import com.model.LicenseInfoForUserUI;
import com.model.MontlyBudget;
import com.model.PaginationConfigVO;
import com.model.RFMModelVO;
import com.model.RegisterUser;
import com.model.StatusInfo;
import com.model.UserInfo;
import com.model.repobased.License;

public interface SalesProDelegateIF {

	public StatusInfo doRegistration(RegisterUser registerUser);

	public StatusInfo checkLogin(RegisterUser registerUser);

	public StatusInfo completePackageTransaction(BankModel bankModel);

	public List<BookPackModel> retriveAllBooksPack();

	public List<BookPackModel> retriveBooksForCategory(int catId);

	public StatusInfo completeBuyingTransaction(BankInfo bankInfo);

	StatusInfo storeStatistics(Long startTime, Integer pageId, Long stopTime, int contentLength, String loginId);

	public List<UserInfo> viewStatistics();

	public String retriveAdvForUserIdAndPageId(String loginId, String id);

	public StatusInfo addBudget(BudgetVO budgetVO);

	public MontlyBudget findBudgetForUser(String loginId, double bookPrice);

	public StatusInfo insertHabitatFileVO(HabitatFileVO habitatFileVO);

	public List<UserInfo> viewUsers();

	public StatusInfo applyLicense(LicenseInfoForUserUI license);

	public LicenseInfoForUserUI obtainLicenseInfoForCustomer(String userId);

	public List<HabitatFileVO> viewSessionForUsers(String userId);

	public List<HabitatFileVO> viewHabitatFileForUserIdAndSessionId(String userId, String sessionId);

	public StatusInfo doKMeans();

	public StatusInfo viewKMeansClustering(PaginationConfigVO paginationConfigVO);

	public List<ClassifyOutput> retriveClassification();

	public StatusInfo performRFM();

	public StatusInfo viewRFMClustering(PaginationConfigVO paginationConfigVO);

	public StatusInfo classifyCustomer();
 
	public List<ClassifyCustomerOutput> viewRFMClassifierGraphs();

	public StatusInfo viewClassifyCustomer(PaginationConfigVO paginationConfigVO);

	public StatusInfo retriveBooksForCategoryAdvCategory(String loginId);

}

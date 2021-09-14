package com.constants;

public interface GeneticConstants {

	interface Session {

		public static final String ORDERINFO_SESSION_KEY = "ORDERINFO_SESSION";

	}

	interface DatabaseColumns {

		public static final String PKEYID_COL = "PKEYID";

		public static final String CUSTNAME_COL = "CUSTNAME";

		public static final String HISTORYID_COL = "HISTORYID";

		public static final String RATING_COL = "RATING";

		public static final String DATE_COL = "DATE";

		public static final String BOOKID_COL = "BOOKID";

		public static final String BOOKNAME_COL = "BOOKNAME";

		public static final String CATID_COL = "CATID";

		public static final String SUBCATID_COL = "SUBCATID";

		public static final String AUTHOR_COL = "AUTHOR";

		public static final String PUBLISHER_COL = "PUBLISHER";

		public static final String BOOKPRICE_COL = "BOOKPRICE";

		public static final String BOOKOVERVIEW_COL = "BOOKOVERVIEW";

		public static final String EDITION_COL = "EDITION";

		public static final String BOOKLOC_COL = "BOOKLOC";

		public static final String SESSIONAPPID_COL = "SESSIONAPPID";

		public static final String ACTIONNAME_COL = "ACTIONNAME";

		public static final String ACTIONTYPE_COL = "ACTIONTYPE";

		public static final String IPADDRESS_COL = "IPADDRESS";

		public static final String USERNAME_COL = "USERNAME";

		public static final String LICENSENAME_COL = "LICENSENAME";

		public static final String SESSIONID_COL = "SESSIONID";

		public static final String TIMEOFACTION_COL = "TIMEOFACTION";

	}

	interface Message {

		public static final String INTERNAL_ERROR = "Please Contact System Adminitrator. An Internal Error has Ocuurred";
		public static final String FIRSTNAME_EMPTY = "First Name cannot be Empty";
		public static final String LASTNAME_EMPTY = "Last Name cannot be Empty";
		public static final String USERID_EMPTY = "User ID Cannot be Empty";
		public static final String EMAIL_EMPTY = "Email Cannot be Empty";
		public static final String PASSWORD_EMPTY = "Password Cannot be Empty";
		public static final String USERREGISTERED_SUCESS_MSG = "User Has Registered Sucessfully";
		public static final String USERALREADY_EXIST = "User Already Exist";
		public static final String NO_USER_EXISTS = "No User Already Exist";
		public static final String PASSWORD_WRONG = "Password does not exist";
		public static final String USER_LOGIN_SUCESS = "User Login is Sucessful";
		public static final String ACCNO_EMPTY = "Account No Cannot be Empty";
		public static final String IPIN_EMPTY = "IPIN Cannot be Empty";
		public static final String ACCNOLSIT_EMPTY = "Account No Not Found/List is Empty";
		public static final String INVALID_CREDENTIALS = "Credentails were Invalid";
		public static final String INSUFFICENT_FUNDS = "Insufficient Funds";
		public static final String BALANCE_UPDATE_FAILED = "Balance Could not be Updated";
		public static final String INSERT_TRANS_FAILED = "Transaction Insertion has Failed";
		public static final String EMPTY_REVIEWSLIST = "Reviews List is Empty";
		public static final String REVIEWS_FETCH_SUCESS = "Reviews Fetched Sucessfully";
		public static final String RANKING_FAILED_BOOKS = "Ranking of Books have Failed";
		public static final String RANK_POLARITY_SUCESS = "Ranking with Respect to Polarity is Sucessful";
		public static final String FREQUENCYLIST_EMPTY = "Frequency List is Empty";
		public static final String SEARCH_EMPTY = "Search Criteria is Empty";
		public static final String COULD_NOT_RANK = "Could not Rank at this Point of time";
		public static final String DELETE_FV = "Could not Delete Feature Vector";
		public static final String DIRECTRATIING_APPLIED_SUCESS = "Direct Rating has been Applied Sucessfully";
		public static final String CUSTNAME_EMPTY = "Customer Name Cannot be Empy Please Reenter";
		public static final String RATING_EMPTY = "Rating Cannot be Empty Please Reenter";
		public static final String HISTORYRATING_LIST_EMPTY = "History Rating List is Empty";
		public static final String CITYMODEL_LIST_EMPTY = "City List is Empty";
		public static final String CITY_ID_EMPTY = "City Id is Empty";
		public static final String CITY_EMPTYMESSAGE = "City  Cannot be Empty.Please Select the City";
		public static final String DATE_EMPTYMESSAGE = "Date Cannot be Empty";
		public static final String CUSTNAME_EMPTYMESSAGE = "Customer Name Cannot be Empty From Session";
		public static final String CUSTNAME_EMPTYSESSION = "Customer Name Session is Empty";
		public static final String EMPTY_STATUSINFO = "Could not Remove the Schdule of the Customer";
		public static final String DELETE_DATA_SUCESS = "Deletion of the Schedule is Sucessful for the Customer";
		public static final String BOOKPACKLIST_EMPTY = "Book Pack List Cannot be Empty";
		public static final String BOOKID_EMPTY = "Book ID cannot be Empty";
		public static final String BANK_ACNO_CORRECTED = "Bank Account No Cannot be Empty";
		public static final String NOOFBOOKS_EMPTY = "Number of Books are Empty";
		public static final String BOOKPRICE_EMPTY = "Book Price Cannot be Empty";
		public static final String SESSION_INVALID = "Your Session has Expired";
		public static final String ACCOUNTNO_EMPTY = "Account Number cannot be Empty";
		public static final String THRESHOLD_INVALID = "Threshold Settings for Content Mining Cannot be Empty";
		public static final String NO_BOOKS_FOUND_FOR_CONTENT_BASED = "No Books Found which satifies the content based Ranking";
		public static final String INTERNAL_ERROR_OCCURED = "Internal Error Occurred.Please Contant System Administrator";
		public static final String STATISTICS_INSERT_FAILED = "Insertion of Statistics Failed ";
		public static final String VIEWSTATS_LIST_EMPTY = "Statistics are Empty";
		public static final String GENETICS_EMPTY = "Genetics Empty";
		public static final String ADVITISEMENT_EMPTY = "Advitisements cannot be EMpty";
		public static final String ADS_COULD_NOT_CLEAN = "Could not Clean up Old Advitisements";
		public static final String ADS_COULD_NOT_INSERT = "Could not Store the Advistisement";
		public static final String ADS_COULD_NOT_UPDATE = "Could not Update the Advitisements for the User Id";
		public static final String ADDRESS_EMPTY_ERR = "Address Cannot be Empty";
		public static final String BUDGET_EMPTY = "Budget Cannot be Empty";
		public static final String EMAIL_INCORRECT_FORMAT = "Email is in Incorrect Format";
		public static final String APPLY_LICENSE_SUCESS = "License has been Applied Sucessfully";
		public static final String DELETE_LICENSE_FAILED = "Could not Update License Information because of Old Licenses";
		public static final String PLEASE_SELECT_LICENSES = "Please Select the License Information";
		public static final String LICENSE_FAILED = "Failed to Apply License for Users";
		public static final String KMEANS_EXECUTION_SUCESSFUL = "Execution of Kmeans is Sucessful";
		public static final String KMEANS_LIST_EMPTY = "K Means List is Empty";
		public static final String RFM_EXECUTION_SUCESSFUL = "RFM Execution is Sucessful";
		public static final String RFM_CRITICAL_ERROR = "Critical Error has been Performed";
		public static final String COULD_NOT_REMOVE_OLD_RFM = "Could not Remove Old RFM";
		public static final String RFM_WEIGHT_DELETE_ERROR = "Could not delete Old RFM Weight Computation";
		public static final String RFM_WEIGHT_SAVE_ERROR = "RFM Weight could not be saved";
		public static final String RFM_LIST_EMPTY = "RFM List is Empty";
		public static final String CLASSIFY_CUSTOMER_EXECUTION_SUCESSFUL = "Classification of Customer has Sucessful";
		public static final String CLASSIFICATION_CUSTOMER_FAILED = "Classification of Customer has Failed";
		public static final String RFM_EMPTY = "RFM List is Empty";
		public static final String SAVE_CLASSIFICATION_FAILED = "Saving of Classification has Failed";
		public static final String DELETE_CLASSIFY_RFM_FAILED = "Delete Classification of RFM has Failed";
		public static final String CUSTOMER_GRAPH_LIST_EMPTY = "Customer Graph Data Cannot be Obtained";
		public static final String RFMCLUSTER__LIST_EMPTY = "RFM Cluster List is Empty";
		public static final String CRTICAL_ERROR_BOOKS = "Critical Error during Books Recommendations based on Stock Data";
		public static final String NO_CLUSTER_FOUND_ADMIN_SHOULD_EXECUTE = "No Cluster Found for Admin to Execute";
		public static final String CLUSTER_3_FOUND = "User Belongs to Cluster 3 Cannot Find Advitisements";
		public static final String NO_PRODUCTS_FOUND_OF_MEDIUM_STOCK = "No Products related to medium stock have been found";
		public static final String NO_PRODUCTS_FOUND_OF_LOW_STOCK = "No Products related to low stock";

	}

	interface DATABASESQL {
		public static final String RETRIVE_REGISTER_USERIDS_SQL = "RETRIVE_REGISTER_USERIDS_SQL";
		public static final String INSERT_LOGIN_SQL = "INSERT_LOGIN_SQL";
		public static final String RETRIVE_PASSWORD_WHERE_USERID_SQL = "RETRIVE_PASSWORD_WHERE_USERID_SQL";
		public static final String RETRIVE_LOGINTYPE_WHERE_USERID_SQL = "RETRIVE_LOGINTYPE_WHERE_USERID_SQL";
		public static final String RETRIVE_ACCCOUNTNOS_SQL = "RETRIVE_ACCCOUNTNOS_SQL";
		public static final String RETRIVE_IPIN_WHERE_ACCNO_SQL = "RETRIVE_IPIN_WHERE_ACCNO_SQL";
		public static final String RETRIVE_BALANCE_WHERE_ACCNO_SQL = "RETRIVE_BALANCE_WHERE_ACCNO_SQL";
		public static final String UPDATE_BALANCE_SQL = "UPDATE_BALANCE_SQL";
		public static final String INSERT_TRANS_SQL = "INSERT_TRANS_SQL";
		public static final String INSERT_REVIEW_SQL = "INSERT_REVIEW_SQL";
		public static final String RETRIVE_ALLREVIEWS_SQL = "RETRIVE_ALLREVIEWS_SQL";
		public static final String INSERT_HISTORY_SQL = "INSERT_HISTORY_SQL";
		public static final String RETRIVE_HISTORYRATING_SQL = "RETRIVE_HISTORYRATING_SQL";
		public static final String DELETE_BESTHISTORY_SQL = "DELETE_BESTHISTORY_SQL";
		public static final String INSERT_BESTHISTORYRATING_SQL = "INSERT_BESTHISTORYRATING_SQL";
		public static final String RETRIVE_BESTHISTORY_SQL = "RETRIVE_BESTHISTORY_SQL";
		public static final String RETRIVE_BOOKPACKS_SQL = "RETRIVE_BOOKPACKS_SQL";
		public static final String INSERT_BOOKRATING_SQL = "INSERT_BOOKRATING_SQL";
		public static final String RETRIVE_BOOKRATING_SQL = "RETRIVE_BOOKRATING_SQL";
		public static final String RETRIVE_RATING_WHERE_BOOKID_SQL = "RETRIVE_RATING_WHERE_BOOKID_SQL";
		public static final String RETRIVE_BOOKPACKS_WHERE_CATID_SQL = "RETRIVE_BOOKPACKS_WHERE_CATID_SQL";
		public static final String INSERT_ORDERDETAILS_SQL = "INSERT_ORDERDETAILS_SQL";
		public static final String RETRIVE_EMAIL_FOR_LOGINID = "RETRIVE_EMAIL_FOR_LOGINID";
		public static final String INSERT_ORDERINFO_SQL = "INSERT_ORDERINFO_SQL";
		public static final String RETRIVE_SETTINGSUSER_SQL = "RETRIVE_SETTINGSUSER_SQL";
		public static final String INSERT_SETTINGS_SQL = "INSERT_SETTINGS_SQL";
		public static final String UPDATE_SETTINGS_SQL = "UPDATE_SETTINGS_SQL";
		public static final String RETRIVE_THRESHOLD_FOR_LOGINID = "RETRIVE_THRESHOLD_FOR_LOGINID";
		public static final String RETRIVE_BOOKIDS_FROM_ORDER_DETAILS_SQL = "RETRIVE_BOOKIDS_FROM_ORDER_DETAILS_SQL";
		public static final String RETRIVE_BOOKIDS_FROM_BOOKID_LOGINID_SQL = "RETRIVE_BOOKIDS_FROM_BOOKID_LOGINID_SQL";
		public static final String RETRIVE_BOOKPACKS_WHERE_BOOKID_SQL = "RETRIVE_BOOKPACKS_WHERE_BOOKID_SQL";
		public static final String RETRIVE_BOOKRATING_NOT_ZERO_SQL = "RETRIVE_BOOKRATING_NOT_ZERO_SQL";
		public static final String INSERT_USER_INFO = "INSERT_USER_INFO_SQL";
		public static final String RETRIVE_NOOFADV_WHERE_USERID_PAGEID_SQL = "RETRIVE_NOOFADV_WHERE_USERID_PAGEID_SQL";
		public static final String RETRIVE_DISTINCTUSERIDS_FROM_USERINFO_SQL = "RETRIVE_DISTINCTUSERIDS_FROM_USERINFO_SQL";
		public static final String RETRIVE_DISTINCT_PAGEIDS_FROM_USERINFO_WHERE_USERID_SQL = "RETRIVE_DISTINCT_PAGEIDS_FROM_USERINFO_WHERE_USERID_SQL";
		public static final String RETRIVE_USERINFO_FROM_USERINFO_WHERE_USERID_PAGEID_SQL = "RETRIVE_USERINFO_FROM_USERINFO_WHERE_USERID_PAGEID_SQL";
		public static final String RETRIVE_COUNT_FROM_USERINFO_WHERE_USERID_PAGEID = "RETRIVE_COUNT_FROM_USERINFO_WHERE_USERID_PAGEID";
		public static final String RETRIVE_DISTINCTS_USER_SQL = "RETRIVE_DISTINCTS_USER_SQL";
		public static final String RETRIVE_DISTINCT_COUNT_OF_USERID_WHERE_PAGEID_SQL = "RETRIVE_DISTINCT_COUNT_OF_USERID_WHERE_PAGEID_SQL";
		public static final String DELETE_ADV_USERID_SQL = "DELETE_ADV_USERID_SQL";
		public static final String INSERT_ADVITISEMENT_SQL = "INSERT_ADVITISEMENT_SQL";
		public static final String UPDATE_NOOFADV_SQL = "UPDATE_NOOFADV_SQL";
		public static final String RETRIVE_ADV_FOR_LOGINID_AND_PAGEID = "RETRIVE_ADV_FOR_LOGINID_AND_PAGEID";
		public static final String RETRIVE_BUDGET_FOR_LOGINID_SQL = "RETRIVE_BUDGET_FOR_LOGINID_SQL";
		public static final String UPDATE_BUDGET_SQL = "UPDATE_BUDGET_SQL";
		public static final String RETRIVE_SUM_BUDGET_SQL_FOR_LOGINID = "RETRIVE_SUM_BUDGET_SQL_FOR_LOGINID";
		public static final String RETRIVE_USERIDS_FROM_BUDGET_SQL = "RETRIVE_USERIDS_FROM_BUDGET_SQL";
		public static final String INSERT_BUDGET_SQL = "INSERT_BUDGET_SQL";
		public static final String INSERT_HABITATFILE_SQL = "INSERT_HABITATFILE_SQL";
		public static final String DELETE_LICENSEUSER_WHERE_USERNAME_SQL = "DELETE_LICENSEUSER_WHERE_USERNAME_SQL";
		public static final String INSERT_LICENSES_FOR_USER_SQL = "INSERT_LICENSES_FOR_USER_SQL";
		public static final String RETRIVE_LICENSEINFO_WHERE_USERNAME_SQL = "RETRIVE_LICENSEINFO_WHERE_USERNAME_SQL";
		public static final String RETRIVE_LICENSENAME_WHERE_USERNAME_SQL = "RETRIVE_LICENSENAME_WHERE_USERNAME_SQL";
		public static final String RETRIVE_HABITATFILE_WHERE_USERNAME_SQL = "RETRIVE_HABITATFILE_WHERE_USERNAME_SQL";
		public static final String RETRIVE_HABITATFILE_SESSION_WHERE_USERNAME_SQL = "RETRIVE_HABITATFILE_SESSION_WHERE_USERNAME_SQL";
		public static final String RETRIVE_HABITATFILE_WHERE_USERNAME_AND_SESSIONAPP_SQL = "RETRIVE_HABITATFILE_WHERE_USERNAME_AND_SESSIONAPP_SQL";
		public static final String RETRIVE_DISTINCT_PRODUCTIDS_SQL = "RETRIVE_DISTINCT_PRODUCTIDS_SQL";
		public static final String RETRIVE_COUNT_WHERE_PRODUCTID_SQL = "RETRIVE_COUNT_WHERE_PRODUCTID_SQL";
		public static final String RETRIVE_DISTINCT_ORDERDATE_FROM_ORDERINFO_WHERE_PRODUCTID_SQL = "RETRIVE_DISTINCT_ORDERDATE_FROM_ORDERINFO_WHERE_PRODUCTID_SQL";
		public static final String INSERT_KMEANS_EXTENDED_SQL = "INSERT_KMEANS_EXTENDED_SQL";
		public static final String DELETE_KMEANS_SQL = "DELETE_KMEANS_SQL";
		public static final String RETRIVE_KMEANS_SQL = "RETRIVE_KMEANS_SQL";
		public static final String RETRIVE_DISTINCT_COUNT_FROM_KMEANS_WHERE_CLUSTERNO_SQL = "RETRIVE_DISTINCT_COUNT_FROM_KMEANS_WHERE_CLUSTERNO_SQL";
		public static final String RETRIVE_DISTINCT_USERIDS_FROM_LOGIN = "RETRIVE_DISTINCT_USERIDS_FROM_LOGIN";
		public static final String RETRIVE_COUNT_WHERE_USERID_SQL = "RETRIVE_COUNT_WHERE_USERID_SQL";
		public static final String RETRIVE_TOTALSPEND_WHERE_USERID_SQL = "RETRIVE_TOTALSPEND_WHERE_USERID_SQL";
		public static final String RETRIVE_MAXDATE_WHERE_USERID_SQL = "RETRIVE_MAXDATE_WHERE_USERID_SQL";
		public static final String INSERT_RFM_SQL = "INSERT_RFM_SQL";
		public static final String DELETE_RFM_SQL = "DELETE_RFM_SQL";
		public static final String RETRIVE_USERIDS_SORT_BY_FREQ_FROM_RFM_SQL = "RETRIVE_USERIDS_SORT_BY_FREQ_FROM_RFM_SQL";
		public static final String RETRIVE_USERIDS_SORT_BY_RECENCY_FROM_RFM_SQL = "RETRIVE_USERIDS_SORT_BY_RECENCY_FROM_RFM_SQL";
		public static final String RETRIVE_USERIDS_SORT_BY_MONETORY_FROM_RFM_SQL = "RETRIVE_USERIDS_SORT_BY_MONETORY_FROM_RFM_SQL";
		public static final String DELETE_RFMWEIGHT_SQL = "DELETE_RFMWEIGHT_SQL";
		public static final String RETRIVE_RFMWEIGHT_SQL = "RETRIVE_RFMWEIGHT_SQL";
		public static final String INSERT_RFMWEIGHT_SQL = "INSERT_RFMWEIGHT_SQL";
		public static final String RETRIVE_RFMWEIGHT_ORDERBY_RFM_SQL = "RETRIVE_RFMWEIGHT_ORDERBY_RFM_SQL";
		public static final String INSERT_BLOCK_CUSTOMER_SQL = "INSERT_BLOCK_CUSTOMER_SQL";
		public static final String DELETE_CLASSIFICATION_RFM_SQL = "DELETE_CLASSIFICATION_RFM_SQL";
		public static final String RETRIVE_DISTINCT_COUNT_FROM_RFMCLUSTER_WHERE_CLUSTERNO_SQL = "RETRIVE_DISTINCT_COUNT_FROM_RFMCLUSTER_WHERE_CLUSTERNO_SQL";
		public static final String RETRIVE_CUSTOMER_RFM_CLUSTER_SQL = "RETRIVE_CUSTOMER_RFM_CLUSTER_SQL";
		public static final String RETRIVE_CLUSTERNO_FROM_KMEANS_WHERE_USERID_SQL = "RETRIVE_CLUSTERNO_FROM_KMEANS_WHERE_USERID_SQL";
		public static final String RETRIVE_CLUSTERNO_FROM_RFM_WHERE_USERID_SQL = "RETRIVE_CLUSTERNO_FROM_RFM_WHERE_USERID_SQL";
		public static final String RETRIVE_PRODUCTIDS_FROM_KMEANS_WHERE_CLUSTERNO_SQL = "RETRIVE_PRODUCTIDS_FROM_KMEANS_WHERE_CLUSTERNO_SQL";
		public static final String RETRIVE_BOOKPACKS_WHERE_BOOKID_IN_SQL = "RETRIVE_BOOKPACKS_WHERE_BOOKID_IN_SQL";
		public static final String RETRIVE_RFMWEIGHT_PAGINATION_SQL = "RETRIVE_RFMWEIGHT_PAGINATION_SQL";
		public static final String RETRIVE_RFMWEIGHT_COUNT_SQL = "RETRIVE_RFMWEIGHT_COUNT_SQL";
		public static final String RETRIVE_CUSTOMER_RFM_CLUSTER_PAGINATION_SQL = "RETRIVE_CUSTOMER_RFM_CLUSTER_PAGINATION_SQL";
		public static final String RETRIVE_CUSTOMER_RFM_CLUSTER_COUNT_SQL = "RETRIVE_CUSTOMER_RFM_CLUSTER_COUNT_SQL";
		public static final String RETRIVE_KMEANS_PAGINATION_SQL = "RETRIVE_KMEANS_PAGINATION_SQL";
		public static final String RETRIVE_KMEANS_COUNT_SQL = "RETRIVE_KMEANS_COUNT_SQL";  
	}

	interface Views {
		public static final String VIEW_REGISTER_INPUT = "registeruser";
		public static final String VIEW_CUSTOMER_WELCOMEPAGE = "custwelcome";
		public static final String VIEW_LOGIN_INPUT = "login";
		public static final String VIEW_ADMIN_WELCOMEPAGE = "admin";
		public static final String VIEW_BANK_INPUT = "bankinput";
		public static final String VIEW_ERROR_PAGE = "error";
		public static final String APPLICATION_WELCOME_PAGE = "applicationwelcome";
		public static final String VIEW_SUCESS_PAGE = "sucess";
		public static final String APPLYREVIEW_VIEW = "applyReview";
		public static final String VIEW_ADMIN_SUCESS_PAGE = "adminsucess";
		public static final String VIEW_ADMIN_ERROR_PAGE = "adminerror";
		public static final String VIEW_POLARITY_PACK = "polarity";
		public static final String VIEW_INPUT_ERROR_PAGE = "inputerror";
		public static final String VIEW_CUSTOMER_ERROR_PAGE = "customererror";
		public static final String VIEW_BOOKS_OUTPUT = "books";
		public static final String VIEW_BOOKS_INPUT = "custwelcome";
		public static final String VIEW_RATING_RANK = "rankrating";
		public static final String VIEW_CUSTWELCOME_INPUT = "custwelcome";
		public static final String TRANSACTION_SUCESS = "Transaction Completed Sucessfully";
		public static final String SUCESS_PAGE = "sucess";
		public static final String VIEW_SETTINGS_INPUT = "personalsettings";
		public static final String SETTINGS_STORE_SUCESS = "Settings has been Stored Sucessfully";
		public static final String VIEW_RANKSBOOKS_OUTPUT = "rankbooks";
		public static final String VIEW_ASSOCIATIONRULEMININING_OUTPUT = "associationRuleMining";
		public static final String VIEW_GENETIC_OUTPUT = "geneticOut";
		public static final String BUDGET_EMPTY = "Budget Cannot be Empty";
		public static final String BUDGET_INPUT_PAGE = "budget";
		public static final String VIEW_BUDGET_WARNING = "budgetwarning";
		public static final String PERSONAL_SETTINGS_PAGE = "personalsettings";
	}

	interface Keys {
		public static final String OBJ = "obj";
		public static final int ADMIN_TYPE = 5;
		public static final String LOGINID_SESSION = "LOGINID_SESSION";
		public static final String LOGINTYPE_SESSION = "LOGINTYPE_SESSION";
		public static final String SESSION_PACKID = "SESSION_PACKID";
		public static final String STOPWORDS_SYMBOL = "[^a-zA-Z]+";
		public static final String SPACE = "  ";
		public static final String BOOKID_KEY = "bookId";
		public static final String RATING_KEY = "rating";
		public static final String CATID = "catId";
		public static final String QUANTITY_KEY = "quantity";
		public static final String LOGINID_KEY = "loginId";
		public static final String ORDERDATE_KEY = "orderDate";
		public static final String TOTALAMT_KEY = "totalAmt";
		public static final String EMAIL_KEY = "email";
		public static final String THRESHOLD_KEY = "threshold";
		public static final String USERID_KEY = "userId";
		public static final String NOOFBYTES_KEY = "noOfBytes";
		public static final String PAGEID_KEY = "pageId";
		public static final String TIMEOFSTAY_KEY = "timeOfStay";
		public static final String NOOFADV_KEY = "noOfAdv";
		public static final String GENETIC_MODEL_KEY = "GENETIC_SESSION";
		public static final String LICENSE_INFO = "LICENSE_INFO";
		public static final String USERNAME_KEY = "USERNAME";
		public static final String HIGHLY_LOYAL_CUST = "HIGHLYLOYAL";
		public static final String MEDIUM_LOYAL_CUST = "MEDIUMLOYAL";
		public static final String LOW_LOYAL_CUST = "LOWLOYAL";
		public static final int MEDIUM_STOCK = 2;

		public static final int LOW_STOCK = 1;
	}

	public static final String TIME_START = "STARTTIME";
	public static final String PAGE_ID = "PAGEID";

	interface Action {

		public static final String SPORTS_PAGE = "SPORTSPAGE";

		public static final String BUY_BUTTON_SPORTS = "SPORTSBTN";

		public static final String BUY_BUTTON_POLITICS = "POLITICSBTN";

		public static final String POLITICS_PAGE = "POLITICSPAGE";

		public static final String BUY_BUTTON_FLIMFARE = "FLIMFAREBTN";

		public static final String FLIMFARE_PAGE = "FLIMFAREPAGE";

		public static final String BUY_BUTTON_ANALYTICS = "ANALYTICSBTN";

		public static final String ANALYTICS_PAGE = "ANALYTICSPAGE";

		public static final String BUY_BUTTON_PROGRAMMING = "PROGRAMMINGBTN";

		public static final String PROGRAMMING_PAGE = "PROGRAMMINGPAGE";

		public static final String BUY_BUTTON_GREETINGS = "GREETINGSBTN";

		public static final String GREETINGS_PAGE = "GREETINGSPAGE";

		public static final String BUTTON_BUDGET_SETTINGS = "BUDGETSETTINGSBTN";

		public static final String BUDGET_PAGE = "BUDGETPAGE";

		public static final String BUTTON_PROCEED_BANK = "PROCEEDBANKBTN";

		public static final String BUTTON_PROCEED_BUDGET = "PROCEEDBUDGETBTN";

		public static final String BANK_PAGE = "BANKPAGE";

		public static final String BUTTON_APPLY_SETTINGS = "APPLYSETTINGSBTN";

		public static final String TRANSACTION_PAGE = "TRANSACTIONPAGE";

		public static final String HOME_PAGE = "HOMEPAGE";

		public static final String LOGIN_PAGE = "LOGINPAGE";

		public static final String LOGOUT_BUTTON = "LOGOUTBTN";

		public static final String RECOMMEDATIONS_PAGE = "RECOMMENDATIONSPAGE";

		public static final String ASSOCIATION_RULE_PAGE = "ASSOCIATIONPAGE";

		public static final String PERSONALSETTINGS_PAGE = "PERSONALSETTINGSPAGE";

		public static final String BUDGETWARNING_PAGE = "BUDGETWARNINGPAGE";

		public static final String ADV_PAGE = "ADVPAGE";

	}

	interface ActionType {

		public static final String PAGE_TYPE_ACTION = "PAGE";

		public static final String BUTTON_TYPE_ACTION = "BUTTON";

		public static final String LINK_TYPE_ACTION = "LINK";

	}

	interface LicenseType {

		String SPORTS = "SPORTS";
		String POLITICS = "POLITICS";
		String FLIMFARE = "FLIMFARE";
		String ANALYTICS = "ANALYTICS";
		String PROGRAMMING = "PROGRAMMING";
		String GREETINGS = "GREETINGS";
		String SETTINGS = "SETTINGS";
		String RANKBOOKS = "RANKBOOKS";
		String BUDGETSET = "BUDGETSET";
	}
}

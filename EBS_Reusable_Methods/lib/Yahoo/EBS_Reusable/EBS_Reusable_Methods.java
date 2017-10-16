//WARNING!
//This file was created by Oracle OpenScript.
//Don't change it.

package lib.Yahoo.EBS_Reusable;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import oracle.oats.scripting.modules.basic.api.ErrorRecoveryAction;
import oracle.oats.scripting.modules.basic.api.IteratingVUserScript;
import oracle.oats.scripting.modules.basic.api.ScriptService;
import oracle.oats.scripting.modules.basic.api.exceptions.AbstractScriptException;
import oracle.oats.scripting.modules.formsFT.api.FormsErrorRecovery;
import oracle.oats.scripting.modules.formsFT.common.api.elements.AbstractWindow;
import oracle.oats.scripting.modules.formsFT.common.api.elements.Window;
import oracle.oats.scripting.modules.formsFT.common.api.exceptions.FormsFTException;
import oracle.oats.scripting.modules.functionalTest.api.TestOperator;
import oracle.oats.scripting.modules.utilities.api.Row;
import oracle.oats.scripting.modules.utilities.api.Table;
import oracle.oats.scripting.modules.webdom.api.elements.DOMElement;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.webdom.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.formsFT.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.basic.api.internal.FuncLibraryWrapper;

public class EBS_Reusable_Methods extends FuncLibraryWrapper
{

	public void initialize() throws AbstractScriptException {
		checkInit();
		callFunction("initialize");
	}

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws AbstractScriptException {
		checkInit();
		callFunction("run");
	}

	public void navigate_Environment(String env) throws AbstractScriptException {
		checkInit();
		callFunction("navigate_Environment", env);
	}

	public void login_EBS() throws AbstractScriptException {
		checkInit();
		callFunction("login_EBS");
	}

	public void login_EBS_SSO() throws AbstractScriptException {
		checkInit();
		callFunction("login_EBS_SSO");
	}

	public void expand_NodeFromList(String[] fldrs, String lnk)
			throws AbstractScriptException {
		checkInit();
		callFunction("expand_NodeFromList", (Object) fldrs, lnk);
	}

	public void expand_NodeFromList(String[] fldrs)
			throws AbstractScriptException {
		checkInit();
		callFunction("expand_NodeFromList", (Object) fldrs);
	}

	public String getMonthYearFormat(int i) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getMonthYearFormat", i);
	}

	public void open_PeriodsForAP(int months) throws AbstractScriptException {
		checkInit();
		callFunction("open_PeriodsForAP", months);
	}

	public void open_PeriodsForAR(int months) throws AbstractScriptException {
		checkInit();
		callFunction("open_PeriodsForAR", months);
	}

	public void open_PeriodsForGL(int months) throws AbstractScriptException {
		checkInit();
		callFunction("open_PeriodsForGL", months);
	}

	public void SwitchResponsibility(String resp)
			throws AbstractScriptException {
		checkInit();
		callFunction("SwitchResponsibility", resp);
	}

	public void Queryformdata(String strfieldprop, String strfieldprop1,
			String value, String GLdate) throws AbstractScriptException {
		checkInit();
		callFunction("Queryformdata", strfieldprop, strfieldprop1, value,
				GLdate);
	}

	public void AddMissingResponsibility(String responsibility)
			throws AbstractScriptException {
		checkInit();
		callFunction("AddMissingResponsibility", responsibility);
	}

	public void selecttreelist(String strfieldprop, String strfieldvalue)
			throws AbstractScriptException {
		checkInit();
		callFunction("selecttreelist", strfieldprop, strfieldvalue);
	}

	public String GetText(String strfieldprop) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("GetText", strfieldprop);
	}

	public void SaveToolbar(String strfieldprop) throws AbstractScriptException {
		checkInit();
		callFunction("SaveToolbar", strfieldprop);
	}

	public void flexWindow(String strfieldprop, String strstepdesc)
			throws AbstractScriptException {
		checkInit();
		callFunction("flexWindow", strfieldprop, strstepdesc);
	}

	public String generateUniqueData(String data)
			throws AbstractScriptException {
		checkInit();
		return (String) callFunction("generateUniqueData", data);
	}

	public void RunInvoicevalidation(String Paygroup)
			throws AbstractScriptException {
		checkInit();
		callFunction("RunInvoicevalidation", Paygroup);
	}

	public void CreateViewAccountingREC() throws AbstractScriptException {
		checkInit();
		callFunction("CreateViewAccountingREC");
	}

	public void CreateViewAccountingTRX() throws AbstractScriptException {
		checkInit();
		callFunction("CreateViewAccountingTRX");
	}

	public String SubmitorCreateAccountingJob(String jobname, String ledger)
			throws AbstractScriptException {
		checkInit();
		return (String) callFunction("SubmitorCreateAccountingJob", jobname,
				ledger);
	}

	public void submitAutoInvoiceProgramJob(String Source)
			throws AbstractScriptException {
		checkInit();
		callFunction("submitAutoInvoiceProgramJob", Source);
	}

	public void ERPLogout() throws AbstractScriptException {
		checkInit();
		callFunction("ERPLogout");
	}

	public void ViewSingleRequest() throws AbstractScriptException {
		checkInit();
		callFunction("ViewSingleRequest");
	}

	public void ViewRequestSet() throws AbstractScriptException {
		checkInit();
		callFunction("ViewRequestSet");
	}

	public String getDateTimeFormat(String data) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getDateTimeFormat", data);
	}

	public String UniqueNumber(String data) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("UniqueNumber", data);
	}

	public void concurrentRequestStatus(String requestProgramName)
			throws AbstractScriptException {
		checkInit();
		callFunction("concurrentRequestStatus", requestProgramName);
	}

	public void verifyJobStatus() throws AbstractScriptException {
		checkInit();
		callFunction("verifyJobStatus");
	}

	public void SubmitandVerifyBatchProcess(String Batchname)
			throws AbstractScriptException {
		checkInit();
		callFunction("SubmitandVerifyBatchProcess", Batchname);
	}

	public void submit_SingleRequest(String requestName)
			throws AbstractScriptException {
		checkInit();
		callFunction("submit_SingleRequest", requestName);
	}

	public void submit_SingleRequest_DefaultParams(String requestName)
			throws AbstractScriptException {
		checkInit();
		callFunction("submit_SingleRequest_DefaultParams", requestName);
	}

	public void submit_SingleRequest_CertainParams(String requestName,
			String... params) throws AbstractScriptException {
		checkInit();
		callFunction("submit_SingleRequest_CertainParams", requestName, params);
	}

	public boolean fileExistsInLocation(String location, String fileName)
			throws AbstractScriptException {
		checkInit();
		return (Boolean) callFunction("fileExistsInLocation", location,
				fileName);
	}

	public boolean fileExistsWithNamePattern(String location, String fileName)
			throws AbstractScriptException {
		checkInit();
		return (Boolean) callFunction("fileExistsWithNamePattern", location,
				fileName);
	}

	public void printLogResultsForProcess(String requiredDetails)
			throws AbstractScriptException {
		checkInit();
		callFunction("printLogResultsForProcess", requiredDetails);
	}

	public void printResultsForProcess(String ProcessName)
			throws AbstractScriptException {
		checkInit();
		callFunction("printResultsForProcess", ProcessName);
	}

	public void copyFileFromInputDirectory(String destDirectory, String fileName)
			throws AbstractScriptException {
		checkInit();
		callFunction("copyFileFromInputDirectory", destDirectory, fileName);
	}

	public void saveFile(String filepath) throws AbstractScriptException {
		checkInit();
		callFunction("saveFile", filepath);
	}

	public Table executeQuery(String qry) throws AbstractScriptException {
		checkInit();
		return (Table) callFunction("executeQuery", qry);
	}

	public Table executeQuery(String qry, List<Object> params)
			throws AbstractScriptException {
		checkInit();
		return (Table) callFunction("executeQuery", qry, params);
	}

	public boolean executeStatement(String statement)
			throws AbstractScriptException {
		checkInit();
		return (Boolean) callFunction("executeStatement", statement);
	}

	public boolean executeStatement(String statement, List<Object> params)
			throws AbstractScriptException {
		checkInit();
		return (Boolean) callFunction("executeStatement", statement, params);
	}

	public void SOAOrderquery(String currdate) throws AbstractScriptException {
		checkInit();
		callFunction("SOAOrderquery", currdate);
	}

	public void Sync() throws AbstractScriptException {
		checkInit();
		callFunction("Sync");
	}

	public void click(String locator) throws AbstractScriptException {
		checkInit();
		callFunction("click", locator);
	}

	public void waitTillPageLoad() throws AbstractScriptException {
		checkInit();
		callFunction("waitTillPageLoad");
	}

	public void setText_Web(String locator, String value)
			throws AbstractScriptException {
		checkInit();
		callFunction("setText_Web", locator, value);
	}

	public void setTextArea_Web(String locator, String value)
			throws AbstractScriptException {
		checkInit();
		callFunction("setTextArea_Web", locator, value);
	}

	public void selectRadioButton_Web(String locator)
			throws AbstractScriptException {
		checkInit();
		callFunction("selectRadioButton_Web", locator);
	}

	public void selectValueComboBox_Web(String locator, String option)
			throws AbstractScriptException {
		checkInit();
		callFunction("selectValueComboBox_Web", locator, option);
	}

	public void selectCheckBox_Web(String locator, boolean option)
			throws AbstractScriptException {
		checkInit();
		callFunction("selectCheckBox_Web", locator, option);
	}

	public void verifyElementProperty(DOMElement element, String property,
			String value, TestOperator operator) throws AbstractScriptException {
		checkInit();
		callFunction("verifyElementProperty", element, property, value,
				operator);
	}

	/**
	 * @Method  Name : captureScreenShot
	 * @Description  : Method to capture active screen shot
	 * @Author  : Jagan Gottimukkala
	 * @Param  dateChange : dateChange indicates expected date format.
	 * @Throws  Exception 
	 * @Last  Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public String captureScreenShot() throws AbstractScriptException {
		checkInit();
		return (String) callFunction("captureScreenShot");
	}

	/**
	 * @Method  Name : createFolder
	 * @Description  : Method to create a folder
	 * @Author       : Jagan Gottimukkala
	 * @Param  dateChange : dateChange indicates expected date format.
	 * @Throws  Exception 
	 * @Last  Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public void createFolder(String folderName) throws AbstractScriptException {
		checkInit();
		callFunction("createFolder", folderName);
	}

	/**
	 * @Method  Name : GetRecentfold
	 * @Description  : Method to get the recently created folder name in the given directory
	 * @Author  : Jagan Gottimukkala
	 * @Param  dateChange : dateChange indicates expected date format.
	 * @Throws  Exception 
	 * @Last  Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public String GetRecentfold(String directoryPath)
			throws AbstractScriptException {
		checkInit();
		return (String) callFunction("GetRecentfold", directoryPath);
	}

	/**
	 * @Method  Name : randomString
	 * @Description  : Method to get the random string
	 * @Author  : Jagan Gottimukkala
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 * @Last  Modified/Updated by: Rajendra Chary
	 */
	public String randomString(String length) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("randomString", length);
	}

	public void writeResult(Boolean result, String resultFilePath,
			String scriptRowNo, String scriptResultPath)
			throws AbstractScriptException {
		checkInit();
		callFunction("writeResult", result, resultFilePath, scriptRowNo,
				scriptResultPath);
	}

	public void verifyPageExists_Web(String title)
			throws AbstractScriptException {
		checkInit();
		callFunction("verifyPageExists_Web", title);
	}

	public void pymtbatchprocess(String batchname)
			throws AbstractScriptException {
		checkInit();
		callFunction("pymtbatchprocess", batchname);
	}

	public void captureformserror(String Message, String scriptpath,
			String testcasename) throws AbstractScriptException {
		checkInit();
		callFunction("captureformserror", Message, scriptpath, testcasename);
	}

	public void writeresults(String scriptpath, String testcasename,
			String Reqnum, String POnum, String SupplierLocation,
			String ReqStatus, String Supplier, String Result)
			throws AbstractScriptException {
		checkInit();
		callFunction("writeresults", scriptpath, testcasename, Reqnum, POnum,
				SupplierLocation, ReqStatus, Supplier, Result);
	}

	public void failresults(String scriptpath, String testcasename,
			String Result, String Alertmsg) throws AbstractScriptException {
		checkInit();
		callFunction("failresults", scriptpath, testcasename, Result, Alertmsg);
	}

	public void finish() throws AbstractScriptException {
		checkInit();
		callFunction("finish");
	}

}


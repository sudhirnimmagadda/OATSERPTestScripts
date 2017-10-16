//WARNING!
//This file was created by Oracle OpenScript.
//Don't change it.

package lib.Yahoo.EBS_Reusable;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.basic.api.exceptions.AbstractScriptException;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import oracle.oats.scripting.modules.webdom.api.elements.DOMButton;
import oracle.oats.scripting.modules.webdom.api.elements.DOMCheckbox;
import oracle.oats.scripting.modules.webdom.api.elements.DOMElement;
import oracle.oats.scripting.modules.webdom.api.elements.DOMLink;
import oracle.oats.scripting.modules.webdom.api.elements.DOMRadioButton;
import oracle.oats.scripting.modules.webdom.api.elements.DOMSelect;
import oracle.oats.scripting.modules.webdom.api.elements.DOMTable;
import oracle.oats.scripting.modules.webdom.api.elements.DOMText;
import oracle.oats.scripting.modules.basic.api.internal.FuncLibraryWrapper;

public class Web_Reusable_Methods extends FuncLibraryWrapper
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

	public FileInputStream getConfigFile(String filePath)
			throws AbstractScriptException {
		checkInit();
		return (FileInputStream) callFunction("getConfigFile", filePath);
	}

	public Properties ReadValuesFromTextFile(FileInputStream file)
			throws AbstractScriptException {
		checkInit();
		return (Properties) callFunction("ReadValuesFromTextFile", file);
	}

	public void navigate_Environment(String env) throws AbstractScriptException {
		checkInit();
		callFunction("navigate_Environment", env);
	}

	public void login_SNet(String userName, String password)
			throws AbstractScriptException {
		checkInit();
		callFunction("login_SNet", userName, password);
	}

	public void selectCarModels(String carLine, List<String> modelYear,
			List<String> modelOptions, List<String> extColor, String Spec)
			throws AbstractScriptException {
		checkInit();
		callFunction("selectCarModels", carLine, modelYear, modelOptions,
				extColor, Spec);
	}

	public void waitTillPageLoad() throws AbstractScriptException {
		checkInit();
		callFunction("waitTillPageLoad");
	}

	public void selectCheckBox(String label) throws AbstractScriptException {
		checkInit();
		callFunction("selectCheckBox", label);
	}

	public void DeselectCheckBox(String label) throws AbstractScriptException {
		checkInit();
		callFunction("DeselectCheckBox", label);
	}

	public void selectCheckBox_Web(String locator, boolean option)
			throws AbstractScriptException {
		checkInit();
		callFunction("selectCheckBox_Web", locator, option);
	}

	public void Sync() throws AbstractScriptException {
		checkInit();
		callFunction("Sync");
	}

	public void click(String locator) throws AbstractScriptException {
		checkInit();
		callFunction("click", locator);
	}

	/**
	 * @Method  Name : clickbutton
	 * @Description  : Method to check existence of button and click
	 * @Author  : Sudhir Nimmagadda
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 */
	public void ClickButton(String label) throws AbstractScriptException {
		checkInit();
		callFunction("ClickButton", label);
	}

	/**
	 * @Method  Name : clicklink
	 * @Description  : Method to check existence of link and click
	 * @Author  : Sudhir Nimmagadda
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 */
	public void ClickLink(String label) throws AbstractScriptException {
		checkInit();
		callFunction("ClickLink", label);
	}

	/**
	 * @Method  Name : selectradiobutton
	 * @Description  : Method to check existence of radio button and select
	 * @Author  : Sudhir Nimmagadda
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 */
	public void selectRadiobtn(String label) throws AbstractScriptException {
		checkInit();
		callFunction("selectRadiobtn", label);
	}

	public void Dropdown(String label, int num) throws AbstractScriptException {
		checkInit();
		callFunction("Dropdown", label, num);
	}

	public void selectbox(String label, String value)
			throws AbstractScriptException {
		checkInit();
		callFunction("selectbox", label, value);
	}

	/**
	 * @Method  Name : settextbox
	 * @Description  : Method to enter data to textbox
	 * @Author  : Sudhir Nimmagadda
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 */
	public void settextbox(String label, String value)
			throws AbstractScriptException {
		checkInit();
		callFunction("settextbox", label, value);
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

	/**
	 * @Method  Name : gettextboxdata
	 * @Description  : Method to capture data from textbox
	 * @Author  : Sudhir Nimmagadda
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 */
	public String gettextboxdata(String label) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("gettextboxdata", label);
	}

	/**
	 * @Method  Name : gettabledata
	 * @Description  : Method to capture data from table
	 * @Author  : Sudhir Nimmagadda
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 */
	public String gettabledata(String prop, int row, int col)
			throws AbstractScriptException {
		checkInit();
		return (String) callFunction("gettabledata", prop, row, col);
	}

	/**
	 * @Method  Name : getwebdata
	 * @Description  : Method to capture data from element
	 * @Author  : Sudhir Nimmagadda
	 * @Param  objectValue :        
	 * @throws  : AbstractScriptException
	 */
	public String getwebdata(String locator) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getwebdata", locator);
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
	 * @Method  Name : createFolder
	 * @Description  : Method to create a folder
	 * @Author  : Jagan Gottimukkala
	 * @Param  dateChange : dateChange indicates expected date format.
	 * @Throws  Exception 
	 * @Last  Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public void createFolder(String folderName) throws AbstractScriptException {
		checkInit();
		callFunction("createFolder", folderName);
	}

	public String getDateTimeFormat(String data) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getDateTimeFormat", data);
	}

	public void writeResult(Boolean result, String resultFilePath,
			String scriptRowNo, String scriptResultPath)
			throws AbstractScriptException {
		checkInit();
		callFunction("writeResult", result, resultFilePath, scriptRowNo,
				scriptResultPath);
	}

	public void finish() throws AbstractScriptException {
		checkInit();
		callFunction("finish");
	}

}


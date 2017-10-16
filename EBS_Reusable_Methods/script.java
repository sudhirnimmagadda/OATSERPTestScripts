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

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.formsFT.api.FormsService forms;
	@ScriptService oracle.oats.scripting.modules.datatable.api.DataTableService datatable;

	String scriptPath="";
	String environments = "";
	@ScriptService oracle.oats.scripting.modules.applet.api.AppletService applet;
	public void initialize() throws Exception {	
	}

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {

	}
	public void navigate_Environment(String env) throws Exception{

		beginStep("[1] No Title (/oracle_portal.php)", 0);
		{
			web.window(2, "/web:window[@index='0' or @title='about:blank']").maximize();
			web.window(2, "/web:window[@index='0' or @title='about:blank']")
			.navigate("http://strportal.subaru1.com:7790/STRPORTAL/oracle_portal.php");
			
			//web.link("/web:window[@index='0']/web:document[@index='0']/web:a[@text='"+env+"']").click();
			
			web.link("/web:window[@index='0' or @title='Stars Dev Portal']/web:document[@index='0']/web:a[@text='STRQA2(SSO)'or @index='2']").click();
			
				}
		endStep();
	}

	public void login_EBS() throws Exception{
	    browser.closeAllBrowsers();
		browser.launch();
		beginStep("[1] Import Excel");
		{
			String defaultExcelFile = "EnvDetails.xls";
			scriptPath = getScriptPackage().getScriptPath();
			datatable.importExcel("C:\\OracleATS\\OFT\\EnvironmentData\\EnvDetails.xls");
			datatable.getCurrentSheet();
			int rowcnt=datatable.getRowCount();
			int colcnt=datatable.getColumnCount(0);
			
			
			for (int i=0;i<rowcnt;i++){
				
				String controlfile =(String)datatable.getValue(i,"A");
				
				  if (controlfile.equalsIgnoreCase("Y")) {
					  info("selected environment is ++ ");
					  String ENV=(String)datatable.getValue(i,"B");
					  String URL=(String)datatable.getValue(i,"C");
					  String username=(String)datatable.getValue(i,"D");
					  info(username);
					  String pwd=(String)datatable.getValue(i,"E");
					  
		   	   web.window(2, "/web:window[@index='0' or @title='about:blank']").navigate(URL);
		   	   think(10);
		   	
		   	   
		   	 if (ENV.equalsIgnoreCase("ERPPTP1")){
		   		web.link("/web:window[@index='0' or @title='Certificate Error: Navigation Blocked']/web:document[@index='0']/web:a[@text='Continue to this website (not recommended).']").click();
		   		//web.dialog("/web:dialog_unknown[@text='The current webpage is trying to open a site in your Trusted sites list. Do you want to allow this?' or @index='1']/web:accPushButton[@index='1' or @name='Yes']").click();
		   		web.dialog("/web:dialog_unknown[@text='The current webpage is trying to open a site in your Trusted sites list. Do you want to allow this?' or @index='1']").clickButton(0);
		   		web.window("/web:window[@index='0' or @title='Certificate Error: Navigation Blocked']").waitForPage(null);
		   	  	  }
		   	  
			  // web.window("/web:window[@index='0' or @title='Login']").waitForPage(null);
			   web.textBox("/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_text[@id='usernameField' or @name='usernameField' or @index='0']")
						   .setText(username);
		
		      // web.textBox("/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_text[@id='usernameField' or @name='usernameField' or @index='0']").pressTab();
			   web.textBox("/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_password[@id='passwordField' or @name='passwordField' or @index='0']")
						.setPassword(pwd);
		       web.button("/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:button[@index='0']").click();
		       web.window("/web:window[@index='0' or @title='Login']").waitForPage(null);		  	  
				  }
			}	  			  
		}
	}	
				  
				
	public void login_EBS_SSO() throws Exception{
		browser.closeAllBrowsers();
		browser.launch();
		beginStep("[1] Connect to the Database");
		{
			String defaultExcelFile = "ServerDetails.xls";
			scriptPath = getScriptPackage().getScriptPath();
			datatable.importSheet(scriptPath.substring(0, scriptPath.lastIndexOf("\\"))+"\\..\\..\\Config\\"+defaultExcelFile, "EnvironmentDetails", "Environment");
			datatable.useFirstRowAsColumnHeader("Environment");
			for (int i = 0; i < datatable.getRowCount("Environment");i++)
			{ 
				datatable.setCurrentRow("Environment", i);
				getVariables().set(datatable.getValue("Environment",i, "Environment_Variable").toString(),datatable.getValue("Environment",i, "Details").toString());

			}
			environments = getVariables().get("TestEnvironment");
			for(String environment : environments.split("\\s*,\\s*")){
				String host = getVariables().get(environment+"_HOST");
				String port = getVariables().get(environment+"_PORT");
				String sid = getVariables().get(environment+"_SID");
				String usrNme = getVariables().get(environment+"_USERNAME");
				String pssWd = getVariables().get(environment+"_PASSWORD");
				getVariables().set("Environment", environment);
				utilities.getSQLService().define(environment,
					"oracle.jdbc.driver.OracleDriver",
					"jdbc:oracle:thin:@"+host+":"+port+":"+sid+"", usrNme,
					pssWd);
				utilities.getSQLService().connect(environment);
			}
		}
		endStep();
		if(!web.element("/web:window[@index='0' or @title='STRDV1']" +
			"/web:document[@index='0']/web:td[@text='Logged In As "+
			getVariables().get("EBS_USERNAME")+"' or @index='33']")
			.exists(2, TimeUnit.SECONDS)){
			beginStep("[2] Login (/RF.jsp)", 0);
			{
				navigate_Environment(getVariables().get("Environment"));
				web.window("/web:window[@index='0' or @title='Subaru SSO']").waitForPage(null);
				web.textBox("/web:window[@index='0' or @title='Subaru SSO']/web:document[@index='0']/web:form[@name='loginForm' or @index='0']/web:input_text[@name='username' or @index='0']")
						.setText(getVariables().get("EBS_USERNAME"));

				web.textBox("/web:window[@index='0' or @title='Subaru SSO']/web:document[@index='0']/web:form[@name='loginForm' or @index='0']/web:input_password[@name='password' or @index='0']")
						.setText(getVariables().get("EBS_PASSWORD"));
				
				/*DOMButton loginbutton =  (DOMButton) web.document("//web:window[@index='0' or @title='Login']/web:document[@index='0']").getElementsByTagName("button").get(0);
				loginbutton.click();*/
				
				web.button("/web:window[@index='0' or @title='Subaru SSO']/web:document[@index='0']/web:form[@name='loginForm' or @index='0']/web:input_submit[@name='submit' or @value='Login' or @index='0']")
							.click();
				
				web.window("/web:window[@index='0' or @title='Login']").waitForPage(null);
				{
					think(1.488);
				}
			}
			endStep();
			if(web.element("/web:window[@index='0' or @title='STRDV1']" +
				"/web:document[@index='0']/web:td[@text='Logged In As "+
				getVariables().get("EBS_USERNAME")+"' or @index='33']")
				.exists(2, TimeUnit.SECONDS)){
				this.reportPassed("Successfully Logged in as : " + 
					getVariables().get("EBS_USERNAME"));
			} else{
				this.fail("Unable to log with the User ID: " +
					getVariables().get("EBS_USERNAME"));
			}
		}
	}
	
	public void expand_NodeFromList(String[] fldrs, String lnk) throws Exception{
		String lastParent = "";
		Sync();
		boolean formsLaunched = false;
		try{	
			getSettings().setErrorRecovery(FormsErrorRecovery.ERR_FORMS_FT_ERROR,
				   ErrorRecoveryAction.Ignore);
		   formsLaunched = forms.window("//forms:window[(@name='NAVIGATOR')]").exists(2, TimeUnit.SECONDS);
		   
		}catch(FormsFTException e){				
				info("Form is not launched. So using Web to navigate to the form");
					}
		if(!formsLaunched){
			for(String fldr: fldrs){
				beginStep("[3] Expand Node from the List", 0);
				{
					//web.image("/web:window[@index='0' or @title='STRDV1']/web:document[@index='0']/web:a[@text='"+fldr+"' and @href='#']/web:img[@alt='Expand']").click();
					web.element("/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='"+fldr+"']").click();
					lastParent = fldr;
				}
				endStep();
			}
			beginStep("[4] Click on the link under the Node", 0);
			{
				web.window("/web:window[@index='0' or @title='Home']").waitForPage(null);
				web.element("/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='Requests']").click();
				
				/*List<DOMElement> lnks= web.element("/web:window[@index='0' or @title='STRDV1']/web:document[@index='0']/web:a[@text='"+lastParent+"' and @href='#']")
				.getNextSibling().getChildren();
				for(DOMElement lnkText : lnks){
					if(lnkText.getAttributes().containsValue(lnk)){
						((DOMElement)lnkText.getElementsByTagName("a").get(0)).click();
					}
				}*/
			}
			endStep();
		} else{
			closeUnecessaryForms();
			StringBuilder item = new StringBuilder();
			for(String fldr: fldrs){
				if(item == null || item.toString().equals("")){
					item = item.append(fldr);
					SwitchResponsibility(fldr);
					continue;
				} else{
					String[] strngs = item.toString().split("\\|");

					if(strngs.length == 1){
						forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
						.selectItem(fldr);
						item =  new StringBuilder();
						item = item.append(fldr+"|");
					} else{
						item = item.append(fldr);
						forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
						.selectItem(item.toString());
						item = item.append(fldr+"|");
					}
				}

			}
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
			.selectItem(item.toString()+lnk);

		}
	}
	
	
	private void closeUnecessaryForms() throws Exception{
		if(!forms.window("//forms:window[(@name='NAVIGATOR')]").isActive()){
			AbstractWindow[] windows = forms.getAllOpenWindows();
			for(AbstractWindow wndw : windows){
				if(((Window)wndw).exists(1, TimeUnit.SECONDS)
						&& !((Window)wndw).getName().equalsIgnoreCase("NAVIGATOR")){
					((Window)wndw).close();
					{
						think(1.042);
					}
				}
			}
		}
	}
	private boolean isInRequiredResponsibility(String responsibility) throws Exception{
		boolean isRequired = false;
		return isRequired;
	}
	
	public void expand_NodeFromList(String[] fldrs) throws Exception{
		String lastParent = "";
		for(String fldr: fldrs){
			
				/*web.image("/web:window[@index='0' or @title='STRDV1']/web:document[@index='0']/web:a[@text='"+fldr+"' and @href='#']/web:img[@alt='Expand']")
					.click();*/
				web.image("/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:a[@text='"+fldr+"']/web:img[@alt='Expand']")
				.click();
				lastParent = fldr;
			}
	}
	
	
	public String getMonthYearFormat(int i) throws Exception{
		Date today = new Date();  
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(today);  
		calendar.add(Calendar.MONTH, i);   
		SimpleDateFormat monthDisplay = new SimpleDateFormat("MMM");
		SimpleDateFormat YearDisplay = new SimpleDateFormat("yy");
		return (monthDisplay.format(calendar.getTime() ).toUpperCase() +"-"+ YearDisplay.format(calendar.getTime()));
	}

	public void open_PeriodsForAP(int months) throws Exception{
		beginStep("[4] Organizations", 0);
		{
			for(int i=0;i < months;i++){
				String periodName = getMonthYearFormat(i);
				forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_PERIOD_NAME_0')]")
				.invokeSoftKey("ENTER_QUERY");
				forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_PERIOD_NAME_0')]")
				.setText(periodName);
				forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_PERIOD_NAME_0')]")
				.invokeSoftKey("EXECUTE_QUERY");
				if(!forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_DSP_DISPLAYED_FIELD_0')]").getText().equalsIgnoreCase("Open")){
					forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_DSP_DISPLAYED_FIELD_0')]")
					.openDialog();
					forms.listOfValues("//forms:listOfValues").select("Open");
				}
				/*forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_DSP_DISPLAYED_FIELD_0')]")
				.invokeSoftKey("NEXT_FIELD");*/
				think(1.051);				
				forms.window("//forms:window[(@name='GL_PERIOD_STATUSES')]")
				.clickToolBarButton("Save");
			} //End of For loop
			forms.window("//forms:window[(@name='GL_PERIOD_STATUSES')]")
			.close();
			forms.window(231, "//forms:window[(@name='NAVIGATOR')]").activate(true);
		}
	}



	private String getActualMaximum(Date d) {
		return null;
		// TODO Auto-generated method stub
	}

	public void open_PeriodsForAR(int months) throws Exception{
		beginStep("[4] Organizations", 0);
		{
			for(int i=0;i < months;i++){
				String periodName = getMonthYearFormat(i);
				forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_PERIOD_NAME_0')]")
				.invokeSoftKey("ENTER_QUERY");
				forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_PERIOD_NAME_0')]")
				.setText(periodName);
				forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_PERIOD_NAME_0')]")
				.invokeSoftKey("EXECUTE_QUERY");
				if(!forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_DSP_MEANING_0')]").getText().equalsIgnoreCase("Open")){
					forms.textField("//forms:textField[(@name='GL_PERIOD_STATUSES_DSP_MEANING_0')]")
					.openDialog();
					forms.listOfValues("//forms:listOfValues").select("Open");
					think(1.051);
				}
				forms.window("//forms:window[(@name='OPEN_NXT_PERIOD')]")
				.clickToolBarButton("Save");
				{
					think(1.021);
				}
			} //End of For loop
			forms.window("//forms:window[(@name='OPEN_NXT_PERIOD')]")
			.close();
			{
				think(1.01);
			}
			forms.window(231, "//forms:window[(@name='NAVIGATOR')]").activate(
				true);

		}
	}



	public void open_PeriodsForGL(int months) throws Exception{
		beginStep("[4] Organizations", 0);
		{
			forms.button("//forms:button[(@name='CONTROL_OPEN_TARGET_PERIOD_0')]").click();
			for(int i=0;i < months;i++){
				String periodName = getMonthYearFormat(i);
				//forms.button("//forms:button[(@name='CONTROL_OPEN_NEXT_PERIOD_0')]").click();
				
				forms.textField("//forms:textField[(@name='FIRST_FIRST_OPEN_PERIOD_0')]").setText(periodName);
				
				if (forms.listOfValues("//forms:listOfValues").exists()){
					forms.listOfValues("//forms:listOfValues").clickCancel();
					continue;
				}
								
				forms.button("//forms:button[(@name='CONTROL_OPEN_PERIOD_0')]").click();
				forms.window("//forms:window[(@name='FIRST')]").activate(true);
				forms.choiceBox("//forms:choiceBox").clickButton("OK");
				forms.window("//forms:window[(@name='FIRST')]").activate(true);
				forms.window("//forms:window[(@name='LATEST')]").selectMenu("View|Requests");

				forms.window("//forms:window[(@name='JOBS_QF')]").activate(true);
				forms.button("//forms:button[(@name='JOBS_QF_FIND_0')]").click();

				concurrentRequestStatus("Open Period");
				forms.window("//forms:window[(@name='JOBS')]").close();

			}
		}

	}


	public void SwitchResponsibility(String resp) throws Exception {
		if(forms.choiceBox("//forms:choiceBox").exists(2, TimeUnit.SECONDS)){
			forms.choiceBox("//forms:choiceBox").clickButton("OK");
		}
		forms.window("//forms:window[(@name='NAVIGATOR')]")
		.clickToolBarButton("Switch Responsibility...");
		think(2.0);
		forms.listOfValues("//forms:listOfValues")
		.find(resp);
		if(forms.listOfValues(24, "//forms:listOfValues").exists(2, TimeUnit.SECONDS) &&
				forms.listOfValues(24, "//forms:listOfValues").getItemCount()==0){
			forms.listOfValues(24, "//forms:listOfValues").clickCancel();
			AddMissingResponsibility(resp);
			SwitchResponsibility(resp);
		}
		if(forms.choiceBox("//forms:choiceBox").exists(2, TimeUnit.SECONDS)){
			forms.choiceBox("//forms:choiceBox").clickButton("OK");
		}
	}
	
	
	public void Queryformdata(String strfieldprop,String strfieldprop1,String value,String GLdate) throws Exception {
	
			forms.textField("//forms:textField[(@name='"+strfieldprop+"')]").invokeSoftKey("ENTER_QUERY");
			forms.textField("//forms:textField[(@name='"+strfieldprop+"')]").setText(value);
			forms.textField("//forms:textField[(@name='"+strfieldprop1+"')]").setText(GLdate);
			forms.textField("//forms:textField[(@name='"+strfieldprop+"')]").invokeSoftKey("EXECUTE_QUERY");
			think(40.0);
			}
	
	public void AddMissingResponsibility(String responsibility) throws Exception{
		beginStep("[1] Navigator - System Administrator", 0);
		{
			forms.window("//forms:window[(@name='NAVIGATOR')]")
			.clickToolBarButton("Switch Responsibility...");
			{
				think(1.592);
			}
			forms.window("//forms:window[(@name='NAVIGATOR')]").activate(
				true);
			{
				think(0.231);
			}
			forms.listOfValues("//forms:listOfValues").select(
			"System Administrator");
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
			.selectItem("Security");
			{
				think(2.724);
			}
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
			.selectItem("Security|User");
			{
				think(1.419);
			}
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
			.selectItem("Security|User|Define");
		}
		endStep();
		beginStep("[2] Users", 0);
		{
			forms.textField(199,
			"//forms:textField[(@name='USER_USER_NAME_0')]")
			.invokeSoftKey("ENTER_QUERY");
			{
				think(1.907);
			}
			forms.textField(200,
			"//forms:textField[(@name='USER_USER_NAME_0')]").setText(
			"TESTUSR1");
			{
				think(0.98);
			}
			forms.textField(201,
			"//forms:textField[(@name='USER_USER_NAME_0')]")
			.invokeSoftKey("EXECUTE_QUERY");
			{
				think(3.571);
			}
			List<String> existingRspbtls =  getResponsibilityList();
			if(!existingRspbtls.contains(responsibility)){
				addResponsibility(responsibility);
			}

		}
		endStep();
		beginStep("[6] Users", 0);
		{
			forms.window(417, "//forms:window[(@name='USER_WINDOW')]").close();
			{
				think(0.592);
			}
			forms.window(418, "//forms:window[(@name='NAVIGATOR')]").activate(
				true);
		}
		endStep();
	}


	private List<String> getResponsibilityList() throws Exception{
		List<String> rspnblts = new ArrayList<String>();
		int counter = 0;
		for(int i=0; counter<forms.blockScroller(203, "//forms:blockScroller[(@barIndex='0')]").getMaximum();i++){
			if(forms.textField("//forms:textField[(@name='USER_RESP_RESPONSIBILITY_NAME_"+i+"')]").exists(2, TimeUnit.SECONDS)){
				rspnblts.add(forms.textField("//forms:textField[(@name='USER_RESP_RESPONSIBILITY_NAME_"+i+"')]").getText());
				counter++;
			} else{
				forms.blockScroller(203, "//forms:blockScroller[(@barIndex='0')]")
				.scrollDown();
				i = i-2;
			}

		}
		return rspnblts;

	}
	private void addResponsibility(String responsibility) throws Exception{

		forms.window(395, "//forms:window[(@name='USER_WINDOW')]")
		.clickToolBarButton("New");
	
		forms.textField(396,
		"//forms:textField[(@name='USER_RESP_RESPONSIBILITY_NAME_1')]")
		.openDialog();
		forms.listOfValues(411, "//forms:listOfValues").find(
			responsibility);
		{
			think(1.795);
		}
		forms.listOfValues(412, "//forms:listOfValues")
		.select(responsibility);
		{
			think(1.598);
		}
		forms.window(415, "//forms:window[(@name='USER_WINDOW')]")
		.clickToolBarButton("Save");
		{
			think(1.598);
		}

	}
	public void selecttreelist(String strfieldprop,String strfieldvalue) throws Exception {
		forms.treeList("//forms:treeList[(@name='"+strfieldprop+"')]").focusItem(strfieldvalue);
		forms.treeList("//forms:treeList[(@name='"+strfieldprop+"')]").selectItem(strfieldvalue);
	}


	public String GetText(String strfieldprop) throws Exception {
		System.out.println("strfieldpropdata------>"+strfieldprop);
		String strData=forms.textField(9,"//forms:textField[(@name='"+strfieldprop+"')]").getText();
		info("Text: " + strData);
		return strData;
	}


	public void SaveToolbar(String strfieldprop) throws Exception {
		if (forms.window(3,"//forms:window[(@name='"+strfieldprop+"')]").exists()){
			forms.window(3,"//forms:window[(@name='"+strfieldprop+"')]").clickToolBarButton("Save");
		}
	}


	public void flexWindow(String strfieldprop,String strstepdesc)throws Exception {
		forms.flexWindow(8, "//forms:flexWindow").openDialog("+strfieldprop+", "");
		think(8.64);
		forms.listOfValues(9, "//forms:listOfValues").select(strstepdesc);
		//forms.flexWindow(10, "//forms:flexWindow").clickOk();
	}

	public String generateUniqueData(String data) throws Exception {
		Calendar cal = Calendar.getInstance();
		int minute =cal.get(Calendar.MINUTE);
		int second =cal.get(Calendar.SECOND);
		String outputData=data + "-"+ minute + second;
		System.out.println("uniquedata---->" + outputData); 
		return outputData; 
	} 

	
	public void RunInvoicevalidation(String Paygroup) throws Exception {
	
	ViewSingleRequest();
	forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
		.setText("Invoice Validation");
	forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
		.invokeSoftKey("NEXT_FIELD");
	forms.flexWindow("//forms:flexWindow").setText("Option", "","all");
	forms.flexWindow("//forms:flexWindow").setText("Pay Group","", Paygroup);
	forms.flexWindow("//forms:flexWindow").clickOk();
	SubmitandVerifyBatchProcess("Invoice Validation");
	
	}

	public void CreateViewAccountingREC() throws Exception {

		forms.window("//forms:window[(@name='ENTER_RECEIPTS')]").selectMenu("Tools|Create Accounting");
		forms.button("//forms:button[(@name='SLACEXEC_OK_BP_0')]").click();
		think(1000);   
		
		forms.choiceBox("//forms:choiceBox").clickButton("OK");

		forms.window("//forms:window[(@name='SLACEXEC')]").activate(true);

		forms.window("//forms:window[(@name='ENTER_RECEIPTS')]").activate(true);

		forms.window("//forms:window[(@name='ENTER_RECEIPTS')]").selectMenu("Tools|View Accounting");

		web.window("/web:window[@index='2' or @title='Subledger Journal Entry Lines']")
		.waitForPage(null);

		String totalamt=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@id='N696:AccountingClass:1']").getAttribute("text");
		System.out.println("receivables acct is"+totalamt);			

		String Acctnum=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@id='N696:Account:1']").getAttribute("text");
		System.out.println("receivables acct is"+Acctnum);	

		web.window("/web:window[@index='2' or @title='Subledger Journal Entry Lines']").close();
	}


	public void CreateViewAccountingTRX() throws Exception {

		forms.window("//forms:window[(@name='TMAI_HEADER')]").selectMenu("Tools|Create Accounting");
		forms.button("//forms:button[(@name='SLACEXEC_OK_BP_0')]").click();
        think(1000);   
		forms.choiceBox("//forms:choiceBox").clickButton("OK");

		forms.window("//forms:window[(@name='SLACEXEC')]").activate(true);

		forms.textField("//forms:textField[(@name='TGW_HEADER_BS_BATCH_SOURCE_NAME_MIR_0')]").setFocus();
		
		forms.window("//forms:window[(@name='TMAI_HEADER')]").selectMenu("Tools|View Accounting");

		web.window("/web:window[@index='2' or @title='Subledger Journal Entry Lines']").waitForPage(null);
		waitTillPageLoad();

		/*String invamt=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@id='N696:AccountedCr:2']").getAttribute("text");
		System.out.println("Invoice amount is"+invamt);*/
		String Accttype=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@id='N696:AccountingClass:1']").getAttribute("text");
		System.out.println("GL Acct Type is"+Accttype);		

		String Acctnum=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@id='N696:Account:1']").getAttribute("text");
		System.out.println("GL Acct num is"+Acctnum);	

		web.window("/web:window[@index='2' or @title='Subledger Journal Entry Lines']").close();
	}
	
	
	public String SubmitorCreateAccountingJob(String jobname,String ledger) throws Exception {
		String Enddate=getDateTimeFormat("format2");
		ViewSingleRequest();
		forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
			.setText(jobname);
		forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
			.invokeSoftKey("NEXT_FIELD");
		forms.flexWindow("//forms:flexWindow").openDialog("Ledger", "");
		forms.listOfValues("//forms:listOfValues").select(ledger);
		forms.flexWindow("//forms:flexWindow").setText("End Date", "",	Enddate);
		forms.flexWindow("//forms:flexWindow").openDialog("Report", "");
		forms.listOfValues("//forms:listOfValues").select("Summary");
		forms.flexWindow("//forms:flexWindow").setText("Post in General Ledger", "", "yes");
		forms.flexWindow("//forms:flexWindow").setText("General Ledger Batch Name", "", "Payment" +Enddate );
		String batchname=forms.flexWindow("//forms:flexWindow").getText("General Ledger Batch Name", "");
		forms.flexWindow("//forms:flexWindow").setTextAndClickOk("Include User Transaction Identifiers", "", "yes");
		SubmitandVerifyBatchProcess(jobname);
		return batchname;
		}
	
	
	
	public void submitAutoInvoiceProgramJob(String Source) throws Exception {
		
		String Defaultdate=getDateTimeFormat("format2");
		ViewSingleRequest();
		forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
							.setText("Autoinvoice Master Program");
		forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
								.invokeSoftKey("NEXT_FIELD");
		forms.flexWindow("//forms:flexWindow").setText("Invoice Source", "", Source);
		forms.flexWindow("//forms:flexWindow").setText("Default Date","", Defaultdate);
		forms.flexWindow("//forms:flexWindow").clickOk();
		SubmitandVerifyBatchProcess("Autoinvoice Master Program");
		
	}



	public void ERPLogout() throws Exception {

		if (forms.window("//forms:window[(@name='NAVIGATOR')]").exists()){

			forms.window("//forms:window[(@name='NAVIGATOR')]").selectMenu("File|Exit Oracle Applications");
			if(forms.choiceBox("//forms:choiceBox").exists())
			{
				info(forms.choiceBox("//forms:choiceBox").getAlertMessage());
				forms.choiceBox("//forms:choiceBox").clickButton("OK");
			}
			web.window("/web:window[@index='1' or @title='Oracle Applications*']").close();
			web.link("/web:window[@index='0' or @title='Oracle Applications Home Page']/web:document[@index='0']/web:a[@text='Logout'or @index='2']")
			.click();
			web.window("/web:window[@index='0' or @title='Login']")
			.waitForPage(null);
			web.window("/web:window[@index='0' or @title='Login']").close();
			browser.closeAllBrowsers();
		}else{
			browser.closeAllBrowsers();
		}
	}

	public void ViewSingleRequest() throws Exception {

		/// modified to work for the 12.2.6 version of oracle//
		forms.window("//forms:window[(@name='NAVIGATOR')]").selectMenu("View|Requests");
		forms.window("//forms:window[(@name='JOBS_QF')]").activate(true);
		forms.button("//forms:button[(@name='JOBS_QF_NEW_0')]").click();
		//forms.button("//forms:button[(@name='WHAT_TYPE_OK_0')]").click();
		}

	public void ViewRequestSet() throws Exception {
		/// modified to work for the 12.2.6 version of oracle//
		forms.window("//forms:window[(@name='NAVIGATOR')]").selectMenu("View|Requests");
		forms.window("//forms:window[(@name='JOBS_QF')]").activate(true);
		forms.button("//forms:button[(@name='JOBS_QF_NEW_SET_0')]").click();
		/*forms.button("//forms:button[(@name='JOBS_QF_NEW_0')]").click();
		forms.radioButton("//forms:radioButton[(@name='WHAT_TYPE_JOB_TYPE_SET_0')]").select();
		forms.button("//forms:button[(@name='WHAT_TYPE_OK_0')]").click();*/  
	}



	public String getDateTimeFormat(String data) throws Exception {
		info("insside getDateTimeFormat");
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DATE);
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
		int hour =cal.get(Calendar.HOUR_OF_DAY);
		int minute =cal.get(Calendar.MINUTE);
		int second =cal.get(Calendar.SECOND);
		cal.set(year,month,day);
		Date d=new Date(cal.getTimeInMillis());
		String month_name=new SimpleDateFormat("MMM").format(d);
		String Year_name=new SimpleDateFormat("yy").format(d);



		String outputDateFormat = "";

		String format1 = "dd-mm-yyyy,0";
		String format2 = "dd-mmm-yyyy,0";
		String add2days = "dd-mmm-yyyy,0";
		String format3 = "dd-mmm-yyyy,2";
		String format4 = "mm/dd/yyyy,0";
		String format5 = "MMM-yy,0";
		String format6 = "dd-MMM-yy,0";
		String format7 =  "lastdayofmonth";


		if (data.equals("format1")){
			outputDateFormat=day+ "-" + (month +1 ) + "-" + year;
		}
		if (data.equals("format2")){
			outputDateFormat=day + "-" + month_name + "-" + year;
			info(outputDateFormat);
		}
		
		if (data.equals("add2days")){
			outputDateFormat=(day+2) + "-" + month_name + "-" + year;
			info(outputDateFormat);
		}
		
		

		if (data.equals("format4")){
			outputDateFormat=(month +1 ) + "/" + day + "/" + year;
			info(outputDateFormat);
		}

		if (data.equals("format5")){
			outputDateFormat= month_name + "-" + Year_name;
			info(outputDateFormat);
		}

		if (data.equals("format6")){
			outputDateFormat= day + "-" +month_name + "-" + Year_name;
			info(outputDateFormat);
		}


		if (data.equals("format7")){

			Calendar calendar = Calendar.getInstance();
			int lastDate = calendar.getActualMaximum(Calendar.DATE);
			System.out.println("Date     : " + calendar.getTime());
			System.out.println("Last Date: " + lastDate);
			outputDateFormat= lastDate + "-" +month_name + "-" + Year_name;
			info(outputDateFormat);
		}
		
				


		cal.add(Calendar.DATE,2);
		Date da=new Date(cal.getTimeInMillis());
		String monthnm=new SimpleDateFormat("MMM").format(da);

		if (data.equals("format3")){
			outputDateFormat=cal.get(Calendar.DATE) + "-" + monthnm + "-" + cal.get(Calendar.YEAR);
			info(outputDateFormat);
			System.out.println("Date Format1: "+ day + "-" + (month +1 ) + "-" + year);
		}
		
		
		cal.add(Calendar.DATE,-30);
		Date pdate=new Date(cal.getTimeInMillis());
		monthnm=new SimpleDateFormat("MMM").format(da);
		
		if (data.equals("format8")){
			outputDateFormat=(month) + "/" + cal.get(Calendar.DATE) + "/" + year;;
			info(outputDateFormat);
			System.out.println("Date Format1: "+ day + "-" + (month +1 ) + "-" + year);
		}
		
		return outputDateFormat;
	}


	public String UniqueNumber(String data) throws AbstractScriptException {

		String uniquenumberFormat = "";
		Date d = new Date();
		System.out.println(" d---->" +d);
		long timestamp = d.getTime();
		String unique_num = new String();
		uniquenumberFormat = Long.toString(timestamp, 0);
		System.out.println(" unique_num---->" +uniquenumberFormat);

		if(data.equals("12digit")){
			uniquenumberFormat = uniquenumberFormat.substring(1);
			System.out.println(" unique_num---->" +uniquenumberFormat);
		}
		if(data.equals("10digit")){
			uniquenumberFormat = uniquenumberFormat.substring(3);
			System.out.println(" unique_num---->" +uniquenumberFormat);
		}
		if(data.equals("8digit")){
			uniquenumberFormat = uniquenumberFormat.substring(5);
			System.out.println(" unique_num---->" +uniquenumberFormat);
		}
		if(data.equals("6digit")){
			uniquenumberFormat = uniquenumberFormat.substring(7);
			System.out.println(" unique_num---->" +uniquenumberFormat);
		}

		if(data.equals("5digit")){
			uniquenumberFormat = uniquenumberFormat.substring(8);
			System.out.println(" unique_num---->" +uniquenumberFormat);
		}
		if(data.equals("4digit")){
			uniquenumberFormat = uniquenumberFormat.substring(9);
			System.out.println(" unique_num---->" +uniquenumberFormat);
		}
		if(data.equals("2digit")){
			uniquenumberFormat = uniquenumberFormat.substring(9);
			System.out.println(" unique_num---->" +uniquenumberFormat);
		}

		return uniquenumberFormat;
	}	




	public void concurrentRequestStatus(String requestProgramName) throws Exception
	{
		forms.window("//forms:window[(@name='JOBS')]").activate(true);
		forms.textField(530,"//forms:textField[(@name='JOBS_REQUEST_ID_0')]").setFocus();
		forms.textField(531, "//forms:textField[(@name='JOBS_PROGRAM_0')]").invokeSoftKey("ENTER_QUERY");
		forms.textField(531, "//forms:textField[(@name='JOBS_PROGRAM_0')]").setText(requestProgramName);
		forms.textField(531, "//forms:textField[(@name='JOBS_PROGRAM_0')]").invokeSoftKey("EXECUTE_QUERY");
		think(300.0);
		String reqID=forms.textField("//forms:textField[(@name='JOBS_REQUEST_ID_0')]").getText().trim();
		getVariables().set("Request ID", reqID);
		forms.textField("//forms:textField[(@name='JOBS_PROGRAM_0')]").setFocus();

		long startTime = System.currentTimeMillis();
		String reqProgramSet=forms.textField(531, "//forms:textField[(@name='JOBS_PROGRAM_0')]").getText();
		info("Program Set Name: "+reqProgramSet);
		if(reqProgramSet.trim().equalsIgnoreCase(requestProgramName))
		{
			info("Request Program Set Name: "+reqProgramSet+" is valid with given and its request ID: "+reqID);
		}
		forms.button("//forms:button[(@name='JOBS_CHANGE_QUERY_0')]").click();
		forms .radioButton("//forms:radioButton[(@name='JOBS_QF_WHICH_JOBS_SPECIFIC_JOBS_0')]") .select();
		forms.textField("//forms:textField[(@name='JOBS_QF_REQUEST_ID_0')]").setFocus();
		forms.textField("//forms:textField[(@name='JOBS_QF_REQUEST_ID_0')]").setText(reqID);
		forms.button("//forms:button[(@name='JOBS_QF_FIND_0')]").click();
		forms.window("//forms:window[(@name='JOBS')]").activate(true);
		String phase="";
		String status="";
		String flagChk="false";
		do{
			phase=forms.textField("//forms:textField[(@name='JOBS_PHASE_0')]").getText().trim();
			status=forms.textField("//forms:textField[(@name='JOBS_STATUS_0')]").getText().trim();
			think(30.0);
			if(phase.equals("Completed"))
			{
				info("Request Set ID: "+getVariables().get("Request ID")+" is phase : "+phase);
				if(status.equals("Normal"))
				{
					info("Request Set ID: "+getVariables().get("Request ID")+" is phase : "+phase+" with Status : "+status);
					flagChk="true";
					long endTime = System.currentTimeMillis();
					long seconds = (endTime - startTime) / 1000;
					info("this"  +reqProgramSet+  "program took"    +seconds+      "secs to complete"); 
				}
				else if(status.equals("Warning"))
				{
					warn("Request Set ID: "+getVariables().get("Request ID")+" is phase : "+phase+" with Status : "+status);
					long endTime = System.currentTimeMillis();
					long seconds = (endTime - startTime) / 1000;
					info("this"  +reqProgramSet+   "program took"   +seconds+   "secs to complete"); 
				}
				else if(status.equals("Error"))
				{
					reportFailure("Request Set ID: "+getVariables().get("Request ID")+" is phase : "+phase+" with Status : "+status);
					long endTime = System.currentTimeMillis();
					long seconds = (endTime - startTime) / 1000;
					info("this"  +reqProgramSet+   "program took"   +seconds+   "secs to complete"); 

					forms.button("//forms:button[(@name='JOBS_VIEW_LOG_0')]").click();
					{
						think(2.518);
					}
					info(forms.textField("//forms:textField[(@name='CONTROL_TEXT_0')]").getText());
					forms.window("//forms:window[(@name='VIEWER')]").close();
				}
				break;
			}
			else
			{
				info("Request Set ID: "+getVariables().get("Request ID")+" is phase : "+phase+" with Status Normal : "+status+" needs to click refresh button");
				forms.button(579, "//forms:button[(@name='JOBS_REFRESH_0')]") .click();
				{ 
					think(1.953);
				}
			}
		}while(!phase.equals("Completed"));

	}

	/*
	 * This method is reusable for verifying the job status.
	 * The method uses the request id from submit job method.
	 * It should be used along with the submit job process.
	 */
	public void verifyJobStatus() throws Exception{
		think(2.042);
		if(!forms.window("//forms:window[(@name='NAVIGATOR')]").isActive()){
			AbstractWindow[] windows = forms.getAllOpenWindows();
			for(AbstractWindow wndw : windows){
				if(((Window)wndw).exists(1, TimeUnit.SECONDS)
						&& !((Window)wndw).getName().equalsIgnoreCase("NAVIGATOR")){
					((Window)wndw).close();
					{
						think(1.042);
					}
				}
			}
		}
		forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);
		think(1.042);
		forms.window("//forms:window[(@name='NAVIGATOR')]").selectMenu(
		"View|Requests");
		{
			think(1.042);
		}

		forms.radioButton("//forms:radioButton[(@name='JOBS_QF_WHICH_JOBS_SPECIFIC_JOBS_0')]")
		.select();
		{
			think(1.042);
		}

		forms.textField("//forms:textField[(@name='JOBS_QF_REQUEST_ID_0')]").setText(
			getVariables().get("requestId"));

		forms.button("//forms:button[(@name='JOBS_QF_FIND_0')]")
		.click();
		{
			think(1.486);
		}
		forms.window("//forms:window[(@name='JOBS')]").activate(true);

		//If the status is No Manager, the script will report the failure
		//and closes the job window before exiting the rest of code.
		if(forms.textField("//forms:textField[(@name='JOBS_PHASE_0')]").getText().equalsIgnoreCase("No Manager")){
			reportFailure("'No Manager' status found for the request ID : "+getVariables().get("requestId")+". Please verify concurrent program manager");
			forms.window(109, "//forms:window[(@name='JOBS')]").close();
			return;
		}

		while(!forms.textField("//forms:textField[(@name='JOBS_PHASE_0')]").getText().equalsIgnoreCase("Completed")){
			think(120.0);
			forms.button("//forms:button[(@name='JOBS_REFRESH_0')]").click();
		}
		if(!forms.textField("//forms:textField[(@name='JOBS_STATUS_0')]").getText().equalsIgnoreCase("Normal")){
			beginStep("[14] Verify Request Details", 0);
			{
				forms.button(392, "//forms:button[(@name='JOBS_JOB_DETAIL_0')]")
				.click();

				info(forms.textField(455,
				"//forms:textField[(@name='JOB_DETAIL_COMPLETION_TEXT_0')]")
				.getText());
				{
					think(0.0040);
				}
				forms.button(456, "//forms:button[(@name='JOB_DETAIL_OK_0')]")
				.click();
			}
			endStep();
		}
		forms.window(109, "//forms:window[(@name='JOBS')]").close();
	}
	
	
	public void SubmitandVerifyBatchProcess(String Batchname) throws Exception {
		
   if(forms.button("//forms:button[(@name='SET_WORK_ORDER_SUBMIT_0')]").exists(5, TimeUnit.SECONDS)){
		forms.button("//forms:button[(@name='SET_WORK_ORDER_SUBMIT_0')]").click();
		 }
   else {
		forms.button("//forms:button[(@name='WORK_ORDER_SUBMIT_0')]").click();
		}
   
		String formname=forms.choiceBox("//forms:choiceBox").getTitle();
		info(formname);
		if (formname.contentEquals("Caution")){
		forms.choiceBox("//forms:choiceBox").clickButton("OK");
	   }
		forms.choiceBox("//forms:choiceBox").clickButton("No");
		forms.window("//forms:window[(@name='WORK_ORDER')]").activate(true);
		forms.button("//forms:button[(@name='JOBS_QF_FIND_0')]").click();
		concurrentRequestStatus(Batchname);
		forms.window("//forms:window[(@name='JOBS')]").activate(true);
		forms.window("//forms:window[(@name='JOBS')]").close();
	}
	
	//Modified by Jagan 4/28
	public void submit_SingleRequest(String requestName) throws Exception{
		getVariables().set("requestId","");	
		getVariables().set("jobName",requestName);
		beginStep("[1] Open View Requests from menu");
		{
			forms.window(80, "//forms:window[(@name='NAVIGATOR')]").selectMenu("View|Requests");
			
			forms.window(81, "//forms:window[(@name='JOBS_QF')]").activate(true);

			forms.button(84, "//forms:button[(@name='JOBS_QF_NEW_0')]").click();
		   			
			forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
						.setText(requestName);
			
			forms.textField("//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
						.invokeSoftKey("NEXT_FIELD");
		
			forms.button("//forms:button[(@name='WORK_ORDER_SUBMIT_0')]").click();
			if(!forms.choiceBox(109, "//forms:choiceBox").getAlertMessage().contains("=")){
				forms.choiceBox(109, "//forms:choiceBox").clickButton("OK");
			}
			
			String message = forms.choiceBox(109, "//forms:choiceBox").getAlertMessage();
			getVariables().set("requestId", message.substring(message.indexOf("=")+1, message.lastIndexOf(")")).trim());
		}
		endStep();
		beginStep("[8] Select No in the Decision");
		{
			forms.choiceBox(102, "//forms:choiceBox").clickButton("No");
			forms.window(103, "//forms:window[(@name='WORK_ORDER')]").activate(true);
		}
		endStep();
	}
	public void submit_SingleRequest_DefaultParams(String requestName) throws Exception{
		getVariables().set("jobName",requestName);
		getVariables().set("requestId","");	
		beginStep("[1] Open View Requests from menu");
		{
			forms.window(80, "//forms:window[(@name='NAVIGATOR')]").selectMenu("View|Requests");
			forms.window(81, "//forms:window[(@name='JOBS_QF')]").activate(true);
			forms.button(84, "//forms:button[(@name='JOBS_QF_NEW_0')]").click();
			forms.button(90,"//forms:button[(@name='WORK_ORDER_CREATE_LIKE_0')]").setFocus();
			forms.textField(91,	"//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
						.setText(requestName);
			forms.textField(92,"//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
					.invokeSoftKey("NEXT_FIELD");
		
		 if(forms.flexWindow(95, "//forms:flexWindow").exists())
			forms.flexWindow(95, "//forms:flexWindow").clickOk();
		
			forms.button(98, "//forms:button[(@name='WORK_ORDER_SUBMIT_0')]").click();
			String message = forms.choiceBox(109, "//forms:choiceBox").getAlertMessage();
			getVariables().set("requestId", message.substring(message.indexOf("=")+1, message.lastIndexOf(")")).trim());
			forms.choiceBox(102, "//forms:choiceBox").clickButton("No");
			forms.window(103, "//forms:window[(@name='WORK_ORDER')]").activate(true);
		}
		endStep();

	}

	public void submit_SingleRequest_CertainParams(String requestName, String... params) throws Exception{
		getVariables().set("jobName",requestName);
		getVariables().set("requestId","");	
		beginStep("[1] Open View Requests from menu");
		{
			forms.window(80, "//forms:window[(@name='NAVIGATOR')]").selectMenu("View|Requests");
			forms.window(81, "//forms:window[(@name='JOBS_QF')]").activate(true);
			forms.button(84, "//forms:button[(@name='JOBS_QF_NEW_0')]").click();
			forms.textField(91,	"//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
						.setText(requestName);
			forms.textField(92,
			"//forms:textField[(@name='WORK_ORDER_USER_CONCURRENT_PROGRAM_NAME_0')]")
			.invokeSoftKey("NEXT_FIELD");
			for(String param : params){
				String field = param.split("=")[0];
				String value = param.split("=")[1];
				if(value.equalsIgnoreCase("null")||value.equalsIgnoreCase("''")){
					value="";
				}
				forms.flexWindow("//forms:flexWindow").setText(field, "", value);
			}
			forms.flexWindow(95, "//forms:flexWindow").clickOk();
			forms.button(98, "//forms:button[(@name='WORK_ORDER_SUBMIT_0')]").click();
			String message = forms.choiceBox(109, "//forms:choiceBox").getAlertMessage();
			getVariables().set("requestId", message.substring(message.indexOf("=")+1, message.lastIndexOf(")")).trim());
			forms.choiceBox(102, "//forms:choiceBox").clickButton("No");
			forms.window(103, "//forms:window[(@name='WORK_ORDER')]").activate(true);
		}
		endStep();
	}
	
	public boolean fileExistsInLocation(String location, String fileName) throws Exception{
		String path = location +File.separator+fileName;
		File f = new File(path);
		f.getParentFile().mkdirs(); 
		f.createNewFile();
		if(f.exists()){
			return true;
		}
		return false;
	}
	public boolean fileExistsWithNamePattern(String location, String fileName) throws Exception{

		File f;
		try{
			f = new File(location);
		}catch(Exception e){
			info("The directory or location provided is invalid");
			return false;
		}

		for(File file : f.listFiles()){
			if(file.getName().startsWith(fileName)){
				return true;
			}
		}
		return false;
	}
	public void printLogResultsForProcess(String requiredDetails) throws Exception{
		think(2.042);
		if(!forms.window("//forms:window[(@name='NAVIGATOR')]").isActive()){
			AbstractWindow[] windows = forms.getAllOpenWindows();
			for(AbstractWindow wndw : windows){
				if(((Window)wndw).exists(1, TimeUnit.SECONDS)
						&& !((Window)wndw).getName().equalsIgnoreCase("NAVIGATOR")){
					((Window)wndw).close();
					{
						think(1.042);
					}
				}
			}
		}
		forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);
		think(1.042);
		forms.window("//forms:window[(@name='NAVIGATOR')]").selectMenu(
		"View|Requests");
		{
			think(1.042);
		}

		forms.radioButton("//forms:radioButton[(@name='JOBS_QF_WHICH_JOBS_SPECIFIC_JOBS_0')]")
		.select();
		{
			think(1.042);
		}

		forms.textField("//forms:textField[(@name='JOBS_QF_REQUEST_ID_0')]").setText(
			getVariables().get("requestId"));

		forms.button("//forms:button[(@name='JOBS_QF_FIND_0')]")
		.click();
		{
			think(1.486);
		}
		forms.window("//forms:window[(@name='JOBS')]").activate(true);

		//If the status is No Manager, the script will report the failure
		//and closes the job window before exiting the rest of code.
		if(forms.textField("//forms:textField[(@name='JOBS_PHASE_0')]").getText().equalsIgnoreCase("No Manager")){
			reportFailure("'No Manager' status found for the request ID : "+getVariables().get("requestId")+". Please verify concurrent program manager");
			forms.window(109, "//forms:window[(@name='JOBS')]").close();
			return;
		}

		while(!forms.textField("//forms:textField[(@name='JOBS_PHASE_0')]").getText().equalsIgnoreCase("Completed")){
			think(120.0);
			forms.button("//forms:button[(@name='JOBS_REFRESH_0')]").click();
		}
		if(!forms.textField("//forms:textField[(@name='JOBS_STATUS_0')]").getText().equalsIgnoreCase("Normal")){
			beginStep("[14] Verify Request Details", 0);
			{
				forms.button(392, "//forms:button[(@name='JOBS_JOB_DETAIL_0')]")
				.click();

				info(forms.textField(455,
				"//forms:textField[(@name='JOB_DETAIL_COMPLETION_TEXT_0')]")
				.getText());
				{
					think(0.0040);
				}
				forms.button(456, "//forms:button[(@name='JOB_DETAIL_OK_0')]")
				.click();
			}
			endStep();
		}
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		getVariables().set("testStartdate", formatter.format(cal.getTime()));
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String path = scriptPath.substring(0, scriptPath.lastIndexOf("\\"))+"\\..\\..\\Results\\ProcessedDetailsLog"+format.format(curDate)+".csv";

		utilities.getFileService().appendStringToFile(path,	getVariables().get("jobName"));
		utilities.getFileService().appendStringToFile(path, System.getProperty("line.separator"));

		if(fileExistsInLocation(scriptPath.substring(0, scriptPath
			.lastIndexOf("\\"))+"\\..\\..\\Results", 
			"OutputLog"+format.format(curDate)+".csv")){
			forms.button("//forms:button[(@name='JOBS_VIEW_LOG_0')]").click();
			String logData = forms.textField("//forms:textField[(@name='CONTROL_TEXT_0')]").getText();
			if(!logData.isEmpty()){
				String[] lines = logData.split(System.getProperty("line.separator"));
				for(String line : lines){
					if(line.contains(requiredDetails)){
						utilities.getFileService().appendStringToFile(path, line.substring(1, line.length()-1));
						utilities.getFileService().appendStringToFile(path, System.getProperty("line.separator"));
					}
				}
			}
		}
		forms.window("//forms:window[(@name='VIEWER')]").close();
		forms.window(109, "//forms:window[(@name='JOBS')]").close();
	}
	public void printResultsForProcess(String ProcessName) throws Exception{
		//Set Context to the query
		
		utilities.getSQLService().callProcedure(getVariables().get("Environment"),"begin\r\napps.mo_global.set_policy_context('S',92);\r\nend;",null);

		//should use current date -1.
		//Execute the Query
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		getVariables().set("testStartdate", formatter.format(cal.getTime()));

		Table result = executeQuery(
			"SELECT   fa.application_short_name,\r\n         frt.responsibility_name,\r\n         fcp.user_concurrent_program_name,\r\n         (SELECT f1.user_name\r\n            FROM fnd_user f1\r\n           WHERE fcr.requested_by = f1.user_id)\r\n             user_n,\r\n         ROUND ( ( ( (fcr.actual_completion_date - fcr.actual_start_date) * 24) * 60), 2) duration_minutes,\r\n         fcr.request_id,\r\n         TRUNC (fcr.actual_start_date) start_date,\r\n         fcr.requested_by,\r\n         fcr.actual_start_date,\r\n         fcr.actual_completion_date,\r\n         fcr.completion_text,\r\n         fcr.argument_text\r\n    FROM apps.fnd_concurrent_requests fcr,\r\n         fnd_responsibility_tl frt,\r\n         fnd_concurrent_programs_vl fcp,\r\n         fnd_application fa\r\n   WHERE fcr.responsibility_id = frt.responsibility_id\r\n     AND fcp.concurrent_program_id = fcr.concurrent_program_id\r\n     AND fcp.application_id = fcr.program_application_id\r\n     AND fcp.application_id = fa.application_id\r\n     AND fcr.requested_by <> 1860\r\n    -- AND fcp.user_concurrent_program_name NOT IN ('Close Discrete Jobs') -- Supress jobs that appear hundreds of times\r\n     AND fcr.requested_by IN (SELECT f1.user_id\r\n                                FROM fnd_user f1\r\n                               WHERE f1.user_name = 'TESTUSR5')\r\n     AND fcr.actual_start_date >= to_DATE ('"+getVariables().get("testStartdate")+"','DD-MON-YY')         AND  fcp.user_concurrent_program_name = '"+ProcessName+"'\r\n AND fcr.actual_start_date IS NOT NULL\r\nORDER BY fcr.actual_start_date\r\n",
			null);

		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		//Save the Results to a File
		String path = scriptPath.substring(0, scriptPath.lastIndexOf("\\"))+"\\..\\..\\Results\\OutputScript"+format.format(curDate)+".csv";

		if(fileExistsInLocation(scriptPath.substring(0, scriptPath
			.lastIndexOf("\\"))+"\\..\\..\\Results", 
			"OutputScript"+format.format(curDate)+".csv")){
			String modContent = utilities.getFileService().readAll(path);
			if(modContent.contains("APPLICATION_SHORT_NAME,RESPONSIBILITY_NAME,USER_CONCURRENT_PROGRAM_NAME,USER_N,DURATION_MINUTES,REQUEST_ID,START_DATE,REQUESTED_BY,ACTUAL_START_DATE,ACTUAL_COMPLETION_DATE,COMPLETION_TEXT,ARGUMENT_TEXT")){
				Iterator<Row> rwItr = result.getRows().iterator();
				while(rwItr.hasNext()){
					Row rw = rwItr.next();
					modContent = Arrays.toString(rw.getAll());
					utilities.getFileService().appendStringToFile(path, modContent.substring(1, modContent.length()-1));
					utilities.getFileService().appendStringToFile(path, System.getProperty("line.separator"));
				}
			} else{
				result.saveToCSVFile(path, true);
			}
		}
	}

	public void copyFileFromInputDirectory(String destDirectory, String fileName) throws Exception{
		File destinationFolder = new File(destDirectory);
		File sourceFolder = new File(scriptPath.substring(0, scriptPath.lastIndexOf("\\"))+"\\..\\..\\InputFiles");
		if (!destinationFolder.exists()){
			destinationFolder.mkdirs();
		}

		if (sourceFolder.exists() && sourceFolder.isDirectory()){
			utilities.getFileService().copyFile(sourceFolder+ "\\" + fileName, destinationFolder+ "\\" + fileName);
		} else{
			System.out.println(sourceFolder + "  Folder does not exists");
		}
	}

	public void saveFile(String filepath) throws Exception{
		try{
			Thread.sleep(3000);
			String diagText=web.dialog("/web:dialog_unknown[@winClass='##32770']").getAttribute("text");
			if(diagText.contains("You are downloading the file")){
				web.dialog("/web:dialog_unknown[@winClass='##32770']")
				.clickButton(1);
			}

			if(web.dialog("/web:dialog_unknown[@text='Do you want to open or save this file?']").exists()){
				web.dialog("/web:dialog_unknown[@text='Do you want to open or save this file?']")
				.clickButton(1);
			}


			if (web.dialog("/web:dialog_unknown[@text='Save As' or @text='Save &in:' or @winClass='##32770']").exists()){

				web.dialog("/web:dialog_unknown[@text='Save As' or @text='Save &in:' or @winClass='##32770']").setText(0, filepath);
				Thread.sleep(1000);
				web.dialog("/web:dialog_unknown[@text='Save As' or @text='Save &in:' or @winClass='##32770']").clickButton(0);
				Thread.sleep(1000);
			}

			if (web.dialog("/web:dialog_unknown[@text='File &name:' and @index='0'or @winClass='##32770']").exists()){
				web.dialog("/web:dialog_unknown[@text='File &name:' and @index='0'or @winClass='##32770']").setText(0, filepath);
				Thread.sleep(2000);
				web.dialog("/web:dialog_unknown[@text='File &name:' and @index='0' or @winClass='##32770']").clickButton(0);
				Thread.sleep(2000);
			}

			/*if(web.dialog("/web:dialog_unknown[@text='Confirm Save As' or @index='0']").exists()){
				web.dialog("/web:dialog_unknown[@text='Confirm Save As' or @index='0']").clickButton(0);
				Thread.sleep(1000);
			}*/
			
			if(web.dialog("/web:dialog_unknown[@text='Download Complete' and @index='0']").exists(3, TimeUnit.SECONDS)){
				web.dialog("/web:dialog_unknown[@text='Download Complete' and @index='0']").clickButton(2);
			}

			reportPassed("File Attached successfully");
		}catch (Exception e) {
			//reportFailure("File Attachment failed :: Exception caught ::" + e.getMessage());
		}
	}


















	public Table executeQuery(String qry) throws Exception{
		return (utilities
				.getSQLService().query(getVariables().get("Environment"),qry,
					null));
	}

	public Table executeQuery(String qry, List<Object> params) throws Exception{
		return (utilities
				.getSQLService().query(getVariables().get("Environment"),qry,
					params));
	}

	public boolean executeStatement(String statement) throws Exception{
		return (utilities
				.getSQLService()
				.execute(getVariables().get("Environment"),statement,
					null));
	}


	public boolean executeStatement(String statement,List<Object> params) throws Exception{
		return (utilities
				.getSQLService()
				.execute(getVariables().get("Environment"),statement,
					params));
	}	



	public void SOAOrderquery(String currdate) throws Exception{
		utilities.getSQLService().execute("STRQA1",	"INSERT INTO verp_vps_deployments (deployment_id,\r\n  allocation_month,\r\n     order_type,\r\n   region_code,\r\n                                    \r\n      bill_to_acct_number,\r\n                                          ship_to_acct_number,\r\n                                          customer_po_number,\r\n                                          lc_number,\r\n                                          contract_number,\r\n                                          model_item_id,\r\n                                          car_line,\r\n                                          port_code,\r\n                                          quantity,\r\n                                          created_by,\r\n                                          creation_date,\r\n                                          last_updated_by,\r\n                                          last_update_date)\r\n    WITH dealers AS\r\n    (\r\n    SELECT region_code, dealer_number, dealer_facing_port, row_number() OVER (PARTITION BY dealer_facing_port ORDER BY dealer_number) rn,\r\n    count(1) over (partition by dealer_facing_port order by null) port_count\r\n    FROM verp_ddms_dealer_hier_v\r\n    WHERE soa_dlr_flag = 'Y'\r\n      AND retail_dlr_flag = 'Y'\r\n      AND ddms_status = 'A'\r\n    ),\r\n    units AS \r\n    (\r\n    SELECT c.port_code, A.soa_model_code || '-' || A.soa_option_code || '-' || A.soa_spec_code ||'-'|| A.soa_ext_color_code mosc,\r\n    row_number() over (partition by c.port_code order by vin ) rn \r\n    FROM VERP_VIG_VEHICLES A INNER JOIN verp_vig_vessel_ports b ON A.vessel_port_id = b.vessel_port_id\r\n                            INNER JOIN verp_vig_ports c ON b.port_id = c.port_id                      \r\n    WHERE trunc (A.creation_date) = '23-MAR-16' -- !!!! Change here !!!\r\n     AND port_code  NOT IN ( '010','011')\r\n     AND vehicle_source <> 'SIA' \r\n     AND NOT exists (SELECT 1 FROM oe_order_headers_all oeh WHERE oeh.attribute2 = vin )\r\n    ),\r\n    next_allo AS\r\n    (SELECT MIN(allocation_month) allocation_month\r\n    FROM verp_vps_allo_ctrl_period\r\n    WHERE allocation_finished_flag = 'N'\r\n    ),\r\n    SUMMARY AS\r\n    (\r\n    SELECT           next_allo.allocation_month, --allocation doesn't really matter since sam doesn't really track it\r\n                     'Wholesale' order_type, --order type\r\n                     d.region_code, --region\r\n                     d.dealer_number bill_to_acct_number, --SNE standard bill to\r\n                     d.dealer_number ship_to_acct_number, --sne standard ship to \r\n                     mi.stars_model_item_id model_item_id,\r\n                     mi.car_line car_line,\r\n                     u.port_code port_code,\r\n                     count(1) qty \r\n    FROM units u LEFT OUTER JOIN dealers d ON u.port_code = d.dealer_facing_port AND MOD(u.rn,d.port_count)+1 = d.rn \r\n                 INNER JOIN verp_vps_model_items mi ON u.mosc = mi.rm_item_number\r\n                 CROSS JOIN next_allo \r\n GROUP BY next_allo.allocation_month, d.region_code, d.dealer_number,  d.dealer_number, mi.stars_model_item_id, mi.car_line, u.port_code\r\n    )\r\nSELECT   verp_vps_deployments_s.NEXTVAL,\r\n          allocation_month, --allocation doesn't really matter since sam doesn't really track it\r\n          'Wholesale' order_type, --order type\r\n          region_code, --region\r\n           bill_to_acct_number, --  bill to\r\n            ship_to_acct_number, --  ship to \r\n            NULL customer_po_number, --po\r\n            null lc_number, --lc\r\n            null contract_number, --contract number\r\n            model_item_id,\r\n            car_line,\r\n            port_code,\r\n            qty , --qty\r\n            fnd_global.user_id created_by,\r\n            SYSDATE creation_date,\r\n            fnd_global.user_id last_updated_by,\r\n            SYSDATE last_update_date \r\n from summary  \r\n",
			null);			

	}


	/*public void SDCOrderquery(String currdate) throws Exception{
		utilities.getSQLService().execute("STRQA1","INSERT INTO verp_vps_deployments (deployment_id,\r\n   allocation_month,\r\n     order_type,\r\n     region_code,\r\n                                          bill_to_acct_number,\r\n                                          ship_to_acct_number,\r\n                                          customer_po_number,\r\n                                          lc_number,\r\n                                          contract_number,\r\n                                          model_item_id,\r\n                                          car_line,\r\n                                          port_code,\r\n                                          quantity,\r\n                                          created_by,\r\n                                          creation_date,\r\n                                          last_updated_by,\r\n                                          last_update_date)\r\nWITH units AS \r\n(\r\nSELECT c.port_code, a.soa_model_code || '-' || a.soa_option_code || '-' || a.soa_spec_code ||'-'|| a.soa_ext_color_code mosc, count(1) qty\r\nFROM VERP_VIG_VEHICLES A INNER JOIN verp_vig_vessel_ports b ON A.vessel_port_id = b.vessel_port_id\r\n                        INNER JOIN verp_vig_ports c ON b.port_id = c.port_id                      \r\nWHERE trunc (A.creation_date) = '23-MAR-16' -- !!!! Change here !!!\r\nAND port_code  = '011'\r\n     AND vehicle_source <> 'SIA' \r\n     AND NOT exists (SELECT 1 FROM oe_order_headers_all oeh WHERE oeh.attribute2 = vin )\r\nGROUP BY c.port_code,  A.soa_model_code || '-' || A.soa_option_code || '-' || A.soa_spec_code ||'-'|| A.soa_ext_color_code\r\n),\r\nnext_allo AS\r\n(SELECT MIN(allocation_month) allocation_month\r\nFROM verp_vps_allo_ctrl_period\r\nWHERE allocation_finished_flag = 'N'\r\n)\r\nSELECT verp_vps_deployments_s.NEXTVAL,\r\n                 next_allo.allocation_month,\r\n                 'SDC', --order type\r\n                 '020', --region\r\n                 '020177', --SDC standard bill to\r\n                 '200011', --sdc standard ship to \r\n                 'SDC_PO' , --po\r\n                 'SDC_LC', --lc\r\n                 null, --contract number\r\n                 mi.stars_model_item_id,\r\n                 mi.car_line,\r\n                 u.port_code,\r\n                 u.qty,\r\n                 fnd_global.user_id,\r\n                 SYSDATE,\r\n                 fnd_global.user_id,\r\n                 SYSDATE\r\nFROM units u INNER JOIN verp_vps_model_items mi ON u.mosc = mi.rm_item_number\r\n             cross join next_allo    \r\n",
			null);
	}


	public void SNEOrderquery(String currdate) throws Exception{
		utilities.getSQLService().execute("STRQA1",	"INSERT INTO verp_vps_deployments (deployment_id,\r\n     allocation_month,\r\n     order_type,\r\n      region_code,\r\n                                          bill_to_acct_number,\r\n                                          ship_to_acct_number,\r\n                                          customer_po_number,\r\n                                          lc_number,\r\n                                          contract_number,\r\n                                          model_item_id,\r\n                                          car_line,\r\n                                          port_code,\r\n                                          quantity,\r\n                                          created_by,\r\n                                          creation_date,\r\n                                          last_updated_by,\r\n                                          last_update_date)\r\nWITH units AS \r\n(\r\nSELECT c.port_code, a.soa_model_code || '-' || a.soa_option_code || '-' || a.soa_spec_code ||'-'|| a.soa_ext_color_code mosc, count(1) qty\r\nFROM VERP_VIG_VEHICLES A INNER JOIN verp_vig_vessel_ports b ON A.vessel_port_id = b.vessel_port_id\r\n                        INNER JOIN verp_vig_ports c ON b.port_id = c.port_id                      \r\nWHERE trunc (A.creation_date) = '23-MAR-16' -- !!!! Change here !!!\r\nAND port_code  = '010'\r\n     AND vehicle_source <> 'SIA' \r\n     AND NOT exists (SELECT 1 FROM oe_order_headers_all oeh WHERE oeh.attribute2 = vin )\r\nGROUP BY c.port_code,  A.soa_model_code || '-' || A.soa_option_code || '-' || A.soa_spec_code ||'-'|| A.soa_ext_color_code\r\n),\r\nnext_allo AS\r\n(SELECT MIN(allocation_month) allocation_month\r\nFROM verp_vps_allo_ctrl_period\r\nWHERE allocation_finished_flag = 'N'\r\n)\r\nSELECT verp_vps_deployments_s.NEXTVAL,\r\n                 next_allo.allocation_month,\r\n                 'SNE', --order type\r\n                 '010', --region\r\n                 '010149', --SNE standard bill to\r\n                 '200010', --sne standard ship to \r\n                 'SNE_PO' , --po\r\n                 null, --lc\r\n                 null, --contract number\r\n                 mi.stars_model_item_id,\r\n                 mi.car_line,\r\n                 u.port_code,\r\n                 u.qty,\r\n                 fnd_global.user_id,\r\n                 SYSDATE,\r\n                 fnd_global.user_id,\r\n                 SYSDATE\r\nFROM units u INNER JOIN verp_vps_model_items mi ON u.mosc = mi.rm_item_number\r\n             CROSS JOIN next_allo \r\n",
			null);
	}

	public void VPSSAMOrderquery() throws Exception{
		
		utilities.getSQLService().execute("STRQA1",
			"DECLARE\r\n    c_count_toward_plan   CONSTANT VARCHAR2 (1) := 'N';\r\n    l_return_status                VARCHAR2 (1);\r\n    l_msg_data                     VARCHAR2 (4000);\r\nBEGIN\r\n    FOR r_deployment IN (SELECT   a.allocation_month,\r\n                                  a.order_type,\r\n                                  a.region_code,\r\n                                  a.bill_to_acct_number,\r\n                                  c.ddms_dealer_name bill_to_name,\r\n                                  c.ddms_status bill_to_status,\r\n                                  a.ship_to_acct_number,\r\n                                  d.ddms_dealer_name ship_to_name,\r\n                                  d.ddms_status ship_to_status,\r\n                                  a.customer_po_number,\r\n                                  a.lc_number,\r\n                                  a.contract_number,\r\n                                  a.car_line,\r\n                                  SUM (quantity) num_units\r\n                             --  b.rm_item_number,\r\n                             --  a.port_code,\r\n                             --  a.quantity\r\n                             FROM verp_vps_deployments a\r\n                                  INNER JOIN verp_vps_model_items b\r\n                                      ON a.model_item_id = b.stars_model_item_id\r\n                                  INNER JOIN verp_ddms_dealer_hier_v c\r\n                                      ON a.bill_to_acct_number = c.dealer_number\r\n                                  INNER JOIN verp_ddms_dealer_hier_v d\r\n                                      ON a.ship_to_acct_number = d.dealer_number\r\n                            WHERE 1 = 1                                 --a.order_type = 'Wholesale'\r\n                              --AND a.car_line = 'Forester'\r\n                              AND c.ddms_status = 'A'\r\n                              AND d.ddms_status = 'A'\r\n                              AND TRUNC (a.creation_date) = TRUNC (SYSDATE) -- !!!! Change here !!!\r\n                         --AND c.dealer_number <> '550103'\r\n                         GROUP BY a.allocation_month,\r\n                                  a.order_type,\r\n                                  a.region_code,\r\n                                  a.bill_to_acct_number,\r\n                                  c.ddms_dealer_name,\r\n                                  c.ddms_status,\r\n                                  a.ship_to_acct_number,\r\n                                  d.ddms_dealer_name,\r\n                                  d.ddms_status,\r\n                                  a.customer_po_number,\r\n                                  a.lc_number,\r\n                                  a.contract_number,\r\n                                  a.car_line\r\n                         ORDER BY region_code, bill_to_acct_number, ship_to_acct_number\r\n                         )\r\n    LOOP\r\n\r\n        DBMS_OUTPUT.put_line ('Attempting to create vons for... ');\r\n        DBMS_OUTPUT.put_line ('...allocation_month         => ' || r_deployment.allocation_month);\r\n        DBMS_OUTPUT.put_line ('...order_type               => ' || r_deployment.order_type);\r\n        DBMS_OUTPUT.put_line (\r\n            '...bill_to_acct_number      => ' || r_deployment.bill_to_acct_number);\r\n        DBMS_OUTPUT.put_line (\r\n            '...ship_to_acct_number      => ' || r_deployment.ship_to_acct_number);\r\n        DBMS_OUTPUT.put_line ('...customer_po_number       => ' || r_deployment.customer_po_number);\r\n        DBMS_OUTPUT.put_line ('...lc_number                => ' || r_deployment.lc_number);\r\n        DBMS_OUTPUT.put_line ('...contract_number          => ' || r_deployment.contract_number);\r\n        DBMS_OUTPUT.put_line ('...car_line                 => ' || r_deployment.car_line);\r\n        DBMS_OUTPUT.put_line ('...count_against_allo_plan  => ' || c_count_toward_plan);\r\n        DBMS_OUTPUT.put_line ('... This will be for  ' || r_deployment.num_units || ' units.');\r\n\r\n        verp_vps_von_creation_pkg.create_sam_orders (\r\n            p_from_dqm_or_allocation    => 'DQM',\r\n            p_allocation_month          => r_deployment.allocation_month,\r\n            p_order_type                => r_deployment.order_type,\r\n            p_bill_to_acct_number       => r_deployment.bill_to_acct_number,\r\n            p_ship_to_acct_number       => r_deployment.ship_to_acct_number,\r\n            p_customer_po_number        => r_deployment.customer_po_number,\r\n            p_lc_number                 => r_deployment.lc_number,\r\n            p_contract_number           => r_deployment.contract_number,\r\n            p_car_line                  => r_deployment.car_line,\r\n            p_count_against_allo_plan   => c_count_toward_plan,\r\n            x_return_status             => l_return_status,\r\n            x_msg_data                  => l_msg_data);\r\n\r\n        DBMS_OUTPUT.put_line ('Return status is ' || l_return_status);\r\n        DBMS_OUTPUT.put_line ('Message Data is ' || l_msg_data);\r\n\r\n        IF l_return_status = 'S'\r\n        THEN\r\n            DBMS_OUTPUT.put_line ('Commiting');\r\n            COMMIT;\r\n        ELSE\r\n            DBMS_OUTPUT.put_line ('Rollback');\r\n ROLLBACK;\r\n END IF;\r\n END LOOP;\r\nEND;\r\n",
			null);
	}*/
	
	
	public void Sync() throws Exception{
		delay(8000);
	}
	public void click(String locator) throws Exception{
		Sync();
		if(web.element("//web:a[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.element("//web:a[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").focus();
			web.element("//web:a[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").click();
			info("Clicked on "+locator+" link");
		}
		else if(web.button("//web:button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.button("//web:button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").focus();
			web.button("//web:button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").click();
			info("Clicked on "+locator+" button");
		}
		else if(web.button("//web:input_button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.button("//web:input_button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").focus();
			web.button("//web:input_button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").click();
			info("Clicked on "+locator+" button");
		}
		else if(web.image("//web:img[@alt='"+locator+"' or @id='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.image("//web:img[@alt='"+locator+"' or @id='"+locator+"']").focus();
			web.image("//web:img[@alt='"+locator+"' or @id='"+locator+"']").click();
			info("Clicked on "+locator+" button");
		}
		waitTillPageLoad();
	}
	public void waitTillPageLoad() throws Exception{
		String browserReady = web.getFocusedWindow().getReadyState();
		while(!browserReady.equals("complete")){
			Thread.sleep(2000);
			browserReady = web.getFocusedWindow()
			.getReadyState();
		}
	}

	public void setText_Web(String locator,String value) throws AbstractScriptException{
		try {
			web.textBox("//web:input_text[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").focus();
			web.textBox("//web:input_text[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").setText(value);
			web.textBox("//web:input_text[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").pressTab();
			waitTillPageLoad();
			reportPassed("Sucessfully entered the value");
		} catch (Exception e) {
			reportFailure("Failed to enter the value in the textbox");
			e.printStackTrace();
		}
	}
	public void setTextArea_Web(String locator,String value) throws AbstractScriptException
	{
	    try {
			web.textArea("//web:textarea[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").focus();
			web.textArea("//web:textarea[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").setText(value);
			web.textArea("//web:textarea[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").pressTab();
			waitTillPageLoad();
			reportPassed("Sucessfully entered the value");


		} catch (Exception e) {
			reportFailure("Failed to enter the value in the textbox");
			e.printStackTrace();
		}
	}
	
	public void selectRadioButton_Web(String locator) throws AbstractScriptException{
		try {
			web.radioButton("//web:input_radio[@id='"+locator+"' or @name='"+locator+"']")
			.select();
			reportPassed("Selected the Radio button with the locator " +locator);
				waitTillPageLoad();

		} catch (Exception e) {
			reportFailure("Not able to select the Radio button with the locator " +locator);
			e.printStackTrace();
		}
	}
	public void selectValueComboBox_Web(String locator,String option) throws Exception {
		try {
			web.selectBox("//web:select[(@id='"+locator+"' or @name='"+locator+"') and multiple mod 'False']").selectOptionByText(option);
			reportPassed("Selected the value from the  " +locator);
			waitTillPageLoad();
		} catch (Exception e) {
			reportFailure("Not able to select the value " +option);
			e.printStackTrace();
			throw e;
		}
	}
	public void selectCheckBox_Web(String locator,boolean option) throws Exception {
		try {
			web.checkBox("//web:input_checkbox[@id='"+locator+"' or @name='"+locator+"']").check(option);
			reportPassed("Checked the checkbox : " +locator);
		waitTillPageLoad();
		} catch (Exception e) {
			reportFailure("Not able to select the checkbox " +locator);
			e.printStackTrace();
			throw e;
		}
	}
	public void verifyElementProperty(DOMElement element,String property, String value, TestOperator operator) throws Exception{
		try {
			element.verifyAttribute("Verifying the properties of the element", property, value, operator);
		    waitTillPageLoad();
		} catch (AbstractScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	

	/**
	 * @Method Name : captureScreenShot
	 * @Description : Method to capture active screen shot
	 * @Author : Jagan Gottimukkala
	 * @Param dateChange :
	 *            dateChange indicates expected date format.
	 * @Throws Exception 
	 * @Last Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public String captureScreenShot() throws Exception{
		String scriptPath = getScriptPackage().getScriptPath();            
		String resultFolder = scriptPath.substring(0, scriptPath.lastIndexOf("\\"))+"\\results";
		String screenPrints = GetRecentfold(resultFolder)+"\\ScreenShots";
		createFolder(screenPrints);
		DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_mm");
		String uniqueDateTime  = df.format(new Date());
		String imagePath = screenPrints+"\\"+uniqueDateTime + ".jpg";

		//Capture the Screen Shot
		ft.getScreenCapture(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height,imagePath);		
		return imagePath;
	}
	/**
	 * @Method Name : createFolder
	 * @Description : Method to create a folder
	 * @Author      : Jagan Gottimukkala
	 * @Param dateChange :
	 *            dateChange indicates expected date format.
	 * @Throws Exception 
	 * @Last Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public void createFolder(String folderName) throws FileNotFoundException{
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			new File(folderName).mkdirs();
		}
	}
	/**
	 * @Method Name : GetRecentfold
	 * @Description : Method to get the recently created folder name in the given directory
	 * @Author : Jagan Gottimukkala
	 * @Param dateChange :
	 *            dateChange indicates expected date format.
	 * @Throws Exception 
	 * @Last Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public String GetRecentfold(String directoryPath) throws FileNotFoundException{

		File dir = new File(directoryPath);
		File[] files = dir.listFiles();
		String foldnam = directoryPath;

		if (files.length == 0) {
			return null;
		} 

		File lastModifiedFile = files[0];

		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
				foldnam = files[i].getPath();
			}
		}
		return foldnam;
	}  
	/**
	 * @Method Name : randomString
	 * @Description : Method to get the random string
	 * @Author : Jagan Gottimukkala
	 * @Param objectValue :        
	 * @throws : AbstractScriptException
	 * @Last Modified/Updated by: Rajendra Chary
	 */
	public String randomString(String length) throws Exception
	{
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength;
		targetStringLength = Integer.parseInt(length);	  

		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) 
			(new Random().nextFloat() * (rightLimit - leftLimit));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}
	
	public void writeResult(Boolean result,String resultFilePath,String scriptRowNo,String scriptResultPath) throws Exception
    {
        datatable.importExcel(resultFilePath, true);
        
        String scriptResult = null;
        
        if(result == true)
        {
              scriptResult = "Pass";
        }
        else if(result == false)
        {
              scriptResult = "Fail";
        }
        datatable.setValue(Integer.parseInt(scriptRowNo) , "Result", scriptResult);
        datatable.setValue(Integer.parseInt(scriptRowNo) , "Result Path", "=HYPERLINK(\""+GetRecentfold( scriptResultPath )+"\\"+"BasicReport.htm\",\"Clik for Result File\")" );
        datatable.exportToExcel(resultFilePath );
        
        
  }
	
	public void verifyPageExists_Web(String title) throws AbstractScriptException
	{
		if (web.getFocusedWindow().getTitle().equalsIgnoreCase(title))
			reportPassed("Page with the title "+ title +"exists");
			else
			reportFailure("Page with the title "+ title +" does not exists");
	}
	
	
	
	

	
	
	
	public void pymtbatchprocess(String batchname) throws Exception{
			
			SwitchResponsibility("SOA Vehicles Payables Manager");
			selecttreelist("NAVIGATOR_LIST_0", "Payments");
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Payments|Entry");
			selecttreelist("NAVIGATOR_LIST_0", "Payments|Entry|Payments Manager");
			web.window("/web:window[@index='2' or @title='Payments Dashboard']")
						.waitForPage(null);
			
			
			String Batchnum=generateUniqueData(batchname);
			web.link("/web:window[@index='2' or @title='Payments Dashboard']/web:document[@index='0']/web:a[@text='Templates' or @index='7']")
						.click();
			web.window("/web:window[@index='2' or @title='Payment Process Request Templates']")
						.waitForPage(null);
			web.textBox("/web:window[@index='2' or @title='Payment Process Request Templates']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchTempName' or @name='SearchTempName' or @index='0']")
						.click();
			web.textBox("/web:window[@index='2' or @title='Payment Process Request Templates']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchTempName' or @name='SearchTempName' or @index='0']")
						.setText(batchname);
			web.button("/web:window[@index='2' or @title='Payment Process Request Templates']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@value='Go' or @index='0']")
						.click();
			think(100);
			web.image("/web:window[@index='2' or @title='Payment Process Request Templates']/web:document[@index='0']/web:img[@alt='Submit Single Request' or @index='31']")
						.click();
			waitTillPageLoad();
			
			/*String pymttemplate=web.element("/web:window[@index='2' or @title='Payment Process Request Templates']/web:document[@index='0']/web:span[@id='TemplateSearchTable:TemplateName:0' or @index='24']").getAttribute("text");
			  info(pymttemplate);*/	
			
			web.textBox("/web:window[@index='2' or @title='Submit Payment Process Request']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='CheckrunName' or @name='CheckrunName' or @index='0']")
						.click();
				
			web.textBox("/web:window[@index='2' or @title='Submit Payment Process Request']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='CheckrunName' or @name='CheckrunName' or @index='0']")
						.setText(Batchnum);
						think(30);
			
			String Batchnumber=web.textBox("/web:window[@index='2' or @title='Submit Payment Process Request']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='CheckrunName' or @name='CheckrunName' or @index='0']")
			    				.getAttribute("value");
						info("the Batchnum is   "+Batchnumber);
				
			web.button("/web:window[@index='2' or @title='Submit Payment Process Request']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='Submit' or @value='Submit' or @index='1']")
						.click();
			
			web.window("/web:window[@index='2' or @title='Payment Process Requests']")
					   .waitForPage(null);
			
			
			web.textBox("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchCheckrunName' or @name='SearchCheckrunName' or @index='0']")
						.click();
				
			web.textBox("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchCheckrunName' or @name='SearchCheckrunName' or @index='0']")
						.setText(Batchnumber);
				
			web.button("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@value='Go' or @index='3']")
						.click();
				
			web.window("/web:window[@index='2' or @title='Payment Process Requests']")
						.waitForPage(null);
				
			String pymtbatchname=web.element("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:span[@id='ResultsTable:NOLINK:0' or @index='18']")
						.getAttribute("text");
			
			
			for(int i=0;i<250;i++)
			{	
			web.button("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='ResultsTable:Refresh:0' or @value='Refresh Status' or @index='7']")
					.click();
			
			think(50);
			String pymtbatchStatus=web.element("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:span[@id='ResultsTable:StatusCodeDsp:0']")
			   .getAttribute("text");
			
			info(pymtbatchStatus);
			
			if (pymtbatchStatus.contentEquals("Cancelled - No Invoices Selected")){
				info("Test Data issue as No Invoices exist to Select");
				break;
				//System.exit(0);
				}
						
			if (pymtbatchStatus.contentEquals("Pending Proposed Payment Review")){
				
				web.image("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:img[@alt='Take Action' or @index='41' or @src='http://soa057.subaru1.com:8080/OA_MEDIA/takeaction_enabled.gif']")
						.click();
				web.window("/web:window[@index='2' or @title='Review Proposed Payments: Payment Process Request ACH-5736']")
						.waitForPage(null);
				String totalpymts=web.element("/web:window[@index='2' or @title='Review Proposed Payments: Payment Process Request ACH-5736']/web:document[@index='0']/web:span[@id='Total']")
									.getAttribute("text");
				web.button("/web:window[@index='2' or @title='Review Proposed Payments: Payment Process Request ACH-5736']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='Go' or @value='Go' or @index='1']")
							.click();
				web.button("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@value='Go' or @index='3']")
						  .click();
				}
			
			if (pymtbatchStatus.contentEquals("Formatting")){
				info("Payments for invoice are getting processed");
				think(50);
				forms.window("//forms:window[(@name='NAVIGATOR')]");
				forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);
				think(1.042);
				forms.window("//forms:window[(@name='NAVIGATOR')]").selectMenu("View|Requests");
				forms.button("//forms:button[(@name='JOBS_QF_FIND_0')]").click();
				concurrentRequestStatus("Format Payment Instructions");
			}

				
				// the below step is optional //	
			
			if (pymtbatchStatus.contentEquals("Confirmed Payment")){
				web.link("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:a[@text='Show' or @index='23']").click();
				String paymentnum=web.link("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:a[id='PmtInstructionsTable___PmtInstructionsVO1___0:LINK:0']").getDisplayText();
				info(paymentnum);
				String paymentstatus=web.element("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:span[id='PmtInstructionsTable___PmtInstructionsVO1___0:Status:0']").getAttribute("text");
				info("Payment Status for the BAtch is" +paymentstatus);
				web.link("/web:window[@index='2' or @title='Payment Process Requests']/web:document[@index='0']/web:a[@id='PmtInstructionsTable___PmtInstructionsVO1___0:LINK:0']").click();
				String noofpayments=web.element("/web:window[@index='2' or @title='Payment Instruction: 271311']/web:document[@index='0']/web:span[@id='PmtSummaryTable_Payments_totalCell']").getAttribute("text");
				info("Total Payments processed for fleet are" +noofpayments);
				String paymentformat=web.element("/web:window[@index='2' or @title='Payment Instruction: 271311']/web:document[@index='0']/web:span[@id='PmtInsFormat']").getAttribute("text");
				info(paymentformat);
				web.link("/web:window[@index='2' or @title='Payment Instruction: 271311']/web:document[@index='0']/web:a[@title='Return to ']").click(); 
				web.window("/web:window[@index='2' or @title='Payment Instruction: 271311']").close();
				break;
					}
			
			think(100);
		  }
	}	
	
				
	public void captureformserror(String Message,String scriptpath,String testcasename) throws Exception{
			String Result = null;			
		if(forms.choiceBox("//forms:choiceBox").exists(3,TimeUnit.SECONDS)){
		   String Alertmsg=forms.choiceBox("//forms:choiceBox").getAlertMessage();
	       forms.captureScreenshot();
		   reportFailure("unexpected window with message:" +Alertmsg);
		   failresults(scriptpath,Result,Alertmsg, testcasename);
		   fail(Message);
			}
			
		}		
	
	
	public void writeresults(String scriptpath,String testcasename,String Reqnum,String POnum,String SupplierLocation,String ReqStatus,String Supplier,String Result) throws Exception {
		
		   Calendar cal = Calendar.getInstance();
	       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	       String datetime=  sdf.format(cal.getTime()) ;
	       String todaysdate=getDateTimeFormat("format2");
	       utilities.getFileService().appendStringToFile(scriptpath+""+"/TestResults.CSV", (testcasename)+","+eval(datetime)+","+eval(todaysdate)+","+ eval(Reqnum)+ "," + eval(POnum)+ ","+ eval(SupplierLocation)+ "," + eval(ReqStatus)+ "," + eval(Supplier)+ "," + eval(Result)+"\n");
		}
	
	
	public void failresults(String scriptpath,String testcasename,String Result,String Alertmsg) throws Exception {
		
		   Calendar cal = Calendar.getInstance();
	       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	       String datetime=  sdf.format(cal.getTime()) ;
	       String todaysdate=getDateTimeFormat("format2");	
	       utilities.getFileService().appendStringToFile(scriptpath+""+"/TestResults.CSV", eval(testcasename)+","+eval(datetime)+","+eval(todaysdate)+","+ eval(Alertmsg)+ "," + eval(Result)+"\n");
	}
	
	
	

	public void finish() throws Exception {
	}
}



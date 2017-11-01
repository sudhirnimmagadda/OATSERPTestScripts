import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import oracle.oats.scripting.modules.formsFT.api.*;
import oracle.oats.scripting.modules.applet.api.*;
import lib.*;

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.applet.api.AppletService applet;
	@ScriptService oracle.oats.scripting.modules.formsFT.api.FormsService forms;
	@ScriptService oracle.oats.scripting.modules.datatable.api.DataTableService datatable;
	@FunctionLibrary("EBS_Reusable_Methods") lib.Yahoo.EBS_Reusable.EBS_Reusable_Methods eBS_Reusable_Methods;

	public void initialize() throws Exception {
		browser.launch();
		eBS_Reusable_Methods.login_EBS();
		eBS_Reusable_Methods.expand_NodeFromList(new String[]{"System Administrator","Concurrent"}, "Requests");
		forms.window("//forms:window[(@name='JOBS_QF')]").close();
		forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);	
		think(100);
		//@obfuscate.
		  }
	

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {
				
		/// getting script path//
		String scriptPath = getScriptPackage().getScriptPath();
		info(scriptPath);
		int path=scriptPath.lastIndexOf("\\");
		String path1=scriptPath.substring(0, path);
		int path2=path1.lastIndexOf("\\");
		String scriptpath=scriptPath.substring(0, path2);
		info(scriptpath);
		
		//// getting test case name////
		String test=scriptPath.substring(path+1);
		int testcase=test.lastIndexOf(".");
		String testcasename=test.substring(0, testcase);
		info(testcasename);
		
		datatable.importExcel("C:\\OracleATS\\OFT\\Purchasing\\TestData\\POValidationData.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        
        String username=(String)datatable.getValue(1, "A");
        String Personname=(String)datatable.getValue(1, "B");
        String Resp=(String)datatable.getValue(1, "C");
        String Description=(String)datatable.getValue(1, "D");
        String Categorylist=(String)datatable.getValue(1, "E");
        
		
		eBS_Reusable_Methods.SwitchResponsibility("System Administrator");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Security");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Security");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Security|User");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Security|User|Define");
		forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").invokeSoftKey("ENTER_QUERY");
		forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").setText(username);
		forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").invokeSoftKey("EXECUTE_QUERY");
		String usernm=eBS_Reusable_Methods.GetText("USER_USER_NAME_0");
		
		if (usernm.contentEquals(username)){
			reportPassed("user exists");
			}
		else {
			fail("user doesnot exists");
			
			}
		
		String employeename=eBS_Reusable_Methods.GetText("USER_EMPLOYEE_NAME_0");
		if (employeename.contentEquals(Personname)){
			reportPassed("person assigned");
			}
		else {
			fail("person not assigned");
			}
		
		forms.textField("//forms:textField[(@name='USER_RESP_RESPONSIBILITY_NAME_0')]").invokeSoftKey("ENTER_QUERY");
		forms.textField("//forms:textField[(@name='USER_RESP_RESPONSIBILITY_NAME_0')]").setText(Resp);
		forms.textField("//forms:textField[(@name='USER_RESP_RESPONSIBILITY_NAME_0')]").invokeSoftKey("EXECUTE_QUERY");
		
		//String respname=eBS_Reusable_Methods.GetText("USER_RESP_RESPONSIBILITY_NAME_0");
		String Appname=eBS_Reusable_Methods.GetText("USER_RESP_APPLICATION_NAME_0");
		
		if (Appname.contentEquals("")){
			fail("Responsbility not assigned");
			}
		else {
			reportPassed("Responsbility is assigned for this user");
			}
		
		String msg=forms.statusBar("//forms:statusBar").getText();
		info(msg);
		forms.window("//forms:window[(@name='USER_WINDOW')]").close();
		forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);
		eBS_Reusable_Methods.SwitchResponsibility("Purchasing Super User");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Setup");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Setup");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Setup|Personnel");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Setup|Personnel|Buyers");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Setup|Personnel|Buyers");
		web.textBox("/web:window[@index='2' or @title='Buyers']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchBuyerName' or @name='SearchBuyerName' or @index='0']")
					.click();
		web.textBox("/web:window[@index='2' or @title='Buyers']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchBuyerName' or @name='SearchBuyerName' or @index='0']")
		            .setText(Personname);
		web.button("/web:window[@index='2' or @title='Buyers']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@index='2']").click();
		think(10);
		
		String buyername=web.element("/web:window[@index='2' or @title='Buyers']/web:document[@index='0']/web:span[@text='oats, user' or @id='BuyerDetailTableRN:BuyerName:0' or @index='30']")
					.getAttribute("text");
		info(buyername);
		String empnum=web.element("/web:window[@index='2' or @title='Buyers']/web:document[@index='0']/web:span[@text='7799' or @id='BuyerDetailTableRN:EmployeeNumber:0' or @index='33']").getAttribute("text");
						info(empnum);
		
		web.textBox("/web:window[@index='2' or @title='Buyers']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='BuyerDetailTableRN:EndDate:0' or @name='BuyerDetailTableRN:EndDate:0' or @index='6']")
		  			.getAttribute("value");
		
		String text=web.table("/web:window[@index='2' or @title='Buyers']/web:document[@index='0']/web:td[@text='No results found.' or @index='86']").getDisplayText();
		info(text);
		
		if (buyername.contentEquals(Personname)){
			reportPassed("user is setup as buyer");
			}
		else {
			fail("user is not setup as buyer");
			}

	}

	public void finish() throws Exception {
	}
}

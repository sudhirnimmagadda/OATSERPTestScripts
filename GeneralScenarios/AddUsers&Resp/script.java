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
		}

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {
		datatable.importExcel("C:\\OracleATS\\OFT\\GeneralScenarios\\TestData\\UserandResponsbilityCreation.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        
        String username=(String)datatable.getValue(1, "A");
        String pwd=(String)datatable.getValue(1, "B");
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
			reportPassed("user Already Exists");
			}
		else {
			eBS_Reusable_Methods.createuser(username, pwd);
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Security|User|Define");
			forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").invokeSoftKey("ENTER_QUERY");
			forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").setText(username);
			forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").invokeSoftKey("EXECUTE_QUERY");
			think(5);
			}
		
		 for (int i=1; i<rowcnt;i++){
		String Resp=(String)datatable.getValue(i, "C");
		eBS_Reusable_Methods.addResponsibility(Resp);
		 }
		
	}

	public void finish() throws Exception {
	}
}

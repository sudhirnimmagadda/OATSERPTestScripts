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
		
		
		datatable.importExcel("C:\\OracleATS\\OFT\\GL Sceanrios\\TestData\\GLSheet.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        
        String DrAccount=(String)datatable.getValue(1, "A");
        info(DrAccount);
        String CrAccount=(String)datatable.getValue(1, "B");
        info(CrAccount);
        String Amount=(String)datatable.getValue(1, "C");
        info(Amount);
		
		eBS_Reusable_Methods.SwitchResponsibility("General Ledger Super User");
		forms.treeList(114, "//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals");
		forms.treeList(161, "//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals|Enter");
		forms.textField(26,"//forms:textField[(@name='FOLDER_QF_BATCH_NAME_0')]").setFocus();
		forms.button(27, "//forms:button[(@name='FOLDER_QF_NEW_HEADER_0')]").click();
		
		String JE=eBS_Reusable_Methods.generateUniqueData("OATSUser");
		forms.textField("//forms:textField[(@name='HEADER_NAME_0')]").setText(JE);
		String JEname=eBS_Reusable_Methods.GetText("HEADER_NAME_0");
		forms.textField("//forms:textField[(@name='HEADER_USER_JE_CATEGORY_NAME_0')]").openDialog();
		forms.window(32, "//forms:window[(@name='HEADER')]").activate(true);
		forms.listOfValues(33, "//forms:listOfValues").select("Adjustment|Adjusting Journal Entry");
		
		forms.textField("//forms:textField[(@name='HEADER_PERIOD_NAME_0')]").setFocus();
			
		forms.textField("//forms:textField[(@name='LINES_JE_LINE_NUM_0')]").setText("1");
			
		forms.textField("//forms:textField[(@name='LINES_JE_LINE_NUM_0')]").invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_0')]").setText(DrAccount);
		
		forms.textField("//forms:textField[(@name='LINES_ENTERED_DR_0')]").setText(Amount);
		
		forms.textField("//forms:textField[(@name='LINES_ENTERED_DR_0')]").invokeSoftKey("NEXT_FIELD");
		
		forms.textField("//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_1')]").setText(CrAccount);
		
		forms.textField("//forms:textField[(@name='LINES_ENTERED_CR_1')]").setText(Amount);
		forms.textField("//forms:textField[(@name='LINES_ENTERED_CR_1')]").invokeSoftKey("NEXT_FIELD");
		
		forms.window("//forms:window[(@name='HEADER')]").clickToolBarButton("Save");
		
		if (forms.button("//forms:button[(@name='CONTROL_HEADER_APPROVE_0')]").isEnabled()){
		forms.button("//forms:button[(@name='CONTROL_HEADER_APPROVE_0')]").click();
		}
		else
		{
			String buttonname=forms.button("//forms:button[(@name='CONTROL_HEADER_APPROVE_0')]").getLabel();
			reportFailure(buttonname +"button is disabled");
		}
		String Approval=forms.choiceBox("//forms:choiceBox").getAlertMessage();
		info(Approval);
		forms.choiceBox("//forms:choiceBox").clickButton("OK");
		
		forms.button("//forms:button[(@name='CONTROL_HEADER_POST_0')]").click();
		forms.choiceBox("//forms:choiceBox").clickButton("OK");
		think(10);
		forms.window("//forms:window[(@name='HEADER')]").close();
		forms.window("//forms:window[(@name='FOLDER')]").close();
		
		forms.window("//forms:window[(@name='NAVIGATOR')]").selectMenu("View|Requests");
		//forms.window("//forms:window[(@name='JOBS_QF')]").activate(true);
		//forms.window("//forms:window[(@name='WORK_ORDER')]").activate(true);
		forms.button("//forms:button[(@name='JOBS_QF_FIND_0')]").click();
		eBS_Reusable_Methods.concurrentRequestStatus("Posting: Single Ledger");
		forms.window("//forms:window[(@name='JOBS')]").activate(true);
		forms.window("//forms:window[(@name='JOBS')]").close();
		
		
		forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]").click();
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals|Enter");
		forms.textField("//forms:textField[(@name='FOLDER_QF_BATCH_NAME_0')]").setFocus();
		
		forms.textField("//forms:textField[(@name='FOLDER_QF_HEADER_NAME_0')]").setText(JEname);
		forms.textField("//forms:textField[(@name='FOLDER_QF_HEADER_NAME_0')]").invokeSoftKey("NEXT_FIELD");
		forms.button("//forms:button[(@name='FOLDER_QF_FIND_0')]").click();
		String JEstatus=eBS_Reusable_Methods.GetText("FOLDER_SHOW_BATCH_STATUS_0");
		info(JEstatus);
		
		if (JEstatus.equalsIgnoreCase("Posted")) {
			reportPassed("Journal Posted Sucessfully");
		}
		
		
		
	
			
		
	}

	public void finish() throws Exception {
	}
}

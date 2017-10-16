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
		
		eBS_Reusable_Methods.SwitchResponsibility("General Ledger Super User");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Journals");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals|Enter");
		forms.textField("//forms:textField[(@name='FOLDER_QF_BATCH_NAME_0')]").setFocus();
		forms.textField("//forms:textField[(@name='FOLDER_QF_LEDGER_NAME_0')]").openDialog();
		forms.textField("//forms:textField[(@name='FOLDER_QF_USER_JE_SOURCE_NAME_0')]").setFocus();
		forms.textField("//forms:textField[(@name='FOLDER_QF_USER_JE_CATEGORY_NAME_0')]").setText("acc");
		forms.textField("//forms:textField[(@name='FOLDER_QF_USER_JE_CATEGORY_NAME_0')]").invokeSoftKey("NEXT_FIELD");
		forms.window("//forms:window[(@name='FOLDER_QF')]").activate(true);
		forms.listOfValues("//forms:listOfValues").select("Accrual|Month End Accrual Entry");
		forms.textField("//forms:textField[(@name='FOLDER_QF_SHOW_BATCH_STATUS_0')]").setText("pos");
		forms.textField("//forms:textField[(@name='FOLDER_QF_SHOW_BATCH_STATUS_0')]").invokeSoftKey("NEXT_FIELD");
		forms.button(39, "//forms:button[(@name='FOLDER_QF_FIND_0')]").click();
		forms.textField("//forms:textField[(@name='FOLDER_SHOW_BATCH_STATUS_7')]").click();
		forms.button("//forms:button[(@name='CONTROL_REVIEW_HEADER_0')]").click();	
		String batchname=eBS_Reusable_Methods.GetText("HEADER_NAME_0");	
		forms.button("//forms:button[(@name='HEADER_HEADER_REVERSE_0')]").click();	
		forms.window(47, "//forms:window[(@name='HEADER')]").activate(true);	

		forms.listOfValues(48, "//forms:listOfValues").select("AUG-17");
	
		forms.choiceBox(51, "//forms:choiceBox").clickButton("Switch Dr/Cr");
	
		forms.choiceBox(54, "//forms:choiceBox").clickButton("OK");
	
		forms.window(57, "//forms:window[(@name='HEADER')]").close();
	
		forms.window(60, "//forms:window[(@name='FOLDER')]").close();	
		forms.window(61, "//forms:window[(@name='NAVIGATOR')]").activate(true);
		forms.textField("//forms:textField[(@name='FOLDER_QF_BATCH_NAME_0')]").setFocus();
		forms.textField("//forms:textField[(@name='FOLDER_QF_USER_JE_SOURCE_NAME_0')]").click();
		forms.textField("//forms:textField[(@name='FOLDER_QF_HEADER_NAME_0')]").setText(batchname);
		forms.button("//forms:button[(@name='FOLDER_QF_FIND_0')]").click();
		forms.button("//forms:button[(@name='CONTROL_REVIEW_HEADER_0')]").click();	
		
	
	}

	public void finish() throws Exception {
	}
}

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
				
		eBS_Reusable_Methods.SwitchResponsibility("Fixed Assets Manager");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Depreciation");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Depreciation");	
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Depreciation|Run Depreciation");
		forms.textField("//forms:textField[(@name='DEPRN_RUN_BOOK_TYPE_CODE_0')]").openDialog();
		forms.window("//forms:window[(@name='FAXDPRUN')]").activate(true);
		forms.listOfValues("//forms:listOfValues").select("YAHOO! INC.|YAHOO! INC.");	
		forms.button("//forms:button[(@name='DEPRN_RUN_RUN_BUTTON_0')]").click();	
		forms.choiceBox(99, "//forms:choiceBox").clickButton("OK");	
		forms.window(101, "//forms:window[(@name='NAVIGATOR')]").activate(true);	
		eBS_Reusable_Methods.concurrentRequestStatus("Depreciation Run");
		
			}

	public void finish() throws Exception {
	}
}

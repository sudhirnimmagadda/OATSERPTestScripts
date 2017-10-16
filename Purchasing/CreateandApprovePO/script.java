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
		
		
		/// getting script path//
		String scriptPath = getScriptPackage().getScriptPath();
		info(scriptPath);
		int path=scriptPath.lastIndexOf("\\");
		String path1=scriptPath.substring(0, path);
		int path2=path1.lastIndexOf("\\");
		String scriptpath=scriptPath.substring(0, path2);
		info(scriptpath);
		
		
		String test=scriptPath.substring(path+1);
		int testcase=test.lastIndexOf(".");
		String testcasename=test.substring(0, testcase);
		info(testcasename);
		
		
		
		
		datatable.importExcel("C:\\OracleATS\\OFT\\Purchasing\\TestData\\POTestData.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        String OU=(String)datatable.getValue(1, "A");
        String Reqtype=(String)datatable.getValue(1, "B");
        String POCategory=(String)datatable.getValue(1, "C");
        String Description=(String)datatable.getValue(1, "D");
        String Qty=(String)datatable.getValue(1, "E");
        String Price=(String)datatable.getValue(1, "F");
        String Org=(String)datatable.getValue(1, "G");
        String DeliverLocation=(String)datatable.getValue(1, "H");
        String Supplier=(String)datatable.getValue(1, "I");
        String SupplierLocation=(String)datatable.getValue(1, "J");
        String DistAcct=(String)datatable.getValue(1, "K");
        
        
        
        String POnum = null;
        String ReqStatus;
        String Result;
		
		String Batchname=eBS_Reusable_Methods.generateUniqueData("OATS");
        eBS_Reusable_Methods.SwitchResponsibility("Purchasing Super User");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Purchase Orders");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Purchase Orders");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Purchase Orders|Purchase Orders");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Purchase Orders|Purchase Orders");
		
		
			forms.textField("//forms:textField[(@name='PO_HEADERS_OPERATING_UNIT_0')]").setFocus();
			forms.textField("//forms:textField[(@name='PO_HEADERS_OPERATING_UNIT_0')]").setText(OU);
			forms.textField("//forms:textField[(@name='PO_HEADERS_OPERATING_UNIT_0')]").invokeSoftKey("NEXT_FIELD");
			forms.textField("//forms:textField[(@name='PO_HEADERS_DESC_FLEX_0')]").click();
			forms.flexWindow("//forms:flexWindow").clickOk();
			forms.textField("//forms:textField[(@name='PO_HEADERS_VENDOR_NAME_0')]").setText(Supplier);
			forms.textField("//forms:textField[(@name='PO_HEADERS_VENDOR_NAME_0')]").invokeSoftKey("NEXT_FIELD");
			forms.window("//forms:window[(@name='PO_HEADERS')]").activate(true);
			
			//forms.listOfValues(183, "//forms:listOfValues").select("APPROVED NETWORKS INC|221349||27-2515413|");
		
			forms.textField("//forms:textField[(@name='PO_LINES_LINE_NUM_0')]").setFocus();
			forms.textField("//forms:textField[(@name='PO_LINES_ITEM_CATEGORY_0')]").openDialog();
			forms.flexWindow("//forms:flexWindow").clickCombination();
			forms.flexWindow("//forms:flexWindow").setTextAndClickOk("Major PO Category", "", "%");
			forms.listOfValues("//forms:listOfValues")
					.select(POCategory);
			forms.textField("//forms:textField[(@name='PO_LINES_ITEM_DESCRIPTION_0')]").setText(Description);
			forms.textField("//forms:textField[(@name='PO_LINES_ITEM_DESCRIPTION_0')]").invokeSoftKey("NEXT_FIELD");
			forms.textField("//forms:textField[(@name='PO_LINES_UNIT_MEAS_LOOKUP_CODE_TL_0')]").invokeSoftKey("NEXT_FIELD");
			forms.textField("//forms:textField[(@name='PO_LINES_QUANTITY_0')]").setText(Qty);
			forms.textField("//forms:textField[(@name='PO_LINES_QUANTITY_0')]").invokeSoftKey("NEXT_FIELD");
			forms.textField("//forms:textField[(@name='PO_LINES_UNIT_PRICE_0')]").setText(Price);
			forms.textField("//forms:textField[(@name='PO_LINES_UNIT_PRICE_0')]").invokeSoftKey("NEXT_FIELD");
			forms.textField("//forms:textField[(@name='PO_LINES_PROMISED_DATE_0')]").invokeSoftKey("NEXT_FIELD");
			
			
			String needbydate=eBS_Reusable_Methods.getDateTimeFormat("add2days");
			forms.textField("//forms:textField[(@name='PO_LINES_NEED_BY_0')]").setText(needbydate);
			forms.textField("//forms:textField[(@name='PO_LINES_NEED_BY_0')]").invokeSoftKey("NEXT_FIELD");
			//forms.flexWindow("//forms:flexWindow)
			forms.textField("//forms:textField[(@name='PO_LINES_NEED_BY_0')]").setFocus();
			
			forms.button("//forms:button[(@name='PO_CONTROL_SHIPMENTS_0')]").click();
			forms.button("//forms:button[(@name='SHIP_CONTROL_DISTRIBUTIONS_0')]").click();
			forms.textField("//forms:textField[(@name='PO_DISTRIBUTIONS_CHARGE_ACCOUNT_FLEX_0')]").setText(DistAcct);	
			forms.textField("//forms:textField[(@name='PO_DISTRIBUTIONS_CHARGE_ACCOUNT_FLEX_0')]").invokeSoftKey("NEXT_FIELD");
			forms.window(359, "//forms:window[(@name='PO_DISTRIBUTIONS')]").clickToolBarButton("Save");
			//forms.choiceBox(362, "//forms:choiceBox").clickButton("OK");
			forms.window(366, "//forms:window[(@name='PO_DISTRIBUTIONS')]").close();
			forms.window(369, "//forms:window[(@name='PO_SHIPMENTS')]").close();
	
		
		
		
			forms.textField(372,"//forms:textField[(@name='PO_LINES_LINE_NUM_0')]").setFocus();
			
			POnum=eBS_Reusable_Methods.GetText("PO_HEADERS_SEGMENT1_0");
			think(10);
			forms.textField("//forms:textField[(@name='PO_HEADERS_SEGMENT1_0')]").invokeSoftKey("ENTER_QUERY");
			forms.textField("//forms:textField[(@name='PO_HEADERS_SEGMENT1_0')]").setText(POnum);
			forms.textField("//forms:textField[(@name='PO_HEADERS_SEGMENT1_0')]").invokeSoftKey("EXECUTE_QUERY");
			think(10);
			
			forms.button("//forms:button[(@name='PO_CONTROL_APPROVE_0')]").click();
		   // forms.button("//forms:button[(@name='PO_ONLINE_REPORT_OK_BUTTON_0')]").click();
		    forms.button("//forms:button[(@name='PO_APPROVE_WF_OK_BUTTON_0')]").click();
		    think(10);
			forms.window("//forms:window[(@name='PO_HEADERS')]").close();
			forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);
		
			forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]").click();
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Purchase Orders");
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Purchase Orders");
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Purchase Orders|Purchase Order Summary");
		
			forms.textField("//forms:textField[(@name='FIND_OPERATING_UNIT_0')]").setFocus();
			forms.textField("//forms:textField[(@name='FIND_PO_NUM_0')]").setText(POnum);
			forms.textField("//forms:textField[(@name='FIND_PO_NUM_0')]").invokeSoftKey("NEXT_FIELD");
			forms.button("//forms:button[(@name='FIND_FIND_0')]").click();
			think(10);
			String POStatus=eBS_Reusable_Methods.GetText("HEADERS_FOLDER_AUTHORIZATION_STATUS_DSP_0");
			
			if (POStatus.equalsIgnoreCase("Approved")){
				info("PO is  " + POStatus +  "sucessfully");
				Result = "Pass";
				eBS_Reusable_Methods.writeresults(scriptpath,testcasename,POnum,DistAcct,SupplierLocation,POStatus,Supplier,Result);
				}
				else
				{
					Result = "Fail";
					eBS_Reusable_Methods.writeresults(scriptpath,testcasename,POnum,DistAcct,SupplierLocation,POStatus,Supplier,Result);	
					fail("PO number not approved");
				
				}
		
		

	}

	public void finish() throws Exception {
	}
}

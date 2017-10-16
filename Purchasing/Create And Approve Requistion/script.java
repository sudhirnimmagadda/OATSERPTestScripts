import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		
		datatable.importExcel("C:\\OracleATS\\OFT\\Purchasing\\TestData\\RequistionData.xls");
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
        //String Unitprc=(String)datatable.getValue(1, "K");
       // String DistAcct=(String)datatable.getValue(1, "M");
       
		
		
        
        String Reqnum;
        String POnum = null;
        String ReqStatus;
        String Result;
		eBS_Reusable_Methods.SwitchResponsibility("Purchasing Super User");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Requisitions");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Requisitions");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Requisitions|Requisitions");
		String shipbydate=eBS_Reusable_Methods.getDateTimeFormat("add2days");
		
		forms.textField("//forms:textField[(@name='PO_REQ_HDR_OPERATING_UNIT_0')]").setText(OU);
		forms.textField("//forms:textField[(@name='PO_REQ_HDR_OPERATING_UNIT_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='PO_REQ_HDR_DOCUMENT_TYPE_DISPLAY_0')]").openDialog();
			
		forms.listOfValues("//forms:listOfValues").select(Reqtype);
		forms.textField("//forms:textField[(@name='PO_REQ_HDR_DESCRIPTION_0')]").setFocus();
		forms.textField("//forms:textField[(@name='PO_REQ_HDR_DESCRIPTIVE_FLEX_0')]").click();
		forms.flexWindow("//forms:flexWindow").openDialog("Requisition Type", "");
		forms.listOfValues("//forms:listOfValues").select(Reqtype);
		forms.flexWindow("//forms:flexWindow").clickOk();
		forms.textField("//forms:textField[(@name='LINES_LINE_NUM_0')]").click();
			
		forms.textField("//forms:textField[(@name='LINES_ITEM_NUMBER_0')]")
					.setFocus();
			
		forms.textField("//forms:textField[(@name='LINES_ITEM_CATEGORY_0')]").openDialog();
		forms.flexWindow(141, "//forms:flexWindow").clickCombination();
		forms.flexWindow(144, "//forms:flexWindow").setTextAndClickOk("Major PO Category", "", "%");
			
		//forms.listOfValues("//forms:listOfValues").select("Brand/Media Marketing|Creative Production|Brand/Media Marketing.Creative Production");
		forms.listOfValues("//forms:listOfValues").select(POCategory);
			
		forms.textField("//forms:textField[(@name='LINES_ITEM_DESCRIPTION_0')]")
					.setText(Description);
			
		forms.textField("//forms:textField[(@name='LINES_ITEM_DESCRIPTION_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='LINES_UNIT_MEAS_LOOKUP_CODE_TL_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='LINES_QUANTITY_0')]").setText(Qty);
			
		forms.textField("//forms:textField[(@name='LINES_QUANTITY_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='LINES_UNIT_PRICE_0')]").setText(Price);
			
		forms.textField("//forms:textField[(@name='LINES_UNIT_PRICE_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='LINES_NEED_BY_DATE_0')]").setText(shipbydate);
		
		//forms.textField("//forms:textField[(@name='LINES_NOTE_TO_AGENT_0')]").setFocus();
			
		forms.tab(165, "//forms:tab[(@name='LINES_STACKED_REGIONS')]").select("Lines");
			
		forms.textField("//forms:textField[(@name='LINES_DEST_ORGANIZATION_0')]").openDialog();
		forms.window(167, "//forms:window[(@name='HEADER_LINES')]").activate(true);
		forms.listOfValues(168, "//forms:listOfValues").select(Org);
		
			forms.textField("//forms:textField[(@name='LINES_DELIVER_TO_LOCATION_0')]").setText(DeliverLocation);
			forms.textField("//forms:textField[(@name='LINES_DELIVER_TO_LOCATION_0')]").invokeSoftKey("NEXT_FIELD");
		
			forms.textField("//forms:textField[(@name='LINES_SUGGESTED_VENDOR_NAME_0')]")
					.setText(Supplier);
			
			forms.textField("//forms:textField[(@name='LINES_SUGGESTED_VENDOR_NAME_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
			forms.textField("//forms:textField[(@name='LINES_SUGGESTED_VENDOR_LOCATION_0')]")
				.setText(SupplierLocation);
			forms.textField("//forms:textField[(@name='LINES_SUGGESTED_VENDOR_LOCATION_0')]").
				invokeSoftKey("NEXT_FIELD");
	
			forms.window(204, "//forms:window[(@name='HEADER_LINES')]").clickToolBarButton("Save");
		
			forms.button("//forms:button[(@name='CONTROL_DISTRIBUTIONS_0')]").click();
			forms.textField("//forms:textField[(@name='DISTRIBUTIONS_CHARGE_ACCOUNT_FLEX_0')]").setText(DistAcct);
			forms.textField("//forms:textField[(@name='DISTRIBUTIONS_CHARGE_ACCOUNT_FLEX_0')]").invokeSoftKey("NEXT_FIELD");
			eBS_Reusable_Methods.captureformserror("pls provide correct chargeaccount",scriptpath,testcasename);
			forms.window("//forms:window[(@name='DISTRIBUTIONS')]").clickToolBarButton("Save");
			forms.window("//forms:window[(@name='DISTRIBUTIONS')]").close();
			forms.window(530, "//forms:window[(@name='HEADER_LINES')]").clickToolBarButton("Save");
			
			Reqnum=eBS_Reusable_Methods.GetText("PO_REQ_HDR_SEGMENT1_0");
			think(5);
			
			
			forms.textField("//forms:textField[(@name='PO_REQ_HDR_SEGMENT1_0')]").invokeSoftKey("ENTER_QUERY");
			think(10);
			forms.alertDialog("//forms:alertDialog").clickYes();
			forms.alertDialog("//forms:alertDialog").clickYes();
			
			
			forms.textField("//forms:textField[(@name='PO_REQ_HDR_SEGMENT1_0')]").setText(Reqnum);
			forms.textField("//forms:textField[(@name='PO_REQ_HDR_SEGMENT1_0')]").invokeSoftKey("EXECUTE_QUERY");
			think(10);
			
			forms.button("//forms:button[(@name='CONTROL_APPROVAL_0')]").click();
			eBS_Reusable_Methods.captureformserror("Unable to Approve",scriptpath,testcasename);
			think(25);
			forms.button("//forms:button[(@name='PO_APPROVE_WF_OK_BUTTON_0')]").click();
			eBS_Reusable_Methods.captureformserror("Unable to Approve",scriptpath,testcasename);
			
			//forms.textField("//forms:textField[(@name='PO_REQ_HDR_OPERATING_UNIT_0')]").setFocus();
			//forms.textField("//forms:textField[(@name='PO_REQ_HDR_OPERATING_UNIT_0')]").setText(OU);
			//forms.textField("//forms:textField[(@name='PO_REQ_HDR_OPERATING_UNIT_0')]").invokeSoftKey("NEXT_FIELD");
			forms.window("//forms:window[(@name='HEADER_LINES')]").close();
			forms.choiceBox("//forms:choiceBox").clickButton("OK");
			forms.alertDialog("//forms:alertDialog").clickYes();
			forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);
			
			
		
			forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]").click();
			forms.treeList(569, "//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Requisitions");
			forms.treeList(592, "//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Requisitions");
			forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Requisitions|Requisition Summary");
			forms.button("//forms:button[(@name='FIND_REQ_CLEAR_0')]").click();
			forms.textField(621,"//forms:textField[(@name='FIND_REQ_REQ_NUM_0')]").setText(Reqnum);
			forms.textField("//forms:textField[(@name='FIND_REQ_REQ_NUM_0')]").invokeSoftKey("NEXT_FIELD");
			forms.button("//forms:button[(@name='FIND_REQ_FIND_0')]").click();
			think(10);
			ReqStatus=eBS_Reusable_Methods.GetText("REQ_HEADERS_FOLDER_AUTHORIZATION_STATUS_DSP_0");
			
			
			
			 if (ReqStatus.equalsIgnoreCase("Approved")){
				 reportPassed("Requsition got approved sucessfully");
				  }
			 else {
				 reportFailure("Requsition not approved sucessfully");
				 Result = "Fail";
				  }
		
			             /// validating PO is generated or not
			forms.button("//forms:button[(@name='REQ_HEADERS_FOLDER_CONTROL_VIEW_LINES_0')]").click();
			POnum=eBS_Reusable_Methods.GetText("REQ_LINES_FOLDER_ORDER_NUM_0");
			if (!(POnum==null)){
			info("PO number" + POnum +"sucessfully created");
			Result = "Pass";
			eBS_Reusable_Methods.writeresults(scriptpath,testcasename,Reqnum,POnum,SupplierLocation,ReqStatus,Supplier,Result);
			}
			else
			{
			fail("PO number not created sucessfully");
			Result = "Fail";
			eBS_Reusable_Methods.writeresults(scriptpath,testcasename,Reqnum,POnum,SupplierLocation,ReqStatus,Supplier,Result);
			}
			
			
				
	}		
		   

	public void finish() throws Exception {
		
	}
	
}
	
	

	   //Calendar cal = Calendar.getInstance();
    //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    //String datetime=  sdf.format(cal.getTime()) ;
    //String todaysdate=eBS_Reusable_Methods.getDateTimeFormat("format2");
    //utilities.getFileService().appendStringToFile("C:/OracleATS/OFT/Purchasing/TestResults.CSV", eval(datetime)+","+eval(todaysdate)+","+ eval(Reqnum)+ "," + eval(POnum)+ ","+ eval(SupplierLocation)+ "," + eval(ReqStatus)+ "," + eval(Supplier)+"\n");
		
	
	
	
	
	


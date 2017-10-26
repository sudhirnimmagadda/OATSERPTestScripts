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
	@ScriptService oracle.oats.scripting.modules.datatable.api.DataTableService datatable;
	
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
		
		datatable.importExcel("C:\\OracleATS\\OFT\\AP Sceanrios\\Test Data\\APInvoiceTestData.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        
        String Supplier=(String)datatable.getValue(1, "A");
        String invAmt=(String)datatable.getValue(1, "B");
        String Lineamt=(String)datatable.getValue(1, "C");
        String PymtMethod=(String)datatable.getValue(1, "D");
        String Distribution=(String)datatable.getValue(1, "F");
        String Pymtprofile=(String)datatable.getValue(1, "I");
        String Bank=(String)datatable.getValue(1, "J");
		
		String Batchname=eBS_Reusable_Methods.generateUniqueData("OATS");
        eBS_Reusable_Methods.SwitchResponsibility("Payables Manager");
        forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Invoices");
        eBS_Reusable_Methods.selecttreelist("NAVIGATOR_LIST_0", "Invoices|Entry");
		eBS_Reusable_Methods.selecttreelist("NAVIGATOR_LIST_0", "Invoices|Entry|Invoice Batches");
		forms.textField("//forms:textField[(@name='BAT_SUM_FOLDER_BATCH_NAME_0')]").setText(Batchname);
		forms.button("//forms:button[(@name='BAT_CONTROL_BLOCK_INVOICE_0')]").click();
		
		String Invoicenum=eBS_Reusable_Methods.generateUniqueData("INVHOLD");
		String Invoicedate=eBS_Reusable_Methods.getDateTimeFormat("format2");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_TYPE_0')]").invokeSoftKey("NEXT_FIELD");
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").click();
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_VENDOR_NAME_0')]").setText("Approved Networks");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_VENDOR_NAME_0')]").invokeSoftKey("NEXT_FIELD");
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").setText(Reqnum);
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").click();
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").setText(Invoicedate);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_NUM_0')]").setText(Invoicenum);
		String invoicenumber=eBS_Reusable_Methods.GetText("INV_SUM_FOLDER_INVOICE_NUM_0");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").setText(invAmt);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_IBY_PAYMENT_METHOD_0')]").setText(PymtMethod);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_IBY_PAYMENT_METHOD_0')]").invokeSoftKey("NEXT_FIELD");
		
		forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("2 Lines");	
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").click();
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").setText(Lineamt);
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").invokeSoftKey("NEXT_FIELD");
		
		/*forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").click();
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").setText("1");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_SHIPMENT_NUMBER_0')]").setText("1");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_SHIPMENT_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");*/
		
		forms.button("//forms:button[(@name='LINE_SUM_CONTROL_DISTRIBUTIONS_0')]").click();
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_AMOUNT_0')]").setText(Lineamt);
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_AMOUNT_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_DIST_CODE_COMBINATION_DISP_0')]").setText(Distribution);
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_DIST_CODE_COMBINATION_DISP_0')]").invokeSoftKey("NEXT_FIELD");
		String DistAcctnum=eBS_Reusable_Methods.GetText("D_SUM_FOLDER_DIST_CODE_COMBINATION_DISP_0");
		info("distribution acctnumber is "+DistAcctnum);
		forms.window("//forms:window[(@name='DIST_SUMMARY')]").clickToolBarButton("Save");
		forms.window("//forms:window[(@name='DIST_SUMMARY')]").close();
			
		
		forms.button("//forms:button[(@name='INV_SUM_CONTROL_ACTIONS_0')]").click();
		forms.checkBox("//forms:checkBox[(@name='INV_SUM_ACTIONS_APPROVE_0')]").check(true);
		forms.button("//forms:button[(@name='INV_SUM_ACTIONS_OK_BUTTON_0')]").click();
		think(10);
		String Invoicestatus=eBS_Reusable_Methods.GetText("INV_SUM_FOLDER_APPROVAL_STATUS_DISPLAY_0");
		
		if (Invoicestatus.equalsIgnoreCase("Needs Revalidation")){
			forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("3 Holds");
			String HldReason=eBS_Reusable_Methods.GetText("AP_HOLDS_HOLD_REASON_0");
			String Hlduser=eBS_Reusable_Methods.GetText("AP_HOLDS_HELD_BY_USER_NAME_0");
			info("Hold Reason is "+  HldReason );
		}
		
		
		
		    					/// to Release the Hold///
		
		forms.window("//forms:window[(@name='INVOICES_SUM_FOLDER_WINDOW')]").close();
		forms.window("//forms:window[(@name='BAT_SUM_FOLDER')]").close();
		
		eBS_Reusable_Methods.selecttreelist("NAVIGATOR_LIST_0", "Invoices|Entry|Invoices");
		forms.textField("//forms:textField[(@name='INVOICES_QF_INVOICE_NUM_0')]").setText(invoicenumber);
		forms.textField("//forms:textField[(@name='INVOICES_QF_INVOICE_NUM_0')]").invokeSoftKey("NEXT_FIELD");
		forms.button("//forms:button[(@name='INVOICES_QF_FIND_0')]").click();
		
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").setText(Lineamt);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").invokeSoftKey("NEXT_FIELD");
		forms.button("//forms:button[(@name='INV_SUM_CONTROL_ACTIONS_0')]").click();
		forms.checkBox("//forms:checkBox[(@name='INV_SUM_ACTIONS_APPROVE_0')]").check(true);
		forms.button("//forms:button[(@name='INV_SUM_ACTIONS_OK_BUTTON_0')]").click();
		think(10);
		forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("1 General");
		think(10);
	    Invoicestatus=eBS_Reusable_Methods.GetText("INV_SUM_FOLDER_APPROVAL_STATUS_DISPLAY_0");
		
		if (Invoicestatus.equalsIgnoreCase("Validated")){
			info("Invoice is sucessfully"+  Invoicestatus );
		}
	}  

	public void finish() throws Exception {
	}
}







	//// to release the hold///
//forms.button("//forms:button[(@name='AP_HOLDS_CONTROL_ACTION_BUTTON_0')]").click();
//forms:textField[(@name='AP_HOLD_RELEASES_RELEASE_NAME_0')]
//forms:textField[(@name='AP_HOLD_RELEASES_RELEASE_REASON_0')]
//forms:button[(@name='AP_HOLD_RELEASES_OK_BUTTON_0')]
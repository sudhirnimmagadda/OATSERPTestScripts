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
        String Amt=(String)datatable.getValue(1, "B");
        String LineAmt=(String)datatable.getValue(1, "C");
        String PymtMethod=(String)datatable.getValue(1, "D");
        String Distribution=(String)datatable.getValue(1, "F");
        String HldName=(String)datatable.getValue(1, "G");
        String Hldreason=(String)datatable.getValue(1, "H");
        String PayTerm=(String)datatable.getValue(1, "D");
        
		
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
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_VENDOR_NAME_0')]").setText(Supplier);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_VENDOR_NAME_0')]").invokeSoftKey("NEXT_FIELD");
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").setText(Reqnum);
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").click();
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").setText(Invoicedate);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_NUM_0')]").setText(Invoicenum);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").setText(Amt);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_IBY_PAYMENT_METHOD_0')]").setText(PymtMethod);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_IBY_PAYMENT_METHOD_0')]").invokeSoftKey("NEXT_FIELD");
		
		forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("2 Lines");	
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").click();
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").setText(Amt);
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").invokeSoftKey("NEXT_FIELD");
		
		/*forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").click();
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").setText("1");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_SHIPMENT_NUMBER_0')]").setText("1");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_SHIPMENT_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");*/
		
		forms.button("//forms:button[(@name='LINE_SUM_CONTROL_DISTRIBUTIONS_0')]").click();
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_AMOUNT_0')]").setText(Amt);
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_AMOUNT_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_DIST_CODE_COMBINATION_DISP_0')]").setText(Distribution);
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_DIST_CODE_COMBINATION_DISP_0')]").invokeSoftKey("NEXT_FIELD");
		String DistAcctnum=eBS_Reusable_Methods.GetText("D_SUM_FOLDER_DIST_CODE_COMBINATION_DISP_0");
		info("distribution acctnumber is "+DistAcctnum);
		forms.window("//forms:window[(@name='DIST_SUMMARY')]").clickToolBarButton("Save");
		forms.window("//forms:window[(@name='DIST_SUMMARY')]").close();
			
								//// putting manual hold///		
		forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("3 Holds");
		think(10);
		forms.textField("//forms:textField[(@name='AP_HOLDS_HOLD_NAME_0')]").setText(HldName);
		forms.textField("//forms:textField[(@name='AP_HOLDS_HOLD_NAME_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='AP_HOLDS_DESC_FLEX_0')]").click();
		forms.flexWindow("//forms:flexWindow").setText("Enter Hold Reason", "",	Hldreason);
		forms.flexWindow("//forms:flexWindow").clickOk();
		
		
		forms.button("//forms:button[(@name='INV_SUM_CONTROL_ACTIONS_0')]").click();
		forms.checkBox("//forms:checkBox[(@name='INV_SUM_ACTIONS_APPROVE_0')]").check(true);
		forms.button("//forms:button[(@name='INV_SUM_ACTIONS_OK_BUTTON_0')]").click();
		think(10);
		forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("1 General");
		String Invoicestatus=eBS_Reusable_Methods.GetText("INV_SUM_FOLDER_APPROVAL_STATUS_DISPLAY_0");
		
		
		if (Invoicestatus.equalsIgnoreCase("Needs Revalidation")){
			forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("3 Holds");
			String HldReason=eBS_Reusable_Methods.GetText("AP_HOLDS_HOLD_REASON_0");
			String Hlduser=eBS_Reusable_Methods.GetText("AP_HOLDS_HELD_BY_USER_NAME_0");
			info("Hold Reason is "+  HldReason );
		}
		

								//// to release the hold///
	forms.button("//forms:button[(@name='AP_HOLDS_CONTROL_ACTION_BUTTON_0')]").click();
	forms.textField("//forms:textField[(@name='AP_HOLD_RELEASES_RELEASE_NAME_0')]").openDialog();
	//forms.textField("//forms:textField[(@name='AP_HOLD_RELEASES_RELEASE_REASON_0')]").openDialog();
	forms.button("//forms:button[(@name='AP_HOLD_RELEASES_OK_BUTTON_0')]").click();
	
	
							///// checking invoice status///
	Invoicestatus=eBS_Reusable_Methods.GetText("INV_SUM_FOLDER_APPROVAL_STATUS_DISPLAY_0");
	
	if (Invoicestatus.equalsIgnoreCase("Validated")){
		reportPassed("Invoice got"+  Invoicestatus );
	}
	else
	{
		reportFailure("Invoice"+  Invoicestatus);
		}
	
	forms.button("//forms:button[(@name='INV_SUM_CONTROL_ACTIONS_0')]").click();
	think(10);
	forms.checkBox("//forms:checkBox[(@name='INV_SUM_ACTIONS_CREATE_ACCOUNTING_0')]").check(true);
	forms.radioButton("//forms:radioButton[(@name='INV_SUM_ACTIONS_ACCOUNTING_DETAILS_FINAL_POST_0')]").select();
	forms.button("//forms:button[(@name='INV_SUM_ACTIONS_OK_BUTTON_0')]").click();
	forms.choiceBox("//forms:choiceBox").clickButton("OK");
	
	String Acctstatus=eBS_Reusable_Methods.GetText("INV_SUM_FOLDER_POSTING_FLAG_DISPLAY_0");
		if (Acctstatus.equalsIgnoreCase("Yes")){
		reportPassed("Invoice is Accounted sucessfully");
	}
	else
	{
		reportFailure("Invoice is not Accounted sucessfully");
		}
		
	}

	public void finish() throws Exception {
	}
}







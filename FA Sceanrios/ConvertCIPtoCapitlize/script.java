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
		
		String Batchname=eBS_Reusable_Methods.generateUniqueData("OATS");
        eBS_Reusable_Methods.SwitchResponsibility("Payables Manager");
        forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Invoices");
        eBS_Reusable_Methods.selecttreelist("NAVIGATOR_LIST_0", "Invoices|Entry");
		eBS_Reusable_Methods.selecttreelist("NAVIGATOR_LIST_0", "Invoices|Entry|Invoice Batches");
		forms.textField("//forms:textField[(@name='BAT_SUM_FOLDER_BATCH_NAME_0')]").setText(Batchname);
		forms.button("//forms:button[(@name='BAT_CONTROL_BLOCK_INVOICE_0')]").click();
		
		String Invoicenum=eBS_Reusable_Methods.generateUniqueData("INV");
		String Invoicedate=eBS_Reusable_Methods.getDateTimeFormat("format2");
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").click();
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_VENDOR_NAME_0')]").setText("Approved Networks");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_VENDOR_NAME_0')]").invokeSoftKey("NEXT_FIELD");
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").setText(Reqnum);
		//forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_QUICK_PO_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").click();
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").setText(Invoicedate);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_DATE_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_NUM_0')]").setText(Invoicenum);
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").setText("200");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_AMOUNT_DSP_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_IBY_PAYMENT_METHOD_0')]").setText("Check");
		forms.textField("//forms:textField[(@name='INV_SUM_FOLDER_IBY_PAYMENT_METHOD_0')]").invokeSoftKey("NEXT_FIELD");
		
		forms.tab("//forms:tab[(@name='TAB_INVOICE_SUM_REGIONS')]").select("2 Lines");	
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").click();
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").setText("200");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_AMOUNT_DISP_0')]").invokeSoftKey("NEXT_FIELD");
		
		/*forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").click();
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").setText("1");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_LINE_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_SHIPMENT_NUMBER_0')]").setText("1");
		forms.textField("//forms:textField[(@name='LINE_SUM_FOLDER_PO_SHIPMENT_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");*/
		
		forms.button("//forms:button[(@name='LINE_SUM_CONTROL_DISTRIBUTIONS_0')]").click();
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_AMOUNT_0')]").setText("200");
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_AMOUNT_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='D_SUM_FOLDER_DIST_CODE_COMBINATION_DISP_0')]").setText("110-100120-1010-0000-000-000000-000-0000");
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
		
		if (Invoicestatus.equalsIgnoreCase("Validated")){
			reportPassed("Invoice got" +Invoicestatus );
		}
		else
		{
			reportFailure("Invoice not"+Invoicestatus);
			}
		forms.window("//forms:window[(@name='INVOICES_SUM_FOLDER_WINDOW')]").close();
		forms.window("//forms:window[(@name='BAT_SUM_FOLDER')]").close();
		
		
		
		          ///// navigating to asset form ///
		
		forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]").click();
		eBS_Reusable_Methods.SwitchResponsibility("Fixed Assets Manager");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets|Asset Workbench");
		
		forms.button("//forms:button[(@name='SMART_FIND_SF_NEW_BUTTON_0')]").click();
		forms.textField("//forms:textField[(@name='ASSET_DESCRIPTION_0')]")
			.setText("Dell Server");
		forms.textField("//forms:textField[(@name='ASSET_ATTRIBUTE_CATEGORY_CODE_0')]")
			.openDialog();
		
		forms.flexWindow("//forms:flexWindow").openDialog("Major Category", "");
		forms.listOfValues("//forms:listOfValues").select("COMPUTERS|Computer Equipment");
		forms.flexWindow("//forms:flexWindow").openDialog("Minor Category", "");
		forms.listOfValues("//forms:listOfValues").select("SERVER|Server");	
		forms.flexWindow("//forms:flexWindow").setText("Location", "",	"00");
		forms.flexWindow("//forms:flexWindow").clickOk();
		forms.flexWindow("//forms:flexWindow").clickOk();

		forms.list("//forms:list[(@name='ASSET_CAP_ASSET_TYPE_0')]")
					.selectItem("CIP");
		forms.button("//forms:button[(@name='ASSET_BOOKS_ASSET_0')]")
					.click();
		forms.textField("//forms:textField[(@name='FA_BOOKS_HEADER_BOOK_TYPE_CODE_0')]")
					.setText("YAHOO! INC");
			
		forms.textField("//forms:textField[(@name='FA_BOOKS_HEADER_BOOK_TYPE_CODE_0')]")
					.invokeSoftKey("NEXT_FIELD");
		forms.button("//forms:button[(@name='FA_BOOKS_ASSIGNMENTS_BOOKS_0')]")
					.click();
		forms.textField("//forms:textField[(@name='ASSIGNMENTS_DIST_IN_OUT_0')]")
					.setText("1");
		forms.textField("//forms:textField[(@name='ASSIGNMENTS_DIST_EXPENSE_ACCOUNT_0')]")
					.click();
		forms.textField("//forms:textField[(@name='ASSIGNMENTS_DIST_EXPENSE_ACCOUNT_0')]")
					.openDialog();
		forms.flexWindow("//forms:flexWindow").setText("Company", "","110");
		forms.flexWindow("//forms:flexWindow").setText("Sub Account","", "000");
		forms.flexWindow("//forms:flexWindow").setText("Region", "","3840");
		forms.flexWindow("//forms:flexWindow").setText("Function", "","000");
		forms.flexWindow("//forms:flexWindow").setText("Cost Center","", "501901");
		forms.flexWindow("//forms:flexWindow").clickOk();
		
		forms.textField("//forms:textField[(@name='ASSIGNMENTS_DIST_LOCATION_DISP_0')]")
					.openDialog();
		forms.flexWindow("//forms:flexWindow").setText("Country", "","US");
		forms.flexWindow("//forms:flexWindow").setText("State", "","CA");
		forms.flexWindow("//forms:flexWindow").setText("County", "","San Jose");
		forms.flexWindow("//forms:flexWindow").setText("City", "",	"San Jose");
		forms.flexWindow("//forms:flexWindow").invokeSoftKey("NEXT_FIELD");
		forms.flexWindow("//forms:flexWindow").setText("Building", "","1 Al");
		forms.flexWindow("//forms:flexWindow").invokeSoftKey("NEXT_FIELD");
		forms.flexWindow("//forms:flexWindow").clickOk();
		forms.window("//forms:window[(@name='ASSIGNMENTS')]").clickToolBarButton("Save");
		String assetinfo=forms.choiceBox("//forms:choiceBox").getAlertMessage();
		String assetdata=assetinfo.substring(50);
		String assetnum=assetdata.substring(0, 7);
		forms.choiceBox("//forms:choiceBox").clickButton("OK");
		forms.window("//forms:window[(@name='ASSETS_FDR')]").close();
		
		
		     /// inquiry the asset number and enter invoice details////
		forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]").click();
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Inquiry");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Inquiry");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets|Asset Workbench");
		forms.textField("//forms:textField[(@name='SMART_FIND_ASSET_NUMBER_0')]").setText(assetnum);
		forms.textField("//forms:textField[(@name='SMART_FIND_ASSET_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.button("//forms:button[(@name='SMART_FIND_SF_FIND_BUTTON_0')]").click();
		forms.button("//forms:button[(@name='CONTROL_INVOICES_BUTTON_0')]").click();
		
		forms.textField("//forms:textField[(@name='INVOICES_INVOICE_NUMBER_0')]").setText(Invoicenum);
		forms.checkBox("//forms:checkBox[(@name='INVOICES_SELECTED_INVOICE_0')]").check(true);
		forms.textField("//forms:textField[(@name='INVOICES_INVOICE_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INVOICES_INVOICE_LINE_NUMBER_0')]").setText("1");
		forms.textField("//forms:textField[(@name='INVOICES_INVOICE_LINE_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INVOICES_VENDOR_NAME_0')]").setText("APPROVED");
		forms.textField("//forms:textField[(@name='INVOICES_VENDOR_NAME_0')]").invokeSoftKey("NEXT_FIELD");
		forms.window("//forms:window[(@name='INVOICES')]").activate(true);
		forms.listOfValues("//forms:listOfValues").select("APPROVED NETWORKS INC|221349|27-2515413|");
		forms.textField("//forms:textField[(@name='INVOICES_VENDOR_NUMBER_0')]")
					.invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='INVOICES_FIXED_ASSETS_COST_0')]")
					.setText("200");
		forms.textField("//forms:textField[(@name='INVOICES_FIXED_ASSETS_COST_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.button("//forms:button[(@name='INVOICES_OK_INVOICES_0')]").click();
		
		
		
		forms.button("//forms:button[(@name='CONTROL_FINANCIAL_INQUIRY_0')]").click();
		forms.textField("//forms:textField[(@name='INQUIRY_BOOKS_BOOK_TYPE_CODE_0')]")
					.setFocus();
		String amt=forms.textField("//forms:textField[(@name='INQUIRY_DEPRN_DEPRN_AMOUNT_0')]").getText();
		info(amt);
		forms.tab("//forms:tab[(@name='INQUIRY_BOOKS_TAB')]").select("Cost History");
		forms.textField("//forms:textField[(@name='INQUIRY_COST_TRANSACTION_TYPE_1')]")
					.setFocus();
		forms.textField("//forms:textField[(@name='INQUIRY_COST_TRANSACTION_TYPE_0')]")
					.setFocus();
		forms.window("//forms:window[(@name='INQUIRY_BOOKS')]").close();
		forms.button("//forms:button[(@name='CONTROL_DETAIL_BUTTON_0')]").click();
		forms.window("//forms:window[(@name='ASSET')]").close();
		forms.window("//forms:window[(@name='ASSETS_FDR')]").close();
			
		forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]")
					.click();
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Assets|Capitalize CIP Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets|Capitalize CIP Assets");
		forms.textField("//forms:textField[(@name='BOOK_QF_BOOK_TYPE_CODE_0')]").setText("YAHOO! INC");
		forms.textField("//forms:textField[(@name='BOOK_QF_BOOK_TYPE_CODE_0')]").invokeSoftKey("NEXT_FIELD");
		forms.textField("//forms:textField[(@name='BOOK_QF_ASSET_NUMBER_0')]").setText(assetnum);
		forms.textField("//forms:textField[(@name='BOOK_QF_ASSET_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.button(573, "//forms:button[(@name='BOOK_QF_FIND_0')]").click();
		
		forms.textField("//forms:textField[(@name='ADDITIONS_ASSET_NUMBER_0')]").setFocus();
		forms.checkBox("//forms:checkBox[(@name='ADDITIONS_CAPITALIZE_OR_REVERSE_0')]").check(true);
		forms.textField("//forms:textField[(@name='ADDITIONS_DESCRIPTION_0')]").setFocus();
		forms.textField("//forms:textField[(@name='ADDITIONS_COST_0')]").setFocus();
		forms.button("//forms:button[(@name='CONTROL_BUTTONS_CAP_BUTTON_0')]").click();
		forms.window("//forms:window[(@name='BOOK_QF')]").close();
		forms.window("//forms:window[(@name='CAPITALIZE_CIP')]").close();
		
		
		
		forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]").click();
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets|Asset Workbench");
		forms.textField("//forms:textField[(@name='SMART_FIND_ASSET_NUMBER_0')]").setText(assetnum);
		forms.textField("//forms:textField[(@name='SMART_FIND_ASSET_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.button("//forms:button[(@name='SMART_FIND_SF_FIND_BUTTON_0')]").click();
		forms.button("//forms:button[(@name='CONTROL_DETAIL_BUTTON_0')]").click();
		String Assettype=forms.list("//forms:list[(@name='ASSET_CAP_ASSET_TYPE_0')]").getItemValue();
		info(Assettype);
		
		forms.button("//forms:button[(@name='ASSET_OK_ASSET_0')]").click();
		forms.window("//forms:window[(@name='ASSET')]").close();
		forms.window("//forms:window[(@name='ASSETS_FDR')]").close();
		forms.window("//forms:window[(@name='NAVIGATOR')]").activate(true);
		

	}

	public void finish() throws Exception {
	}
}

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
		
				
		datatable.importExcel("C:\\OracleATS\\OFT\\AR Sceanrios\\Test Data\\AR Data.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        
        String Source=(String)datatable.getValue(1, "A");
        String Class=(String)datatable.getValue(1, "B");
        String Type=(String)datatable.getValue(1, "C");
        String LegalEntity=(String)datatable.getValue(1, "D");
        String BiltoNum=(String)datatable.getValue(1, "E");
        String ShiptoNum=(String)datatable.getValue(1, "F");
        String PayTerm=(String)datatable.getValue(1, "G");
        String LineItemNum=(String)datatable.getValue(1, "H");
        String Descr=(String)datatable.getValue(1, "I");
        String Quantity=(String)datatable.getValue(1, "J");
        String Unitprc=(String)datatable.getValue(1, "K");
        String DistAcct=(String)datatable.getValue(1, "M");
        String Recpmethod=(String)datatable.getValue(1, "N");
        
        eBS_Reusable_Methods.SwitchResponsibility("Receivables Manager");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Transactions");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Transactions|Transactions");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Transactions|Transactions");
		forms.textField("//forms:textField[(@name='TGW_HEADER_BS_BATCH_SOURCE_NAME_MIR_0')]")
					.setText(Source);
			
		forms.textField("//forms:textField[(@name='TGW_HEADER_BS_BATCH_SOURCE_NAME_MIR_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.list("//forms:list[(@name='TGW_HEADER_CTT_CLASS_MIR_0')]")
					.selectItem(Class);
			
		forms.list("//forms:list[(@name='TGW_HEADER_CTT_CLASS_MIR_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='TGW_HEADER_CTT_TYPE_NAME_MIR_0')]").setText(Type);
		
		//forms.window(248, "//forms:window[(@name='TMAI_HEADER')]").activate(true);
			
		//forms.listOfValues(249, "//forms:listOfValues").select("Invoice|Invoice|Regular Invoice");
		
		
		forms.textField(261,"//forms:textField[(@name='TGW_HEADER_RAC_SHIP_TO_CUSTOMER_NUM_MIR_0')]")
					.setText(BiltoNum);

		forms.textField("//forms:textField[(@name='TGW_HEADER_RAC_SHIP_TO_CUSTOMER_NUM_MIR_0')]")
						.invokeSoftKey("NEXT_FIELD");
		
		
		if(forms.choiceBox("//forms:choiceBox").exists(3,TimeUnit.SECONDS)){
			String Alertmsg=forms.choiceBox("//forms:choiceBox").getAlertMessage();
		forms.captureScreenshot();
		reportFailure("unexpected window with message:" +Alertmsg);
		fail("given customer doesnot exists");
		
			}
		
		forms.textField(259,"//forms:textField[(@name='TGW_HEADER_RAC_BILL_TO_CUSTOMER_NUM_MIR_0')]")
							.setText(ShiptoNum);
		
		forms.textField(259,"//forms:textField[(@name='TGW_HEADER_RAC_BILL_TO_CUSTOMER_NUM_MIR_0')]")
						.invokeSoftKey("NEXT_FIELD");
		
		forms.button(263,"//forms:button[(@name='TGW_HEADER_HEADER_LINE_ITEMS_0')]")
					.click();
			
		forms.textField(267,"//forms:textField[(@name='TLIN_LINES_LINE_NUMBER_0')]")
					.setFocus();
		
		forms.textField(268,"//forms:textField[(@name='TLIN_LINES_DESCRIPTION_0')]").setText(Descr);
		
	/*		
		forms.textField(268,"//forms:textField[(@name='TLIN_LINES_DESCRIPTION_0')]")
					.openDialog();
		
		forms.listOfValues(271, "//forms:listOfValues").find("%");
			
		forms.listOfValues(272, "//forms:listOfValues").select("ITEM Invoice|ITEM Invoice|Line+");
		
		forms.textField(275,"//forms:textField[(@name='TLIN_LINES_DESCRIPTION_0')]")
					.invokeSoftKey("NEXT_FIELD");
					*/
			
		forms.textField("//forms:textField[(@name='TLIN_LINES_QUANTITY_0')]")
					.setText(Quantity);
			
		forms.textField("//forms:textField[(@name='TLIN_LINES_QUANTITY_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='TLIN_LINES_UNIT_SELLING_PRICE_0')]")
					.setText(Unitprc);
			
		forms.textField("//forms:textField[(@name='TLIN_LINES_UNIT_SELLING_PRICE_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
		forms.textField("//forms:textField[(@name='TLIN_LINES_EXTENDED_AMOUNT_0')]")
					.invokeSoftKey("NEXT_FIELD");
		
		String invamt=eBS_Reusable_Methods.GetText("TLIN_LINES_EXTENDED_AMOUNT_0");
		
		
			
		forms.button("//forms:button[(@name='TLIN_LINES_LINE_ACCOUNTING_0')]")
					.click();
		forms.textField("//forms:textField[(@name='TACC_ACC_ASSGN_ACCOUNTING_FLEX_0')]").setText(DistAcct);
		
		forms.textField("//forms:textField[(@name='TACC_ACC_ASSGN_ACCOUNTING_FLEX_0')]").invokeSoftKey("NEXT_FIELD");
			
		forms.window("//forms:window[(@name='TACCOUNTS')]").clickToolBarButton("Save");
			
		forms.window("//forms:window[(@name='TACCOUNTS')]").close();
		
		forms.textField(290,"//forms:textField[(@name='TLIN_LINES_LINE_NUMBER_0')]").setFocus();
			
		forms.window("//forms:window[(@name='TLIN_LINES')]").close();
		
		forms.textField("//forms:textField[(@name='TGW_HEADER_BS_BATCH_SOURCE_NAME_MIR_0')]").setFocus();
			
		forms.button("//forms:button[(@name='TGW_HEADER_HEADER_COMPLETE_0')]").click();
			
		String totalamt=eBS_Reusable_Methods.GetText("TGW_HEADER_TOTAL_BALANCE_MIR_0");
		String ARinvoice=eBS_Reusable_Methods.GetText("TGW_HEADER_TRX_NUMBER_MIR_0");
		
		forms.window("//forms:window[(@name='TMAI_HEADER')]").clickToolBarButton("Save");
		
		
		
									/// Posting the Transactions to GL /////	
		forms.window("//forms:window[(@name='TMAI_HEADER')]").selectMenu("Tools|Create Accounting");
		forms.button("//forms:button[(@name='SLACEXEC_OK_BP_0')]").click();
		
		String acctdata=forms.choiceBox("//forms:choiceBox").getAlertMessage();
		info(acctdata);
		forms.choiceBox("//forms:choiceBox").clickButton("OK");
		
		
		forms.window("//forms:window[(@name='TMAI_HEADER')]").selectMenu("Tools|View Accounting");
		web.window("/web:window[@index='2' or @title='Subledger Journal Entry Lines']").waitForPage(null);
			
		String RevAcct=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@text='110-130100-0000-0000-000-000000-000-0000' or @id='N3:Account:0' or @index='50']")
					.getAttribute("text");
		info(RevAcct);
			
		String ExpAcct=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@text='198-310611-0000-3840-000-505840-000-0000' or @id='N3:Account:1' or @index='58']")
					.getAttribute("text");
		info(ExpAcct);
		web.window(317,	"/web:window[@index='2' or @title='Subledger Journal Entry Lines']").close();
		forms.window("//forms:window[(@name='TMAI_HEADER')]").close();		
				
			
									/// Creating Receipt for the Invoice created///
			forms.button("//forms:button[(@name='NAV_CONTROLS_COLLAPSE_ALL_0')]").click();
			eBS_Reusable_Methods.selecttreelist("NAVIGATOR_LIST_0", "Receipts");
			eBS_Reusable_Methods.selecttreelist("NAVIGATOR_LIST_0", "Receipts|Receipts");
			forms.textField("//forms:textField[(@name='RGW_FOLDER_M_PAYMENT_METHOD_DSP_0')]").click();
			forms.textField("//forms:textField[(@name='RGW_FOLDER_M_PAYMENT_METHOD_DSP_0')]").setText(Recpmethod);
			forms.textField("//forms:textField[(@name='RGW_FOLDER_M_RECEIPT_NUMBER_0')]").click();
			String Recnum=eBS_Reusable_Methods.generateUniqueData("REC");
			forms.textField("//forms:textField[(@name='RGW_FOLDER_M_RECEIPT_NUMBER_0')]").setText(Recnum);
			forms.textField("//forms:textField[(@name='RGW_FOLDER_M_NET_AMOUNT_DSP_0')]").click();
			forms.textField("//forms:textField[(@name='RGW_FOLDER_M_NET_AMOUNT_DSP_0')]").setText(invamt);
			forms.textField("//forms:textField[(@name='RGW_FOLDER_M_CUSTOMER_NUMBER_0')]").click();
			
			//forms.textField("//forms:textField[(@name='RGW_FOLDER_M_CUSTOMER_NUMBER_0')]").setText(BiltoNum);
			//forms.textField("//forms:textField[(@name='RGW_FOLDER_M_CUSTOMER_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
			//forms.textField("//forms:textField[(@name='RGW_FOLDER_M_CUSTOMER_NAME_0')]").click();
		
		forms.textField("//forms:textField[(@name='RGW_FOLDER_M_TXN_NUMBER_0')]").click();
		forms.textField("//forms:textField[(@name='RGW_FOLDER_M_TXN_NUMBER_0')]").setText(ARinvoice);
		forms.textField("//forms:textField[(@name='RGW_FOLDER_M_TXN_NUMBER_0')]").invokeSoftKey("NEXT_FIELD");
		forms.window("//forms:window[(@name='APP_FOLDER')]").activate(true);
			
		//forms.listOfValues("//forms:listOfValues").select(ARinvoice);
		
		forms.button("//forms:button[(@name='RGW_FOLDER_APPLICATIONS_BUTTON_DETAIL_0')]").click();
		think(5);
		forms.textField("//forms:textField[(@name='APP_FOLDER_APPLY_DATE_0')]").click();
		
	if (forms.alertDialog("//forms:alertDialog").exists()){
		forms.alertDialog("//forms:alertDialog").clickYes();
		}
		forms.window("//forms:window[(@name='APP_FOLDER')]").clickToolBarButton("Save");
		forms.window("//forms:window[(@name='APP_FOLDER')]").close();
		
		//forms.textField("//forms:textField[(@name='RGW_FOLDER_M_PAYMENT_METHOD_DSP_0')]").setFocus();
		String AppliedAmt=eBS_Reusable_Methods.GetText("RGW_FOLDER_M_APPLIED_AMOUNT_0");
		String Receiptnum=eBS_Reusable_Methods.GetText("RGW_FOLDER_M_RECEIPT_NUMBER_0");
		
		
		forms.window("//forms:window[(@name='ENTER_RECEIPTS')]").selectMenu("Tools|Create Accounting");
		forms.button("//forms:button[(@name='SLACEXEC_OK_BP_0')]").click();
		think(20);	
		forms.choiceBox("//forms:choiceBox").clickButton("OK");
		forms.window("//forms:window[(@name='SLACEXEC')]").activate(true);
		forms.window("//forms:window[(@name='ENTER_RECEIPTS')]").activate(true);
		forms.textField("//forms:textField[(@name='RGW_FOLDER_M_PAYMENT_METHOD_DSP_0')]").setFocus();
		forms.window("//forms:window[(@name='ENTER_RECEIPTS')]").selectMenu("Tools|View Accounting");
		web.window("/web:window[@index='2' or @title='Subledger Journal Entry Lines']").waitForPage(null);
			
		String Acctname= web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@id='N696:AccountingClass:1']").getAttribute("text");
			info(Acctname);
			
		String Acctnum=web.element("/web:window[@index='2' or @title='Subledger Journal Entry Lines']/web:document[@index='0']/web:span[@id='N696:Account:1']").getAttribute("text");
			info(Acctnum);
						
		//web.window("/web:window[@index='2' or @title='Subledger Journal Entry Lines']").close();
		forms.window("//forms:window[(@name='ENTER_RECEIPTS')]").close();
		
		
		String Invoicedate=eBS_Reusable_Methods.getDateTimeFormat("format2");
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       String datetime=  sdf.format(cal.getTime()) ;		
       utilities.getFileService().appendStringToFile("C:/OracleATS/OFT/AR Sceanrios/TestResults.CSV", eval(datetime)+","+eval(Invoicedate)+","+ eval(Acctnum)+ "," + eval(Receiptnum)+ ","+ eval(BiltoNum)+ "," + eval(ARinvoice)+ "," + eval(AppliedAmt)+"\n");
	
	    
		
			
		}		
		
	

	public void finish() throws Exception {
		
		//eBS_Reusable_Methods.ERPLogout();	
		
	}
}


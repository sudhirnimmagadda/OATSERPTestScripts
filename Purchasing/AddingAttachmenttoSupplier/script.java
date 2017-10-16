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
		
		eBS_Reusable_Methods.SwitchResponsibility("Payables Manager");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Suppliers");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Suppliers");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Suppliers|Entry");
		web.window("/web:window[@index='2' or @title='Suppliers']").waitForPage(null);
	
	
	
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
		
		datatable.importExcel("C:\\OracleATS\\OFT\\Purchasing\\TestData\\Attachments\\Supplier Attachments.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        for (int i=1; i<rowcnt;i++){
        
        String Oraclesupplier=(String)datatable.getValue(i, "B");
        String Attachfilename=(String)datatable.getValue(i, "C");
        String Filelocation=(String)datatable.getValue(i, "D");
        String Description=(String)datatable.getValue(i, "E");
        String Categorylist=(String)datatable.getValue(i, "F");
        String Attachtype=(String)datatable.getValue(i, "G");
        
        String FilePath= Filelocation +Attachfilename;
		info(FilePath);
		
		web.textBox("/web:window[@index='2' or @title='Suppliers']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchSuppName' or @name='SearchSuppName' or @index='0']")
			.click();
		
		web.textBox("/web:window[@index='2' or @title='Suppliers']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='SearchSuppName' or @name='SearchSuppName' or @index='0']")
					.setText(Oraclesupplier);
			
		web.button("/web:window[@index='2' or @title='Suppliers']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='GoButton' or @index='1']")
					.click();
		think(10);
		
		               /// validating supplier exists or not///
		if (web.window("/web:window[@title='Suppliers']").exists()){
		String Suppliernum=web.element("/web:window[@index='0' or @title='Suppliers']/web:document[@index='0']/web:td[@index='147']").getAttribute("value");
		info(Suppliernum);
		if ((Suppliernum==null)){
			String Result=  Oraclesupplier + "doesnot exists";
			info("test");
			eBS_Reusable_Methods.writeresults(scriptpath,testcasename,Oraclesupplier,Attachfilename,Description,Categorylist,Attachtype,Result);
			continue;
			}
		}
		
		if (web.image("/web:window[@index='2' or @title='Suppliers']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:img[@alt='Update' or @src='https://erpint.fin.yahoo.com:8000/OA_MEDIA/detailsicon_enabled.gif']").exists()){
				web.image("/web:window[@index='2' or @title='Suppliers']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:img[@alt='Update' or @index='105' or @src='https://erpint.fin.yahoo.com:8000/OA_MEDIA/detailsicon_enabled.gif']")
						.click();
			web.window("/web:window[@index='2' or @title='Update DLF - 236724: Quick Update']").waitForPage(null);
			
		}	
			
		web.link("/web:window[@index='2' or @title='Update DLF - 236724: Quick Update']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Organization']").click();
		web.window("/web:window[@index='2' or @title='Organization']").waitForPage(null);
		web.button("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='AttachmentTable_ATTACH_/oracle/apps/pos/supplier/webui/OrganizationPG.attachItem:ATTACH_/oracle/apps/pos/supplier/webui/OrganizationPG.attachItem_AddAttachmentButton:0']")
					.click();
			
		//web.selectBox("/web:window[@index='2' or @title='Organization']/web:document[@index='0']/web:select[(@id='attType' or @index='0') and multiple mod 'False']")
		  //  .selectOptionByText("File");
			
		web.textBox("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:input_text[@id='attachTitle']").click();
		web.textBox("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:input_text[@id='attachTitle']")
					.setText(Attachfilename);
		web.textArea("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:textarea[@id='attachDescription' or @name='attachDescription' or @index='0']")
					.setText(Description);
		web.selectBox("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:select[(@id='categoryList' or @index='1') and multiple mod 'False']")
					.selectOptionByText(Categorylist);
		web.textBox("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:input_file[@id='FileInput' or @name='FileInput' or @index='0']")
					.click();
		
		
		//String filepath ="C:\\Attachments\\W9DLF.pdf";
		eBS_Reusable_Methods.saveFile(FilePath);
		
		/*/web:dialog_unknown[@text='Choose File to Upload' or @index='1']/web:accPane[@index='0' or @name='Choose File to Upload']
		/web:dialog_unknown[@text='File &name:' or @index='1']/web:accPushButton[@index='24' or @name='Cancel']
		*/
		web.button("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:input_submit[@value='Apply']").click();
		web.window(135, "/web:window[@index='2' or @title='Organization']").waitForPage(null);
			think(10);
		web.button("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:input_reset[@value='Close']").click();
			think(10);
		web.link("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Suppliers']").click();
		web.window("/web:window[@index='2' or @title='Suppliers']").waitForPage(null);
		datatable.setNextRow();
		String Result = "File Attached Sucessfully for " +Oraclesupplier;
		eBS_Reusable_Methods.writeresults(scriptpath,testcasename,Oraclesupplier,Attachfilename,Description,Categorylist,Attachtype,Result);	
		}
		
		

	}

	public void finish() throws Exception {
	}
}

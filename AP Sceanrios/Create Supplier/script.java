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
		web.clearAllPersistentCookies();
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
		
		
		datatable.importExcel("C:\\OracleATS\\OFT\\FA Sceanrios\\TestData\\Supplier Test Data.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        String OrgName=(String)datatable.getValue(1, "A");
        info(OrgName);
        String Altname=(String)datatable.getValue(1, "B");
        info(Altname);
        String Country=(String)datatable.getValue(1, "C");
        info(Country);
        String TaxPayID=(String)datatable.getValue(1, "D");
        info(TaxPayID);
        String Addline1=(String)datatable.getValue(1, "E");
        info(Addline1);
        String Addline2=(String)datatable.getValue(1, "F");
        info(Addline2);
        String City=(String)datatable.getValue(1, "G");
        info(City);
        String State=(String)datatable.getValue(1, "H");
        info(State);
        String Postalcode=(String)datatable.getValue(1, "I");
        info(Postalcode);
        String ADDName=(String)datatable.getValue(1, "J");
        info(ADDName);
        String AddPur=(String)datatable.getValue(1, "K");
        info(AddPur);
        String Phone=(String)datatable.getValue(1, "L");
        info(Phone);
        String EMailadd=(String)datatable.getValue(1, "M");
        info(EMailadd);
        String Crenumsite=(String)datatable.getValue(1, "N");
        info(Crenumsite);
        String CrtcntDir=(String)datatable.getValue(1, "O");
        info(CrtcntDir);
        String FrstName=(String)datatable.getValue(1, "P");
        info(FrstName);
        String Lstname=(String)datatable.getValue(1, "Q");
        info(Lstname);
        String Bankdtls=(String)datatable.getValue(1, "R");
        info(Bankdtls);
        String Crtbnk=(String)datatable.getValue(1, "S");
        info(Crtbnk);
        String Country1=(String)datatable.getValue(1, "T");
        info(Country1);
        String SelExtbnk=(String)datatable.getValue(1, "U");
        info(SelExtbnk);
        String BankNum=(String)datatable.getValue(1, "V");
        info(BankNum);
        String Accnum=(String)datatable.getValue(1, "W");
        info(Accnum);
        String AccName=(String)datatable.getValue(1, "X");
        info(AccName);
        String Currency=(String)datatable.getValue(1, "Y");
        info(Currency);
        String PayTerm=(String)datatable.getValue(1, "Z");
        info(PayTerm);
        String PayMeth=(String)datatable.getValue(1, "AA");
        info(PayMeth);
        String PayGroup=(String)datatable.getValue(1, "AB");
        info(PayGroup);

        
		eBS_Reusable_Methods.SwitchResponsibility("Payables Manager");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Suppliers");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Suppliers");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Suppliers|Entry");
		
		             					//// Launching Suppliers page ///
		
		web.window("/web:window[@index='2' or @title='Suppliers']").waitForPage(null);
		web.button("/web:window[@index='2' or @title='Suppliers']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='supCreatBtn' or @index='0']")
			.click();
		//web.window("/web:window[@index='2' or @title='Create Supplier']").waitForPage(null);
		web.textBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='organization_name' or @name='organization_name']")
			.click();
		
		web.textBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='organization_name' or @name='organization_name']")
			.setText(OrgName);
		
		web.textBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='alias' or @name='alias']")
			.click();
		
		web.textBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='org_name_pronunciation' or @name='org_name_pronunciation']")
					.click();
			
		web.textBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='org_name_pronunciation' or @name='org_name_pronunciation']")
					.setText(Altname);
			
		web.selectBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:select[(@id='supplierDff9' or @name='supplierDff9' or @index='2') and multiple mod 'False']")
					.selectOptionByText(Country);
		
		web.window("/web:window[@index='2' or @title='Create Supplier']").waitForPage(null);
			
		web.textBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='taxPayId' or @name='taxPayId' or @index='7']")
					.click();
			
		web.textBox("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='taxPayId' or @name='taxPayId' or @index='7']")
					.setText(TaxPayID);
			
		web.button("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='applyBtn' or @index='1']")
					.click();
		
		web.window("/web:window[@index='2' or @title='Update Radisson Test - 233445: Quick Update']").waitForPage(null);
		
		                         			//// Validating whether Supplier Exists or not ////
   if (web.element("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0']/web:div[@text='Error']").exists()){
	   String errordata=web.element("/web:window[@index='2' or @title='Create Supplier']/web:document[@index='0']/web:div[@text='There is currently another supplier in the system with the same Taxpayer Id.']").getAttribute("text");
	   info(errordata);
	   System.out.println("Supplier or Tax id : "+errordata);
	   reportFailure("Supplier or Tax id : "+errordata);
	   System.exit(0);
	  	        }
   
   String suppliernum=web.element("/web:window[@index='2' or @title='Update Radisson Test - 233445: Quick Update']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:span[@id='supplierNumber']")
					    			.getAttribute("text");  
	info(suppliernum);
			
	web.link("/web:window[@index='2' or @title='Update Radisson Test - 233445: Quick Update']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Organization' or @index='9']")
					.click();
	web.window("/web:window[@index='2' or @title='Organization']").waitForPage(null);
			
	web.link("/web:window[@index='2' or @title='Organization']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Tax Details' or @index='10']").click();
	
	web.window("/web:window[@index='2' or @title='Tax Details']").waitForPage(null);
			
	web.link("/web:window[@index='2' or @title='Tax Details']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Address Book' or @index='11']").click();
		
	web.window("/web:window[@index='2' or @title='Address Book']").waitForPage(null);
	
	
	 														//// creating  address for  supplier sites/////
			
	web.button("/web:window[@index='2' or @title='Address Book']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='Create' or @index='2']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Create/Update Address']").waitForPage(null);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex1' or @name='HzAddressStyleFlex1' or @index='1']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex1' or @name='HzAddressStyleFlex1' or @index='1']")
					.setText(Addline1);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex2' or @name='HzAddressStyleFlex2' or @index='2']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex2' or @name='HzAddressStyleFlex2' or @index='2']")
					.setText(City);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex5' or @name='HzAddressStyleFlex5' or @index='5']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex5' or @name='HzAddressStyleFlex5' or @index='5']")
					.setText(Addline2);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex6' or @name='HzAddressStyleFlex6' or @index='6']")
					.click();
			
	web.selectBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:select[(@id='HzAddressStyleFlex7' or @name='HzAddressStyleFlex7' or @index='0') and multiple mod 'False']")
					.selectOptionByText(State);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex8' or @name='HzAddressStyleFlex8' or @index='7']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex8' or @name='HzAddressStyleFlex8' or @index='7']")
					.setText(Postalcode);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='hzPartySiteName' or @name='hzPartySiteName' or @index='8']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='hzPartySiteName' or @name='hzPartySiteName' or @index='8']")
					.setText(ADDName);
			
	web.checkBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_checkbox[@id='paySite' or @name='paySite' or @index='2']").check(true);
			
	web.checkBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_checkbox[@id='purSite' or @name='purSite' or @index='1']").check(false);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='phAreaCode' or @name='phAreaCode' or @index='11']")
					.click();
			
	//web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='phAreaCode' or @name='phAreaCode' or @index='11']")
		//			.setText("703");
			
	//web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='phNumber' or @name='phNumber' or @index='12']")
		//			.click();
			
	//web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='phNumber' or @name='phNumber' or @index='12']")
		///			.setText("7033909600");
	
	
			
	web.element("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:td[@text='Purchasing' or @index='147']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='emailAddr' or @name='emailAddr' or @index='15']")
					.click();
			
	//web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='emailAddr' or @name='emailAddr' or @index='15']")
					//.setText("ravi.keesara@ahatis.com");
			
	web.button("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='nextBtn' or @index='1']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Create Address: Site Creation']").waitForPage(null);
			
	web.checkBox("/web:window[@index='2' or @title='Create Address: Site Creation']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_checkbox[@name='N10:selected:0' or @index='1']")
					.check(true);
			
	web.button(	"/web:window[@index='2' or @title='Create Address: Site Creation']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='applyBtn' or @index='2']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Address Book']").waitForPage(null);
			
	String siteaddress=web.element("/web:window[@index='2' or @title='Address Book']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:span[@text='Radissonsite' or @id='N32:addressName:0' or @index='67']")
					.getAttribute("text");
	info(siteaddress);
	
	
														/// create second site address////
			
	web.button(	"/web:window[@index='2' or @title='Address Book']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='Create' or @index='2']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Create/Update Address']").waitForPage(null);
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex1' or @name='HzAddressStyleFlex1' or @index='1']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex1' or @name='HzAddressStyleFlex1' or @index='1']")
					.setText("246 REFLECTIONS DR");
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex2' or @name='HzAddressStyleFlex2' or @index='2']")
					.click();
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex2' or @name='HzAddressStyleFlex2' or @index='2']")
					.setText("ste 23");
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex5' or @name='HzAddressStyleFlex5' or @index='5']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex5' or @name='HzAddressStyleFlex5' or @index='5']")
					.setText("SAN JOSE");
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex6' or @name='HzAddressStyleFlex6' or @index='6']")
					.click();
	
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex6' or @name='HzAddressStyleFlex6' or @index='6']")
					.setText("california");
			
	web.selectBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:select[(@id='HzAddressStyleFlex7' or @name='HzAddressStyleFlex7' or @index='0') and multiple mod 'False']")
					.selectOptionByText("CA");
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex8' or @name='HzAddressStyleFlex8' or @index='7']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='HzAddressStyleFlex8' or @name='HzAddressStyleFlex8' or @index='7']")
					.setText("2017");
			
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='hzPartySiteName' or @name='hzPartySiteName' or @index='8']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='hzPartySiteName' or @name='hzPartySiteName' or @index='8']")
					.setText("SanRamonBart10");
			
	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='phAreaCode' or @name='phAreaCode' or @index='11']")
					.click();
			
//	web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='phAreaCode' or @name='phAreaCode' or @index='11']")
	//				.setText("408");
			
	//web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='phNumber' or @name='phNumber' or @index='12']")
	//				.setText("9489489999");
			
	web.checkBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_checkbox[@id='purSite' or @name='purSite' or @index='1']")
					.check(true);
	
	//web.textBox("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='emailAddr' or @name='emailAddr' or @index='15']")
		//			.setText("ravi.keesara@ahatis.com");
			
	web.button("/web:window[@index='2' or @title='Create/Update Address']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='nextBtn' or @index='1']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Create Address: Site Creation']").waitForPage(null);
	
	web.checkBox("/web:window[@index='2' or @title='Create Address: Site Creation']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_checkbox[@name='N10:selected:0' or @index='1']")
				.check(true);
			
	web.button("/web:window[@index='2' or @title='Create Address: Site Creation']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='applyBtn' or @index='2']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Address Book']").waitForPage(null);
	
	think(20);
	
	                                 				/// contact directory///
			
	web.link("/web:window[@index='2' or @title='Address Book']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Contact Directory' or @href=\"javascript:_submitNav(&apos;DefaultFormName&apos;,&apos;https%3A//erpptp1.fin.yahoo.com%3A8000/OA_HTML/OA.jsp%3Fpage%3D/oracle/apps/pos/supplier/webui/ByrCntctDirPG%26retainAM%3DY%26_ti%3D765824128%26oapc%3D31%26OAMC%3D84856_68_0%26menu%3DY%26oaMenuLevel%3D4%26oas%3DETdFm80JgfCcfl0ZEInqOw..&apos;)\" or @index='12']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Update Radisson Test - 233445: Contact Directory']").waitForPage(null);
			
	web.button("/web:window[@index='2' or @title='Update Radisson Test - 233445: Contact Directory']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='CreateBtn' or @index='0']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Add Contact']").waitForPage(null);
			
	web.textBox("/web:window[@index='2' or @title='Add Contact']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='FirstName' or @name='FirstName' or @index='0']")
					.click();
	
	web.textBox("/web:window[@index='2' or @title='Add Contact']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='FirstName' or @name='FirstName' or @index='0']")
				.setText("ravi");
			
	web.textBox("/web:window[@index='2' or @title='Add Contact']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='LastName' or @name='LastName' or @index='2']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Add Contact']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='LastName' or @name='LastName' or @index='2']")
					.setText("keesara");
			
			
	web.button("/web:window[@index='2' or @title='Add Contact']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='applyBtn' or @index='1']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Update Radisson Test - 233445: Contact Directory']").waitForPage(null);
	
	
	                                        //// Business Classification /////
			
	web.link("/web:window[@index='2' or @title='Update Radisson Test - 233445: Contact Directory']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Business Classification' or @index='13']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Business Classification']").waitForPage(null);
	
	                                         ////Banking Details///
			
	web.link("/web:window[@index='2' or @title='Business Classification']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Banking Details' or @index='16']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Bank Accounts']").waitForPage(null);
	
	
			
	//web.button("/web:window[@index='2' or @title='Bank Accounts']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='BAAddButton' or @index='3']")
					//.click();
	
	web.button("/web:window[@index='2' or @title='Bank Accounts']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='CreateBankAccount111' or @index='4']")
	               .click();
		
	//web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
			
	web.textBox("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='Country' or @name='Country' or @index='0']")
					.click();
			
	web.image("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:img[@alt='Search: Country' or @index='30' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/alta/func_search_16_ena.png']")
					.click();
		
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
			
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.click();
			
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.setText(Bankdtls);
			
	web.button("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:button[@index='2']")
					.click();
		
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
	
	web.image("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:img[@alt='Quick Select' or @index='7' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/cache/cqs.gif']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Create Bank Account']")
					.waitForPage(null);
	
	                                              /// Entering Bank Name///
	
	web.textBox("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='BankNameSelect' or @name='BankNameSelect' or @index='1']")
	     .click();
			
	web.image("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0']/web:img[@alt='Search: Bank Name' or @index='36' or @src='https://erpint.fin.yahoo.com:8000/OA_HTML/cabo/images/alta/func_search_16_ena.png']")
					.click();
	
	//web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
	
	think(20);
	
	web.selectBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:select[(@id='categoryChoice' or @name='categoryChoice' or @index='0') and multiple mod 'False']")
	          	.selectOptionByText("Bank Number");
			
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.click();
			
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.setText(BankNum);
			
	web.button("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:button[@index='2']")
					.click();
	
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
			
	web.image("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:img[@alt='Quick Select' or @index='4' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/cache/cqs.gif']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Create Bank Account']").waitForPage(null);
	
	 																/// Entering Branch Name///
			
	web.image("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:img[@alt='Search: Branch Name' or @index='47' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/alta/func_search_16_ena.png']")
					.click();
		
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
			
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.click();
			
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.setText("san%");
			
	web.button("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:button[@index='2']")
					.click();
		
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
			
	web.image("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:img[@alt='Quick Select' or @index='4' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/cache/cqs.gif']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Create Bank Account']")
					.waitForPage(null);
	
	
													/// Entering Account Number and Account Name///
			
	
	web.textBox("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='AcctNumber' or @name='AcctNumber' or @index='3']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='AcctNumber' or @name='AcctNumber' or @index='3']")
					.setText(Accnum);
			
	web.button("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='Apply' or @index='1']")
					.click();
		
			
	web.textBox("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='AcctName' or @name='AcctName' or @index='6']")
					.click();
			
	web.textBox("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='AcctName' or @name='AcctName' or @index='6']")
					.setText(AccName);
	
			
	web.textBox("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='Currency' or @name='Currency' or @index='7']")
					.click();
	
	web.image("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0']/web:img[@alt='Search: Currency' or @index='63' or @src='https://erpint.fin.yahoo.com:8000/OA_HTML/cabo/images/alta/func_search_16_ena.png']").click();
	
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
	
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']").click();
	
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
	     .setText(Currency);
	
	web.button("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:button[@index='2']")
			.click();

	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);

	web.image("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:img[@alt='Quick Select' or @index='4' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/cache/cqs.gif']")
			.click();

	web.window("/web:window[@index='2' or @title='Create Bank Account']")
			.waitForPage(null);
	
	web.button("/web:window[@index='2' or @title='Create Bank Account']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='Apply_uixr' or @index='3']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Bank Accounts']").waitForPage(null);
			
	web.button("/web:window[@index='2' or @title='Bank Accounts']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='apply' or @index='1']")
					.click();
	web.window("/web:window[@index='2' or @title='Bank Accounts']").waitForPage(null);
	
	
			
	web.link("/web:window[@index='2' or @title='Bank Accounts']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Accounting' or @index='20']").click();
		
	web.window("/web:window[@index='2' or @title='Supplier Accounting Details']").waitForPage(null);
	
			
	web.link("/web:window[@index='2' or @title='Supplier Accounting Details']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Tax and Reporting' or @href=\"javascript:_submitNav(&apos;DefaultFormName&apos;,&apos;https%3A//erpptp1.fin.yahoo.com%3A8000/OA_HTML/OA.jsp%3Fpage%3D/oracle/apps/pos/supplier/webui/ByrTaxRptPG%26retainAM%3DY%26_ti%3D765824128%26oapc%3D47%26OAMC%3D84856_93_0%26menu%3DY%26oaMenuLevel%3D4%26oas%3D7dsgdfw6fuOG42B9bXbVyA..&apos;)\" or @index='20']")
					.click();
	web.window("/web:window[@index='2' or @title='Tax and Reporting']").waitForPage(null);
	
			
	web.link("/web:window[@index='2' or @title='Tax and Reporting']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Purchasing' or @index='21']")
					.click();
	web.window(500, "/web:window[@index='2' or @title='Purchasing']").waitForPage(null);
	
			
	web.link("/web:window[@index='2' or @title='Purchasing']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Receiving' or @index='22']")
					.click();
	web.window("/web:window[@index='2' or @title='Receiving']").waitForPage(null);
	
			
	web.link("/web:window[@index='2' or @title='Receiving']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Payment Details' or @index='23']")
					.click();
	web.window("/web:window[@index='2' or @title='Payment Details']").waitForPage(null);
			
	web.checkBox("/web:window[@index='2' or @title='Payment Details']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_checkbox[@id='PmtMthdAssignmentsRN:Defaultitem:0' or @name='PmtMthdAssignmentsRN:Defaultitem:0' or @index='0']")
					.check(true);
		
	web.button("/web:window[@index='2' or @title='Payment Details']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:button[@id='Apply_uixr' or @index='6']")
					.click();
		
	web.window("/web:window[@index='2' or @title='Payment Details']").waitForPage(null);
	
	
	                                      /// payment terms////
	
	web.link("/web:window[@index='2' or @title='Invoice Management']/web:document[@index='0' or @name='APPS_NAVIGATION']/web:a[@text='Invoice Management' or @index='25']")
			.click();
	
	web.element("/web:window[@index='2' or @title='Invoice Management']/web:document[@index='0']/web:label[@innerText='Terms' and @text='Terms' and @index='2']").click();
			
	web.textBox("/web:window[@index='2' or @title='Invoice Management']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:input_text[@id='N45:siteTerms:0' or @name='N45:siteTerms:0' or @index='5']").click();
	
	web.image("/web:window[@index='2' or @title='Invoice Management']/web:document[@index='0']/web:img[@alt='Search: Terms' or @index='139' or @src='https://erpint.fin.yahoo.com:8000/OA_HTML/cabo/images/alta/func_search_16_ena.png']").click();
	
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
	
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.click();
			
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
					.setText(PayTerm);
			
	web.button("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:button[@index='2']")
					.click();
		
	web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);
			
	web.image("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:img[@alt='Quick Select' or @index='4' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/cache/cqs.gif']")
					.click();
	
	                                 					/// entering Payment group////
	web.element("/web:window[@index='2' or @title='Invoice Management']/web:document[@index='0']/web:label[@innerText='Payment' and @text='Payment' and @index='1']").click();
	
	web.selectBox("/web:window[@index='2' or @title='Invoice Management']/web:document[@index='0']/web:form[@id='DefaultFormName' or @name='DefaultFormName' or @index='0']/web:select[(@id='N36:payGrp1:0' or @name='N36:payGrp1:0' or @index='8') and multiple mod 'False']").selectOptionByIndex(3);
	
	web.textBox("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:input_text[@name='searchText' or @index='0']")
			.click();

		web.button("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:form[@id='_LOVResFrm' or @name='_LOVResFrm' or @index='0']/web:button[@index='2']")
			.click();

		web.window("/web:window[@index='3' or @title='Search and Select List of Values']").waitForPage(null);

		web.image("/web:window[@index='3' or @title='Search and Select List of Values']/web:document[@index='1']/web:img[@alt='Quick Select' or @index='4' or @src='https://erpptp1.fin.yahoo.com:8000/OA_HTML/cabo/images/cache/cqs.gif']")
				.click();
		web.window("/web:window[@index='2' or @title='Payment Details']").close();
		
		

	}

	public void finish() throws Exception {
	}
}

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
		
		datatable.importExcel("C:\\OracleATS\\OFT\\Purchasing\\TestData\\POApproverValidation.xls");
        datatable.getCurrentSheet();
        int rowcnt=datatable.getRowCount();
        int colcnt=datatable.getColumnCount(0);
        
        
        String username=(String)datatable.getValue(1, "A");
        String pwd=(String)datatable.getValue(1, "B");
        String Description=(String)datatable.getValue(1, "D");
        String Categorylist=(String)datatable.getValue(1, "E");
        
		
		eBS_Reusable_Methods.SwitchResponsibility("Global HR Manager");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
					.selectItem("People");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
					.selectItem("People|Enter and Maintain");
		forms.choiceBox("//forms:choiceBox").clickButton("No");
		forms.textField("//forms:textField[(@name='EMP_QF_FULL_NAME_0')]").setText("oats, user");
		forms.button("//forms:button[(@name='EMP_QF_FIND_0')]").click();
		
		String lastname=eBS_Reusable_Methods.GetText("PERSON_LAST_NAME_0");
		String firstname=eBS_Reusable_Methods.GetText("PERSON_FIRST_NAME_0");
		String empnum=eBS_Reusable_Methods.GetText("PERSON_EMPLOYEE_NUMBER_0");
		
		if  (lastname==null){
			fail("user not created as an employee");
			}
		
		String endata=eBS_Reusable_Methods.GetText("PERSON_D_EFFECTIVE_END_DATE_0");
		
		  if(!endata.equals("")){
			fail("user is end dated");
			}
		
		forms.tab("//forms:tab[(@name='REGION_LIST')]").select("Employment");
		forms.tab("//forms:tab[(@name='REGION_LIST')]").select("Office Details");
		String email=eBS_Reusable_Methods.GetText("PERSON_EMAIL_ADDRESS_0");
		String person=eBS_Reusable_Methods.GetText("PERSON_D_HOME_OFFICE_0");
		
		
		forms.button("//forms:button[(@name='CTL_PERWSEPI_NAV_NAV_BUTTON3_0')]").click();
		String orgname=eBS_Reusable_Methods.GetText("ASSGT_ORGANIZATION_NAME_0");
		
		 for (int i=1; i<rowcnt;i++){
				String position=(String)datatable.getValue(i, "C");
				String jobname=eBS_Reusable_Methods.GetText("ASSGT_JOB_NAME_0");
				info(jobname);
				 if (position.contentEquals(jobname)){
					info("user is assigned with this" + jobname);
					break;
				 	}
				 }
		
		
		forms.tab("//forms:tab[(@name='ASSGT_TAB_CANVAS')]").select("Supervisor");
		String supervisiornum=eBS_Reusable_Methods.GetText("ASSGT_SUPERVISOR_EMPLOYEE_NUMBER_0");
		String supervisiorname=eBS_Reusable_Methods.GetText("ASSGT_SUPERVISOR_NAME_0");
		info(supervisiorname);
		forms.window("//forms:window[(@name='ASSGT')]").close();
		forms.window("//forms:window[(@name='WINDOW1')]").close();
		forms.window(187, "//forms:window[(@name='NAVIGATOR')]").activate(true);
		
		
		
		eBS_Reusable_Methods.SwitchResponsibility("System Administrator");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Security");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Security");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Security|User");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Security|User|Define");
		forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").invokeSoftKey("ENTER_QUERY");
		forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").setText(username);
		forms.textField("//forms:textField[(@name='USER_USER_NAME_0')]").invokeSoftKey("EXECUTE_QUERY");
		String usernm=eBS_Reusable_Methods.GetText("USER_USER_NAME_0");
		String emailid=eBS_Reusable_Methods.GetText("USER_EMAIL_ADDRESS_0");
		String userendate=eBS_Reusable_Methods.GetText("USER_END_DATE_0");
		info(usernm);
		info(emailid);
		info(userendate);
		
		String respenddate=eBS_Reusable_Methods.GetText("USER_RESP_END_DATE_0");
		
		
		
			
		

	}

	public void finish() throws Exception {
	}
}

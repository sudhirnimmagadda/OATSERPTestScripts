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
		
		datatable.importExcel("C:\\OracleATS\\OFT\\FA Sceanrios\\TestData\\FA Data.xls");
		datatable.getCurrentSheet();
		int rowcnt=datatable.getRowCount();
		int colcnt=datatable.getColumnCount(0);
		String desc=(String)datatable.getValue(1,"A");
	    String Assettype=(String)datatable.getValue(1,"B");
		String Majcat=(String)datatable.getValue(1,"C");
		String Mincat=(String)datatable.getValue(1,"D");
	    String Loc=(String)datatable.getValue(1,"E");
		String Book=(String)datatable.getValue(1,"F");
		String Costamt=(String)datatable.getValue(1,"G");
	    String YTDAMT=(String)datatable.getValue(1,"H");
		String LTDAMT=(String)datatable.getValue(1,"I");
		String Salval=(String)datatable.getValue(1,"J");
	    String SerDat=(String)datatable.getValue(1,"K");
		String SubAct=(String)datatable.getValue(1,"L");
		String Region=(String)datatable.getValue(1,"M");
	    String Function=(String)datatable.getValue(1,"N");
		String Cstctr=(String)datatable.getValue(1,"O");
		String IntCo=(String)datatable.getValue(1,"P");
	    String Feature=(String)datatable.getValue(1,"Q");
		String Country=(String)datatable.getValue(1,"R");
		String State=(String)datatable.getValue(1,"S");
	    String County=(String)datatable.getValue(1,"T");
		String City=(String)datatable.getValue(1,"U");
		String Building=(String)datatable.getValue(1,"V");
		
		
		
		eBS_Reusable_Methods.SwitchResponsibility("Fixed Assets Manager");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Assets|Asset Workbench");
		
		  					/// enter asset details////
		forms.button("//forms:button[(@name='SMART_FIND_SF_NEW_BUTTON_0')]").click();
		forms.textField("//forms:textField[(@name='ASSET_DESCRIPTION_0')]").setText("Dell Laptop");
		forms.textField("//forms:textField[(@name='ASSET_ATTRIBUTE_CATEGORY_CODE_0')]").openDialog();
		forms.flexWindow("//forms:flexWindow").openDialog("Major Category", "");
		forms.listOfValues("//forms:listOfValues").select("COMPUTERS|Computer Equipment");
		forms.flexWindow("//forms:flexWindow").openDialog("Minor Category", "");
		forms.listOfValues("//forms:listOfValues").select("LAPTOP|Laptop");	
		forms.flexWindow("//forms:flexWindow").setText("Location", "",	"00");
		forms.flexWindow("//forms:flexWindow").clickOk();
		forms.flexWindow("//forms:flexWindow").clickOk();
		forms.list("//forms:list[(@name='ASSET_CAP_ASSET_TYPE_0')]").selectItem("Capitalized");
		
		
		forms.button("//forms:button[(@name='ASSET_BOOKS_ASSET_0')]").click();
		forms.textField("//forms:textField[(@name='FA_BOOKS_HEADER_BOOK_TYPE_CODE_0')]").openDialog();
		forms.window(117, "//forms:window[(@name='FA_BOOKS')]").activate(true);
		forms.listOfValues("//forms:listOfValues").select("YAHOO! INC.|YAHOO! INC.");
		forms.textField("//forms:textField[(@name='FA_BOOKS_HEADER_TRANSACTION_NAME_0')]").setFocus();
		forms.textField("//forms:textField[(@name='FA_BOOKS_COST_0')]").setText("1500");
		forms.textField("//forms:textField[(@name='FA_BOOKS_YTD_DEPRN_0')]").setText("10");
		forms.textField("//forms:textField[(@name='FA_BOOKS_LTD_DEPRN_0')]").setText("10");
		forms.textField("//forms:textField[(@name='FA_BOOKS_SALVAGE_VALUE_0')]").setText("10");
		forms.textField("//forms:textField[(@name='FA_BOOKS_DATE_PLACED_IN_SERVICE_0')]").setText("10-JUl-2017");
		forms.textField("//forms:textField[(@name='FA_BOOKS_PRORATE_CONVENTION_CODE_0')]").setFocus();
		forms.button("//forms:button[(@name='FA_BOOKS_ASSIGNMENTS_BOOKS_0')]").click();
		
		
		
		forms.textField("//forms:textField[(@name='ASSIGNMENTS_DIST_IN_OUT_0')]").setText("1");
		forms.textField("//forms:textField[(@name='ASSIGNMENTS_DIST_EXPENSE_ACCOUNT_0')]").openDialog();
		forms.flexWindow(135, "//forms:flexWindow").openDialog("Company","");
		forms.listOfValues(136, "//forms:listOfValues").select("110|Yahoo! Inc.");
		forms.flexWindow(139, "//forms:flexWindow").setText("Sub Account","", "1010");
		forms.flexWindow(140, "//forms:flexWindow").setText("Region", "","3840");
		forms.flexWindow(141, "//forms:flexWindow").setText("Function", "",	"160");
		forms.flexWindow(142, "//forms:flexWindow").setText("Cost Center","", "502695");
		forms.flexWindow(143, "//forms:flexWindow").setText("Interco", "","000");
		forms.flexWindow(144, "//forms:flexWindow").setText("Future", "","0000");
		forms.flexWindow(145, "//forms:flexWindow").clickOk();
		
								//// enter location details ///
		forms.textField("//forms:textField[(@name='ASSIGNMENTS_DIST_LOCATION_DISP_0')]").openDialog();
		forms.flexWindow(151, "//forms:flexWindow").openDialog("Country","");
		forms.listOfValues(152, "//forms:listOfValues").select("US|United States");
		forms.flexWindow(155, "//forms:flexWindow").openDialog("State", "");
		forms.listOfValues(156, "//forms:listOfValues").select("CA|California");
		forms.flexWindow(159, "//forms:flexWindow").openDialog("County", "");
		forms.listOfValues(160, "//forms:listOfValues").select("LA COUNTY|Los Angeles County");
		forms.flexWindow(163, "//forms:flexWindow").openDialog("City", "");
		forms.listOfValues(164, "//forms:listOfValues").select("SAN DIEGO|San Diego");
		forms.flexWindow(167, "//forms:flexWindow").openDialog("Building","");
		forms.listOfValues(168, "//forms:listOfValues").select("1 ALHAMBRA|1 Alhambra Plaza");
		forms.flexWindow(171, "//forms:flexWindow").clickOk();
		//forms.textField(174,"//forms:textField[(@name='ASSIGNMENTS_DIST_ASSIGNED_TO_NAME_0')]").openDialog();
		forms.window(182, "//forms:window[(@name='ASSIGNMENTS')]").clickToolBarButton("Save");
		forms.choiceBox(185, "//forms:choiceBox").clickButton("OK");
		

	}

	public void finish() throws Exception {
	}
}

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
		
		
		eBS_Reusable_Methods.SwitchResponsibility("General Ledger Super User");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").focusItem("Journals");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals");
		forms.treeList("//forms:treeList[(@name='NAVIGATOR_LIST_0')]").selectItem("Journals|Enter");
		
		beginStep("[6] Find Journals", 0);
		{
			forms.captureScreenshot(26);
			{
				think(20.24);
			}
			forms.textField(27,
					"//forms:textField[(@name='FOLDER_QF_BATCH_NAME_0')]")
					.setFocus();
			{
				think(0.047);
			}
			forms.button(28, "//forms:button[(@name='FOLDER_QF_NEW_HEADER_0')]")
					.click();
		}
		endStep();
		beginStep("[7] Journals (YAHOO! US) - [New]", 0);
		{
			forms.captureScreenshot(30);
			{
				think(18.902);
			}
			forms.textField(31, "//forms:textField[(@name='HEADER_NAME_0')]")
					.setText("TestJE_1");
			{
				think(0.042);
			}
			forms.textField(32, "//forms:textField[(@name='HEADER_NAME_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(0.854);
			}
			forms.textField(33,
					"//forms:textField[(@name='HEADER_DESCRIPTION_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(1.094);
			}
			forms.textField(34,
					"//forms:textField[(@name='HEADER_LEDGER_NAME_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(2.439);
			}
			forms.textField(35,
					"//forms:textField[(@name='HEADER_USER_JE_CATEGORY_NAME_0')]")
					.openDialog();
			{
				think(2.515);
			}
			forms.window(36, "//forms:window[(@name='HEADER')]").activate(true);
			{
				think(0.172);
			}
			forms.listOfValues(37, "//forms:listOfValues").select(
					"Adjustment|Adjusting Journal Entry");
		}
		endStep();
		beginStep("[8] Categories", 0);
		{
			forms.captureScreenshot(39);
			{
				think(1.437);
			}
			applet.button(
					55,
					"/applet:TJavaWindow[@text='Categories']/applet:TJavaButton[@text='OK' and @posIndex='1']")
					.click();
		}
		endStep();
		beginStep("[9] Journals (YAHOO! US) - TestJE_1 11-SEP-2017 13:20:49", 0);
		{
			forms.captureScreenshot(57);
			{
				think(0.938);
			}
			forms.textField(58,
					"//forms:textField[(@name='HEADER_PERIOD_NAME_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(1.922);
			}
			forms.textField(59,
					"//forms:textField[(@name='HEADER_DEFAULT_EFFECTIVE_DATE_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(0.361);
			}
			forms.textField(60,
					"//forms:textField[(@name='HEADER_ORIGINATING_BAL_SEG_VALUE_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(0.608);
			}
			forms.textField(61,
					"//forms:textField[(@name='HEADER_CONTROL_TOTAL_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(0.592);
			}
			forms.textField(62,
					"//forms:textField[(@name='HEADER_CURRENCY_CODE_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(1.037);
			}
			forms.textField(63,
					"//forms:textField[(@name='HEADER_ACCRUAL_REV_PERIOD_NAME_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(0.005);
			}
			forms.list(64,
					"//forms:list[(@name='HEADER_ACCRUAL_REV_CHANGE_SIGN_FLAG_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(0.619);
			}
			forms.textField(65,
					"//forms:textField[(@name='LINES_JE_LINE_NUM_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(1.703);
			}
			forms.textField(66,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_0')]")
					.invokeSoftKey("PREVIOUS_FIELD");
			{
				think(3.019);
			}
			forms.textField(67,
					"//forms:textField[(@name='LINES_JE_LINE_NUM_0')]")
					.setText("1");
			{
				think(0.059);
			}
			forms.textField(68,
					"//forms:textField[(@name='LINES_JE_LINE_NUM_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(1.031);
			}
			forms.textField(69,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_0')]")
					.openDialog();
		}
		endStep();
		beginStep("[10] GLOBAL Accounting Flexfield", 0);
		{
			forms.captureScreenshot(71);
			{
				think(2.252);
			}
			forms.flexWindow(72, "//forms:flexWindow")
					.openDialog("Company", "");
			{
				think(38.767);
			}
			forms.listOfValues(73, "//forms:listOfValues").select(
					"110|Yahoo! Inc.");
		}
		endStep();
		beginStep("[11] Company", 0);
		{
			forms.captureScreenshot(75);
			{
				think(1.362);
			}
			forms.flexWindow(76, "//forms:flexWindow")
					.openDialog("Account", "");
			{
				think(5.378);
			}
			forms.listOfValues(77, "//forms:listOfValues").select(
					"100300|Receivables -Remainco");
		}
		endStep();
		beginStep("[12] Account", 0);
		{
			forms.captureScreenshot(79);
			{
				think(1.17);
			}
			forms.flexWindow(80, "//forms:flexWindow").openDialog(
					"Sub Account", "");
			{
				think(4.027);
			}
			forms.listOfValues(81, "//forms:listOfValues").select(
					"1013|Credit Suisse");
		}
		endStep();
		beginStep("[13] Sub Account", 0);
		{
			forms.captureScreenshot(83);
			{
				think(3.526);
			}
			forms.flexWindow(84, "//forms:flexWindow").setText("Region", "",
					"0");
			{
				think(0.188);
			}
			applet.button(
					97,
					"/applet:TJavaWindow[@text='Sub Account']/applet:TJavaButton[@text='OK' and @posIndex='1']")
					.click();
			{
				think(0.326);
			}
			forms.flexWindow(98, "//forms:flexWindow").setText("Function", "",
					"0");
			{
				think(0.517);
			}
			forms.flexWindow(99, "//forms:flexWindow").setText("Cost Center",
					"", "0");
			{
				think(1.762);
			}
			forms.flexWindow(100, "//forms:flexWindow").clickOk();
		}
		endStep();
		beginStep("[14] Journals (YAHOO! US) - TestJE_1 11-SEP-2017 13:20:49",
				0);
		{
			forms.captureScreenshot(102);
			{
				think(7.317);
			}
			forms.textField(103,
					"//forms:textField[(@name='LINES_ENTERED_DR_0')]").setText(
					"300");
			{
				think(0.067);
			}
			forms.textField(104,
					"//forms:textField[(@name='LINES_ENTERED_DR_0')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(0.597);
			}
			forms.textField(105,
					"//forms:textField[(@name='LINES_DESCRIPTION_0')]")
					.invokeSoftKey("NEXT_FIELD");
		}
		endStep();
		beginStep("[15] Enter Journals: Lines", 0);
		{
			forms.captureScreenshot(107);
			{
				think(1.491);
			}
			forms.flexWindow(108, "//forms:flexWindow").clickCancel();
		}
		endStep();
		beginStep("[16] Journals (YAHOO! US) - TestJE_1 11-SEP-2017 13:20:49",
				0);
		{
			forms.captureScreenshot(110);
			{
				think(3.542);
			}
			forms.textField(111,
					"//forms:textField[(@name='LINES_JE_LINE_NUM_1')]")
					.setFocus();
			{
				think(1.256);
			}
			forms.textField(112,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_1')]")
					.openDialog();
		}
		endStep();
		beginStep("[17] GLOBAL Accounting Flexfield", 0);
		{
			forms.captureScreenshot(114);
			{
				think(0.913);
			}
			forms.flexWindow(115, "//forms:flexWindow").openDialog("Account",
					"");
			{
				think(3.478);
			}
			forms.listOfValues(116, "//forms:listOfValues").select(
					"100301|Cash Clearing - Remainco");
		}
		endStep();
		beginStep("[18] Account", 0);
		{
			forms.captureScreenshot(118);
			{
				think(1.183);
			}
			forms.flexWindow(119, "//forms:flexWindow").openDialog(
					"Sub Account", "");
			{
				think(9.751);
			}
			forms.listOfValues(120, "//forms:listOfValues").select(
					"1012|Goldman, Sachs, & Company - inactive");
		}
		endStep();
		beginStep("[19] Sub Account", 0);
		{
			forms.captureScreenshot(122);
			{
				think(5.22);
			}
			forms.flexWindow(123, "//forms:flexWindow").setText("Region", "",
					"00");
			{
				think(1.163);
			}
			forms.flexWindow(124, "//forms:flexWindow").setText("Function", "",
					"0");
			{
				think(0.725);
			}
			forms.flexWindow(125, "//forms:flexWindow").setText("Cost Center",
					"", "0");
			{
				think(1.712);
			}
			forms.flexWindow(126, "//forms:flexWindow").clickOk();
		}
		endStep();
		beginStep("[20] Journals (YAHOO! US) - TestJE_1 11-SEP-2017 13:20:49",
				0);
		{
			forms.captureScreenshot(128);
			{
				think(3.718);
			}
			forms.textField(129,
					"//forms:textField[(@name='LINES_ENTERED_DR_1')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(2.551);
			}
			forms.textField(130,
					"//forms:textField[(@name='LINES_ENTERED_CR_1')]").setText(
					"300");
			{
				think(0.0);
			}
			forms.textField(131,
					"//forms:textField[(@name='LINES_ENTERED_CR_1')]")
					.invokeSoftKey("NEXT_FIELD");
			{
				think(2.78);
			}
			forms.window(132, "//forms:window[(@name='HEADER')]")
					.clickToolBarButton("Save");
			{
				think(1.489);
			}
			forms.statusBar(133, "//forms:statusBar")
					.assertText(
							"FormsFT AutoValidation: Verify StatusBar text value",
							"FRM-40400: Transaction complete: 3 records applied and saved.",
							MatchOption.Exact);
			{
				think(2.71);
			}
			forms.button(134,
					"//forms:button[(@name='CONTROL_HEADER_APPROVE_0')]")
					.click();
		}
		endStep();
		beginStep("[21] Note", 0);
		{
			forms.captureScreenshot(136);
			{
				think(10.024);
			}
			forms.choiceBox(137, "//forms:choiceBox").clickButton("OK");
		}
		endStep();
		beginStep("[22] Journals (YAHOO! US) - TestJE_1 11-SEP-2017 13:20:49",
				0);
		{
			forms.captureScreenshot(139);
			{
				think(2.29);
			}
			forms.button(140, "//forms:button[(@name='CONTROL_HEADER_POST_0')]")
					.click();
		}
		endStep();
		beginStep("[23] Note", 0);
		{
			forms.captureScreenshot(142);
			{
				think(3.146);
			}
			forms.choiceBox(143, "//forms:choiceBox").clickButton("OK");
		}
		endStep();
		beginStep("[24] Journals (YAHOO! US) - TestJE_1 11-SEP-2017 13:20:49",
				0);
		{
			forms.captureScreenshot(145);
			{
				think(10.504);
			}
			forms.window(146, "//forms:window[(@name='HEADER')]").close();
		}
		endStep();
		beginStep("[25] Enter Journals (YAHOO! US)", 0);
		{
			forms.captureScreenshot(148);
			{
				think(1.666);
			}
			forms.window(149, "//forms:window[(@name='FOLDER')]").close();
		}
		endStep();

	}

	public void finish() throws Exception {
	}
}

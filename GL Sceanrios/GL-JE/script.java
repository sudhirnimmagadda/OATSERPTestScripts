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

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.applet.api.AppletService applet;
	@ScriptService oracle.oats.scripting.modules.formsFT.api.FormsService forms;

	public void initialize() throws Exception {
		browser.launch();
	}

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {
		beginStep(
				"[1] Certificate Error: Navigation Blocked (/erpptp1.fin.yahoo.com:8000/)",
				0);
		{
			web.window(2, "/web:window[@index='0' or @title='about:blank']")
					.navigate("https://erpptp1.fin.yahoo.com:8000/");
			web.window(4,
					"/web:window[@index='0' or @title='Certificate Error: Navigation Blocked']")
					.waitForPage(null);
			{
				think(1.523);
			}
			web.link(
					5,
					"/web:window[@index='0' or @title='Certificate Error: Navigation Blocked']/web:document[@index='0']/web:a[@text='Continue to this website (not recommended).' or @href='https://erpptp1.fin.yahoo.com:8000/' or @index='1']")
					.click();
		}
		endStep();
		beginStep(
				"[2] E-Business Suite Home Page Redirect (/erpptp1.fin.yahoo.com:8000/)",
				0);
		{
			web.window(6, "/web:window[@index='0' or @title='Login']")
					.waitForPage(null);
			{
				think(4.221);
			}
			web.textBox(
					7,
					"/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_text[@id='usernameField' or @name='usernameField' or @index='0']")
					.setText("oatsuser");
			{
				think(2.252);
			}
			web.textBox(
					8,
					"/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_password[@id='passwordField' or @name='passwordField' or @index='0']")
					.click();
			{
				think(1.245);
			}
			web.textBox(
					9,
					"/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_password[@id='passwordField' or @name='passwordField' or @index='0']")
					.setPassword(deobfuscate("4zusrct2Oc8OaAiVmHHImw=="));
			{
				think(2.168);
			}
			web.button(
					10,
					"/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:button[@index='0']")
					.click();
		}
		endStep();
		beginStep("[3] Home (/OA.jsp)", 0);
		{
			web.window(11, "/web:window[@index='0' or @title='Home']")
					.waitForPage(null);
			{
				think(4.247);
			}
			web.element(
					14,
					"/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='General Ledger Super User' or @index='61']")
					.click();
		}
		endStep();
		beginStep("[4] Home (/OA.jsp)", 0);
		{
			web.window(15, "/web:window[@index='0' or @title='Home']")
					.waitForPage(null);
			{
				think(4.126);
			}
			web.element(
					18,
					"/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='Journals' or @index='73']")
					.click();
		}
		endStep();
		beginStep("[5] Home (/OA.jsp)", 0);
		{
			web.window(19, "/web:window[@index='0' or @title='Home']")
					.waitForPage(null);
			{
				think(2.217);
			}
			web.element(
					22,
					"/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='Enter' or @index='75']")
					.click();
		}
		endStep();
		beginStep(
				"[6] Home (/fndlist.jar&jinit_appletcache=off&gp15=icx_ticket&gv15=dlKQXcIBt78VGZDt-u9TAg..&gp2=resp_app&gv2=SQLGL&gp3=resp&gv3=GENERAL_LEDGER_SUPER_USER&gp4=sec_group&gv4=STANDARD&gp5=function&gv5=GLXJEENT_A&gp6=other_params&gv6=&gp7=jsp_agent&gv7=https%3A%2F%2Ferpptp1.fin.yahoo.com%3A8000%2FOA_HTML%2F&gp13=dbc&gv13=ERPPTP1&fsst=10995717487121652172701973800129712673)",
				0);
		{
			web.window(23,
					"/web:window[@index='1' or @title='Oracle E-Business Suite R12']")
					.waitForPage(null);
		}
		endStep();
		beginStep("[7] Find Journals", 0);
		{
			forms.captureScreenshot(27);
			{
				think(18.497);
			}
			forms.textField(28,
					"//forms:textField[(@name='FOLDER_QF_BATCH_NAME_0')]")
					.setFocus();
			
			forms.button(29, "//forms:button[(@name='FOLDER_QF_NEW_BATCH_0')]")
					.click();
		
			forms.textField(32, "//forms:textField[(@name='BATCH_NAME_0')]")
					.setText("oatsbatch1");
			{
				think(7.246);
			}
			forms.textField(33,
					"//forms:textField[(@name='BATCH_DESCRIPTION_0')]")
					.setText("oatstest1");
			
			forms.button(34, "//forms:button[(@name='BATCH_GO_HEADER_0')]")
					.click();
		
			forms.textField(37, "//forms:textField[(@name='HEADER_NAME_0')]")
					.setText("OATS-JE1");
			{
				think(5.031);
			}
			forms.textField(38,
					"//forms:textField[(@name='HEADER_USER_JE_CATEGORY_NAME_0')]")
					.openDialog();
			{
				think(1.938);
			}
			forms.window(39, "//forms:window[(@name='HEADER')]").activate(true);
			{
				think(0.163);
			}
			forms.listOfValues(40, "//forms:listOfValues").select("AX Receivables|AX Receivables Entry");
		
			
			forms.textField(44,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_0')]")
					.setFocus();
			
			forms.textField(45,
					"//forms:textField[(@name='LINES_JE_LINE_NUM_0')]")
					.setText("1");
			
			forms.textField(254,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_0')]")
					.setFocus();
			
			forms.textField(560,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_0')]")
					.setText("110-100111-1010-0000-000-000000-000-0000");
			
			forms.textField(561,
					"//forms:textField[(@name='LINES_ENTERED_DR_0')]").setText("700");
			{
				think(0.063);
			}
			forms.textField(562,
					"//forms:textField[(@name='LINES_ENTERED_DR_0')]")
					.invokeSoftKey("NEXT_FIELD");
			
			forms.textField(563,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_1')]")
					.setText("110-100120-1010-000-000-000000-000-0000");
			
			forms.textField(564,
					"//forms:textField[(@name='LINES_ACCOUNTING_FLEXFIELD_1')]")
					.invokeSoftKey("NEXT_FIELD");
			
			forms.textField(565,
					"//forms:textField[(@name='LINES_ENTERED_CR_1')]").setText("700");
			
			forms.textField(566,
					"//forms:textField[(@name='LINES_ENTERED_CR_1')]")
					.invokeSoftKey("NEXT_FIELD");
			
			forms.window(567, "//forms:window[(@name='HEADER')]")
					.clickToolBarButton("Save");
			
			forms.button(569,
					"//forms:button[(@name='CONTROL_HEADER_APPROVE_0')]")
					.click();
		
			forms.choiceBox(572, "//forms:choiceBox").clickButton("OK");
		
		
			forms.window(575, "//forms:window[(@name='HEADER')]").close();
		
			forms.window(578, "//forms:window[(@name='BATCH')]").close();
		
			forms.window(581, "//forms:window[(@name='FOLDER')]").close();
			
			forms.window(582, "//forms:window[(@name='NAVIGATOR')]").activate(true);
		
			forms.treeList(88, "//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
				.selectItem("Journals");
	
			forms.treeList(111, "//forms:treeList[(@name='NAVIGATOR_LIST_0')]")
					.selectItem("Journals|Enter");
		
			forms.textField(610,
					"//forms:textField[(@name='FOLDER_QF_BATCH_NAME_0')]")
					.setFocus();
			
			forms.textField(611,
					"//forms:textField[(@name='FOLDER_QF_HEADER_NAME_0')]")
					.setText("OATSJE");
			
			forms.button(612, "//forms:button[(@name='FOLDER_QF_FIND_0')]")
					.click();
		
			forms.button(615,
					"//forms:button[(@name='CONTROL_REVIEW_HEADER_0')]")
					.click();
		
			forms.window(618, "//forms:window[(@name='HEADER')]").close();
		}
		endStep();

	}

	public void finish() throws Exception {
	}
}

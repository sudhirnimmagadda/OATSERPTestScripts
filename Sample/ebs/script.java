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
	@ScriptService oracle.oats.scripting.modules.datatable.api.DataTableService datatable;

	public void initialize() throws Exception {
		browser.launch();
	}

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {
		beginStep(
				"[1] Internet Explorer cannot display the webpage (/erpptp1.fin.yahoo.com:8000/)",
				0);
		{
			web.window(2, "/web:window[@index='0' or @title='about:blank']")
					.navigate("https://erpptp1.fin.yahoo.com:8000/");
			web.window(4,
					"/web:window[@index='0' or @title='Certificate Error: Navigation Blocked']")
					.waitForPage(null);
			{
				think(45.357);
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
				think(5.163);
			}
			web.textBox(
					7,
					"/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_text[@id='usernameField' or @name='usernameField' or @index='0']")
					.setText("oatsuser");
			{
				think(2.047);
			}
			web.textBox(
					8,
					"/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_password[@id='passwordField' or @name='passwordField' or @index='0']")
					.click();
			{
				think(1.295);
			}
			web.textBox(
					9,
					"/web:window[@index='0' or @title='Login']/web:document[@index='0']/web:form[@id='login' or @index='0']/web:input_password[@id='passwordField' or @name='passwordField' or @index='0']")
					.setPassword(deobfuscate("4zusrct2Oc8OaAiVmHHImw=="));
			{
				think(1.057);
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
				think(1.179);
			}
			web.element(
					14,
					"/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='System Administrator' or @index='71']")
					.click();
		}
		endStep();
		beginStep("[4] Home (/OA.jsp)", 0);
		{
			web.window(15, "/web:window[@index='0' or @title='Home']")
					.waitForPage(null);
			{
				think(17.004);
			}
			web.element(
					18,
					"/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='Requests' or @index='217']")
					.click();
		}
		endStep();
		beginStep("[5] Home (/OA.jsp)", 0);
		{
			web.window(19, "/web:window[@index='0' or @title='Home']")
					.waitForPage(null);
			{
				think(1.656);
			}
			web.element(
					22,
					"/web:window[@index='0' or @title='Home']/web:document[@index='0']/web:div[@text='Run' or @index='219']")
					.click();
		}
		endStep();
		beginStep(
				"[6] Home (/fndlist.jar&jinit_appletcache=off&gp15=icx_ticket&gv15=kwlvE3eADQQj-slelHOGHw..&gp2=resp_app&gv2=SYSADMIN&gp3=resp&gv3=SYSTEM_ADMINISTRATOR&gp4=sec_group&gv4=STANDARD&gp5=function&gv5=FND_FNDRSRUN&gp6=other_params&gv6=&gp7=jsp_agent&gv7=https%3A%2F%2Ferpptp1.fin.yahoo.com%3A8000%2FOA_HTML%2F&gp13=dbc&gv13=ERPPTP1&fsst=38084434078760319795969931263460230597)",
				0);
		{
			web.window(23,
					"/web:window[@index='1' or @title='Oracle E-Business Suite R12']")
					.waitForPage(null);
		}
		endStep();
		beginStep("[7] Submit a New Request", 0);
		{
			forms.captureScreenshot(27);
			{
				think(15.718);
			}
			forms.button(28, "//forms:button[(@name='WHAT_TYPE_OK_0')]")
					.click();
		}
		endStep();
		beginStep("[8] Submit Request", 0);
		{
			forms.captureScreenshot(30);
			{
				think(7.418);
			}
			forms.window(31, "//forms:window[(@name='WORK_ORDER')]").close();
		}
		endStep();
		beginStep("[9] Submit a New Request", 0);
		{
			forms.captureScreenshot(33);
			{
				think(3.682);
			}
			forms.button(34, "//forms:button[(@name='WHAT_TYPE_CANCEL_0')]")
					.click();
			{
				think(0.34);
			}
			forms.window(35, "//forms:window[(@name='NAVIGATOR')]").activate(
					true);
		}
		endStep();
		beginStep("[10] Navigator - System Administrator", 0);
		{
			forms.captureScreenshot(37);
		}
		endStep();

	}

	public void finish() throws Exception {
	}
}

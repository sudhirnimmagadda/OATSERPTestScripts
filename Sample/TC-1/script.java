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
		beginStep("[1] Google (/)", 0);
		{
			web.window(2, "/web:window[@index='0' or @title='about:blank']")
					.navigate("http://www.google.com/");
			web.window(4, "/web:window[@index='0' or @title='Google']")
					.waitForPage(null);
			{
				think(2.454);
			}
			web.textBox(
					5,
					"/web:window[@index='0' or @title='Google']/web:document[@index='0']/web:form[@name='f' or @index='0']/web:input_text[@name='q' or @index='0']")
					.setText("open script oracle");
			{
				think(0.328);
			}
			web.textBox(
					6,
					"/web:window[@index='0' or @title='Google']/web:document[@index='0']/web:form[@name='f' or @index='0']/web:input_text[@name='q' or @index='0']")
					.pressEnter();
		}
		endStep();
		beginStep("[2] open script oracle - Google Search (/search)", 0);
		{
			web.window(7,
					"/web:window[@index='0' or @title='open script oracle - Google Search']")
					.waitForPage(null);
			{
				think(12.313);
			}
			web.element(
					8,
					"/web:window[@index='0' or @title='open script oracle - Google Search']/web:document[@index='0']/web:td[@id='rhs_block' or @index='37']")
					.click();
			{
				think(6.406);
			}
			web.link(
					9,
					"/web:window[@index='0' or @title='open script oracle - Google Search']/web:document[@index='0']/web:a[@text='More items...' or @href='https://www.google.com/url?url=https://docs.oracle.com/cd/E25294_01/doc.920/e15487/chap3_os_fm.htm&rct=j&frm=1&q=&esrc=s&sa=U&ved=0ahUKEwj4t-OvwOvVAhXkrFQKHcunDD4QMAgbMAE&usg=AFQjCNH-TlU9UaI95-v_sdK_u74pa_JA-g' or @index='40']")
					.click();
		}
		endStep();
		beginStep(
				"[3] https://www.google.com/url?url=https://docs.oracle.com/cd/E25294_01/doc.920/e15487/chap3_os_fm.htm&rct=j&frm=1&q=&esrc=s&sa=U&ved=0ahUKEwj4t-OvwOvVAhXkrFQKHcunDD4QMAgbMAE&usg=AFQjCNH-TlU9UaI95-v_sdK_u74pa_JA-g (/chap3_os_fm.htm&rct=j&frm=1&q=&esrc=s&sa=U&ved=0ahUKEwj4t-OvwOvVAhXkrFQKHcunDD4QMAgbMAE&usg=AFQjCNH-TlU9UaI95-v_sdK_u74pa_JA-g)",
				0);
		{
			web.window(10,
					"/web:window[@index='0' or @title='Oracle OpenScript Tutorial']")
					.waitForPage(null);
		}
		endStep();

	}

	public void finish() throws Exception {
	}
}

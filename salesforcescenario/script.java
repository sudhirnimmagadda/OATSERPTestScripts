import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	
	public void initialize() throws Exception {
		browser.launch();
	}
		
	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {
		beginStep("[1] Login | Salesforce (/test.salesforce.com/)", 0);
		{
			web.window(2, "/web:window[@index='0' or @title='about:blank']")
					.navigate("https://test.salesforce.com/");
			web.window(4,
					"/web:window[@index='0' or @title='Login | Salesforce']")
					.waitForPage(null);
			{
				think(3.687);
			}
			web.textBox("/web:window[@index='0' or @title='Login | Salesforce']/web:document[@index='0']/web:form[@id='login_form' or @name='login' or @index='0']/web:input_text[@id='username' or @name='username' or @index='0']")
					.setText("admin@ahatis.com.pnctest");
			
			web.textBox("/web:window[@index='0' or @title='Login | Salesforce']/web:document[@index='0']/web:form[@id='login_form' or @name='login' or @index='0']/web:input_password[@id='password' or @name='pw' or @index='0']")
					.click();
			
			web.textBox("/web:window[@index='0' or @title='Login | Salesforce']/web:document[@index='0']/web:form[@id='login_form' or @name='login' or @index='0']/web:input_password[@id='password' or @name='pw' or @index='0']")
					.setPassword(deobfuscate("+5qdZ7MmtKT7EnOPuuhZVg=="));
			
			web.button("/web:window[@index='0' or @title='Login | Salesforce']/web:document[@index='0']/web:form[@id='login_form' or @name='login' or @index='0']/web:input_submit[@id='Login' or @name='Login' or @value='Log In to Sandbox' or @index='0']")
					.click();
		
			web.window("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']").waitForPage(null);
			
			web.link("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']/web:document[@index='0' or @name='umps|CS3$00DQ000000EeLQg_005Q000000PpndU_02_3b2bc042-df89-4229-b344-d19a5138eee6|4|']/web:a[@text='Trade Promotion Management' or @index='20']")
					.click();
		
			web.window("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']").waitForPage(null);
			
			think(10);
			
			web.textBox("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']/web:document[@index='0' or @name='umps|CS3$00DQ000000EeLQg_005Q000000PpndU_02_3b2bc042-df89-4229-b344-d19a5138eee6|4|']/web:form[@id='thePage:tpmform' or @name='thePage:tpmform' or @index='2']/web:input_text[@id='thePage:tpmform:searchblock:j_id26:j_id27' or @name='thePage:tpmform:searchblock:j_id26:j_id27' or @index='1']")
					.click();
			
			web.image("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']/web:document[@index='0' or @name='umps|CS3$00DQ000000EeLQg_005Q000000PpndU_02_3b2bc042-df89-4229-b344-d19a5138eee6|4|']/web:img[@alt='Account Lookup (New Window)' or @index='24' or @src='https://cttpm2012.cs3.visual.force.com/img/s.gif']")
					.click();
		
			web.window("/web:window[@index='1' or @title='Search ~ Salesforce - Enterprise Edition']").waitForPage(null);
			
			web.textBox("/web:window[@index='1' or @title='Search ~ Salesforce - Enterprise Edition']/web:document[@index='1' or @name='searchFrame']/web:form[@id='theForm' or @name='theForm' or @index='0']/web:input_text[@id='lksrch' or @name='lksrch' or @index='0']")
					.click();
			
			web.textBox("/web:window[@index='1' or @title='Search ~ Salesforce - Enterprise Edition']/web:document[@index='1' or @name='searchFrame']/web:form[@id='theForm' or @name='theForm' or @index='0']/web:input_text[@id='lksrch' or @name='lksrch' or @index='0']")
					.setText("kroger");
			
			web.button("/web:window[@index='1' or @title='Search ~ Salesforce - Enterprise Edition']/web:document[@index='1' or @name='searchFrame']/web:form[@id='theForm' or @name='theForm' or @index='0']/web:input_submit[@name='go' or @value=' Go! ' or @index='0']")
					.click();
		
			web.window("/web:window[@index='1' or @title='Search ~ Salesforce - Enterprise Edition']").waitForPage(null);
			
			web.link("/web:window[@index='1' or @title='Search ~ Salesforce - Enterprise Edition']/web:document[@index='2' or @name='resultsFrame']/web:a[@text='Kroger' or @href='https://cttpm2012.cs3.visual.force.com/_ui/common/data/LookupResultsFrame?lkfm=thePage%3Atpmform&lknm=thePage%3Atpmform%3Asearchblock%3Aj_id26%3Aj_id27&lktp=001&lksrch=kroger&go=+Go%21+#' or @index='1']")
					.click();
			
			web.image("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']/web:document[@index='0' or @name='umps|CS3$00DQ000000EeLQg_005Q000000PpndU_02_3b2bc042-df89-4229-b344-d19a5138eee6|4|']/web:img[@id='thePage:tpmform:searchblock:j_id26:j_id28:btnplans' or @index='25' or @src='https://cttpm2012.cs3.visual.force.com/resource/1399377317000/cttpm2012__btn_plans']")
					.click();
		
			web.window("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']").waitForPage(null);
			
			web.link("/web:window[@index='0' or @title='Salesforce - Enterprise Edition']/web:document[@index='0' or @name='umps|CS3$00DQ000000EeLQg_005Q000000PpndU_02_3b2bc042-df89-4229-b344-d19a5138eee6|4|']/web:a[@text='View' or @href='https://cttpm2012.cs3.visual.force.com/apex/TPM?id=001F0000018pDHtIAM#' or @index='65']")
					.click();
		
			web.window("/web:window[@index='1' or @title='Business Plan: FY2017 Kroger ~ Salesforce - Enterprise Edition']").waitForPage(null);
		}
		endStep();

	}
	
	public void finish() throws Exception {
	}
}

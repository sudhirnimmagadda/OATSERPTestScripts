import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;



import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.basic.api.exceptions.AbstractScriptException;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import oracle.oats.scripting.modules.webdom.api.elements.DOMButton;
import oracle.oats.scripting.modules.webdom.api.elements.DOMCheckbox;
import oracle.oats.scripting.modules.webdom.api.elements.DOMElement;
import oracle.oats.scripting.modules.webdom.api.elements.DOMLink;
import oracle.oats.scripting.modules.webdom.api.elements.DOMRadioButton;
import oracle.oats.scripting.modules.webdom.api.elements.DOMSelect;
import oracle.oats.scripting.modules.webdom.api.elements.DOMTable;
import oracle.oats.scripting.modules.webdom.api.elements.DOMText;
import oracle.oats.scripting.modules.formsFT.api.*;

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.datatable.api.DataTableService datatable;
	@ScriptService oracle.oats.scripting.modules.applet.api.AppletService applet;
	@ScriptService oracle.oats.scripting.modules.formsFT.api.FormsService forms;
	
	public void initialize() throws Exception {
	}

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {

	}

	public FileInputStream getConfigFile(String filePath) throws FileNotFoundException {

		FileInputStream configFile = null;

		File propertyFile = new File(filePath);
		System.out.println("Loading " + filePath + " from " + propertyFile.getAbsolutePath());
		if (propertyFile.exists()) {
			configFile = new FileInputStream(propertyFile);
		}

		if (configFile == null) {
			throw new RuntimeException("Cannot find  config file: " + propertyFile);
		}
		return configFile;
	}

	public Properties ReadValuesFromTextFile(FileInputStream file) throws IOException {

		Properties properties = new Properties();
		properties.load(file);
		return properties;
	}

	public void navigate_Environment(String env) throws Exception {
		    browser.launch();
		   
			web.window("/web:window[@index='0' or @title='about:blank']").navigate(env);
			{
				think(10.171);
			}
	}
			
	public void login_SNet(String userName,String password) throws  Exception
	{		
		DOMText  element_UserName=web.textBox(4,"/web:window[@index='0' or @title='Subarunet - Login']/web:document[@index='0']/web:form[@name='loginForm' or @index='0']/web:input_text[@id='username' or @name='username' or @index='0']");
		DOMText  element_Password=web.textBox(4,"/web:window[@index='0' or @title='Subarunet - Login']/web:document[@index='0']/web:form[@name='loginForm' or @index='0']/web:input_password[@name='password' or @index='0']");
		
		//Setting the value in 'UserName' TextBox
		element_UserName.clearText();
		element_UserName.setText(userName);
		element_UserName.pressTab();
		
		//Setting the value in 'Password' TextBox
		element_Password.clearText();
		element_Password.setText(password);
		
		//Clicking on Login Button
		web.button("/web:window[@index='0' or @title='Subarunet - Login']/web:document[@index='0']/web:form[@name='loginForm' or @index='0']/web:input_submit[@name='submit' or @value='Enter' or @index='0']").click();
	    
		//Verifying the login
		if (web.image("/web:window[@index='0' or @title='Welcome to Subarunet.com']/web:document[@index='0']/web:img[@src='https://snet-qa.subarunet.com/home/images/icons/home.png']").exists(50, TimeUnit.SECONDS))
				this.reportPassed("Successfully Logged in as : " + userName);
		else{
			this.fail("Unable to login with the User ID: " + userName);
	}
			
	}
	public void selectCarModels(String carLine,List<String> modelYear,List<String> modelOptions,List<String> extColor,String Spec) throws  Exception
	{
		waitTillPageLoad();
		Thread.sleep(2000);
		web.waitForObject("/web:window[@index='0' or @title='.*']/web:document[@index='1']/web:form[@name='landing' or @index='0']/web:select[(@index='0') and multiple mod 'False']", 10000);

		web.selectBox("/web:window[@index='0' or @title='.*']/web:document[@index='1']/web:form[@name='landing' or @index='0']/web:select[(@index='0') and multiple mod 'False']")
			.selectOptionByText(carLine);
		
		for (String year : modelYear) {
			web.checkBox("/web:window[@index='0' or @title='.*']/web:document[@index='1']/web:form[@name='landing' or @index='0']/web:input_checkbox[@id='"+year+"']").check(true);
		}

		web.button("/web:window[@index='0' or @title='.*']/web:document[@index='1']/web:form[@name='criteria' or @index='0']/web:button[@index='0']")
		.click();
		waitTillPageLoad();
		for (String option : modelOptions) {
			DOMCheckbox checkbox = (DOMCheckbox) web.element("//li[@text=' "+option+"*']").getElementsByTagName("input").get(0);
			checkbox.check(true);
		}
		
		for (String color : extColor) {
			DOMCheckbox checkbox = (DOMCheckbox) web.element("//li[@text=' "+color+"*']").getElementsByTagName("input").get(0);
			checkbox.check(true);
		}
		
		DOMCheckbox checkbox = (DOMCheckbox) web.element("//li[@text=' "+Spec+"*']").getElementsByTagName("input").get(0);
		checkbox.check(true);
		
		web.button("/web:window[@index='0' or @title='.*']/web:document[@index='1']/web:form[@name='criteria' or @index='0']/web:button[@index='0']")
			.click();
		waitTillPageLoad();
	}

	public void waitTillPageLoad() throws Exception{
		String browserReady = web.getFocusedWindow().getReadyState();
		while(!browserReady.equals("complete")){
			Thread.sleep(2000);
			browserReady = web.getFocusedWindow()
			.getReadyState();
		}
	}
	
	public void selectCheckBox(String label) throws Exception
	{	
	
			
		DOMCheckbox checkbox = (DOMCheckbox) web.element("//div[@text=' "+label+"*']").getElementsByTagName("input").get(0);
		checkbox.check(true);	
	}
	
	public void DeselectCheckBox(String label) throws Exception
	{
		DOMCheckbox checkbox = (DOMCheckbox) web.element("//div[@text=' "+label+"*']").getElementsByTagName("input").get(0);
		checkbox.check(false);	
	}
public void selectCheckBox_Web(String locator,boolean option) throws Exception


	{
		 try {
				web.checkBox("//web:input_checkbox[@id='"+locator+"' or @name='"+locator+"' or @value='"+locator+"']").check(option);
				reportPassed("Checked the checkbox : " +locator);

				waitTillPageLoad();
			} catch (Exception e) {
				reportFailure("Not able to select the checkbox " +locator);
				e.printStackTrace();
				throw e;
			}
	}
	public void Sync() throws Exception
	{
		delay(8000);

	}

	public void click(String locator) throws Exception
	{
		Sync();
		if(web.element("//web:a[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").exists(5, TimeUnit.SECONDS)){
		web.element("//web:a[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").focus();
		web.element("//web:a[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"']").click();
		info("Clicked on "+locator+" link");
		}
		else if(web.button("//web:button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.button("//web:button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").focus();
			web.button("//web:button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").click();
			info("Clicked on "+locator+" button");
		}
		else if(web.button("//web:input_button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.button("//web:input_button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").focus();
			web.button("//web:input_button[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").click();
			info("Clicked on "+locator+" button");


		}
		else if(web.button("//web:input_submit[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.button("//web:input_submit[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").focus();
			web.button("//web:input_submit[@id='"+locator+"' or @name='"+locator+"' or @title='"+locator+"' or @text='"+locator+"' or @value='"+locator+"']").click();
			info("Clicked on "+locator+" button");
		}
		
		else if(web.image("//web:img[@alt='"+locator+"' or @id='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.image("//web:img[@alt='"+locator+"' or @id='"+locator+"']").focus();
			web.image("//web:img[@alt='"+locator+"' or @id='"+locator+"']").click();
			info("Clicked on "+locator+" Image");
		}
		else if(web.element("//web:span[@text='"+locator+"' or @id='"+locator+"' or @value='"+locator+"']").exists(5, TimeUnit.SECONDS)){
			web.element("//web:span[@text='"+locator+"' or @id='"+locator+"' or @value='"+locator+"']").getParent().click();
			//web.element("//web:span[@text='"+locator+"' or @id='"+locator+"' or @value='"+locator+"']").click();
			info("Clicked on "+locator+" Tab");


		}
		waitTillPageLoad();

	}
	
	
	
	
	/**
	 * @Method Name : clickbutton
	 * @Description : Method to check existence of button and click
	 * @Author : Sudhir Nimmagadda
	 * @Param objectValue :        
	 * @throws : AbstractScriptException
	 */
	public void ClickButton(String label) throws Exception {
		
		DOMButton Button = (DOMButton) web.button("//input_submit[@id='"+label+"' or @name='"+label+"' or @value='"+label+"']");
		if (Button.exists(10, TimeUnit.SECONDS)){
			Button.click();	
		}
		else {
			DOMButton Button1 = (DOMButton) web.button("//input_button[@id='"+label+"' or @name='"+label+"' or @value='"+label+"']");
			if (Button1.exists(10, TimeUnit.SECONDS)){
				Button.click();	
			}
			
			else {
				reportFailure("Button doesnot exist");
				}
			}
	}
	
	/**
	 * @Method Name : clicklink
	 * @Description : Method to check existence of link and click
	 * @Author : Sudhir Nimmagadda
	 * @Param objectValue :        
	 * @throws : AbstractScriptException
	 */
	
	public void ClickLink(String label) throws Exception {
		
		DOMLink link = (DOMLink) web.link("//a[@text='"+label+"']");
			if (link.exists(15,TimeUnit.SECONDS)){
				link.getParent().click();	
				}
		else {
			reportFailure("Link doesnot exist");
			}
		}
	
		
	/**
	 * @Method Name : selectradiobutton
	 * @Description : Method to check existence of radio button and select
	 * @Author : Sudhir Nimmagadda
	 * @Param objectValue :        
	 * @throws : AbstractScriptException
	 */
	
	public void selectRadiobtn(String label) throws Exception {
		
		DOMRadioButton RadioBtn = (DOMRadioButton) web.radioButton("//input_radio[@id='"+label+"' or @name='"+label+"']");
		if (RadioBtn.exists(5, TimeUnit.SECONDS)){
		RadioBtn.select();	
		}
		else {
			reportFailure("Radio button doesnot exist");
		}
			}
	
	
	
	public void Dropdown(String label,int num) throws Exception {
		
		DOMSelect select = (DOMSelect) web.selectBox("//select[@id='"+label+"' or @name='"+label+"']");
			if (select.exists(15,TimeUnit.SECONDS)){
				select.selectOptionByIndex(num);	
				}
		else {
			reportFailure("Dropdown doesnot exist");
			}
		}
	
		
public void selectbox(String label,String value) throws Exception {
		
		DOMSelect select = (DOMSelect) web.selectBox("//select[@id='"+label+"' or @name='"+label+"']");
			if (select.exists(15,TimeUnit.SECONDS)){
				select.selectOptionByText(value);	
				}
		else {
			reportFailure("selectbox doesnot exist");
				}
		}
	

/**
 * @Method Name : settextbox
 * @Description : Method to enter data to textbox
 * @Author : Sudhir Nimmagadda
 * @Param objectValue :        
 * @throws : AbstractScriptException
 */

public void settextbox(String label,String value) throws Exception {
	
	DOMText settext = (DOMText) web.textBox("//input_text[@id='"+label+"' or @name='"+label+"']");
		if (settext.exists(15,TimeUnit.SECONDS)){
			settext.click();
			settext.setText(value);
			}
	else {
		reportFailure("textbox doesnot exist");
		}
	}
	public void setText_Web(String locator,String value) throws AbstractScriptException
		{
		    try {
				web.textBox("//web:input_text[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").focus();
				web.textBox("//web:input_text[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").setText(value);
				web.textBox("//web:input_text[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").pressTab();
				waitTillPageLoad();
				reportPassed("Sucessfully entered the value");
			} catch (Exception e) {
				reportFailure("Failed to enter the value in the textbox");
				e.printStackTrace();
			}
		}
		public void setTextArea_Web(String locator,String value) throws AbstractScriptException
		{
		    try {
				web.textArea("//web:textarea[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").focus();
				web.textArea("//web:textarea[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").setText(value);
				web.textArea("//web:textarea[@id='"+locator+"' or @text='"+locator+"' or @name='"+locator+"' or @title='"+locator+"']").pressTab();
				waitTillPageLoad();
				reportPassed("Sucessfully entered the value");
			} catch (Exception e) {
				reportFailure("Failed to enter the value in the textbox");
				e.printStackTrace();
			}
		}
public void selectRadioButton_Web(String locator) throws AbstractScriptException
		{
			 try {
			web.radioButton("//web:input_radio[@id='"+locator+"' or @name='"+locator+"']")
				.select();
			reportPassed("Selected the Radio button with the locator " +locator);
			waitTillPageLoad();
				} catch (Exception e) {
					reportFailure("Not able to select the Radio button with the locator " +locator);
					e.printStackTrace();
				}
		}
		public void selectValueComboBox_Web(String locator,String option) throws Exception
		{
			 try {
			web.selectBox("//web:select[(@id='"+locator+"' or @name='"+locator+"') and multiple mod 'False']").selectOptionByText(option);
			reportPassed("Selected the value from the  " +locator);
			waitTillPageLoad();
				} catch (Exception e) {
					reportFailure("Not able to select the value " +option);
					e.printStackTrace();
					throw e;
				}
		}
/**
 * @Method Name : gettextboxdata
 * @Description : Method to capture data from textbox
 * @Author : Sudhir Nimmagadda
 * @Param objectValue :        
 * @throws : AbstractScriptException
 */
public String gettextboxdata(String label) throws Exception {
	
	DOMText settext = (DOMText) web.textBox("//input_text[@id='"+label+"' or @name='"+label+"']");
		if (settext.exists(15,TimeUnit.SECONDS)){
			String textboxdata=settext.getAttribute("value");
			return textboxdata;
			}
	else {
		reportFailure("textbox doesnot exist");
		return null;
		}
	}



/**
 * @Method Name : gettabledata
 * @Description : Method to capture data from table
 * @Author : Sudhir Nimmagadda
 * @Param objectValue :        
 * @throws : AbstractScriptException
 */

public String gettabledata(String prop,int row,int col) throws Exception

	{
	DOMTable table =web.table("//web:table[@id='"+prop+"']");
	if (table.exists(5, TimeUnit.SECONDS)){
		int rowcount=table.getRowCount();
		int colcount=table.getColumnCount();
		String celldata=table.getCell(row,col);
		
		return celldata;
		
		}	
	else {
		reportFailure("no data exists ");
		return null;
			}
		}

		
		/**
	 * @Method Name : getwebdata
	 * @Description : Method to capture data from element
	 * @Author : Sudhir Nimmagadda
	 * @Param objectValue :        
	 * @throws : AbstractScriptException
	 */
	
	public String getwebdata(String locator) throws Exception
		{
		DOMElement webdata= (DOMElement) web.element("//web:div[@id='"+locator+"' or @name='"+locator+"' or @text='"+locator+"']");
		if (webdata.exists(15,TimeUnit.SECONDS)){
			String capturedata=webdata.getAttribute("text");
			return capturedata;
			}
	else {
		DOMElement webdata1= (DOMElement) web.element("//web:span[@id='"+locator+"' or @name='"+locator+"' or @text='"+locator+"']");
		if (webdata1.exists(15,TimeUnit.SECONDS)){
			String capturedata1=webdata1.getAttribute("text");
			return capturedata1;
		}
		
		else {
			reportFailure("Element doesnot exist");
			}
	}
		return null;
		}	
	
	/**
	 * @Method Name : randomString
	 * @Description : Method to get the random string
	 * @Author : Jagan Gottimukkala
	 * @Param objectValue :        
	 * @throws : AbstractScriptException
	 * @Last Modified/Updated by: Rajendra Chary
	 */
	public String randomString(String length) throws Exception
	{
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength;
	    	targetStringLength = Integer.parseInt(length);	  
	    
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (new Random().nextFloat() * (rightLimit - leftLimit));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    return generatedString;
	}
	/**
	 * @Method Name : captureScreenShot
	 * @Description : Method to capture active screen shot
	 * @Author : Jagan Gottimukkala
	 * @Param dateChange :
	 *            dateChange indicates expected date format.
	 * @Throws Exception 
	 * @Last Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	public String captureScreenShot() throws Exception{
		
		String scriptPath = getScriptPackage().getScriptPath();            
        String resultFolder = scriptPath.substring(0, scriptPath.lastIndexOf("\\"))+"\\results";
        String screenPrints = GetRecentfold(resultFolder)+"\\ScreenShots";
        createFolder(screenPrints);
        DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_mm");
		String uniqueDateTime  = df.format(new Date());
		String imagePath = screenPrints+"\\"+uniqueDateTime + ".jpg";

		//Capture the Screen Shot
		ft.getScreenCapture(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height,imagePath);		
		return imagePath;
	}
	
	/**
	 * @Method Name : GetRecentfold
	 * @Description : Method to get the recently created folder name in the given directory
	 * @Author : Jagan Gottimukkala
	 * @Param dateChange :
	 *            dateChange indicates expected date format.
	 * @Throws Exception 
	 * @Last Modified/Updated by: Vamshi Krishna Chigullapally
	 */
 public String GetRecentfold(String directoryPath) throws FileNotFoundException{
         
         File dir = new File(directoryPath);
         File[] files = dir.listFiles();
         String foldnam = directoryPath;
         
         if (files.length == 0) {
             return null;
         } 

         File lastModifiedFile = files[0];
         
         for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
                foldnam = files[i].getPath();
               }
         }
         return foldnam;
   }  
 
 /**
	 * @Method Name : createFolder
	 * @Description : Method to create a folder
	 * @Author : Jagan Gottimukkala
	 * @Param dateChange :
	 *            dateChange indicates expected date format.
	 * @Throws Exception 
	 * @Last Modified/Updated by: Vamshi Krishna Chigullapally
	 */
	 public void createFolder(String folderName) throws FileNotFoundException{
      File theDir = new File(folderName);
      // if the directory does not exist, create it
        if (!theDir.exists()) {
              new File(folderName).mkdirs();
       }
}
	 
	 public String getDateTimeFormat(String data) throws Exception {
			info("insside getDateTimeFormat");
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DATE);
			int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
			int hour =cal.get(Calendar.HOUR_OF_DAY);
			int minute =cal.get(Calendar.MINUTE);
			int second =cal.get(Calendar.SECOND);
			cal.set(year,month,day);
			Date d=new Date(cal.getTimeInMillis());
			String month_name=new SimpleDateFormat("MMM").format(d);
			String Year_name=new SimpleDateFormat("yy").format(d);



			String outputDateFormat = "";

			String format1 = "dd-mm-yyyy,0";
			String format2 = "dd-mmm-yyyy,0";
			String format3 = "dd-mmm-yyyy,2";
			String format4 = "mm/dd/yyyy,0";
			String format5 = "MMM-yy,0";
			String format6 = "dd-MMM-yy,0";
			String format7 =  "lastdayofmonth";


			if (data.equals("format1")){
				outputDateFormat=day+ "-" + (month +1 ) + "-" + year;
			}
			if (data.equals("format2")){
				outputDateFormat=day + "-" + month_name + "-" + year;
				info(outputDateFormat);
			}

			if (data.equals("format4")){
				outputDateFormat=(month +1 ) + "/" + day + "/" + year;
				info(outputDateFormat);
			}

			if (data.equals("format5")){
				outputDateFormat= month_name + "-" + Year_name;
				info(outputDateFormat);
			}

			if (data.equals("format6")){
				outputDateFormat= day + "-" +month_name + "-" + Year_name;
				info(outputDateFormat);
			}


			if (data.equals("format7")){

				Calendar calendar = Calendar.getInstance();
				int lastDate = calendar.getActualMaximum(Calendar.DATE);
				System.out.println("Date     : " + calendar.getTime());
				System.out.println("Last Date: " + lastDate);
				outputDateFormat= lastDate + "-" +month_name + "-" + Year_name;
				info(outputDateFormat);
			}
			
					


			cal.add(Calendar.DATE,2);
			Date da=new Date(cal.getTimeInMillis());
			String monthnm=new SimpleDateFormat("MMM").format(da);

			if (data.equals("format3")){
				outputDateFormat=cal.get(Calendar.DATE) + "-" + monthnm + "-" + cal.get(Calendar.YEAR);
				info(outputDateFormat);
				System.out.println("Date Format1: "+ day + "-" + (month +1 ) + "-" + year);
			}
			
			
			cal.add(Calendar.DATE,-30);
			Date pdate=new Date(cal.getTimeInMillis());
			monthnm=new SimpleDateFormat("MMM").format(da);
			
			if (data.equals("format8")){
				outputDateFormat=(month) + "/" + cal.get(Calendar.DATE) + "/" + year;;
				info(outputDateFormat);
				System.out.println("Date Format1: "+ day + "-" + (month +1 ) + "-" + year);
			}
			
			return outputDateFormat;
		}
	 public void writeResult(Boolean result,String resultFilePath,String scriptRowNo,String scriptResultPath) throws Exception
     {
           datatable.importExcel(resultFilePath, true);
           
           String scriptResult = null;
           
           if(result == true)
           {
                 scriptResult = "Pass";
           }
           else if(result == false)
           {
                 scriptResult = "Fail";
           }
           datatable.setValue(Integer.parseInt(scriptRowNo) , "Result", scriptResult);
           datatable.setValue(Integer.parseInt(scriptRowNo) , "Result Path", "=HYPERLINK(\""+GetRecentfold( scriptResultPath )+"\\"+"BasicReport.htm\",\"Clik for Result File\")" );
           datatable.exportToExcel(resultFilePath );
           
           
     }
	public void finish() throws Exception {
	}
}

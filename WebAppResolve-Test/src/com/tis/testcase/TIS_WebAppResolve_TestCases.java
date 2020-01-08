package com.tis.testcase;

import com.pageobjectmodel.pages.TIS_eFLOW;

import SapAppmanager.ApplicationManager;
import SapDocuments.PurchaseOrder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjectmodel.pages.Login;

public class TIS_WebAppResolve_TestCases extends Init {
			

	static Login l = new Login(driver);
	static TIS_eFLOW eFLOW = new TIS_eFLOW(driver);
	static String url = input.ReadGlobal("URL");
	static String mailURL = input.ReadGlobal("MailURL");
	static String unLogin = input.ReadGlobal("UserName");
	static String pwLogin = input.ReadGlobal("Password");
	
	static //Start application in FQA
    ApplicationManager appSAPMain = new ApplicationManager("FQA.bat");
	
    
    

	public static void TC_01_TIS_WebAppResolve_Login() throws InterruptedException {
		/*
		  String userName = * input.Read(tc.TestCaseID, "UserName", 1); 
		 */
		result.print("Verification of TIS Login");
		Init.OpenUrl(url);
		l.login(unLogin, pwLogin);
		l.logout();
	}
	
	public static void TC_01_TIS_WebAppResolve_Login_Invalid_credentials() throws InterruptedException {
		/*
		  String userName = * input.Read(tc.TestCaseID, "UserName", 1); 
		 */
		result.print("Verification of TIS WebApp resolve Login with invalid credentials");
		Init.OpenUrl(url);
		l.login_invalidCredentials(unLogin, pwLogin);
		//l.logout();
	}
	
	
	public static void TC_02_TIS_WebAppResolve_Approve() throws InterruptedException {
		result.print("Verification of Workflow Approve");
		
		String eflowID = input.Read(tc.TestCaseID, "eFLOW_ID", 1); 
		
		Init.OpenUrl(url);
		l.login(unLogin, pwLogin);
		eFLOW.approveWorkFlow(eflowID);
		l.logout();		
		
	}
	
	public static void TC_03_TIS_WebAppResolve_Reject() throws InterruptedException {
		result.print("Verification of Workflow Reject");
		String eflowID = input.Read(tc.TestCaseID, "eFLOW_ID", 1); 
		
		Init.OpenUrl(url);
		l.login(unLogin, pwLogin);
		eFLOW.rejectWorkFlow(eflowID);
		l.logout();		
		
	}
	
	public static void TC_04_TIS_WebAppResolve_Forward() throws InterruptedException {
		result.print("Verification of Forward Workflow");
		
		String eflowID = input.Read(tc.TestCaseID, "eFLOW_ID", 1); 
		String recipientID = input.Read(tc.TestCaseID, "recipient_ID", 1);
		Init.OpenUrl(url);
		l.login(unLogin, pwLogin);
		//invoice.approveWorkFlow(eflowID);
		eFLOW.forwardWorkFlow(eflowID,recipientID);
		l.logout();
		
	}
	
	public static void TC_05_TIS_WebAppResolve_Inquiry() throws InterruptedException {
		result.print("Verification of Workflow Inquiry");
		
		String eflowID = input.Read(tc.TestCaseID, "eFLOW_ID", 1); 
		String recipientID = input.Read(tc.TestCaseID, "recipient_ID", 1);
		Init.OpenUrl(url);
		l.login(unLogin, pwLogin);
		eFLOW.sendInquiry(eflowID,recipientID);
		l.logout();
		//l.cleanup();
		
	}
	
	public static void TC_06_TIS_WebAppResolve_Email() throws InterruptedException {
		result.print("Verification of Workflow email notification");
		
		//String eflowID = input.Read(tc.TestCaseID, "eFLOW_ID", 1); 
		String UserName = input.Read(tc.TestCaseID, "mUserName", 1);
		String pwd = input.Read(tc.TestCaseID, "mpwd", 1);
		
		Init.OpenUrl(mailURL);
		eFLOW.emailNotification(UserName, pwd);
		
		/*
		l.login(unLogin, pwLogin);
		invoice.forwardWorkFlow(eflowID,recipientID);
		l.logout();
		*/
		
	}
	
	public static void TC_07_TIS_WebAppResolve_Substitution() throws InterruptedException {
		result.print("Verification of substitute user");
		
		String UserID = input.Read(tc.TestCaseID, "UserID", 1); 
		String SubUserID = input.Read(tc.TestCaseID, "Substitute", 1);
		Init.OpenUrl(url);
		l.login(unLogin, pwLogin);
		eFLOW.substitution_eFLOW(UserID, SubUserID);
		l.logout();
		//l.cleanup();
		
	}
	
	public static void TC_08_TIS_WebAppResolve_ChangePassword() throws InterruptedException {
		result.print("Verification of ChangePassword");
		
		String OldPassword = input.Read(tc.TestCaseID, "UserID", 1); 
		String NewPassword = input.Read(tc.TestCaseID, "Substitute", 1);
		Init.OpenUrl(url);
		l.login(unLogin, pwLogin);
		eFLOW.ChangePassword_eFLOW(OldPassword, NewPassword);
		l.logout();
		//l.cleanup();
		
	}
       
	@Test(dataProvider = "validPOFromcsv")
	public static void TC_09_TIS_WebAppResolve_IntegrationSapTest(PurchaseOrder docSAP) throws InterruptedException {
		
		
		//start SAP
        appSAPMain.init();
        
      //Login
        appSAPMain.getSession().login("main");
        
        docSAP.createSapDoc();
    
        appSAPMain.stop();
        
        
		
		Init.OpenUrl(url);	

	}	
	
	@Test(dataProvider = "validPOFromcsv")
    public static void integrationTestSap(PurchaseOrder docSAP) {

        //start SAP
        appSAPMain.init();
        
        //Login
        appSAPMain.getSession().login("main");

        //Create SAP doc
        
        docSAP.createSapDoc();
        docSAP.startWorkflow();
        
       appSAPMain.stop();
       
       
}
}
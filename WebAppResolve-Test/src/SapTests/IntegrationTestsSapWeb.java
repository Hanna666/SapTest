package SapTests;

import org.openqa.jetty.html.Input;
import com.pageobjectmodel.pages.TIS_eFLOW;
import com.tis.testcase.Init;
import com.pageobjectmodel.pages.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

import SapAppmanager.ApplicationManager;
import SapDocuments.GoodsReceipt;
import SapDocuments.SapDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pageobjectmodel.pages.TIS_eFLOW;
import com.pageobjectmodel.pages.Login;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import com.pageobjectmodel.pages.TIS_eFLOW;

public class IntegrationTestsSapWeb extends Init {
	
	static Login l = new Login(driver);
	static TIS_eFLOW eFLOW = new TIS_eFLOW(driver);
	static String url = input.ReadGlobal("URL");
	static String mailURL = input.ReadGlobal("MailURL");
	static String unLogin = input.ReadGlobal("UserName");
	static String pwLogin = input.ReadGlobal("Password");

    //Start application in TDV
    ApplicationManager appSAPMain = new ApplicationManager("TDV.bat");

    


    @Test
    public void integrationTestSap() {

        //start SAP
        appSAPMain.init();
        
        //Login
        appSAPMain.getSession().login("main");


        //Create SAP doc in se37 (/TISA/RFC_OCR_CREATE) and remember his number
        SapDocument docSAP = new SapDocument(appSAPMain);
        /*  
        docSAP.createSapDoc();
        
        //Open "n/TISA/C"
        docSAP.openControl();
              
        //start Workflow for created Sap doc, by doc number
        docSAP.startWorkflow();           
              
       	  /*
       	  1. Open WebApp;
          2. Approve document and verify that it's has been approved; 		
          3. Close WebApp
           
       				SapDocument eFlowID = docSAP;
       				Init.OpenUrl(url);
       				l.login(unLogin, pwLogin);
       				eFLOW.approveWorkFlowToo(eFlowID);
       				l.logout();	
      	*/			
      	//Open "n/TISA/C"
          docSAP.openControl();
       		        
        //Find created Sap doc
       	  docSAP.findSapDoc();
       	  
       	//Check, doc have status approved
       	  docSAP.verifyThatDocApproved();

       
       
       
        
       
    }
    
}

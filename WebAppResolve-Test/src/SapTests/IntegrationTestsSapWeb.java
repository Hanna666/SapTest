package SapTests;

import org.openqa.jetty.html.Input;
import com.pageobjectmodel.pages.TIS_eFLOW;
import com.tis.testcase.Init;
import com.pageobjectmodel.pages.Login;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SapAppmanager.ApplicationManager;
import SapDocuments.GoodsReceipt;
import SapDocuments.PurchaseOrder;

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

public class IntegrationTestsSapWeb {

    //Start application in FQA
    ApplicationManager appSAPMain = new ApplicationManager("TDV.bat");

    @DataProvider
    public Iterator<Object[]> validPOFromcsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("resources/PO_data.csv")))) {

            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                list.add(new Object[]{new PurchaseOrder(appSAPMain).fillData(split)});
                line = reader.readLine();
            }
        }
        return list.iterator();
    }


    @Test(dataProvider = "validPOFromcsv")
    public void integrationTestSap(PurchaseOrder docSAP) {

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

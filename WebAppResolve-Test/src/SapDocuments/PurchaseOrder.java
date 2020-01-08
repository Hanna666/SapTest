package SapDocuments;

import java.util.SplittableRandom;

import SapAppmanager.ApplicationManager;
import SapAppmanager.HelperBase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.tis.testcase.Init;

public class PurchaseOrder extends HelperBase {
	
    private String sapDocNum;
    private String docNumber;
    private String fiDocNumber;

    private String docType;
    private String generalCurrency;
    private String vendor;
    private String termsOfPayment;
    private String compCode;
    private String purchGroup;
    private String purchOrg;
    private String itemMaterial;
    private String itemQuantity;
    private String itemPO_Unit;
    private String itemNetPrice;
    private String itemPlant;
    private String itemStore;

    private String headerTablePOTAB1;
    private String headerTablePOTAB2;
    private String headerTableSAPPO;

    private String itemsTablePO;
    private String itemsTableSAPPO;

    private String itemDetailConfirmationTab;

    private String fieldForPurchOrg;

    public PurchaseOrder(ApplicationManager app) {
        super(app);

        headerTablePOTAB1 = "wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/tabsG_0900_TABSTRIP/tabpG_0900_TS_TAB1/ssubG_0900_TS_TAB1_SUB:" +
                "/EBY/SAPLPDPO_VIEW_SCREENS:0101/ctxt/EBY/PDPO_SHDR_IF-";
        headerTablePOTAB2 = "wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/tabsG_0900_TABSTRIP/tabpG_0900_TS_TAB2/ssubG_0900_TS_TAB2_SUB:" +
                "/EBY/SAPLPDPO_VIEW_SCREENS:0102/ctxt/EBY/PDPO_SHDR_IF-";
        itemsTablePO = "wnd[0]/shellcont[1]/shell";

        headerTableSAPPO = "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102" +
                "/tabsHEADER_DETAIL/tabpTABHDT10/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1221/ctxtMEPO1222-";
        itemsTableSAPPO = "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB2:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1211/tblSAPLMEGUITC_1211/";

        itemDetailConfirmationTab = "wnd[0]/usr/subSUB0:SAPLMEGUI:0020/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/" +
                "subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL/tabpTABIDT18/";

        fieldForPurchOrg = "wnd[0]/usr/subSUB0:SAPLMEGUI:0020/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/" +
                "tabsHEADER_DETAIL/tabpTABHDT10/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1221/ctxtMEPO1222-EKGRP";
    }

    public PurchaseOrder fillData(String[] split) {
        docType = split[0];
        generalCurrency = split[1];
        vendor = split[2];
        termsOfPayment = split[3];
        compCode = split[4];
        purchGroup = split[5];
        purchOrg = split[6];
        itemMaterial = split[7];
        itemQuantity = split[8];
        itemPO_Unit = split[9];
        itemNetPrice = split[10];
        itemPlant = split[11];
        itemStore = split[12];

        return this;
    }

    public PurchaseOrder withDocType() {
        type(headerTablePOTAB1+"DOC_TYPE", docType);
        return this;
    }

    public PurchaseOrder withVendor() {
        type(headerTablePOTAB1+"VENDOR", vendor);
        return this;
    }

    public PurchaseOrder withTermsOfPayment() {
        type(headerTablePOTAB1+"PMNTTRMS", termsOfPayment);
        return this;
    }

    public PurchaseOrder withGeneralCurrency() {
        type(headerTablePOTAB1+"CURRENCY", generalCurrency);
        return this;
    }

    public PurchaseOrder withPurchOrg() {
        type(headerTablePOTAB2+"PURCH_ORG", purchOrg);
        return this;
    }

    public PurchaseOrder withPurchGroup() {
        type(headerTablePOTAB2+"PUR_GROUP", purchGroup);
        return this;
    }

    public PurchaseOrder withCompCode() {
        type(headerTablePOTAB2+"COMP_CODE", compCode);
        return this;
    }

    public PurchaseOrder create() {
        makeAction("wnd[0]/tbar[1]/btn[5]", "press");
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");

        //Fiil header
        withDocType()
                .withGeneralCurrency()
                .withVendor()
                .withTermsOfPayment()
                .selectTab("2")
                .withCompCode()
                .withPurchGroup()
                .withPurchOrg()
                //Fill item
                .addLineItem()
                .fillCurrentItemMaterial(0)
                .fillCurrentItemQuantity(0)
                .fillCurrentItemPO_Unit(0)
                .fillCurrentItemNetPrice(0)
                .pressEnterInItem();

        return this;
    }
    
    
    public void createSapDoc () {
    	
    	//Start functional module
    	waitElement("wnd[0]/tbar[0]/okcd", 3000);
        type("wnd[0]/tbar[0]/okcd", "se37");
        makeAction("wnd[0]/tbar[0]/btn[0]", "press");
        makeAction("wnd[0]/tbar[1]/btn[8]", "press");
        type("wnd[0]/usr/ctxtRS38L-NAME", "/TISA/RFC_OCR_CREATE");
        
        //Create Doc
        makeAction("wnd[0]/tbar[1]/btn[8]", "press");
        makeAction("wnd[0]/tbar[1]/btn[42]", "press");
        makeAction("wnd[0]/usr/lbl[2,6]", "setFocus");
        makeAction("wnd[0]/tbar[1]/btn[2]", "press");
        makeAction("wnd[0]/tbar[1]/btn[8]", "press");
        sapDocNum = getFieldProperty("wnd[0]/usr/lbl[34,22]", "Text").toString();
       
 
        
    }
    
    public void pasteDoc () {
    	type("wnd[0]/tbar[0]/okcd", sapDocNum);
    	
    }
    
    public void startWorkflow () { 
    type ("wnd[0]/tbar[0]/okcd", "/n/TISA/C");
    makeAction("wnd[0]/tbar[0]/btn[0]", "press");
    setCurrentLineByDocNumber("wnd[0]/usr/subWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0011/subMAIN_SCA:/TISA/SAPLAP_DISPLAY:0150/tabsTS_WORKLIST/tabpWORKLIST_TAB1/ssubWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0151/cntlCC_WORKLIST_TAB1/shellcont/shell", "FW_DOCNO", sapDocNum);
    pressButton("wnd[0]/usr/subWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0011/subMAIN_SCA:/TISA/SAPLAP_DISPLAY:0150/tabsTS_WORKLIST/tabpWORKLIST_TAB1/ssubWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0151/cntlCC_WORKLIST_TAB1/shellcont/shell", "pressToolbarButton", "WF_START");
    setFieldProperty("wnd[0]/usr/subWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0011/subDIALOG_SCA:/TISA/SAPLAP_DISPLAY:0013/ssubDIALOG_SCA:/TISA/SAPLWF_DISPLAY_DIALOG:0010/cmbGO_FL_START->MC_WF_ID", "key", "WF_CHK_NOK");
    setFieldProperty("wnd[0]/usr/subWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0011/subDIALOG_SCA:/TISA/SAPLAP_DISPLAY:0013/ssubDIALOG_SCA:/TISA/SAPLWF_DISPLAY_DIALOG:0010/subTASK_SCA:/TISA/SAPLWF_DISPLAY_DIALOG:0040/tabsTS_TASK/tabpTS_TASK_1/ssubTS_TASK_SCA:/TISA/SAPLWF_DISPLAY_DIALOG:0030/tbl/TISA/SAPLWF_DISPLAY_DIALOGTC_REC/ctxt/TISA/SWF_REC_ENTRY-REC_ID[0,0]",
    		"text", "Zkhananueva");
    setFieldProperty("wnd[0]/usr/subWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0011/subDIALOG_SCA:/TISA/SAPLAP_DISPLAY:0013/ssubDIALOG_SCA:/TISA/SAPLWF_DISPLAY_DIALOG:0010/subNOTE_SCA:/TISA/SAPLFW_DISPLAY_DIALOG:0090/cntlCC_NOTE/shellcont/shell",
    		"text", "Test note");
    makeAction("wnd[0]/usr/subWORKLIST_SCA:/TISA/SAPLAP_DISPLAY:0011/subDIALOG_SCA:/TISA/SAPLAP_DISPLAY:0013/ssubDIALOG_SCA:/TISA/SAPLWF_DISPLAY_DIALOG:0010/btnBUTN_1", "press");
    }
	

	public PurchaseOrder save() {
        makeAction("wnd[0]/tbar[0]/btn[11]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

        docNumber = getFieldProperty("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/subG_0900_BASIC_SUB:" +
                "/EBY/SAPLPDVI_BASICSCREEN:0910/txt/EBY/PDVI_SBASIC_BO-NUMBR", "Text").toString();
        return this;
    }

    public PurchaseOrder selectTab(String value) {
        makeAction(String.format("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/tabsG_0900_TABSTRIP/tabpG_0900_TS_TAB%s", value), "select");
        return this;
    }

    public PurchaseOrder addLineItem() {
        pressButton("wnd[0]/shellcont[1]/shell", "pressToolbarButton", "ADDROW");
        return this;
    }

    public PurchaseOrder removeLineItem() {
        setSelectedRow("wnd[0]/shellcont[1]/shell", String.valueOf(Integer.parseInt(getFieldProperty("wnd[0]/shellcont[1]/shell", "RowCount").toString()) - 1));
        pressButton(itemsTablePO, "pressToolbarButton", "DELROW");
        return this;
    }

    public PurchaseOrder fillCurrentItemMaterial(int row) {
        modifyCell(itemsTablePO, row, "/EBY/MATERIAL", itemMaterial);
        return this;
    }

    public PurchaseOrder fillCurrentItemQuantity(int row) {
        modifyCell(itemsTablePO, row, "QUANTITY", itemQuantity);
        return this;
    }

    public PurchaseOrder fillCurrentItemNetPrice(int row) {
        modifyCell(itemsTablePO, row, "NET_PRICE", itemNetPrice);
        return this;
    }

    public PurchaseOrder fillCurrentItemPO_Unit(int row) {
        modifyCell(itemsTablePO, row, "PO_UNIT", itemPO_Unit);
        return this;
    }

    public PurchaseOrder pressEnterInItem() {
        setSelectedRow("wnd[0]/shellcont[1]/shell", "0");
        makeAction("wnd[0]/shellcont[1]/shell", "pressEnter");
        return this;
    }

    public void pressCheck() {
        makeAction("wnd[0]/tbar[1]/btn[26]", "press");
        makeAction("wnd[1]/usr/radGB_PO", "select");
        makeAction("wnd[1]/usr/radGB_PO", "setFocus");
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

    }

    public void post() {

        makeAction("wnd[0]/tbar[1]/btn[16]", "press");
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

        fiDocNumber = getFieldProperty(headerTablePOTAB1+"PO_NUMBER", "Text").toString();

    }

    public void postFromLine() {

        setCurrentLineByDocNumber("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_MAIN:0120/cntlCC_MAIN/shellcont/shell", "NUMBR", docNumber);
        makeAction("wnd[0]/tbar[1]/btn[16]", "press");
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

        fiDocNumber = getFieldProperty(headerTablePOTAB1+"PO_NUMBER", "Text").toString();

    }

    public void createGoodsReceipt() {
        //Press Create Goods Receipt
        makeAction("wnd[0]/tbar[1]/btn[17]", "press");
        //Enter number of PO to text in GR
        type("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/tabsG_0900_TABSTRIP/tabpG_0900_TS_TAB1/ssubG_0900_TS_TAB1_SUB:" +
                "/EBY/SAPLPDDN_DETAILSCREEN:0101/txt/EBY/PDDN_SHDR_IF-HEADER_TXT", docNumber);

    }

    public void createSAPPO() {

        //Start transaction
        type("wnd[0]/tbar[0]/okcd", "/nME21N");
        makeAction("wnd[0]/tbar[0]/btn[0]", "press");

        //fill header
        type("wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB0:SAPLMEGUI:0030/subSUB1:SAPLMEGUI:1105/ctxtMEPO_TOPLINE-SUPERFIELD", vendor);
        type(headerTableSAPPO+"EKORG", purchOrg);
        type(headerTableSAPPO+"BUKRS", compCode);

        //fill material items
        type(itemsTableSAPPO+"ctxtMEPO1211-EMATN[4,0]", itemMaterial);
        type(itemsTableSAPPO+"txtMEPO1211-MENGE[6,0]", itemQuantity);
        type(itemsTableSAPPO+"txtMEPO1211-NETPR[10,0]", itemNetPrice);
        type(itemsTableSAPPO+"ctxtMEPO1211-NAME1[15,0]", itemPlant);
        type(itemsTableSAPPO+"ctxtMEPO1211-LGOBE[16,0]", itemStore);
        type(itemsTableSAPPO+"txtMEPO1211-WAERS[11,0]", generalCurrency);

        //press to show Purch.Group
        makeAction(headerTableSAPPO+"EKORG", "setFocus");
        makeAction("wnd[0]/tbar[0]/btn[0]", "press");

        //waiting for purch.group
        waitElement(fieldForPurchOrg, 1500);

        //fill purch group
        type(fieldForPurchOrg, purchGroup);

        //choose confirmation
        setFieldProperty(itemDetailConfirmationTab+"ssubTABSTRIPCONTROL1SUB:SAPLMEVIEWS:1101/subSUB1:SAPLMEGUI:1334/cmbMEPO1334-BSTAE", "key", "0001");
        makeAction(itemDetailConfirmationTab+"ssubTABSTRIPCONTROL1SUB:SAPLMEVIEWS:1101/subSUB1:SAPLMEGUI:1334/cmbMEPO1334-BSTAE", "setFocus");
        makeAction("wnd[0]/tbar[0]/btn[0]", "press");

        //save
        makeAction("wnd[0]/tbar[0]/btn[11]", "press");

        fiDocNumber = getFieldProperty("wnd[0]/sbar/pane[0]", "text").toString().split(" ")[6];

    }

    public PurchaseOrder copyData(PurchaseOrder oldPO){

        docType = oldPO.docType;
        generalCurrency = oldPO.generalCurrency;
        vendor = oldPO.vendor;
        termsOfPayment = oldPO.termsOfPayment;
        compCode = oldPO.compCode;
        purchGroup = oldPO.purchGroup;
        purchOrg = oldPO.purchOrg;
        itemMaterial = oldPO.itemMaterial;
        itemQuantity = oldPO.itemQuantity;
        itemPO_Unit = oldPO.itemPO_Unit;
        itemNetPrice = oldPO.itemNetPrice;
        itemPlant = oldPO.itemPlant;
        itemStore = oldPO.itemStore;

        return this;

    }

    public void linkToSAPDocument(){

        //Call window to enter number SAP PO
        makeAction("wnd[0]/mbar/menu[0]/menu[9]", "select");
        //Wait element
        waitElement("wnd[1]/usr/ctxt/EBY/PDVI_SPOPUP-VALUE1", 2000);
        //Enter number SAP PO
        setFieldProperty("wnd[1]/usr/ctxt/EBY/PDVI_SPOPUP-VALUE1", "text", fiDocNumber);
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

        selectTab("1");

        if (!getFieldProperty(headerTablePOTAB1 + "PO_NUMBER", "text").toString().equals(fiDocNumber)) {
            try {
                throw new Exception("Can't link PD PO and SAP PO documents");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getDocNumber() {
        return docNumber;
    }

    public String getFiDocNumber() {
        return fiDocNumber;
    }
    
    public String getSapDocNum() {
    	return sapDocNum;
    }
    
    
	   
   }
    
    



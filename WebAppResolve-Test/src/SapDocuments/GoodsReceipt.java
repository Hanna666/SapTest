package SapDocuments;

import SapAppmanager.ApplicationManager;
import SapAppmanager.HelperBase;

public class GoodsReceipt extends HelperBase {

    private String docNumber;
    private String fiDocNumber;

    public GoodsReceipt(ApplicationManager app) {
        super(app);
    }

    public GoodsReceipt withDocType(String value){
        type("", value);
        return this;
    }

    public GoodsReceipt withVendor(String value){
        type("", value);
        return this;
    }

    public GoodsReceipt withTermsOfPayment(String value){
        type("", value);
        return this;
    }

    public GoodsReceipt withGeneralCurrency(String value){
        type("", value);
        return this;
    }

    public GoodsReceipt withPurchOrg(String value){
        type("", value);
        return this;
    }

    public GoodsReceipt withPurchGroup(String value){
        type("", value);
        return this;
    }

    public GoodsReceipt withCompCode(String value){
        type("", value);
        return this;
    }

    public GoodsReceipt create(){
        makeAction("wnd[0]/tbar[1]/btn[5]", "press");
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");
        return this;
    }

    public GoodsReceipt save(){
        makeAction("wnd[0]/tbar[0]/btn[11]", "press");
        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

        docNumber = getFieldProperty("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/subG_0900_BASIC_SUB:" +
                "/EBY/SAPLPDVI_BASICSCREEN:0910/txt/EBY/PDVI_SBASIC_BO-NUMBR", "Text").toString();
        return this;
    }

    public GoodsReceipt selectTab(String value){
        makeAction(String.format("B%s",value), "select");
        return this;
    }

    public GoodsReceipt addLineItem(){
        pressButton("wnd[0]/shellcont[1]/shell", "pressToolbarButton", "ADDROW");
        return this;
    }

    public GoodsReceipt removeLineItem(){
        setSelectedRow("wnd[0]/shellcont[1]/shell", String.valueOf(Integer.parseInt(getFieldProperty("wnd[0]/shellcont[1]/shell", "RowCount").toString())-1));
        pressButton("wnd[0]/shellcont[1]/shell", "pressToolbarButton", "DELROW");
        return this;
    }

    public GoodsReceipt fillCurrentItemMaterial(String value, int row){
        modifyCell("wnd[0]/shellcont[1]/shell", row, "/EBY/MATERIAL", value);
        return this;
    }

    public GoodsReceipt fillCurrentItemQuantity(String value, int row){
        modifyCell("wnd[0]/shellcont[1]/shell", row, "QUANTITY", value);
        return this;
    }

    public GoodsReceipt fillCurrentItemNetPrice(String value, int row){
        modifyCell("wnd[0]/shellcont[1]/shell", row, "NET_PRICE", value);
        return this;
    }

    public GoodsReceipt fillCurrentItemGR_Unit(String value, int row){
        modifyCell("wnd[0]/shellcont[1]/shell", row, "PO_UNIT", value);
        return this;
    }

    public GoodsReceipt pressEnterInItem(){
        setSelectedRow("wnd[0]/shellcont[1]/shell", "0");
        makeAction("wnd[0]/shellcont[1]/shell", "pressEnter");
        return this;
    }

    public String returnGRnumber(){
        return getFieldProperty("", "Text").toString();
    }


    public void pressCheck(){
        makeAction("wnd[0]/tbar[1]/btn[26]", "press");
        makeAction("wnd[1]/usr/radGB_PO", "select");
        makeAction("wnd[1]/usr/radGB_PO", "setFocus");
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

    }

    public void post(){

        makeAction("wnd[0]/tbar[1]/btn[16]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

        fiDocNumber = getFieldProperty("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/tabsG_0900_TABSTRIP/tabpG_0900_TS_TAB1/ssubG_0900_TS_TAB1_SUB:" +
                "/EBY/SAPLPDDN_DETAILSCREEN:0101/txt/EBY/PDDN_SHDR_IF-MAT_DOC", "Text").toString();

    }

    public void postFromLine(){

        setCurrentLineByDocNumber("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_MAIN:0120/cntlCC_MAIN/shellcont/shell","NUMBR", docNumber);
        makeAction("wnd[0]/tbar[1]/btn[16]", "press");
        makeAction("wnd[1]/tbar[0]/btn[0]", "press");

        checkForErrorsAndCloseWindow("/usr/cntlGR_2000_CONTAINER/shellcont/shell/shellcont[0]/shell");

        fiDocNumber = getFieldProperty("wnd[0]/usr/subSUB1:/EBY/SAPLPDVI_SCREEN:0906/tabsG_0900_TABSTRIP/tabpG_0900_TS_TAB1/ssubG_0900_TS_TAB1_SUB:" +
                "/EBY/SAPLPDDN_DETAILSCREEN:0101/txt/EBY/PDDN_SHDR_IF-MAT_DOC", "Text").toString();

    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getFiDocNumber() {
        return fiDocNumber;
    }


}

package SapAppmanager;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;

import java.util.concurrent.TimeUnit;

public class HelperBase {
    protected ApplicationManager app;

    public HelperBase(ApplicationManager app) {
        this.app = app;
    }

    protected ActiveXComponent findById(String id){

        return new ActiveXComponent(app.sessions.get("current").invoke("findById",
                id).toDispatch());
    }

    protected void type(String id, String text){
        findById(id).setProperty("text", text);
    }

    protected void pressButton(String id, String action, String toolbar) {
        findById(id).invoke(action, toolbar);
    }

    protected Variant makeAction(String id, String action){
        return findById(id).invoke(action);
    }
    
   // protected void selectWorkflow (String id, String key) {
	//	return findById(id).setProperty("propertyValue", key);


    protected void selectNode(String id, String row){
        Variant[] argVariant = new Variant[2];
        argVariant[0] = new Variant(row);
        argVariant[1] = new Variant("hierarchy");
        findById(id).invoke("selectNode", argVariant);
    }

    protected void setCurrentCell(String id, int row, String column){
        Variant[] argVariant = new Variant[2];
        argVariant[0] = new Variant(row);
        argVariant[1] = new Variant(column);
        findById(id).invoke("setCurrentCell", argVariant);
    }
    

    protected void setSelectedRow(String id, String row){
        findById(id).setProperty("selectedRows", row);
    }

    protected void modifyCell(String id, int row, String column, String value){
        Variant[] argVariant = new Variant[3];
        argVariant[0] = new Variant(row);
        argVariant[1] = new Variant(column);
        argVariant[2] = new Variant(value);
        findById(id).invoke("modifyCell", argVariant);
    }

    protected Variant getFieldProperty(String id, String property){
        return findById(id).getProperty(property);
    }

    protected void setFieldProperty(String id, String property, String value){
        findById(id).setProperty(property, value);
    }

    protected void doubleClickNode(String id, String node){
        findById(id).invoke("doubleClickNode", node);
    }
    
    
   

    protected void setCurrentLineByDocNumber(String id, String column, String value)  {

        ActiveXComponent Obj = findById(id);
        Obj.invoke("clearSelection");
        Obj.setProperty("currentCellColumn", column);

        String currentDocNumber;
        boolean anError = true;
        int rows = Obj.getProperty("RowCount").getInt();

        Variant[] argVar = new Variant[2];
        argVar[1] = new Variant(column);

        for (int i = 0; i < rows; i++) {
            argVar[0] = new Variant(i);
            currentDocNumber = findById(id).invoke("getCellValue", argVar).getString();
            if (currentDocNumber.equals(value)) {
                anError = false;
                Obj.setProperty("currentCellRow", i);
                break;
            }
        }
        if (anError) {
            try {
                throw new Exception("Can't find doc: "+value);
            } catch (Exception e) {
                e.printStackTrace();
                
            }
        }

    }

    protected String getActiveWindowId(){
        return new ActiveXComponent(app.sessions.get("current").getProperty("ActiveWindow").toDispatch()).getProperty("Id").getString();
    }

    protected void closePopupWindow(){
        makeAction(getActiveWindowId(), "Close");
    }

    protected String turnOffMessages(String area){
        //Turn Off success
        pressButton(getActiveWindowId()+area, "pressButton", "BTN_SUCC");
        //Turn Off yellow messages
        pressButton(getActiveWindowId()+area, "pressButton", "BTN_WARN");

        //If there are no any errors - there is no node
        return makeAction(getActiveWindowId()+area.replace("shellcont[0]","shellcont[1]"), "GetFocusedNodeKey").toString();
    }

    protected boolean isActiveWindowContain(String value){
        if(getFieldProperty(getActiveWindowId(), "Text").toString().toLowerCase().contains(value.toLowerCase())){
            return true;
        }
        return false;
    }

    protected void checkForErrorsAndCloseWindow(String area){

        if(!isActiveWindowContain("message")){
            return;
        }

        if(turnOffMessages(area).equals("")){
            closePopupWindow();
        }else {
            try {
                throw new Exception("Problems in node: "+app.getNavigation().returnTopNodePD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void snooze(int mlSec) {
        try {
            TimeUnit.MILLISECONDS.sleep(mlSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean isElementPresent(String id){

        try {
            findById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected void waitElement(String id, int mlSec){
        Boolean present = false;
        long timeStart = System.currentTimeMillis();
        long timeEnd = timeStart+mlSec;
        while (timeStart<=timeEnd){
            present = isElementPresent(id);
            if (present){
                break;
            }
        }
    }

    protected void setCurrentSession(String keyName){
        ActiveXComponent oldSession = app.sessions.get("current");
        app.sessions.replace("current",app.sessions.get(keyName));
        app.sessions.replace(keyName,oldSession);
    }

    protected void addSessionToMap(String keyName, ActiveXComponent session){
        app.sessions.put(keyName,session);
    }
}

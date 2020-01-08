package SapAppmanager;

public class NavigationHelper extends HelperBase{
    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void openControl(){
        waitElement("wnd[0]/tbar[0]/okcd", 3000);
        type("wnd[0]/tbar[0]/okcd", "/n/TISA/C");
        makeAction("wnd[0]/tbar[0]/btn[0]", "press");
        makeAction("wnd[0]/tbar[1]/btn[8]", "press");
    }

    public void openNode(String node){
        doubleClickNode("wnd[0]/shellcont/shell/shellcont[2]/shell", node);
    }

    public String returnTopNodePD(){
        return findById("wnd[0]/shellcont/shell/shellcont[2]/shell").getProperty("TopNode").getString();
    }

    public NavigationHelper pressBack(){
        makeAction("wnd[0]/tbar[0]/btn[3]", "press");
        return this;
    }

    public NavigationHelper pressUp(){
        makeAction("wnd[0]/tbar[0]/btn[15]", "press");
        return this;
    }

    public void closeSAP(){
        while (!isActiveWindowContain("Log Off")){
            pressUp();
        }
        makeAction("wnd[1]/usr/btnSPOP-OPTION1","press");
    }
    
    
       
    
   // public String returnSapDocNo() {
   //     findById("wnd[0]/usr/lbl[34,22]").getProperty("DocNo").getString();
      
 
    

}

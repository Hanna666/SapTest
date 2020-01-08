package SapAppmanager;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {

        super(app);
    }

    public void startSAP() {

        snooze(2000);

        ComThread.InitSTA();

        //-Set SapGuiAuto = GetObject("SAPGUI")-----------------------------
        app.sAPROTWr = new ActiveXComponent("SapROTWr.SapROTWrapper");
        try{
            app.rotEntry = app.sAPROTWr.invoke("GetROTEntry", "SAPGUI").toDispatch();

            //-Set application = SapGuiAuto.GetScriptingEngine----------------
            app.scriptEngine = Dispatch.call(app.rotEntry, "GetScriptingEngine");
            app.guiApp = new ActiveXComponent(app.scriptEngine.toDispatch());
            //-Set connection = application.Children(0)-----------------------
            app.connection = new ActiveXComponent(
                    app.guiApp.invoke("Children", ApplicationManager.qntConnections).toDispatch()
            );
            //-Set session = connection.Children(0)---------------------------
            app.sessions.put("current" ,new ActiveXComponent(
                    app.connection.invoke("Children", 0).toDispatch()));

            ApplicationManager.qntConnections +=1;

        } catch (Exception e) {
            System.out.println(
                    e.getMessage());
        }

    }

    public void login(String user) {

        waitElement("wnd[0]/usr/txtRSYST-MANDT", 2000);

        //--Enter Client
        type("wnd[0]/usr/txtRSYST-MANDT", app.getProperties().getProperty(String.format("%sUserClient",user)));

        //--Enter User
        type("wnd[0]/usr/txtRSYST-BNAME", app.getProperties().getProperty(String.format("%sUserName",user)));

        //--Enter password
        type("wnd[0]/usr/pwdRSYST-BCODE", app.getProperties().getProperty(String.format("%sUserPassword",user)));

        //--Enter language
        type("wnd[0]/usr/txtRSYST-LANGU", app.getProperties().getProperty(String.format("%sUserLang",user)));

        //--Press Enter
        makeAction("wnd[0]/tbar[0]/btn[0]", "press");

    }

}

package SapAppmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.jacob.activeX.*;
import com.jacob.com.*;


public class ApplicationManager {

    //-Variables--------------------------------------------------------
    ActiveXComponent sAPROTWr, guiApp, connection;
    Map<String, ActiveXComponent> sessions;
    Dispatch rotEntry;
    Variant scriptEngine;

    static int qntConnections;

    private final Properties properties;
    private Process shell;
    private String fileName;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;

    public ApplicationManager(String fileName) {
        properties = new Properties();
        this.fileName = fileName;
        sessions = new HashMap<>();
    }

    public void init(){
        if (shell != null) {
            return;
        }

        String target = System.getProperty("target", "local");
        try {
            properties.load(new FileReader(new File(String.format("resources/%s.properties", target))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProcessBuilder pro = new ProcessBuilder(properties.getProperty("batPath")+fileName);
        try {
            shell = pro.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ComThread.InitSTA();

        navigationHelper = new NavigationHelper(this);
        sessionHelper = new SessionHelper(this);

        //Start SAP
        sessionHelper.startSAP();

    }

    public void stop() {
        navigationHelper.closeSAP();
        ComThread.Release();
        shell.destroy();
    }

    public NavigationHelper getNavigation() {
        return navigationHelper;
    }

    public SessionHelper getSession() {
        return sessionHelper;
    }

    public Properties getProperties() {
        return properties;
    }

}

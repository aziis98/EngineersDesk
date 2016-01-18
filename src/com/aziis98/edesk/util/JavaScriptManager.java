package com.aziis98.edesk.util;

import javax.script.*;
import java.io.*;

public class JavaScriptManager {

    private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private static ScriptEngine engine = scriptEngineManager.getEngineByName( "nashorn" );

    public static void load(String fileName) {
        try
        {
            engine.getBindings( ScriptContext.ENGINE_SCOPE ).put( "__TEST__", "a test string" );
            engine.eval( new FileReader( fileName ) );
        }
        catch (ScriptException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}

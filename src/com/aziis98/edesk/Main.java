package com.aziis98.edesk;

import com.aziis98.edesk.display.*;
import com.aziis98.edesk.event.*;

public class Main {

    public static void main(String[] args) {
        // args[0] = <file to open>
        launch(new DisplayJFrame());
    }

    public static void launch(AbstractDisplay display) {
        display.setTitle( "Engineers'Desk" );

        EventManager.addListener( "display.render", (Drawer drawer) -> {

        });
    }

}

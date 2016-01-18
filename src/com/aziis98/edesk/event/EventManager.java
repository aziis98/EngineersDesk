package com.aziis98.edesk.event;

import java.util.*;

public class EventManager {

    private static final Map<String, List<Listener>> listeners = new HashMap<>();

    public static <T> void addListener(String event, Listener<T> listener) {
        List<Listener> listenerList = listeners.get( event );

        if ( listenerList == null )
            listeners.put( event, listenerList = new LinkedList<>() );

        listenerList.add( listener );
    }

    @SuppressWarnings("unchecked")
    public static <T> void invoke(String event, T data) {
        List<Listener> listenerList = EventManager.listeners.get( event );

        if ( listenerList != null )
        {
            listenerList.forEach( listener -> listener.invoke( data ) );
        }
    }


    @FunctionalInterface
    public interface Listener<T> {

        void invoke(T data);

    }


}

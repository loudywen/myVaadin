package com.vaadin.devon.util;

import com.vaadin.devon.entity.User;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;



/**
 * Convenience wrapper for storing and retreiving a user from the VaadinSession
 */
public class CheckSession {

    private static final String KEY = "currentuser";
    
    public static void signOut(){
    	VaadinSession.getCurrent().close();
    }

    public static void set(User user) {
    	
    		VaadinSession.getCurrent().setAttribute(KEY, user);
    		Page.getCurrent().reload();
    }

    public static User get() {
        return (User) VaadinSession.getCurrent().getAttribute(KEY);
    }

    public static boolean isLoggedIn() {
        return get() != null;
    }
}

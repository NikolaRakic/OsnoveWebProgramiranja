package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.ConnectionManager;

public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
         ConnectionManager.close();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("inicijalizacija...");
         ConnectionManager.open();
         System.out.println("zavrsena inicijalizacija");
    }
	
}

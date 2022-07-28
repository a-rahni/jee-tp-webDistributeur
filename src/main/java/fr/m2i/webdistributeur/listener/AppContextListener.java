package fr.m2i.webdistributeur.listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    private static Logger logger = Logger.getLogger(AppContextListener.class.getName());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("---- App started ----");
        logger.info(dtf.format(LocalDateTime.now()));
        logger.info("---- App started ----");

        logger.info("---- Init done ----");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("---- App stopped ----");
        logger.info(dtf.format(LocalDateTime.now()));
        logger.info("---- App stopped ----");
    }
}

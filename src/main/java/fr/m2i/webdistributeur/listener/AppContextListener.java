package fr.m2i.webdistributeur.listener;

import fr.m2i.webdistributeur.Distributor;
import fr.m2i.webdistributeur.dao.UserDao;
import fr.m2i.webdistributeur.utils.UserDb;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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
        
        // On rÈcupËrere le servlet context via le servlet context event passÈ en paramËtre
        ServletContext ctx = sce.getServletContext();
        
        // On rÈcupËre les identifiants de la base de donnÈe stockÈs dans le web.xml en context-param
        String dbUser = ctx.getInitParameter("dbUser");
        String dbPass = ctx.getInitParameter("dbPass");
        
        // On rÈcupËrere l'instance de notre base de donnÈes
        UserDb userDb = UserDb.getInstance(dbUser, dbPass);
        
         // Si elle est null c'est que les identifiants sont mauvais
        if (userDb == null) {
            logger.severe("/!\\ Impossible de se connecter √† la base de donn√©e /!\\");
            return; // On s'arrÍte la
        }
        // Si on arrive ici c'est que la connexion ‡† la base de donnÈe s'est bien passÈe -> les identifiants sont bons
        
        // On garde dans les attributs du servlet context l'instance de notre base de donn√©e
        ctx.setAttribute("userDb", userDb);
        
        // on instancie la ditributeur
        Distributor distributeur = Distributor.getInstance();
        // on garde l'instance distributeur dans  les atributs servelt context
        ctx.setAttribute("distributeur", distributeur);
        
        // on instancie UserDao
        UserDao userDao = new UserDao(userDb);
        ctx.setAttribute("userDao", userDao);
        // ajouter la liste des role daans les attribut de servlet context
        /* TO DO*/

        logger.info("---- Init done ----");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("---- App stopped ----");
        logger.info(dtf.format(LocalDateTime.now()));
        logger.info("---- App stopped ----");
    }
}

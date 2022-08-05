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
        
        // On r�cup�rere le servlet context via le servlet context event pass� en param�tre
        ServletContext ctx = sce.getServletContext();
        
        // On r�cup�re les identifiants de la base de donn�e stock�s dans le web.xml en context-param
        String dbUser = ctx.getInitParameter("dbUser");
        String dbPass = ctx.getInitParameter("dbPass");
        
        // On r�cup�rere l'instance de notre base de donn�es
        UserDb userDb = UserDb.getInstance(dbUser, dbPass);
        
         // Si elle est null c'est que les identifiants sont mauvais
        if (userDb == null) {
            logger.severe("/!\\ Impossible de se connecter à la base de donnée /!\\");
            return; // On s'arr�te la
        }
        // Si on arrive ici c'est que la connexion � la base de donn�e s'est bien pass�e -> les identifiants sont bons
        
        // On garde dans les attributs du servlet context l'instance de notre base de donnée
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

package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.Distributor;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyProductServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        setDistributorAttribute(request);
        this.getServletContext().getRequestDispatcher("/META-INF/customer/buyProduct.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //super.doPost(request, response);
         addCredit(request);
        buyProduct(request);
        setDistributorAttribute(request);

        this.getServletContext().getRequestDispatcher("/META-INF/customer/buyProduct.jsp").forward(request, response);
    }
    
    
     private void setDistributorAttribute(HttpServletRequest request) {
         //Distributor distributeur = Distributor.getInstance();
         Distributor distributeur = (Distributor)this.getServletContext().getAttribute("distributeur");
        if(distributeur == null){
            request.setAttribute("error", "data not availables");
            // add error dispaly in jsp page ..: to do
            return;
        }
        request.setAttribute("credit", distributeur.getCredit());
        request.setAttribute("stock", distributeur.getStock());
    }
    
     private void addCredit(HttpServletRequest request) {
        //Distributor distributeur = Distributor.getInstance();
        Distributor distributeur = (Distributor)this.getServletContext().getAttribute("distributeur");
        if(distributeur == null){
            request.setAttribute("error", "data not availables");
            // add error dispaly in jsp page ..: to do
            return;
        }
        String credit = request.getParameter("credit");

        if (credit == null) {
            return;
        }

        try {
            int amount = Integer.parseInt(credit);

            if (amount < 0) {
                request.setAttribute("creditError", "Vous ne pouvez pas ajouter un montant négatif");
                return;
            }

            distributeur.insererArgent(amount);
            // distributeur.setCredit(distributeur.getCredit() + Integer.parseInt(amount));
            request.setAttribute("creditError", null);
        } catch (Exception e) {
            request.setAttribute("creditError", "Une erreur est survenue lors de l'ajout du crédit");
        }
    }
     
      private void buyProduct(HttpServletRequest request) {
        //Distributor distributeur = Distributor.getInstance();
        Distributor distributeur = (Distributor)this.getServletContext().getAttribute("distributeur");
        if(distributeur == null){
            request.setAttribute("error", "data not availables");
            // add error dispaly in jsp page ..: to do
            return;
        }
        
        String productId = request.getParameter("productId");

        if (productId == null || "".equals(productId)) {
            return;
        }

        try {
            int id = Integer.parseInt(productId);
            
            if (distributeur.getProduit(id) == null) {
                request.setAttribute("productError", "Le produit demandé n'existe pas");
                return;
            }
            
            if (!distributeur.creditSuffisant(id)) {
                request.setAttribute("insufficientError", "Votre crédit est insuffisant");
                return;
            }

            if (!distributeur.stockSuffisant(id)) {
                request.setAttribute("productError", "Le produit n'est plus en stock");
                return;
            }

            distributeur.commanderProduit(id);
        } catch(Exception e) {
            request.setAttribute("productError", "Une erreur est survenue lors de l'achat");
        }
      }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.Distributor;
import fr.m2i.webdistributeur.Product;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductServlet extends HttpServlet {

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
        this.getServletContext().getRequestDispatcher("/META-INF/provider/updateProduct.jsp").forward(request, response);
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

        if (request.getParameter("valider") != null ) { 
            updateProduct(request);
            response.setHeader("Refresh", "5; URL=HandleProductServlet");
    
            this.getServletContext().getRequestDispatcher("/META-INF/provider/updateProduct.jsp").forward(request, response);
            //response.sendRedirect("HandleProductServlet");
                   
        }
    }
    
     private void updateProduct(HttpServletRequest request) {

         //Distributor distributeur = Distributor.getInstance();
         Distributor distributeur = (Distributor)this.getServletContext().getAttribute("distributeur");
        if(distributeur == null){
            request.setAttribute("updateError", "data not availables, update can not be done");
            return;
        }

        String productId = request.getParameter("idProduct");

        if (productId == null || "".equals(productId)) {
            return;
        }
        

        try {
            int id = Integer.parseInt(productId);
            
            if (distributeur.getProduit(id) == null) {
                request.setAttribute("updateError", "Le produit a modifier n'existe pas");
                return;
            }
            
            String name = request.getParameter("nameProduct");
            if("".equals(name)){
                request.setAttribute("updateError", "Le produit a modifier n'existe pas");
                return;
            }
        
            String price = request.getParameter("priceProduct");
            int prix = Integer.parseInt(price);
            String quantity = request.getParameter("qteProduct");
            int quantite = Integer.parseInt(quantity);
            
            Product product = new Product(id,name,prix,quantite );  
            distributeur.updateProduct(product);
            request.setAttribute("updateMessage", "le produit a été bien modifié ");
            
        } catch(Exception e) {
            request.setAttribute("updateError", "Une erreur est survenue lors de l'achat");
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

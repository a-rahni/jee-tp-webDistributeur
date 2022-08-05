package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.Distributor;
import fr.m2i.webdistributeur.Product;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductServlet extends HttpServlet {

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
        this.getServletContext().getRequestDispatcher("/META-INF/provider/addProduct.jsp").forward(request, response);
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
        addProduct(request);
        response.setHeader("Refresh", "5; URL=HandleProductServlet");
    
            this.getServletContext().getRequestDispatcher("/META-INF/provider/addProduct.jsp").forward(request, response);
           // response.sendRedirect("HandleProductServlet");
    }
    
    private void addProduct(HttpServletRequest request) {

        // Distributor distributeur = Distributor.getInstance();
        Distributor distributeur = (Distributor)this.getServletContext().getAttribute("distributeur");
        if(distributeur == null){
            request.setAttribute("error", "data not availables");
            // add error dispaly in jsp page ..: to do
            return;
        }
        String productId = request.getParameter("idProduct");

        if (productId == null || "".equals(productId)) {
            return;
        }
        

        try {
            int id = Integer.parseInt(productId);
            
            if (distributeur.getProduit(id) != null) {
                request.setAttribute("addError", "L'idetifiant du produit existe déja");
                return;
            }
            
            String name = request.getParameter("nameProduct");
            if((name == null) || ("".equals(name))){
                request.setAttribute("addError", "Le nom ne doit pas etre null");
                return;
            }
        
            String price = request.getParameter("priceProduct");
            int prix = Integer.parseInt(price);
            String quantity = request.getParameter("qteProduct");
            int quantite = Integer.parseInt(quantity);
            
            Product product = new Product(id,name,prix,quantite );  
            distributeur.addProduct(product);
            request.setAttribute("addMessage", "le produit a été bien ajouté ");
            
        } catch(Exception e) {
            request.setAttribute("addError", "Une erreur est survenue lors de l'ajout");
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

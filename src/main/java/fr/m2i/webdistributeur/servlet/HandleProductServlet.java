package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.Distributor;
import fr.m2i.webdistributeur.Product;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleProductServlet extends HttpServlet {

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
        setStockAttribute(request);
        
        this.getServletContext().getRequestDispatcher("/META-INF/provider/handleProduct.jsp").forward(request, response);
    }
    
    private void setStockAttribute(HttpServletRequest request) {
        Distributor distributeur = Distributor.getInstance();
        request.setAttribute("stock", distributeur.getStock());
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
        Distributor distributeur = Distributor.getInstance();
        String deleteRequest = request.getParameter("supp");
        String modifRequest = request.getParameter("modif");

        if (deleteRequest != null || modifRequest != null) {
            Product product = checkProduct(request);
            if(product != null){
                if(deleteRequest != null){
                    distributeur.removeProduct(product.getId());
                    request.setAttribute("stock", distributeur.getStock());
                    this.getServletContext().getRequestDispatcher("/META-INF/provider/handleProduct.jsp").forward(request, response);
                }
                if(modifRequest != null){
                    request.setAttribute("product", product);
                    this.getServletContext().getRequestDispatcher("/META-INF/provider/updateProduct.jsp").forward(request, response);
                    //response.sendRedirect("provider/UpdateProductServlet");
                }
            }
        }

    }
    
    private Product checkProduct(HttpServletRequest request){
        
        String productId = request.getParameter("idProduct");

        if (productId == null || "".equals(productId)) {
            return null;
        }
        
        Distributor distributeur = Distributor.getInstance();

        try {
            int id = Integer.parseInt(productId);
            Product product = distributeur.getProduit(id);
            if ( product == null) {
                request.setAttribute("productError", "Le produit demandé n'existe pas");
            }
            return product;

        } catch(Exception e) {
            request.setAttribute("productError", "Une erreur est survenue lors de l'achat");
            return null;
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

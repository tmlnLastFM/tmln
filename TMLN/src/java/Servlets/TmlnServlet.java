/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.TMLNArtist;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Chart;
import de.umass.lastfm.Period;
import de.umass.lastfm.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author michi
 */
@WebServlet(name = "TmlnServlet", urlPatterns = {"/TmlnServlet"})
public class TmlnServlet extends HttpServlet {
    private static String key = "4d2f280d1bdd14ca03f7383532c38d7f";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.getSession().setAttribute("top10", "");
        request.getRequestDispatcher("/tmlnjsp.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        String user = "nici6120";
        
        // Berechnung der Top 10 des Zeitraums
        // Zeitraum muss noch per Input einlesbar gemacht werden
        
        // getWeeklyArtist statt getTopArtists
        // Epoch Time
        
        Collection<Artist> topLastfmArtists = User.getTopArtists(user, Period.SIX_MONTHS, key);
        TMLNArtist[] top10Artists = new TMLNArtist[10];
        int i = 0;
        for (Artist topArtist : topLastfmArtists) {
            top10Artists[i++] = new TMLNArtist(i,topArtist.getName(),topArtist.getPlaycount());
            if(i==10) { break; }
        }
        request.getSession().setAttribute("top10", top10Artists);
        request.getRequestDispatcher("/tmlnjsp.jsp").forward(request, response);
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

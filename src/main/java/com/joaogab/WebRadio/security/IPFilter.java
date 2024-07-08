package com.joaogab.WebRadio.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@WebFilter("/admin")
public class IPFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(IPFilter.class.getName());

    private List<String> allowedIPs = Arrays.asList("sua.lista.de.ips","pode.inserir.varios.ips");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String clientIP = req.getRemoteAddr();

        LOGGER.info("Request from IP: " + clientIP);

        if (allowedIPs.contains(clientIP)) {
            chain.doFilter(request, response);
        } else {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = res.getWriter();
            out.println("<html><head><title>Acesso Negado</title></head><body>");
            out.println("<h1>Acesso Negado</h1>");
            out.println("<p>O seu IP (" + clientIP + ") não está autorizado a acessar esta página.</p>");
            out.println("</body></html>");
        }
    }
}

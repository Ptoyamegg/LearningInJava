package xyz.dyk.javaweb.filter;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(StandardCharsets.ISO_8859_1.name());
            System.out.println("setting characterEncoding-----------null");
        }
        System.out.println("setting characterEncoding-----------");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

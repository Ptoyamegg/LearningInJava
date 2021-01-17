package xyz.dyk.javaweb.sevrlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorld extends HttpServlet {
    private String message;

    @Override
    public void init() throws ServletException {
        System.out.println("init()");
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>" + message + "</h1>");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service()");
        super.service(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
    }
}

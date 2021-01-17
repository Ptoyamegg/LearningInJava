package xyz.dyk.javaweb.sevrlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

public class HelloFrom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println(parameterMap);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentType = req.getContentType();
        System.out.println(contentType);
        String encoding = req.getCharacterEncoding();
        System.out.println(encoding);
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println(parameterMap);
        String pathInfo = req.getPathInfo();
        System.out.println(pathInfo);
        String localAddr = req.getLocalAddr();
        System.out.println(localAddr);
        String remoteAddr = req.getRemoteAddr();
        System.out.println(remoteAddr);
        String remoteHost = req.getRemoteHost();
        System.out.println(remoteHost);
        String protocol = req.getProtocol();
        System.out.println(protocol);
        parameterMap.forEach((k, v) -> {
            System.out.print(k + ">>>>");
            for (String s : v) {
                System.out.print(s.getBytes());
                System.out.print(s.getBytes(StandardCharsets.ISO_8859_1));
                System.out.print(new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }
            System.out.println();
        });
    }
}

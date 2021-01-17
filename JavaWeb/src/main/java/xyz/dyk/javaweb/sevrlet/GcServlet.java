package xyz.dyk.javaweb.sevrlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GcServlet extends HttpServlet {
    private static int i = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
//                    new BigData();
                }
            }
        }).start();
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>OK</h1>");
    }

    private class BigData {
        private int count;
        private byte[] bytes;

        public BigData() {
            count = i++;
            System.out.println(i);
            this.bytes = new byte[1024];
            for (int i = 0; i < bytes.length; i++) {
                this.bytes[i] = (byte) Math.random();
            }
        }
    }
}

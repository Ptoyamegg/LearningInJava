package xyz.dyk.chapter4.inetAddress;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressTest {
    public static void main(String[] args) throws IOException {
//        args = new String[]{"www.baidu.com"};
        if (args.length > 0) {
            String host = args[0];
            InetAddress[] address = InetAddress.getAllByName(host);
            for (InetAddress inetAddress : address) {
                System.out.println(inetAddress);
            }
        } else {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}

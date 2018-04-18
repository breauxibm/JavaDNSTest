package com.ibm.us.breaux;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Simple Java DNS lookup, with no DNS caching. One-time or repeat until interrupted.
 */
public class DnsTest
{
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java " + DnsTest.class.getName() + " <hostname> [repeat-seconds]");
            System.exit(1);
        }

        java.security.Security.setProperty("networkaddress.cache.ttl" , "0");
        java.security.Security.setProperty("networkaddress.cache.negative.ttl" , "0");

        String hostname = args[0];

        checkDns(hostname);

        if (args.length == 2) {
            int delay = Integer.valueOf(args[1]);

            while (true) {
                try {
                    Thread.sleep(delay * 1000);
                }
                catch (InterruptedException e) { }
                checkDns(hostname);
            }
        }
    }

    protected static void checkDns(String hostname) {
        long start = System.currentTimeMillis();

        try {
            InetAddress host = InetAddress.getByName(hostname);
            long end = System.currentTimeMillis();
            System.out.println(new Date() + ": " + hostname + "=" + host.getHostAddress() + ", " + (end-start) + "ms");
        }

        catch (UnknownHostException e) {
            long end = System.currentTimeMillis();
            System.err.println(new Date() + ": " + e.toString() + ", " + (end-start) + "ms");
        }
    }

}

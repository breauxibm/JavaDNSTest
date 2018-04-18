# JavaDNSTest
Trivial Java DNS test program. Zeroes [Java DNS TTL settings](https://docs.oracle.com/javase/8/docs/technotes/guides/net/properties.html) as well, to force new queries.


`Usage: java com.ibm.us.breaux.DnsTest <hostname> [cycle-seconds]`

If no `cycle-seconds` is provided, a single query will be made. If a number is provided, the program will query repeatedly, every `cycle-seconds`, until interrupted.

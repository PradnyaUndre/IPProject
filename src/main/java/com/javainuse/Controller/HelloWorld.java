package com.javainuse.Controller;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javainuse.util.ConstantUtil;

@RestController
public class HelloWorld {
	
	@Autowired
	ConstantUtil constantUtil= new ConstantUtil();
	/*
	@RequestMapping({ "/" })
	public String start() {
		return "start";
	}	
*/
	
	@GetMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}	

	@GetMapping("/displayMessage")
	public String displayMessage() {
		//return "Good Morning";
		return "start";
	}
	
	 @GetMapping("/showIpAddress")
	  public String test(HttpServletRequest  request) throws UnknownHostException {
	    String remoteAddress = request.getRemoteAddr();
	    String localAddress = request.getLocalAddr();
	    System.out.println("remoteAddress="+remoteAddress);
	    System.out.println("localAddress="+localAddress);
	    int localPort = request.getLocalPort();
	    System.out.println("localPort="+localPort);
	    System.out.println("getRemoteHost="+request.getRemotePort());
	    System.out.println("getRemoteHost="+request.getRemoteHost());

	    System.out.println(getIpAddressFromHeader(request));
	    
	    InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost IA=="+localHost.getHostAddress());
	    //IPv4 Address. . . . . . . . . . . : 192.168.0.73(Preferred) //172.20.1.131
        
	    /*
	    InetSocketAddress remoteAddress1 = request.getRemoteAddress();
	    System.out.println(remoteAddress1.getAddress());
	    System.out.println(remoteAddress1.getHostName());
	    System.out.println(remoteAddress1.getHostString());
	    System.out.println(remoteAddress1.getPort());
	    */
		return remoteAddress;
	  }
	 
	 @GetMapping("/getAllIpaddress")
	 public void getAllIpAddress()
	 {
		 try {
	            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
	            while( networkInterfaceEnumeration.hasMoreElements()){
	                for ( InterfaceAddress interfaceAddress : networkInterfaceEnumeration.nextElement().getInterfaceAddresses())
	                    if ( interfaceAddress.getAddress().isSiteLocalAddress())
	                        System.out.println(interfaceAddress.getAddress().getHostAddress());
	            }
	        } catch (SocketException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 @GetMapping("/displayIP")
	  public static Map<String, String> getClientIp(HttpServletRequest request) {

		 /*
		 	System.out.println("header="+request.getH);
	        String remoteAddr = "";
	        if (request != null) {
	            remoteAddr = request.getHeader("X-FORWARDED-FOR");
	            if (remoteAddr == null || "".equals(remoteAddr)) {
	                remoteAddr = request.getRemoteAddr();
	            }
	        }
	        System.out.println("remoteAddr=="+remoteAddr);
	        return remoteAddr;
	        */
	        
	        Map<String, String> result = new HashMap<>();

	        Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            System.out.println("Key=="+key);
	            String value = request.getHeader(key);
	            System.out.println("value=="+value);
	            result.put(key, value);
	        }

	        return result;
	    }
	 
	@GetMapping("/getJavaTPointIP")
	 public static void getData()
	 {
		try{
			System.out.println("In getJavaTPointIP");
			InetAddress ip=InetAddress.getByName("www.javatpoint.com");  
			  
			System.out.println("Host Name: "+ip.getHostName());  
			System.out.println("IP Address: "+ip.getHostAddress());  
			
			InetAddress ip1=InetAddress.getByName("www.geeksforgeeks.org");  
			System.out.println("Host Name 1: "+ip1.getHostName());  
			System.out.println("IP1 Address: "+ip1.getHostAddress());  

			}catch(Exception e){System.out.println(e);}  
		
	//	public static InetAddress getByName(String host) throws UnknownHostException	It returns the instance of InetAddress containing LocalHost IP and name.
		
	//public static InetAddress getLocalHost() throws UnknownHostException	It returns the instance of InetAdddress containing local host name and address.
		//public String getHostName()	It returns the host name of the IP address.
		//public String getHostAddress()	It returns the IP address in string format.
	}  
	 
	 
	 /*
	 
	 public static String getRequestIP(HttpServletRequest request)
	 {
	        for (String header: IP_HEADERS) 
	            String value = request.getHeader(header);
	            if (value == null || value.isEmpty()) {
	                continue;
	            }
	            String[] parts = value.split("\\s*,\\s*");
	            return parts[0];
	        }
	        return request.getRemoteAddr();
	 */
	 
	 
	 /*
	  * remoteAddress=0:0:0:0:0:0:0:1
localAddress=0:0:0:0:0:0:0:1
localPort=8080
getRemoteHost=59658
getRemoteHost=0:0:0:0:0:0:0:1
null
	  * 
	  */
	 
	 private String getIpAddressFromHeader(HttpServletRequest request) {
		    for (String ipHeader : constantUtil.POSSIBLE_IP_HEADERS) {
		      String headerValue = Collections.list(request.getHeaders(ipHeader)).stream()
		          .filter(StringUtils::hasLength)
		          .findFirst()
		          .orElse(null);
		 
		      if (headerValue != null) {
		        return headerValue;
		      }
		    }
		 
		    return null;
		  }
}

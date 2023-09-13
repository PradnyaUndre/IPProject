package com.javainuse.Controller;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

@RestController
@Component
public class IPAddressInterceptor implements HandlerInterceptor {
	
	@Override
	@GetMapping("/getaddrs")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
 
        String ipAddress = request.getHeader("X-Forward-For");
        System.out.println(ipAddress);
        if(ipAddress== null){
            ipAddress = request.getRemoteAddr();
        }
        System.out.println(ipAddress);
        if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
        	InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("localHost IA=="+localHost.getHostAddress());
        }
        
        return false;
    }

}

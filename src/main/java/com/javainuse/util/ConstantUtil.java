package com.javainuse.util;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ConstantUtil {
	
	 public static final List<String> POSSIBLE_IP_HEADERS = List.of(
		      "X-Forwarded-For",
		      "HTTP_FORWARDED",
		      "HTTP_FORWARDED_FOR",
		      "HTTP_X_FORWARDED",
		      "HTTP_X_FORWARDED_FOR",
		      "HTTP_CLIENT_IP",
		      "HTTP_VIA",
		      "HTTP_X_CLUSTER_CLIENT_IP",
		      "Proxy-Client-IP",
		      "WL-Proxy-Client-IP",
		      "REMOTE_ADDR"
		  );

}

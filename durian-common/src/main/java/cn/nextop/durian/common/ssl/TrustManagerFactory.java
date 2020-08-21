package cn.nextop.durian.common.ssl;

import javax.net.ssl.TrustManager;

public interface TrustManagerFactory {
	
	TrustManager[] create(Object id);
}

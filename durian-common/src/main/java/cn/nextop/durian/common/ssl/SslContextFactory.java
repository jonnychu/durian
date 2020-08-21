package cn.nextop.durian.common.ssl;

import javax.net.ssl.SSLContext;

public interface SslContextFactory {
	
	SSLContext create(Object id);
}

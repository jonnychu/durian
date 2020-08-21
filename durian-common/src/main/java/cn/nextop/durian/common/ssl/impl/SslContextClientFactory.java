package cn.nextop.durian.common.ssl.impl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.nextop.durian.common.ssl.SslContextFactory;
import cn.nextop.durian.common.ssl.TrustManagerFactory;

public final class SslContextClientFactory implements SslContextFactory {
	//
	private static final Logger LOGGER = LoggerFactory.getLogger(SslContextClientFactory.class);
	
	//
	private SSLContext instance;
	private String protocol = "TLS";
	private TrustManagerFactory trustManagerFactory;
	
	/**
	 * 
	 */
	public String getProtocol() {
		return protocol;
	}
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public void setTrustManagerFactory(TrustManagerFactory factory) {
		this.trustManagerFactory = factory;
	}
	
	/**
	 * 
	 */
	@Override
	public synchronized SSLContext create(final Object id) {
		//
		if(this.instance != null) return this.instance;
		
		//
		try {
			//
			if(this.trustManagerFactory == null) {
				this.trustManagerFactory = new TrustAllManagerFactory();
				LOGGER.warn("trust manager was not properly set, use trust ALL manager instead");
			}
			
			//
			final TrustManager[] managers = this.trustManagerFactory.create(id);
			this.instance = SSLContext.getInstance(this.protocol); this.instance.init(null, managers, null);
		} catch (Exception e) {
			throw new RuntimeException("failed to create ssl context, id: " + id + ", protocol: " + this.protocol, e);
		}
		return this.instance;
	}
}

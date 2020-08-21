package cn.nextop.durian.common.ssl.impl;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.Security;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import cn.nextop.durian.common.ssl.SslContextFactory;

public final class SslContextServerFactory implements SslContextFactory {
	//
	private SSLContext instance;
	private String keyStorePath;
	private String protocol = "TLS";
	private String keyStorePassword;
	private String certificatePassword;
	
	/**
	 * 
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public void setKeyStorePath(String path) {
		this.keyStorePath = path;
	}
	
	public void setKeyStorePassword(String password) {
		this.keyStorePassword = password;
	}
	
	public void setCertificatePassword(String password) {
		this.certificatePassword = password;
	}
	
	/**
	 * 
	 */
	protected String getAlgorithm() {
		String r = Security.getProperty("ssl.KeyManagerFactory.algorithm");
		return r != null ? r : "SunX509";
	}
	
	/**
	 * 
	 */
	@Override
	public synchronized SSLContext create(final Object id) {
		//
		if(this.instance != null) return this.instance;
		
		//
		InputStream is = null;
		try {
			//
			final Thread t = Thread.currentThread();
			is = t.getContextClassLoader().getResourceAsStream(this.keyStorePath);
			if(is == null) {
				throw new Exception("failed to load certificate from: " + this.keyStorePath); 
			}
			
			//
			final char[] ksp = this.keyStorePassword.toCharArray();
			final char[] cp = this.certificatePassword.toCharArray();
			final KeyStore ks = KeyStore.getInstance("JKS"); ks.load(is, ksp);
			final KeyManagerFactory kmf = KeyManagerFactory.getInstance(getAlgorithm()); kmf.init(ks, cp);
			this.instance = SSLContext.getInstance(protocol); instance.init(kmf.getKeyManagers(), null, null);
		} catch (Exception e) {
			throw new RuntimeException("failed to create ssl context, id: " + id + ", protocol: " + this.protocol, e);
		} finally {
			try { if(is != null) is.close(); } catch(Throwable ignore) { /* NOP */ }
		}
		return this.instance;
	}
}

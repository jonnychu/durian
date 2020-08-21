package cn.nextop.durian.common.ssl.impl;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509TrustManager;

import cn.nextop.durian.common.ssl.TrustManagerFactory;

public class TrustAllManagerFactory extends TrustManagerFactorySpi implements TrustManagerFactory {
	//
	private TrustManager[] managers = new TrustManager[]{ new TrustAllManager() };
	
	/**
	 * 
	 */
	@Override
	public TrustManager[] create(Object id) {
		return this.managers;
	}
	
	/**
	 * 
	 */
	@Override
	protected void engineInit(KeyStore keystore)
	throws KeyStoreException {
	}
	
	@Override
	protected TrustManager[] engineGetTrustManagers() {
		return this.managers;
	}
	
	@Override
	protected void engineInit(ManagerFactoryParameters parameters)
	throws InvalidAlgorithmParameterException {
	}
	
	/**
	 * 
	 */
	public static class TrustAllManager implements X509TrustManager {
		
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
		
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}
		
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}
	}
}

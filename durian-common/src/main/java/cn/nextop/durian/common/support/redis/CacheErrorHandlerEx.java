package cn.nextop.durian.common.support.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * @author jonny
 */
public class CacheErrorHandlerEx implements CacheErrorHandler {
	
	private static final Logger log = LoggerFactory.getLogger(CacheErrorHandlerEx.class);
	
	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		log.error(exception.getMessage(), exception);
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
		log.error(exception.getMessage(), exception);
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		log.error(exception.getMessage(), exception);
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		log.error(exception.getMessage(), exception);
	}

}

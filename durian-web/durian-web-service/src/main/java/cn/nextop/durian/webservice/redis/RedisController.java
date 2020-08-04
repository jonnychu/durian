package cn.nextop.durian.webservice.redis;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.nextop.durian.common.support.redis.RedisUtil;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Resource
	private RedisUtil redisUtil;
	
	@RequestMapping("set")
	public boolean redisset(String key, String v1) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(Long.valueOf(1));
		userEntity.setGuid(String.valueOf(1));
		userEntity.setName("zhangsan");
		userEntity.setAge(String.valueOf(20));
		userEntity.setCreateTime(new Date());
		return redisUtil.set(key, userEntity, 60);
	}

	@RequestMapping("get")
	public Object redisget(String key) {
		return redisUtil.get(key);
	}

	@RequestMapping("expire")
	public boolean expire(String key) {
		return redisUtil.expire(key, 60);
	}
}
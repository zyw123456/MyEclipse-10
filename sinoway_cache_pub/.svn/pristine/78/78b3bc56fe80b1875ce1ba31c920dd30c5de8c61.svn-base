import com.sinoway.cache.ICache;
import com.sinoway.cache.redis.RedisClusterImpl;


public class Redis {
	public static void main(String[] args) {
		ICache icache = new RedisClusterImpl();
		String json="xsss";
		icache.save("testString", json);
		Object object = icache.get("testString");
		System.out.println(object);
	}
}

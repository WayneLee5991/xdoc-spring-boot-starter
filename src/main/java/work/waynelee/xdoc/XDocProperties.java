/**
 * 
 */
package work.waynelee.xdoc;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lwq
 * 2019年4月11日 下午2:40:04
 */
@ConfigurationProperties(prefix="java.xdoc")
public class XDocProperties {
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}

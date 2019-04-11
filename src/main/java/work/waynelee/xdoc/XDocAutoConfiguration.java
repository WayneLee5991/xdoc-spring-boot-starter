/**
 * 
 */
package work.waynelee.xdoc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 李文庆
 * 2019年4月11日 下午2:42:46
 */
@Configuration
@EnableConfigurationProperties({XDocProperties.class})
public class XDocAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public XDocProperties xDocProperties(){
		return new XDocProperties();
	}
	
	@Bean
	@ConditionalOnMissingBean(value = XDocClient.class)
	public XDocClient xDocClient(){
		
		return new XDocClient();
	}
	
}

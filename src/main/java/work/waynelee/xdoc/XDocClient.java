/**
 * 
 */
package work.waynelee.xdoc;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 文档工具类，暂时只提供转换和预览，其他功能请参考本类中的使用方法调用 {@link XDocService}
 * @author lwq <br>
 * 2019年4月11日 下午2:46:29
 */
@Component
public class XDocClient {

	@Autowired
	private XDocProperties xDocProperties;
	private static XDocProperties properties;
	
	@PostConstruct
	private void init(){
		properties = this.xDocProperties;
	}
	
	/**
	 * 文档类型转换
	 * @param xdoc 文档地址，可以是本地文档，例如：F:/a.docx<br>
	 * 		                    也可以是服务器上的文档，以http或https开始
	 * @param out
	 * @param format
	 */
	public static void convert(String xdoc,Object out,FormatEnum format){
		exe(xdoc, out, format);
	}
	
	/**
	 * 文档类型转换
	 * @param xdoc 文档地址，可以是本地文档，例如：F:/a.docx<br>
	 * 			        也可以是服务器上的文档，以http或https开始
	 * @param file 保存至本地文件
	 */
	public static void convert(String xdoc,File file){
		
		String key = properties.getKey();
		if (StringUtils.isEmpty(key)) {
			throw new RuntimeException("请配置 xdoc key!");
		}
		XDocService service = new XDocService(key);
		
		try {
			service.to(xdoc, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * office文档预览
	 * @param xdoc 文档地址，可以是本地文档，例如：F:/a.docx<br>
	 * 				也可以是服务器上的文档，以http或https开始
	 * @param out out 输出目标，OutputStream 或 HttpServletResponse，推荐使用HttpServletResponse直接返回页面
	 * @param format 预览的类型，推荐使用pdf和html
	 */
	public static void preview(String xdoc,Object out,FormatEnum format){
		exe(xdoc, out, format);
	}
	
	private static void exe(String xdoc,Object out,FormatEnum format){
		
		String key = properties.getKey();
		if (StringUtils.isEmpty(key)) {
			throw new RuntimeException("请配置 xdoc key!");
		}
		XDocService service = new XDocService(key);
		
		try {
			service.to(xdoc,out,format.getFormat());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public enum FormatEnum{
		
		FLASH("flash"),
		PDF("pdf"),
		//(数字签名PDF)
		SPDF("s.pdf"),
		DOCX("docx"),
		EPUB("epub"),
		JAR("jar"),
		SVG("svg"),
		SWF("swf"),
		HTML("html"),
		TXT("text"),
		PNG("png"),
		JPG("jpg"),
		GIF("gif");
		
		private String format;
		
		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		FormatEnum(String format) {
			this.format = format;
		}
		
	}
	
}

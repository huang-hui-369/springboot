```
@Configuration
public class MyWebAppConfiguration implements WebMvcConfigurer {
	
	@Value("${docpath.base}")
    private String basePath;

	//定制资源映射
   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //意思是：url中读取到/upload时，就会自动将/upload解析成D:/idea/java_workspace/image/upload
        registry.addResourceHandler("/doc/**").addResourceLocations(basePath);
        /**
         * Linux系统
         * registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/image/upload/");
         */
    }
}
```
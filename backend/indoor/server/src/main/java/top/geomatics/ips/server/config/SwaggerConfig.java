package top.geomatics.ips.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chenfa
 * Swagger配置类
 */
@Configuration
@EnableSwagger2
//@EnableWebMvc
//@ComponentScan(basePackages = {"top.geomatics.ips.server.controller"})
public class SwaggerConfig {

	@Bean
	public Docket controllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("top.geomatics.ips.server.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo(){
		Contact contact = new Contact("dyj","http://www.geomatics.top","397095092@qq.com");
		return new ApiInfoBuilder()
				.title("室内定位Rest服务接口文档")
				.version("1.0.0")
				.description("API接口")
				.contact(contact)
				.build();
	}

}

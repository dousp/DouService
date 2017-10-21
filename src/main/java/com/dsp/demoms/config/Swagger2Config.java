package com.dsp.demoms.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket apiAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    @Bean
    public Docket apiProject() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("project")
                .apiInfo(apiInfo())
                // .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dsp.demoms"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket apiActuator() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("SpringBoot-Actuator")//分组名称
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.or(PathSelectors.regex("/mgmt/.*")))
                .build();
    }

    @Bean
    public Docket testDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API for test")//分组名称
                .select()
                .paths(Predicates.or(PathSelectors.regex("/hello/.*")))//指定路径处理PathSelectors.any()代表所有的
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket testDocket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API for test2")//分组名称
                .select()
                .paths(Predicates.or(PathSelectors.regex("/word/.*")))//指定路径处理PathSelectors.any()代表所有的
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("Spring Boot学习项目")
                .termsOfServiceUrl("http://xxx.com/")
                .contact(new Contact("Dsp","url","douspeng@sina.cn"))
                .version("1.0")
                .build();
    }

}

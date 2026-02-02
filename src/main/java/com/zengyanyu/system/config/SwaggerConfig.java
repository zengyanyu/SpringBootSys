package com.zengyanyu.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 调试工具中的文字默认显示为中文，操作更简单，界面更清晰
 * 访问地址：http://localhost:8083/doc.html
 *
 * @author zengyanyu
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("所有API").pathMapping("/")
                .select()
                // API基础扫描路径
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getGlobalParameters());// 添加全局参数
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-api文档")
                .description("swagger-api文档 描述")
                .version("1.0.0")
                .contact(new Contact("xxxxxx", "xxxxxxxx", ""))
                .build();
    }

    /**
     * 添加Swagger的全局参数
     */
    private List<Parameter> getGlobalParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("Authorization") // 请求头的名称
                .description("Token") // 描述信息
                .modelRef(new ModelRef("string")) // 类型，这里是string类型
                .parameterType("header") // 参数类型，这里是header类型
                .required(false) // 是否必填，这里设置为false，表示非必填，可以根据实际需求设置
                .build());
        return parameters;
    }

    /**
     * 解决Spring Boot和Swagger冲突的配置（项目启动不正常）
     * 处理方案一：spring.mvc.pathmatch.matching-strategy=ant_path_matcher
     * 处理方案二：springfoxHandlerProviderBeanPostProcessor()
     * org.springframework.context.ApplicationContextException: Failed to start bean
     * 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException
     *
     * @return
     */
//    @Bean
//    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private void customizeSpringfoxHandlerMappings(List<RequestMappingInfoHandlerMapping> mappings) {
//                List<RequestMappingInfoHandlerMapping> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }

}

package com.yj.config;

import com.yj.constants.SystemConstants;
import com.yj.enums.AppHttpCodeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class Knife4jConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {


        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("vivo50众筹平台")
                        .description("还在为星期四要不到50而烦恼吗? 快来使用vivo50众筹平台! 这次一定能吃上! XD")
                        // .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("亦瑾", "null", "yj1425840290@gmail.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("all")
                .select()
                //指定Controller扫描路径。可以不具体到controller，它会扫描指定路径下的所有
                .apis(RequestHandlerSelectors.basePackage("com.yj.controller"))
                .paths(PathSelectors.any())
                .build();

        //添加全局响应状态码
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Arrays.stream(AppHttpCodeEnum.values()).forEach(errorEnum -> {
            responseMessageList.add(
                    new ResponseMessageBuilder().code(errorEnum.getCode()).message(errorEnum.getMsg()).responseModel(
                            new ModelRef(errorEnum.getMsg())).build()
            );
        });
        docket
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList);
        return docket;
    }
}
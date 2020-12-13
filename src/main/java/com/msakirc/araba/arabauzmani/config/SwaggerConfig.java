package com.msakirc.araba.arabauzmani.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                // .securityContexts(Collections.singletonList(securityContext()))
                // .securitySchemes(Arrays.asList(securitySchema()))
                .apiInfo(apiInfo());
    }

    // private OAuth securitySchema() {
    //
    //     List<AuthorizationScope> authorizationScopeList = new ArrayList();
    //     authorizationScopeList.add(new AuthorizationScope("read", "read all"));
    //     authorizationScopeList.add(new AuthorizationScope("write", "access all"));
    //
    //     List<GrantType> grantTypes = new ArrayList();
    //     GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant("/oauth/token");
    //     grantTypes.add(passwordCredentialsGrant);
    //
    //     return new OAuth("oauth2", authorizationScopeList, grantTypes);
    // }

    // private SecurityContext securityContext() {
    //     return SecurityContext.builder().securityReferences(defaultAuth()).build();
    // }

    // private List<SecurityReference> defaultAuth() {
    //
    //     final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
    //     authorizationScopes[0] = new AuthorizationScope("read", "read all");
    //     authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
    //     authorizationScopes[2] = new AuthorizationScope("write", "write all");
    //
    //     return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    // }

    // private List<AuthorizationScope> scopes() {
    //     List<AuthorizationScope> list = new ArrayList();
    //     list.add(new AuthorizationScope("read_scope", "Grants read access"));
    //     list.add(new AuthorizationScope("write_scope", "Grants write access"));
    //     list.add(new AuthorizationScope("admin_scope", "Grants read write and delete access"));
    //     return list;
    // }

    private ApiInfo apiInfo() {
        final Contact contact = new Contact("Araba Uzmanı", "arabauzmanı.com", "support@arabauzmanı.com");
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Araba UzmanıAPI")
                .description("Araba Uzmanıp Backend APIs.")
                .version("1.0.0")
                .contact(contact)
                .license("Terms of service")
                .licenseUrl("http://www.arabauzmanı.com").build();
        return apiInfo;
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(true)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    // @Bean
    // public SecurityConfiguration security() {
    //     return new SecurityConfiguration("", "", "", "", "Bearer access token", ApiKeyVehicle.HEADER,
    //             HttpHeaders.AUTHORIZATION, "");
    // }

}



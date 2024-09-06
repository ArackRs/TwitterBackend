package dev.arack.enlace.shared.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "Demo - Twitter API Backend",
                description = "This API provides endpoints for user authentication, post management, and following functionality, modeled after Twitter.",
                termsOfService = "https://twitterapi.example.com/terms_and_conditions",
                version = "1.0.0",
                contact = @Contact(name = "Jack Arana Ramos", url = "https://website-arack.web.app/", email = "arack.rs@gmail.com"),
                license = @License(name = "Standard Software Use License for TwitterAPI", url = "https://twitterapi.example.com/license")
        ),
        servers = {
                @Server(description = "Development Server", url = "http://localhost:8080"),
                @Server(description = "Production Server", url = "https://t-enlace-dzhhhvgpc8d4gtfu.eastus-01.azurewebsites.net")
        },
        security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "JWT Bearer Token Authentication for accessing the API.",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {  }

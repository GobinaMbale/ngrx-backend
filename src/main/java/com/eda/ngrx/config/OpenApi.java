package com.eda.ngrx.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "EDA",
                        email = "eda@gmail.cm",
                        url = "https://www.eda.com"
                ),
                description = "OpenApi documentation for NGRX application",
                title = "OpenApi specification - NGRX",
                version = "1.1",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8085"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://www."
                )
        }
)
public class OpenApi {
}

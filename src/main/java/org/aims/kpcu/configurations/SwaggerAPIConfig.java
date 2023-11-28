package org.aims.kpcu.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerAPIConfig {

 
 //@Value("${http://217.160.150.140:8080}")
  //@Value("{http://127.0.0.1:8080}")
  //@Value("${http://127.0.0.1:8080}")
 @Value("${https://aims.us.to:8080}")
  private String devUrl;

    @Value("${http://127.0.0.1:8443}")
    private String local;

  

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("New KPCU AIMS API");

      Server localServer = new Server();
      localServer.setUrl(local);
      localServer.setDescription("Testing Locally");




      Contact contact = new Contact();

    contact.setName("Patrick");
    contact.email("ngupatgi@gmail.com");




   
    Info info = new Info()
        .title("Aims API(new)")
        .version("1.0")
        .contact(contact)
        .description("New KPCU AIMS API.This API exposes endpoints to upload data from local. it also facilitates applying of kpcu loans and their recovery");

    return new OpenAPI().info(info).servers(List.of(devServer,localServer));
  }
}
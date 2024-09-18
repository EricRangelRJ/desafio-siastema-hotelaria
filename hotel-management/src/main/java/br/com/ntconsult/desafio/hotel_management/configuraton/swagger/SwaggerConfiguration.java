package br.com.ntconsult.desafio.hotel_management.configuraton.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setName("Desafio NT Consult | Eric Rangel");
        contact.setUrl("https://ericrangelrj.notion.site/Desafio-NT-Consult-b91ecd4b5c1c4ff9abb2e1016e86e9b2");
        contact.setEmail("ericlsrangel@gmail.com");

        Info info = new Info()
                .title("Microservico de Gerenciamento de Hoteis")
                .contact(contact)
                .version("1.0")
                .description("Responsável por todas as operações relacionadas ao Hotel, incluindo a consultas de quartos, disponibilidades, cadastro de hoteis entre outros.");



        return new OpenAPI()
                .info(info);
    }
}

package br.com.desafio.customer_management.config;

import br.com.desafio.customer_management.models.entities.Customer;
import br.com.desafio.customer_management.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInicializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInicializer.class);

    @Bean
    CommandLineRunner initData(CustomerRepository repository) {

        logger.info("::: Iniciando carga na collection do mongo-db :::");
        return args -> {
            logger.info("::: Limpando dados antigos na collection do mongo-db :::");
            repository.deleteAll();
            // Carregando dados
            repository.save(new Customer("1abb4def-02a3-4733-90f9-25d24cf42ec1", "Eric Rangel", "ericlsrangel@gmail.com","5521965770649"));
            repository.save(new Customer("3d0b2734-5905-40fe-8f61-8e336022607a", "Bruna Rangel", "bruna@gmail.com","5521970138204"));
            logger.info("::: Finalizando carga na collection do mongo-db :::");
            // Mostrando todos os dados inseridos
            repository.findAll().forEach(System.out::println);
        };
    }

}

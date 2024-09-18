package br.com.ntconsult.desafio.review_management.config;

import br.com.ntconsult.desafio.review_management.models.entity.HotelCacheEntity;
import br.com.ntconsult.desafio.review_management.repository.HotelCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInicializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInicializer.class);

    @Bean
    CommandLineRunner initData(HotelCacheRepository repository) {

        return args -> {
            // Limpando dados antigos
            logger.info("::: Limpando a collection do mongo-db :::");
            repository.deleteAll();
            logger.info("::: Iniciando Carga na collection do mongo-db :::");

            // Inserindo novos dados
            repository.save(new HotelCacheEntity("ca341327-6986-477d-ac09-f024e1f4a792","Pousada Lago dos Ipês","CREATED"));
            repository.save(new HotelCacheEntity("b75ff717-62b6-4747-8ee5-c1086ee6a566"," Hotel Mar do Norte","CREATED"));
            repository.save(new HotelCacheEntity("392984c5-a5bc-45b5-b2fd-ef8541b416e7","Eco Resort Pantanal","CREATED"));
            repository.save(new HotelCacheEntity("7575687e-6f8e-48e5-b212-65af6fca8a23","Pousada Paraíso das Águas","CREATED"));
            repository.save(new HotelCacheEntity("b75ff717-62b6-4747-8ee5-c1086ee6a567","Hotel Mar do Sul","CREATED"));

            logger.info("::: Finalizando Carga na collection do mongo-db :::");
            // Mostrando todos os dados inseridos
            repository.findAll().forEach(System.out::println);
        };
    }

}

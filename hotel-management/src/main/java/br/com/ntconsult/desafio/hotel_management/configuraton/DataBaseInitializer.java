package br.com.ntconsult.desafio.hotel_management.configuraton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class DataBaseInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseInitializer.class);

    @Bean
    public CommandLineRunner initDatabase(DataSource dataSource) {
        logger.info("::: Iniciando carga nas tabelas :::");
         return args -> {

            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {

                logger.info("::: Iniciando carga na tabela de hoteis :::");
                statement.execute("INSERT INTO public.hotel (id,amenities,description,location,name,rating) VALUES" +
                        " ('ca341327-6986-477d-ac09-f024e1f4a792'::uuid,'Café da manhã, Estacionamento gratuito, Wi-Fi gratuito, Área verde','Pousada charmosa e tranquila, próxima às montanhas de Minas Gerais.','Belo Horizonte, Minas Gerais','Pousada Lago dos Ipês',9.8)," +
                        " ('b75ff717-62b6-4747-8ee5-c1086ee6a566'::uuid,'Piscina, Restaurante, Serviço de quarto, Estacionamento gratuito','Hotel à beira-mar com vista para as dunas e as águas cristalinas de Natal.','Natal, Rio Grande do Norte','Hotel Mar do Norte',9.7)," +
                        " ('392984c5-a5bc-45b5-b2fd-ef8541b416e7'::uuid,'Passeios de barco, Piscina, Restaurante, Trilhas ecológicas','Resort ecológico no coração do Pantanal, perfeito para amantes da natureza.','Corumbá, Mato Grosso do Sul','Eco Resort Pantanal',9.7)," +
                        " ('7575687e-6f8e-48e5-b212-65af6fca8a23'::uuid,'Café da manhã incluso, Piscina, Wi-Fi gratuito, Estacionamento','Pousada acolhedora a poucos metros das praias de águas cristalinas.','Maceió, Alagoas','Pousada Paraíso das Águas',8.0)," +
                        " ('b75ff717-62b6-4747-8ee5-c1086ee6a567'::uuid,'Piscina, Restaurante, Serviço de quarto, Estacionamento gratuito','Hotel à beira-mar com vista para as dunas e as águas cristalinas de Natal.','Natal Rio Grande do Norte','Hotel Mar do Norte',10.0);");

                logger.info("::: Iniciando carga na tabela de quartos :::");
                statement.execute("INSERT INTO public.room (id,amenities,number_of_guests,price_per_night,type,hotel_id) VALUES" +
                        " ('545ed316-b9f1-4779-b685-a81ae10670f5'::uuid,'Free Wi-Fi, Fan, Mini Fridge, Hair Dryer',4,180.00,'Superior Room','ca341327-6986-477d-ac09-f024e1f4a792'::uuid)," +
                        " ('545ed316-b9f1-4779-b685-a81ae10670f7'::uuid,'Free Wi-Fi, Air Conditioning, Mini Bar, Jacuzzi, Safe',4,180.00,'Superior Room','ca341327-6986-477d-ac09-f024e1f4a792'::uuid)," +
                        " ('545ed316-b9f1-4779-b685-a81ae10670f8'::uuid,'Free Wi-Fi, Air Conditioning, Private Pool, Butler Service, Safe',4,500.00,'Presidential Suite','ca341327-6986-477d-ac09-f024e1f4a792'::uuid)," +
                        " ('545ed316-b9f1-4779-b685-a81ae10670f9'::uuid,'Free Wi-Fi, Air Conditioning, Mini Bar, Jacuzzi, Safe',2,300.00,'Executive Suite','ca341327-6986-477d-ac09-f024e1f4a792'::uuid)," +
                        " ('545ed316-b9f1-4779-b685-a81ae10670f6'::uuid,'Free Wi-Fi, Air Conditioning, Kitchenette, Flat Screen TV, Play Area',6,220.00,'Family Room','b75ff717-62b6-4747-8ee5-c1086ee6a567'::uuid)," +
                        " ('100ed316-b9f1-4779-b685-a81ae10670f5'::uuid,'Free Wi-Fi, Fan, Mini Fridge, Hair Dryer',4,180.00,'Superior Room','7575687e-6f8e-48e5-b212-65af6fca8a23'::uuid)," +
                        " ('101ed316-b9f1-4779-b685-a81ae10670f7'::uuid,'Free Wi-Fi, Air Conditioning, Mini Bar, Jacuzzi, Safe',4,180.00,'Superior Room','7575687e-6f8e-48e5-b212-65af6fca8a23'::uuid)," +
                        " ('102ed316-b9f1-4779-b685-a81ae10670f8'::uuid,'Free Wi-Fi, Air Conditioning, Private Pool, Butler Service, Safe',4,500.00,'Presidential Suite','7575687e-6f8e-48e5-b212-65af6fca8a23'::uuid)," +
                        " ('103ed316-b9f1-4779-b685-a81ae10670f9'::uuid,'Free Wi-Fi, Air Conditioning, Mini Bar, Jacuzzi, Safe',2,300.00,'Executive Suite','7575687e-6f8e-48e5-b212-65af6fca8a23'::uuid)," +
                        " ('104ed316-b9f1-4779-b685-a81ae10670f6'::uuid,'Free Wi-Fi, Air Conditioning, Kitchenette, Flat Screen TV, Play Area',6,220.00,'Family Room','7575687e-6f8e-48e5-b212-65af6fca8a23'::uuid);" +
                        "INSERT INTO public.room (id,amenities,number_of_guests,price_per_night,type,hotel_id) VALUES" +
                        " ('105ed316-b9f1-4779-b685-a81ae10670f9'::uuid,'Free Wi-Fi, Air Conditioning, Mini Bar, Jacuzzi, Safe',2,300.00,'Executive Suite','b75ff717-62b6-4747-8ee5-c1086ee6a566'::uuid)," +
                        " ('106ed316-b9f1-4779-b685-a81ae10670f6'::uuid,'Free Wi-Fi, Air Conditioning, Kitchenette, Flat Screen TV, Play Area',6,220.00,'Family Room','392984c5-a5bc-45b5-b2fd-ef8541b416e7'::uuid);");


                logger.info("::: Iniciando carga na tabela de disponibilidade de quartos :::");
                statement.execute("INSERT INTO public.room_availability (available,date,room_id) VALUES" +
                        " (true,'2024-08-10','545ed316-b9f1-4779-b685-a81ae10670f6'::uuid)," +
                        " (true,'2024-07-10','545ed316-b9f1-4779-b685-a81ae10670f7'::uuid)," +
                        " (true,'2024-06-10','545ed316-b9f1-4779-b685-a81ae10670f8'::uuid)," +
                        " (true,'2024-05-10','545ed316-b9f1-4779-b685-a81ae10670f9'::uuid)," +
                        " (true,'2024-09-11','545ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-03-12','545ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-02-13','545ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','545ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-09-10','545ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','545ed316-b9f1-4779-b685-a81ae10670f6'::uuid);" +
                        "INSERT INTO public.room_availability (available,date,room_id) VALUES" +
                        " (true,'2024-01-14','545ed316-b9f1-4779-b685-a81ae10670f6'::uuid)," +
                        " (true,'2024-01-14','545ed316-b9f1-4779-b685-a81ae10670f6'::uuid)," +
                        " (true,'2024-01-14','545ed316-b9f1-4779-b685-a81ae10670f6'::uuid)," +
                        " (true,'2024-01-14','545ed316-b9f1-4779-b685-a81ae10670f6'::uuid)," +
                        " (true,'2024-06-10','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-05-10','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-09-11','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-03-12','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-02-13','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid);" +
                        "INSERT INTO public.room_availability (available,date,room_id) VALUES" +
                        " (true,'2024-01-14','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','100ed316-b9f1-4779-b685-a81ae10670f5'::uuid)," +
                        " (true,'2024-01-14','103ed316-b9f1-4779-b685-a81ae10670f9'::uuid)," +
                        " (true,'2024-01-14','103ed316-b9f1-4779-b685-a81ae10670f9'::uuid)," +
                        " (true,'2024-01-14','101ed316-b9f1-4779-b685-a81ae10670f7'::uuid)," +
                        " (true,'2024-01-14','102ed316-b9f1-4779-b685-a81ae10670f8'::uuid)," +
                        " (true,'2024-01-14','104ed316-b9f1-4779-b685-a81ae10670f6'::uuid);" +
                        "INSERT INTO public.room_availability (available,date,room_id) VALUES" +
                        " (true,'2024-01-14','105ed316-b9f1-4779-b685-a81ae10670f9'::uuid)," +
                        " (true,'2024-01-14','106ed316-b9f1-4779-b685-a81ae10670f6'::uuid);");
            }
             logger.info("::: Finalizada carga nas tabelas :::");
        };

    }

}
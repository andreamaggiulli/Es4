package Esercizi.Es4.config;

import Esercizi.Es4.model.Carrello;
import Esercizi.Es4.model.Prodotto;
import Esercizi.Es4.repository.CarrelloRepository;
import Esercizi.Es4.repository.ProdottoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class Configurazione
{
    @Bean
    CommandLineRunner commandLineRunner(ProdottoRepository prodottoRepository, CarrelloRepository carrelloRepository)
    {
        return args -> {
            Carrello carrello1 = new Carrello("001");
            Carrello carrello2 = new Carrello("002");
            Carrello carrello3 = new Carrello(LocalDate.of(2020,05,30));
            Prodotto prodotto1 = new Prodotto("001","Mela",3.5);
            Prodotto prodotto2 = new Prodotto("002","Pera",2);
            Prodotto prodotto3 = new Prodotto("003","Prosciutto di parma",4.5);
            Prodotto prodotto4 = new Prodotto("004","Biscotti",3.75);


            prodottoRepository.deleteAll();

            carrelloRepository.deleteAll();

            prodottoRepository.saveAll(
                    List.of(prodotto1,prodotto2,prodotto3,prodotto4)
            );

            carrelloRepository.saveAll((List.of(carrello1,carrello2, carrello3)));
        };

    }
}

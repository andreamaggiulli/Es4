package Esercizi.Es4.repository;

import Esercizi.Es4.model.Carrello;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloRepository extends MongoRepository<Carrello, String>
{
    //Carrello findCarrelloById_carrello(Integer id_carrello);

    //@Query("{ 'autore' : ?0 }")
    //List<Libro> findLibriByAutore(String autore);

    //@Query("{ 'anno' : ?0 }")
    //List<Carrello> findCarrelloByAnno(Integer anno);

    //void deleteCarrelloById(Integer id_carrello);
}

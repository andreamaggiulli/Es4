package Esercizi.Es4.repository;

import Esercizi.Es4.model.Prodotto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends MongoRepository<Prodotto, String>
{
    //Prodotto findProdottoById(Integer id_prodotto);
    Prodotto findProdottoByNome(String nome);
    //void deleteProdottoById(Integer id_prodotto);
}

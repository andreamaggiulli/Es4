package Esercizi.Es4.services;

import Esercizi.Es4.model.Carrello;
import Esercizi.Es4.model.NotaSpesa;
import Esercizi.Es4.model.Prodotto;

import java.util.List;

public interface Servizio
{
    List<Prodotto> getProdotti();
    List<Carrello> getCarrelli();
    List<Carrello> getCarrelliByAnno(Integer anno);
    Carrello getCarrelloById(String id_carrello);
    Prodotto getProdottoById(String id_prodotto);
    Prodotto getProdottoByNome(String nome);
    void saveCarrello(Carrello carrello);
    void saveProdotto(Prodotto prodotto);
    void updateCarrello(Carrello carrello, NotaSpesa notaSpesa);
    void updateProdotto(Prodotto prodotto);
    void deleteCarrello(String id_carrello);
    void deleteProdotto(String id_prodotto);
    //void insertIntoCarrello(String id_carrello, Prodotto prodotto);
}

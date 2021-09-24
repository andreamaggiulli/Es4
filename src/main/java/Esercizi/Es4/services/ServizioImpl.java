package Esercizi.Es4.services;

import Esercizi.Es4.model.Bilancio;
import Esercizi.Es4.model.Carrello;
import Esercizi.Es4.model.NotaSpesa;
import Esercizi.Es4.model.Prodotto;
import Esercizi.Es4.repository.CarrelloRepository;
import Esercizi.Es4.repository.ProdottoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServizioImpl implements Servizio
{
    private final CarrelloRepository carrelloRepository;
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public ServizioImpl(CarrelloRepository carrelloRepository, ProdottoRepository prodottoRepository)
    {
        this.carrelloRepository = carrelloRepository;
        this.prodottoRepository = prodottoRepository;
    }

    @Override
    public List<Prodotto> getProdotti()
    {
        return prodottoRepository.findAll();
    }

    @Override
    public List<Carrello> getCarrelli()
    {
        return carrelloRepository.findAll();
    }

    @Override
    public List<Carrello> getCarrelliByAnno(Integer anno)
    {
        List<Carrello> temp = new ArrayList<Carrello>();
        for (Carrello c : getCarrelli())
        {
            if (c.getData().getYear() == anno)
            {
                temp.add(c);
            }
        }
        return temp;
    }

    @Override
    public Carrello getCarrelloById(String id_carrello)
    {
        return carrelloRepository.findById(id_carrello).get();
    }

    @Override
    public Prodotto getProdottoById(String id_prodotto)
    {
        return prodottoRepository.findById(id_prodotto).get();
    }

    @Override
    public Prodotto getProdottoByNome(String nome)
    {
        return prodottoRepository.findProdottoByNome(nome);
    }

    @Override
    public void saveCarrello(Carrello carrello)
    {
        carrelloRepository.save(carrello);
    }

    @Override
    public void saveProdotto(Prodotto prodotto)
    {
        prodottoRepository.save(prodotto);
    }

    @Override
    public void updateCarrello(Carrello carrello, NotaSpesa notaSpesa)
    {
        carrello.getLista().add(notaSpesa);
        calcolaTotale(carrello);
        deleteCarrello(carrello.getId_carrello());
        saveCarrello(carrello);
    }

    @Override
    public void updateProdotto(Prodotto prodotto)
    {
        prodotto.setId_prodotto(prodottoRepository.findProdottoByNome(prodotto.getNome()).getId_prodotto());
        prodottoRepository.deleteById(prodottoRepository.findProdottoByNome(prodotto.getNome()).getId_prodotto());
        prodottoRepository.save(prodotto);
    }

    @Override
    public void deleteCarrello(String id_carrello)
    {
        carrelloRepository.deleteById(id_carrello);
    }

    @Override
    public void deleteProdotto(String id_prodotto)
    {
        prodottoRepository.deleteById(id_prodotto);
    }

    public void calcolaTotale (Carrello carrello)
    {
        List<NotaSpesa> temp = carrello.getLista();
        carrello.setTotale(temp.stream().mapToDouble(p -> p.getQuantita() * (prodottoRepository.findProdottoByNome(p.getNome_prodotto()).getPrezzo())).sum());
    }

    public Bilancio calcolaBilancio(Integer anno)
    {
        List<Carrello> lista_bilancio = getCarrelliByAnno(anno);
        double tot = 0;
        for (Carrello c : lista_bilancio)
        {
            tot+=c.getTotale();
        }
        Bilancio bilancio = new Bilancio(lista_bilancio.size(),tot);
        return bilancio;
    }

    //@Override
    //public void insertIntoCarrello(String id_carrello, Prodotto prodotto)
    //{
        //if(!(carrelloRepository.findById(id_carrello) == null))
        //{
            //carrelloRepository.findById(id_carrello).get().getLista().put(prodotto, (int) (Math.random()*10));
            //carrelloRepository.findById(id_carrello).get().calcolaTotale();
        //}
        //else
        //{
            //log.error("Carrello non esistente");
        //}
    //}
}

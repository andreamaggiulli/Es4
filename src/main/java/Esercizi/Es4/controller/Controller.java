package Esercizi.Es4.controller;

import Esercizi.Es4.model.Bilancio;
import Esercizi.Es4.model.Carrello;
import Esercizi.Es4.model.NotaSpesa;
import Esercizi.Es4.model.Prodotto;
import Esercizi.Es4.services.Servizio;
import Esercizi.Es4.services.ServizioImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/supermercato")
public class Controller
{
    private final ServizioImpl servizio;

    @Autowired
    public Controller(ServizioImpl servizio)
    {
        this.servizio = servizio;
    }

    @GetMapping("/carrelli")
    public ResponseEntity<List<Carrello>> getCarrelli()
    {
        return ResponseEntity.ok().body(servizio.getCarrelli());
    }

    @GetMapping("/prodotti")
    public ResponseEntity<List<Prodotto>> getProdotti()
    {
        return ResponseEntity.ok().body(servizio.getProdotti());
    }

    @GetMapping("/carrelli/{id_carrello}")
    public ResponseEntity<Carrello> getCarrelloById(@PathVariable String id_carrello)
    {
        return ResponseEntity.ok().body(servizio.getCarrelloById(id_carrello));
    }

    @GetMapping("/prodotti/{id_prodotto}")
    public ResponseEntity<Prodotto> getProdottoById(@PathVariable String id_prodotto)
    {
        return ResponseEntity.ok().body(servizio.getProdottoById(id_prodotto));
    }

    @PostMapping("/prodotti")
    public ResponseEntity<Prodotto> addProdotto(@RequestBody Prodotto prodotto)
    {
        servizio.saveProdotto(prodotto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/prodotti/ricerca/" +prodotto.getNome()).toUriString());
        log.info("Prodotto {} salvato all'interno del database raggiungibile al link {} ",prodotto.getNome(), uri.toString());
        return ResponseEntity.created(uri).body(prodotto);
    }

    @PostMapping("/carrelli")
    public ResponseEntity<Carrello> addElement(@RequestBody NotaSpesa notaSpesa)
    {
        Prodotto p = servizio.getProdottoByNome(notaSpesa.getNome_prodotto());
        Carrello carrello = new Carrello();
        carrello.getLista().add(notaSpesa);
        servizio.calcolaTotale(carrello);
        servizio.saveCarrello(carrello);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/carrelli/ricerca/" +carrello.getId_carrello()).toUriString());
        log.info("Carrello {} salvato all'interno del database raggiungibile al link {} ",carrello.getId_carrello(), uri.toString());
        return ResponseEntity.created(uri).body(carrello);
    }

    @PutMapping("/carrelli/addNewNota/{id_carrello}")
    public ResponseEntity<Carrello> addNota (@PathVariable String id_carrello, @RequestBody NotaSpesa notaSpesa)
    {
        Carrello c = servizio.getCarrelloById(id_carrello);
        if(c==null)
        {
            log.error("carrello non presente");
        }
        else
        {
            servizio.updateCarrello(c, notaSpesa);
        }
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/prodotti/ricerca/{nome}")
    public ResponseEntity<Prodotto> getProdottiByNome(@PathVariable String nome){
        return ResponseEntity.ok().body(servizio.getProdottoByNome(nome));
    }

    @GetMapping("/carrelli/ricercaAnno")
    public ResponseEntity<Bilancio> getCarrelliByAnno(@RequestParam Integer anno)
    {
        Bilancio bilancio = servizio.calcolaBilancio(anno);
        return ResponseEntity.ok().body(bilancio);
    }


}

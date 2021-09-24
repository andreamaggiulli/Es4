package Esercizi.Es4.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class NotaSpesa
{
    String nome_prodotto;
    Integer quantita;

    public NotaSpesa(String nome_prodotto, Integer quantita)
    {
        this.nome_prodotto = nome_prodotto;
        this.quantita = quantita;
    }

}

package Esercizi.Es4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Prodotto")
public class Prodotto
{
    @Id
    String id_prodotto;
    static Integer contatore = 0;
    String nome;
    double prezzo;

    public Prodotto(String id_prodotto, String nome, double prezzo)
    {
        this.id_prodotto = id_prodotto;
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public Prodotto(String nome, double prezzo)
    {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public Prodotto()
    {

    }

}

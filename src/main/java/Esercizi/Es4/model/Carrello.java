package Esercizi.Es4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Data
@Document(collection = "Carrello")
@AllArgsConstructor
public class Carrello
{
    @Id
    String id_carrello;
    List<NotaSpesa> lista = new ArrayList<NotaSpesa>();
    double totale;
    LocalDate data;

    public Carrello(String id_carrello)
    {
        this.id_carrello = id_carrello;
        this.data = LocalDate.now();
    }

    public Carrello(String id_carrello, List<NotaSpesa> lista)
    {
        this.id_carrello = id_carrello;
        this.lista = lista;
        this.data = LocalDate.now();
    }

    public Carrello(List<NotaSpesa> lista)
    {
        this.lista = lista;
        this.data = LocalDate.now();
    }

    public Carrello()
    {
        this.data = LocalDate.now();
    }

    public Carrello(LocalDate data)
    {
        this.data = data;
    }
}

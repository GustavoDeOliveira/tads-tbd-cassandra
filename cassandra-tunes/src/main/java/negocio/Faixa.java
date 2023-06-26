package negocio;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "tunes", name = "faixa")
public class Faixa {

    @PartitionKey
    private long id;
    private String titulo;
    private String autor;
    private LocalDate publicadoEm;
    private long reproducoes;

    public Faixa() {
    }

    public Faixa(String titulo, String autor, LocalDate publicadoEm) {
        this.titulo = titulo;
        this.autor = autor;
        this.publicadoEm = publicadoEm;
        this.reproducoes = 0;
    }

    @PartitionKey
    public long getId() {
        return this.id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getPublicadoEm() {
        return publicadoEm;
    }

    public void setPublicadoEm(LocalDate nascimento) {
        this.publicadoEm = nascimento;
    }

    public long getReproducoes() {
        return reproducoes;
    }

    public void setReproducoes(long reproducoes) {
        this.reproducoes = reproducoes;
    }

    @Override
    public String toString() {
        return "Faixa [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", publicadoEm=" + publicadoEm + ", reproducoes="
                + reproducoes + "]";
    }
}

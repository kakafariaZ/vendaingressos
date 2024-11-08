/*******************************************************************************
 * Autor: Kauan Caio de Arruda Farias
 * Componente Curricular: Algoritmos II
 * Concluido em: 27/10/2024
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a
 * fonte do código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação.
 ******************************************************************************************/
package vendaingressos;

import java.util.Objects;
import java.io.Serializable;
import com.google.gson.annotations.Expose;

/**
 * Classe que representa um ingresso de um evento.
 */
public class Ingresso implements Serializable {
    private Evento evento; // Evento associado ao ingresso
    private double preco; // Preço do ingresso
    private String assento; // Assento associado ao ingresso
    @Expose private boolean ativo; // Indica se o ingresso está ativo

    /**
     * Construtor da classe Ingresso.
     *
     * @param evento O evento associado ao ingresso.
     * @param preco O preço do ingresso.
     * @param assento O assento associado ao ingresso.
     */
    public Ingresso(Evento evento, double preco, String assento) {
        this.evento = evento;
        this.preco = preco;
        this.assento = assento;
        this.ativo = true; // O ingresso é ativo ao ser criado
    }

    /**
     * Obtém o evento associado ao ingresso.
     *
     * @return O evento associado.
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Obtém o preço do ingresso.
     *
     * @return O preço do ingresso.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Obtém o assento associado ao ingresso.
     *
     * @return O assento do ingresso.
     */
    public String getAssento() {
        return assento;
    }

    /**
     * Verifica se o ingresso está ativo.
     *
     * @return true se o ingresso estiver ativo, false caso contrário.
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * Define o estado ativo do ingresso.
     *
     * @param ativo O novo estado do ingresso.
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * Cancela o ingresso, tornando-o inativo, se o evento estiver ativo.
     *
     * @return true se o cancelamento for bem-sucedido, false caso contrário.
     */
    public boolean cancelar() {
        if (evento.isAtivo()) {
            this.ativo = false; // O ingresso é cancelado
            return true;
        }
        return false; // Cancelamento não permitido
    }

    /**
     * Reativa o ingresso, se o evento estiver ativo.
     */
    public void reativar() {
        if (evento.isAtivo()) {
            this.ativo = true; // O ingresso é reativado
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingresso ingresso = (Ingresso) obj;
        return Double.compare(ingresso.preco, preco) == 0 &&
                Objects.equals(evento, ingresso.evento) &&
                Objects.equals(assento, ingresso.assento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evento, preco, assento);
    }
}

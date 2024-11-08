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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;

/**
 * Classe que representa um evento no sistema de venda de ingressos.
 */
public class Evento implements Serializable {
    private String nome; // Nome do evento
    @Expose private String descricao; // Descrição do evento
    private Date data; // Data do evento
    @Expose private List<String> assentosDisponiveis = new ArrayList<>(); // Lista de assentos disponíveis

    /**
     * Construtor da classe Evento.
     *
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     */
    public Evento(String nome, String descricao, Date data) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

    /**
     * Obtém o nome do evento.
     *
     * @return O nome do evento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a descrição do evento.
     *
     * @return A descrição do evento.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém a data do evento.
     *
     * @return A data do evento.
     */
    public Date getData() {
        return data;
    }

    /**
     * Adiciona um assento à lista de assentos disponíveis.
     *
     * @param assento O assento a ser adicionado.
     */
    public void adicionarAssento(String assento) {
        assentosDisponiveis.add(assento);
    }

    /**
     * Remove um assento da lista de assentos disponíveis.
     *
     * @param assento O assento a ser removido.
     */
    public void removerAssento(String assento) {
        assentosDisponiveis.remove(assento);
    }

    /**
     * Obtém a lista de assentos disponíveis.
     *
     * @return Uma nova lista contendo os assentos disponíveis.
     */
    public List<String> getAssentosDisponiveis() {
        return new ArrayList<>(assentosDisponiveis);
    }

    /**
     * Verifica se o evento está ativo (ou seja, se a data do evento é futura).
     *
     * @return true se o evento está ativo, false caso contrário.
     */
    public boolean isAtivo() {
        Date hoje = new Date();
        return data.after(hoje);
    }
}

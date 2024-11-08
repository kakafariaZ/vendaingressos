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
import com.google.gson.annotations.Expose;

/**
 * Classe que representa um pagamento para a compra de ingressos.
 */
public class Pagamento implements Serializable {
    private String tipo; // Ex: "Cartão de Crédito", "Transferência"
    @Expose private String numero; // Ex: Número do cartão ou conta
    private String titular; // Nome do titular do cartão ou conta
    @Expose private String validade; // Data de validade do cartão ou dados da conta

    /**
     * Construtor da classe Pagamento.
     *
     * @param tipo O tipo de pagamento (e.g., "Cartão de Crédito").
     * @param numero O número do cartão ou da conta.
     * @param titular O nome do titular do cartão ou conta.
     * @param validade A data de validade do cartão ou os dados da conta.
     */
    public Pagamento(String tipo, String numero, String titular, String validade) {
        this.tipo = tipo;
        this.numero = numero;
        this.titular = titular;
        this.validade = validade;
    }

    // Getters e Setters

    /**
     * Obtém o tipo de pagamento.
     *
     * @return O tipo de pagamento.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo de pagamento.
     *
     * @param tipo O tipo de pagamento a ser definido.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém o número do cartão ou da conta.
     *
     * @return O número do cartão ou da conta.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define o número do cartão ou da conta.
     *
     * @param numero O número do cartão ou da conta a ser definido.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtém o nome do titular do cartão ou conta.
     *
     * @return O nome do titular.
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Define o nome do titular do cartão ou conta.
     *
     * @param titular O nome do titular a ser definido.
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * Obtém a data de validade do cartão ou dados da conta.
     *
     * @return A data de validade ou os dados da conta.
     */
    public String getValidade() {
        return validade;
    }

    /**
     * Define a data de validade do cartão ou os dados da conta.
     *
     * @param validade A data de validade ou os dados da conta a serem definidos.
     */
    public void setValidade(String validade) {
        this.validade = validade;
    }
}

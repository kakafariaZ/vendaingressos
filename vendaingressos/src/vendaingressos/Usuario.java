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
import java.util.List;
import java.util.Objects;
import com.google.gson.annotations.Expose;

/**
 * Classe que representa um usuário do sistema de venda de ingressos.
 */
public class Usuario implements Serializable {
    private String login; // Nome de usuário para login
    @Expose private String senha; // Senha do usuário
    private String nome; // Nome completo do usuário
    private String cpf; // CPF do usuário
    private String email; // E-mail do usuário
    private boolean isAdmin; // Indica se o usuário é um administrador
    private List<Ingresso> ingressos; // Lista de ingressos do usuário
    private List<Pagamento> formasDePagamento; // Lista de formas de pagamento

    /**
     * Construtor da classe Usuario.
     *
     * @param login Nome de usuário para login.
     * @param senha Senha do usuário.
     * @param nome Nome completo do usuário.
     * @param cpf CPF do usuário.
     * @param email E-mail do usuário.
     * @param isAdmin Indica se o usuário é um administrador.
     */
    public Usuario(String login, String senha, String nome, String cpf, String email, boolean isAdmin) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = isAdmin;
        this.ingressos = new ArrayList<>();
        this.formasDePagamento = new ArrayList<>();
    }

    // Getters e Setters

    /**
     * Obtém o login do usuário.
     *
     * @return O login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha A nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome O novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o CPF do usuário.
     *
     * @return O CPF do usuário.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do usuário.
     *
     * @param cpf O novo CPF do usuário.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o e-mail do usuário.
     *
     * @return O e-mail do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     *
     * @param email O novo e-mail do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Verifica se o usuário é um administrador.
     *
     * @return true se o usuário é administrador, false caso contrário.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Adiciona um ingresso à lista de ingressos do usuário.
     *
     * @param ingresso O ingresso a ser adicionado.
     */
    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    /**
     * Cancela um ingresso da lista de ingressos do usuário.
     *
     * @param ingresso O ingresso a ser cancelado.
     * @return true se o ingresso foi cancelado, false caso contrário.
     */
    public boolean cancelarIngresso(Ingresso ingresso) {
        return ingressos.remove(ingresso);
    }

    /**
     * Remove um ingresso da lista de ingressos do usuário.
     *
     * @param ingresso O ingresso a ser removido.
     * @return true se o ingresso foi removido, false caso contrário.
     */
    public boolean removerIngresso(Ingresso ingresso) {
        return ingressos.remove(ingresso);
    }

    /**
     * Obtém a lista de ingressos do usuário.
     *
     * @return A lista de ingressos do usuário.
     */
    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    /**
     * Adiciona uma forma de pagamento à lista do usuário.
     *
     * @param pagamento A forma de pagamento a ser adicionada.
     */
    public void adicionarPagamento(Pagamento pagamento) {
        formasDePagamento.add(pagamento);
    }

    /**
     * Obtém a lista de formas de pagamento do usuário.
     *
     * @return A lista de formas de pagamento do usuário.
     */
    public List<Pagamento> getFormasDePagamento() {
        return formasDePagamento;
    }

    /**
     * Lista as formas de pagamento do usuário.
     *
     * @return A lista de formas de pagamento.
     */
    public List<Pagamento> listarPagamentos() {
        return this.formasDePagamento;
    }

    /**
     * Realiza o login do usuário verificando as credenciais.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return true se as credenciais estiverem corretas, false caso contrário.
     */
    public boolean login(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(cpf, usuario.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, cpf);
    }
}

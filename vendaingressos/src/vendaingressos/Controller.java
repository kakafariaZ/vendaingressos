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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.annotations.Expose;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Classe responsável pelo controle das operações relacionadas a usuários,
 * eventos, e compras de ingressos no sistema.
 */
public class Controller {
    private Map<String, Usuario> usuarios = new HashMap<>(); // Mapa de usuários
    private Map<String, Evento> eventos = new HashMap<>(); // Mapa de eventos
    private final Gson gson; // Objeto Gson para manipulação de JSON

    /**
     * Construtor da classe Controller.
     * Inicializa o Gson e carrega os dados de usuários e eventos do arquivo JSON.
     */
    public Controller() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        carregarDados();
    }

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param nome O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param email O e-mail do usuário.
     * @param isAdmin Indica se o usuário é um administrador.
     * @return O usuário cadastrado.
     */
    public Usuario cadastrarUsuario(String login, String senha, String nome, String cpf, String email, boolean isAdmin) {
        Usuario usuario = new Usuario(login, senha, nome, cpf, email, isAdmin);
        usuarios.put(login, usuario);
        salvarDados();
        return usuario;
    }

    /**
     * Edita os dados de um usuário existente.
     *
     * @param login O login do usuário a ser editado.
     * @param novoNome O novo nome do usuário.
     * @param novoEmail O novo e-mail do usuário.
     * @param novaSenha A nova senha do usuário.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    public void editarUsuario(String login, String novoNome, String novoEmail, String novaSenha) {
        Usuario usuario = usuarios.get(login);
        if (usuario != null) {
            usuario.setNome(novoNome);
            usuario.setEmail(novoEmail);
            usuario.setSenha(novaSenha);
            salvarDados();
        } else {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
    }

    /**
     * Obtém um usuário pelo seu login.
     *
     * @param login O login do usuário.
     * @return O usuário correspondente ao login, ou null se não encontrado.
     */
    public Usuario getUsuario(String login) {
        return usuarios.get(login); // Retorna o usuário se encontrado, ou null se não encontrado
    }

    /**
     * Obtém uma lista de todos os usuários cadastrados.
     *
     * @return Uma lista com todos os usuários.
     */
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios.values()); // Retorna todos os usuários
    }

    /**
     * Cadastra um novo evento no sistema.
     *
     * @param usuario O usuário que está cadastrando o evento.
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     * @return O evento cadastrado.
     * @throws SecurityException Se o usuário não for um administrador.
     */
    public Evento cadastrarEvento(Usuario usuario, String nome, String descricao, Date data) {
        if (!usuario.isAdmin()) {
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }

        Evento evento = new Evento(nome, descricao, data);
        eventos.put(nome, evento);
        salvarDados();
        return evento;
    }

    /**
     * Adiciona um assento a um evento existente.
     *
     * @param nomeEvento O nome do evento.
     * @param assento O assento a ser adicionado.
     */
    public void adicionarAssentoEvento(String nomeEvento, String assento) {
        Evento evento = eventos.get(nomeEvento);
        if (evento != null) {
            evento.adicionarAssento(assento);
            salvarDados();
        }
    }

    /**
     * Obtém um evento pelo seu nome.
     *
     * @param nome O nome do evento.
     * @return O evento correspondente ao nome, ou null se não encontrado.
     */
    public Evento getEvento(String nome) {
        return eventos.get(nome); // Retorna o evento se encontrado, ou null se não encontrado
    }

    /**
     * Adiciona uma forma de pagamento a um usuário.
     *
     * @param usuario O usuário que está adicionando a forma de pagamento.
     * @param tipo O tipo de pagamento (ex: cartão de crédito).
     * @param numero O número do cartão ou conta.
     * @param validade A validade do cartão.
     * @param codigoSeguranca O código de segurança do cartão.
     */
    public void adicionarFormaPagamento(Usuario usuario, String tipo, String numero, String validade, String codigoSeguranca) {
        Pagamento pagamento = new Pagamento(tipo, numero, validade, codigoSeguranca);
        usuario.adicionarPagamento(pagamento);
        salvarDados();
    }

    /**
     * Realiza a compra de um ingresso para um evento específico.
     *
     * @param usuario O usuário que está comprando o ingresso.
     * @param nomeEvento O nome do evento.
     * @param assento O assento a ser comprado.
     * @return O ingresso comprado.
     * @throws IllegalArgumentException Se o evento ou assento não estiver disponível.
     */
    public Ingresso comprarIngresso(Usuario usuario, String nomeEvento, String assento) {
        Evento evento = eventos.get(nomeEvento);
        if (evento != null && evento.getAssentosDisponiveis().contains(assento)) {
            Ingresso ingresso = new Ingresso(evento, 100.0, assento);  // preço fixo para o exemplo
            usuario.adicionarIngresso(ingresso);
            evento.removerAssento(assento);
            salvarDados();
            return ingresso;
        }
        throw new IllegalArgumentException("Evento ou assento indisponível.");
    }

    /**
     * Cancela a compra de um ingresso.
     *
     * @param usuario O usuário que deseja cancelar a compra.
     * @param ingresso O ingresso a ser cancelado.
     * @return true se a compra foi cancelada com sucesso, false caso contrário.
     */
    public boolean cancelarCompra(Usuario usuario, Ingresso ingresso) {
        if (usuario.getIngressos().contains(ingresso) && ingresso.cancelar()) {
            usuario.removerIngresso(ingresso);
            salvarDados();
            return true;
        }
        return false;
    }

    /**
     * Lista todos os eventos disponíveis.
     *
     * @return Uma lista de eventos disponíveis.
     */
    public List<Evento> listarEventosDisponiveis() {
        return new ArrayList<>(eventos.values());
    }

    /**
     * Lista todos os ingressos comprados por um usuário.
     *
     * @param usuario O usuário cujos ingressos serão listados.
     * @return Uma lista de ingressos comprados pelo usuário.
     */
    public List<Ingresso> listarIngressosComprados(Usuario usuario) {
        return usuario.getIngressos();
    }

    // Métodos para salvar e carregar dados em JSON

    /**
     * Salva os dados de usuários e eventos em arquivos JSON.
     */
    public void salvarDados() {
        try (FileWriter writerUsuarios = new FileWriter("usuarios.json");
             FileWriter writerEventos = new FileWriter("eventos.json")) {
            gson.toJson(usuarios, writerUsuarios);
            gson.toJson(eventos, writerEventos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    /**
     * Carrega os dados de usuários e eventos a partir de arquivos JSON.
     */
    private void carregarDados() {
        try (FileReader readerUsuarios = new FileReader("usuarios.json");
             FileReader readerEventos = new FileReader("eventos.json")) {

            Type tipoUsuarios = new TypeToken<Map<String, Usuario>>() {}.getType();
            Type tipoEventos = new TypeToken<Map<String, Evento>>() {}.getType();

            usuarios = gson.fromJson(readerUsuarios, tipoUsuarios);
            eventos = gson.fromJson(readerEventos, tipoEventos);

            if (usuarios == null) usuarios = new HashMap<>();
            if (eventos == null) eventos = new HashMap<>();

        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }

    /**
     * Limpa todos os usuários e eventos do sistema.
     * Após a limpeza, os dados são salvos.
     */
    public void limparDados() {
        usuarios.clear(); // Limpa todos os usuários
        eventos.clear();  // Limpa todos os eventos
        salvarDados();    // Salva as mudanças, se necessário
    }
}

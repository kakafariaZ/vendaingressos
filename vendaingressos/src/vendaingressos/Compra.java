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

/**
 * Classe que representa uma compra de ingresso.
 */
public class Compra {
    private Usuario usuario; // O usuário que realiza a compra
    private Ingresso ingresso; // O ingresso a ser comprado

    /**
     * Construtor da classe Compra.
     *
     * @param usuario O usuário que realizará a compra.
     * @param ingresso O ingresso que será comprado.
     */
    public Compra(Usuario usuario, Ingresso ingresso) {
        this.usuario = usuario;
        this.ingresso = ingresso;
    }

    /**
     * Realiza a compra do ingresso.
     *
     * Este método verifica se o ingresso está disponível (ativo).
     * Se o ingresso não estiver ativo, a compra não é realizada.
     * Caso contrário, o ingresso é adicionado à lista de ingressos do usuário,
     * e a sua disponibilidade é atualizada para inativa. Uma notificação de
     * compra bem-sucedida é enviada ao usuário.
     *
     * @return true se a compra for realizada com sucesso, false caso contrário.
     */
    public boolean realizarCompra() {
        if (!ingresso.isAtivo()) {
            System.out.println("Ingresso não disponível.");
            return false;
        }

        usuario.adicionarIngresso(ingresso);
        ingresso.setAtivo(false);

        // Envia a notificação de compra
        Notificacao.enviarNotificacao(usuario, "vendas@meuingresso.com",
                "Compra realizada com sucesso! Agradecemos e tenha um ótimo evento!");
        System.out.println("Compra realizada com sucesso!");
        return true;
    }
}

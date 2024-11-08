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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe responsável por enviar notificações aos usuários.
 */
public class Notificacao {
    /**
     * Envia uma notificação ao usuário e salva em um arquivo de texto.
     *
     * @param usuario O usuário ao qual a notificação será enviada.
     * @param remetente O remetente da mensagem.
     * @param mensagem O conteúdo da mensagem a ser enviada.
     */
    public static void enviarNotificacao(Usuario usuario, String remetente, String mensagem) {
        String destinatario = usuario.getEmail(); // Obtém o email do usuário
        String conteudo = "Para: " + destinatario + "\n" +
                "De: " + remetente + "\n" +
                "Mensagem: " + mensagem;

        // Salva a notificação em um arquivo .txt
        System.out.println("Tentando salvar notificação em 'notificacao_" + usuario.getLogin() + ".txt'");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notificacao_" + usuario.getLogin() + ".txt"))) {
            writer.write(conteudo);
            writer.newLine();
            System.out.println("Notificação salva com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a notificação: " + e.getMessage());
        }
    }
}

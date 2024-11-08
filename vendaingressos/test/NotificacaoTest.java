import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.io.File;
import vendaingressos.Notificacao;
import vendaingressos.Usuario;

public class NotificacaoTest {

    @Test
    public void testEnviarNotificacao() {
        // Dados para a notificação
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        String remetente = "vendas@meuingresso.com";
        String mensagem = "Compra realizada com sucesso. Muito obrigado e tenha um ótimo evento!";

        // Enviar notificação
        Notificacao.enviarNotificacao(usuario, remetente, mensagem);

        // Verificar se o arquivo foi criado
        File arquivo = new File("notificacao_" + usuario.getLogin() + ".txt"); // Alterado aqui
        assertTrue("Arquivo de notificação não foi criado.", arquivo.exists());

        // Limpeza (opcional) - Deletar o arquivo criado após o teste
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }

}

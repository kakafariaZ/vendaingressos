import vendaingressos.Controller;
import vendaingressos.Pagamento;
import vendaingressos.Usuario;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PagamentoTest {

    @Test
    public void testListarPagamentos() {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        // Adicionando algumas formas de pagamento
        controller.adicionarFormaPagamento(usuario, "Cartão de Crédito", "1234567890123456", "John Doe", "12/25");
        controller.adicionarFormaPagamento(usuario, "Transferência", "6543210987654321", "John Doe", "10/24");

        // Verificando se o método listarPagamentos() retorna a quantidade correta
        assertEquals(2, usuario.listarPagamentos().size());
    }
}

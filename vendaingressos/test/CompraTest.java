package vendaingressos;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Calendar; // Importando Calendar
import java.util.Date;     // Importando Date

public class CompraTest {

    @Test
    public void testRealizarCompra() {
        // Configurando a data do evento
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        // Criando usuário e ingresso
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Evento evento = new Evento("Show de Rock", "Banda XYZ", data);
        Ingresso ingresso = new Ingresso(evento, 100.0, "A1");

        // Criando a compra
        Compra compra = new Compra(usuario, ingresso);

        // Realizando a compra e verificando o resultado
        boolean sucesso = compra.realizarCompra();
        assertTrue(sucesso);
        assertFalse(ingresso.isAtivo()); // Verifica se o ingresso está marcado como não ativo
    }
}

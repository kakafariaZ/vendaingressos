import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vendaingressos.Controller;
import vendaingressos.Evento;
import vendaingressos.Usuario;
import vendaingressos.Ingresso;

public class ControllerTest {

    private Controller controller;
    private Usuario admin;
    private Usuario usuario;

    @Before
    public void setUp() {
        controller = new Controller();

        // Cadastrar usuários de teste
        admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
    }

    @After
    public void tearDown() {
        // Limpa os arquivos de JSON após cada teste (opcional)
        controller.limparDados();
    }

    @Test
    public void testPersistenciaCadastrarUsuario() {
        // Verifica se o usuário é salvo corretamente e recuperado após reiniciar o controller
        controller.salvarDados(); // Salva dados

        // Cria uma nova instância do Controller e carrega os dados persistidos
        Controller novoController = new Controller();
        Usuario usuarioPersistido = novoController.getUsuario("johndoe");

        assertNotNull(usuarioPersistido);
        assertEquals("johndoe", usuarioPersistido.getLogin());
        assertEquals("John Doe", usuarioPersistido.getNome());
    }

    @Test
    public void testPersistenciaCadastrarEvento() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        // Admin cadastra evento e salva
        Evento evento = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data);
        controller.salvarDados();

        // Recarrega o controller e verifica se o evento foi persistido
        Controller novoController = new Controller();
        Evento eventoPersistido = novoController.getEvento("Show de Rock");

        assertNotNull(eventoPersistido);
        assertEquals("Show de Rock", eventoPersistido.getNome());
        assertEquals("Banda XYZ", eventoPersistido.getDescricao());

        // Comparar os componentes da data
        Calendar calendarPersistido = Calendar.getInstance();
        calendarPersistido.setTime(eventoPersistido.getData());

        assertEquals(calendar.get(Calendar.YEAR), calendarPersistido.get(Calendar.YEAR));
        assertEquals(calendar.get(Calendar.MONTH), calendarPersistido.get(Calendar.MONTH));
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarPersistido.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testPersistenciaCompraIngresso() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data);
        controller.adicionarAssentoEvento("Show de Rock", "A1");

        // Usuário compra ingresso e salva
        controller.comprarIngresso(usuario, "Show de Rock", "A1");
        controller.salvarDados();

        // Recarrega o controller e verifica se o ingresso foi persistido
        Controller novoController = new Controller();
        List<Ingresso> ingressos = novoController.listarIngressosComprados(usuario);

        assertEquals(1, ingressos.size());
        assertEquals("Show de Rock", ingressos.get(0).getEvento().getNome());
        assertEquals("A1", ingressos.get(0).getAssento());
    }

    @Test
    public void testPersistenciaEditarUsuario() {
        // Edita o usuário e salva
        controller.editarUsuario("johndoe", "Jonathan Doe", "jonathan.doe@example.com", "novaSenha123");
        controller.salvarDados();

        // Recarrega o controller e verifica as mudanças
        Controller novoController = new Controller();
        Usuario usuarioEditado = novoController.getUsuario("johndoe");

        assertEquals("Jonathan Doe", usuarioEditado.getNome());
        assertEquals("jonathan.doe@example.com", usuarioEditado.getEmail());
        assertTrue(usuarioEditado.login("johndoe", "novaSenha123"));
    }
}

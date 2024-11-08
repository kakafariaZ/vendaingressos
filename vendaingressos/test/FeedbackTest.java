import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import vendaingressos.Feedback;
import vendaingressos.Evento;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class FeedbackTest {

    @Test
    public void testCriarFeedback() {
        // Criação da data do evento no passado
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.SEPTEMBER, 10);
        Date dataEvento = calendar.getTime();

        // Criação do evento e feedback
        Evento evento = new Evento("Show de Jazz", "Uma noite com os melhores músicos", dataEvento);
        Feedback feedback = new Feedback(evento, 4, "Ótimo evento!");

        // Validação da criação do feedback
        assertNotNull(feedback);
        assertEquals(4, feedback.getNota());
        assertEquals("Ótimo evento!", feedback.getComentario());
        assertEquals(evento, feedback.getEvento());
    }

    @Test
    public void testSalvarFeedbackJson() throws IOException {
        // Configuração de data para evento passado
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.SEPTEMBER, 10);
        Date dataEvento = calendar.getTime();

        // Criação de evento e feedback
        Evento evento = new Evento("Show de Rock", "Banda ABC ao vivo", dataEvento);
        Feedback feedback = new Feedback(evento, 5, "Incrível!");

        // Salvar feedback em arquivo JSON
        feedback.salvarFeedbackJson("feedback_test.json");

        // Leitura e validação do conteúdo salvo no JSON
        Gson gson = new Gson();
        try (Reader reader = new FileReader("feedback_test.json")) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertNotNull(jsonObject);
            assertEquals("Incrível!", jsonObject.get("comentario").getAsString());
            assertEquals(5, jsonObject.get("nota").getAsInt());
            assertEquals("Show de Rock", jsonObject.getAsJsonObject("evento").get("nome").getAsString());
        }
    }

    @Test
    public void testEventoNaoAvaliadoAntesDeTerminar() {
        // Configuração de data para evento futuro
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        Date dataEvento = calendar.getTime();

        Evento evento = new Evento("Show de Samba", "Músicos de Samba ao vivo", dataEvento);

        // Tentar criar feedback para evento futuro
        Feedback feedback = new Feedback(evento, 3, "Não gostei.");

        // Verificar se o feedback não deve ser permitido para evento futuro
        assertFalse(feedback.isEventoAvaliado());
    }
}

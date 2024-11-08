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
import vendaingressos.Evento;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe que representa o feedback de um evento, incluindo avaliação e comentários.
 */
public class Feedback {
    private Evento evento; // Evento avaliado
    private int nota; // Avaliação de 1 a 5
    private String comentario; // Comentário do usuário
    private Date dataCriacao; // Data de criação do feedback

    /**
     * Construtor da classe Feedback.
     *
     * @param evento O evento a ser avaliado.
     * @param nota A nota dada ao evento (de 1 a 5).
     * @param comentario O comentário sobre o evento.
     */
    public Feedback(Evento evento, int nota, String comentario) {
        this.evento = evento;
        this.nota = nota;
        this.comentario = comentario;
        this.dataCriacao = new Date(); // Data de criação do feedback
    }

    /**
     * Obtém o evento avaliado.
     *
     * @return O evento avaliado.
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Obtém a nota dada ao evento.
     *
     * @return A nota (de 1 a 5).
     */
    public int getNota() {
        return nota;
    }

    /**
     * Obtém o comentário sobre o evento.
     *
     * @return O comentário do usuário.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Obtém a data de criação do feedback.
     *
     * @return A data de criação do feedback.
     */
    public Date getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Verifica se o evento já foi avaliado (ou seja, se a data do evento já passou).
     *
     * @return true se o evento foi avaliado, false caso contrário.
     */
    public boolean isEventoAvaliado() {
        Date dataAtual = Calendar.getInstance().getTime();
        return evento.getData().before(dataAtual);
    }

    /**
     * Salva o feedback em um arquivo JSON no caminho especificado.
     *
     * @param caminhoArquivo O caminho do arquivo onde o feedback será salvo.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public void salvarFeedbackJson(String caminhoArquivo) throws IOException {
        if (!isEventoAvaliado()) {
            System.out.println("O evento ainda não ocorreu. Avaliação não permitida.");
            return;
        }

        // Criação do objeto GSON e formatação JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Serializa o feedback em JSON e escreve no arquivo especificado
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(this, writer);
            System.out.println("Feedback salvo com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o feedback: " + e.getMessage());
            throw e;
        }
    }
}

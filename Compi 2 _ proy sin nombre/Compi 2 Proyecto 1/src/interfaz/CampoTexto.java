package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;

public class CampoTexto extends JPanel {

    public RSyntaxTextArea areaTexto;
    public RTextScrollPane scrollTexto;
    public boolean guardado;
    public boolean esgxml;
    public String path;
    public String carpeta = "";
    boolean tipo;

    public CampoTexto(boolean bo) {
        super(new GridLayout());
        esgxml = bo;
        iniciarAjustes(bo);
        iniciarColores();
    }

    private void iniciarColores() {

        SyntaxScheme scheme = areaTexto.getSyntaxScheme();
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.BLUE;
        Color c = Color.decode("#f26868");
        
        Color pCasiReservadas = Color.decode("#211347");
        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = c;
        scheme.getStyle(Token.IDENTIFIER).foreground = Color.BLACK;
        scheme.getStyle(Token.DATA_TYPE).foreground = Color.decode("#7a60c4");
        scheme.getStyle(Token.COMMENT_EOL).foreground = Color.GRAY;
        scheme.getStyle(Token.LITERAL_CHAR).foreground = Color.ORANGE;
        scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.GRAY;
        scheme.getStyle(Token.SEPARATOR).foreground = Color.black;
        scheme.getStyle(Token.OPERATOR).foreground = Color.black;
        scheme.getStyle(Token.FUNCTION).foreground = pCasiReservadas;

    }

    private void iniciarAjustes(boolean bo) {
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();

        atmf.putMapping("text", "Analizador.ColorArit");
        areaTexto = new RSyntaxTextArea(20, 60);
        areaTexto.setSyntaxEditingStyle("text");
        areaTexto.setCodeFoldingEnabled(true);
        areaTexto.setCurrentLineHighlightColor(new Color(227, 242, 253, 200));
        areaTexto.setFadeCurrentLineHighlight(true);
        areaTexto.setBorder(BorderFactory.createEmptyBorder());
        areaTexto.setFont(new Font("Consolas", 0, 15));
        scrollTexto = new RTextScrollPane(areaTexto);
        scrollTexto.setViewportBorder(BorderFactory.createEmptyBorder());
        this.add(scrollTexto);
    }

    public String getTexto() {
        return areaTexto.getText();
    }

    public void setTexto(String texto) {
        areaTexto.setText(texto);
    }

    /**
     * @return the guardado
     */
    public boolean isGuardado() {
        return guardado;
    }

    /**
     * @param guardado the guardado to set
     */
    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the areaTexto
     */
    public RSyntaxTextArea getAreaTexto() {
        return areaTexto;
    }

    /**
     * @param areaTexto the areaTexto to set
     */
    public void setAreaTexto(RSyntaxTextArea areaTexto) {
        this.areaTexto = areaTexto;
    }

}

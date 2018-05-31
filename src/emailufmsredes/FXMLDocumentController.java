/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailufmsredes;

import emailufmsredes.MandarEmail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Asaf Santana
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label labelAnexo;
    
    @FXML
    private JFXButton enviar;
    
    @FXML
    private JFXButton fechar;
    
    @FXML
    private JFXButton tirarAnexo;
    
    @FXML
    private JFXButton anexar;
    
    @FXML
    private JFXTextField remetente;
    
    @FXML
    private JFXTextField destinatario;
    
    @FXML
    private JFXTextArea menssagemDoTexto;
    
    @FXML
    private JFXTextArea assunto;
    
    private MandarEmail email;
    
    private String caminho;
    
    
    /**
     * Metodo responsavel pela acao do botão enviar
     * @param event 
     */
    @FXML
    private void botaoEnviar(ActionEvent event) {
        
        if (destinatario.getText() == null || destinatario.getText().equals("")) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Sem destinatario");
            alerta.show();
        } else if (remetente.getText() == null || remetente.getText().equals("")) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Sem remetente");
            alerta.show();
        } else {
            
            if (menssagemDoTexto == null || menssagemDoTexto.getText().equals("")) {
                menssagemDoTexto.setText("Sem Menssagem");
            }
            
            try {
                
                email.Enviar(destinatario.getText(), remetente.getText(), menssagemDoTexto.getText(), assunto.getText(), caminho);
                
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText("Enviado com sucesso");
                alerta.show();
                limparCampos();
            } catch (EmailException ex) {
                System.out.println("ERRO");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Erro ao Enviar");
                alerta.show();
                
            }
            
        }
        
    }
    /**
     * Esse metodo fecha a aplicação
     * @param event 
     */
    @FXML
    private void fecharView(ActionEvent event) {
        
        System.exit(0);
        
    }
    
    @FXML
    private void tirarAnexoMetodo(ActionEvent event) {
        
        caminho = null;
        labelAnexo.setText("");
        tirarAnexo.setVisible(false);
        
    }
    
    /**
     * Esse metodo é responsavel pela acao do botao de anexar 
     * @param event 
     */
    @FXML
    private void anexarDoc(ActionEvent event) {

        // com essas linha nos permite abrir a pagina de arquivos
        FileChooser f = new FileChooser();
        
        f.getExtensionFilters().add(new ExtensionFilter("Arquivos", "*.txt", "*.doc", "*.jpg", "*.pdf", "*.zip"));
        File file = f.showOpenDialog(new Stage());
        
        if (file.getAbsolutePath() != null) {
            this.caminho = file.getAbsolutePath();
            labelAnexo.setText("Anexado");
            tirarAnexo.setVisible(true);
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        email = new MandarEmail();
        tirarAnexo.setVisible(false);
        
    }
    /**
     * Esse metodo é responsavel por limpar os campos
     */
    public void limparCampos() {
        destinatario.setText(null);
        assunto.setText(null);
        remetente.setText(null);
        menssagemDoTexto.setText(null);
        caminho = null;
        labelAnexo.setText("");
        tirarAnexo.setVisible(false);
    }
    
}

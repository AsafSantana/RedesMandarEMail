/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailufmsredes;

import java.util.logging.Level;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author Asaf Santana
 */
public class MandarEmail {

    public void MandarEmail() {

    }

    /**
     * Metodo responsavel por mandar o email
     *
     * @param destinatario
     * @param remetente
     * @throws EmailException
     */
    public void Enviar(String destinatario, String remetente, String menssagem, String assunto, String caminho) throws EmailException {

      
        
        MultiPartEmail email = new MultiPartEmail();

        email.setHostName("smtp.googlemail.com"); // o servidor SMTP para envio do e-mail
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("redestrabalho7@gmail.com", "redes123"));
        email.setSSLOnConnect(true);
        email.setFrom(remetente);
        email.setSubject(assunto); // assunto do e-mail
        email.setMsg(menssagem); //conteudo do e-mail
        email.addTo(destinatario);

        
       
        if (caminho != "" && caminho != null) {
            
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(caminho);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Arquivo");
        attachment.setName("Arquivo");
        
        email.attach(attachment);
        }
        

        email.send();
  }
    

}

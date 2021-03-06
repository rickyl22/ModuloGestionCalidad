/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grupo.action;
import DBMS.DBMS;
import domain.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ani
 */
public class TerminarAccion extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Accion acc = (Accion) form;
        String registro_nc= acc.getRegistro_nc();
        boolean terminada;
        
        
        terminada = DBMS.getInstance().terminarAccion(acc.getRegistro_nc(), acc.getAccion());
        final String username = "ulab-calidad@usb.ve";
        final String password = "coordcalidad";
        Properties props;
        Session session;
        Message message;
        if(terminada) {
            ArrayList<Accion> acciones_no_terminadas;
            acciones_no_terminadas = DBMS.getInstance().consultarAccionesNoTerminadas(registro_nc);
            if(acciones_no_terminadas.isEmpty()){
                if(DBMS.getInstance().terminarNoConformidad(registro_nc)){
                    NoConformidad nc= DBMS.getInstance().buscarNc(registro_nc);
                    if (nc.getOrigen_nc()==1){
                        String to = DBMS.getInstance().getEmailQueja(nc.getCodigo_origen_nc());
                        props = new Properties();
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.port", "587");

                        session = Session.getInstance(props,
                          new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(username, password);
                                }
                          });

                        try {

                                message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(username));
                                message.setRecipients(Message.RecipientType.TO,
                                        InternetAddress.parse(to));
                                message.setSubject("Se ha cerrado el proceso referente a tu queja en la unidad de laboratorios.");
                                message.setText("El equipo de gestion de calidad de la unidad de laboratorios\nse complace en anunciarle que se ha cerrado el proceso "
                                        + "\nde gestion referente a la queja que ha emitido "
                                        +"\n\npara más información ingrese al módulo de gestión de calidad SIGULAB, o acerquese a nuestras oficinas");

                                Transport.send(message);

                        } catch (MessagingException e) {
                                throw new RuntimeException(e);
                        }

                    }
                }

            }
            acc.setMensaje("La acción '"+acc.getAccion()+"' ha sido terminada.");
            
                
                
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
                try {
 
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(username));
			message.setSubject("SIGULAB-Accion terminada");
			message.setText("Se ha terminada la acción "+acc.getAccion()+" asociada a la no conformidad "+acc.getRegistro_nc()+"\n\nPara más información ingrese al módulo de gestión de calidad SIGULAB");
                        
			Transport.send(message);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        }
        else {
            acc.setMensaje("La acción '"+acc.getAccion()+"' no se ha podido terminar.");
        }
        request.setAttribute("Accion", acc);
        return mapping.findForward(SUCCESS);
    }
}

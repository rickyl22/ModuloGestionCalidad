/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo.action;

import domain.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import DBMS.*;
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
public class ModificarUsuario extends org.apache.struts.action.Action {

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
        Usuario user = (Usuario) form;
        ArrayList<Usuario> listGrupo;

        user.setUsbid(user.getUsbid());

        boolean agrego = DBMS.getInstance().agregarRelacionGU(user, user.getGrupo(), 1);

        if (agrego) {
            listGrupo = DBMS.getInstance().usuariosSinGrupo(user.getGrupo());
            
            
                final String username = "ulab-calidad@usb.ve";
		final String password = "coordcalidad";
                String to = DBMS.getInstance().getEmail(user.getUsbid());
                Usuario us = DBMS.getInstance().buscarUsuario(user.getUsbid());
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject("SIGULAB-Nuevo grupo");
			message.setText(""+us.getNombre()+", "
				+ "\nSe te ha agregado al grupo "+user.getGrupo()+"\n\nPara más información ingrese al módulo de gestión de calidad SIGULAB");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
            
            for (int j = 0; j < listGrupo.size(); j++) {
                listGrupo.get(j).setGrupo(user.getGrupo());
            }

            request.setAttribute("huerfanos", listGrupo);
            return mapping.findForward(SUCCESS);
        } else {
                return mapping.findForward(SUCCESS);
        }

    }
}

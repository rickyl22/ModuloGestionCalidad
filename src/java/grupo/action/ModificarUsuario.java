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
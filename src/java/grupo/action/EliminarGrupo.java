/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grupo.action;

import domain.Grupo;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Anna
 */
public class EliminarGrupo extends org.apache.struts.action.Action {

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
            Grupo grupo = (Grupo) form;
            boolean elimino;
            elimino = DBMS.getInstance().eliminarGrupo(grupo);
            
            ArrayList<Grupo> grupos;
            grupos = DBMS.getInstance().consultarGrupos();
        
            request.setAttribute("listGrupos", grupos);
            request.setAttribute("grupitos", grupo);
            
            if(elimino) 
                return mapping.findForward(SUCCESS);
            else {
                grupo.setError("No se pudo eliminar el grupo.");
                return mapping.findForward(FAILURE);
            }
            
    }
}

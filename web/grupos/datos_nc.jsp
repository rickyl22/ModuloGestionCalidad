<%@page import="domain.NoConformidad"%>
<%@page contentType="text/html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%NoConformidad nc = (NoConformidad) request.getAttribute("nc");
String registro_nc = nc.getRegistro_nc();
String visible = (String) request.getAttribute("visible");%>
<html:hidden property="" styleId="encargado" value="<%=visible%>"/>
<table>
    <tr>
        <td>
            <p style="margin-top: -10px; color:#336699; font-size: 14px; font-weight: bolder; size: 80px">
                No Conformidad
            </p>
        </td>
        <td>
            <p style="font-size: 12px; margin-top: -10px">
                <b>Registro:</b>
                <bean:write name="nc" property="registro_nc"/>
            </p>
        </td>
    </tr>
</table>
            
<div id="noconformidades">
    <table>
        <tr>
            <td>
                <p style="font-size: 12px">
                    <b>Situacion:</b>
                    <bean:write name="nc" property="situacion_nc"/>
                </p>
            </td>
            <td>
                <p style="font-size: 12px">
                    <b>Origen:</b>
                    <%= request.getAttribute("origen") %>
                </p>
            </td>
            <td>
                <p style="font-size: 12px">
                    <b>Fecha:</b>
                    <bean:write name="nc" property="fecha_nc"/>
                </p>
            </td>
        </tr>
    </table>

    <table>
        <tr>
            <td style="width: 50%">
                <p align="center"><b>Norma ISO 9001:</b></p><br>
                <p style="font-size: 12px">
                    <b>Codigo:</b>
                    <bean:write name="nc" property="codigo_nc1"/>
                </p>
            </td>
            <td>
                <p align="center"><b>ISO/IEC 17025</b></p><br>
                <p style="font-size: 12px">
                    <b>Codigo:</b>
                    <bean:write name="nc" property="codigo_nc2"/>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 12px">
                    <b>Requisito:</b>
                </p>
                <bean:write name="nc" property="requisito_nc1"/>
            </td>
            <td>
                <p style="font-size: 12px">
                    <b>Requisito:</b>
                </p>
                <bean:write name="nc" property="requisito_nc2"/>
            </td>
        </tr>
        <tr>
            <td style="width: 50%">
                <p style="font-size: 12px">
                    <b>Clausula:</b>
                    <bean:write name="nc" property="clausula_nc1"/>
                </p>
            </td>
            <td>
                <p style="font-size: 12px">
                    <b>Clausula:</b>
                    <bean:write name="nc" property="clausula_nc2"/>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 12px">
                    <b>Declaracion de la No Conformidad:</b>
                </p>
                <bean:write name="nc" property="declaracion_nc1"/>
            </td>
            <td>
                <p style="font-size: 12px">
                    <b>Declaracion de la No Conformidad:</b>
                </p>
                <bean:write name="nc" property="declaracion_nc2"/>
            </td>
        </tr>
    </table>
</div>
            
        <div style="vertical-align: middle; width: 560px; margin-bottom: -4px; margin-top: 10px">
            <p style="color:#336699; font-size: 14px; font-weight: bolder; size: 80px">Acciones Preventivas:</p>
        </div>
<div id="noconformidades">
    <table>
        <logic:iterate id="a_p" name="AccionPreventiva">
            <tr>
                <td style="vertical-align: middle">
                    <html:form action="/linkaccion">
                        
                        <html:hidden name="a_p" property="registro_nc"/>
                        <html:hidden name="a_p" property="accion"/>
                        <html:submit><bean:write name="a_p" property="accion"/></html:submit>
                    </html:form>
                    
                    <html:link action="linkaccion" paramId="Accion" paramName="a_p"
                                       paramProperty="accion" >
                        
                        
                    </html:link>
                </td>
            </tr>
        </logic:iterate>
    </table>
</div> 
        <div style="vertical-align: middle; width: 560px; margin-bottom: -4px; margin-top: 10px">
            <p style="color:#336699; font-size: 14px; font-weight: bolder; size: 80px">Acciones Correctivas:</p>
        </div>           
<div id="noconformidades">
    <table>
        <logic:iterate id="a_c" name="AccionCorrectiva">
            <tr>
                <td style="vertical-align: middle">
                    <bean:write name="a_c" property="accion" />
                </td>
            </tr>
        </logic:iterate>
    </table>
</div> 
<br>       

<html:form action="/nueva_accion">
    <html:hidden property="registro_nc" value="<%=registro_nc%>" />
    <html:submit style="visibility: hidden" styleId="agregarp" value="+ Accion" />
</html:form>           
  
            
           
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="CSS/Style.css" /> 
        <script type="text/javascript" src="JS/sesion.js"></script>
</head>
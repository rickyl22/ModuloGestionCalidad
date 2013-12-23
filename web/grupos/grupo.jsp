<%@page contentType="text/html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%String Agregar= (String) request.getAttribute("Agregar");%>
<%String AgregarAction= (String) request.getAttribute("AgregarAction");%>

<table style="margin-top: -10px">

    <tr>
        <td>
            <bean:write name="grupitos" property="error" filter="false"/>
        </td>
    </tr>
    <tr>
        <td style="vertical-align: middle; width: 560px">
            <p style="color:#336699; font-size: 1.154em; font-weight: bolder; size: 80px">GRUPOS</p>
        </td>
        <td></td>
        <td></td>
        <td>
            <html:link action="nuevo_grupo">
                <html:submit styleId="crear" value="Crear Nuevo" style="margin: 0px 0px 0px -30px"/>
            </html:link>
        </td>
    </tr>

    <logic:iterate id="infoGrupo" name="listGrupos">
        
        <tr>
            <td style="width: 560px">
                <html:link action="/linkGrupos" paramId="verGrupo"
                           paramName="infoGrupo" paramProperty="nombre_grupo"> 
                    <p style="color: #1c6ea0;">
                        <bean:write name="infoGrupo" property="nombre_grupo"/><br/>
                    </p>
                </html:link>
            </td>
            <td style="width: 115px">
                
            </td>
            <td style="width: 115px">
                <html:form action="<%=AgregarAction%>">
                    <html:hidden name="infoGrupo" property="nombre_grupo"/>
                    <html:submit styleId= "agregarp" value="<%=Agregar%>"/>
                </html:form>
            </td>
            <td style="width: 115px">
                <html:form action="/eliminargrupo">
                    <html:hidden name="infoGrupo" property="nombre_grupo"/>
                    <html:submit styleId="eliminar" value="Eliminar"/>
                </html:form>
            </td>
        </tr>
        
        
    </logic:iterate>
</table>
        
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="CSS/Style.css" />
</head>
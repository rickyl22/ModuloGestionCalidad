<%@page contentType="text/html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="../cod_plantilla.jsp" flush="true">
    <tiles:put name="titulo" value="Publicacion" />
    <tiles:put name="cuerpo" value="/cartelera/nueva_publicacion.jsp" />
</tiles:insert>

<%@page contentType="text/html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<body>
    <div id="login">
       <div id="titulo">
            <p>Introduzca USBID y Contrase�a.</p>
       </div>
        <html:form action="/iniciar">

            <table >
                <tbody id="etiqueta">
                    <tr>
                        <td>
                            <p>USBID:</p>
                            <html:text styleId="usbid" property="usbid" value=""/>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>Contrase�a:</p>
                            <html:password styleId="password" property="password" value=""/>
                            
                        </td>
                    </tr>
                    <tr>
                        <td><html:submit  styleId="loginbt" value="Iniciar Sesi�n" /></td>
                    </tr>
                </tbody>
            </table>
        </html:form>
        
    </div>
</body>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link rel="stylesheet" type="text/css" href="CSS/Style.css" />
</head>



	

 
        
        


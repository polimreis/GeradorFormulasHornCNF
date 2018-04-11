<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>

<HEAD>
<title># Gerador CNF #</title>
</HEAD>


<BODY>
<html:html>

<table class="azul" width="800" height="80" align="center">
	<TR>
		<td align="center"><img src="imagens/bola.JPG"></td>
		<TD><FONT size="5" color="#330066" face="verdana" style="">Gerador
		de CNF 1.0 </FONT> <br>
		</TD>
	</TR>
</table>

<table align="center" width="800" height="35">
	<tr>
		<TD></TD>
	</tr>
</table>

<!-- 
<table width="800" height="380" align="center" class="azul">

	<tr bgcolor="#D3E5FB" height="30">
		<td align="center"><img src="imagens/projeto2.JPG"></td>

		<td><FONT size="2" color="blue" " face="verdana" style="">Bem-vindo
		ao Sistema de Gerenciamento de Demandas de Materiais! Para ter acesso
		ao sistema favor logar-se!</FONT></td>
	</tr>

	<tr align="center">

		<td></td>

		<td><br>
		<FONT size="2" style="" face="verdana" color="red">${logout}</FONT> <FONT
			size="2" style="" face="verdana" color="red">${msg}</FONT> 
			<html:form method="POST" action="/action">
			<br>
			<FONT size="1" color="blue">Numero de Clausulas (M)</font>
			<br>
			<html:text property="M" />
			<br>
			<FONT size="1" color="blue">Numero de Atomos (N)</font>
			<br>
			<html:text property="N" />
			<br>
			<FONT size="1" color="blue">Numero de Literais (K)</font>
			<br>
			<html:text property="K" />
			<br>
			<html:submit value="GERAR" style="background-color: #D3E5FB" />

			<html:hidden property="metodo" value="gerarCnf" />


		</html:form></td>
	</tr>
</table>

 -->
 
 
 		<!--  <form name="form" action="/action" enctype="text/plain" method="post" > -->
		
		<!--  		</form> -->
 
</html:html>

</BODY>

</html>

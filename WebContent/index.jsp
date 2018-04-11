<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html>

<HEAD>
<title># Gerador CNF #</title>

<link rel="stylesheet" type="text/css" href="utils/estilo.css">
</HEAD>


<BODY>
<html:html>

<table class="azul" width="800" height="80" align="center">
	<TR>
		<td align="center"><img src="imagens/usp.JPG"></td>
		<td align="center"><FONT size="5" color="#330066" face="verdana"
			style="">Gerador de K-Clausulas </FONT> <br>
		</TD>
		<td align="center"><img src="imagens/ime.JPG"></td>
	</TR>
</table>

<table align="center" width="800" height="35">
	<tr>
		<TD></TD>
	</tr>
</table>

<table width="800" height="50" align="center" class="azul">

	<tr bgcolor="#D3E5FB" height="30">
		<td align="center"><FONT size="2" color="blue" " face="verdana"
			style="">Bem-vindo ao Sistema Gerador de K-Clausulas .</Font> <br>
		<FONT size="2" color="blue" " face="verdana" style=""> Preencha
		os campos abaixo para geração de um arquivo com clausulas no formato
		DIMACS. (Formato CNF) </FONT></td>
	</tr>
</table>


<table align="center" width="800" height="35">
	<tr align="center" >
		<td align="center"> <FONT size="2" style="" face="verdana" color="red">${erro}</FONT> 
		    <FONT size="2" style="" face="verdana" color="green">${msg}</FONT> <br>
		</TD>
	</tr>
</table>

<table width="400" height="120" align="center">


	<tr align="center" height="40">
		<td align="center">
		   



		<form name="form" action="action.do">
		
		<input type="hidden" name="metodo" value="gerarCnf"> <br>

		<FONT size="2" color="blue" " face="verdana" color="blue">
		Numero de Clausulas (M): &nbsp</FONT> 
		<input type="text" name="M" id="M"  value="${M}" style="width: 100px;" maxlength="10">
		<br>
		<br>
		<FONT size="2" color="blue" " face="verdana" color="blue">
		Numero de Atomos (N): &nbsp &nbsp</FONT> 
		<input type="text" name="N" id="N"  value="${N}" style="width: 100px;" maxlength="10">
		<br>
		<br>
		<FONT size="2" color="blue" " face="verdana" color="blue">
		Numero de Literais (K): &nbsp &nbsp</FONT> 
		<input type="text" name="K" id="K"  value="${K}" style="width: 100px;" maxlength="10">
		<br>
		<br>
		<br>
		<br>
		<input type="submit" value="Gerar Arquivo"></form>


		</td>
	</tr>
</table>





<table align="center" width="800" height="50">
	<tr>
		<TD><br>
		</TD>
	</tr>
</table>

<table align="center">
	<tr>
		<td align="center"><FONT size="1" style="" face="verdana">2009
		- Developed by Poliana Magalhaes Reis </FONT></td>
	</tr>

	<tr>
		<td align="center"><FONT size="1" style="" face="verdana">Laboratório
		de Inteligencia Artificial </FONT></td>
	</tr>

	<tr>
		<td align="center"><FONT size="1" style="" face="verdana">Professor
		Marcelo Finger</FONT></td>
	</tr>
</table>

</html:html>
</BODY>
</html>
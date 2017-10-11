<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="commons/taglibs.jsp"%><%-- 共享文件 --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%

HttpSession ses=request.getSession();
String sesname= (String)ses.getAttribute("realName") ;

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		ctx = "${ctx}";
		var namese = '<%=sesname %>';
	</script>
	<script type="text/javascript" src="${ctx}/resources/js/extjs/ux/TabCloseMenu.js"></script>	
	<script type="text/javascript" src="${ctx}/resources/js/commons/share.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/programMain.js"></script>
  </head>
  <body>
	<table id="topmg" border="0" cellpadding="0" cellspacing="0" width="100%" background="resources/images/topbg.gif">
    	<tr>
    		<td align="left"><img src="resources/images/top.gif"/></td>
    		<td align="right"><img  src="resources/images/topright.gif"/></td>
    	</tr>
    </table>
  </body>
</html>

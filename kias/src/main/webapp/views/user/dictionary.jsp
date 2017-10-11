<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/commons/taglibs.jsp"%><%-- 共享文件 --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	</script>
	<script type="text/javascript" src="${ctx}/resources/js/commons/share.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/user/dictionaryManager.js"></script>
  </head>
  <body>
    <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
    	<tr>
	    	<td><div id="grid"></div></td>
    	</tr>
    </TABLE>
  </body>
</html>

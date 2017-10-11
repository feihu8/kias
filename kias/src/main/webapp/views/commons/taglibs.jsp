<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="user" value="${sessionScope.CURRENT_USER}" />
<c:set var="fields" value="${applicationScope.fields}" />
<%-- EXT-JS引导文件(根据模式决定调入 ext_all.js 或 ext_all_dev.js) --%>
<script type="text/javascript" src="${ctx}/resources/js/extjs/bootstrap.js"></script>
 
<%-- EXT-国际化文件 --%>
<script type="text/javascript" src="${ctx}/resources/js/extjs/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<%-- EXT-CSS文件 --%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/extjs/resources/css/ext-all.css" />

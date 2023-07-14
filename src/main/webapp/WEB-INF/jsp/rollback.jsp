<%-- 지시자 태그, 페이지의 속성정의 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%-- 스크립트릿 태그 자바코드를 페이지에 포함 -->
<%
  String message = (String) request.getAttribute("message");
  String redirectUrl = (String) request.getAttribute("redirectUrl");
%>

<%-- HTML 태그 -->
<script>
if("<%=message%>" !== "") {
  alert("<%=message%>");
}
document.location.replace("<%=redirectUrl%>");
</script>

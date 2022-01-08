<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <title>Title</title>
</head>
<body>

<%-- 코드 개선전
<ul>
    <li> id=<%=((Member)request.getAttribute("member")).getId()%> </li>
    <li> username=<%=((Member)request.getAttribute("member")).getUsername()%> </li>
    <li> age=<%=((Member)request.getAttribute("member")).getAge()%> </li>
</ul>
 --%>

 <%-- 코드 개선후 프로퍼티 접근법 자돟으로 get set 이 동작한다.--%>
 <ul>
     <li> id=${member.id} </li>
     <li> username=${member.username}  </li>
     <li> age=${member.age} </li>
 </ul>

<a href="/index.html">메인</a>
</body>
</html>
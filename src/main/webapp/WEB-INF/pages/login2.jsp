<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HEAD>
    <link href="${pageContext.request.contextPath}resources/packages/ext-theme-gray/build/resources/ext-theme-gray-all.css" rel="stylesheet">
    <TITLE>Login</TITLE>
</HEAD>
<c:url value="/login2" var="loginUrl"/>
<form action="${loginUrl}" method="post">
  <c:if test="${param.error != null}">
    <p>
      Invalid username and password.
    </p>
  </c:if>
  <c:if test="${param.logout != null}">
    <p>
      You have been logged out.
    </p>
  </c:if>
  <p>
    <label for="username">Username</label>
    <input type="text" id="username" name="username" autocomplete="off"/>
  </p>
  <p>
    <label for="password">Password</label>
    <input type="password" id="password" name="password"/>
  </p>
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
  <button type="submit" class="btn">Log in</button>
</form>
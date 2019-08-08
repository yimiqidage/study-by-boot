<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="" />" >
</head>
<body>
用户是否有管理员权限：
<security:authorize access="hasAnyRole('ROLE_ADMIN')">
    有
</security:authorize>
</body>
</html>
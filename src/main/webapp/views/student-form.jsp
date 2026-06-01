<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>
 <c:if test="${student == null}">Thêm sinh viên</c:if>
 <c:if test="${student != null}">Sửa sinh viên</c:if>
</h2>
<form action="students" method="post">
 <c:if test="${student != null}">
 <input type="hidden" name="id" value="${student.id}">
 <input type="hidden" name="action" value="update">
 </c:if>
 <c:if test="${student == null}">
 <input type="hidden" name="action" value="insert">
 </c:if>
 Mã SV: <input name="masv" value="${student.masv}"><br>
 Họ tên: <input name="hoten" value="${student.hoten}"><br>
 <button type="submit">Lưu</button>
</form>


<a href="students">Quay lại</a>

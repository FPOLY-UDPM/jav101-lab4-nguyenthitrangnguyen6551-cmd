<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Danh sách sinh viên</h2>
<a href="students?action=new">Thêm sinh viên</a>
<br><br>
<table border="1" cellpadding="5">
 <tr>
 <th>ID</th>
 <th>Mã SV</th>
 <th>Họ tên</th>
 <th>Hành động</th>
 </tr>
 <c:forEach var="s" items="${students}">
 <tr>
 <td>${s.id}</td>
 <td>${s.masv}</td>
 <td>${s.hoten}</td>
 <td>
 <a href="students?action=edit&id=${s.id}">Sửa</a> |
 <a href="students?action=delete&id=${s.id}"
 onclick="return confirm('Xóa sinh viên này?')">Xóa</a>
 </td>
 </tr>
 </c:forEach>


</table>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><p>

<table width="500" cellpadding="0" cellspacing="0" border="1">
	<!-- 등록 form에서 post로 전송시 컨트롤러에서 insert처리를 한다. -->
    <form action="write" method="post">
       <tr>
          <td>작성자</td>
          <td> <input type="text" name="writer" size="100"></td>
       </tr>
       <tr>
          <td>제목</td>
          <td> <input type="text" name="title" size="100"></td>
       </tr>
       <tr>
          <td>내용</td>
          <td> <input type="text" name="content" size="100"></td>
       </tr>
       <tr>
          <td colspan="2"> <input type="submit" value="입력">
                &nbsp;&nbsp; <a href="list">목록보기</a></td>
       </tr>
    </form>
</table>
</body>
</html>
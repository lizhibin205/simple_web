<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello World</title>
</head>
<body>
   <h1>${message}</h1>
   <table>
     <tbody>
         <tr th:each="msg: ${messageList}">
             <td th:text="${msg.id}"></td>
             <td th:text="${msg.message}"></td>
         </tr>
     </tbody>
     <thead>
       <th><td>Id</td><td>Message</td></th>
     </thead>
   </table>
</body>
</html>
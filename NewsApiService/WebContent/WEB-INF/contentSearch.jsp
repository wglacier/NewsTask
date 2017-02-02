<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}

th {
    padding: 5px;
    text-align: center;
    background-color: #4CAF50;
    color: white;
}
</style>	

<body>
Search result:
<br>
<table>
	<tr>
		<th>title</th>
		<th>author</th>
		<th>url</th>
		<th>time</th>
		<th>content</th>
	</tr>
		
	<c:forEach items="${articles}" var="article">
    <tr>      
        <td>${article.title}</td>
        <td>${article.author}</td>
        <td><a href="${article.url}" target="_blank">link</a></td>
        <td>${article.last_updated}</td>
        <td>${article.content}</td>  
    </tr>
</c:forEach>
</table>
</body>

</html>

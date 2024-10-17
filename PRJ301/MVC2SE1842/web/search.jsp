<%-- 
    Document   : search
    Created on : Oct 11, 2024, 2:09:03 PM
    Author     : ADMIN
--%>

<%@page import="nhant.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <form action="DispatchServlet"><!--  dung phuong thuc GET hay POST cung duoc-->
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue") %>" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>
        <%  
            String searchValue = request.getParameter("txtSearchValue");
            if(searchValue !=null){
                List<RegistrationDTO> result = (List<RegistrationDTO>)
                        request.getAttribute("SEARCH_RESULT");
                if(result!= null){//has at least result
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                           <%
                               int count = 0;
                               for(RegistrationDTO dto : result){
                                   %>
                             <tr>
                                <td>
                                    <%= ++count %>
                                    </td>
                                <td>
                                    <%= dto.getUsername() %>
                                </td>
                                <td>
                                    <%= dto.getPassword() %>
                                </td>
                                <td>
                                    <%= dto.getFullName() %>
                                </td>
                                <td>
                                    <%= dto.isRole() %>
                                </td>
                            </tr>      
                            <%
                               }//lay tung phan tu trong result de duyet
                           %> 
                        </tbody>
                    </table>

        <%
                }else{//no result
                    %>
                    <h2>
                        <font color="red">
                            No record is matched!!!
                            </font>    
                    </h2>
        <%
                }
                //ket qua search nam o attribute cua request scope
                //List <Registration DTO>
                //phai ep kieu do 
            }//yeu cau dau tien phai check no khac null vi neu khong thi se bi loi 500
        %>
    </body>
</html>

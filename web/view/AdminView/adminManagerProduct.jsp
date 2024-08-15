


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="Model.*" %>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Management</title>
        <style>
            /* Basic styling */
            body {
                font-family: Arial, sans-serif;
            }
            h1 {
                text-align: center;
                margin-top: 50px;
            }
            table {
                width: 80%;
                margin: 0 auto;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            .add-user-button {
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .add-user-button:hover {
                background-color: #45a049;
            }

        </style>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            .taskbar {
                background-color: #333;
                color: #fff;
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 10px;
            }
            .taskbar input[type="text"] {
                padding: 5px;
                border-radius: 5px;
                border: none;
                outline: none;
            }
            .taskbar button {
                padding: 5px 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                background-color: #555;
                color: #fff;
                margin-left: 10px;
            }
            .search{
                text-decoration: none;
                align-content: center;
            }
            .material-icons-outlined {
                vertical-align: middle;
                line-height: 1px;
                font-size: 35px;
                color: #f8f9fa;
                
               

            }
             .right-taskbar{
                 width: 65%;
                 display: flex;
                 justify-content: space-between;
                }

        </style>
    </head>
    <body>

        <h1>Product Management</h1>

        <!-- Button to add a new product -->


        <div class="taskbar">
            <div>
                <button onclick="goHome()">
                    <a href="addUser.jsp" class="add-user-button">Home</a>
                </button>
            </div>
            <div class="right-taskbar" >
                <form action="searchproduct" method="get">
                     <a class ="search">
                    <span class="material-icons-outlined">search</span>
                    <input type="text" name="txt" placeholder="Search..." style="width:400px;">
                    <button type="submit" class="btn btn-secondary btn-number">search</button>
                </a>

                </form>
               
                <div class="dropdown">
                    <form action="adminmanagerproduct" method="post" name="sortProduct">
                        <select onchange="sort(this)" name="optionSort">
                            <option value="0" ${requestScope.valueOption==0 ?'selected':'' }>Sort by ID</option>
                            <option ${requestScope.valueOption==1 ?'selected':'' } value="1">Sort by Price</option>
                            <option value="2" ${requestScope.valueOption==2 ?'selected':'' }>Sort by Name(A-Z)</option>
                            <option value="3" ${requestScope.valueOption==3 ?'selected':'' }>Sort by Quantity</option>
                        </select>
                    </form>
                </div>
            </div>
        </div>


        <!-- Table to display product data -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Input day</th>
                    <th>Quantity</th>
                    <th>Brand</th>
                   
                </tr>
            </thead>
            <tbody>
                <!-- Populate this section dynamically with product data -->
                <c:forEach items="${listProduct}" var="i">
                    <tr>
                        <td>${i.product_id}</td>
                        <td>${i.product_name}</td>
                        <td><img style="width: 100px;height: 100px" src="${imageDb.getListImage(i.product_id).get(0).imageProduct_url}" alt="" /></td>
                        <td>${i.product_originPrice}</td>
                        <td>${categoryDb.getCategoryById(i.category_id).category_name}</td>
                        <td style="width: 120px">${i.product_importDate}</td>
                        <td style="width: 10px">${i.product_quantity}</td>
                        <td>${brandDb.getNameBrandByID(i.brand_id)}</td>

                        <td>

                        </td>
                    </tr>
                </c:forEach>
                <!-- More rows for additional products -->
            </tbody>
        </table>
        
        
        <script>
            function goHome() {
                // Thực hiện hành động khi nhấn vào nút Home
                console.log("Redirect to Home page");
            }

             function sort(ads) {
                document.sortProduct.submit();
                // Thực hiện hành động khi nhấn vào nút Sort
                console.log("Sorting items");
            }
        </script>
    </body>
</html>

</html>

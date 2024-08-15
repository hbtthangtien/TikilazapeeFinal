<%-- 
    Document   : Edit
    Created on : May 26, 2024, 11:14:37 PM
    Author     : sktnb
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="CSS/manage.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 100%;
                
            }

            .container {
                width: 100%;
            }
            .item {
                float: left;
                width: 25%;
                box-sizing: border-box;
                padding: 5px;
                border: 1px solid #000;
                text-align: center;
            }

        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="edit" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Product ID</label>
                                    <input value="${detail.product_id}" name="id" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Product Name</label>
                                    <input value="${detail.product_name}" name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Category Name</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listc}" var="o">
                                            <option value="${o.category_id}" ${detail.category_id == o.category_id ? 'selected="selected"' : ''}>${o.category_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Image</label>
                                    <input type="file" id="avatara" name="avatar" accept="image/jpeg,image/png"  value="Choose image" required>

                                    <div class="container">
                                        <div class="item"><img src="../${listi.get(0).imageProduct_url}"></div>
                                        <div class="item"><img src="../${listi.get(1).imageProduct_url}"></div>
                                        <div class="item"><img src="../${listi.get(2).imageProduct_url}"></div>
                                        <div class="item"><img src="../${listi.get(3).imageProduct_url}"></div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label>Origin Price</label>
                                    <input value="${detail.product_originPrice}" name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Percent Sale</label>
                                    <input value="${detail.product_percenSale}" name="sale" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input value="${detail.product_quantity}" name="quantity" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" required>${detail.product_Describes}</textarea>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Edit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="JS/manage.js" type="text/javascript"></script>
    </body>
</html>

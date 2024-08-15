<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.*"  %>
<%@page import="util.*" %>


<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">

        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">

        <meta charset="utf-8" />
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
            />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="CSS/style.css" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"
            />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500&family=Lato:wght@300;400;700&display=swap"
            rel="stylesheet"
            />

        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet"/>
        <link
            href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet"
            />

        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <title>Product Details Page</title>
        <link rel="stylesheet" href="style.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
            />
        <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.2/uicons-solid-rounded/css/uicons-solid-rounded.css'>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.2/uicons-regular-straight/css/uicons-regular-straight.css'>
        <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.2/uicons-regular-rounded/css/uicons-regular-rounded.css'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".wish-icon i").click(function () {
                    $(this).toggleClass("fa-heart fa-heart-o");
                });
            });
        </script>
        <style type="text/css">
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap");

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: "Poppins", sans-serif;
            }
            nav {
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 20px 8%;
            }
            nav .logo {
                font-size: 32px;
                font-weight: 500;
                cursor: pointer;
            }
            nav ul li {
                list-style: none;
                display: inline-block;
            }
            nav ul li a {
                display: block;
                margin: 0 10px;
                color: #000;
                font-weight: 500;
                text-decoration: none;
                font-size: 17px;
                position: relative;
            }
            nav ul li a::before {
                content: "";
                position: absolute;
                bottom: 0;
                left: 0;
                background-color: #000;
                width: 0;
                height: 2.5px;
                transition: all 0.3s ease;
            }
            nav ul li a:hover::before {
                width: 100%;
            }
            nav ul i {
                font-size: 20px;
                margin-left: 10px;
                cursor: pointer;
            }
            .flex-box {
                display: flex;
                width: 1000px;
                margin: 20px auto;
            }
            .left {
                width: 40%;
            }
            .big-img {
                width: 250px;
            }
            .big-img img {
                width: inherit;
            }
            .images {
                display: flex;
                justify-content: space-between;
                width: 60%;
                margin-top: 15px;
            }
            .small-img {
                width: 50px;
                overflow: hidden;
                border: 1.5px solid black;
            }
            .small-img img {
                width: inherit;
                cursor: pointer;
                transition: all 0.3s ease;
            }
            .small-img:hover img {
                transform: scale(1.2);
            }
            .url {
                font-size: 16px;
                font-weight: 400;
                color: rgb(0, 102, 255);
            }
            .pname {
                font-size: 22px;
                font-weight: 600;
                margin-top: 50px;
            }
            .ratings i {
                color: rgb(255, 136, 0);
            }
            .price {
                font-size: 20px;
                font-weight: 500;
                margin: 20px 0;
            }
            .size {
                display: flex;
                align-items: center;
                margin: 20px 0;
            }
            .size p {
                font-size: 18px;
                font-weight: 500;
            }
            .psize {
                width: 60px;
                height: 30px;
                border: 1px solid #ff5e00;
                color: #000;
                text-align: center;
                margin: 0 10px;
                cursor: pointer;
            }
            .psize.active {
                border-width: 1.5px;
                color: #ff5e00;
                font-weight: 500;
            }
            .quantity {
                display: flex;
            }
            .quantity p {
                font-size: 18px;
                font-weight: 500;
            }
            .quantity input {
                width: 40px;
                font-size: 17px;
                font-weight: 500;
                text-align: center;
                margin-left: 15px;
            }
            .btn-box {
                display: flex;
                margin-top: 40px;
            }
            .btn-box button {
                font-size: 18px;
                padding: 8px 25px;
                border: none;
                outline: none;
                border-radius: 6px;
                cursor: pointer;
                color: white;
            }
            .cart-btn {
                background-color: #ff5e00;
                margin-right: 20px;
            }
            .buy-btn {
                background-color: #00aeff;
                margin-right: 20px;
            }
            .wishlist-btn {
                background-color: #cccccc;
            }
            .cart-btn:hover {
                background-color: #ff3c00;
            }
            .buy-btn:hover {
                background-color: #0066ff;
            }
            .wishlist-btn:hover {
                background-color: #cccccc; /* Màu nền khi hover */

            }


            h2 {
                color: #000;
                font-size: 26px;
                font-weight: 300;
                text-align: center;
                text-transform: uppercase;
                position: relative;
                margin: 30px 0 60px;
            }
            h2::after {
                content: "";
                width: 100px;
                position: absolute;
                margin: 0 auto;
                height: 4px;
                border-radius: 1px;
                background: #7ac400;
                left: 0;
                right: 0;
                bottom: -20px;
            }
            .carousel {
                margin: 50px auto;
                padding: 0 70px;
            }
            .carousel .item {
                color: #747d89;
                min-height: 325px;
                text-align: center;
                overflow: hidden;
            }
            .carousel .thumb-wrapper {
                padding: 25px 15px;
                background: #fff;
                border-radius: 6px;
                text-align: center;
                position: relative;
                box-shadow: 0 2px 3px rgba(0,0,0,0.2);
            }
            .carousel .item .img-box {
                height: 120px;
                margin-bottom: 20px;
                width: 100%;
                position: relative;
            }
            .carousel .item img {
                max-width: 100%;
                max-height: 100%;
                display: inline-block;
                position: absolute;
                bottom: 0;
                margin: 0 auto;
                left: 0;
                right: 0;
            }
            .carousel .item h4 {
                font-size: 18px;
            }
            .carousel .item h4, .carousel .item p, .carousel .item ul {
                margin-bottom: 5px;
            }
            .carousel .thumb-content .btn {
                color: #7ac400;
                font-size: 11px;
                text-transform: uppercase;
                font-weight: bold;
                background: none;
                border: 1px solid #7ac400;
                padding: 6px 14px;
                margin-top: 5px;
                line-height: 16px;
                border-radius: 20px;
            }
            .carousel .thumb-content .btn:hover, .carousel .thumb-content .btn:focus {
                color: #fff;
                background: #7ac400;
                box-shadow: none;
            }
            .carousel .thumb-content .btn i {
                font-size: 14px;
                font-weight: bold;
                margin-left: 5px;
            }
            .carousel .item-price {
                font-size: 13px;
                padding: 2px 0;
            }
            .carousel .item-price strike {
                opacity: 0.7;
                margin-right: 5px;
            }
            .carousel-control-prev, .carousel-control-next {
                height: 44px;
                width: 40px;
                background: #7ac400;
                margin: auto 0;
                border-radius: 4px;
                opacity: 0.8;
            }
            .carousel-control-prev:hover, .carousel-control-next:hover {
                background: #78bf00;
                opacity: 1;
            }
            .carousel-control-prev i, .carousel-control-next i {
                font-size: 36px;
                position: absolute;
                top: 50%;
                display: inline-block;
                margin: -19px 0 0 0;
                z-index: 5;
                left: 0;
                right: 0;
                color: #fff;
                text-shadow: none;
                font-weight: bold;
            }
            .carousel-control-prev i {
                margin-left: -2px;
            }
            .carousel-control-next i {
                margin-right: -4px;
            }
            .carousel-indicators {
                bottom: -50px;
            }
            .carousel-indicators li, .carousel-indicators li.active {
                width: 10px;
                height: 10px;
                margin: 4px;
                border-radius: 50%;
                border: none;
            }
            .carousel-indicators li {
                background: rgba(0, 0, 0, 0.2);
            }
            .carousel-indicators li.active {
                background: rgba(0, 0, 0, 0.6);
            }
            .carousel .wish-icon {
                position: absolute;
                right: 10px;
                top: 10px;
                z-index: 99;
                cursor: pointer;
                font-size: 16px;
                color: #abb0b8;
            }
            .carousel .wish-icon .fa-heart {
                color: #ff6161;
            }
            .star-rating li {
                padding: 0;
            }
            .star-rating i {
                font-size: 14px;
                color: #ffc000;
            }
        </style>
        <%@include file="../../Template/libheader.jsp" %>
        <c:url value="/JS/jquery-3.7.1.min.js" var="libJquery"/>
    </head>
    <body>
        <%@include file="../../Template/navbar.jsp" %>

        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="search" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
        <title>Search</title>
        <path fill="currentColor" d="M19 3C13.488 3 9 7.488 9 13c0 2.395.84 4.59 2.25 6.313L3.281 27.28l1.439 1.44l7.968-7.969A9.922 9.922 0 0 0 19 23c5.512 0 10-4.488 10-10S24.512 3 19 3zm0 2c4.43 0 8 3.57 8 8s-3.57 8-8 8s-8-3.57-8-8s3.57-8 8-8z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="user" viewBox="0 0 16 16">
        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="cart" viewBox="0 0 16 16">
        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
    </symbol>
    <svg xmlns="http://www.w3.org/2000/svg" id="chevron-left" viewBox="0 0 16 16">
    <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
    </svg>
    <symbol xmlns="http://www.w3.org/2000/svg" id="chevron-right" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="cart-outline" viewBox="0 0 16 16">
        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="quality" viewBox="0 0 16 16">
        <path d="M9.669.864 8 0 6.331.864l-1.858.282-.842 1.68-1.337 1.32L2.6 6l-.306 1.854 1.337 1.32.842 1.68 1.858.282L8 12l1.669-.864 1.858-.282.842-1.68 1.337-1.32L13.4 6l.306-1.854-1.337-1.32-.842-1.68L9.669.864zm1.196 1.193.684 1.365 1.086 1.072L12.387 6l.248 1.506-1.086 1.072-.684 1.365-1.51.229L8 10.874l-1.355-.702-1.51-.229-.684-1.365-1.086-1.072L3.614 6l-.25-1.506 1.087-1.072.684-1.365 1.51-.229L8 1.126l1.356.702 1.509.229z" />
        <path d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1 4 11.794z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="price-tag" viewBox="0 0 16 16">
        <path d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0z" />
        <path d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1zm0 5.586 7 7L13.586 9l-7-7H2v4.586z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="shield-plus" viewBox="0 0 16 16">
        <path d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z" />
        <path d="M8 4.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V9a.5.5 0 0 1-1 0V7.5H6a.5.5 0 0 1 0-1h1.5V5a.5.5 0 0 1 .5-.5z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-fill" viewBox="0 0 16 16">
        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-empty" viewBox="0 0 16 16">
        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-half" viewBox="0 0 16 16">
        <path d="M5.354 5.119 7.538.792A.516.516 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.537.537 0 0 1 16 6.32a.548.548 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.52.52 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.58.58 0 0 1 .085-.302.513.513 0 0 1 .37-.245l4.898-.696zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.565.565 0 0 1 .162-.505l2.907-2.77-4.052-.576a.525.525 0 0 1-.393-.288L8.001 2.223 8 2.226v9.8z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="quote" viewBox="0 0 24 24">
        <path fill="currentColor" d="m15 17l2-4h-4V6h7v7l-2 4h-3Zm-9 0l2-4H4V6h7v7l-2 4H6Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="facebook" viewBox="0 0 24 24">
        <path fill="currentColor" d="M9.198 21.5h4v-8.01h3.604l.396-3.98h-4V7.5a1 1 0 0 1 1-1h3v-4h-3a5 5 0 0 0-5 5v2.01h-2l-.396 3.98h2.396v8.01Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="youtube" viewBox="0 0 32 32">
        <path fill="currentColor" d="M29.41 9.26a3.5 3.5 0 0 0-2.47-2.47C24.76 6.2 16 6.2 16 6.2s-8.76 0-10.94.59a3.5 3.5 0 0 0-2.47 2.47A36.13 36.13 0 0 0 2 16a36.13 36.13 0 0 0 .59 6.74a3.5 3.5 0 0 0 2.47 2.47c2.18.59 10.94.59 10.94.59s8.76 0 10.94-.59a3.5 3.5 0 0 0 2.47-2.47A36.13 36.13 0 0 0 30 16a36.13 36.13 0 0 0-.59-6.74ZM13.2 20.2v-8.4l7.27 4.2Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="twitter" viewBox="0 0 256 256">
        <path fill="currentColor" d="m245.66 77.66l-29.9 29.9C209.72 177.58 150.67 232 80 232c-14.52 0-26.49-2.3-35.58-6.84c-7.33-3.67-10.33-7.6-11.08-8.72a8 8 0 0 1 3.85-11.93c.26-.1 24.24-9.31 39.47-26.84a110.93 110.93 0 0 1-21.88-24.2c-12.4-18.41-26.28-50.39-22-98.18a8 8 0 0 1 13.65-4.92c.35.35 33.28 33.1 73.54 43.72V88a47.87 47.87 0 0 1 14.36-34.3A46.87 46.87 0 0 1 168.1 40a48.66 48.66 0 0 1 41.47 24H240a8 8 0 0 1 5.66 13.66Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="instagram" viewBox="0 0 256 256">
        <path fill="currentColor" d="M128 80a48 48 0 1 0 48 48a48.05 48.05 0 0 0-48-48Zm0 80a32 32 0 1 1 32-32a32 32 0 0 1-32 32Zm48-136H80a56.06 56.06 0 0 0-56 56v96a56.06 56.06 0 0 0 56 56h96a56.06 56.06 0 0 0 56-56V80a56.06 56.06 0 0 0-56-56Zm40 152a40 40 0 0 1-40 40H80a40 40 0 0 1-40-40V80a40 40 0 0 1 40-40h96a40 40 0 0 1 40 40ZM192 76a12 12 0 1 1-12-12a12 12 0 0 1 12 12Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="linkedin" viewBox="0 0 24 24">
        <path fill="currentColor" d="M6.94 5a2 2 0 1 1-4-.002a2 2 0 0 1 4 .002zM7 8.48H3V21h4V8.48zm6.32 0H9.34V21h3.94v-6.57c0-3.66 4.77-4 4.77 0V21H22v-7.93c0-6.17-7.06-5.94-8.72-2.91l.04-1.68z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="nav-icon" viewBox="0 0 16 16">
        <path d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="close" viewBox="0 0 16 16">
        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="navbar-icon" viewBox="0 0 16 16">
        <path d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z" />
    </symbol>
    </svg> 
    <nav>

    </nav>

    <div class="flex-box">

        <div class="left">
            <div class="big-img">
                <img src="${product.listImage.get(0).imageProduct_url}" alt="?" onclick="openFullscreen(this)" />
            </div>
            <div class="images">
                <div class="small-img">
                    <img
                        src="${product.listImage.get(0).imageProduct_url}"
                        onclick="showImg(this.src)"
                        />
                </div>
                <div class="small-img">
                    <img
                        src="${product.listImage.get(1).imageProduct_url}"
                        onclick="showImg(this.src)"
                        />
                </div>
                <div class="small-img">
                    <img
                        src="${product.listImage.get(2).imageProduct_url}"
                        onclick="showImg(this.src)"
                        />
                </div>
                <div class="small-img">
                    <img
                        src="${product.listImage.get(3).imageProduct_url}"
                        onclick="showImg(this.src)"
                        />
                </div>
            </div>
        </div>

        <div class="right">

            <div class="pname">${product.product_name}</div>

            <div class="star-rating">
                <ul class="list-inline">
                    <li class="list-inline-item"><i class="fa fa-star"></i></li>
                    <li class="list-inline-item"><i class="fa fa-star"></i></li>
                    <li class="list-inline-item"><i class="fa fa-star"></i></li>


                </ul>
            </div>

            <i class="d-flex justify-content-between">
                <h4>
                    <p class="small text-dark">
                        <s>${Feature.format(product.product_originPrice)}VND</s>
                    </p>
                </h4>
            </i>

            <i class="d-flex justify-content-between mb-3">
                <h4 class="text-danger mb-0">
                    ${Feature.format(Feature.calculateSalePrice(product.product_originPrice,product.product_percenSale))}VND
                </h4>
            </i>
            <div class="size">
                <p>Type :</p>
                <c:forEach items="${product.listType}" var="type">
                    <a class="btn btn-outline-dark fs-4 ms-3 <c:if test="${pType != null && type.type_id == pType.type_id}">active</c:if>" href="product?product_id=${product.product_id}&type=${type.type_id}<c:if test="${param.color != null}">&color=${param.color}</c:if>">${type.type_describes}</a>
                </c:forEach>
            </div>
            <div class="size">
                <p>Color :</p>
                 <c:if test="${pType != null}">
                <c:forEach items="${pType.listColor}" var="color">
                    <a class="btn btn-outline-dark fs-4 ms-3 <c:if test="${color.color_id == param.color}">active</c:if>" href="product?product_id=${product.product_id}&type=${requestScope.pType.type_id}&color=${color.color_id}">${color.color_name}</a>
                </c:forEach>    
                </c:if>
                    <c:if test="${pType == null}">
                         <c:forEach items="${product.listColor}" var="color">
                    <a class="btn btn-outline-dark fs-4 ms-3 <c:if test="${color.color_id == param.color}">active</c:if>" href="product?product_id=${product.product_id}&color=${color.color_id}">${color.color_name}</a>
                </c:forEach>   
                    </c:if>
                
            </div>
            <script>
                // Lấy tất cả các phần tử có class 'psize'
                const sizes = document.querySelectorAll('.psize');

                sizes.forEach(size => {
                    // Thêm sự kiện click cho từng phần tử
                    size.addEventListener('click', function () {
                        // Xóa class 'active' từ tất cả các phần tử
                        sizes.forEach(s => s.classList.remove('active'));
                        // Thêm class 'active' vào phần tử được click
                        this.classList.add('active');
                    });
                });
                function getUrlParameter(name) {
                    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                    var results = regex.exec(location.search);
                    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
                }


                var type_id = getUrlParameter('type');
                var color_id = getUrlParameter('color');

                console.log("Type ID:", type_id);
                console.log("Color ID:", color_id);
                window.onload = function () {
                    var quantityInput = document.getElementById("quantity");
                    var addToCartButton = document.querySelector(".cart-btn");
                    var buyButton = document.querySelector(".buy-btn");
                    var availableStock = parseInt(quantityInput.max);

                    function showMessage(message) {
                        var messageElement = document.getElementById("message");
                        messageElement.textContent = message;
                        messageElement.style.color = "red";
                        messageElement.style.marginTop = "10px"; // thêm khoảng cách trên để tách biệt thông báo
                    }
                    // Disable buttons if type_id or color_id is missing
                    if (!type_id || !color_id) {
                        addToCartButton.disabled = true;
                        buyButton.disabled = true;
                        showMessage("Please select a type and color.");
                    } else if (availableStock === 0) {
                        addToCartButton.disabled = true;
                        buyButton.disabled = true;
                        showMessage("Sold Out");
                    }
                }
            </script>
            <div class="quantity">
                <p>Quantity : </p>                 
                <span class="btn btn1" onclick="productAction(-1)"><i class="fa fa-minus"></i></span>
                <input
                    id="quantity"
                    type="number"
                    min="1"
                    max="${requestScope.db.getStockByProductCharacterics(requestScope.product.product_id, requestScope.pType.type_id, requestScope.pColor.color_id)}"
                    value="1"
                    name="numberOfProduct"
                    class="input-quantity"
                    readonly
                    />
                <span class="btn btn1" onclick="productAction(1)"><i class="fa fa-plus"></i></span>
                <p>Available : <c:if test="${param.type != null and param.color != null}">
                          ${requestScope.db.getStockByProductCharacterics(requestScope.product.product_id, requestScope.pType.type_id, requestScope.pColor.color_id)}  
                </c:if>
                    <c:if test="${param.type == null or param.color == null}">
                        ${requestScope.db.quantityOfProductById(product.product_id)}
                    </c:if></p>
            </div>
            <p id="message" style="margin-top: 10px;"></p> 
            <div class="btn-box">
                <input type="hidden" name="idProduct" value="${product.product_id}" />
                <input
                    type="hidden"
                    name="priceOfProduct"
                    value=" ${Feature.format(Feature.calculateSalePrice(product.product_originPrice,product.product_percenSale))}"
                    />
                <button class="cart-btn" id="cart-btn-id">
                    Add to Cart
                </button>
                <button onclick="userActionBuy(this)" class="buy-btn">Buy Now</button>
                <button onclick="userActionWishList(this)" class="wishlist-btn">Wish List</button>
            </div>
        </div>
    </div>
    <div class="flex-box">
        <div class="row">
            <div class="col-md-12 mt-3">
                <div class="card rounded-4">
                    <div class="card-header rounded-top-4 bg-white">
                        <h4>Description</h4>
                    </div>
                    <div class="card-body rounded-bottom-4">
                        <p>${product.product_Describes}</p>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="flex-box">
        <div class="container p-0">
            <div class="card shadow-sm rounded-4 ps-3 pe-3 pt-1">
                <a href="viewstore?store_id=${infoStore.user_id}">
                        <div class="card-body">
                            <div class="row g-2">
                                <div class="col-2">
                                    <div class="ratio ratio-1x1 w-50">
                                        <img class="w-100 border rounded-4" src="${infoStore.store_image}" alt="?"/>
                                    </div>
                                </div>

                                <div class="col-10 ps-4 pe-4">
                                    <div class="row g-3">
                                        <div class="col-4">
                                            <h5 class="fw-bold text-primary">${infoStore.store_name}</h5>
                                            <h5>${infoStore.store_phone}</h5>
                                        </div>
                                        <div class="col-4">
                                            <h5><i class="fi fi-rs-marker"></i>${infoStore.store_address}</h5>
                                            <h5>Rate: ${store.avgStarRate(i.user_id)}</h5>
                                        </div>
                                        <div class="col-4">
                                            <h5><i class="fi fi-sr-flame"></i>1000  Followers</h5>
                                            <h5>Chat</h5>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>


                  


                </a>
            </div>  
            </a>
        </div>

    </div>

    <div class="flex-box">
        <div class="card rounded-4 w-100">
            <div class="card-header rounded-top-4">
                <h4>Product Review</h4>
            </div>
            <div class="card-body rounded-bottom-4">
                <div class="row p-4 gy-4">
                    <c:choose>
                        <c:when test="${feedback.isEmpty()}">
                            <p class="text-center">No results.</p>
                        </c:when>
                        <c:otherwise>


                            <c:forEach items="${feedback}" var="i">
                                <div class="col-12">

                                    <div class="row">
                                        <div class="col-2">
                                            <div class="ratio-1x1 w-100">
                                                <img style="border-radius: 50% !important; width: 50% !important;" class="w-100 border" src="image\image_avatar_user\avataruser(1).jpg" alt=""/>
                                            </div>
                                        </div>
                                        <div class="col-4">

                                            <h5>Phạm Hoàng Anh <span class="text-warning"><c:forEach begin="1" end="${i.feedback_rateStars}"><i class="fa fa-star pe-2"></i></c:forEach></span> </h5  > 
                                            <p>${i.feedback_comment}  </p>
                                        </div>
                                    </div>

                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

    </div>
    <div class="container-xl">
        <div class="row">
            <div class="col-md-12">
                <h2>Products <b>Related</b></h2>
                <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="0">

                    <!-- Wrapper for carousel items -->
                    <div class="carousel-inner">
                        <div class="item carousel-item active">
                            <div class="row">
                                <div class="col-sm-3">
                                    <a href="product?product_id=5">
                                        <div class="thumb-wrapper">
                                            <span class="wish-icon"><i class="fa fa-heart-o"></i></span>
                                            <div class="img-box">
                                                <img src="image\image_product\image_coat\image_Jacket\1_Jacket_DirtyCoins_01.jpg" class="img-fluid" alt="">									
                                            </div>
                                            <div class="thumb-content">
                                                <h4>Jacket DirtyCoins</h4>									
                                                <div class="star-rating">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>

                                                    </ul>
                                                </div>
                                                <p class="item-price"><strike>${Feature.format(product.product_originPrice)}VND</strike> <b>${Feature.format(Feature.calculateSalePrice(product.product_originPrice,product.product_percenSale))}</b></p>
                                                <a href="#" class="btn btn-primary">Add to WishList</a>
                                            </div>						
                                        </div>
                                    </a>


                                </div>
                                <div class="col-sm-3">
                                    <a href="product?product_id=6">
                                        <div class="thumb-wrapper">
                                            <span class="wish-icon"><i class="fa fa-heart-o"></i></span>
                                            <div class="img-box">
                                                <img src="image\image_product\image_coat\image_Jacket\2_Jacket_Nike_01.jpg" class="img-fluid" alt="">									
                                            </div>
                                            <div class="thumb-content">
                                                <h4>Jacket Nike</h4>									
                                                <div class="star-rating">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>


                                                    </ul>
                                                </div>
                                                <p class="item-price"><strike>${Feature.format(product.product_originPrice)}VND</strike> <b>1.000.000VND</b></p>
                                                <a href="#" class="btn btn-primary">Add to WishList</a>
                                            </div>						
                                        </div>
                                    </a>
                                </div>
                                <div class="col-sm-3">
                                    <a href="product?product_id=7">
                                        <div class="thumb-wrapper">
                                            <span class="wish-icon"><i class="fa fa-heart-o"></i></span>
                                            <div class="img-box">
                                                <img src="image\image_product\image_coat\image_Jacket\3_Jacket_Adidas_01.jpg" class="img-fluid" alt="">									
                                            </div>
                                            <div class="thumb-content">
                                                <h4>Jacket Adidas</h4>									
                                                <div class="star-rating">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>

                                                    </ul>
                                                </div>
                                                <p class="item-price"><strike>${Feature.format(product.product_originPrice)}VND</strike> <b>1.250.000VND</b></p>
                                                <a href="#" class="btn btn-primary">Add to WishList</a>
                                            </div>						
                                        </div>
                                    </a>
                                </div>
                                <div class="col-sm-3">
                                    <a href="product?product_id=8">
                                        <div class="thumb-wrapper" >
                                            <span class="wish-icon"><i class="fa fa-heart-o"></i></span>
                                            <div class="img-box">
                                                <img src="image\image_product\image_coat\image_Jacket\4_Jacket_Paradox_01.jpg" class="img-fluid" alt="">									
                                            </div>
                                            <div class="thumb-content">
                                                <h4>Jacket Paradox</h4>									
                                                <div class="star-rating">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>

                                                    </ul>
                                                </div>
                                                <p class="item-price"><strike>${Feature.format(product.product_originPrice)}VND</strike> <b>1.200.000VND</b></p>
                                                <a href="#" class="btn btn-primary">Add to WishList</a>
                                            </div>						
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>

                    </div>


                </div>
            </div>
        </div>
    </div>


    <div id="modal" class="w3-modal w3-black" style="padding-top:0" onclick="this.style.display = 'none'">
        <span class="w3-button w3-black w3-xlarge w3-display-topright">&times;</span>
        <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
            <img id="img" class="w3-image w-50">
            <p id="caption"></p>
        </div>
    </div>
    <script src="${libJquery}"></script>                                             
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        let bigImg = document.querySelector(".big-img img");
        function showImg(pic) {
            bigImg.src = pic;
        }
    </script>

    <script>
        function productAction(val) {
            var x = Number(document.getElementById("quantity").value);
            var sum = x + val;
            var max = Number(
                    document.getElementById("quantity").getAttribute("max")
                    );
            var min = Number(
                    document.getElementById("quantity").getAttribute("min")
                    );
            if (sum <= max && sum >= min) {
                document.getElementById("quantity").value = x + val;
            }
        }
        function userActionBuy(btn) {
            document.order.action = "product";
            document.order.submit();
        }
        function userActionWishList(btn) {
            document.order.action = "wishlist";
            document.order.submit();
        }

        // Modal Image Gallery
        function openFullscreen(element) {
            document.getElementById("img").src = element.src;
            document.getElementById("modal").style.display = "block";
            const caption = document.getElementById('caption');
            captionText.innerHTML = element.alt;
            console.log('HI');
        }
    </script>
    <script>
        // Lấy tất cả các phần tử có class 'psize'
        const sizes = document.querySelectorAll('.psize');

        sizes.forEach(size => {
            // Thêm sự kiện click cho từng phần tử
            size.addEventListener('click', function () {
                // Xóa class 'active' từ tất cả các phần tử
                sizes.forEach(s => s.classList.remove('active'));
                // Thêm class 'active' vào phần tử được click
                this.classList.add('active');
            });
        });
        function getUrlParameter(name) {
            name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
            var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
            var results = regex.exec(location.search);
            return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
        }


        var type_id = getUrlParameter('type');
        var color_id = getUrlParameter('color');

        console.log("Type ID:", type_id);
        console.log("Color ID:", color_id);
        window.onload = function () {
            var quantityInput = document.getElementById("quantity");
            var addToCartButton = document.querySelector(".cart-btn");
            var buyButton = document.querySelector(".buy-btn");
            var availableStock = parseInt(quantityInput.max);

            if (type_id ===null || !color_id === null) {
                addToCartButton.disabled = true;
                buyButton.disabled = true;


                var missingSelectionMessage = document.createElement("p");
                missingSelectionMessage.textContent = "Please select a type and color.";
                missingSelectionMessage.style.color = "red";
                var quantityDiv = document.querySelector(".quantity");
                quantityDiv.appendChild(missingSelectionMessage);
            } else if (availableStock === 0) {
                addToCartButton.disabled = true;
                buyButton.disabled = true;


                var soldOutMessage = document.createElement("p");
                soldOutMessage.textContent = "Sold Out";
                soldOutMessage.style.color = "red";
                var quantityDiv = document.querySelector(".quantity");
                quantityDiv.appendChild(soldOutMessage);
            }
        }
    </script>
    <script>
        function getUrlParameter(name) {
            name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
            var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
            var results = regex.exec(location.search);
            return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
        }
        const btn_cart = document.getElementById('cart-btn-id');

        btn_cart.addEventListener('click', () => {
            const type_id = Number(getUrlParameter('type'));
            const product_id = Number(getUrlParameter("product_id"));
            const color_id = Number(getUrlParameter("color"));
            const quantity = Number(document.getElementById('quantity').value);
            $.ajax({
                url: "/tikilazapee/cart",
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    'product_id': product_id,
                    'type_id': type_id,
                    'color_id': color_id,
                    'quantity': quantity
                }),
                success: function () {
                    location.href = "cart";
                }
            });
        });

    </script>
</body>
</html>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.*" %>
<%@page import="Model.*" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Ansonika">
    <title>Seller Manage Blog</title>

    <!-- Favicons-->
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" type="image/x-icon" href="img/apple-touch-icon-57x57-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="img/apple-touch-icon-72x72-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="img/apple-touch-icon-114x114-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="img/apple-touch-icon-144x144-precomposed.png">
	
    <!-- GOOGLE WEB FONT -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <!-- BASE CSS -->
    <c:url var="bootstrap" value="/CSS/bootstrap.min.css"/>
    <c:url var="style" value="/CSS/styleBlogForSeller.css"/>
    <c:url var="style1" value="/CSS/blogForSeller.css"/>
    <link href="${bootstrap}" rel="stylesheet">
    <link href="${style}" rel="stylesheet">
	
	<!-- SPECIFIC CSS -->
    <link href="${style1}" rel="stylesheet">

    <!-- YOUR CUSTOM CSS -->

</head>

<body>
	
	<div id="page">
	

	
	<main class="bg_gray">
		<div class="container margin_30">
			<div class="page_header">
				<h1>Blog &amp; News</h1>
			</div>
			<!-- /page_header -->
			<div class="row">
				<div class="col-lg-9">
					<div class="widget search_blog d-block d-sm-block d-md-block d-lg-none">
						<div class="form-group">
							<input type="text" name="search" id="search" class="form-control" placeholder="Search..">
							<button type="submit"><i class="ti-search"></i></button>
						</div>
					</div>
					<!-- /widget -->
					<div class="row">
						<div class="col-md-6">
                                                    <c:forEach items="${listBlog}" var="b">
							<article class="blog">
								<figure>
                                                                    <a href="blog-post.html"><img style="width: 430px;
                                                                                                         height: auto" src="../${b.blog_image}" alt="">
										<div class="preview"><span>Edit</span></div>
									</a>
								</figure>
								<div class="post_info">
									<small>${category.getCategoryById(b.getCategory_id()).getCategory_name()} - ${b.blog_create_day}</small>
									<h2><a href="blog-post.html">${b.blog_title}</a></h2>
									<p>${Feature.formatBlogContext(b.getBlog_content())}...</p>
									<ul>
										<li>
											<div class="thumb"><img src="${ud.getUserById(b.user_id).getImage()}" alt=""></div> ${user.getUserById(b.user_id).getFullname()}
										</li>
										<li><i class="ti-comment"></i>20</li>
									</ul>
								</div>
							</article>
                                                </c:forEach>
							<!-- /article -->
						</div>
						<!-- /col -->
						<div class="col-md-6">
							<article class="blog">
								<figure>
									<a href="blog-post.html"><img src="img/blog-2.jpg" alt="">
										<div class="preview"><span>Read more</span></div>
									</a>
								</figure>
								<div class="post_info">
									<small>Category - 20 Nov. 2017</small>
									<h2><a href="blog-post.html">At usu sale dolorum offendit</a></h2>
									<p>Quodsi sanctus pro eu, ne audire scripserit quo. Vel an enim offendit salutandi, in eos quod omnes epicurei, ex veri qualisque scriptorem mei.</p>
									<ul>
										<li>
											<div class="thumb"><img src="img/avatar.jpg" alt=""></div> Admin
										</li>
										<li><i class="ti-comment"></i>20</li>
									</ul>
								</div>
							</article>
							<!-- /article -->
						</div>
					</div>
					<!-- /row -->

					<div class="pagination__wrapper no_border add_bottom_30">
						<ul class="pagination">
							<li><a href="#0" class="prev" title="previous page">&#10094;</a></li>
							<li>
								<a href="#0" class="active">1</a>
							</li>
							<li>
								<a href="#0">2</a>
							</li>
							<li>
								<a href="#0">3</a>
							</li>
							<li>
								<a href="#0">4</a>
							</li>
							<li><a href="#0" class="next" title="next page">&#10095;</a></li>
						</ul>
					</div>
					<!-- /pagination -->

				</div>
				<!-- /col -->

				<aside class="col-lg-3">
					<div class="widget search_blog d-none d-sm-none d-md-none d-lg-block">
						<div class="form-group">
							<input type="text" name="search" id="search_blog" class="form-control" placeholder="Search..">
							<button type="submit"><i class="ti-search"></i></button>
						</div>
					</div>
					<!-- /widget -->
					<div class="widget">
						<div class="widget-title">
							<h4>Latest Post</h4>
						</div>
						<ul class="comments-list">
							<li>
								<div class="alignleft">
									<a href="#0"><img src="img/blog-5.jpg" alt=""></a>
								</div>
								<small>Category - 11.08.2016</small>
								<h3><a href="#" title="">Verear qualisque ex minimum...</a></h3>
							</li>
							<li>
								<div class="alignleft">
									<a href="#0"><img src="img/blog-6.jpg" alt=""></a>
								</div>
								<small>Category - 11.08.2016</small>
								<h3><a href="#" title="">Verear qualisque ex minimum...</a></h3>
							</li>
							<li>
								<div class="alignleft">
									<a href="#0"><img src="img/blog-4.jpg" alt=""></a>
								</div>
								<small>Category - 11.08.2016</small>
								<h3><a href="#" title="">Verear qualisque ex minimum...</a></h3>
							</li>
						</ul>
					</div>
					<!-- /widget -->
					<div class="widget">
						<div class="widget-title">
							<h4>Categories</h4>
						</div>
						<ul class="cats">
							<li><a href="#">Food <span>(12)</span></a></li>
							<li><a href="#">Places to visit <span>(21)</span></a></li>
							<li><a href="#">New Places <span>(44)</span></a></li>
							<li><a href="#">Suggestions and guides <span>(31)</span></a></li>
						</ul>
					</div>
					<!-- /widget -->
					<div class="widget">
						<div class="widget-title">
							<h4>Popular Tags</h4>
						</div>
						<div class="tags">
							<a href="#">Food</a>
							<a href="#">Bars</a>
							<a href="#">Cooktails</a>
							<a href="#">Shops</a>
							<a href="#">Best Offers</a>
							<a href="#">Transports</a>
							<a href="#">Restaurants</a>
						</div>
					</div>
					<!-- /widget -->
				</aside>
				<!-- /aside -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</main>
	<!--/main-->
	

	<!--/footer-->
	</div>
	<!-- page -->
	
	<div id="toTop"></div><!-- Back to top button -->
	
	<!-- COMMON SCRIPTS -->
    <script src="JS/common_scripts.min.js"></script>
    <script src="JS/mainBlogSeller.js"></script>

		
</body>
</html>
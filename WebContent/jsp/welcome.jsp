<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]>      <html lang="en" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="en" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="en" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <head>
    	<!-- meta character set -->
    	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     	<!-- Always force latest IE rendering engine or request Chrome Frame -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Sharanya Books</title>		
		<!-- Meta Description -->
        <meta name="description" content="Blue One Page Creative Template">
        <meta name="keywords" content="one page, single page, onepage, responsive, parallax, creative, business, html5, css3, css3 animation">
        <meta name="author" content="Muhammad Morshed">
		
		<!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS
		================================================== -->
		
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
		
		<!-- Fontawesome Icon font -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.fancybox.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slit-slider.css">
		<!-- bootstrap.min -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/animate.css">
		<!-- Main Stylesheet -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

		<!-- Modernizer Script for old Browsers -->
        <script src="<%=request.getContextPath()%>/js/modernizr-2.6.2.min.js"></script>

    </head>
	
    <body id="body">

		<!-- preloader -->
		<div id="preloader">
            <div class="loder-box">
            	<div class="battery"></div>
            </div>
		</div>
		<!-- end preloader -->

        <!--
        Fixed Navigation
        ==================================== -->
        <header id="navigation" class="navbar-inverse navbar-fixed-top animated-header">
            <div class="container">
                <div class="navbar-header">
                    <!-- responsive nav button -->
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
                    </button>
					<!-- /responsive nav button -->
					
					<!-- logo -->
					<h1 class="navbar-brand">
						<a href="#body">Sharanya Books</a>
					</h1>
					<!-- /logo -->
                </div>

				<!-- main nav -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <ul id="nav" class="nav navbar-nav">
                        <li><a href="#body">Home</a></li>
                        <li><a href="#service">Service</a></li>
                        <li><a href="#portfolio">portfolio</a></li>
                        <li><a href="#contact">Contact</a></li>
                    </ul>
                </nav>
				<!-- /main nav -->
				
            </div>
        </header>
        <!--
        End Fixed Navigation
        ==================================== -->
		
		<main class="site-content" role="main">
		
        <!--
        Home Slider
        ==================================== -->
		
		<section id="home-slider">
            <div id="slider" class="sl-slider-wrapper">

				<div class="sl-slider">
				
					<div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="-25" data-slice2-rotation="-25" data-slice1-scale="2" data-slice2-scale="2">

						<div class="bg-img bg-img-1"></div>

						<div class="slide-caption">
                            <div class="caption-content">
                                <h2 class="animated fadeInDown">Sharanya Books</h2>
                                <span class="animated fadeInDown">Manage Books,Become Book SelectionFree</span>
                                <a href="<%=request.getContextPath()%>/jsp/registeruser.jsp" class="btn btn-blue btn-effect">Sign Up</a>
							 	<a href="<%=request.getContextPath()%>/jsp/login.jsp" class="btn btn-blue btn-effect">Login</a>
								 <span>India's Fastest Online Shopping Destination</span>	
                            </div>
                        </div>
						
					</div>
					
					<div class="sl-slide" data-orientation="vertical" data-slice1-rotation="10" data-slice2-rotation="-15" data-slice1-scale="1.5" data-slice2-scale="1.5">
					
						<div class="bg-img bg-img-2"></div>
						<div class="slide-caption">
                            <div class="caption-content">
                                <h2>Shranya Shop</h2>
                                <span>Manage Buying, With Secure Transaction</span>
                               
                                <a href="<%=request.getContextPath()%>/jsp/registeruser.jsp" class="btn btn-blue btn-effect">Sign Up</a>
								<a href="<%=request.getContextPath()%>/jsp/login.jsp" class="btn btn-blue btn-effect">Login</a>
								 <span>Purchase Products of your choice</span>
			
								
                           
                            </div>
                        </div>
						
					</div>
					
					<div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="3" data-slice2-rotation="3" data-slice1-scale="2" data-slice2-scale="1">
						
						<div class="bg-img bg-img-3"></div>
						<div class="slide-caption">
                            <div class="caption-content">
                                <h2>Sharanya Art</h2>
                                <span></span>
                                <a href="<%=request.getContextPath()%>/jsp/registeruser.jsp" class="btn btn-blue btn-effect">Join US</a>
                            </div>
                        </div>

					</div>

				</div><!-- /sl-slider -->

                <!-- 
                <nav id="nav-arrows" class="nav-arrows">
                    <span class="nav-arrow-prev">Previous</span>
                    <span class="nav-arrow-next">Next</span>
                </nav>
                -->
                
                <nav id="nav-arrows" class="nav-arrows hidden-xs hidden-sm visible-md visible-lg">
                    <a href="javascript:;" class="sl-prev">
                        <i class="fa fa-angle-left fa-3x"></i>
                    </a>
                    <a href="javascript:;" class="sl-next">
                        <i class="fa fa-angle-right fa-3x"></i>
                    </a>
                </nav>
                

				<nav id="nav-dots" class="nav-dots visible-xs visible-sm hidden-md hidden-lg">
					<span class="nav-dot-current"></span>
					<span></span>
					<span></span>
				</nav>

			</div><!-- /slider-wrapper -->
		</section>
		
        <!--
        End Home SliderEnd
        ==================================== -->
			
			<!-- about section -->
			<section id="about" >
				<div class="container">
					<div class="row">
						<div class="col-md-4 wow animated fadeInLeft">
							<div class="recent-works">
								<h3>Recent Works</h3>
								<div id="works">
									<div class="work-item">
										<p>Purchase Sports Books> <br> This is better way to improve the Books & Reduce Sports Value</p>
									</div>
									<div class="work-item">
										<p>Books Gym <br><br> Going to Gym improves Gym Books Management</p>
									</div>
									<div class="work-item">
										<p>Eating Habits <br><br> Having nice , Less Oily Food</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-7 col-md-offset-1 wow animated fadeInRight">
							<div class="welcome-block">
								<h3>Welcome To Ecommerce</h3>								
						     	 <div class="message-body">
									<img src="<%=request.getContextPath()%>/img/member-1.jpg" class="pull-left" alt="member">
						       		<p>Sports Books, Bolly Wood News, Politics News.</p>
						     	 </div>
						       	<a href="#" class="btn btn-border btn-effect pull-right">Read More</a>
						    </div>
						</div>
					</div>
				</div>
			</section>
			<!-- end about section -->
			
			
			<!-- Service section -->
			<section id="service">
				<div class="container">
					<div class="row">
					
						<div class="sec-title text-center">
							<h2 class="wow animated bounceInLeft">Service</h2>
							<p class="wow animated bounceInRight">The Key Features of our Job</p>
						</div>
						
						<div class="col-md-3 col-sm-6 col-xs-12 text-center wow animated zoomIn">
							<div class="service-item">
								<div class="service-icon">
									<i class="fa fa-home fa-3x"></i>
								</div>
								<h3>Gym</h3>
								<p>Let us help you get strong, balanced & flexible – mentally and physically. In order to help you achieve your fitness goals, City Fitness offers 3 free personal training 
								sessions and a complete selection of cardio equipment, free weights, weight machines and fitness classes </p>
							</div>
						</div>
					
						<div class="col-md-3 col-sm-6 col-xs-12 text-center wow animated zoomIn" data-wow-delay="0.3s">
							<div class="service-item">
								<div class="service-icon">
									<i class="fa fa-tasks fa-3x"></i>
								</div>
								<h3>Lose Weight</h3>
								<p>ose It! is designed with one goal - to help you lose weight in a Bookshy, sustainable way. No magic pills, no crazy diets - just a simple, easy-to-use program that helps you stay in your calorie budget. But don't take our word for it.
								The results of Lose It! users speak for themselves.</p>
							</div>
						</div>
					
						<div class="col-md-3 col-sm-6 col-xs-12 text-center wow animated zoomIn" data-wow-delay="0.6s">
							<div class="service-item">
								<div class="service-icon">
									<i class="fa fa-clock-o fa-3x"></i>
								</div>
								<h3>Book SelectionTechniques</h3>
								<p>A few minutes of practice per day can help ease anxiety. 
								Research suggests that daily meditation may alter the brain’s neural pathways, making you more resilient to Book Selection</p>
							</div>
						</div>
					
						<div class="col-md-3 col-sm-6 col-xs-12 text-center wow animated zoomIn" data-wow-delay="0.9s">
							<div class="service-item">
								<div class="service-icon">
									<i class="fa fa-heart fa-3x"></i>
								</div>
								
								<h3>Food Habits</h3>
								<p>As life gets back into full swing after the holiday period it can be easy to slip back into old patterns. The start of a new year is a perfect time to identify key dietary behaviours that may be preventing you from reaching your goal weight</p>							
							</div>
						</div>
						
					</div>
				</div>
			</section>
			<!-- end Service section -->
			
			<!-- portfolio section -->
			<section id="portfolio">
				<div class="container">
					<div class="row">
					
						<div class="sec-title text-center wow animated fadeInDown">
							<h2>Book SelectionManagement Centers</h2>
							<p>Our Locations</p>
						</div>
						

						<ul class="project-wrapper wow animated fadeInUp">
							<li class="portfolio-item">
								<img src="<%=request.getContextPath()%>/img/portfolio/item.jpg" class="img-responsive" alt="Located at United States. Vogas">
								<figcaption class="mask">
									<h3>Wall street</h3>
									<p>Located at United States. Vogas</p>
								</figcaption>
								<ul class="external">
									<li><a class="fancybox" title="Araund The world" data-fancybox-group="works" href="img/portfolio/item.jpg"><i class="fa fa-search"></i></a></li>
									<li><a href=""><i class="fa fa-link"></i></a></li>
								</ul>
							</li>
							
							<li class="portfolio-item">
								<img src="<%=request.getContextPath()%>/img/portfolio/item2.jpg" class="img-responsive" alt="Located behid Newyork Public Park  ">
								<figcaption class="mask">
									<h3>Paris</h3>
									<p>Located behid Newyork Public Park </p>
								</figcaption>
								<ul class="external">
									<li><a class="fancybox" title="Wall street" href="img/slider/banner.jpg" data-fancybox-group="works" ><i class="fa fa-search"></i></a></li>
									<li><a href=""><i class="fa fa-link"></i></a></li>
								</ul>
							</li>
							
							<li class="portfolio-item">
								<img src="<%=request.getContextPath()%>/img/portfolio/item3.jpg" class="img-responsive" alt="Turkey Hospitality Solutions">
								<figcaption class="mask">
									<h3>Turkey</h3>
									<p>Turkey Hospitality Solutions </p>
								</figcaption>
								<ul class="external">
									<li><a class="fancybox" title="Behind The world" data-fancybox-group="works" href="img/portfolio/item3.jpg"><i class="fa fa-search"></i></a></li>
									<li><a href=""><i class="fa fa-link"></i></a></li>
								</ul>
							</li>
							
							<li class="portfolio-item">
								<img src="<%=request.getContextPath()%>/img/portfolio/item4.jpg" class="img-responsive" alt="Bangalore IT Book SelectionSolutions">
								<figcaption class="mask">
									<h3>India</h3>
									<p>Bangalore IT Book SelectionSolutions </p>
								</figcaption>
								<ul class="external">
									<li><a class="fancybox" title="Wall street 4" data-fancybox-group="works" href="img/portfolio/item4.jpg"><i class="fa fa-search"></i></a></li>
									<li><a href=""><i class="fa fa-link"></i></a></li>
								</ul>
							</li>
							
							<li class="portfolio-item">
								<img src="<%=request.getContextPath()%>/img/portfolio/item5.jpg" class="img-responsive" alt="Cricket Team Australia Regular Book SelectionManagement">
								<figcaption class="mask">
									<h3>Australia</h3>
									<p>Cricket Team Australia Regular Book SelectionManagement </p>
								</figcaption>
								<ul class="external">
									<li><a class="fancybox" title="Wall street 5" data-fancybox-group="works" href="img/portfolio/item5.jpg"><i class="fa fa-search"></i></a></li>
									<li><a href=""><i class="fa fa-link"></i></a></li>
								</ul>
							</li>
							
							<li class="portfolio-item">
								<img src="<%=request.getContextPath()%>/img/portfolio/item6.jpg" class="img-responsive" alt="Africa Home Book SelectionManagement">
								<figcaption class="mask">
									<h3>South Africa</h3>
									<p>Africa Home Book SelectionManagement </p>
								</figcaption>
								<ul class="external">
									<li><a class="fancybox" title="Wall street 6" data-fancybox-group="works" href="img/portfolio/item6.jpg"><i class="fa fa-search"></i></a></li>
									<li><a href=""><i class="fa fa-link"></i></a></li>
								</ul>
							</li>
						</ul>
						
					</div>
				</div>
			</section>
			<!-- end portfolio section -->
			
			<!-- Testimonial section -->
			<section id="testimonials" class="parallax">
				<div class="overlay">
					<div class="container">
						<div class="row">
						
							<div class="sec-title text-center white wow animated fadeInDown">
								<h2>What people say</h2>
							</div>
							
							<div id="testimonial" class=" wow animated fadeInUp">
								<div class="testimonial-item text-center">
									<img src="<%=request.getContextPath()%>/img/member-1.jpg" alt="Our Clients">
									<div class="clearfix">
										<span>Katty Flower</span>
										<p>I was going through a bad spell recently and this excellent Solution helped get re-focus and get back on track. Everything seemed to make so much sense. I found a lot of the subject material is covered by Occupational Therapists in work but put in a much easier to understand way / put in a better perspective. Thanks, Mike Cram.</p>
									</div>
								</div>
								<div class="testimonial-item text-center">
									<img src="<%=request.getContextPath()%>/img/member-1.jpg" alt="Our Clients">
									<div class="clearfix">
										<span> Jessica</span>
										<p>Jessica Ann Barazon is a renowned Booksh writer and Booksh enthusiast specializing in relationships, life improvement, ADHD, parenting, mental Booksh, fitness and weight loss. She completed her B.A. in Pychology from Minanao State. She is especially interested in feminist ethics, psychology, and mental Booksh awareness. Aside from writing she also loves food, dogs, fashion and travel.</p>
									</div>
								</div>
								<div class="testimonial-item text-center">
									<img src="<%=request.getContextPath()%>/img/member-1.jpg" alt="Our Clients">
									<div class="clearfix">
										<span>Sachin</span>
										<p>The worlds best Book SelectionManagement Solution</p>
									</div>
								</div>
							</div>
						
						</div>
					</div>
				</div>
			</section>
			<!-- end Testimonial section -->
			
			<!-- Price section -->
			<section id="price">
				<div class="container">
					<div class="row">
					
						<div class="sec-title text-center wow animated fadeInDown">
							<h2>Price</h2>
							<p>Our price for your  Book SelectionControl</p>
						</div>
						
						<div class="col-md-4 wow animated fadeInUp">
							<div class="price-table text-center">
								<span>Silver</span>
								<div class="value"><span>Rs 100</span><span></span><br>
                                <span>month</span> </div>
								<ul>
									<li>No Bonus Points</li>
									<li>No Bonus Points</li>
									<li>No Bonus Points</li>
									<li>No Bonus Points</li>
									<li><a href="#">sign up</a></li>
								</ul>
							</div>
						</div>
						
						<div class="col-md-4 wow animated fadeInUp" data-wow-delay="0.4s">
							<div class="price-table featured text-center">
								<span>Gold</span>
								<div class="value">
									<span>Rs 200</span><span></span><br>
									<span>month</span>
								</div>
								<ul>
									<li>Free Trial</li>
									<li>Free Trial</li>
									<li>Free Trial</li>
									<li>Free Trial</li>
									<li><a href="#">sign up</a></li>
								</ul>
							</div>
						</div>
						
						<div class="col-md-4 wow animated fadeInUp" data-wow-delay="0.8s">
							<div class="price-table text-center">
								<span>Diamond</span>
								<div class="value">
									<span>Rs 300</span><span></span><br>
									<span>month</span>
								</div>
								<ul>
									<li>All Bonus Points</li>
									<li>All Bonus Points</li>
									<li>All Bonus Points</li>
									<li>All Bonus Points</li>
									<li><a href="#">sign up</a></li>
								</ul>
							</div>
						</div>
		
					</div>
				</div>
			</section>
			<!-- end Price section -->
			
			<!-- Social section -->
			<section id="social" class="parallax">
				<div class="overlay">
					<div class="container">
						<div class="row">
						
							<div class="sec-title text-center white wow animated fadeInDown">
								<h2>FOLLOW US</h2>
								<p>Beautifully simple follow buttons to help you get followers on</p>
							</div>
							
							<ul class="social-button">
								<li class="wow animated zoomIn"><a href="#"><i class="fa fa-thumbs-up fa-2x"></i></a></li>
								<li class="wow animated zoomIn" data-wow-delay="0.3s"><a href="#"><i class="fa fa-twitter fa-2x"></i></a></li>
								<li class="wow animated zoomIn" data-wow-delay="0.6s"><a href="#"><i class="fa fa-dribbble fa-2x"></i></a></li>							
							</ul>
							
						</div>
					</div>
				</div>
			</section>
			<!-- end Social section -->
			
			<!-- Contact section -->
			<section id="contact" >
				<div class="container">
					<div class="row">
						
						<div class="sec-title text-center wow animated fadeInDown">
							<h2>Contact</h2>
						</div>
						
						<div class="col-md-5 wow animated fadeInRight">
							<address class="contact-details">
								<h3>Contact Us</h3>						
								<p><i class="fa fa-pencil"></i>Icare<span>Bangalore</span> <span>Karnataka</span></p><br>
								<p><i class="fa fa-phone"></i>+91 </p>
								<p><i class="fa fa-envelope"></i>icare@gmail.com</p>
							</address>
						</div>
			
					</div>
				</div>
			</section>
			<!-- end Contact section -->
			
			<section id="google-map">
				<div id="map-canvas" class="wow animated fadeInUp"></div>
			</section>
		
		</main>
		
		<footer id="footer">
			<div class="container">
				<div class="row text-center">
					<div class="footer-content">
						<div class="wow animated fadeInDown">
							<p>newsletter signup</p>
							<p>Get Cool Stuff! We hate spam!</p>
						</div>
						<form action="#" method="post" class="subscribe-form wow animated fadeInUp">
							<div class="input-field">
								<button type="submit" class="submit-icon">
									<i class="fa fa-paper-plane fa-lg"></i>
								</button>
							</div>
						</form>
						<div class="footer-social">
							<ul>
								<li class="wow animated zoomIn"><a href="#"><i class="fa fa-thumbs-up fa-3x"></i></a></li>
								<li class="wow animated zoomIn" data-wow-delay="0.3s"><a href="#"><i class="fa fa-twitter fa-3x"></i></a></li>
								<li class="wow animated zoomIn" data-wow-delay="0.6s"><a href="#"><i class="fa fa-skype fa-3x"></i></a></li>
								<li class="wow animated zoomIn" data-wow-delay="0.9s"><a href="#"><i class="fa fa-dribbble fa-3x"></i></a></li>
								<li class="wow animated zoomIn" data-wow-delay="1.2s"><a href="https://www.youtube.com/watch?v=kKPh-eZDoTU"><i class="fa fa-youtube fa-3x"></i></a></li>
							</ul>
						</div>
						
						<p>Book Selection Management is responsible for commerce</p>
					</div>
				</div>
			</div>
		</footer>
		
		<!-- Essential jQuery Plugins
		================================================== -->
		<!-- Main jQuery -->
        <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js"></script>
		<!-- Twitter Bootstrap -->
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		<!-- Single Page Nav -->
        <script src="<%=request.getContextPath()%>/js/jquery.singlePageNav.min.js"></script>
		<!-- jquery.fancybox.pack -->
        <script src="<%=request.getContextPath()%>/js/jquery.fancybox.pack.js"></script>
		<!-- Owl Carousel -->
        <script src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
        <!-- jquery easing -->
        <script src="<%=request.getContextPath()%>/js/jquery.easing.min.js"></script>
        <!-- Fullscreen slider -->
        <script src="<%=request.getContextPath()%>/js/jquery.slitslider.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.ba-cond.min.js"></script>
		<!-- onscroll animation -->
        <script src="<%=request.getContextPath()%>/js/wow.min.js"></script>
		<!-- Custom Functions -->
        <script src="<%=request.getContextPath()%>/js/main.js"></script>
    </body>
</html>
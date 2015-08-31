<!--This file is the header that is displayed on every page. It includes the header image and the navigation items-->

<html>

	<head>
    	
    	<title>
    		NPP9 1059 Final Project - Nicks Vinyls
    	</title>
    	
    	<link rel="stylesheet" type="text/css" href="FP.css">
	
	</head>
	
	<body>
	
		<div id="main_wrapper">
		
		<div id="nav">
		
		<img src="http://studentprojects.sis.pitt.edu/fall2014/npp9/IS1059/FinalProject/header2.jpg" width="950px"><br>
	
		<a href="index.php">Home</a> -
        <a href="FPgenres.php">Genres</a> -
        <a href="FPartists.php">Artists</a> -
        <a href="FPalbums.php">Albums</a> -
        <a href="FPreviews.php"> Reviews</a> -
        <a href="FPnews.php"> News</a> - 
       
         <?php 
         
         	session_start();
         	
         	if ($_SESSION['user_level'] == "01") {
    			echo '<a href="FPaccount.php"> Admin</a> - ';
    		}
    		else {
    			if($_SESSION['signed_in']) {
    				echo '<a href="FPaccount.php"> View Account</a> - ';
    			}
    		}
    		if($_SESSION['signed_in']) {
        		echo "<b>" . $_SESSION['user_name'] . '</b>: <a href="FPlogout.php">Sign out?</a>';
    		}
    		else {
        		echo '<a href="FPlogin.php">Sign in</a> or <a href="FPmakeuser.php">Create Account</a>.';
    		} 
    		echo "<hr>"; 
    		
    	?>
    	
    	</div>
    	
    	<div id="main">
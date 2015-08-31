<!--This file lets the user log out if they are logged in.-->

<?php

include 'FPheader.php';	//The header is inlcuded
	
session_start();	//The session is started.
session_destroy();	//The session is destroyed and the user is logged out.
	
//Lets the user know that they have been logged out and are preceeded to the forum homepage.
echo 'You have been logged out. <a href="index.php">Click here to preceed</a>.';

include 'FPfooter.php';					//The connect file is included. 
	
?>
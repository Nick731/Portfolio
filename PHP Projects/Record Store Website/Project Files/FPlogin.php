<!--This file is used to let the user login with an already existing account.-->

<?php

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included
 
session_start(); 	//The session is started.
 
echo '<h3>Log in</h3>';		//A header is printed

if($_SESSION['signed_in'] == true) {				//Checks to see if the user is already signed in.
    echo '<a href="FPlogout.php">Log out?</a>';	//If the user is already signed in, they are asked if they want to log out.
}
else {
    if($_SERVER['REQUEST_METHOD'] != 'POST') {
    	//The form for loggin in is printed with text boxes and a submit button.
        echo '<form method="post" action="">									
            	  Username: <input type="text" name="user_name" /><br>
            	  Password: <input type="password" name="user_password"><br>
            	  <input type="submit" value="Log in" />
         	  </form>';
    }
    else {     
    	$errors = "";										//A variable is created to store the errors.
    	
        if(!isset($_POST['user_name'])) {					//Checks to see if the username field is empyty.
            $errors .= 'Please enter a username.<br>';		//Tells the user to enter a username.
        }
         
        if(!isset($_POST['user_password'])) {				//Checks to see if the password field is empyty.
            $errors .= 'Please enter a password.<br>';		//Tells the user to enter a password.
        }
         
        echo $errors;										//The errors are displayed.
        
        //The query is made.
        $query = "SELECT * FROM customers WHERE user_name = '" . mysql_real_escape_string($_POST['user_name']) . "'AND user_password = '" . mysql_real_escape_string($_POST['user_password']) . "';";
                         
        $result = mysql_query($query);						//The query is run on the database.
            
        if(!$result) {										//If the query did not work.
            echo 'Sign in error'  . mysql_error();			//The error message is displayed.
        }
        else {
            if(mysql_num_rows($result) == 0) {				//If the query returns 0 results.
                echo 'Wrong username or password. <a href="FPlogin.php">Try again</a>.';			//An error message is displayed.
            }
            else {
                $_SESSION['signed_in'] = true;				//The session variable signed_in is set to true.
                    
                while($row = mysql_fetch_assoc($result)) {	//The while loop prints the results.
                	//The session variable are set.
                    $_SESSION['user_id'] = $row['user_id'];
                    $_SESSION['user_name'] = $row['user_name'];
                    $_SESSION['user_level'] = $row['user_level'];                 
                }
                //Once everything has been set, a welcome message is printed with the users username.
                echo 'Welcome, ' . $_SESSION['user_name'] . '. <a href="index.php">Click here to preceed</a>.';
                
            }
        }
    }
}

include 'FPfooter.php';					//The connect file is included. 

?>
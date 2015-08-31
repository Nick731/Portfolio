<!--This file is used to create a new user. Once the user has been created, they are prometed to log in.-->

<?php 

session_start();			//The session is started.

include 'FPheader.php';	//The header is inlcuded
include 'FPconnect.php';	//The connect file is inlcuded
 
echo '<h3>Sign up</h3>';	//A header is printed
 
if($_SERVER['REQUEST_METHOD'] != 'POST') {	//If the form has not been submitted already, the for is displayed.
    echo '<form method="post" action="">
              Username: <input type="text" name="user_name" /><br><br>
        	  Password: <input type="password" name="user_password"><br>
        	  Password again: <input type="password" name="user_pass_check"><br><br>
        	  Email: <input type="text" name="user_email"><br><br>
        	  <input type="submit" value="Create User" />
     	  </form>';
}
else {    
	$error = "";															//The error variable is created.
	
    if(!isset($_POST['user_name'])) {										//The username field is checked to see if it is empty.
        $errors .= 'Please enter a username.<br>';
    }
     
    if(isset($_POST['user_password'])) {									//The password field is checked to see if it is empty.
        if($_POST['user_password'] != $_POST['user_pass_check']) {			//Making sure the two entered passwords are the same.
            $errors .= 'Please make sure that your passwords match.<br>';	//Error message added.
        }
    } 
    else {
        $errors .= 'The password field cannot be empty.';	//Error message added.
    }
     
    echo $errors;	//The errors are printed.
    
    //The query is created.
    $query = "INSERT INTO customers(
    			user_name, 
    			user_password, 
    			user_email,
    			user_level,
    			albums_album_id)
            VALUES('" . 
                mysql_real_escape_string($_POST['user_name']) . "', '" . 
                mysql_real_escape_string($_POST['user_password']) . "', '" .
                mysql_real_escape_string($_POST['user_email']) . "', " .
                "3, 100)";
                       
    $result = mysql_query($query);		//The query is run on the database.
    if(!$result) {  						//Makes sure the query ran
        echo "invalid query: " . mysql_error() . "\n";	//mysql error is printed
        echo $query;
		
    }
    else {
        echo "Success. Please <a href='FPlogin.php'>log in</a>";	//Lets the user know their account was created.
    }
    
}

include 'FPfooter.php';					//The connect file is included. 

?>
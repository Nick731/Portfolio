<!--This file is used to determing wether the user is an administrator or regurlar user.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is inlcuded
include 'FPconnect.php'; 	//The connect file is included

if(!$_SESSION['signed_in']) {
        		echo 'You need to be signed in to view your account.';
}
else {

	$query = 'SELECT * from customers WHERE user_id ="' . $_SESSION['user_id'] . '"';
    		
	$result = mysql_query($query);		//The query is run on the database.
 
	if(!$result) {		//If there was a problem with the query.
    	echo 'The user level could not be fetched'  . mysql_error();	//mysql error is printed.
	} 
    		
	while($row = mysql_fetch_assoc($result)) { 
    		
    	if ($row['user_level'] == 1){	//Checking to see if the user is an admin
    		include 'FPadmin.php'; 	//The Admin file is included
    	}
    	else { 
    		include 'FPuser.php'; 	//The Admin file is included
    	}			
	}
}
    		
?>
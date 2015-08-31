<!--This file is used to delete a comment-->

<?php

session_start();	//The session is started.

include 'FPheader.php';	//The header is inlcuded
include 'FPconnect.php'; 	//The connect file is included

$query = 'DELETE from comments WHERE comment_id =' . $_GET['comment_id'];
    		
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The comment could not be delete.'  . mysql_error(); //An error message is printed.
}
else {
	echo 'The comment has been deleted.';
}

include 'FPfooter.php';					//The connect file is included. 

?>
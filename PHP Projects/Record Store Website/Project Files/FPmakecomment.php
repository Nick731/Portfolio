<!--This file is used to let users comment on news articles and store their comment in the database.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included.
include 'FPconnect.php';	//The connect file is inlcuded.
 
echo '<h2>Create New Comment</h2>';											//A header is displayed.

if($_SESSION['signed_in'] == false) {									//Checking to see if the user is signed in.
    echo 'Sorry, you have to be <a href="FPlogin.php">signed in</a> to write a comment.';
}
else {
    if($_SERVER['REQUEST_METHOD'] = 'POST') {  						//Making sure the form has not already been submitted. 
    	if (!isset($_POST['comment_content']) || empty($_POST['comment_content']) || !isset($_POST['news_id']) || empty($_POST['news_id'])) { 
    		echo 'Please fill in all fields.';
    	}
        $query = "INSERT INTO comments(
            		comment_content,
                    news_news_id,
                    customers_user_id) 
                VALUES('" . 
                   	mysql_real_escape_string($_POST['comment_content']) . "', " . 
                    mysql_real_escape_string($_POST['news_id']) . ", " . 
                    $_SESSION['user_id'] . ")";
                      
        $result = mysql_query($query);		//The query is run on the database.
        
        if(!$result) {		//If the query was unseccessful, a message is displayed.
        	 echo 'Error creating your comment. ' . mysql_error();
        }
        else {
        	echo 'Your comment has been created. Click <a href="FPselectcomments.php?news_id=' . $_POST['news_id'] . '">here</a>  to view it.';
        }
    }
}

include 'FPfooter.php';					//The connect file is included. 

?>
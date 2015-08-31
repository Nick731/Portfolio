<!--This file is used to let the user create a news article if they are an editor.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included.
include 'FPconnect.php';	//The connect file is inlcuded.
 
echo '<h2>Create News Article</h2>';											//A header is displayed.

if($_SESSION['signed_in'] == false) {									//Checking to see if the user is signed in.
    echo 'Sorry, you have to be <a href="FPlogin.php">signed in</a> to write a news article.';
}
else {
    if($_SERVER['REQUEST_METHOD'] != 'POST') {  						//Making sure the form has not already been submitted. 
    	
        
	echo '<form method="post" action="FPmakenews.php">'; 
	    echo 'News Article Name: <input type="text" name="news_name" /><br>
		  	  News Article Content:<br><textarea name="news_content" rows="10" cols="133"/></textarea><br>
		      <input type="submit" value="Create News Article" />
	   	  </form>';
        
    }
    else {
    	if (!isset($_POST['news_name']) || empty($_POST['news_name']) || !isset($_POST['news_content']) || empty($_POST['news_content'])) { 
    		echo 'Please fill in all fields.';
    	}
        $query = "INSERT INTO news(
            		news_name,
                    news_content,
                    customers_user_id) 
                VALUES('" . 
                   	mysql_real_escape_string($_POST['news_name']) . "', '" . 
                    mysql_real_escape_string($_POST['news_content']) . "', " . 
                    $_SESSION['user_id'] . ")";
                      
        $result = mysql_query($query);		//The query is run on the database.
        
        if(!$result) {		//If the query was unseccessful, a message is displayed.
        	 echo 'Error creating your news article. ' . mysql_error();
        }
        else {
        	echo 'Your news article has been created. Click <a href="FPnews.php">here</a>  to view all news articles.';
        }
    }
}

include 'FPfooter.php';					//The connect file is included. 

?>
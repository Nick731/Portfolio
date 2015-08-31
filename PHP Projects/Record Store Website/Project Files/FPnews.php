<!--This file is used to display all of the news articles.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is inlcuded
include 'FPconnect.php'; 	//The connect file is included



$query = 'SELECT * from customers WHERE user_id ="' . $_SESSION['user_id'] . '"';
    		
$result = mysql_query($query);		//The query is run on the database.
 
if(!$result) {		//If there was a problem with the query.
    echo 'The user level could not be fetched'  . mysql_error();	//mysql error is printed.
} 


    		
while($row = mysql_fetch_assoc($result)) { 
    		
    if ($row['user_level'] == 2){	//Checking to see if the user is an admin
    	echo '<a href="FPmakenews.php">Write A News Article</a>';
    }
}
    
$query = 'SELECT * FROM news LEFT JOIN customers on customers_user_id = user_id';



$result = mysql_query($query);		//The query is run on the database.
 
if(!$result) {		//If there was a problem wit hthe query.
    echo 'The news articles could not be displayed. '  . mysql_error();	//mysql error is printed.
} 
else {
	if(mysql_num_rows($result) == 0) {		//If the query returns an empty set.
        echo 'No news articles have been made yet.';
    } 
    else {	
    	echo '<h2>Music News</h2>';
    	//The table is built.
        echo '<table align="">'; 
             
        while($row = mysql_fetch_assoc($result)) {    	//The results of the query are printed.           
            echo '<tr>';
            echo '<td><h3><a href="FPselectcomments.php?news_id=' . $row['news_id'] . '">' . $row['news_name'] . '</a></h3><b>By: </b>' . $row['user_name'] . '<br><b>Date: </b>' . $row['news_date'];
        }
        echo '</table>';
   }
} 

include 'FPfooter.php';					//The connect file is included. 
 		
?>
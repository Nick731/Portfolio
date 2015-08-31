<!--This file is used to display all of the reviews-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included

$query = 'SELECT * From reviews,customers,albums where reviews.users_user_id = customers.user_id and reviews.albums_album_id = albums.album_id ORDER BY album_name ASC';
 
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The reviews could not be displayed.'  . mysql_error(); //An error message is printed.
}

    	 echo'<h3><a href="FPmakereview.php">Write A Review</a></h3>';
        //The table to display the results is built.
        echo '<h1>Reviews</h1>';
        echo '<div id="tablediv"><table border="1" align="center">
    			<td><b>Album Name</b>
    			<td><b>Review Name</b>
    			<td><b>Userame</b>
    			<td><b>Score</b>
    			<td><b>Review Content</b>
  			</tr>';
             
        while($row = mysql_fetch_assoc($result)) {	//The results are being printed.               
           echo '<tr>';
                    echo '<td>' . str_replace('_', ' ', $row['album_name']);
                    echo '<td>' . $row['review_name'];
                    echo '<td>' . $row['user_name'];
                	echo '<td>' . $row['review_score'];
                	echo '<td>' . $row['review_content'];
                	
                	if ($_SESSION['user_level'] == "01") {
    					echo '<td><a href="FPdeletereview.php?review_id=' . $row['review_id'] . '">DELETE</a>';
    				}
            echo '</tr>';
        }
        echo '</table></div>';

include 'FPfooter.php';					//The connect file is included. 

?>
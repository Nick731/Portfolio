<!--This file is used to display the user profile-->

<?php

echo '<h2>Welcome, ' . $_SESSION['user_name'] . '</h2>';

//This query is used to dislay the album that the user has on hold.
$query = 'SELECT album_name FROM albums LEFT JOIN customers on album_id = albums_album_id where user_id =' . $_SESSION['user_id'];
    		
$result = mysql_query($query);		//The query is run on the database.
 
if(!$result) {						//If there was a problem with the query.
    echo mysql_error();				//mysql error is printed.
} 
    		
while($row = mysql_fetch_assoc($result)) { 
    foreach ($row as $value){
    	if ($value != 'blank' || $value == ''){
			echo '<b>Album on hold: </b>' . str_replace('_', ' ', $row['album_name']) . '<br><br>';
		}
		else {
			echo 'You do not have any albums on reserve currently.<br><br>';
		}
	}
}




//This query is used to display all of the reviews the user has made.
$query = 'SELECT * FROM reviews LEFT JOIN albums on albums_album_id = album_id where users_user_id =' . $_SESSION['user_id'];
 
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The reviews could not be displayed.'  . mysql_error(); //An error message is printed.
}
if(mysql_num_rows($result) == 0) {		//If the query return 0 results.
        echo 'You have not written any reviews yet.<br><br>';	//An error message is displayed.
}
else {
	//The table to display the results is built.
	echo '<div id="tablediv"><table border="1" align="center" width="900" >
		<tr>
			<h2>Reviews From ' . $_SESSION['user_name'] . '</h2>
		<tr>
			<td><b>Album Name</b>
			<td><b>Review Name</b>
			<td><b>Score</b>
			<td><b>Review Content</b>
		</tr>';
			 
	while($row = mysql_fetch_assoc($result)) {	//The results are being printed.               
		echo '<tr>';
			echo '<td>' . str_replace('_', ' ', $row['album_name']);
			echo '<td>' . $row['review_name'];
			echo '<td>' . $row['review_score'];
			echo '<td>' . $row['review_content'];
			echo '<td><a href="FPdeletereview.php?review_id=' . $row['review_id'] . '">DELETE</a>';
			echo '</tr>';
	}
	echo '</table><br><br></div>';
}



//This query is used to display the comments the user has made.
$query = 'SELECT * FROM comments LEFT JOIN customers on customers_user_id = user_id where customers_user_id =' . $_SESSION['user_id'];
 
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The comments could not be displayed.'  . mysql_error(); //An error message is printed.
}
if(mysql_num_rows($result) == 0) {		//If the query return 0 results.
        echo 'You have not written any comments yet.<br><br>';	//An error message is displayed.
}
else {
	//The table to display the results is built.
	echo '<div id="tablediv"><table border="1" width="900" align="center">
		
			<h2>Comments From ' . $_SESSION['user_name'] . '<h2>
			<tr>
			<td><b>Date</b>
			<td><b>Comment</b>
		</tr>';
			 
	while($row = mysql_fetch_assoc($result)) {	//The results are being printed.               
		echo '<tr>';
			echo '<td>' . $row['comment_date'];
			echo '<td>' . $row['comment_content'];
			echo '<td><a href="FPdeletecomment.php?comment_id=' . $row['comment_id'] . '">DELETE</a>';
			echo '</tr>';
	}
	echo '</table><br><br></div>';
}

include 'FPfooter.php';					//The connect file is included. 

?>
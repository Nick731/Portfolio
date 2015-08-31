<!--This file is used to display the reviews for an album.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included

$album_id = $_GET['album_id'];			//The topic id is saved.
$artist_id = $_GET['artist_id'];	//The category id is saved.

$query = "SELECT * FROM albums left join artists on artists_artist_id = artist_id WHERE album_id='" . $_GET['album_id'] . "'";

$result = mysql_query($query);		//The query is run on the database.
 
if(!$result) {		//If there was a problem wit hthe query.
    echo 'The ablum information could not be displayed'  . mysql_error();	//mysql error is printed.
} 
else {
	 echo '<table>';
	 while($row = mysql_fetch_assoc($result)) {    	//The results of the query are printed.           
            echo '<tr>';
            echo '<td><img src="' . $row['album_image'] . '" height="300" width="300">';
            echo '<td><h2>' . str_replace('_', ' ', $row['album_name']) . '</h2><br>';
            echo '<b>Artist: </b>' . str_replace('_', ' ', $row['artist_name']) . '<br><br>';
            echo '<b>Price: </b>$' . $row['album_price'] . '<br><br>';
            echo '<b>Year: </b>' . $row['album_year'] . '<br><br>';
            echo '<b>Stock Available: </b>' . $row['album_stock'] . '<br><br>';
            
            
   
    	if($_SESSION['signed_in']){				//If the user is not signed in.
			echo '<form method="post" action="FPadditem.php">
			<input type="hidden" name="album_id" value="' . $row['album_id'] . '" />
			<input type="submit" value="Reserve A Copy" />';
			
			
			
				$query = 'SELECT * from customers WHERE user_id ="' . $_SESSION['user_id'] . '"'; 

				$results = mysql_query($query);									//The query is run on the database.

				while($row2 = mysql_fetch_assoc($results)) {     				//The query results are printed in the table.
					if($row2['albums_album_id'] != 100) {
						echo '<br><br>You have already reserved an album and you can only reserve one album at a time. 
						Reserving this will overwrite your old reservation.';
					}
				}
			}
		echo '</form>';
	}
	echo '</tr>';
	echo '</table>';
}
//The query is built.
$query = "SELECT * FROM  reviews LEFT JOIN customers ON reviews.users_user_id = customers.user_id WHERE  reviews.albums_album_id = '" . $album_id . "'";

$result = mysql_query($query);		//The query is run on the database.
 
if(!$result) {		//If there was a problem wit hthe query.
    echo 'The reviews could not be displayed'  . mysql_error();	//mysql error is printed.
} 
else {
	if(mysql_num_rows($result) == 0) {		//If the query returns an empty set.
        echo'<h3>No reviews written yet.</h3>';
    } 
    else {	
    	//The table is built.
        echo '<table border="1" align="center" width="900">
              <tr>
    			<th align="center" colspan="5"> <b>Reviews</th>
    		<tr>
    			<td><b>Review Name</b>
    			<td><b>Userame</b>
    			<td><b>Score</b>
    			<td><b>Review Content</b>
  			</tr>'; 
             
        while($row = mysql_fetch_assoc($result)) {    	//The results of the query are printed.           
            echo '<tr>';
			echo '<td>' . $row['review_name'];
			echo '<td>' . $row['user_name'];
			echo '<td>' . $row['review_score'];
			echo '<td>' . $row['review_content'];
			if ($_SESSION['user_level'] == "01") {
				echo '<td><a href="FPdeletereview.php?review_id=' . $row['review_id'] . '">DELETE</a>';
			}
            echo '</tr>';
        }
        echo '</table>';
        }
        if($_SESSION['signed_in']) { ?>
        <!--The form to make a new reply is built. Hidden inputs are used to pass data to the makereply file.-->
        <form method="post" action="FPreviewhandler.php">
        	<h4>Write A Review:</h4>
         	Review name: <input type="text" name="review_name" /><br>
         	<input type="hidden" name="album_id" value="<?php echo $album_id; ?>"/>
         	<input type="hidden" name="artist_id" value="<?php echo $artist_id ?>"/>
         	Score: <select name="review_score">
    			<option value="1">1</option>
    			<option value="2">2</option>
    			<option value="3">3</option>
    			<option value="4">4</option>
    			<option value="5">5</option>
    		</select><br>
    		Review Content:<br><textarea name="review_content" rows="10" cols="133"></textarea><br>
    		<input type="submit" value="Reply" />
		</form>
   <?php 
   	}
   	else {
   		echo 'Sign in to write a review.';
   	}
}

include 'FPfooter.php';					//The connect file is included. 

?>
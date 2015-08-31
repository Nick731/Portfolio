<!--This file is used to display all of the albums for a selected artist.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included

$query = 'SELECT * FROM albums LEFT JOIN artists on artists_artist_id = artist_id WHERE artists_artist_id="' . $_GET['artist_id'] . '" ORDER BY album_name ASC';		//The query is made.
 
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The albums could not be displayed.'  . mysql_error(); //An error message is printed.
}
else {
    if(mysql_num_rows($result) == 0) {		//If the query return 0 results.
        echo 'No albums defined yet for this artist.';	//An error message is displayed.
    }
    else {
    	echo '<h1>Albums</h1>';
        //The table to display the results is built.
        echo '<table border="0">';
             
        while($row = mysql_fetch_assoc($result)) {     //The query results are printed in the table.          
            echo '<tr>';
                echo '<td class="leftpart">';
                    echo '<img src="' . $row['album_image'] . '" height="235" width="235">';
                echo '<td>';
                	echo '<h3>Album: <a href="FPselectreviews.php?album_id=' . $row['album_id'] . '">' . str_replace('_', ' ', $row['album_name']) . '</a></h3>';
                	echo '<b>Artist: </b>' . str_replace('_', ' ', $row['artist_name']) . '<br><br>';
                	echo '<b>Price: </b>$' . $row['album_price'] . '<br><br>';
                	echo '<b>Copies Available: </b>' . $row['album_stock'];
                	echo '<br><br><form method="post" action="FPadditem.php">
                			<input type="hidden" name="album_id" value="' . $row['album_id'] . '" />
        	  				<input type="submit" value="Reserve A Copy" />';
        	  				
        	  				if($_SESSION['signed_in']){				//If the user is not signed in.
        	  				
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
                echo '</td>';
            echo '</tr>';
        }
        echo '</table>';
    }
}

include 'FPfooter.php';					//The connect file is included. 

?>
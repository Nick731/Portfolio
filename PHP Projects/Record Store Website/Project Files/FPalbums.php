<!--This file is used to display all of the albums.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included

$category_id = $_GET['category_id'];	//The category id is save in a variable.

//The query is built.
$query = "SELECT * from albums LEFT JOIN artists on artists_artist_id = artist_id  WHERE album_id != 100 AND album_stock > 0 ORDER BY album_name ASC";
 
$result = mysql_query($query);										//The query is run on the database.
 
if(!$result) {	//If there was a problem with the database.
    echo 'The albums could not be displayed.'  . mysql_error();		//mysql error is printed.
}
else {
    if(mysql_num_rows($result) == 0) {	//If the query returns an empty set.
        echo 'No albums defined yet.';
    }
    else {
    	echo '<h1>Albums</h1>';
        //The table is built.
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
                	if($_SESSION['signed_in']){				//If the user is not signed in.
                		echo '<br><br><form method="post" action="FPadditem.php">
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
                echo '</td>';
            echo '</tr>';
        }
        echo '</table>';
    }
}

include 'FPfooter.php';					//The connect file is included. 

?>
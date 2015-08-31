<!--This file is used to display all of the artists for a selected genre.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included

$query = "SELECT * FROM artists WHERE genres_genre_id='" . $_GET['genre_id'] . "' AND artist_name !='blank' ORDER BY artist_name ASC";		//The query is made.
 
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The genres could not be displayed.'  . mysql_error(); //An error message is printed.
}
else {
    if(mysql_num_rows($result) == 0) {		//If the query return 0 results.
        echo 'No artists fall under the ' . $_GET['genre_name'] . ' genre.';	//An error message is displayed.
    }
    else {
    	echo '<h1>Artists</h1>';
        //The table to display the results is built.
        echo '<table border="0">'; 
             
        while($row = mysql_fetch_assoc($result)) {	//The results are being printed.               
            echo '<tr>';
                echo '<td>';
                    echo '<h3><a href="FPselectalbums.php?artist_id=' . $row['artist_id'] . '">' . str_replace('_', ' ', $row['artist_name']) . '</a></h3>';
                echo '</td>';
            echo '</tr>';
        }
        echo '</table>';
    }
}

include 'FPfooter.php';					//The connect file is included. 

?>
<!--This file is used to display all of the genres-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included

$query = "SELECT * FROM genres where genre_name !='blank' ORDER BY genre_name ASC";		//The query is made.
 
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The genres could not be displayed.'  . mysql_error(); //An error message is printed.
}
else {
    if(mysql_num_rows($result) == 0) {		//If the query return 0 results.
        echo 'No genres defined yet.';	//An error message is displayed.
    }
    else {
    	echo '<h1>Genres</h1>';
        //The table to display the results is built.
        echo '<table border="0">';
             
        while($row = mysql_fetch_assoc($result)) {	//The results are being printed.               
            echo '<tr>';
                echo '<td>';
                    echo '<h3><a href="FPselectartists.php?genre_id=' . $row['genre_id'] . '&genre_name=' . $row['genre_name'] . '">' . $row['genre_name'] . '</a></h3>';
                echo '</td>';
            echo '</tr>';
        }
        echo '</table>';
    }
}

include 'FPfooter.php';					//The connect file is included. 

?>
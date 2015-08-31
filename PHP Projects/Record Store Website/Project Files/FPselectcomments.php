<!--This file displays the news story and the comments that go along with it.-->

<?php

session_start();	//The session is started.

include 'FPheader.php';	//The header is included
include 'FPconnect.php';	//The connect file is included

$news_id = $_GET['news_id'];

$query = "SELECT * FROM news LEFT JOIN customers on customers_user_id = user_id WHERE news_id=" . $_GET['news_id'];		//The query is made.
 
$result = mysql_query($query);				//The query is run on the database.
 
if(!$result) {								//If the query did not return results
    echo 'The news article could not be displayed.'  . mysql_error(); //An error message is printed.
}
else {
    if(mysql_num_rows($result) == 0) {		//If the query return 0 results.
        echo 'Empty set.';	//An error message is displayed.
    }
    else {
       
        while($row = mysql_fetch_assoc($result)) {    	//The results of the query are printed.    
            echo '<h3>' . $row['news_name'] . '</h3>
            <b>By: </b>' . $row['user_name'] . '<br><b>
            Date: </b>' . $row['news_date'] . 
            '<br><br>' . $row['news_content'];
            
        } 
    }
}
echo '<br/><br/>';
//The query is built.
$query = "SELECT * from comments left join customers on customers_user_id = user_id WHERE  news_news_id = " . $news_id;

$result = mysql_query($query);		//The query is run on the database.
 
if(!$result) {		//If there was a problem wit hthe query.
    echo 'The reviews could not be displayed'  . mysql_error();	//mysql error is printed.
} 
else {
	if(mysql_num_rows($result) == 0) {		//If the query returns an empty set.
        echo'<h3>No comments yet.</h3>';
    } 
    else {	
    	echo '<h2>Comments</h2>';
    	//The table is built.
        echo '<table border="1" align="center" width="900">
              <tr>
                  <td><b>Username</b></td>
                  <td><b>Date</b></td>
                  <td><b>Comment</b></td>
              </tr>'; 
             
        while($row = mysql_fetch_assoc($result)) {    	//The results of the query are printed.           
            echo '<tr>';
            echo '<td>' . $row['user_name'] . "</td>";
            echo '<td>' . $row['comment_date'] . "</td>";
            echo '<td>' . $row['comment_content'] . "</td>";
            if ($_SESSION['user_level'] == "01") {
            	echo '<td><a href="FPdeletecomment.php?comment_id=' . $row['comment_id'] . '">DELETE</a>';
            }	
            echo '</tr>';
        }
        echo '</table>';
    } 
	if($_SESSION['signed_in']) { ?>
	<!--The form to make a new reply is built. Hidden inputs are used to pass data to the makereply file.-->
	<form method="post" action="FPmakecomment.php">
		<input type="hidden" name="news_id" value="<?php echo $news_id; ?>"/><br/>
		Leave a Comment:<br><textarea name="comment_content" rows="6" cols="134"></textarea><br>
		<input type="submit" value="Reply" />
	</form>
	
	<?php 
	}
	else {
		echo 'Sign in to post a comment.';
	}
}

include 'FPfooter.php';					//The connect file is included. 

?>
<!--This file is used to make a review of an album.-->

<?php 

session_start();	//The session is started.

include 'FPheader.php';	//The header is included.
include 'FPconnect.php';	//The connect file is inlcuded.
 
echo '<h2>Create review</h2>';	

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$name = ($_POST['formName']);			//Here the items from the post array are store into variables
	$content = $_POST['listItem'];
			
	if(!$_SESSION['signed_in']){				//If the user is not signed in.
        echo 'You must be signed in to post a reivew.';
    }
    if (!isset($_POST['review_name']) || empty($_POST['review_name']) || !isset($_POST['album_id']) || empty($_POST['album_id']) || !isset($_POST['review_score']) || empty($_POST['review_score']) || !isset($_POST['review_content']) || empty($_POST['review_content'])) { 
    	echo 'Please fill in all fields.';
    }
    else {
    	//The query is built.
        $query = "INSERT INTO 
        		`reviews` ( 
        			`review_name`, 
        			`review_score`, 
        			`review_content`,  
        			`albums_album_id`, 
        			`users_user_id`) 
        		VALUES ('" . 
        			mysql_real_escape_string($_POST['review_name']) . "', " . 
        			$_POST['review_score'] . ", '" .
                	mysql_real_escape_string($_POST['review_content']) . "', " . 
                    $_POST['album_id'] . ", " . 
                    $_SESSION['user_id'] . ")";
                                         
        $result = mysql_query($query);		//The query is run on the database.
                         
        if(!$result) {		//If there was a problem with the query, error messages are displayed..
            echo 'Your review has not been saved, please try again later. ' . mysql_error();
            echo '<br>' . $query;
            echo "<br><br>review name: '" . $_POST['review_name'] . "'<br>reivew content: '" . $_POST['review_content'] . "'<br>review score id: '" . $_POST['review_score'] . "'<br>album id: ' " . $_POST['album_id'] . " '<br><br>";
        } 
        else {
            echo 'Your review has been saved.<br>';
            echo '<a href="FPreviews.php?album_id=' . $_POST['album_id'] . '&artist_id=' . $_POST['artist_id'] . '">Click here to see your review.</a><br><br><br>';
        }
    }
}

	$query = 'SELECT * FROM albums where album_name != "blank" ORDER BY album_name ASC ';		//The query is made.
 
	$result = mysql_query($query);				//The query is run on the database.
	 
	if(!$_SESSION['signed_in']){				//If the user is not signed in.
        echo 'You must be signed in to post a reivew.';
	} 
	else{
		if(!$result) {								//If the query did not return results
    		echo 'The albums could not be displayed.'  . mysql_error(); //An error message is printed.
		}
		?>
		<form method="post" action=""> 
    		Review Name: <input type="text" name="review_name" value = "<?php echo $name ?>"/><br>
    		Album: <select name="album_id">
    		<?php while($row = mysql_fetch_assoc($result)) {	//The categories are listed in a dropdown menu.
        		echo'<option value="' . $row['album_id'] . '">' . str_replace('_', ' ', $row['album_name']) . '</option>';
    		} ?>
    		</select><br>
    		Score: <select name="review_score">
    			<option value="1">1</option>
    			<option value="2">2</option>
    			<option value="3">3</option>
    			<option value="4">4</option>
    			<option value="5">5</option>
    		</select><br>
        	Review Content:<br><textarea name="review_content" rows="10" cols="133" value="<?php echo $content ?>"/></textarea><br>
        	<input type="submit" value="Create review" />
    	</form> <?php
	}

include 'FPfooter.php';					//The connect file is included. 

?>
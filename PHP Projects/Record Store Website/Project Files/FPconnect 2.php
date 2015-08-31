<!--This file is used to connect to the server and the database.-->

<?php

$host="localhost";								//The login information needed to connect to the database is specified
$userName = "root";
$password = "root";
$database ='FinalProject';
				
$link = mysql_connect($host, $userName, $password);				//An attempt to connect to the server is made
				
if (!$link) {
	die("Could not connect to server: " . mysql_error());		//An error message is displayed if the connection to the server was unsuccessful
}
				
$selectedDB = mysql_select_db($database, $link);				//The database is selected on the server
				
if (!$selectedDB) {
	die("Could not connect to database: " . mysql_error());		//An error message is displayed if the connection to the database was unsuccessful
}
					 		
?>
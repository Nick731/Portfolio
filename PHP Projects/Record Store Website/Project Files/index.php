<!--This is the main file for the forum. It serves as the forum homepage and includes navigation links to the other parts of the site-->

<?php 

session_start();							//The session is started.

include 'FPheader.php';						//The header is inclueded/
include 'FPconnect.php';					//The connect file is included. 
 
echo"

<h1>

	Welcome to Nick's Vinyls

</h1>
	<div id='content3'>
		<img src='http://www.pitt.edu/~npp9/1052/finaleproject/recordstore.jpg' height='200'>					
	</div>	
	
	At Nick's Vinyls we want to bring you the best experience in finding the vinyls you want. No more going to the record  
    shop and hoping the vinyl you want is there. Here at Nick's Vinyls, we tell you exactly what we have in stock. In addition 
    to checking our stock, we also provide album reviews and the latest music news written by our editor. So feel free to create 
    an account and check  out our site to see our collection of vinlys, reviews, and news. I hope we have the one you're looking for.
    <br><br>
    Best,
        	
<h3>
    
    Nick Peters

</h3>"; 

include 'FPfooter.php';					//The connect file is included. 

?>
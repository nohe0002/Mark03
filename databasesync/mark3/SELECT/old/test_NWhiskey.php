<?php


$proz = array();
$Whiskey = array();
$NWhiskey = array ();
$stdaw = array();

$db =mysqli_connect("localhost", "app_mark3", "app_mark3_passwd" ,"test");


//Hier wird die Datenbankabfrage getestet. Heißt alle Werte aus dem aus der Tabelle Whiskey sollen in einem Mehrdemesionales Array gespeichert werden



$query=("SELECT whiskeyid, fruchtig, rauchig, mild, stark, honig, erde, holz from whiskey;");

$execution=mysqli_query($db, $query);

$row=array();

if($execution === false){
	die(mysqli_error($db));

}else{
	if(mysqli_num_rows($execution) == 0){

	print "fail";

}else{
	while($r = mysqli_fetch_array($execution)){

	array_push($row, $r);
	}

    print_r ($row);
   
	
	
	
	
	//$json = json_encode($row);
	//print $json;
}
}

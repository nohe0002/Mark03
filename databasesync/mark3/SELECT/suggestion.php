<?php

//$nickname = $_GET['nickname'];


$con =mysqli_connect("localhost", "app_mark3", "app_mark3_passwd" ,"test");


//require('var/www/html/mark3/config/db_connect.php');

$nickname = htmlspecialchars($_POST['nickname']);


$success = "suggestion_success";
$proz = array();
$Whiskey = array();
$NWhiskey = array ();
$stdaw = array();
$result =array();
$diff =array();



   $Whiskey = whiskey_abfrage($nickname, $Whiskey, $con);
  // print_r ($Whiskey);
   $NWhiskey = new_whiskey_abfrage($NWhiskey, $con);
  // print_r($NWhiskey);
   $Whiskey = bewertungs_berechnung($Whiskey);   
 // print_r ($Whiskey);
   $proz = berechnung_schnitt($Whiskey, $proz);
 //  print_r ($proz);
   $stdaw = berechnung_standardabweichung($NWhiskey, $proz, $stdaw);
 //  print_r ($stdaw);
   $sort = array_sortieren($stdaw);
 //  print_r ($sort);
   $diff = gleiche_loeschen($sort, $Whiskey);
 //  print_r ($diff);
   $result = vorschlag_abfrage($diff, $con, $success);
 //  print_r ($result);

 $json = json_encode($result);
print $json;
		


//Hier wird die Datenbankabfrage getestet. Heißt alle Werte aus dem aus der Tabelle Whiskey sollen in einem Mehrdemesionales Array gespeichert werden



function whiskey_abfrage($nickname, $Whiskey, $con){

$query=("SELECT whiskey.whiskeyid, fruchtig, rauchig, mild, stark, honig, erde, holz, bewertung from user, whiskey, whiskeyuser where user.userid = whiskeyuser.userid and whiskey.whiskeyid = whiskeyuser.whiskeyid and nickname='$nickname';");

$execution=mysqli_query($con, $query);

$Whiskey=array();
if($execution === false){
	die(mysqli_error($con));$execution=mysqli_query($con, $query);

}else{
	if(mysqli_num_rows($execution) == 0){

	print "fail";

}else{
	while($r = mysqli_fetch_row($execution)){

	array_push($Whiskey, $r);
	}

	return $Whiskey;
	
	
	//$json = json_encode($row);
	//print $json;
}
}
}


function new_whiskey_abfrage($NWhiskey, $con){


$query=("SELECT whiskeyid, fruchtig, rauchig, mild, stark, honig, erde, holz from whiskey;");

$execution=mysqli_query($con, $query);

$NWhiskey=array();

if($execution === false){
	die(mysqli_error($con));

}else{
	if(mysqli_num_rows($execution) == 0){

	print "failnew";

}else{
	while($r = mysqli_fetch_row($execution)){

	array_push($NWhiskey, $r);
	}

   // print_r ($Whiskey);
   
	return $NWhiskey;
	
	
	
	//$json = json_encode($row);
	//print $json;
}
}
}


function bewertungs_berechnung($Whiskey){
	//print_r ($Whiskey);

for ($i=0; $i < count($Whiskey); $i++) {

		
		
	for($x=0; $x < count($Whiskey[$i]) -1; $x++){
		
		if($x != 0){
		
		//echo $Whiskey[$i][$x] * $Whiskey[$i][count($Whiskey[$i])-1];	
 		$Whiskey[$i][$x] = $Whiskey[$i][$x] * $Whiskey[$i][count($Whiskey[$i])-1];
		
		echo "\n"; 		
	}	}
}
return $Whiskey;
}




//Hier muss der Durchschnitt erechnet werden
//Dazu werden die werte zusammen gerechnet und dann durch die anzahl der werte geteilt.

function berechnung_schnitt($Whiskey, $proz){
	//print_r ($Whiskey);
//Brauche ein Array damit ich alle Aromen speichern kann
//$proz = array();
$zwerg;	

	for($i=0; $i < count($Whiskey[0])-1; $i++){
					
		$zwerg =0;
		echo "\n";
		for($x=0; $x < count($Whiskey); $x++){
			
			//echo $Whiskey[$x][$i];
			$zwerg = $zwerg + $Whiskey[$x][$i];		
			
			
		}

		//Da wird der Durchschnitt ausgegben in Prozent fuer jedes aroma
		

		//echo $zwerg / count($Whiskey);
		
		$proz[$i] = $zwerg / count($Whiskey);
	
}	//print_r ($proz);

	return $proz;
}


//Hier wird die Standardabweichung berechenet
function berechnung_standardabweichung($NWhiskey, $proz, $stdaw){
$zwerg;
	for($i=0; $i < count($NWhiskey); $i++){
		
		$zwerg =0;
		//echo $i;	
		//echo "\n";
		//echo "\n";
		//echo count($NWhiskey[$i]);
		for($x=0; $x < count($NWhiskey[$i]); $x++){
		//echo $x;	
		
		if($x != 0){
		$zwerg = $zwerg + pow($NWhiskey[$i][$x] - $proz[$x], 2);		
		//echo $zwerg;
		//echo "\n";
		}	
		$stdaw[$i][0] = $NWhiskey[$i][0];
		$stdaw[$i][1] = $zwerg;
	}

}



return $stdaw;
}



function array_sortieren($stdaw){

foreach ($stdaw as $nr => $inhalt){

$id[$nr] = $inhalt[0];
$prozent[$nr] = $inhalt[1];
}

array_multisort($prozent, SORT_ASC, $stdaw);

return $stdaw;

}



function vorschlag_abfrage($diff, $con, $success){



for($i=0; $i < count($diff); $i++){

//$x = $sort[$i][0];



$x = $diff[$i];

$query=("SELECT * FROM whiskey WHERE whiskeyid='$x';");

$execution=mysqli_query($con, $query);

$zwerg=array();
if($execution === false){
	die(mysqli_error($con));

}else{
	if(mysqli_num_rows($execution) == 0){

	print "fail";

}else{
	while($r = mysqli_fetch_assoc($execution)){

	array_push($zwerg, $r);
	}
   //print_r ($zwerg);
   
  $result[$i] = $zwerg[0];
    
	//$json = json_encode($row);
	//print $json;
}
 
}
    
	
}
//print $success;
return $result;

}





function gleiche_loeschen($sort, $Whiskey){


$zwerg =  array();


$y = array();
$z = array();
for($i=0; $i < count($sort); $i++){

	$y[$i] = $sort[$i][0];
	

}

for($i=0; $i < count($Whiskey); $i++){
	
	$z[$i] = $Whiskey[$i][0]; 	

}
//print_r ($y);
//print_r ($z);

$zwerg = array_diff($y, $z);
//print_r ($zwerg);

$diff = array_merge(array_filter($zwerg));

//print_r ($diff);

return $diff;


}


?>


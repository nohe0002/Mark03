<?php

$nickname = $_GET['nickname'];

$proz = array();
$Whiskey = array();
$NWhiskey = array ();
$stdaw = array();

$db =mysqli_connect("localhost", "app_mark3", "app_mark3_passwd" ,"test");


    $Whiskey = whiskey_abfrage($nickname, $Whiskey, $db);
    //print_r ($Whiskey);
    $NWhiskey = new_whiskey_abfrage($NWhiskey, $db);
  //  print_r($NWhiskey);
    $Whiskey = bewertungs_berechnung($Whiskey);   
  //  print_r ($Whiskey);
    $proz = berechnung_schnitt($Whiskey, $proz);
   // print_r ($proz);
    $stdaw = berechnung_standardabweichung($NWhiskey, $proz, $stdaw);
    //print_r ($stdaw);
    $sort = array_sortieren($stdaw);
    print_r ($sort);
	


//Hier wird die Datenbankabfrage getestet. Heißt alle Werte aus dem aus der Tabelle Whiskey sollen in einem Mehrdemesionales Array gespeichert werden



function whiskey_abfrage($nickname, $Whiskey, $db){

$query=("SELECT whiskey.whiskeyid, fruchtig, rauchig, mild, stark, honig, erde, holz, bewertung from user, whiskey, whiskeyuser where user.userid = whiskeyuser.userid and whiskey.whiskeyid = whiskeyuser.whiskeyid and nickname='$nickname';");

$execution=mysqli_query($db, $query);

$Whiskey=array();

if($execution === false){
	die(mysqli_error($db));

}else{
	if(mysqli_num_rows($execution) == 0){

	print "fail";

}else{
	while($r = mysqli_fetch_array($execution)){

	array_push($Whiskey, $r);
	}

	return $Whiskey;
	
	
	//$json = json_encode($row);
	//print $json;
}
}
}


function new_whiskey_abfrage($NWhiskey, $db){

$query=("SELECT whiskeyid, fruchtig, rauchig, mild, stark, honig, erde, holz from whiskey;");

$execution=mysqli_query($db, $query);

$NWhiskey=array();

if($execution === false){
	die(mysqli_error($db));

}else{
	if(mysqli_num_rows($execution) == 0){

	print "failnew";

}else{
	while($r = mysqli_fetch_array($execution)){

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
			
			echo $Whiskey[$x][$i];
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




?>

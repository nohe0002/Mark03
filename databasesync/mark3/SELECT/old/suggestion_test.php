<?php

//Hier soll die Standardabweichung berechnet werden. 

//Auslesen aus der Datenbank und in Array speichern



//$field = array();

//$strSQL = "SELECT pos_x, pos_y FROM field;";

//$db->query($strSQL);

//while ($row = $db->getResult()) {

// $field[$row['pos_x']][$row['pos_y']] = true;

//}


//Aufruf der einzenlnen Funktionen

//Allgemein Variabeln

$proz = array();
$Whiskey = array();
$NWhiskey = array ();
$stdaw = array();


//Mehrdemensionales Array der noch verfuegbaren Whiskeys

  $NWhiskey[0][0] = "1";
  $NWhiskey[0][1] = "1";
  $NWhiskey[0][2] = "0";
  $NWhiskey[0][3] = "1";
  $NWhiskey[0][4] = "0";
  $NWhiskey[0][5] = "1";
 
 
  $NWhiskey[1][0] = "2";
  $NWhiskey[1][1] = "1";
  $NWhiskey[1][2] = "0";
  $NWhiskey[1][3] = "1";
  $NWhiskey[1][4] = "1";
  $NWhiskey[1][5] = "1";
 
  $NWhiskey[2][0] = "3";
  $NWhiskey[2][1] = "1";
  $NWhiskey[2][2] = "1";
  $NWhiskey[2][3] = "1";
  $NWhiskey[2][4] = "1";
  $NWhiskey[2][5] = "0";


//Mehrdemensionales Array der Getrunkenen Whiskeys

$Whiskey[0][0] = "4";
$Whiskey[0][1] = "0";
$Whiskey[0][2] = "0";
$Whiskey[0][3] = "1";
$Whiskey[0][4] = "0";
$Whiskey[0][5] = "1";
$Whiskey[0][6] = "2";


$Whiskey[1][0] = "5";
$Whiskey[1][1] = "1";
$Whiskey[1][2] = "1";
$Whiskey[1][3] = "0";
$Whiskey[1][4] = "1";
$Whiskey[1][5] = "1";
$Whiskey[1][6] = "4";

$Whiskey[2][0] = "6";
$Whiskey[2][1] = "1";
$Whiskey[2][2] = "0";
$Whiskey[2][3] = "1";
$Whiskey[2][4] = "1";
$Whiskey[2][5] = "0";
$Whiskey[2][6] = "1";



//Aufrauf der einzelnen Funktionen
//print_r ($Whiskey);
$Whiskey = bewertungs_berechnung($Whiskey);
//print_r ($Whiskey);
$proz = berechnung_schnitt($Whiskey, $proz);
//print_r ($proz);
$stdaw = berechnung_standardabweichung($NWhiskey, $proz, $stdaw);
print_r ($stdaw);
$sort = array_sortieren($stdaw);
print_r ($sort);


//Aromen werden mit der Bewertung mutlipliziert
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




function array_sortiren($stdaw){


foreach($stdaw as $key => $row){

$data[$key]  = $row[1];

}

$data = array_column($data, 1);


array_multisort($data, SORT_DESC, $stdaw);

$sort =1;
print_r ($stdaw);

return $sort;


}



function array_sortieren($stdaw){

foreach ($stdaw as $nr => $inhalt){

$id[$nr] = $inhalt[0];
$prozent[$nr] = $inhalt[1];
}

array_multisort($prozent, SORT_ASC, $stdaw);

return $stdaw;

}








//while($i< count($Whiskey)){

//	while($x < count($Whiskey[$i])){
		
//	echo "hans";
//	$x++;
//	}
//	$i++;
//}
//}



//foreach ($termin as $nr => $inhalt)
//{
//    $band[$nr]  = strtolower( $inhalt['Band'] );
//    $ort[$nr]   = strtolower( $inhalt['Ort'] );
//    $datum[$nr] = strtolower( $inhalt['Datum'] );
//}

//echo count($Whiskey);

//Tests
//echo ($Whiskey[0][Bewertung] * $Whiskey[0][aroma1]);

//echo "<pre>";
//print_r ( $Whiskey );

//echo $Whiskey[1][Bewertung];



//Die Standardabweichung funktioniert hier. Allerdings brauche ich noch meine werte und muss gucken wie das ganze berechene

//Hier wird ein Array angelegt
$valueArray = array(
    "Aroma1" => "3",
    "Aroma2" => "9",
    "Aroma3" => "8",
    "Aroma4" => "2",
    "Aroma5" => "1",
);

$test1 = test($valueArray);

//echo $test;

//Das ist eine fertige Funktion zur Standardabweichung Berechnung
function test($valueArray) {
	$sum = array_sum($valueArray);
	$count = count($valueArray);
	$mean =	$sum / $count;
	$result = 0;
	foreach ($valueArray as $value)
		$result += pow($value - $mean, 2);
	unset($value);			
	$count = ($count == 1) ? $count : $count - 1;

	return sqrt($result / $count);
}


?>

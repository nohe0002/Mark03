<?php

//Die Datenbank verbinung wird eingebuden
require('/var/www/html/mark3/config/db_connect.php');

//Der im Browser mitgebene Wert wird in der Variable $username gespeichert
$nickname=htmlspecialchars($_GET['nickname']);
$password=htmlspecialchars($_GET['password']);

//Die Fehlermeldung wird noch ausgtausch durch etwas das nicht in der Datenbank stehen kann. Dadurch kann ich dann in der App prüfen ob es den User gibt oder nicht und kann das dann als meldung schreiben
$failmassage = "Es wurde kein Datensatz gefunden";

//Dies ist die SQL abfrage. Es werden alle Daten angefordert aus der Tablle user, die den usernamen von der Variable usernamen haben.
$query=("SELECT nickname, firstname, lastname, birth, email, credit, code, password FROM user WHERE nickname='$nickname' and password='$password'");

//Die SQL abfrage wird ausgefuehert und in der variable execution gespeichert.
$execution=mysqli_query($con, $query);

//Das Array Rows wird angelegt
$rows=array();


//Die Abfrage wird geprueft ob diese ausgefuehrt wurde. Falls positive, geht es einfach weiter, falls negative wird der mysql Error ausgebenen.
if($execution === false) {
	
	die(mysqli_error($con, $query));
}else{

	if(mysqli_num_rows($execution) == 0){

		print $failmassage;

}else{
	while($r = mysqli_fetch_array($execution)){
         array_push($rows, $r);
	 }

	

	$json = json_encode($rows);
	print $json;


}
}
//Ueber die while-schleife werden die werte der execution in das array zeilenweise geschrieben. 

//while($r = mysqli_fetch_array($execution)){
//	array_push($rows, $r);
//}

//Zur besseren Ausgabe wird aus dem array ein json object. Welches dann im Browser wieder geben wird. 

//$json = json_encode($rows);
//print $json;


?>

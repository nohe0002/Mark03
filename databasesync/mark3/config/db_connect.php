<?php

define('DB_USER', "app_mark3"); //db user
define('DB_PASSWORD', "app_mark3_passwd"); //db userpassword
define('DB_DATABASE', "mark3"); //Databasename
define('DB_SERVER', "localhost"); //db server

$con = mysqli_connect(DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE);

//Check connection
if(mysqli_connect_error()){

	echo "FAILED to connect to MySQL: " . mysqli_connect_error();

}

?>

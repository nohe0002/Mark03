<?php

//Es darf keinen Usernamen doppelt geben. Ebenfalls darf es keinen account geben mit der selben mail adresse
//Daher muss nach dem Usernamen in de checkquery gesucht werden. 
//Wenn es den usernamen noch nicht gibt ist der code egal.
//Muss alles noch geprüft werden. Der Rest wird denke ich über die RegActivity gemacht

require('/var/www/html/mark3/config/db_connect.php');

$nickname=htmlspecialchars($_POST['nickname']);
$firstname=htmlspecialchars($_POST['firstname']);
$lastname=htmlspecialchars($_POST['lastname']);
$birth=htmlspecialchars($_POST['birth']);
$email=htmlspecialchars($_POST['email']);
$code=htmlspecialchars($_POST['code']);
$password=htmlspecialchars($_POST['password']);


$failmessage_email = "emailfail";
$failmessage_user = "nicknamefail";
$failmessage_connection = "connectionfail";
$successmessage = "registrationsuccess";


//$passwordhash = password_hash($password, PASSWORD_DEFAULT);


$insert_query=("INSERT INTO user (nickname, firstname, lastname, birth, email, code, password) VALUES ('$nickname', '$firstname', '$lastname', '$birth', '$email', '$code', '$password')");
$check_query_nickname=("SELECT nickname FROM user WHERE nickname='$nickname'");
$check_query_email=("SELECT email FROM user WHERE email='$email'");


#$insert_execution=mysqli_query($con, $insert_query);
$check_execution_nickname=mysqli_query($con, $check_query_nickname);
$check_execution_email=mysqli_query($con, $check_query_email);

if($check_execution_nickname === false) {

    die(mysqli_error($con, $check_query_nickname));
    print $failmessage_connection;
	
}elseif(mysqli_num_rows($check_execution_nickname) != 0) {

        print $failmessage_user;
    }elseif(mysqli_num_rows($check_execution_email) != 0){ 
        print $failmessage_email;
    }else{
    mysqli_query($con, $insert_query);
	print $successmessage;
    }




?>

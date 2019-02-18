<?php

require('/var/www/html/mark3/config/db_connect.php');
$user_name = $_POST["nickname"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from user where nickname like '$user_name' and password like'$user_pass';";
$result = mysqli_query($conn, $mysql_qry);

if(mysqli_num_rows($result) > 0) {
$row = mysqli_fetch_assoc($result);
echo"login success";
}
else{
echo "login not success";
}

?>

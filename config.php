<?php

$link = mysqli_connect('localhost', 'id9282849_autoads_db_imran', '12345678','id9282849_autoads_db');

//'serername', "db_user_name", "db_password", "db_name"
//$link = mysql_connect('localhost', 'root', '');
if (!$link) {
    die('Could not connect: ' . mysql_error());
}


?>
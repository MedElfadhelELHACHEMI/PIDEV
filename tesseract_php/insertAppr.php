<?php
require_once('connect.php');



$username=$_GET['username'];
$password=$_GET['password'];
$nom=$_GET['nom'];
$prenom=$_GET['prenom'];
$role=$_GET['role'];
$email=$_GET['email'];
$adresse=$_GET['adresse'];
$tel=$_GET['telephone'];
$date=$_GET['date'];
$tel1 = (int)$tel;

$sql = "insert into utilisateur (pseudo,mdp,nom,prenom,telephone,adresse,mail,role, date_naissance) values( '$username','$password','$nom','$prenom',$tel1,'$adresse','$email','$role',str_to_date('$date','%d-%m-%y'))";

if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>
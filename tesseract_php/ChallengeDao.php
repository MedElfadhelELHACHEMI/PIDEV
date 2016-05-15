<?php
require_once('connect.php');

$action=$_GET['action'];

if($action=="ajouterChallenge")
{
$id = $_GET['id'];
$id_organisation=$_GET['id_organisation'];
$nom=$_GET['nom'];
$description=$_GET['description'];
$theme=$_GET['theme'];
$date=$_GET['date'];
$id_utilisateur=$_GET['id_utilisateur'];
$duree=$_GET['duree'];
$sql = "insert into `challenge` VALUES ( '$id','$id_organisation','$nom','$description','$theme','$date','$id_utilisateur','$duree')";

if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);

}

if($action=="modifierChallenge")
{
$id = $_GET['id'];
$id_organisation=$_GET['id_organisation'];
$nom=$_GET['nom'];
$description=$_GET['description'];
$theme=$_GET['theme'];
$date=$_GET['date'];
$id_utilisateur=$_GET['id_utilisateur'];
$duree=$_GET['duree'];
$sql = "Update `challenge` set id='$id',id_organisation='$id_organisation',nom='$nom',description='$description',theme='$theme',date='$date',id_utilisateur='$id_utilisateur',duree='$duree' where `id`='$id'";

if (mysqli_query($conn, $sql)) {
    echo $sql;
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);

}


if($action=="findById")
{
$id = $_GET['id'];
$sql = "SELECT * FROM `challenge` where id='$id'";
$result = $conn->query($sql);
$xml = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $xml->addChild('challenge');
	    $mydata->addAttribute('id',$row['id']);
        $mydata->addAttribute('id_organisation',$row['id_organisation']);
        $mydata->addAttribute('nom',$row['nom']);
        $mydata->addAttribute('description',$row['description']);
		$mydata->addAttribute('theme',$row['theme']);
		$mydata->addAttribute('date',$row['date']);
		$mydata->addAttribute('id_utilisateur',$row['id_utilisateur']);
		$mydata->addAttribute('duree',$row['duree']);
         }
} else {
    echo "0 results";
}
$conn->close();
header ("Content-Type:text/xml");
	 echo($xml->asXML());
	 	
}
if($action=="findByIdorg")
{
$id = $_GET['id'];
$sql = "SELECT * FROM `challenge` where id_organisation='$id'";
$result = $conn->query($sql);
$xml = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $xml->addChild('challenge');
        $mydata->addAttribute('id',$row['id']);
        $mydata->addAttribute('id_organisation',$row['id_organisation']);
        $mydata->addAttribute('nom',$row['nom']);
        $mydata->addAttribute('description',$row['description']);
        $mydata->addAttribute('theme',$row['theme']);
        $mydata->addAttribute('date',$row['date']);
        $mydata->addAttribute('id_utilisateur',$row['id_utilisateur']);
        $mydata->addAttribute('duree',$row['duree']);
         }
} else {
    echo "0 results";
}
$conn->close();
header ("Content-Type:text/xml");
     echo($xml->asXML());
        
}

?>
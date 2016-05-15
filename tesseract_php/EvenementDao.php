<?php
require_once('connect.php');

$action=$_GET['action'];

if($action=="ajouterEvenement")
{
$id = $_GET['id'];
$id_organisation=$_GET['id_organisation'];
$nom=$_GET['nom'];
$description=$_GET['description'];
$nbr_max=$_GET['nbr_max'];
$affiche=$_GET['affiche'];
$date=$_GET['date'];
$sql = "insert into `evenement` VALUES ( '$id','$id_organisation','$nom','$description','$nbr_max','$affiche','$date')";

if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);

}

if($action=="modifierEvenement")
{
$id = $_GET['id'];
$id_organisation=$_GET['id_organisation'];
$nom=$_GET['nom'];
$description=$_GET['description'];
$nbr_max=$_GET['nbr_max'];
$affiche=$_GET['affiche'];
$date=$_GET['date'];
$sql = "Update `evenement` set `id_organisation`='$id_organisation',`nom`='$nom',`description`='$description',`nbr_max`='$nbr_max',`affiche`='$affiche',`date`='$date' where `id`='$id'";

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
$sql = "SELECT * FROM `evenement` where id='$id'";
$result = $conn->query($sql);
$xml = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $xml->addChild('evenement');
	    $mydata->addAttribute('id',$row['id']);
        $mydata->addAttribute('id_organisation',$row['id_organisation']);
        $mydata->addAttribute('nom',$row['nom']);
        $mydata->addAttribute('description',$row['description']);
		$mydata->addAttribute('nbr_max',$row['nbr_max']);
		$mydata->addAttribute('affiche',$row['affiche']);
		$mydata->addAttribute('date',$row['date']);
         }
} else {
    echo "0 results";
}
$conn->close();
header ("Content-Type:text/xml");
	 echo($xml->asXML());
	 	
}
if($action=="findByIdOrg")
{
$id = $_GET['id'];
$sql = "SELECT * FROM `evenement` where id_organisation='$id'";
$result = $conn->query($sql);
$xml = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $xml->addChild('evenement');
        $mydata->addAttribute('id',$row['id']);
        $mydata->addAttribute('id_organisation',$row['id_organisation']);
        $mydata->addAttribute('nom',$row['nom']);
        $mydata->addAttribute('description',$row['description']);
        $mydata->addAttribute('nbr_max',$row['nbr_max']);
        $mydata->addAttribute('affiche',$row['affiche']);
        $mydata->addAttribute('date',$row['date']);
         }
} else {
    echo "0 results";
}
$conn->close();
header ("Content-Type:text/xml");
     echo($xml->asXML());
        
}

?>
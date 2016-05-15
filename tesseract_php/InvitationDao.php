<?php
require_once('connect.php');

$action=$_GET['action'];

if($action=="ajouterInvitation")
{
$id = $_GET['id'];
$id_organisation=$_GET['id_organisation'];
$id_utilisateur=$_GET['id_utilisateur'];
$sens=$_GET['sens'];
$etat=$_GET['etat'];
$date=$_GET['date'];

$sql = "insert into `invitations` VALUES ( '$id','$id_organisation','$id_utilisateur','$sens','$etat','$date')";

if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);

}

if($action=="modifierInvitation")
{
$id = $_GET['id'];
$id_organisation=$_GET['id_organisation'];
$id_utilisateur=$_GET['id_utilisateur'];
$sens=$_GET['sens'];
$etat=$_GET['etat'];
$date=$_GET['date'];

$sql = "Update `invitations` set `id_organisme`='$id_organisation',`id_utilisateur`='$id_utilisateur',`sens`='$sens',`etat`='$etat',`date`='$date' where `id`='$id'";

if (mysqli_query($conn, $sql)) {
    echo $sql;
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);

}


if($action=="findByOrganisation")
{
$id = $_GET['id'];
$sql = "SELECT * FROM `invitations` where `id_organisme`='$id'";
$result = $conn->query($sql);
$xml = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $xml->addChild('invitation');
	    $mydata->addAttribute('id',$row['id']);
        $mydata->addAttribute('id_organisme',$row['id_organisme']);
        $mydata->addAttribute('id_utilisateur',$row['id_utilisateur']);
        $mydata->addAttribute('sens',$row['sens']);
		$mydata->addAttribute('etat',$row['etat']);
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
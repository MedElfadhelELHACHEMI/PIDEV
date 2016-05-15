<?php
require_once('connect.php');

$action=$_GET['action'];

if($action=="findById")
{
$id = $_GET['id'];
$sql = "SELECT * FROM `utilisateur` where id='$id'";
$result = $conn->query($sql);
$xml = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $xml->addChild('utilisateur');
	    $mydata->addAttribute('id',$row['id']);
        $mydata->addAttribute('id_organisation',$row['id_organisation']);
        $mydata->addAttribute('pseudo',$row['pseudo']);
        $mydata->addAttribute('mdp',$row['mdp']);
		$mydata->addAttribute('sexe',$row['sexe']);
		$mydata->addAttribute('nom',$row['nom']);
		$mydata->addAttribute('prenom',$row['prenom']);
		$mydata->addAttribute('date_naissance',$row['date_naissance']);
		$mydata->addAttribute('telephone',$row['telephone']);
		$mydata->addAttribute('adresse',$row['adresse']);
		$mydata->addAttribute('mail',$row['mail']);
		$mydata->addAttribute('photo',$row['photo']);
		$mydata->addAttribute('role',$row['role']);
		$mydata->addAttribute('score',$row['score']);
         }
} else {
    echo "0 results";
}
$conn->close();
header ("Content-Type:text/xml");
	 echo($xml->asXML());
	 	
}

?>
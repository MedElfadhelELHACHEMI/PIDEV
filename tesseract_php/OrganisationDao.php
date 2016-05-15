<?php
require_once('connect.php');

$action=$_GET['action'];

if($action=="findById")
{
$id = $_GET['id'];
$sql = "SELECT * FROM `organisation` where id='$id'";
$result = $conn->query($sql);
$xml = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $xml->addChild('organisation');
        $mydata->addAttribute('id',$row['id']);
        $mydata->addAttribute('nom',$row['nom']);
        $mydata->addAttribute('adress',$row['adress']);
        $mydata->addAttribute('email',$row['email']);
		$mydata->addAttribute('matricule',$row['adress']);
		$mydata->addAttribute('telephone',$row['telephone']);
		$mydata->addAttribute('photo',$row['photo']);
		
		
         }
} else {
    echo "0 results";
}
$conn->close();
header ("Content-Type:text/xml");
	 echo($xml->asXML());
	 	
}

?>
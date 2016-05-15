<?php  
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "tesseractj2me";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

  if(isset($_GET['username'])&&isset($_GET['password']))
{
    $username = $_GET['username'];
    $password = $_GET['password'];
  

 
 $query_search = "SELECT * FROM utilisateur WHERE  pseudo='$username' and mdp='$password'";
  $rs=$conn->query($query_search);
      
if($rs!=false){  
      $xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
      $root_element = "people"; 
      $xml .= "<$root_element>";
	    $rs->data_seek(0);
       while($row = $rs->fetch_assoc()){
      $xml .= "<utilisateur ";
 
      foreach($row as $key => $value)
      {
         //$key holds the table column name
         $xml .= " $key=\"$value\"";
 
         //embed the SQL data in a CDATA element to avoid XML entity issues
         $xml .= ""; 
      }
 
      $xml.="></utilisateur>";
}}
//close the root element
$xml .= "</$root_element>";
 
//output the XML data
if ($xml != '<?xml version="1.0" encoding="UTF-8"?><people></people>'){
	//send the xml header to the browser
	header ("Content-Type:text/xml"); 
	echo $xml;
}else{//si Requete n'a pas retourner de resultat POUR LE FREELANCER

	echo 'B';
}
	
	

 
 
}else{//si pas de parametres
echo 'A';
	
}

 ?>
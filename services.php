<?php 
error_reporting(0);
require("config.php");
$response=["error"=>false];

//*******************************************************
if(isset($_POST['sign_up_user']))
{
$fname=$_POST['fname'];
$lname=$_POST['lname'];
$email=$_POST['email'];
$mobile=$_POST['mobile'];

$password=$_POST['password'];
$user_type=$_POST['user_type'];

$query="SELECT * from user where mobile='$mobile' AND user_type = '$user_type'";
$result=mysqli_query($link, $query);
	if(mysqli_num_rows($result)>0)
	{
		$response["error"]=true;
		$response["msg"]="already exists";
	}

else
{
$query="INSERT INTO user(f_name, l_name, mobile, email, password, user_type) VALUES ('$fname','$lname','$mobile','$email','$password','$user_type')";

$result=mysqli_query($link,$query);

	if($result){
		$response["error"]=false;
		$response["msg"]="inserted";
	}else{
		$response["error"]=true;
		$response["msg"]="failed";
	}
}

}

//*******************************************************

if(isset($_POST['get_reg_veh']))
{
 
	$user_id=$_POST['user_id']; 

	$query="SELECT * FROM vehicle WHERE user_id = '$user_id'";
	
	$result=mysqli_query($link, $query);

	if($result)
	{
			$data=[];

			 foreach($result as $row)
			 {
				array_push($data,$row);
			 }
				$response["error"]=false;
				$response["msg"]="Numbers or registered vehicles found";
				$response["data"]=$data;
	}
	else
	{
			$response["error"]=true;
			$response["msg"]="No User found";
	}
	

}
//*******************************************************
if(isset($_POST['get_user_profile']))
{

	$mobile=$_POST['mobile']; 

	$query="SELECT * FROM user WHERE mobile = '$mobile'";
	
	$result=mysqli_query($link, $query);
	
		if(mysqli_num_rows($result)>0)
		{
			$response["error"]=false;
			$response["msg"]="Found";
			$response["data"]= mysqli_fetch_assoc($result);
		}
		else
		{
			$response["error"]=true;
			$response["msg"]="No data found";
		}

}


//*******************************************************

if(isset($_POST['login']))
{

$mobile=$_POST['mobile'];
$password=$_POST['password'];



$query="SELECT * from user where mobile='$mobile' AND password='$password'";
$result=mysqli_query($link, $query);
if(mysqli_num_rows($result)>0)
    {
        $response["error"]=false;
        $response["msg"]="Found";
        $response["data"]= mysqli_fetch_assoc($result);
    }
    else
    {
    	$response["error"]=true;
    	$response["msg"]="No data found";
    }

}

//*******************************************************************************
if(isset($_POST['get_user']))
{
 
	$mobile=$_POST['mobile']; 

	$query="SELECT * FROM user WHERE mobile = '$mobile'";
	
	$result=mysqli_query($link, $query);

	if($result)
	{
			$data=[];

			 foreach($result as $row)
			 {
				array_push($data,$row);
			 }
				$response["error"]=false;
				$response["msg"]="drivers data found";
				$response["data"]=$data;
	}
	else
	{
			$response["error"]=true;
			$response["msg"]="No User found";
	}
	

}
//------------------------------------------------------------------------------

//*******************************************************
if(isset($_POST['get_password']))
{

	$email=$_POST['email'];
	$user_type_id=$_POST['user_type_id'];

	$query="SELECT password FROM user WHERE user_type_id = '$user_type_id' and email='$email'";

	$result=mysqli_query($link, $query);

	$result=mysqli_query($link, $query);
	if(mysqli_num_rows($result)>0)
	{
		$response["error"]=false;
		$response["msg"]="password found";
		$response["data"]= mysqli_fetch_assoc($result);
	}
	else
	{
			$response["error"]=true;
			$response["msg"]="No data found against the email address you provided, please make sure you enter correct email address";
	}

}

//------------------------------------------------------------------------------


if(isset($_POST['reg_veh']))
{
	$user_id = $_POST['user_id'];
	$veh_type = $_POST['veh_type'];
	$veh_reg_no = $_POST['veh_reg_no'];
	$veh_color = $_POST['veh_color'];
	$veh_model = $_POST['veh_model'];
	$veh_make = $_POST['veh_make'];
	$veh_make_year = $_POST['veh_make_year'];
	
	$query="INSERT INTO vehicle(user_id, veh_type, veh_reg_no, veh_color, veh_model, veh_make, veh_make_year) VALUES 
	('$user_id','$veh_type','$veh_reg_no','$veh_color','$veh_model','$veh_make','$veh_make_year')";

	$result=mysqli_query($link,$query);

	if($result){
		$response["error"]=false;
		$response["msg"]="Vehicle Registered Successfully";
	}else{
		$response["error"]=true;
		$response["msg"]="Failed ";
	}
	

}
//------------------------------------------------------------------------------




echo json_encode($response);




?>
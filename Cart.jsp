<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<link rel="icon" type="image/png" href="./Images/popmart_logo.png">
<link rel="stylesheet" href="CSS/Product.css"> <!---change --->
<!-- bootstrap CSS link -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
</head>
<body>
<!---- header ---->
<nav class="navbar navbar-expand-lg navbar-light bg-light">

        <a class="navbar-brand" href="ProductList.jsp"></a>
		<img width="15%"src="Images/popmart_logo.png"  />
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="ProductList.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="Cart.jsp">Cart </a></li>
                <li class="nav-item"><a class="nav-link" onclick = "showAlert()" href="Login.html">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
<h2 class = "title"><br>Cart</h2>
<!---- background ---->
  <style>
  body {
	background-image: url('https://i.ibb.co/6mXm938/wallpaper3.webp');
    background-repeat: no-repeat;
    background-attachment: fixed; 
    background-size: 100% 100%;
  }
  </style>

<!----items to add to cart-->
<div class="container">       
    <div class="d-flex py-3"><h3>Total Price: ${(total>0)?dcf.format(total):0} </h3> <form action='check-out' method='Get'><button type="submit" class="mx-3 btn btn-primary">Check Out</button></form></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Series</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					${added}

					<td>
				</tr>
				
				</tr>

			</tbody>
		</table>

</div>
<!---- footer ---->
</body>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function showAlert() {
      var Success = "You have logged out!";

      alert (Success);
    }
    </script>


</html>
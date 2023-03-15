<!DOCTYPE html>
<html>
<head>
<title>Product</title>
<link rel="icon" type="image/png" href="./Images/popmart_logo.png">
<link rel="stylesheet" href="CSS/Product.css"> <!---change --->
<!-- bootstrap CSS link -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
</head>
<body>
<!---- header ---->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">

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
                <li class="nav-item"><a class="nav-link" href="Login.html">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
<h2 class = "title"><br>Products</h2>
<!---- background ---->
  <style>
  body {
    background-image: url('https://i.ibb.co/TtJK8Wq/wallpaper2.jpg');
    background-repeat: no-repeat;
    background-attachment: fixed; 
    background-size: 100% 100%;
  }
  </style>

<!----items to add to cart-->
<div class="container">       
    <div class="row">
        <div class="col-md-3 my-3">
            <div class="card w-100">
                    <div class="card-body">
                        <img class="card-img-top" style="width: 100%; height: 100%; padding-top: 30px" src="Images/AbConImg/star.png">
                        <h5 class="card-title">Welcome, User!
                            <script type="text/javascript">
                                checkIfLogin()
                            </script>
                        </h5>
                    </div>
            </div>
        </div>
        

        ${items}
    </div>

</div>


<!---- footer ---->
</body>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function checkIfLogin(strValue) {
        if(strValue==""){
            return "Please Login!";
        }else{
            return "Welcome" + strValue;
        }
    }
</script>
</html>
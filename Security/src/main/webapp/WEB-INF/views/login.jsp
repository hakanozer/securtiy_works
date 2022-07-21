<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            ${ email }
            <h1> Admin Login </h1>
            <f:form modelAttribute="user" method="post" action="login">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <f:input path="email" name="email" required="required" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"></f:input>
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                    <f:errors class="invalid" path="email"></f:errors>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <f:input path="password" name="password" required="required" type="password" class="form-control" id="exampleInputPassword1"></f:input>
                    <f:errors class="invalid" path="password"></f:errors>
                </div>
                <div class="mb-3">
                    <select name="userStatus">
                        <option>Class -1</option>
                        <option>Class -2</option>
                        <option>Class -3</option>
                    </select>
                </div>
                <div class="mb-3 form-check">
                    <input name="remember" type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Remember</label>
                </div>
                <input type="hidden" name="token" value=${token} />
                <button type="submit" class="btn btn-primary">Submit</button>
            </f:form>
        </div>
        <div class="col-sm-4"></div>
    </div>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>
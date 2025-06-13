<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Mobile Corporate Banking App Stat</title>

    <!-- Bootstrap Core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/blog-post.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<link rel="shortcut icon" href="dist/img/favicon.ico">
  	<link rel="apple-touch-icon" href="dist/img/apple-touch-icon.png">
	
</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="stat">SILVERLAKE.MLEB.MCB</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <!-- <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul> -->
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-sm-12 col-xs-12 col-md-8">

                <!-- Blog Post -->

                <!-- Title -->
                <h1>MCB APP STAT</h1>

                <!-- 
                <p class="lead">
                    by <a href="#">MLEB</a>
                </p> -->

                <hr>

                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> Stat info on ${infoDateTime}</p>

                <hr>


					<!-- MLEB service info -->

					<div class="well">
					<table class="table table-striped">
					    <thead>
					      <tr>
					        <th>MLEB Services</th>
					        <th width="25%">Status</th>
					      </tr>
					    </thead>
					    <tbody>
					    	<c:forEach var="listValue" items="${appStatResp.mlebList}">
							  <tr>
						        <td>${listValue.name} : </td>
						        <td>${listValue.desc}</td>
						      </tr>
							</c:forEach>
					      
					    </tbody>
					  </table>
               		</div>







					<!-- client connection info -->
               		
					
					<div class="well">
					<table class="table table-striped">
					    <thead>
					      <tr>
					        <th>Client Connection</th>
					        <th width="25%">Status</th>
					      </tr>
					    </thead>
					    <tbody>
					    	<c:forEach var="listValue" items="${appStatResp.clientList}">
							  <tr>
						        <td>${listValue.name} : </td>
						        <td>${listValue.desc}</td>
						      </tr>
							</c:forEach>
					      
					    </tbody>
					  </table>
               		</div>

              

                

            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div class="col-sm-12 col-xs-12 col-md-4">

                

                <!-- Blog Categories Well -->
                <div class="well">
                    <!-- <h4>WS Request Info</h4> -->
                    
                    	<table class="table">
					    <thead>
					      <tr>
					        <th>WS Request Info</th>
					        <th width="40%"></th>
					      </tr>
					    </thead>
					    <tbody>
					    	
					    	<c:forEach var="rqlistValue" items="${appStatResp.requestList}">
							  <tr>
						        <td>${rqlistValue.name} </td>
						        <td><span style="color:blue;" >${rqlistValue.status}</span></td>
						      </tr>
							</c:forEach>
					    	
					    	
					    	
							   
							
					      
					    </tbody>
					  </table>
                        
                  
                    <!-- /.row -->
                </div>

                <!-- Side Widget Well -->
                <div class="well">
                
                	<table class="table">
					    <thead>
					      <tr>
					        <th>App System Info</th>
					        <%-- <c:if test="${not empty xheapMaxSize}">
					        	<th width="${xSizeTab}">Web</th>
					        </c:if>
					        <th width="${xSizeTab}">App</th> --%>
					      </tr>
					    </thead>
					    <tbody>
					    	
							  <tr>
						        <td>Current Heap Size:</td>
						        
						        
						        
						        
						        <c:forEach var="rqlistValue" items="${appStatResp.systemList}">
						        <td><span style="color:blue;" >${rqlistValue.currentHeap}</span></td>
						        </c:forEach>

						      </tr>
						      
						       <tr>
						        <td>Commited Heap Size:</td>
						        
						         <c:forEach var="rqlistValue" items="${appStatResp.systemList}">
						        <td><span style="color:blue;" >${rqlistValue.commitedHeap}</span></td>
						        </c:forEach>
						       </tr>
						      
						      <tr>
						        <td>Max Heap Size:</td>
						      
						      
						       <c:forEach var="rqlistValue" items="${appStatResp.systemList}">
						        <td><span style="color:blue;" >${rqlistValue.maxHeap}</span></td>
						        </c:forEach>
						      </tr>
						      
						      <tr>
						        <td>Non Heap Size:</td>
						       <c:forEach var="rqlistValue" items="${appStatResp.systemList}">
						        <td><span style="color:blue;" >${rqlistValue.nonHeap}</span></td>
						        </c:forEach>
						      </tr>
						      
						      <tr>
						        <td>Max Non Heap Size:</td>
						        <c:forEach var="rqlistValue" items="${appStatResp.systemList}">
						        <td><span style="color:blue;" >${rqlistValue.maxNonHeap}</span></td>
						        </c:forEach>
						      </tr>  
							
					      
					    </tbody>
					  </table>
                
                
                </div>
                
                
                
                
                
                <div class="well">
                    <h4>Reload App properties</h4>
                    <c:if test="${not empty rsReload}">
					  	<div class="alert alert-${rsReload}" id="alertReload">
					  		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						  <span>${rsReloadMsg}</span>
						</div> 
					</c:if>
                    <form action="stat" method="POST">
                    	<button id="submitReload" type="submit" class="btn btn-primary">Reload</button>
                    </form>
                </div>
                
                <div class="well">
                    <h4>Reload Response Message</h4>
                    <c:if test="${not empty rsRespMsgReload}">
					  	<div class="alert alert-${rsRespMsgReload}" id="alertRespMsgReload">
					  		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						  <span>${rsRespMsgReloadMsg}</span>
						</div> 
					</c:if>
                    <form action="statmsg" method="POST">
                    	<button id="submitReload" type="submit" class="btn btn-primary">Reload</button>
                    </form>
                </div>
                
                
                
                
                
                
				
            </div>

        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Silverlake MLEB 2016</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="dist/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="dist/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready (function(){
			
			document.getElementById('alertRespMsgReload').scrollIntoView({block: 'start', behavior: 'smooth'});
			document.getElementById('alertReload').scrollIntoView({block: 'start', behavior: 'smooth'});
			$("#submitReload").click(function showAlert() {
				$("#alertReload").slideUp(50);
				$("#alertRespMsgReload").slideUp(50);
			});
		});
	</script>
	
</body>

</html>

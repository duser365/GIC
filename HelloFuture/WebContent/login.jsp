<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
  	<style>
    	h1 {text-align: center;}
    	p {text-align: center;}
    	div {text-align: center;}
  	</style>
    <title>Login</title>
  </head>
  <body>
  	<h1>Welcome to the future!</h1>
    <div>    	
	    <video id="videoID" autoplay style="border: 1px solid black;" width="320" height="240"></video>
	    <canvas id="canvasID" style="border: 1px solid black;" width="320" height="240"></canvas>
	    <br>
	    <form action="futureServlet" method="post">
		    <input type="button" value="Start Camera" onclick="start()" style="width: 159px; height: 30px;"/>
		    <input type="button" value="Stop Camera" onclick="stop()" style="width: 159px; height: 30px;"/>
		   	<input type="button" value="Take Photo" onclick="capture()" style="width: 159px; height: 30px;"/>
		   	<input type="submit" value="Submit" onclick="stop()" style="width: 159px; height: 30px;"/>
			<input type="hidden" name="imageData" id="imageID" />
		</form>		
   	</div>
   	
	<script type="text/javascript">
		var video = document.getElementById('videoID');
		var canvas = document.getElementById('canvasID');
		var context = canvas.getContext('2d');

		function capture() {
			context.drawImage(video, 0, 0, canvas.width, canvas.height);
			document.getElementById('imageID').value = canvas.toDataURL("image/png");
		};
		
		var stop = function() {
			  var stream = video.srcObject;
			  var tracks = stream.getTracks();

			  for (var i = 0; i < tracks.length; i++) {
			    var track = tracks[i];
			    track.stop();
			  }

			  video.srcObject = null;
		}

		var start = function() {
			var video = document.getElementById("videoID"),
			 vendorUrl = window.URL || window.webkitURL;

			if (navigator.mediaDevices.getUserMedia) {
				navigator.mediaDevices.getUserMedia({ video: true })
				.then(function (stream) {
				  video.srcObject = stream;
				}).catch(function (error) {
				  console.log("Something went wrong!");
				});
			}
		}
	</script>
</body>  
</html>
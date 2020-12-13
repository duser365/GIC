<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="image/logo.png" />
<link rel="stylesheet" href="css/style1.css">
<title>Registration</title>
</head>
<body>

	<div class="testbox">
		<form action="uploadUserDetails" method="post"
			enctype="multipart/form-data">
			<div class="site-logo">
				<img src="image/logo.png" alt="logo">
			</div>

			<p class="top-info">Thank you for banking with us. Please register your
				photo to help us serve you better.</p>
			<p class="top-info">Say goodbye to using debit card at our ATMs!</p>
			<div class="item">
				<p>
					Full Name<span class="required">*</span>
				</p>
				<div class="name-item">
					<input type="text" id="fname" name="fname" placeholder="full name"
						required />
				</div>
			</div>
			<div class="contact-item">
				<div class="item">
					<p>
						Email<span class="required">*</span>
					</p>
					<input type="email" name="name" placeholder="email id" required />
				</div>
			</div>
			<div class="contact-item">
				<div class="item">
					<p>
						Phone<span class="required">*</span>
					</p>
					<input type="text" name="name" placeholder="phone number" required />
				</div>
			</div>
			<div class="position-item">
				<div class="item">
					<p>
						Date of Birth<span class="required">*</span>
					</p>
					<input type="date" name="bdate" required /> <i
						class="fas fa-calendar-alt"></i>
				</div>
			</div>
			<div class="position-item">
				<div class="item">
					<p>Upload Photo:</p>
					<input type="file" name="inputPhoto" accept="image">
				</div>
			</div>
			<div class="btn-block">
				<button type="submit">Submit Details</button>
			</div>

		</form>
	</div>
</body>
</html>
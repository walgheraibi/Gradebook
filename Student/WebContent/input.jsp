<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grade Form</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>

	<ul class="nav nav-tabs">
		<li role="presentation" ><a data-toggle="tab"
			href="#form">Add Assignment </a></li>
		<li role="presentation"><a data-toggle="tab" href="#byStudent">
				assignments by a student</a></li>
		<li role="presentation"><a data-toggle="tab" href="#byType">
				assignments by a type </a></li>
					<li role="presentation"><a data-toggle="tab" href="#bySandT">
				assignments by a type & Student</a></li>
	</ul>
	<div class="tab-content">

		<div id="form" class="tab-pane fade in active">
			<div class="container">
				<br /> <br />

				<div class="jumbotron">
					<center>
						<h1>Add Assignment Form</h1>
					</center>
					<form class="form-horizontal" action="Studentform" method="post">
						<input type="hidden" name="action" value="add">
						<div class="form-group">
							<input type="text" class="form-control" id="studentid"
								name="studentid" placeholder="Student ID" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="classname"
								name="classname" placeholder="Class Name" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="assignmentname"
								name="assignmentname" placeholder="Assignment Name" required>
						</div>
						<div class="form-group">
							<div class="dropdown">
								<select class=" btn btn-primary btn-lg " name="type"
									text="Assignment Type">
									<option class=" btn btn-primary btn-lg " name="type"
										value="homework">homework</option>
									<option class=" btn btn-primary btn-lg " name="type"
										value="quiz">quiz</option>
									<option class=" btn btn-primary btn-lg " name="type"
										value="test">test</option>
									<option class=" btn btn-primary btn-lg " name="type"
										value="project">project</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" id="date" name="date"
								placeholder="Date (MM-DD-YYYY)" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="grade" name="grade"
								placeholder="Grade" required>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-lg btn-block"
								value="Add Student">
						</div>
					</form>
				</div>
			</div>
		</div>

		<div id="byStudent" class="tab-pane fade">
			<div class="container">
				<br /> <br />

				<div class="jumbotron">
					<center>
						<h1>assignments by a student</h1>
					</center>
					<form class="form-horizontal" action="Studentform" method="post">
						<input type="hidden" name="action" value="byStudent">
						<div class="form-group">
							<input type="text" class="form-control" id="getstudentid"
								name="getstudentid" placeholder="Student ID" required>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-lg btn-block"
								value="Get All Assignment for this Student">
						</div>
					</form>
					${assignmentbyid}
				</div>
			</div>
		</div>

		<div id="byType" class="tab-pane fade">
			<div class="container">
				<br /> <br />

				<div class="jumbotron">
					<center>
						<h1>assignments by a type</h1>
					</center>
					<form class="form-horizontal" action="Studentform" method="post">
						<input type="hidden" name="action" value="byType">
						<div class="form-group">
							<div class="dropdown">
								<select class=" btn btn-primary btn-lg " name="assigntype"
									text="Assignment Type">
									<option class=" btn btn-primary btn-lg " name="assigntype"
										value="homework">homework</option>
									<option class=" btn btn-primary btn-lg " name="assigntype"
										value="quiz">quiz</option>
									<option class=" btn btn-primary btn-lg " name="assigntype"
										value="test">test</option>
									<option class=" btn btn-primary btn-lg " name="assigntype"
										value="project">project</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-lg btn-block"
								value="Get All Assignment for this Type">
						</div>
					</form>
					${assignmentbytype}
				</div>
			</div>
		</div>

		<div id="bySandT" class="tab-pane fade">
			<div class="container">
				<br /> <br />

				<div class="jumbotron">
					<center>
						<h1>assignments by a type & Student</h1>
					</center>
					<form class="form-horizontal" action="Studentform" method="post">
						<input type="hidden" name="action" value="bySandT">

						<div class="form-group">
							<input type="text" class="form-control" id="getsid" name="getsid"
								placeholder="Student ID" required>
						</div>

						<div class="form-group">
							<div class="dropdown">
								<select class=" btn btn-primary btn-lg " name="assignmtype"
									text="Assignment Type">
									<option class=" btn btn-primary btn-lg " name="assignmtype"
										value="homework">homework</option>
									<option class=" btn btn-primary btn-lg " name="assignmtype"
										value="quiz">quiz</option>
									<option class=" btn btn-primary btn-lg " name="assignmtype"
										value="test">test</option>
									<option class=" btn btn-primary btn-lg " name="assigntype"
										value="project">project</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-lg btn-block"
								value="Get All Assignment for this Type and This Student">
						</div>
					</form>
					${assignmentbyst}
				</div>
			</div>
		</div>

	</div>
</body>

</html>
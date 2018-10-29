<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AngularJS Application</title>

<!-- Javascript Files -->
<script type="text/javascript" src="resource/js/angular.min.js"></script>
<script type="text/javascript" src="resource/js/form.js"></script>

<!-- CSS Styling -->
<style type="text/css">
.cssStyling {
	color: green;
	font-size: larger;
}
</style>
</head>
<body>
	<h1>Consulta Tweets</h1>
	<div ng-app="consultaTweets">
		<div>
			<form ng-controller="TweetsController" ng-submit="getTop5Users()">
				<p></p>
				<p>
					<button id="formBtn" type="submit">Top 5 users from tweets</button>
				</p>
				<p>
					<span id="welcomeText" class="cssStyling">{{msgFromServlet}}</span>
				</p>
			</form>
		</div>
		<div>
			<form ng-controller="TweetsController"
				ng-submit="getTotalTweetsByHour()">
				<p></p>
				<p>
					<button id="formBtn" type="submit">Tweets By hour</button>
				</p>
				<p>
					<span id="welcomeText" class="cssStyling">{{msgFromServlet}}</span>
				</p>
			</form>
		</div>
		<div>
			<form ng-controller="TweetsController"
				ng-submit="getTweetCountByTagAndLocation()">
				<p>
					<label>Type a tag to filter or leave it empty to get all
						results:</label> <input id="tag" type="text" ng-model="tag"
						placeholder="Hashtag" onblur="this.placeholder = 'Hashtag'"
						onfocus="this.placeholder = ''" />
				</p>
				<p>
					<button id="formBtn" type="submit">Tweets By
						location/language</button>
				</p>
				<p>
					<span id="welcomeText" class="cssStyling">{{msgFromServlet}}</span>
				</p>
			</form>
		</div>
	</div>
</body>
</html>
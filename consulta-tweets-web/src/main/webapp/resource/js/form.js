var consultaTweetsApp = angular.module("consultaTweets", []);

consultaTweetsApp
		.controller(
				"TweetsController",
				[
						'$scope',
						'$http',
						function($scope, $http) {

							$http.defaults.headers.post["Content-Type"] = "application/json; charset=utf-8";

							$scope.getTweetCountByTagAndLocation = function() {
								console.log("Tag -> " + $scope.tag);
								$scope.tag = $scope.tag == "" ? undefined
										: $scope.tag
								$http(
										{
											url : 'http://localhost:8080/consulta-tweets-api/tweetCountByTagAndLocation',
											method : "GET",
											params : {
												'tag' : $scope.tag
											}
										}).then(function(response) {
									console.log("Success -> " + response.data);
									$scope.msgFromServlet = response.data;
								}, function(response) {
									console.log("Failure -> " + response.data);
									$scope.msgFromServlet = response.data;
								});
							};

							$scope.getTotalTweetsByHour = function() {
								$http(
										{
											url : 'http://localhost:8080/consulta-tweets-api/totalTweetsByHour',
											method : "GET"
										}).then(function(response) {
									console.log("Success -> " + response.data);
									$scope.msgFromServlet = response.data;
								}, function(response) {
									console.log("Failure -> " + response.data);
									$scope.msgFromServlet = response.data;
								});
							};

							$scope.getTop5Users = function() {
								$http(
										{
											url : 'http://localhost:8080/consulta-tweets-api/top5Users',
											method : "GET"
										}).then(function(response) {
									console.log("Success -> " + response.data);
									$scope.msgFromServlet = response.data;
								}, function(response) {
									console.log("Failure -> " + response.data);
									$scope.msgFromServlet = response.data;
								});
							};

						} ]);
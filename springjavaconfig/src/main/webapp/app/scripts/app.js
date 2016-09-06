var app = angular.module('stompapp', []);
app.controller('MyController', function($scope) {
	$scope.msg = "Creepy.";

	$scope.onClick = function() {
		var socket = new SockJS('/springjavaconfig/rest/chat');
		var stompClient = Stomp.over(socket);
		
		
		stompClient.connect({}, function(frame) {
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/messages', function(messageOutput) {
				alert(messageOutput);
			});
		});
	};

});
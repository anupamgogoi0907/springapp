var app = angular.module('myapp', []);
app.controller('IndexController', function($scope) {
	$scope.msg = "Creepy."
	connect();
	function connect() {
		var socket = new SockJS('/gs-guide-websocket');
		var stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/greetings', function(greeting) {
				showGreeting(JSON.parse(greeting.body).content);
			});
		});

	}
	;
});
var app = angular.module('stompapp', []);
app.controller('MyController', function ($scope) {
	var client = null;
	$scope.stompmsg = '';

	// Connect to Websocket.
	connectStomp();


    function connectStomp() {
        var socket = new SockJS('/springjavaconfig/ws/websocket');
		client = Stomp.over(socket);
		client.connect({}, function (frame) {
			console.log('Connected: ' + frame);
			client.subscribe('/topic/ping', function (result) {
				alert(result.body);
			});
		});
    };
	$scope.onStomp = function () {
		client.send('/app/ping', {}, $scope.stompmsg);
	};


});
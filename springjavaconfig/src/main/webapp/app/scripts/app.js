var app = angular.module('stompapp', []);
app.controller('MyController', function($scope) {
	var client = null;

	$scope.info = {
		msginput : "",
		msgoutput : ""
	};
		
	// Connect to Websocket.
	connectStomp();

	function connectStomp() {
		var socket = new SockJS('/springjavaconfig/ws/websocket');
		client = Stomp.over(socket);
		client.connect({}, function(frame) {
			console.log('Connected: ' + frame);
			
			// Subscribe to messages published on this destination.
			client.subscribe('/topic/ping', function(result) {
				var data = result.body;
				
				$scope.info.msgoutput = data;
				$scope.$apply();
			});
		});
	}
	;
	
	// Send a message.
	$scope.onStomp = function() {
		client.send('/app/ping', {}, $scope.info.msginput);
	};

});
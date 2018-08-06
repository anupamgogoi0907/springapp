var app = angular.module("app", [ ]);
app.controller("indexController", function($scope,$http) {
    $scope.tags=[];
    $scope.users=[];
    $scope.dates=[];
    $scope.mapDataByTag={};
    $scope.mapDataByHour={};

    $scope.getUsers=function(){
       $http.get("/stats/users").then(function successCallback(response) {
       				$scope.users = response.data.data;
       				console.log($scope.users);

       			}, function errorCallback(response) {
       				console.log(response);
       			});
    };

    $scope.getDataByHour=function(){
        $http.get("/stats/hour").then(function successCallback(response) {
                                $scope.mapDataByHour=response.data.data;
                                $scope.dates=Object.keys($scope.mapDataByHour);
                   				console.log($scope.mapDataByHour);
                   			}, function errorCallback(response) {
                   				console.log(response);
                   			});
    };

    $scope.getDataByTag=function(){
        $http.get("/stats/tag").then(function successCallback(response) {
                            $scope.mapDataByTag=response.data.data;
                            $scope.tags=Object.keys($scope.mapDataByTag);
               				console.log($scope.mapDataByTag);
               			}, function errorCallback(response) {
               				console.log(response);
               			});
    };


});
(function() {
    var userAdminApp = angular.module('UserAdminApp', ["ngRoute"]);

    userAdminApp.config(
        function($routeProvider) {
            $routeProvider
                .when("/", {
                    templateUrl : "ListUsers.html",
                    controller : "ListUserController"
                })
                .when("/EditUser/:userId", {
                    templateUrl : "EditUser.html",
                    controller : "editUserController"
                })
                .when("/CreateUser", {
                    templateUrl : "CreateUser.html",
                    controller : "CreateUserController"
                });
        });

    var ListUserController = function($scope) {
        var settings = {
            "url": "http://localhost:8080/rest/users",
            "method": "GET",
            "timeout": 0,
        }

        $.ajax(settings).done(function (response) {
            $scope.users = response;
            $scope.$digest();
        });
    }
    ListUserController.$inject = ['$scope'];
    userAdminApp.controller('ListUserController', ListUserController);

    var CreateUserController = function ($scope) {
        $scope.error = "";
        $scope.roles = [ "Admin", "Pharmaceut", "Produktionsleder", "Laborant" ];
        $scope.newUser = { userId: "", cprNumber: "", userName: "", ini: "", role: "" };

        $scope.submitCreate = function() {
            var settings = {
                url: "/rest/users",
                method: "POST",
                data: JSON.stringify($scope.newUser),
                contentType: "application/json",
                success : function() {
                    location.href = '/';
                },
                error : function(data) {
                    $scope.error = data.responseText;
                    $scope.$digest();
                }
            }
            $.ajax(settings);
        }
    }
    CreateUserController.$inject = ['$scope'];
    userAdminApp.controller('CreateUserController', CreateUserController);

    var editUserController = function($scope, $routeParams)
    {
        $scope.error = "";

        var settings = {
            "url": "http://localhost:8080/rest/users/" + $routeParams.userId,
            "method": "GET",
            "timeout": 0,
        }

        $.ajax(settings).done(function (response) {
            $scope.editedUser = response;
            $scope.$digest();
        });

        $scope.roles = [ "Admin", "Pharmaceut", "Produktionsleder", "Laborant" ];

        $scope.submitEdit = function()
        {
            var settings = {
                url: "/rest/users/",
                method : "PUT",
                data: JSON.stringify($scope.editedUser),
                contentType: "application/json",
                success : function () {
                    location.href = '/';
                },
                error : function(data) {
                    $scope.error = data.responseText;
                    $scope.$digest();
                }
            }
            $.ajax(settings);
        }
    }
    editUserController.$inject = ['$scope', '$routeParams'];
    userAdminApp.controller('editUserController', editUserController);
})();
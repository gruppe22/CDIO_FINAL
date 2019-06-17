(function() {
    var userAdminApp = angular.module('UserAdminApp', ["ngRoute"]);

    userAdminApp.config(
        function($routeProvider) {
            $routeProvider
                .when("/", {
                    templateUrl : "LogInd.html",
                    controller: "LogIndController"
                })
                .when("/ListUsers", {
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

    var LogIndController = function($scope) {
        $scope.submitLogIn = function() {
            $.ajax({
                url: "http://localhost:8080/rest/brugere/" + angular.element('#brugerlogininput').val(),
                method: "GET",
                timeout: 0,
                success:
                    function (data) {
                    if (data.rolle.toLowerCase() == "administrator") {
                        angular.element('#ajaxchangediv').html('<a class="nav-link" href="#!ListUsers"> Id fundet. Klik her</a>');
                    }
                    else if (data.rolle.toLowerCase() == "farmaceut") {
                        angular.element('#ajaxchangediv').html('<a class="nav-link" href="#!ListComm"> Id fundet. Klik her</a>');
                    }
                    else if (data.rolle.toLowerCase() == "produktionsleder") {
                        angular.element('#ajaxchangediv').html('<a class="nav-link" href="#!ListProdBatch">Id fundet. Klik her</a>');
                    }
                    else if (data === undefined) {
                        angular.element('#ajaxchangediv').html('<p style="color : red;"> Indtastet brugerId har ingen rolle, eller kunne ikke findes</p>');
                    }
                    else {
                        angular.element('#ajaxchangediv').html('<p style="color : red;"> Indtastede brugerId har ikke rettigheder til denne applikation. </p>');
                    }
                },
                error : function () {
                    /*angular.element('#ajaxchangediv').html('<p style="color : red;"> BrugerId kunne ikke findes. </p>')*/
                    angular.element('#ajaxchangediv').html('<a class= nav-link" href="#!ListUsers"> Test, tryk her. </a>');

                }
            });
        }
    };

    LogIndController.$inject = ['$scope'];
    userAdminApp.controller('LogIndController', LogIndController);

    var ListUserController = function($scope) {
        var settings = {
            "url": "http://localhost:8080/rest/brugere",
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
        $scope.newUser = { oprId: "", oprNavn: "", ini: "", cpr: "", rolle: "" };

        $scope.submitCreate = function() {
            var settings = {
                url: "/rest/brugere",
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
            "url": "http://localhost:8080/rest/brugere/" + $routeParams.userId,
            "method": "GET",
            "timeout": 0,
        }

        $.ajax(settings).done(function (response) {
            $scope.editedUser = response;
            $scope.$digest();
        });

        $scope.roles = [ "Admin", "Farmaceut", "Produktionsleder", "Laborant" ];

        $scope.submitEdit = function()
        {
            var settings = {
                url: "/rest/brugere/",
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
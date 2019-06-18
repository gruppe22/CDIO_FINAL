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
                })
                .when("/ListComm", {
                    templateUrl : "ListComm.html",
                    controller : "ListCommController"
                })
                .when("/CreateComm", {
                    templateUrl : "CreateComm.html",
                    controller : "CreateCommController"
                })
                .when("/EditComm/:raavareId", {
                    templateUrl : "EditComm.html",
                    controller : "EditCommController"
                })
                .when("/ListRecepts", {
                    templateUrl : "ListRecepts.html",
                    controller : "ListReceptsController"
                })
                .when("/ShowRecept/:receptId", {
                    templateUrl : "ShowRecept.html",
                    controller : "ShowReceptController"
                })
                .when("/CreateRecept", {
                    templateUrl : "CreateRecept.html",
                    controller : "CreateReceptController"
                })
                .when("/CreateRecept/Next/:receptId", {
                    templateUrl : "CreateReceptNext.html",
                    controller : "CreateReceptNextController"
                })
                .when("/ListProducts", {
                    templateUrl : "ListProducts.html",
                    controller : "ListProductsController"
                })
                .when("/CreateProduct", {
                    templateUrl : "CreateProduct.html",
                    controller : "CreateProductController"
                })
                .when("/ListCB", {
                    templateUrl : "ListCB.html",
                    controller : "ListCBController"
                })
                .when("/CreateCB", {
                    templateUrl : "CreateCB.html",
                    controller : "CreateCBController"
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
                        angular.element('.admindiv').css({'display' : 'inherit'});
                        angular.element('.farmaceut').css({'display' : 'inherit'});
                        angular.element('.pleder').css({'display' : 'inherit'});
                        location.href = '#!ListUsers';
                    }
                    else if (data.rolle.toLowerCase() == "pharmaceut") {
                        angular.element('.farmaceut').css({'display' : 'inherit'});
                        angular.element('.pleder').css({'display' : 'inherit'});
                        location.href = '#!ListComm';
                    }
                    else if (data.rolle.toLowerCase() == "produktionsleder") {
                        angular.element('.pleder').css({'display' : 'inherit'});
                        location.href = '#!ListProdBatch';
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
                    angular.element('#ajaxchangediv').html('<a class= nav-link" href="#!ListComm"> Test, tryk her. </a>');

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
        $scope.roles = [ "Administrator", "Pharmaceut", "Produktionsleder", "Laborant" ];
        $scope.newUser = { oprId: "", oprNavn: "", ini: "", cpr: "", rolle: "" };

        $scope.submitCreate = function() {
            var settings = {
                url: "/rest/brugere",
                method: "POST",
                data: JSON.stringify($scope.newUser),
                contentType: "application/json",
                success : function() {
                    location.href = '#!ListUsers';
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

        $scope.roles = [ "Administrator", "Pharmaceut", "Produktionsleder", "Laborant" ];

        $scope.submitEdit = function()
        {
            var settings = {
                url: "/rest/brugere/",
                method : "PUT",
                data: JSON.stringify($scope.editedUser),
                contentType: "application/json",
                success : function () {
                    location.href = '#!ListUsers';
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

    var ListCommController = function ($scope) {
        var settings = {
            "url" : "http://localhost:8080/rest/raavare",
            "method" : "GET",
            "timeout" : 0,
        };

        $.ajax(settings).done(function (response) {
            $scope.comms = response;
            $scope.$digest();
        });
    };
    ListCommController.$inject = ['$scope'];
    userAdminApp.controller('ListCommController', ListCommController);

    var CreateCommController = function ($scope) {
        $scope.error = "";
        $scope.newComm = { raavareId: "", raavareNavn: "", leverandoer: ""};

        $scope.submitCreate = function() {
            var settings = {
                url: "/rest/raavare",
                method: "POST",
                data: JSON.stringify($scope.newComm),
                contentType: "application/json",
                success : function() {
                    location.href = '#!ListComm';
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
    userAdminApp.controller('CreateCommController', CreateCommController);

    var EditCommController = function($scope, $routeParams)
    {
        $scope.error = "";

        var settings = {
            "url": "http://localhost:8080/rest/raavare/" + $routeParams.raavareId,
            "method": "GET",
            "timeout": 0,
        }

        $.ajax(settings).done(function (response) {
            $scope.editedComm = response;
            $scope.$digest();
        });

        $scope.submitEdit = function()
        {
            var settings = {
                url: "/rest/raavare/",
                method : "PUT",
                data: JSON.stringify($scope.editedComm),
                contentType: "application/json",
                success : function () {
                    location.href = '#!ListComm';
                },
                error : function(data) {
                    $scope.error = data.responseText;
                    $scope.$digest();
                }
            }
            $.ajax(settings);
        }
    }
    EditCommController.$inject = ['$scope', '$routeParams'];
    userAdminApp.controller('EditCommController', EditCommController);

    var ListReceptsController = function($scope) {
        var settings = {
            url: "/rest/recept/",
            method: "GET",
            timeout: 0
        };

        $.ajax(settings).done(function (response) {
            $scope.recepts = response;
            $scope.$digest();

        })
    }
    ListReceptsController.$inject = ['$scope'];
    userAdminApp.controller('ListReceptsController', ListReceptsController);

    var ShowReceptController = function($scope, $routeParams) {
        var settings = {
            url: "/rest/receptkomponent/" + $routeParams.receptId,
            method: "GET",
            timout: 0
        };


        var settings2 = {
            "url" : "http://localhost:8080/rest/raavare",
            "method" : "GET",
            "timeout" : 0,
        };

        $.ajax(settings).done(function (response) {
            let fetchedReceptKomponent = response;
                    $.ajax(settings2).done(function (response) {
                    for(i = 0; i < fetchedReceptKomponent.length; i++) {
                        for(j = 0; j < response.length; j++) {
                            if (fetchedReceptKomponent[i].raavareId === response[j].raavareId) {
                                fetchedReceptKomponent[i].raavareNavn = response[j].raavareNavn;
                            }
                        }
                    }

                    $scope.result = fetchedReceptKomponent;
                    $scope.$digest();
                });
            $scope.$digest();
        });


        var settings3 = {
            url : "rest/recept/" + $routeParams.receptId,
            method: "GET",
            timeout: 0
        };

        $.ajax(settings3).done(function(response) {
            $scope.recept = response;
            $scope.$digest()
        });
    }

    ShowReceptController.$inject = ['$scope', '$routeParams'];
    userAdminApp.controller('ShowReceptController', ShowReceptController);

    var CreateReceptController = function ($scope) {
        $scope.error = "";
        $scope.newRecept = {receptId: "", receptNavn: ""};


        $scope.submitRecept = function () {
            var settings = {
                url: "rest/recept/",
                method: "POST",
                data: JSON.stringify($scope.newRecept),
                contentType: "application/json",
                success: function () {
                    location.href="#!CreateRecept/Next/" + $scope.newRecept.receptId
                },
                error: function (data) {
                    $scope.error = data.responseText;
                    $scope.$digest()
                }
            };

            $.ajax(settings)
        };
    }

    CreateReceptController.$inject = ['$scope'];
    userAdminApp.controller("CreateReceptController", CreateReceptController);

    var CreateReceptNextController = function ($scope, $routeParams) {
        $scope.error = "";
        $scope.newReceptNext = {receptId: $routeParams.receptId, raavareId: "", nomNetto:"", tolerance:""};


        $scope.nextRecept = function () {
            var settings = {
                url: "rest/receptkomponent/",
                method: "POST",
                data: JSON.stringify($scope.newReceptNext),
                contentType: "application/json",
                success: function () {
                    location.href="#!CreateRecept/Next/" + $routeParams.receptId
                },
                error: function (data) {
                    $scope.error = data.responseText;
                    $scope.$digest()
                }
            };

            $.ajax(settings)
        };

        $scope.doneRecept = function() {
            var settings = {
                url: "rest/receptkomponent/",
                method: "POST",
                data: JSON.stringify($scope.newReceptNext),
                contentType: "application/json",
                success: function () {
                    location.href="#!ListRecepts"
                },
                error: function (data) {
                    $scope.error = data.responseText;
                    $scope.$digest()
                }
            };

            $.ajax(settings)
        }
    }

    CreateReceptNextController.$inject = ['$scope', '$routeParams'];
    userAdminApp.controller("CreateReceptNextController", CreateReceptNextController);

    var ListCBController = function($scope) {
        var settings = {
            url: "/rest/raavarebatch/",
            method: "GET",
            timeout: 0
        };

        $.ajax(settings).done(function (response) {
            $scope.recepts = response;
            $scope.$digest();

        })

        console.log($scope.recepts);
    }
    ListCBController.$inject = ['$scope'];
    userAdminApp.controller('ListCBController', ListCBController);

    var CreateCBController = function ($scope) {
        $scope.error = "";
        $scope.newCB = {rbId: "", raavareId: "", maengde: ""};


        $scope.submitCB = function () {
            var settings = {
                url: "rest/raavarebatch/",
                method: "POST",
                data: JSON.stringify($scope.newCB),
                contentType: "application/json",
                success: function () {
                    location.href="#!ListCB"
                },
                error: function (data) {
                    $scope.error = data.responseText;
                    $scope.$digest()
                }
            };

            $.ajax(settings)
        };
    }

    CreateCBController.$inject = ['$scope'];
    userAdminApp.controller("CreateCBController", CreateCBController);

    var ListProductsController = function($scope) {
        var settings = {
            url: "/rest/produktbatch/",
            method: "GET",
            timeout: 0
        };

        $.ajax(settings).done(function (response) {
            $scope.recepts = response;
            $scope.$digest();

        })
    }
    ListProductsController.$inject = ['$scope'];
    userAdminApp.controller('ListProductsController', ListProductsController);

    var CreateProductController = function ($scope) {
        $scope.error = "";
        $scope.newProduct = {pbId: "", receptId: "", status: 0};


        $scope.submitProduct = function () {
            var settings = {
                url: "rest/produktbatch/",
                method: "POST",
                data: JSON.stringify($scope.newProduct),
                contentType: "application/json",
                success: function () {
                    location.href="#!ListProducts"
                },
                error: function (data) {
                    $scope.error = data.responseText;
                    $scope.$digest()
                }
            };

            $.ajax(settings)
        };
    }

    CreateProductController.$inject = ['$scope'];
    userAdminApp.controller("CreateProductController", CreateProductController);
})();
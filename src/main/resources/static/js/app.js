(function () {

    var app = angular.module('notesApp', ['ngRoute', 'ngMaterial']);

    app.config(['$locationProvider', '$routeProvider',
        function ($locationProvider, $routeProvider) {

            $routeProvider
                .when('/', {
                    templateUrl: '/partials/notes-view.html',
                    controller: 'notesController'
                })
                .when('/login', {
                    templateUrl: '/partials/login.html',
                    controller: 'loginController',
                })
                .otherwise('/');
        }
    ]);

    app.run(['$rootScope', '$location', 'AuthService', function ($rootScope, $location, AuthService) {
        $rootScope.$on('$routeChangeStart', function (event) {

            if ($location.path() == "/login") {
                return;
            }

            if (!AuthService.isLoggedIn()) {
                console.log('DENY');
                event.preventDefault();
                $location.path('/login');
            }
        });
    }]);

    app.service('AuthService', function ($http) {
        var loggedUser = null;

        function login(username, password) {
            return $http.post("api/login", {username: username, password: password}).then(function (user) {
                loggedUser = user;
                setLoggedInState(true);
            }, function (error) {
                loggedUser = null;
                // If there is an error, log it to the console
                console.error(error);
            })
        }

        function logout() {
            // Clear the user's session
            setLoggedInState(false);
            // Redirect the user to the login page
            window.location.href = '/#/login';
        }

        function setLoggedInState(state) {
            sessionStorage.setItem('isLoggedIn', state);
        }

        function isLoggedIn() {
            return sessionStorage.getItem('isLoggedIn') === 'true';
        }

        return {
            logout: logout,
            login: login,
            isLoggedIn: isLoggedIn
        }
    });

    app.controller('loginController', function ($scope, AuthService, $location) {

        $scope.invalidCreds = false;
        $scope.login = {
            username: null,
            password: null
        };

        $scope.login = function () {
            AuthService.login($scope.login.username, $scope.login.password).then(function (user) {
                console.log(user);
                $location.path("/");
            }, function (error) {
                console.log(error);
                $scope.invalidCreds = true;
            });
        };
        $scope.logout = function () {
            AuthService.logout();
        }

    });


    app.controller('notesController', function ($scope, $http) {

        $scope.isEditCreateView = false;

        $scope.newNoteView = function () {
            $scope.selectedNote = {};
            $scope.isEditCreateView = true;
        };


        $scope.clearScreen = function () {
            $scope.selectedNote = null;
            $scope.isEditCreateView = false;
        };

        $scope.cancelEdit = function () {
            $scope.selectedNote = null;
            $scope.isEditCreateView = false;
        };

        $scope.deleteNote = function (i) {
            var r = confirm("Are you sure you want to delete this note?");
            if (r == true) {
                $http.delete('/api/notes/' + i)
                    .then(function (response) {
                        // If the request is successful, update the notes list
                        $scope.getNotes();
                        $scope.clearScreen()
                    }, function (error) {
                        // If there is an error, log it to the console
                        console.error(error);
                    });
            }
        };

        $scope.viewNote = function (note) {
            $scope.selectedNote = note;
            $scope.isEditCreateView = true;
        }

        // Define a function to save/update a note
        $scope.saveNote = function () {

            if ($scope.selectedNote.id) {
                // Send a POST or PUT request to the server API to save/update the note
                $http.put('/api/notes/' + $scope.selectedNote.id, $scope.selectedNote)
                    .then(function (response) {
                        // If the request is successful, update the notes list
                        $scope.getNotes();
                    }, function (error) {
                        // If there is an error, log it to the console
                        console.error(error);
                    });
            } else {
                // Send a POST or PUT request to the server API to save/update the note
                $http.post('/api/notes', $scope.selectedNote)
                    .then(function (response) {
                        // If the request is successful, update the notes list
                        $scope.getNotes();
                        $scope.clearScreen();
                    }, function (error) {
                        // If there is an error, log it to the console
                        console.error(error);
                    });
            }
        };

        // Define a function to get the latest list of notes from the server
        $scope.getNotes = function () {
            // Send a GET request to the server API to retrieve the notes list
            $http.get('/api/notes')
                .then(function (response) {
                    // If the request is successful, update the notes list on the client-side
                    $scope.notes = response.data;
                    console.log($scope.notes)
                }, function (error) {
                    // If there is an error, log it to the console
                    console.error(error);
                });
        };

        $scope.getNotes()

    });

})();
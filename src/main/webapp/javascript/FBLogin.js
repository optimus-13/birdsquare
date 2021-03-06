window.fbAsyncInit = function () {
    FB.init({
        appId:'454719261240892', // App ID from the App Dashboard
        status:true, // check the login status upon init?
        cookie:true, // set sessions cookies to allow your server to access the session?
        xfbml:true  // parse XFBML tags on this page?
    });

    FB.Event.subscribe('auth.login', function (response) {
        var uid = response.authResponse.userID;
        document.cookie = 'fbuid=' + uid;

        setFbuidInCheckinFormPage(uid)
        fetchUserData(response);
        redirectToReferrerPage();
    });

    FB.Event.subscribe('auth.logout', function (response) {
        document.cookie = '';
        redirectToLogin();
    });

    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
            // the user is logged in and has authenticated your
            // app, and response.authResponse supplies
            // the user's ID, a valid access token, a signed
            // request, and the time the access token
            // and signed request each expire

            var uid = response.authResponse.userID;
            fetchUserData(response);
            document.cookie = 'fbuid=' + uid;
            setFbuidInCheckinFormPage(uid);

//            } else if (response.status === 'not_authorized') {
//                // the user is logged in to Facebook,
//                // but has not authenticated your app
        } else {
            document.cookie = '';
            // the user isn't logged in to Facebook.
            redirectToLogin();
        }
    });

    function redirectToReferrerPage() {
        if (document.URL === fetchUrl('/login')) {
            window.location.href = document.referrer;
        }
    }

    function redirectToLogin() {
        if (document.URL != fetchUrl('/login')) {
            window.location.href = fetchUrl('/login');
        }
    }

    function setFbuidInCheckinFormPage(uid) {
        if (document.URL === fetchUrl('/checkinform')) {
            document.getElementById('fbuid').value = uid;
        }
    }

    function fetchUrl(thePage) {
        var loginUrl = document.URL.replace(/\/[^\/]+$/, '') + thePage;
        return 'http://' + loginUrl.replace('http://', '').replace('//','/');
    }

    function fetchUserData(response) {
        if (document.URL === fetchUrl('/home') | document.URL === fetchUrl('/') ) {
            FB.api('/me', function (response) {
                $("#username").append(response.name);
                if (response.location != null)
                    $('#city').append(response.location.name);
                $('#fbpic').append("<img style='width:100px; height:100px' src='http://graph.facebook.com/" + response.id + "/picture'/>")
            });
        }
    }
};

// Load the SDK Asynchronously
(function(d){
    var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
    js = d.createElement('script'); js.id = id; js.async = true;
    js.src = "//connect.facebook.net/en_US/all.js";
    d.getElementsByTagName('head')[0].appendChild(js);
}(document));

function loginUser() {
    FB.login(function (response) {
        }, {scope:'user_likes, offline_access'}
    );

};

function logOutUser() {
    FB.logout(function (response) {
    });
}

let login = {
    url : {
        doLoginUrl : 'http://localhost:8081/auth/doLogin'
    },
    method : {
        doLogin : function (params) {
            let username = params.username
            let password = params.password
            if (username == '' || username == null || username == undefined){
                $('#login-username').removeClass("form-control is-valid")
                $('#login-username').addClass("form-control is-invalid")
            }else {
                $('#login-username').removeClass("form-control is-invalid")
                $('#login-username').addClass("form-control is-valid")
            }
            if (password == '' || password == null || password == undefined){
                $('#login-password').removeClass("form-control is-valid")
                $('#login-password').addClass("form-control is-invalid")
            }else {
                $('#login-password').removeClass("form-control is-invalid")
                $('#login-password').addClass("form-control is-valid")
            }
            $.ajax({
                url : login.url.doLoginUrl,
                type : 'post',
                dataType : 'json',
                data: {
                    'username' : username,
                    'password' : password
                },
                success : function (result) {
                    if (result != null && result.code == '200'){
                        location.href = result.action
                    }
                },
                error : function (res) {
                    console.log(res)
                }
            })
        }
    }
}
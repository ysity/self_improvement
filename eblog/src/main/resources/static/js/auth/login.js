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
                    if (result != null){
                        if (result.code == '200'){
                            location.href = result.action
                        }
                        if (result.code == '501'){
                            alert(result.msg + "请先去注册")
                            location.href = "/auth/reg"
                        }
                        if(result.code == '502'){
                            alert(result.msg)
                            $('#login-password').val()
                        }
                        if (result.code == '503'){
                            alert(result.msg)
                        }
                        if(result.code == '504'){
                            alert(result.msg)
                            location.href = "/index"
                        }
                    }

                },
                error : function (res) {
                    console.log(res)
                }
            })
        }
    }
}
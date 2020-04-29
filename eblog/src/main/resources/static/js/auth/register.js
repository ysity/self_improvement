let register = {
    url:{
        doRegisterUrl:'http://localhost:8081/auth/register'
    },
    data:{
        flagName : false,
        flagPas : false,
        flagRePas : false,
        flagEmail : false,
    },

    method:{
        validateName : function (selector) {
            let name = selector.val()
            if(name.length<2||name.length>10){
                selector.removeClass("form-control is-valid")
                selector.addClass("form-control is-invalid");
                register.data.flagName = false;
            }else{
                selector.removeClass("form-control is-invalid")
                selector.addClass("form-control is-valid");
                register.data.flagName = true;
            }
        },
        validateEmail : function (selector) {
            let pattern = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/
            if (!pattern.test(selector.val())){
                selector.removeClass("form-control is-valid")
                selector.addClass("form-control is-invalid");
                register.data.flagEmail= false
            }else {
                selector.removeClass("form-control is-invalid")
                selector.addClass("form-control is-valid");
                register.data.flagEmail = true
            }
        },
        validatePassword : function (selector) {
            let passWord= selector.val();
            if(passWord.length<6||passWord.length>18){
                selector.removeClass("form-control is-valid")
                selector.addClass("form-control is-invalid");
                register.data.flagPas = false;
            }else{
                selector.removeClass("form-control is-invalid")
                selector.addClass("form-control is-valid");
                register.data.flagPas = true;
            }
        },
        validateRePassword : function (selector1,selector2) {
            let passWord = selector1.val();
            let repassWord = selector2.val();
            if((passWord!=repassWord)||(repassWord.length<6||repassWord.length>18)){
                selector2.removeClass("form-control is-valid")
                selector2.addClass("form-control is-invalid");
                register.data.flagRePas = false;
            }else{
                selector2.removeClass("form-control is-invalid")
                selector2.addClass("form-control is-valid");
                register.data.flagRePas = true;
            }
        },
        //登录操作
        doLogin:function (params) {
            let email = params.email
            let username = params.username
            let password = params.password
            let repassword = params.repassword
            let captcha = params.captcha
            let user = {
                "username": username,
                "email" : email,
                "password" : password
            }
            let vo = {}
            vo.user = user
            vo.captcha = captcha
            vo.repassword = repassword
            $.ajax({
                url : register.url.doRegisterUrl,
                type: 'post',
                data: JSON.stringify(vo),
                dataType : 'json',
                headers : {"Content-Type" : "application/json;charset=utf-8"},
                success : function (result) {
                    if (result != null && result.code == '200'){
                        location.href = result.action;
                    }
                },
                error : function (result) {
                    console.log(result)
                    console.log("error")
                }
            })
        }
    }
}
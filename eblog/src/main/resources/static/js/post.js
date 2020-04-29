window.onload = function () {
    post();
}
let post = function () {
    let url = "http://localhost:8081/post/hotpos"
    ajax("post",url,true)
}
let ajax = function (type,url,flag) {
    let xmlhttp
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest()
    }else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.status == 200 && xmlhttp.readyState == 4){
            articles(xmlhttp.responseText)
        }
    }
    xmlhttp.open(type,url,flag)
    xmlhttp.send();
}

let articles = function (data) {
    if (data != null){
        let res = JSON.parse(data);
        if (res.code == "200"){
            let str = ''
            for(var i = 0; i<res.data.length; i++){
                str += `<li>[`+(i+1)+`]<a>`+res.data[i].title+`</a></li>`
            }
            $('.list').append(str)
        }
    }

}



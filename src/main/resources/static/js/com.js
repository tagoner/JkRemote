/* 전역 변수 */
const getMethod = "GET";
const postMethod = "POST";


/* 공통 함수 */
/*
    - getRequest 함수
    
    ex)
    var param = {
        "test" : "test"
    };
    getRequest("/test", param, (arg) => {
        console.log(arg);
    });  
*/
function getAction(url, jsonParam, callback) {
    httpRequest(url, getMethod, jsonParam, callback);
}

/*
    - postRequest 함수
    
    ex)
    var param = {
        "test" : "test"
    };
    postRequest("/test", param, (arg) => {
        console.log(arg);
    });  
*/
function postAction(url, jsonParam, callback) {
    httpRequestGetData(url, postMethod, jsonParam, callback);
}

/*
    - httpRequest 함수
*/
function httpRequestGetData(url, method, param, callback) {
    var res = fetch(url, {
        method
    ,   headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    ,   body: JSON.stringify(param)
    })
    .catch(e => {
        console.error(e);
        alert("에러가 발생했습니다. \r\n관리자에게 문의해주십시오.");
    });
    
    res
    .then(arg => {
        if(arg.redirected) {
            window.location.href = arg.url;
            return;
        }
        else
            return arg.text();
    })
    .then(rst => {
        var data = "";
        
        try {
            data = JSON.parse(rst);
        }
        catch(e) {
            console.error(e);
            data = rst;
        }
        
        callback(data);
    })
}

/*
    - httpRequest 함수
*/
function httpRequestGetResponse(url, param, callback) {
    fetch(url, {
        method: postMethod
    ,   headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    ,   body: JSON.stringify(param)
    })
    .then(res => { debugger
        callback(res);
    })
    .catch(e => {
        console.error(e);
        alert("에러가 발생했습니다. \r\n관리자에게 문의해주십시오.");
    });
}
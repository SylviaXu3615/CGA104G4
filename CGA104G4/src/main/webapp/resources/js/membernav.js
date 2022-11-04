//動態生成NAV-BAR
let body = document.querySelector("body");
let divwrapper = document.createElement("div");
divwrapper.setAttribute("class", "wrapper");
let header = document.createElement("header");
header.innerHTML =
    `<div class="container-fluid">
        <div class="header-content d-flex flex-wrap align-items-center">
            <div class="logo">
                <a href="index.html" title="">
                    <img class="logoimg" src="../resources/images/1.png" alt="">
                </a>
            </div>
            <nav>
                <ul>
                    <li><a href="" >吉食專區</a></li>
                    <li><a href="" >最新消息</a></li>
                    <li><a href="" >關於我們</a></li>
                    <li><a href="" >Q&A</a></li>
                    <li><a href="" >消費資訊+</a>
                        <ul>
                            <li><a href="" >最愛店家</a></li>
                            <li><a href="" >預約福袋</a></li>
                            <li><a href="" >訂單資訊</a></li>
                        </ul>
                    </li>
                    <li><a href="" >個人資訊+</a>
                        <ul>
                            <li><a href="memberinfoupdate.html" class="mem-info">個人基本資料</a></li>
                            <li><a href="JPSfilterTest.jsp" >更改密碼</a></li>
                            <li><a href="" >錢包專區</a></li>
                            <li><a href="" >個人訊息</a></li>
                        </ul>
                    </li>
                 </ul>
             </nav>
            <div class="menu-btn">
                <span class="bar1"></span>
                <span class="bar2"></span>
                <span class="bar3"></span>
            </div>
            <ul class="oth-lnks ml-auto">
                <li>
                    <a href="#" title="" class="">
                        <img alt="" src="../resources/images/bell.svg" style="width:2rem">
                    </a>
                    <span class="cart-item-num">0</span>
                </li>
                <li>
                    <a href="#" title="" class="">
                        <img alt="" src="../resources/images/shopping-cart.svg" style="width:2rem">
                    </a>
                     <span class="cart-item-num">0</span>
                </li>
            </ul>
            <nav class="padding-remove">
                <ul id="logul">
                    <li><a href="" title="">登入|註冊</a>
                        <ul class="uladjust">
                            <li><a href="./membersignup.html" title="">會員註冊</a></li>
                            <li><a href="./memberlogin.html" title="">會員登入</a></li>
                            <li><a href="" title="">商家註冊</a></li>
                            <li><a href="./storelogin.html" title="">商家登入</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div >`

//動態生成response-navbar
let div = document.createElement("div");
div.setAttribute("class", "responsive-mobile-menu");
div.innerHTML =
    `<ul>
        <li><a href="">吉食專區</a></li>
        <li><a href="">最新消息</a></li>
        <li><a href="">關於我們</a></li>
        <li><a href="">Q&A</a></li>
        <li><a href="">消費資訊+</a>
            <ul>
                <li><a href="">最愛店家</a></li>
                <li><a href="">預約福袋</a></li>
                <li><a href="">訂單資訊</a></li>
            </ul>
        </li>
        <li><a href="#" >個人資訊+</a>
            <ul>
                <li><a href="memberinfoupdate.html" class="mem-info">個人基本資料</a></li>
                <li><a href="JPSfilterTest.jsp" >更改密碼</a></li>
                <li><a href="" >錢包專區</a></li>
                <li><a href="" >個人訊息</a></li>
            </ul>
        </li>
        <li id="log-il-response"><a href="">登入|註冊</a>
            <ul>
                <li><a href="./membersignup.html">會員註冊</a></li>
                <li><a href="./memberlogin.html">會員登入</a></li>
                <li><a href="" title="">商家註冊</a></li>
                <li><a href="./storelogin.html" title="">商家登入</a></li>
            </ul>
        </li>
    </ul>`

divwrapper.append(header);
divwrapper.append(div);
body.insertAdjacentElement("afterbegin", divwrapper);

//頁面載入時判斷是否有登入
(async function logincheck(){
    let memData = sessionStorage.getItem("memData");
    if(memData){
        loginNavChange();
        return;
    }

    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx+"/member/memberlogin.do?action=loginCheck";

    let response = await fetch(url,{ method: 'get'}).then(e=>e.json());
    if(response.state){
        let memData={};
        memData.memId = response.memId;
        memData.memName = response.memName;
        memData.memPic = response.memPic;
        sessionStorage.setItem("memData",JSON.stringify(memData));
        loginNavChange();

    }
})();

//登入改變NAV顯示結果
function loginNavChange(){
    //改變一般navbar
    document.querySelector("#logul").innerHTML="";
    document.querySelector("#logul").innerHTML= `<li><a href="" title="" class="logout">登出</a></li>`;
    //改變response-navbar
    document.querySelector("#log-il-response").innerHTML="";
    document.querySelector("#log-il-response").innerHTML=`<a href="" class="logout">登出</a>`;
//綁定登出連結觸發事件(resposne與一般NAR綁定同事件)
    document.querySelectorAll("a.logout").forEach(e=>e.addEventListener("click",function (e) {
        e.preventDefault();
        logout();
    }));
}

//登出時刪除SESSION與sessionStorage資料
async function logout(){
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx+"/member/memberlogin.do?action=logout";

    await fetch(url,{ method: 'get'})
        .then(response=>{
        if (response.ok){
            sessionStorage.removeItem("memData");
            window.location.reload();
            window.location.href = "./index.html";
        }
    })

}






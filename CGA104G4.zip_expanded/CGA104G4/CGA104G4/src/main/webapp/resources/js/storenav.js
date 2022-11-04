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
                    <li><a href="" >基本資料維護</a></li>
                    <li><a href="" >我的評論</a></li>
                    <li><a href="" >帳務管理</a></li>
                    <li><a href="" >購買廣告</a></li>
                    <li><a href="" >一般商品管理+</a>
                        <ul>
                            <li><a href="" >商品變更</a></li>
                            <li><a href="" >訂單管理</a></li>
                        </ul>
                    </li>
                    <li><a href="" >福袋商品管理+</a>
                        <ul>
                            <li><a href="" >福袋變更</a></li>
                            <li><a href="" >福袋訂單管理</a></li>
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
                     <a href="" title="" class="">
                         <img alt="" src="../resources/images/sign-out.svg" style="width:2rem">
                         <span>登出</span>
                     </a>
                </li>
            </ul>
        </div>
    </div >`


let div = document.createElement("div");
div.setAttribute("class", "responsive-mobile-menu");
div.innerHTML =
    `<ul>
        <li><a href="" >基本資料維護</a></li>
        <li><a href="" >我的評論</a></li>
        <li><a href="" >帳務管理</a></li>
        <li><a href="" >購買廣告</a></li>
        <li><a href="" >一般商品管理+</a>
        <ul>
             <li><a href="" >商品變更</a></li>
             <li><a href="" >訂單管理</a></li>
        </ul>
        </li>
        <li><a href="" >福袋商品管理+</a>
              <ul>
                   <li><a href="" >福袋變更</a></li>
                   <li><a href="" >福袋訂單管理</a></li>
              </ul>
        </li>
        <li><a href="" >登出</a></li>              
    </ul>`

divwrapper.append(header);
divwrapper.append(div);
body.insertAdjacentElement("afterbegin", divwrapper);

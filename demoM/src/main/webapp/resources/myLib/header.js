let testing = document.querySelectorAll('.testing'),
    testing_logo = document.querySelector('.testing_logo'),
    shopping_basket = document.querySelector('.shopping_basket'),
    menu_list_main = document.querySelectorAll('.menu_list_main>ul>li>a'),
    menu_list_sub = document.querySelectorAll('.menu_list_sub>ul>li>a'),
    Team_members = document.querySelector(".Team_members"),
    clientData,
    ShoppingBasketData,
    jyh = document.querySelector('.jyh'),
    lhn = document.querySelector('.lhn'),
    ksb = document.querySelector('.ksb'),
    kso = document.querySelector('.kso'),
    jgj = document.querySelector('.jgj'),
    img = document.querySelector('.img'),
    header_acide = document.querySelector('.header_acide');
    loginComplete =
    {
        hname: "",
        hid: "",
        hpw: "",
        birthDate: "",
        sx: "",
        zip: "",
        har: "",
        dar: "",
        darCb: "",
        hpn: "",
        hem: "",
        cpn: "",
        car: "",
        cdar: "",
        hsp: "",
        img: "",
        totalAmountPayment: "", //총 결제금액
        reserve: "", //적립금
        coupon: "", //쿠폰 
        ShoppingBasket: "",
        Order: "",
        lastestOrder: "", //최근 주문정보
        lastestPosted: "", //최근 등록 게시글
        wishList: "" //관심 상품 정보
    };
var path = window.location.pathname,
    herfChange;

// 메뉴바 활성화시에 뒤 흐린화면 연출
let createBack = document.createElement('div');
header_acide.appendChild(createBack);
createBack.className = "create_back";
createBack.addEventListener('click', change);

//메뉴바(헤더) 움직임 함수
function change() {
    let menuList = document.querySelector('.menu_list'),
        menuIcon1 = document.querySelector('.menu_icon1'),
        menuIcon2 = document.querySelector('.menu_icon2'),
        menuIcon3 = document.querySelector('.menu_icon3'),
        menu_icon_box = document.querySelector('.menu_icon_box');
    if (menu_icon_box.id == "menuBtn1") {

        menuList.style.animation = "slidein 0.7s";
        menuList.style.animationFillMode = "forwards";

        menuIcon1.style.animation = "animate-line-1 1s";
        menuIcon1.style.animationFillMode = "forwards";

        menuIcon2.style.animation = "animate-line-2 1s";
        menuIcon2.style.animationFillMode = "forwards";

        menuIcon3.style.animation = "animate-line-3 1s";
        menuIcon3.style.animationFillMode = "forwards";

        menu_icon_box.id = "menuBtn2";

        createBack.style.animation = "create_back_in 1s"
        createBack.style.display = "block"
    } else {
        menuList.style.animation = "slideout 0.7s";

        menuIcon1.style.animation = "animate-line-4 1s";

        menuIcon2.style.animation = "animate-line-5 1s";

        menuIcon3.style.animation = "animate-line-6 1s";

        menu_icon_box.id = "menuBtn1";

        createBack.style.animation = "create_back_out 1s"
        setTimeout(() => { createBack.style.display = "none" }, 990);
        
    }
}
/*//로그인 정보 db에서 가져옴
async function getClientData() {
    try {
        const response = await axios.get('http://localhost:3000/loginComplete/1');

        clientData = response.data;
        loginCheck(clientData);
    } catch (err) {
        loginCheck(loginComplete);
        console.log('데이터를 가져오는 중 오류 발생');
        console.log(err.message);
    }
};
//로그인 여부에 따라 메뉴바 경로가 바뀌도록 일괄 설정
if (path === '/index.html') {
    console.log('나는 메인이다');
    herfChange = './';
} else {
    herfChange = '../';
}
const
    mainLink = [
        `${herfChange}LHN/Perfumepage.html`, "", "", "", "", ""
    ],
    loginLink = [ //로그인 후
        `${herfChange}/index.html`,
        // `javascript:void(0);`,
        `${herfChange}mypage/mypage_Edit.html`,
        `${herfChange}mypage/mypage.html`,
        `${herfChange}clientcenter_main/index.html`,
        `${herfChange}order/order.html`,
        `${herfChange}store/store.html`,
        `${herfChange}event/event.html`,
        `${herfChange}brandstory/brandstory.html`
    ],
    logioutLink = [ //로그인 전
        `${herfChange}member/member.html`,
        `${herfChange}idinfo/idinfo.html`,
        `${herfChange}member/member.html`,
        `${herfChange}clientcenter_main/index.html`,
        `${herfChange}member/member.html`,
        `${herfChange}store/store.html`,
        `${herfChange}event/event.html`,
        `${herfChange}brandstory/brandstory.html`
    ];

//정보를 받아서 로그인한 여부를 확인한후에 메뉴바(해더) 정보 변경
function loginCheck(clientData) {
    const hearder_icon = document.querySelectorAll(".hearder_icon>ul>li>a");
    hearder_icon[0].href = `${herfChange}cart/cart.html`; 
    hearder_icon[2].href = `${herfChange}search/search.html`;
    for (let i = 0; i < menu_list_main.length; i++) {
        menu_list_main[i].href = mainLink[i]
    }
    
    if (clientData.hname != '') {
        testing[0].addEventListener('click', () => {
            const response = axios.put('http://localhost:3000/loginComplete/1', loginComplete);
            putClientData(clientData);
        });
        for (let i = 0; i < menu_list_sub.length; i++) {
            menu_list_sub[i].href = loginLink[i]
        }
        testing[0].innerText = `로그아웃`
        testing[1].innerText = `회원정보`
        testing_logo.href = `${herfChange}mypage/mypage.html`;
        shopping_basket.innerText = clientData.ShoppingBasket.length;
    } else {
        for (let i = 0; i < menu_list_sub.length; i++) {
            menu_list_sub[i].href = logioutLink[i]
        }
        testing[0].innerText = `로그인`
        testing[1].innerText = `회원가입`
        testing_logo.href = `${herfChange}member/member.html`;
        shopping_basket.innerText = 0;
    }
};
//활동하며 바뀐 정보를 다시 profile에 저장
async function putClientData(clientData) {
    try {
        const response = await axios.get('http://localhost:3000/profile/');
        profileData = response.data;
        for (let i = 0; i < profileData.length; i++) {
            if ((profileData[i].hid == clientData.hid) && (profileData[i].hpw == clientData.hpw)) {
                console.log(`같은 아이디는 ${profileData[i].hid}, 비밀번호는 ${profileData[i].hpw} id는 ${i+1}`);
                const responsepost = await axios.put(`http://localhost:3000/profile/${i+1}`, clientData);
            }
        }
    } catch (err) {
        console.log('데이터를 가져오는 중 오류 발생');
        console.log(err.message);
    }
}*/
//모든 페이지에 폰트 일괄 부여
function addFont() {
    const head = document.querySelector('head');
    head.innerHTML += `
    <link rel="shortcut icon" href="https://danielstruth.com/shopimages/dmcosmetic/favicon.ico" type="image/x-icon">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville&family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville&family=Noto+Sans+KR&family=Nunito+Sans:opsz@6..12&display=swap" rel="stylesheet">
    `;
}

/*getClientData();*/
addFont();
/*Team_members.innerHTML = "";*/
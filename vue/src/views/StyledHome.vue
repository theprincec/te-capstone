<template >
    <div id="page">
        <svg id="bg-circles" width="740" height="507" viewBox="0 0 740 507" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M740 257C740 395.071 628.071 507 490 507C351.929 507 240 395.071 240 257C240 118.929 351.929 7 490 7C628.071 7 740 118.929 740 257Z" fill="#AAAAAA" fill-opacity="0.4"/>
            <path id="orange-circle" fill-rule="evenodd" clip-rule="evenodd" d="M173 342C266.336 342 342 266.336 342 173C342 79.6639 266.336 4 173 4C79.6639 4 4 79.6639 4 173C4 266.336 79.6639 342 173 342ZM173 346C268.545 346 346 268.545 346 173C346 77.4547 268.545 0 173 0C77.4547 0 0 77.4547 0 173C0 268.545 77.4547 346 173 346Z"  fill-opacity="0.4" stroke="#FFBC6C" stroke-width="1"/>
        </svg>
        <div id="bottom-page"></div>

        <div id="bottom-page-gradient"></div>
        <div id="circle-decoration">
            <div class="circle c1"></div>
            <div class="circle c1"></div>
            <div class="circle c1"></div>
            <div class="circle c1"></div>
            <div class="circle c2"></div>
            <div class="circle c2"></div>
            <div class="circle c2"></div>
        </div>
        <div id="buttons-nav">
            <router-link :to="{ name: 'register' }" style="text-decoration: none" >
                <button id="create">Create Account</button>
            </router-link>
            <router-link :to="{ name: 'login' }" style="text-decoration: none" >
                <button id="login">Log In</button>
            </router-link>
        </div>
        <div id="middle-section">
            <div id="middle-text">
                <h2 id="scheduling-text">
                    <svg width="700"  viewBox="0 0 793 109" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M59.3537 34.6379H34.9856V27.1592C34.9856 23.6692 34.674 21.4463 34.0508 20.4907C33.4276 19.5351 32.3889 19.0573 30.9347 19.0573C29.3558 19.0573 28.1509 19.7013 27.32 20.9893C26.5306 22.2773 26.1358 24.2301 26.1358 26.8476C26.1358 30.213 26.5929 32.7475 27.5069 34.451C28.3795 36.1544 30.8516 38.2111 34.9233 40.6209C46.5984 47.5594 53.9524 53.2516 56.9855 57.6972C60.0185 62.1429 61.535 69.31 61.535 79.1985C61.535 86.3863 60.6833 91.6837 58.9798 95.0907C57.3179 98.4976 54.0771 101.364 49.2575 103.691C44.4379 105.976 38.8289 107.119 32.4304 107.119C25.4088 107.119 19.405 105.789 14.4192 103.13C9.47498 100.471 6.23422 97.085 4.69693 92.9717C3.15964 88.8584 2.391 83.0209 2.391 75.4591V68.8529H26.7591V81.1305C26.7591 84.9114 27.0915 87.3419 27.7562 88.4222C28.4626 89.5024 29.6882 90.0426 31.4333 90.0426C33.1783 90.0426 34.4663 89.357 35.2972 87.9859C36.1698 86.6148 36.606 84.579 36.606 81.8783C36.606 75.9369 35.7958 72.0522 34.1754 70.224C32.5135 68.3959 28.421 65.3421 21.8979 61.0626C15.3748 56.7416 11.0538 53.6047 8.93485 51.6519C6.81589 49.6992 5.05009 46.9985 3.63745 43.55C2.26636 40.1015 1.58081 35.6974 1.58081 30.3377C1.58081 22.6097 2.55719 16.9591 4.50996 13.386C6.50428 9.81281 9.7035 7.02907 14.1076 5.03476C18.5117 2.99889 23.8299 1.98096 30.0622 1.98096C36.8761 1.98096 42.6721 3.08199 47.4501 5.28405C52.2697 7.48611 55.4482 10.2698 56.9855 13.6353C58.5643 16.9591 59.3537 22.6305 59.3537 30.6493V34.6379Z" fill="white" fill-opacity="0.3"/>
                    <path d="M150.232 48.0996H123.995V30.5246C123.995 25.4142 123.704 22.2358 123.122 20.9893C122.582 19.7013 121.356 19.0573 119.445 19.0573C117.284 19.0573 115.913 19.826 115.332 21.3632C114.75 22.9005 114.459 26.2244 114.459 31.3348V78.2013C114.459 83.104 114.75 86.3032 115.332 87.799C115.913 89.2947 117.222 90.0426 119.258 90.0426C121.211 90.0426 122.478 89.2947 123.06 87.799C123.683 86.3032 123.995 82.7924 123.995 77.2665V64.615H150.232V68.5413C150.232 78.97 149.484 86.3655 147.989 90.7281C146.535 95.0907 143.273 98.9131 138.204 102.195C133.177 105.478 126.965 107.119 119.57 107.119C111.883 107.119 105.547 105.727 100.561 102.943C95.5755 100.16 92.2724 96.3164 90.652 91.4137C89.0317 86.4694 88.2215 79.053 88.2215 69.1646V39.686C88.2215 32.4151 88.4708 26.9723 88.9693 23.3576C89.4679 19.7013 90.9429 16.1905 93.3942 12.8251C95.8871 9.45965 99.3149 6.82133 103.677 4.91011C108.082 2.95734 113.13 1.98096 118.822 1.98096C126.55 1.98096 132.927 3.47669 137.955 6.46817C142.982 9.45965 146.285 13.199 147.864 17.6862C149.443 22.1319 150.232 29.0704 150.232 38.5019V48.0996Z" fill="white" fill-opacity="0.3"/>
                    <path d="M239.117 4.09992V105H212.879V62.6207H205.026V105H178.788V4.09992H205.026V40.1846H212.879V4.09992H239.117Z" fill="white" fill-opacity="0.3"/>
                    <path d="M268.857 4.09992H312.607V24.2924H295.095V43.4254H311.485V62.6207H295.095V84.8075H314.352V105H268.857V4.09992Z" fill="white" fill-opacity="0.3"/>
                    <path d="M341.101 4.09992H360.733C373.405 4.09992 381.964 4.6816 386.409 5.84495C390.897 7.0083 394.304 8.91952 396.63 11.5786C398.957 14.2377 400.411 17.2084 400.993 20.4907C401.575 23.7315 401.865 30.1299 401.865 39.686V75.0229C401.865 84.0804 401.429 90.1464 400.557 93.221C399.726 96.254 398.251 98.6431 396.132 100.388C394.013 102.092 391.395 103.296 388.279 104.003C385.163 104.668 380.468 105 374.194 105H341.101V4.09992ZM367.339 21.3632V87.7366C371.12 87.7366 373.446 86.9888 374.319 85.493C375.191 83.9557 375.628 79.8217 375.628 73.0909V33.89C375.628 29.3197 375.482 26.3906 375.191 25.1026C374.901 23.8146 374.236 22.8797 373.197 22.2981C372.158 21.6748 370.206 21.3632 367.339 21.3632Z" fill="white" fill-opacity="0.3"/>
                    <path d="M490.438 4.09992V71.5328C490.438 79.1777 490.189 84.5582 489.69 87.6743C489.192 90.7489 487.717 93.9273 485.265 97.2096C482.814 100.45 479.573 102.923 475.543 104.626C471.554 106.288 466.839 107.119 461.396 107.119C455.371 107.119 450.053 106.122 445.441 104.127C440.829 102.133 437.381 99.5364 435.096 96.3371C432.811 93.1379 431.46 89.7725 431.045 86.2409C430.629 82.6677 430.421 75.189 430.421 63.8048V4.09992H456.659V79.7594C456.659 84.1635 456.888 86.9888 457.345 88.2352C457.843 89.4401 458.82 90.0426 460.274 90.0426C461.936 90.0426 462.995 89.3778 463.452 88.0482C463.951 86.6772 464.2 83.4779 464.2 78.4506V4.09992H490.438Z" fill="white" fill-opacity="0.3"/>
                    <path d="M545.98 4.09992V84.8075H561.934V105H519.742V4.09992H545.98Z" fill="white" fill-opacity="0.3"/>
                    <path d="M613.737 4.09992V105H587.499V4.09992H613.737Z" fill="white" fill-opacity="0.3"/>
                    <path d="M701.998 4.09992V105H679.001L665.352 59.1306V105H643.415V4.09992H665.352L680.06 49.533V4.09992H701.998Z" fill="white" fill-opacity="0.3"/>
                    <path d="M791.942 41.2441H765.704V32.0827C765.704 26.3075 765.455 22.6928 764.956 21.2386C764.457 19.7844 763.273 19.0573 761.404 19.0573C759.783 19.0573 758.682 19.6805 758.1 20.927C757.519 22.1734 757.228 25.3726 757.228 30.5246V78.9492C757.228 83.4779 757.519 86.4694 758.1 87.9236C758.682 89.3362 759.845 90.0426 761.59 90.0426C763.502 90.0426 764.79 89.2324 765.454 87.612C766.161 85.9916 766.514 82.8339 766.514 78.139V66.1731H761.217V50.8418H791.942V105H775.426L772.995 97.7706C771.209 100.887 768.945 103.234 766.202 104.813C763.502 106.35 760.303 107.119 756.605 107.119C752.201 107.119 748.067 106.059 744.203 103.94C740.38 101.78 737.472 99.1209 735.477 95.9632C733.483 92.8055 732.237 89.5024 731.738 86.0539C731.239 82.5639 730.99 77.3496 730.99 70.411V40.4339C730.99 30.7947 731.51 23.7938 732.548 19.4312C733.587 15.0687 736.558 11.08 741.46 7.46533C746.405 3.80908 752.782 1.98096 760.593 1.98096C768.28 1.98096 774.657 3.55979 779.726 6.71746C784.795 9.87513 788.098 13.6353 789.636 17.9978C791.173 22.3188 791.942 28.6134 791.942 36.8815V41.2441Z" fill="white" fill-opacity="0.3"/>
                    <path d="M59.3537 34.6379H34.9856V27.1592C34.9856 23.6692 34.674 21.4463 34.0508 20.4907C33.4276 19.5351 32.3889 19.0573 30.9347 19.0573C29.3558 19.0573 28.1509 19.7013 27.32 20.9893C26.5306 22.2773 26.1358 24.2301 26.1358 26.8476C26.1358 30.213 26.5929 32.7475 27.5069 34.451C28.3795 36.1544 30.8516 38.2111 34.9233 40.6209C46.5984 47.5594 53.9524 53.2516 56.9855 57.6972C60.0185 62.1429 61.535 69.31 61.535 79.1985C61.535 86.3863 60.6833 91.6837 58.9798 95.0907C57.3179 98.4976 54.0771 101.364 49.2575 103.691C44.4379 105.976 38.8289 107.119 32.4304 107.119C25.4088 107.119 19.405 105.789 14.4192 103.13C9.47498 100.471 6.23422 97.085 4.69693 92.9717C3.15964 88.8584 2.391 83.0209 2.391 75.4591V68.8529H26.7591V81.1305C26.7591 84.9114 27.0915 87.3419 27.7562 88.4222C28.4626 89.5024 29.6882 90.0426 31.4333 90.0426C33.1783 90.0426 34.4663 89.357 35.2972 87.9859C36.1698 86.6148 36.606 84.579 36.606 81.8783C36.606 75.9369 35.7958 72.0522 34.1754 70.224C32.5135 68.3959 28.421 65.3421 21.8979 61.0626C15.3748 56.7416 11.0538 53.6047 8.93485 51.6519C6.81589 49.6992 5.05009 46.9985 3.63745 43.55C2.26636 40.1015 1.58081 35.6974 1.58081 30.3377C1.58081 22.6097 2.55719 16.9591 4.50996 13.386C6.50428 9.81281 9.7035 7.02907 14.1076 5.03476C18.5117 2.99889 23.8299 1.98096 30.0622 1.98096C36.8761 1.98096 42.6721 3.08199 47.4501 5.28405C52.2697 7.48611 55.4482 10.2698 56.9855 13.6353C58.5643 16.9591 59.3537 22.6305 59.3537 30.6493V34.6379Z" stroke="white" stroke-width="2"/>
                    <path d="M150.232 48.0996H123.995V30.5246C123.995 25.4142 123.704 22.2358 123.122 20.9893C122.582 19.7013 121.356 19.0573 119.445 19.0573C117.284 19.0573 115.913 19.826 115.332 21.3632C114.75 22.9005 114.459 26.2244 114.459 31.3348V78.2013C114.459 83.104 114.75 86.3032 115.332 87.799C115.913 89.2947 117.222 90.0426 119.258 90.0426C121.211 90.0426 122.478 89.2947 123.06 87.799C123.683 86.3032 123.995 82.7924 123.995 77.2665V64.615H150.232V68.5413C150.232 78.97 149.484 86.3655 147.989 90.7281C146.535 95.0907 143.273 98.9131 138.204 102.195C133.177 105.478 126.965 107.119 119.57 107.119C111.883 107.119 105.547 105.727 100.561 102.943C95.5755 100.16 92.2724 96.3164 90.652 91.4137C89.0317 86.4694 88.2215 79.053 88.2215 69.1646V39.686C88.2215 32.4151 88.4708 26.9723 88.9693 23.3576C89.4679 19.7013 90.9429 16.1905 93.3942 12.8251C95.8871 9.45965 99.3149 6.82133 103.677 4.91011C108.082 2.95734 113.13 1.98096 118.822 1.98096C126.55 1.98096 132.927 3.47669 137.955 6.46817C142.982 9.45965 146.285 13.199 147.864 17.6862C149.443 22.1319 150.232 29.0704 150.232 38.5019V48.0996Z" stroke="white" stroke-width="2"/>
                    <path d="M239.117 4.09992V105H212.879V62.6207H205.026V105H178.788V4.09992H205.026V40.1846H212.879V4.09992H239.117Z" stroke="white" stroke-width="2"/>
                    <path d="M268.857 4.09992H312.607V24.2924H295.095V43.4254H311.485V62.6207H295.095V84.8075H314.352V105H268.857V4.09992Z" stroke="white" stroke-width="2"/>
                    <path d="M341.101 4.09992H360.733C373.405 4.09992 381.964 4.6816 386.409 5.84495C390.897 7.0083 394.304 8.91952 396.63 11.5786C398.957 14.2377 400.411 17.2084 400.993 20.4907C401.575 23.7315 401.865 30.1299 401.865 39.686V75.0229C401.865 84.0804 401.429 90.1464 400.557 93.221C399.726 96.254 398.251 98.6431 396.132 100.388C394.013 102.092 391.395 103.296 388.279 104.003C385.163 104.668 380.468 105 374.194 105H341.101V4.09992ZM367.339 21.3632V87.7366C371.12 87.7366 373.446 86.9888 374.319 85.493C375.191 83.9557 375.628 79.8217 375.628 73.0909V33.89C375.628 29.3197 375.482 26.3906 375.191 25.1026C374.901 23.8146 374.236 22.8797 373.197 22.2981C372.158 21.6748 370.206 21.3632 367.339 21.3632Z" stroke="white" stroke-width="2"/>
                    <path d="M490.438 4.09992V71.5328C490.438 79.1777 490.189 84.5582 489.69 87.6743C489.192 90.7489 487.717 93.9273 485.265 97.2096C482.814 100.45 479.573 102.923 475.543 104.626C471.554 106.288 466.839 107.119 461.396 107.119C455.371 107.119 450.053 106.122 445.441 104.127C440.829 102.133 437.381 99.5364 435.096 96.3371C432.811 93.1379 431.46 89.7725 431.045 86.2409C430.629 82.6677 430.421 75.189 430.421 63.8048V4.09992H456.659V79.7594C456.659 84.1635 456.888 86.9888 457.345 88.2352C457.843 89.4401 458.82 90.0426 460.274 90.0426C461.936 90.0426 462.995 89.3778 463.452 88.0482C463.951 86.6772 464.2 83.4779 464.2 78.4506V4.09992H490.438Z" stroke="white" stroke-width="2"/>
                    <path d="M545.98 4.09992V84.8075H561.934V105H519.742V4.09992H545.98Z" stroke="white" stroke-width="2"/>
                    <path d="M613.737 4.09992V105H587.499V4.09992H613.737Z" stroke="white" stroke-width="2"/>
                    <path d="M701.998 4.09992V105H679.001L665.352 59.1306V105H643.415V4.09992H665.352L680.06 49.533V4.09992H701.998Z" stroke="white" stroke-width="2"/>
                    <path d="M791.942 41.2441H765.704V32.0827C765.704 26.3075 765.455 22.6928 764.956 21.2386C764.457 19.7844 763.273 19.0573 761.404 19.0573C759.783 19.0573 758.682 19.6805 758.1 20.927C757.519 22.1734 757.228 25.3726 757.228 30.5246V78.9492C757.228 83.4779 757.519 86.4694 758.1 87.9236C758.682 89.3362 759.845 90.0426 761.59 90.0426C763.502 90.0426 764.79 89.2324 765.454 87.612C766.161 85.9916 766.514 82.8339 766.514 78.139V66.1731H761.217V50.8418H791.942V105H775.426L772.995 97.7706C771.209 100.887 768.945 103.234 766.202 104.813C763.502 106.35 760.303 107.119 756.605 107.119C752.201 107.119 748.067 106.059 744.203 103.94C740.38 101.78 737.472 99.1209 735.477 95.9632C733.483 92.8055 732.237 89.5024 731.738 86.0539C731.239 82.5639 730.99 77.3496 730.99 70.411V40.4339C730.99 30.7947 731.51 23.7938 732.548 19.4312C733.587 15.0687 736.558 11.08 741.46 7.46533C746.405 3.80908 752.782 1.98096 760.593 1.98096C768.28 1.98096 774.657 3.55979 779.726 6.71746C784.795 9.87513 788.098 13.6353 789.636 17.9978C791.173 22.3188 791.942 28.6134 791.942 36.8815V41.2441Z" stroke="white" stroke-width="2"/>
                    </svg>
                    <br>
                    <span>made easy</span>
                </h2>
                <div id="bottom-text">
                    <img src="../assets/Carehub_text.png" style="width:260px">
                    <h3 id="solution-text"> 
                        CAREHUB is the solution for enterprise scheduling and ease of mind. 
                        We make scheduling as convenient as possible for you and your clients!
                    </h3>
                    <router-link :to="{ name: 'register' }" style="text-decoration: none" >
                        <button id="register">Lets Get Started</button>
                    </router-link>
                </div>
            </div>
            <div id="hero-image-container">
                <img id="hero-image" src="../assets/heroDoc.png" alt="Doctor planning with patient">
            </div>
            
        </div>

    </div>
  
</template>

<script>
    export default {
    name: "styled-home",
    components: {}
    }
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:ital@1&family=Work+Sans&display=swap');
body{
    font-family: 'Roboto', sans-serif;
    background-color:#475f69;
}
#circle-decoration{
    height: 25px;
    width: 100vw;
    position: absolute;
    bottom: 25px;
    z-index: 1;
    display: flex;
    text-align: end;
    justify-content: flex-end;
    padding-right: 8%;
}
.circle{
    display: inline-block;
    height: 25px;
    width: 25px;
    /* border: 2px solid white; */
    border-radius: 20px;
    margin-right: 6px;
}
.circle.c1{
    border: 1px solid white;
    opacity: .5;
}
.circle.c2{
    border: 1px solid white;
}

#page{
    background:linear-gradient(165deg, #d9e8ec,#4f9cbd);
    height: 100vh;
    width: 100vw;
    z-index: -10000;
    display: grid;
    grid-template-columns: 1fr 1fr ;
    grid-template-rows: 20vh 30vh 50vh;
    grid-template-areas:
        "nav-buttons nav-buttons"
        "middle middle"
        "bottom-text image";
    grid-row-gap: 20px;
    grid-column-gap: 20px;
}
#orange-circle{
    stroke-dasharray:500;
    /* stroke-dashoffset: 600; */

    animation: mymove 4s linear infinite;
    -webkit-animation: mymove 4s linear infinite;
}

path{
    -webkit-animation: mymove 4s linear infinite;
}

@keyframes circle-stroke {
    from {stroke-dashoffset: 0} ; 
    50% {stroke-dashoffset: 300} ; 
    to {stroke-dashoffset: 600} ; 
}
@keyframes mymove {
  from {left: 0px;}
  to {left: 200px;}
}
#bottom-page{
    background-color:#475f69;
    height: 55vh;
    width: 100vw;
    position: absolute;
    bottom: 0;
    z-index: 0;
}
#bottom-page-gradient{
    background-color: #4f9cbd;
    background: linear-gradient(90deg,  #5e7e8c, #8b9aa0);
    height: 35px;
    width: 100vw;
    position: absolute;
    bottom: 70px;
    z-index: 1;
}
#buttons-nav{
    /* height: 120px; */
    padding: 20px;
    padding-right: 100px;
    display: flex;
    justify-content: flex-end;
    grid-area: nav-buttons;
    flex-wrap: wrap;
}
button{
    padding: 8px 20px 10px 20px;
    border-radius: 30px;
    color: white;
    height: 2.6em;
    font-size: 1.2em;
    font-weight: 400;
    vertical-align: middle;
    text-transform: uppercase;
    margin: 0px 10px;
    letter-spacing: .1em;
    margin-top: 4px;
    
}
#create, #register{
    background-color: #ff9901;
    border: 2px solid #ff9901;

}
#create:hover{
    background: linear-gradient(#f5bd43,orange, orange, darkorange);
}
#create:hover{
    background: linear-gradient(#f5bd43,orange, orange, darkorange);
    border: 2px solid darkorange;
}
#register{
    margin-top: 20px;
    height: 2em;
    background: linear-gradient(#f5bd43,orange, orange, darkorange); 
    border: 2px solid #ff990100;
    /* border: 2pt solid darkorange; */
}
#register:hover {
    border: 2px solid darkorange;
    background-color: #ff9901;
    background: linear-gradient(#ff9901, #ff9901);

}
#login{
    background-color: #999999;
    border: 2px solid #999999;
}
#login:hover{
    background: linear-gradient(#dddddd,#999999,#999999, #787878);
    border: 2px solid #888888;

}
#middle-section{
    grid-area: middle;
    display: flex;
    flex-wrap: wrap;
    padding-right: 100px;
    justify-items: space-between;
}
#middle-text{
    width: 60%;
    min-width: 450px;
    display: inline-block;
    z-index: 250;
    padding-right: 0;

}

#scheduling-text {
    grid-area: top-text;
    text-transform: uppercase;
    font-family: 'Work Sans', sans-serif;
    color: white;
    /* padding: 200px; */
    /* background: lightcoral; */
    color: white;
    padding: 0px 30px 30px 100px;
    font-size: 40pt;
    font-weight: 1050;
    font-family: 'Works Sans', sans-serif;
    min-width: 400px;
}


#scheduling-text span{
   opacity:.9;
   margin-top: 20px;
   padding-top: 40px;
}
#scheduling-text svg{
   position: absolute;
   z-index: -500;
}
#bg-circles{
    position: absolute;
    z-index: 0;
    top: 160px;
    left: 100px;
    bottom: 500px;

}


#hero-image-container{
    grid-area: image;
    z-index: 1000;
    /* width: 100%; */
    /* text-align: left; */
    justify-content: left;
    width: 40%;
    min-width: 350px;
    display: inline-block;
    text-align: center;
    align-items: center;
}
#hero-image{
    width: 100%;
    position: relative;
}

#bottom-text{
    /* width: 300px; */
    /* background: lightcoral; */
    color: white;
    padding: 0px 30px 50px 100px;
    grid-area: bottom-text;
    z-index: 200;
    font-size: 16pt;
    font-weight: 100;
    max-width: 650px;
}

@media only screen and (max-width: 768px){
    #buttons-nav{
        padding-right: 30px;
    }
    #bottom-page{
    height: 52vh;
}

    #middle-section{
        padding-left:0px;
        padding-right: 50px;
        flex-wrap: flex-start;
        text-align: center;
    }
    #scheduling-text {
        padding: 20px 30px 30px 30px;
        min-width: 300px;
        font-size: 40px;
        
    }
    #scheduling-text svg{
        margin-left:-200px;
        opacity: .5;
    }
    #scheduling-text span{
        opacity: 1;
    }
    #bottom-text{
        padding: 0px 30px 50px 30px;
        font-size: 14px;
    }
    #middle-text{
        width:50%;
        min-width: 310px;
    }
    #hero-image-container{
        /* width: 100px; */
        min-width: 300px;
        padding-top: 100px;

    }
    button{ 
        height: 2.0em;
    }
    #register{
        width: 100%;
        height: 1.8em;
        padding-top: 3px;;
    }
}
@media only screen and (max-width: 450px){

    #middle-text{
        width:100%;
    }
    #hero-image-container{
        width:100%;
        display: none;
    }
    button{
        font-size: 1.1em;
        padding-top: 4px;
    }

}



</style>

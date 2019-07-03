# ChiayiTravel
Side Project 抵嘉旅遊APP
## 目前完成進度:https://www.youtube.com/watch?v=HMKAdM1KNRI
### 應用情境
1.小明一家人想要到嘉義遊玩，剛好看到APP符合他的需求，下載使用APP讓小明更快了解有關嘉義的旅遊資訊。<br>
2.小明能夠使用APP註冊會員。<br>
3.小明想要找尋嘉義景點、飯店、活動、商店的相關資訊，讓小明可以規劃住宿與安排行程。<br>
4.小明看了許多資訊，但因選項太多無法進行有效的挑選，因此小明可利用APP查詢店家、旅遊景點相關文章與遊記。<br>
5.小明到合作店家消費之後，可累積點數換取優惠券。<br>

### 系統需求說明
#### 功能性需求
##### 非會員功能
1.查詢當地景點、商家、飯店、活動的相關資訊以及遊記<br>
2.提供APP用戶加入會員<br>
3.提供商店、飯店、景點、活動擁有者進行註冊為會員<br>

##### 會員功能
1.提供會員登入系統<br>
2.提供擁有者於系統內註冊自己的商家<br>
3.提供商家消費集點功能(QRcode)及兌換優惠卷<br>
4.查詢集點數及記錄<br>

### ER Diagram及詳細說明
<img width="100%" height="100%" src="https://github.com/a26412372/ChiayiTravel_Android/blob/master/app/src/main/res/mipmap-xxxhdpi/erdplus-diagram.png"/>

##### Entity
1. 基本Entity: traveler(遊客), owner(某店家擁有者)<br>
2. 衍生Entity: shop(商店), hotel(飯店), attraction(景點), activity(活動)<br>
3. 衍生Entity: pixcrawl(爬蟲資料), coupon(優惠券)<br>

##### Relationship
<br>店家 = 商店(shop) + 飯店(hotel) + 景點(attraction) + 活動(activity)的總稱<br>
1. own: 某店家擁有者(owner)擁有(own)商店(shop)或飯店(hotel)或景點(attraction)或活動(activity)<br>
2. consume: 遊客(traveler)前往商店(shop)消費(consume)<br>
3. stay: 遊客(traveler)前往飯店(hotel)住宿(stay)<br>
4. provide: 商店(shop)或飯店(hotel)提供(provide)優惠券(coupon)<br>
8. matchArticle: 每一個店家關鍵字爬取文章至爬蟲資料(pixcrawl)<br>
9. exchange: 遊客(traveler)兌換(exchange)優惠券(coupon)<br>

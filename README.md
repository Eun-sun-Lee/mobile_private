# 개인과제_소프트웨어학과_20203118_이은선
## 실행 환경
### 맥 OS 상의 안드로이드 스튜디오에서 실행
### SDK 버전 : 안드로이드 12
```
defaultConfig {

        applicationId "com.example.eunsun"
        minSdk 31
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    } 
```

## 구현 내용
### 1. 첫번째 화면 <로그인> : MainActivity / activity_main (Relative Layout 사용) -> 구현 완료 
<img width="254" alt="image" src="https://user-images.githubusercontent.com/84428520/198939919-efd30fa4-2947-4ce0-b40e-273ec6d0ab21.png">

### activity_main
#### logoImageView, loginIdEditText, loginPasswordEditText, loginButton, signButton, noSignButton
- logoImageView : wishlist 로고 이미지를 나타냄. [ImageView]
- loginIdEditText : 아이디 입력 칸 [EditText]
- loginPasswordEditText : 비밀번호 입력 칸 [EditText]
- loginButton : 로그인 버튼 [AppCompatButton]
- signButton : 회원가입 버튼 [AppCompatButton]
- noSignButton : 회원가입 없이 앱 이용하기 버튼 [AppCompatButton]

### MainActivity
- 첫번째 화면 초기화시에 **프레퍼런스**를 통해 기존에 저장된 개인정보 읽어옴.
- loginButton(로그인 버튼) 클릭시 처리 :
  * 입력된 아이디의 길이가 7자 이하라면 Toast로 _아이디를 올바르게 입력하세요(8자 이상)._ 메세지 띄우고 아이디 입력 칸 focus
  * 입력된 비밀번호의 길이가 7자 이하이거나 17자 이상, 혹은 비밀번호 특수키 등 규칙에 맞지 않는다면 Toast로 _영문, 숫자, 특수문자를 포함해서 비밀번호를 올바르게 입력하세요(8자-16자)._ 메세지 띄우고 비밀번호 입력 칸 focus
  ```
  비밀번호 특수키 규칙 : 영문, 숫자, 특수문자를 모두 포함해야함.
  String passwordValidation = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$";
  ```
  * 만약 입력된 id를 키로 하여 프레퍼런스에서 가져온 값이 비어있지 않다면(회원가입한 회원이라면) 비밀번호 검사 진행
      * 입력된 id를 키로 해서 회원정보(비밀번호,이름,전화번호,주소)를 하나의 String으로 묶은 데이터 값을 프레퍼런스에서 가져온다.
      * 그 후 회원정보를 하나의 String으로 묶은 데이터 값을 specialKey("\\")로 split 한 후 비밀번호 정보만 가져온다.
      * 가져온 비밀번호가 현재 입력된 비밀번호와 같다면 *"currLoginId" : id*의 쌍으로 프레퍼런스에 현재 로그인된 회원 id를 저장한다. 그 후, 현재 앱에 로그인이 돼있다는 boolean 정보("isLogined", true)를 intent로 ThirdActivity(상품 리스트 화면)에 넘긴다.
      * 프레퍼런스에서 가져온 비밀번호와 현재 입력된 비밀번호가 같지 않다면 Toast로 _비밀번호가 일치하지 않습니다._ 메세지 띄우고 비밀번호 입력 칸 focus
  *  입력된 id를 키로 하여 프레퍼런스에서 가져온 값이 비어있다면(회원가입하지 않은 회원이라면) Toast로 _아이디 또는 비밀번호를 확인해주세요._ 메세지 띄우기

- signButton(회원가입 버튼) 클릭시 처리 : 
  * MainActivity(로그인 화면)에서 SecondActivity(회원가입 화면)로 intent 넘기기

- noSignButton(회원가입 없이 앱 이용하기 버튼) 클릭시 처리 : 
  * 현재 앱에 로그인이 돼있지 않다는 boolean 정보("isLogined", false)를 intent로 ThirdActivity(상품 리스트 화면)에 넘긴다.

### 2. 두번째 화면 <회원가입> : SecondActivity / activity_second (Linear Layout 사용) -> 구현 완료 
<img width="253" alt="image" src="https://user-images.githubusercontent.com/84428520/199054363-755aca70-31f8-4a10-8588-8198dcc72e36.png">
<img width="251" alt="image" src="https://user-images.githubusercontent.com/84428520/199054514-e9bd312f-3993-4a4f-b013-bd03e1af464b.png">
<img width="241" alt="image" src="https://user-images.githubusercontent.com/84428520/199215918-82f5c054-3ae0-4902-ad13-19938fd9a398.png">

### activity_second
#### backImageButton, signTextView, idTextView, idEditText, idCheckTextView, passwordTextView, passwordEditText, passwordCheckTextView, passwordTextView2, passwordEditText2, passwordCheckTextView2, nameTextView, nameEditText, phoneNumberTextView, phoneNumberEditText, addressTextView, addressEditText, privateRadioButton, privateTextView, privateRadioButton2, privateTextView2, signButton
- ScrollView 사용
- backImageButton : 뒤로 가기 버튼 [ImageButton]
- signTextView : 상단 "회원가입" 텍스트 [TextView]
- idTextView : "아이디" 텍스트 [TextView]
- idEditText : 아이디 입력 칸 [EditText]
- idCheckTextView : "이미 존재하는 아이디입니다." 텍스트 [TextView]
- passwordTextView : "비밀번호" 텍스트 [TextView]
- passwordEditText : 비밀번호 입력 칸 [EditText]
- passwordCheckTextView : "영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)" 텍스트
- passwordTextView2 : "비밀번호 재확인" 텍스트 [TextView]
- passwordEditText2 : 비밀번호 재확인 입력 칸 [EditText]
- passwordCheckTextView2 : "비밀번호가 일치하지 않습니다." 텍스트
- nameTextView : "이름" 텍스트 [TextView]
- nameEditText : 이름 입력 칸 [EditText]
- phoneNumberTextView : "전화번호" 텍스트 [TextView]
- phoneNumberEditText : 전화번호 입력 칸 [EditText]
- addressTextView : "주소" 텍스트 [TextView]
- addressEditText : 주소 입력 칸 [EditText]
- privateRadioButton : "이용약관 동의" 라디오 버튼 [RadioButton]
- privateTextView : _이용약관_에 해당하는 자세한 텍스트 내용 [TextView]
- privateRadioButton2 : "개인정보 수집 및 이용 동의" 라디오 버튼 [RadioButton]
- privateTextView2 : _개인정보 수집 및 이용_에 해당하는 자세한 텍스트 내용 [TextView]
- signButton : 가입하기 버튼 [AppCompatButton]

### SecondActivity
- 두번째 화면 초기화시에 **프레퍼런스**를 통해 기존에 저장된 개인정보 읽어옴.
- addTextChangedListener와 TextWatcher를 사용하여 아이디/비밀번호/비밀번호 재확인 editText 입력값 변화에 따른 이벤트를 처리해줌.
  * 입력된 비밀번호와 비밀번호 재확인이 같지 않다면 passwordCheckTextView2(_비밀번호가 일치하지 않습니다._) setVisiblity(View.VISIBLE)
  * 입력된 비밀번호와 비밀번호 재확인이 같다면 passwordCheckTextView2(_비밀번호가 일치하지 않습니다._) setVisiblity(View.INVISIBLE)
  * 입력된 비밀번호가 특수키 등 규칙에 맞지 않거나 비밀번호 자릿수가 7자 이하 혹은 17자 이상이라면 passwordCheckTextView(_영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)_) setVisibility(View.VISIBLE)
  * 입력된 비밀번호가 자릿수/특수키 등 규칙에 모두 부합한다면 passwordCheckTextView(_영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)_) setVisibility(View.INVISIBLE)
  ```
  비밀번호 특수키 규칙 : 영문, 숫자, 특수문자를 모두 포함해야함.
  String passwordValidation = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$";
  ```
- backImageButton(뒤로 가기 버튼) 클릭시 처리 : 
  * SecondActivity(회원가입 화면)에서 MainActivity(로그인 화면)로 intent 넘기기
  
- signButton(회원가입 버튼) 클릭시 처리 : 
  * 입력된 아이디의 길이가 7자 이하라면 Toast로 _아이디를 올바르게 입력하세요(최소 8자 이상)._ 메세지 띄우고 아이디 입력 칸 focus
  * 만약 입력된 id를 키로 하여 프레퍼런스에서 가져온 값이 비어있지 않다면(입력된 id가 이미 존재하는 아이디라면) Toast로 _이미 존재하는 아이디입니다._ 메세지 띄우고 idCheckTextView setVisibility(View.VISIBLE) 
  * 입력된 비밀번호의 길이가 7자 이하이거나 17자 이상, 혹은 비밀번호 특수키 등 규칙에 맞지 않는다면 Toast로 _영문, 숫자, 특수문자를 포함해서 비밀번호를 올바르게 입력하세요(8자-16자)._ 메세지 띄우고 비밀번호 입력 칸 focus
  * 입력된 비밀번호 재확인의 길이가 7자 이하이거나 17자 이상이라면 Toast로 _비밀번호 재확인이 필요합니다._ 메세지 띄우고 비밀번호 재확인 입력 칸 focus
  * 이름 입력 칸이 비어있거나 입력된 전화번호의 길이가 6자 이하이거나, 혹은 입력된 주소의 길이가 5자 이하라면 Toast로 _이름, 전화번호, 주소를 모두 입력해야 합니다._ 메세지 띄우기
  * 이용약관 동의 라디오 버튼이나 개인정보 수집 및 이용 동의 라디오 버튼이 체크 되어있지 않다면 Toast로 _이용약관과 개인정보 수집 및 이용에 대해 모두 동의해주세요._ 메세지 띄우기
  * 회원가입 조건에 모두 부합한다면 회원정보를 다음과 같은 방식으로 프레퍼런스에 저장
    ```  
    1. "index" : index(int형, default: 0) --> id를 프레퍼런스에 저장할때, 먼저 index 값을 불러온 후 1을 증가시켜준다.
    2. "index(int->String)" : id
    3. "id" : OneData(비밀번호\\이름\\전화번호\\주소)
       OneData 저장 예) dmstjsdmstjs2!\\Eun-sun-Lee\\01034569432\\Seoul Hongdae Hyundae Apartment
    + "currLoginId" : id --> 현재 로그인된 회원 id를 저장하기 위해 MainActivity에서 처리
    ```
    
    ```
        String specialKey = "\\\\"; 
        int prefIndex = pref.getInt("index",0);
        Integer index = prefIndex+1;
        editor.putInt("index",index); // "index" : 0,1,.... 
        editor.putString(index.toString(),id); // "0" : "eunsun208080", "1: "minsuk12",...
        String oneData = String.join(specialKey, password, name, phoneNumber, address);
        editor.putString(id,oneData);
        editor.apply();
     ```  
- 프레퍼런스에 회원정보 저장하고 SecondActivity(회원가입 화면)에서 MainActivity(로그인 화면)로 intent 넘기기
 
 ### 3. 세번째 화면 <상품 리스트> : ThirdActivity / activity_third (Constraint Layout 사용) -> 구현 완료 
 <img width="238" alt="image" src="https://user-images.githubusercontent.com/84428520/199215335-39a3f184-0b57-4bf7-82a7-210e5f9b5516.png">
<img width="235" alt="image" src="https://user-images.githubusercontent.com/84428520/199215422-1d41e8d0-3eeb-4aeb-8540-3abd17066e41.png">

 ### activity_third
 #### wishlistImageView, downImageView, productRecyclerView, pdRegisterButton, pdDeleteButton, mypageImageButton
 - ScrollView 
 - wishlistImageView : wishlist 로고 이미지를 나타냄. [ImageView]
 - downImageView : 아래 방향 화살표 이미지를 나타냄. [ImageView]
 - productRecyclerView : 상품명과 상품 이미지를 동적 목록으로 관리 [RecyclerView]
 - pdRegisterButton : 상품 등록하기 버튼 [AppCompatButton]
 - pdDeleteButton : 상품 삭제하기 버튼 [AppCompatButton]
 - mypageImageButton : 마이페이지 버튼 [ImageButton]


### ThirdActivity
- 세번째 화면 초기화시에 **프레퍼런스**를 통해 기존에 저장된 개인정보 읽어옴.
- getExtra()를 통해 앱의 로그인 여부를 나타내는 boolean 정보("isLogined")를 intent로 받아온다. 
- RecyclerView 객체와 productAdapter를 연결해준다.
- productAdapter에 Product 타입의 데이터를 addItem()을 통해 넣어준다. 
- mypageImageButton(마이페이지 버튼) 클릭시 처리 : 
  * intent로 받아온 "isLogined" 값이 true라면 ThirdActivity(상품 리스트 화면)에서 MyPageActivity(마이페이지 화면)으로 intent 넘기기
  * intent로 받아온 "isLogined" 값이 false라면 AlertDialog(_회원가입 하시겠습니까?_) 띄우기
      * 사용자가 _예_ 버튼 클릭 시 ThirdActivity(상품 리스트 화면)에서 SecondActivity(회원가입 화면)으로 intent 넘기기
      * 사용자가 _아니요_ 버튼 클릭 시 dialog.cancel()을 통해 AlertDialog 취소하기




 
 
 
    



  






    



- View을 상속한 여러가지 위젯을 사용하여 화면을 구성(기능에 맞는 위젯 선택하여 구성)
  View Group을 상속한 위젯 ListView, GridView, AdapterView, ToolBar 등
  Text View을 상속한 CheckBox, Switch, ToggleButton, RadioButton 등
  ImageView, ImageButton 등

* 과제 제출시에는 이캠퍼스 개인과제 제출 페이지에서 구현내용과 실행환경(Readme.txt 파일)을
  자세하게 설명하고 소스 파일은 압축(ZIP)하여 등록
* Readme.txt 파일에는 SDK 버전(Android 버전 12)을 기재하고 
  구현된 기능을 간략하게 설명하고 그외 앱 실행 환경 및 참고할 내용이 있다면 기재

* 각페이지 구성시에 View을 상속한 여러가지 위젯을 사용하여 화면을 구성
  (기능에 맞는 위젯 선택하여 구성)
   ListView, GridView, AdapterView, ToolBar, Text View, CheckBox, Switch, 
   ToggleButton, RadioButton, ImageView, ImageButton 등

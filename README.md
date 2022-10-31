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
  * MainActivity에서 SecondActivity(회원가입 화면)로 intent 넘기기

- noSignButton(회원가입 없이 앱 이용하기 버튼) 클릭시 처리 : 
  * 현재 앱에 로그인이 돼있지 않다는 boolean 정보("isLogined", false)를 intent로 ThirdActivity(상품 리스트 화면)에 넘긴다.


  
- 
- ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
- ID, 비밀번호 입력이 정상이고 로그인 버튼 클릭 시 세번째 페이지 이동
- 회원가입 없이도 메인 버튼(상품 출력 페이지)을 클릭하면 세번째 화면으로 이동 가능 

2. 두번째 화면 (Linear Layout 혹은 Fragment 사용) - 5점
- 회원가입 페이지, 첫번째 페이지에서 회원가입 버튼 클릭 시 출력
- ID(EditView, 중복검사), 비밀번호(EditView, 자릿수/특수키 등 규칙 체크)
- 이름/전화번호/주소(EditView)
- 개인정보 사용 동의 간략 약관(TextView), 동의 여부(Radio Button, Decline/Accept)
- 회원정보를 저장하고 첫번째 페이지로 이동 
  회원정보 저장은 전역변수, 프레퍼런스(Preference), 파일 중에 하나를 선택하여 활용  

3. 세번째 화면 (Constraint Layout, Table Layout, Grid Layout, Frame Layout 중 하나 사용) - 5점
- 상품명, 상품이미지 리스트를 보여주는 화면 (5개이상 이미지를 기본으로 출력)
  (선택) 화면 아래 부분에서 상품명, 상품이미지를 등록 및 삭제하는 버튼 추가
- 화면 아래 부분에 회원정보 버튼은 회원인 경우는 가입한 회원 정보를 보여주고
  회원이 아닌 경우는 회원정보 버튼을 클릭하면 회원가입 여부를 물어보고
  원하면 회원가입 페이지인 두번째 화면으로 이동 (유저관리 - 5점)
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

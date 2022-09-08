# Git Token 발급 받아 사용하기

* 2021년 08월부터 id/pwd 만으로 github에 push할 수 없다. 

## Git Token 발급받기

* Github > Settings > Developer settings > Personal access tokens 에서 발급

## Git Token 사용하여 Push 하기

### 1) git remote set-url

* 원격 저장소 URL 을 https://<생성된 토큰 붙여넣기>@github.com/username/repo.git 형태로 변경하면 사용 가능하다.

```shell
$ git remote set-url origin https://<생성된 토큰 붙여넣기>@github.com/username/repo.git
```

### 2) Source Tree를 사용하는 경우

* 소스트리 저장소 메인 화면 우 상단의 설정 > 수정 버튼 > 원격 탭
* 저장되 원격 주소를 아래와 같은 형태로 수정한다.<br>
https://<생성된 토큰 붙여넣기>@github.com/username/repo.git

### 3) Windows 에서 코드로 디바이스 추가하여 연동

* https://github.com/settings/connections/applications/0120e057bd645470c1ed

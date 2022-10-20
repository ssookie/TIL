# "MongoDB 데이터 삭제"

## 도큐먼트에서 특정 필드의 전체 데이터 삭제하기

* users 라는 모델의 필드 A의 데이터를 삭제

```javascript
db.users.update({}, {$unset: {A:1}}, {multi: true});
```

* cf) 아무거나 하나의 도큐먼트의 A 필드 삭제시

```javascript
db.users.update({}, {$unset: {A:1}});
```

// TODO
https://www.baeldung.com/mongodb-check-field-exists
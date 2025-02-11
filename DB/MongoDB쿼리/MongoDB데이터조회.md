# "MongoDB 데이터 조회 Methods" - Qeury and Projection Operators

> https://www.mongodb.com/docs/manual/reference/method

## [find()](https://www.mongodb.com/docs/manual/reference/method/db.collection.find/)

> db.collection.find(query, projection, options);

```javascript
db.users.find( { "name": "ssookie_" } ).pretty();
```

### projection
* 쿼리의 결과값에서 보여질 field를 지정한다.

```javascript
db.articles.find( { } , { "_id": false, "title": 1, "content": true } )
```

* $elemMatch 연산자를 projection 연산자로 사용하여 // TODO 

## [count()](https://www.mongodb.com/docs/manual/reference/method/db.collection.count/)

> db.collection.count(query, options);

```javascript
db.getCollection('item').find({}).count();
```

## Query 연산자

### [$where](https://www.mongodb.com/docs/manual/reference/operator/query/where/)

* $where 연산자를 통하여 javascript expression 을 사용 할 수 있다.

```javascript
// gtin field 가 비어있는 Document 갯수 조회
db.getCollection('item').find({ $where: "this.gtin.length == 0" }).count();
```

### [$in](https://www.mongodb.com/docs/manual/reference/operator/query/in/)

* 주어진 배열 안에 속하는 값을 찾는다.

```javascript
// writer 값이 배열 ["Alpha", "Bravo"] 안에 속하는 값인 Document 조회
db.articles.find( { "writer": { $in: [ "Alpha", "Bravo" ] } } ).pretty()
```

### $elemMatch 

* Embedded Documents 배열을 쿼리할때 사용한다.

```javascript
// comments 중 "ssookie_" 가 작성한 덧글이 있는 Document 조회
db.articles.find({ 
    "comments": { $elemMatch: { "name": "ssookie_" } } 
});
```

* 참고로, Embedded Document 배열이 아니라 아래 Document의 "name" 처럼 한개의 Embedded Document 일 때에는, 배열의 원소에 직접 접근한다.

```
  {
    "username": "ssookie_",
    "name": { "first": "ssookie", "last": "Kim"},
    "language": ["korean", "english"]
  }
```

```javascript
db.users.find({ "name.first": "ssookie"});
```

* Document의 배열이아니라 그냥 배열일 때에는, 아래와 같이 쿼리한다.

```javascript
db.users.find({ "language": "korean"});
```

### [$exists](https://www.mongodb.com/docs/manual/reference/operator/query/exists)

* MongoDB 컬럼이 있는/없는 경우의 데이터를 불러온다.

```javascript
db.collection.find({'name': { $exists: true }});
db.collection.find({'name': { $exists: false }});
```

## Reference

* https://velopert.com/479
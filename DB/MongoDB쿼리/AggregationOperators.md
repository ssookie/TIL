# "MongoDB Aggregation Pipeline Operators"

> https://www.mongodb.com/docs/manual/reference/operator/aggregation/

## [$strLenCP](https://www.mongodb.com/docs/manual/reference/operator/aggregation/strLenCP/)

* 지정된 문자열의 UTF-8 기준 문자길이를 반환한다.

```javascript
// item Collection 에서 _id의 문자길이 조회
db.getCollection('item').aggregate( [
   {
      $project: {
         _id: 1,
         idLength: { $strLenCP: "$_id" }
      }
   }
] );

// 결과
/* 1 */
{
    "_id" : "030900010533",
    "idLength" : 12
}

/* 2 */
{
    "_id" : "4894191161123",
    "idLength" : 13
}
```

* 

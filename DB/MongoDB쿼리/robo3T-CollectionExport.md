# "Robo3T Collection Export"

* MongoDB 클라이언트로 Robo3T와 MongoDB Compass를 사용하고 있는데, 무료 버전에서는 쿼리 조회 후 Export 하는 기능이 없어서 직접 print 하기로 함.
    * Robo 3T Export 기능: https://studio3t.com/knowledge-base/articles/mongodb-export-csv-json-sql-bson/
* 참고) Json to Csv Converter: https://data.page/json/csv

```javascript
// Robo 3T 에서 실행, to json.
records = [];
var cursor = db.getCollection('foo').find({}, {});
while(cursor.hasNext()) {
    records.push(cursor.next())
}
print(tojson(records));
```

```javascript
// to csv.
records = [];
var cursor = db.getCollection('foo').find({}, {});
while (cursor.hasNext()) {
    jsonObject = cursor.next();
    print(jsonObject._id.valueOf() + "\t" + jsonObject.name);
}
```
## 1. GET /api/v1/programs/regions

### request

```
Parameters = {
  region=[
  강원도 평창군 진부면
]}
Headers = [Accept: "application/json;charset=UTF-8"]
```

### response

```
Status = 200
Headers = [
  Content-Type : "application/json;charset=UTF-8"
]
Content type = application/json;charset=UTF-8
Body = {
  "region": "강원도 평창군 진부면",
  "programs": [
    {
      "prgm_name": "오대산국립공원 힐링캠프",
      "theme": "숲 치유"
    },
    {
      "prgm_name": "소금강 지역문화 체험",
      "theme": "자연생태"
    },
    {
      "prgm_name": "(1박2일)자연으로 떠나는 행복여행",
      "theme": "문화생태체험,자연생태체험"
    }
  ]
}
```

---

## 2. POST /api/v1/programs

### request

```
Headers = [
  Content-Type : "application/json;charset=UTF-8",
  Accept : "application/json;charset=UTF-8"
]
Body = {
  "programName": "오대산국립공원 해피700!! 문화·생태 여행 두번째",
  "themes": "문화생태체험",
  "regions": "강원도",
  "programDescription": "월정사, 전나무 숲길, 성보박물관 2관",
  "programDetailDescription": "★ 월정사 역사·문화 해설\n ★ 전나무 숲 자연해설\n ★ 성보박물관 탐방"
}
```
### respone 

```
Status = 200
Headers = [
  Content-Type : "application/json;charset=UTF-8"
]
Content type = application/json;charset=UTF-8
Body = {
  "code": "prg-111",
  "name": "오대산국립공원 해피700!! 문화·생태 여행 두번째",
  "description": "월정사, 전나무 숲길, 성보박물관 2관",
  "detailDescription": "★ 월정사 역사·문화 해설\n ★ 전나무 숲 자연해설\n ★ 성보박물관 탐방",
  "regions": [
    {
      "code": "reg-77",
      "name": "강원도"
    }
  ],
  "themes": [
    {
      "name": "문화생태체험"
    }
  ],
  "fullTheme": "문화생태체험"
}
```

---

## 3. PUT /api/v1/programs/{programCode}

### request

ex) /api/v1/programs/prg-1

```
Headers = [
  Content-Type : "application/json;charset=UTF-8",
  Accept : "application/json;charset=UTF-8"
]
Body = 
{
  "programName": "오대산국립공원 해피700!! 문화·생태 여행",
  "themes": "생태체험",
  "regions": "강원도",
  "programDescription": "월정사, 전나무 숲길, 성보박물관",
  "programDetailDescription": "★ 월정사 역사·문화 해설\n ★ 전나무 숲 자연해설\n ★ 성보박물관 탐방"
}
```

### response

```
Status = 200
Headers = [
  Content-Type : "application/json;charset=UTF-8"
]
Content type = application/json;charset=UTF-8
Body = {
  "code": "prg-1",
  "name": "오대산국립공원 해피700!! 문화·생태 여행",
  "description": "월정사, 전나무 숲길, 성보박물관",
  "detailDescription": "★ 월정사 역사·문화 해설\n ★ 전나무 숲 자연해설\n ★ 성보박물관 탐방",
  "regions": [
    {
      "code": "reg-1",
      "name": "강원도 속초"
    }
  ],
  "themes": [
    {
      "name": "문화생태체험"
    },
    {
      "name": "자연생태체험"
    }
  ],
  "fullTheme": "문화생태체험,자연생태체험"
}
```

---

## 4. GET /api/v1/programs/regions

### request

```
Parameters = {
  region=[
  강원도 평창군 진부면
]}
Headers = [Accept: "application/json;charset=UTF-8"]

```

### respoonse

```
Status = 200
Error message = null
Headers = [
  Content-Type : "application/json;charset=UTF-8"
]
Content type = application/json;charset=UTF-8
Body = {
  "region": "강원도 평창군 진부면",
  "programs": [
    {
      "prgm_name": "오대산국립공원 힐링캠프",
      "theme": "숲 치유"
    },
    {
      "prgm_name": "소금강 지역문화 체험",
      "theme": "자연생태"
    },
    {
      "prgm_name": "(1박2일)자연으로 떠나는 행복여행",
      "theme": "문화생태체험,자연생태체험"
    }
  ]
}
```

---

## 5. GET /api/v1/programs/descriptions

### request

```
Parameters = {
  keyword=[
  세계문화유산
]}
Headers = [Accept: "application/json;charset=UTF-8"]
```

### response

```
Status = 200
Error message = null
Headers = [
  Content-Type : "application/json;charset=UTF-8"
]
Content type = application/json;charset=UTF-8
Body = {
  "keyword": "세계문화유산",
  "programs": [
    {
      "region": "경상북도 경주시",
      "count": 2
    }
  ]
}
```

---

## 6. GET /api/v1/programs/detail-descriptions

### request

```
Parameters = {
  keyword=[
  문화
]}
Headers = [Accept: "application/json;charset=UTF-8"]
```

### response

```
Status = 200
Headers = [
  Content-Type : "application/json;charset=UTF-8"
]
Content type = application/json;charset=UTF-8
Body = {
  "keyword": "문화",
  "count": 59
}
```

---

## 7. GET /api/v1/programs/recommends

### request

```
Parameters = {
  region=[
  경상남도
  통영
], keyword=[생태체]}
Headers = [Accept: "application/json;charset=UTF-8"]
```

### response

```
Status = 200
Headers = [
  Content-Type : "application/json;charset=UTF-8"
]
Content type = application/json;charset=UTF-8
Body = {
"program": "prg-26"
}
```

---

## 8. POST /api/v1/files/csv
   
### request

```
Headers = [Content-Type : "multipart/form-data"]
body = csvfile
```

### response

```
Status = 200
```


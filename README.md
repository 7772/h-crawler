# h-crawler

## 소개

3가지 웹사이트를 크롤링하여 특정 요구사항을 수행한 결과를 반환하는 API 프로그램.

## API

- cURL: `$ curl localhost:8080`
- Method: GET
- URL: `/`
- Request: 없음
- Response:
    ```
    // Success case
    {
        "Status": 200,
        "Merge": "AaB2C4Ddefg"
    }
  
    // Failure case
    {
        "Status": 422,
        "Error": "ERROR_MESSAGE",
    }
    ```
- Timeout: 5000ms
- Cache: 1m

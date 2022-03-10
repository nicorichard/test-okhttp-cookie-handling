# Test - OKHTTP Cookie Handling

Unit test playground to test OKHTTP behaviours

## Results

### fire_two_cookie_setting_requests_without_cookiejar

```
--> GET https://httpbin.org/cookies/set/COOKIE/CHOCOLATE h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
User-Agent: okhttp/4.9.3
--> END GET
<-- 302 https://httpbin.org/cookies/set/COOKIE/CHOCOLATE (339ms)
date: Thu, 10 Mar 2022 21:21:34 GMT
content-type: text/html; charset=utf-8
content-length: 223
server: gunicorn/19.9.0
location: /cookies
set-cookie: COOKIE=CHOCOLATE; Path=/
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP
--> GET https://httpbin.org/cookies h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
User-Agent: okhttp/4.9.3
--> END GET
<-- 200 https://httpbin.org/cookies (160ms)
date: Thu, 10 Mar 2022 21:21:34 GMT
content-type: application/json
content-length: 20
server: gunicorn/19.9.0
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP

RUN THE REQUEST AGAIN

--> GET https://httpbin.org/cookies/set/COOKIE/CHOCOLATE h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
User-Agent: okhttp/4.9.3
--> END GET
<-- 302 https://httpbin.org/cookies/set/COOKIE/CHOCOLATE (160ms)
date: Thu, 10 Mar 2022 21:21:35 GMT
content-type: text/html; charset=utf-8
content-length: 223
server: gunicorn/19.9.0
location: /cookies
set-cookie: COOKIE=CHOCOLATE; Path=/
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP
--> GET https://httpbin.org/cookies h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
User-Agent: okhttp/4.9.3
--> END GET
<-- 200 https://httpbin.org/cookies (205ms)
date: Thu, 10 Mar 2022 21:21:35 GMT
content-type: application/json
content-length: 20
server: gunicorn/19.9.0
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP
```

### fire_two_cookie_setting_requests_with_a_cookiejar

```
--> GET https://httpbin.org/cookies/set/COOKIE/CHOCOLATE h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
User-Agent: okhttp/4.9.3
--> END GET
<-- 302 https://httpbin.org/cookies/set/COOKIE/CHOCOLATE (372ms)
date: Thu, 10 Mar 2022 21:21:32 GMT
content-type: text/html; charset=utf-8
content-length: 223
server: gunicorn/19.9.0
location: /cookies
set-cookie: COOKIE=CHOCOLATE; Path=/
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP
--> GET https://httpbin.org/cookies h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
Cookie: COOKIE=CHOCOLATE
User-Agent: okhttp/4.9.3
--> END GET
<-- 200 https://httpbin.org/cookies (160ms)
date: Thu, 10 Mar 2022 21:21:33 GMT
content-type: application/json
content-length: 49
server: gunicorn/19.9.0
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP

RUN THE REQUEST AGAIN

--> GET https://httpbin.org/cookies/set/COOKIE/CHOCOLATE h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
Cookie: COOKIE=CHOCOLATE
User-Agent: okhttp/4.9.3
--> END GET
<-- 302 https://httpbin.org/cookies/set/COOKIE/CHOCOLATE (354ms)
date: Thu, 10 Mar 2022 21:21:33 GMT
content-type: text/html; charset=utf-8
content-length: 223
server: gunicorn/19.9.0
location: /cookies
set-cookie: COOKIE=CHOCOLATE; Path=/
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP
--> GET https://httpbin.org/cookies h2
Host: httpbin.org
Connection: Keep-Alive
Accept-Encoding: gzip
Cookie: COOKIE=CHOCOLATE; COOKIE=CHOCOLATE
User-Agent: okhttp/4.9.3
--> END GET
<-- 200 https://httpbin.org/cookies (174ms)
date: Thu, 10 Mar 2022 21:21:33 GMT
content-type: application/json
content-length: 49
server: gunicorn/19.9.0
access-control-allow-origin: *
access-control-allow-credentials: true
<-- END HTTP
```
A quick insight : You can install the Spring Assistant plugin in Intellij IDE to make Spring boot projects just like you make them in Spring tool suite.


**JWT**
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA or ECDSA.


**JWT Token Structure**
1. Header
2. Payload (data)
3. Signature

This JWT has an advantage over cookie-based and session-based authentication where the token can exist at either on the client-side or the server-side.In JWT standard the token can move from client to server and vice-versa. You need to generate a token first and after that while accessing a secure endpoint you will have the send the token in  the header where Authorization will serve as the key. The server will validate your token first and after successful validation you will be getting a response from the server or else an exception will be thrown.


**Dependencies**
1.Spring Security
2.Spring Web
3.io.jsonwebtoken 
4.jax-b api

**Generating a Token**
To generate make a seperate class say JwtUtil and implement all the methods such generateToken and extractUsername and many more. You can get the list of all the methods to be implemented from github.

**Validating a token**
After generating the token you need to validate it. Create a seperate class say JwtValidate extending OncePerRequestFilter which will acts as a addBeforeFilter to check for the validation of the token. Configure the JwtValidate class in your Security config file so that the filer is enabled.

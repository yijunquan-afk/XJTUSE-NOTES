openssl

```
Enter pass phrase for ca.key : yjqlovemhy
```

```bash
root@hspEdu01 /u/l/ssl.ca-0.1# ./new-root-ca.sh 
No Root CA key round. Generating one
Generating RSA private key, 1024 bit long modulus
............++++++
...................++++++
e is 65537 (0x10001)
Enter pass phrase for ca.key:
Verifying - Enter pass phrase for ca.key:

Self-sign the root CA...
Enter pass phrase for ca.key:
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [MY]:CA
State or Province Name (full name) [Perak]:ShanXi
Locality Name (eg, city) [Sitiawan]:XiAn
Organization Name (eg, company) [My Directory Sdn Bhd]:ShiJi
Organizational Unit Name (eg, section) [Certification Services Division]:ShiJiTechj
Common Name (eg, MD Root CA) []:MD Root CA
Email Address []:2696974822@qq.com
root@hspEdu01 /u/l/ssl.ca-0.1# 

```

```bash
root@hspEdu01 /u/l/ssl.ca-0.1# ./new-server-cert.sh 
Usage: ./new-server-cert.sh <www.domain.com>
root@hspEdu01 /u/l/ssl.ca-0.1# ./new-server-cert.sh www.yjqlovemhy.com
No www.yjqlovemhy.com.key round. Generating one
Generating RSA private key, 1024 bit long modulus
.......................++++++
..........................................++++++
e is 65537 (0x10001)

Fill in certificate data
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [MY]:CA
State or Province Name (full name) [Perak]:XiAn
Locality Name (eg, city) [Sitiawan]:ShanXi
Organization Name (eg, company) [My Directory Sdn Bhd]:SDiji
Organizational Unit Name (eg, section) [Secure Web Server]:Siji
Common Name (eg, www.domain.com) []:www.yjqlovemhy.com
Email Address []:2696974822@qq.com

You may now run ./sign-server-cert.sh to get it signed
```





```bash
root@hspEdu01 /u/l/ssl.ca-0.1# ./sign-server-cert.sh  www.yjqlovemhy.com
CA signing: www.yjqlovemhy.com.csr -> www.yjqlovemhy.com.crt:
Using configuration from ca.config
Enter pass phrase for ./ca.key:
Check that the request matches the signature
Signature ok
The Subject's Distinguished Name is as follows
countryName           :PRINTABLE:'CA'
stateOrProvinceName   :PRINTABLE:'XiAn'
localityName          :PRINTABLE:'ShanXi'
organizationName      :PRINTABLE:'SDiji'
organizationalUnitName:PRINTABLE:'Siji'
commonName            :PRINTABLE:'www.yjqlovemhy.com'
emailAddress          :IA5STRING:'2696974822@qq.com'
Certificate is to be certified until Jun  7 11:31:49 2023 GMT (365 days)
Sign the certificate? [y/n]:y


1 out of 1 certificate requests certified, commit? [y/n]y
Write out database with 1 new entries
Data Base Updated
CA verifying: www.yjqlovemhy.com.crt <-> CA cert
www.yjqlovemhy.com.crt: C = CA, ST = XiAn, L = ShanXi, O = SDiji, OU = Siji, CN = www.yjqlovemhy.com, emailAddress = 2696974822@qq.com
error 7 at 0 depth lookup:certificate signature failure
139864998762384:error:0D0C50A1:asn1 encoding routines:ASN1_item_verify:unknown message digest algorithm:a_verify.c:206:
root@hspEdu01 /u/l/ssl.ca-0.1# 

```



```bash
root@hspEdu01 /u/l/ssl.ca-0.1# ./new-user-cert.sh 
Usage: ./new-user-cert.sh user@email.address.com
root@hspEdu01 /u/l/ssl.ca-0.1# ./new-user-cert.sh  yjq@www.yjqlovemhy.com
No yjq@www.yjqlovemhy.com.key round. Generating one
Generating RSA private key, 1024 bit long modulus
....++++++
...++++++
e is 65537 (0x10001)

Fill in certificate data
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Common Name (eg, John Doe) []:Junquan Yi
Email Address []:13019@163.com

You may now run ./sign-user-cert.sh to get it signed

```



```bash
root@hspEdu01 /u/l/ssl.ca-0.1# ./sign-user-cert.sh 
Usage: ./sign-user-cert.sh user@email.address.com
root@hspEdu01 /u/l/ssl.ca-0.1# ./sign-user-cert.sh  yjq@www.yjqlovemhy.com
CA signing: yjq@www.yjqlovemhy.com.csr -> yjq@www.yjqlovemhy.com.crt:
Using configuration from ca.config
Enter pass phrase for ./ca.key:
Check that the request matches the signature
Signature ok
The Subject's Distinguished Name is as follows
commonName            :PRINTABLE:'Junquan Yi'
emailAddress          :IA5STRING:'13019@163.com'
Certificate is to be certified until Jun  7 11:39:01 2023 GMT (365 days)
Sign the certificate? [y/n]:y


1 out of 1 certificate requests certified, commit? [y/n]y
Write out database with 1 new entries
Data Base Updated
CA verifying: yjq@www.yjqlovemhy.com.crt <-> CA cert
yjq@www.yjqlovemhy.com.crt: CN = Junquan Yi, emailAddress = 13019@163.com
error 7 at 0 depth lookup:certificate signature failure
139984428902288:error:0D0C50A1:asn1 encoding routines:ASN1_item_verify:unknown message digest algorithm:a_verify.c:206:

```



密码：yjqlovemhy

```bash
root@hspEdu01 /u/l/ssl.ca-0.1# ./p12.sh 
Usage: ./p12.sh user@email.address.com
root@hspEdu01 /u/l/ssl.ca-0.1# ./p12.sh yjq@www.yjqlovemhy.com
Enter Export Password:
Verifying - Enter Export Password:

The certificate for yjq@www.yjqlovemhy.com has been collected into a pkcs12 file.
You can download to your browser and import it.
```

ca.crt

```bash
root@hspEdu01 /u/l/ssl.ca-0.1# cat ca.crt 
-----BEGIN CERTIFICATE-----
MIICxDCCAi2gAwIBAgIJAJKx8IIfr5DNMA0GCSqGSIb3DQEBCwUAMIGJMQswCQYD
VQQGEwJDQTEPMA0GA1UECBMGU2hhblhpMQ0wCwYDVQQHEwRYaUFuMQ4wDAYDVQQK
EwVTaGlKaTETMBEGA1UECxMKU2hpSmlUZWNoajETMBEGA1UEAxMKTUQgUm9vdCBD
QTEgMB4GCSqGSIb3DQEJARYRMjY5Njk3NDgyMkBxcS5jb20wHhcNMjIwNjA3MTEy
NTQxWhcNMzIwNjA0MTEyNTQxWjCBiTELMAkGA1UEBhMCQ0ExDzANBgNVBAgTBlNo
YW5YaTENMAsGA1UEBxMEWGlBbjEOMAwGA1UEChMFU2hpSmkxEzARBgNVBAsTClNo
aUppVGVjaGoxEzARBgNVBAMTCk1EIFJvb3QgQ0ExIDAeBgkqhkiG9w0BCQEWETI2
OTY5NzQ4MjJAcXEuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYjMWG
5xyUBzb4zp8J/1ehx4Qhq2EfONfD5KKBH4rMFbCIX+CXfWQhWpDfyru7mCYIZjyt
sGaibZrXQHt4wmCnO+GYUWJCs6hkKz7ojC90LYFtn4Koj1LRapyINL6jGU+3gE+T
6fCKM0ZvYb2jccFRucQuU86Ayd8J9bySdJZcBQIDAQABozIwMDAPBgNVHRMBAf8E
BTADAQH/MB0GA1UdDgQWBBSBJhK9myOT3SWWGpezydsP0Rmq9zANBgkqhkiG9w0B
AQsFAAOBgQCEB12xQKt//Cwt1WBfK/FFt/Kr9NWIlgLl9AOKijHDtmd0OOT8pRmk
PAkheOWVj5F3kkCsgfboNoxnGbXAO6D5bcYdv83eJzbR1DYuEuwvn4HAAUh8a4dm
KHRwX1U+bS3T8VLLPNzOOSob1PWrpeOlhctJ5tOWZuSkvzIkhswSYw==
-----END CERTIFICATE-----
```

ca.db.certs/01.pem 

```bash
root@hspEdu01 /u/l/ssl.ca-0.1# cd ca.db.certs/
root@hspEdu01 /u/l/s/ca.db.certs# ls
01.pem  02.pem
root@hspEdu01 /u/l/s/ca.db.certs# cat 01.pem 
Certificate:
    Data:
        Version: 3 (0x2)
        Serial Number: 1 (0x1)
    Signature Algorithm: md5WithRSAEncryption
        Issuer: C=CA, ST=ShanXi, L=XiAn, O=ShiJi, OU=ShiJiTechj, CN=MD Root CA/emailAddress=2696974822@qq.com
        Validity
            Not Before: Jun  7 11:31:49 2022 GMT
            Not After : Jun  7 11:31:49 2023 GMT
        Subject: C=CA, ST=XiAn, L=ShanXi, O=SDiji, OU=Siji, CN=www.yjqlovemhy.com/emailAddress=2696974822@qq.com
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
                Public-Key: (1024 bit)
                Modulus:
                    00:b0:99:ca:f0:0e:e4:ad:a4:d3:98:c6:b4:f0:e9:
                    53:35:31:05:32:a8:25:70:81:4c:43:58:4c:53:34:
                    a0:3c:27:b8:8d:01:74:18:46:e4:a2:58:7c:4e:f3:
                    85:ef:8d:29:e6:8c:06:4d:fd:3b:e0:e0:e4:74:58:
                    4a:76:28:7f:14:99:0f:58:90:f6:7d:2a:58:49:c3:
                    69:c0:5a:c2:2b:bb:23:90:ec:ff:02:79:32:d1:dd:
                    21:2e:db:ed:30:5c:d1:4c:ee:88:25:b1:09:12:2a:
                    08:3d:e4:9f:57:ea:f7:da:0d:59:4d:ba:a2:0a:58:
                    e9:af:a8:fe:8c:c3:64:3e:77
                Exponent: 65537 (0x10001)
        X509v3 extensions:
            X509v3 Authority Key Identifier: 
                keyid:81:26:12:BD:9B:23:93:DD:25:96:1A:97:B3:C9:DB:0F:D1:19:AA:F7

            X509v3 Extended Key Usage: 
                TLS Web Server Authentication, TLS Web Client Authentication, Microsoft Server Gated Crypto, Netscape Server Gated Crypto
            X509v3 Basic Constraints: critical
                CA:FALSE
    Signature Algorithm: md5WithRSAEncryption
         cd:c6:ef:a0:d2:80:36:62:3d:94:d8:af:ec:8c:3e:57:1c:65:
         3a:81:de:7d:ed:40:3f:87:05:3a:41:ed:33:a8:d4:a4:e9:74:
         d2:46:ef:57:80:d6:db:fe:41:fb:76:e2:73:29:a9:24:f5:f0:
         57:84:b3:af:ce:e7:88:53:f2:c5:70:d9:98:d0:fd:3d:ba:0e:
         24:f2:0f:9f:9f:48:b0:e7:72:e1:bb:c4:b5:79:3b:37:c5:5e:
         2b:f9:2d:6e:13:05:0e:5a:e2:41:6a:1a:2c:83:28:6a:d6:f0:
         7a:96:c1:cf:1f:fb:49:98:12:b6:76:95:1a:b7:16:53:e5:90:
         73:fc
-----BEGIN CERTIFICATE-----
MIIC8zCCAlygAwIBAgIBATANBgkqhkiG9w0BAQQFADCBiTELMAkGA1UEBhMCQ0Ex
DzANBgNVBAgTBlNoYW5YaTENMAsGA1UEBxMEWGlBbjEOMAwGA1UEChMFU2hpSmkx
EzARBgNVBAsTClNoaUppVGVjaGoxEzARBgNVBAMTCk1EIFJvb3QgQ0ExIDAeBgkq
hkiG9w0BCQEWETI2OTY5NzQ4MjJAcXEuY29tMB4XDTIyMDYwNzExMzE0OVoXDTIz
MDYwNzExMzE0OVowgYsxCzAJBgNVBAYTAkNBMQ0wCwYDVQQIEwRYaUFuMQ8wDQYD
VQQHEwZTaGFuWGkxDjAMBgNVBAoTBVNEaWppMQ0wCwYDVQQLEwRTaWppMRswGQYD
VQQDExJ3d3cueWpxbG92ZW1oeS5jb20xIDAeBgkqhkiG9w0BCQEWETI2OTY5NzQ4
MjJAcXEuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwmcrwDuStpNOY
xrTw6VM1MQUyqCVwgUxDWExTNKA8J7iNAXQYRuSiWHxO84XvjSnmjAZN/Tvg4OR0
WEp2KH8UmQ9YkPZ9KlhJw2nAWsIruyOQ7P8CeTLR3SEu2+0wXNFM7oglsQkSKgg9
5J9X6vfaDVlNuqIKWOmvqP6Mw2Q+dwIDAQABo2cwZTAfBgNVHSMEGDAWgBSBJhK9
myOT3SWWGpezydsP0Rmq9zA0BgNVHSUELTArBggrBgEFBQcDAQYIKwYBBQUHAwIG
CisGAQQBgjcKAwMGCWCGSAGG+EIEATAMBgNVHRMBAf8EAjAAMA0GCSqGSIb3DQEB
BAUAA4GBAM3G76DSgDZiPZTYr+yMPlccZTqB3n3tQD+HBTpB7TOo1KTpdNJG71eA
1tv+Qft24nMpqST18FeEs6/O54hT8sVw2ZjQ/T26DiTyD5+fSLDncuG7xLV5OzfF
Xiv5LW4TBQ5a4kFqGiyDKGrW8HqWwc8f+0mYErZ2lRq3FlPlkHP8
-----END CERTIFICATE-----
root@hspEdu01 /u/l/s/ca.db.certs# 

```

ca.db.certs/02.pem 

```bash
root@hspEdu01 /u/l/s/ca.db.certs# cat 02.pem 
Certificate:
    Data:
        Version: 3 (0x2)
        Serial Number: 2 (0x2)
    Signature Algorithm: md5WithRSAEncryption
        Issuer: C=CA, ST=ShanXi, L=XiAn, O=ShiJi, OU=ShiJiTechj, CN=MD Root CA/emailAddress=2696974822@qq.com
        Validity
            Not Before: Jun  7 11:39:01 2022 GMT
            Not After : Jun  7 11:39:01 2023 GMT
        Subject: CN=Junquan Yi/emailAddress=13019@163.com
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
                Public-Key: (1024 bit)
                Modulus:
                    00:b5:e6:bb:e8:45:d5:8d:57:fc:65:98:f1:22:36:
                    5e:79:11:6d:05:d0:c3:91:cd:18:72:f8:41:dc:c2:
                    37:c9:38:4e:93:f9:15:4b:54:21:f9:2b:d2:a1:68:
                    dc:7f:28:d4:6c:d0:8f:30:e7:f9:d8:92:0f:46:3d:
                    53:0e:31:ea:32:13:10:cb:84:84:53:a8:a0:4a:5c:
                    26:f1:11:e2:62:3b:1e:31:29:46:c7:ac:45:d6:b8:
                    ad:79:67:e0:c1:b0:59:c8:6c:ec:64:43:29:aa:48:
                    e2:90:38:55:0d:f8:ab:28:21:8d:63:55:29:29:40:
                    3b:85:e3:88:bb:b2:ec:a4:a7
                Exponent: 65537 (0x10001)
        X509v3 extensions:
            X509v3 Subject Alternative Name: 
                email:13019@163.com
            X509v3 Basic Constraints: critical
                CA:FALSE
            X509v3 Authority Key Identifier: 
                keyid:81:26:12:BD:9B:23:93:DD:25:96:1A:97:B3:C9:DB:0F:D1:19:AA:F7

            X509v3 Extended Key Usage: 
                TLS Web Client Authentication, E-mail Protection
    Signature Algorithm: md5WithRSAEncryption
         8e:f8:d5:b0:2d:25:66:b0:25:16:c1:3e:a5:59:7c:6b:db:41:
         56:fb:f6:ca:cb:8f:62:b5:dc:25:a9:3f:86:cb:2d:0c:b8:1e:
         ac:d3:53:07:69:3e:f6:c9:8e:1b:f3:64:53:ec:23:50:7f:64:
         2f:63:30:3e:67:72:5f:35:c9:d1:42:ff:c4:95:4a:fd:db:62:
         d7:6c:fc:52:a2:14:a8:6f:b1:4d:7f:9d:38:5d:a1:87:fe:13:
         96:1a:06:83:61:03:3e:99:cf:ad:50:0b:14:eb:52:a9:99:3c:
         98:83:64:e0:44:d0:24:cb:dd:78:63:c0:63:1e:7e:f8:0e:9d:
         5c:a9
-----BEGIN CERTIFICATE-----
MIICnTCCAgagAwIBAgIBAjANBgkqhkiG9w0BAQQFADCBiTELMAkGA1UEBhMCQ0Ex
DzANBgNVBAgTBlNoYW5YaTENMAsGA1UEBxMEWGlBbjEOMAwGA1UEChMFU2hpSmkx
EzARBgNVBAsTClNoaUppVGVjaGoxEzARBgNVBAMTCk1EIFJvb3QgQ0ExIDAeBgkq
hkiG9w0BCQEWETI2OTY5NzQ4MjJAcXEuY29tMB4XDTIyMDYwNzExMzkwMVoXDTIz
MDYwNzExMzkwMVowMzETMBEGA1UEAxMKSnVucXVhbiBZaTEcMBoGCSqGSIb3DQEJ
ARYNMTMwMTlAMTYzLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAtea7
6EXVjVf8ZZjxIjZeeRFtBdDDkc0YcvhB3MI3yThOk/kVS1Qh+SvSoWjcfyjUbNCP
MOf52JIPRj1TDjHqMhMQy4SEU6igSlwm8RHiYjseMSlGx6xF1riteWfgwbBZyGzs
ZEMpqkjikDhVDfirKCGNY1UpKUA7heOIu7LspKcCAwEAAaNqMGgwGAYDVR0RBBEw
D4ENMTMwMTlAMTYzLmNvbTAMBgNVHRMBAf8EAjAAMB8GA1UdIwQYMBaAFIEmEr2b
I5PdJZYal7PJ2w/RGar3MB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDAN
BgkqhkiG9w0BAQQFAAOBgQCO+NWwLSVmsCUWwT6lWXxr20FW+/bKy49itdwlqT+G
yy0MuB6s01MHaT72yY4b82RT7CNQf2QvYzA+Z3JfNcnRQv/ElUr922LXbPxSohSo
b7FNf504XaGH/hOWGgaDYQM+mc+tUAsU61KpmTyYg2TgRNAky914Y8BjHn74Dp1c
qQ==
-----END CERTIFICATE-----
root@hspEdu01 /u/l/s/ca.db.certs# 

```



 www.yjqlovemhy.com.crt 

```
root@hspEdu01 /u/l/ssl.ca-0.1# cat www.yjqlovemhy.com.crt 
Certificate:
    Data:
        Version: 3 (0x2)
        Serial Number: 1 (0x1)
    Signature Algorithm: md5WithRSAEncryption
        Issuer: C=CA, ST=ShanXi, L=XiAn, O=ShiJi, OU=ShiJiTechj, CN=MD Root CA/emailAddress=2696974822@qq.com
        Validity
            Not Before: Jun  7 11:31:49 2022 GMT
            Not After : Jun  7 11:31:49 2023 GMT
        Subject: C=CA, ST=XiAn, L=ShanXi, O=SDiji, OU=Siji, CN=www.yjqlovemhy.com/emailAddress=2696974822@qq.com
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
                Public-Key: (1024 bit)
                Modulus:
                    00:b0:99:ca:f0:0e:e4:ad:a4:d3:98:c6:b4:f0:e9:
                    53:35:31:05:32:a8:25:70:81:4c:43:58:4c:53:34:
                    a0:3c:27:b8:8d:01:74:18:46:e4:a2:58:7c:4e:f3:
                    85:ef:8d:29:e6:8c:06:4d:fd:3b:e0:e0:e4:74:58:
                    4a:76:28:7f:14:99:0f:58:90:f6:7d:2a:58:49:c3:
                    69:c0:5a:c2:2b:bb:23:90:ec:ff:02:79:32:d1:dd:
                    21:2e:db:ed:30:5c:d1:4c:ee:88:25:b1:09:12:2a:
                    08:3d:e4:9f:57:ea:f7:da:0d:59:4d:ba:a2:0a:58:
                    e9:af:a8:fe:8c:c3:64:3e:77
                Exponent: 65537 (0x10001)
        X509v3 extensions:
            X509v3 Authority Key Identifier: 
                keyid:81:26:12:BD:9B:23:93:DD:25:96:1A:97:B3:C9:DB:0F:D1:19:AA:F7

            X509v3 Extended Key Usage: 
                TLS Web Server Authentication, TLS Web Client Authentication, Microsoft Server Gated Crypto, Netscape Server Gated Crypto
            X509v3 Basic Constraints: critical
                CA:FALSE
    Signature Algorithm: md5WithRSAEncryption
         cd:c6:ef:a0:d2:80:36:62:3d:94:d8:af:ec:8c:3e:57:1c:65:
         3a:81:de:7d:ed:40:3f:87:05:3a:41:ed:33:a8:d4:a4:e9:74:
         d2:46:ef:57:80:d6:db:fe:41:fb:76:e2:73:29:a9:24:f5:f0:
         57:84:b3:af:ce:e7:88:53:f2:c5:70:d9:98:d0:fd:3d:ba:0e:
         24:f2:0f:9f:9f:48:b0:e7:72:e1:bb:c4:b5:79:3b:37:c5:5e:
         2b:f9:2d:6e:13:05:0e:5a:e2:41:6a:1a:2c:83:28:6a:d6:f0:
         7a:96:c1:cf:1f:fb:49:98:12:b6:76:95:1a:b7:16:53:e5:90:
         73:fc
-----BEGIN CERTIFICATE-----
MIIC8zCCAlygAwIBAgIBATANBgkqhkiG9w0BAQQFADCBiTELMAkGA1UEBhMCQ0Ex
DzANBgNVBAgTBlNoYW5YaTENMAsGA1UEBxMEWGlBbjEOMAwGA1UEChMFU2hpSmkx
EzARBgNVBAsTClNoaUppVGVjaGoxEzARBgNVBAMTCk1EIFJvb3QgQ0ExIDAeBgkq
hkiG9w0BCQEWETI2OTY5NzQ4MjJAcXEuY29tMB4XDTIyMDYwNzExMzE0OVoXDTIz
MDYwNzExMzE0OVowgYsxCzAJBgNVBAYTAkNBMQ0wCwYDVQQIEwRYaUFuMQ8wDQYD
VQQHEwZTaGFuWGkxDjAMBgNVBAoTBVNEaWppMQ0wCwYDVQQLEwRTaWppMRswGQYD
VQQDExJ3d3cueWpxbG92ZW1oeS5jb20xIDAeBgkqhkiG9w0BCQEWETI2OTY5NzQ4
MjJAcXEuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwmcrwDuStpNOY
xrTw6VM1MQUyqCVwgUxDWExTNKA8J7iNAXQYRuSiWHxO84XvjSnmjAZN/Tvg4OR0
WEp2KH8UmQ9YkPZ9KlhJw2nAWsIruyOQ7P8CeTLR3SEu2+0wXNFM7oglsQkSKgg9
5J9X6vfaDVlNuqIKWOmvqP6Mw2Q+dwIDAQABo2cwZTAfBgNVHSMEGDAWgBSBJhK9
myOT3SWWGpezydsP0Rmq9zA0BgNVHSUELTArBggrBgEFBQcDAQYIKwYBBQUHAwIG
CisGAQQBgjcKAwMGCWCGSAGG+EIEATAMBgNVHRMBAf8EAjAAMA0GCSqGSIb3DQEB
BAUAA4GBAM3G76DSgDZiPZTYr+yMPlccZTqB3n3tQD+HBTpB7TOo1KTpdNJG71eA
1tv+Qft24nMpqST18FeEs6/O54hT8sVw2ZjQ/T26DiTyD5+fSLDncuG7xLV5OzfF
Xiv5LW4TBQ5a4kFqGiyDKGrW8HqWwc8f+0mYErZ2lRq3FlPlkHP8
-----END CERTIFICATE-----
root@hspEdu01 /u/l/ssl.ca-0.1# 

```

www.yjqlovemhy.com.csr

```
root@hspEdu01 /u/l/ssl.ca-0.1# cat www.yjqlovemhy.com.csr
-----BEGIN CERTIFICATE REQUEST-----
MIIB/jCCAWcCAQAwgYsxCzAJBgNVBAYTAkNBMQ0wCwYDVQQIEwRYaUFuMQ8wDQYD
VQQHEwZTaGFuWGkxDjAMBgNVBAoTBVNEaWppMQ0wCwYDVQQLEwRTaWppMRswGQYD
VQQDExJ3d3cueWpxbG92ZW1oeS5jb20xIDAeBgkqhkiG9w0BCQEWETI2OTY5NzQ4
MjJAcXEuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwmcrwDuStpNOY
xrTw6VM1MQUyqCVwgUxDWExTNKA8J7iNAXQYRuSiWHxO84XvjSnmjAZN/Tvg4OR0
WEp2KH8UmQ9YkPZ9KlhJw2nAWsIruyOQ7P8CeTLR3SEu2+0wXNFM7oglsQkSKgg9
5J9X6vfaDVlNuqIKWOmvqP6Mw2Q+dwIDAQABoDIwMAYJKoZIhvcNAQkOMSMwITAR
BglghkgBhvhCAQEEBAMCBkAwDAYDVR0TAQH/BAIwADANBgkqhkiG9w0BAQsFAAOB
gQBQdNCRwaZskMgj6h3Z4W98m2yDQfpLpg400OIzHyHCVO8dk/Gb4x7YQZWZuKSS
O1h6k8DoJTprhfd7QFemHW/CRL6ZPZjKDuegbmPAyUYmqMizxT4CbEcgzM7LLr+i
oOIOcme120DcSAi/dxaFF6ajfHqhozNbPhfKdjw0m5m4mg==
-----END CERTIFICATE REQUEST-----
```

www.yjqlovemhy.com.key

```
root@hspEdu01 /u/l/ssl.ca-0.1# cat www.yjqlovemhy.com.key 
-----BEGIN RSA PRIVATE KEY-----
MIICXAIBAAKBgQCwmcrwDuStpNOYxrTw6VM1MQUyqCVwgUxDWExTNKA8J7iNAXQY
RuSiWHxO84XvjSnmjAZN/Tvg4OR0WEp2KH8UmQ9YkPZ9KlhJw2nAWsIruyOQ7P8C
eTLR3SEu2+0wXNFM7oglsQkSKgg95J9X6vfaDVlNuqIKWOmvqP6Mw2Q+dwIDAQAB
AoGAOpZq+wUV3H2oLTEO6jWeAzGGFe4urYpXsKfkel4lIo/S7ASlbUBnrWxJoS+t
KXHdiry9grYWXV5+rBJ2cYixz6f2PFbHsMcZUHyAU15IYBYS+x/4Oenoe4Sfuyy+
Bb6AlRdkhx9NNtLwZ9PrTF7j1mXh/kiK/qEsd5VwFRrDdWECQQDnqws+JKbo+Tjk
ReO8tEipuBk52jIiIpfWjyzXKDERHxg4laQgPh9kruCApmvfh5kTe5+huNtr89DJ
Yq1ihSlPAkEAwyYhIkLyjUSyLwgrebiowXVwLS6KeAz70eyCNpW6mO7BQDUSoPeG
X6B/Ofiu4tBvlSSJZt2/j5mGxzdWAfx+WQJAV42M8i9n5SRLG/pN10NLMtkgr3yG
9d+dArmZeLb31ECsJrZnEMhplOOCdRxwKEpWz8YozjXOtz2t6m4gmNs6gQJAIKHs
on0NcEAPh7vXAUi2t3r64iNJNl/9cBIbiCMri81I9YKqdc5OYwlEnR4PqI/wSMFO
VKw6Dzo+BmwjbruZKQJBAIPa5rJa5uo+4m7IMrI4XYgZ1t7JrMLapMmDcuxxPsWF
8vwu+AG2QWgbeoIVwEUbanRq7vBeVHSfk5eXPl7RAz0=
-----END RSA PRIVATE KEY-----

```

yjq@www.yjqlovemhy.com.key

```
root@hspEdu01 /u/l/ssl.ca-0.1# cat yjq@www.yjqlovemhy.com.key 
-----BEGIN RSA PRIVATE KEY-----
MIICXAIBAAKBgQC15rvoRdWNV/xlmPEiNl55EW0F0MORzRhy+EHcwjfJOE6T+RVL
VCH5K9KhaNx/KNRs0I8w5/nYkg9GPVMOMeoyExDLhIRTqKBKXCbxEeJiOx4xKUbH
rEXWuK15Z+DBsFnIbOxkQymqSOKQOFUN+KsoIY1jVSkpQDuF44i7suykpwIDAQAB
AoGAQbKCL5P5W0FqAW/Jt6eXW3a1v6H04JYIvI15jDgBWgWiK8OMs1ZBpvLy8iUj
bjHa9yokbW97R4MJTT8c3kWc5bjNmYmElrjkuID/5iujR0nZVufztJA4BX1JSdps
tu0UriE8Ryaafv7HLInfLMxDG12gtmfSfAjTi7KoNgfHfXECQQDm1VXyxrvH9EI2
g7emaiozAshTF3fnO5iDoEfJ5m3MVEooS6zCm4vfGqDqqUXTF5MG7G3GCgWacWtQ
s2QIl6bbAkEAybuuxUOBtjH52iRTXC7ANOtQ8Tz/kExDYmyo3t0tLIKoRqKSp8wS
2T4RVsJQVxGV6wTDXrPPGbaWUiiMbEHFJQJAQDhQHigOLDYXicUhfeoBbYmzo/Je
iHV7G/umnVQ/bd5xdz5+hPHzexGUfVS0uWLQo9d5PuSg5HPzkFMoXH+ciwJBAKtp
zbSSivpMdtgz+50doHaW6R1CzolHNA7C868DUuo6T1BNvb3UusNbaRJGZEvHhpK/
Zl7m/HgF3K8NA1zNef0CQBD5jtZDns4Vk24u+BrCacQD0NKsNzVWy/Trjzff3M/q
Tit0TuUjbxODQN/ytUXQUEhL1c5AC9SuMgii8QaLpf4=
-----END RSA PRIVATE KEY-----
root@hspEdu01 /u/l/ssl.ca-0.1# 
```



yjq@www.yjqlovemhy.com.p12

```c
root@hspEdu01 /u/l/ssl.ca-0.1# vim yjq@www.yjqlovemhy.com.p12 

0<82>
¥^B^A^C0<82>
k^F *<86>H<86>÷^M^A^G^A <82>
\^D<82>
X0<82>
T0<82>^F÷^F *<86>H<86>÷^M^A^G^F <82>^Fè0<82>^Fä^B^A^@0<82>^FÝ^F *<86>H<86>÷^M^A^G^A0^\^F
*<86>H<86>÷^M^A^L^A^F0^N^D^HÚ[»<X^Aè^@^B^B^H^@<80><82>^F°þB ^Y¤<8d>Ê}xIKùsO,*<8b>QdÄ^Y[|]^C¦|^C@´<86>1a^?j<9f>^Wä<93>)ã^_£^]ôÍon^Q¬!kìÁýµJW^Y®çø«<8f>^XÄ¥Ü:V<9b>µ78UK3y²! )nT^E<98>ÖleÍ]µ^V 
,^HÍÉl8^?Þ^Lg>^HÜ14É;·[¨øî¶6<9f>Á¬P^yÝxO^_Weûr<97>ç*<89>o[¶<83>^V-ô^^^\Ü
ÂæÔ/o^K<88>ãýàw4<8a>Z¹Án1<9f><98>^OÌÐ¯rÁ3ù<8f>ÄüEÚë-K<92><88>Î­Á^QR
è^\çdò>^GÓ£çBÑNlð¾<8d>ÿ<86>Rô<8c>Ü3<8e>qõ9Trª§0^B~gßl½¿<99>Qg<88>@^@¤@Ä
ß½©Ð<86>^RV<90>ã<9d>7®<92>¨[KG<94>f2OðJÅÅ¬^Pþ^AêJW·ß^EòBþ¤²ÏÄý1údZu}IFF^Vè9|ZQài<81>¸¶90>nåãÖ^V1L¼è6+^RxÐ^^èlÑû7^Yr<9a><¢+ønàØ<9f>Eä<93>^BWÌ7²)²èzG^Tj3RµÒÒ°Ã<87>D&<82>±Z1§ÂlÆö
^M^G<9e><82>^LhÖõÚ¾W×^Z^B<89>»<À©<8e>GÃÚêQx[sM¿<92>^Ef <87>!Ú^NV<9e>9NU^[^?êV<81>I;V^]Ú0^X<93><9a>^S^M°ì5<8d>çO=/M<84>ßj>{V]ÌY¹·^ZÎcÉnå^L$¤®ÍBf¥>ØÐ<81>¸Î^HN^YÛSe0Ë^U^U^G÷}Þ<98>ý<96>^Wfûª+ó²Ê.<80>{<93>ù6_^Ol<9a>Ñ_ö<99>¾6öÊë}ú{Af)ÕØù^A<8b>^]ã[C^Y0<82>^C<94>Ep<81>^RGeafp%<9b>^[ô}$<8e>)ße<8b>Å<86>±º18<8d>Þ7<8d>Òxf<89>\#fÚ^M <9f><80>À¦+(^?^BÐq<83>u<8a>W³ð<92>^](_W<8e>/2<9d>òÙÿNdãz[^Z5Ëz»á^S`÷^H{ø:3óbçm«ÃKÒ:ö®^Z^C<jÓäz6<94>.\q?ÛfnK<90>I^O^O»Õ^Dê<80>± <91>^"$L<9d>ì^SÄËH0³Í2
^Ck0ÒheL<97><9d>
ë^U^EbE^[^[Û4^B<96>N#t^T^[^X^Cz6@£uV;Úàu½¤^W¯2]Hù<99>H®-adÅ6'<80>Ë1÷ÜÜ£^Gèö^Ç<93>ãp<9f>L@#ûf<91>^N<95>^F<93><9e>^<9d>-3,*^M·^HFË    É<83>&^]ñ<94>^KHÑã<8b>1À:¨å|<89>ULµZ·a|Öi
<9b>A *ÖÍ<®#<8d>30PøÄìVÑâêúº)LZ<99>ÿ^HÜçnËðäª9&RxOñv. 02<9f>°¨<9<9b>}<8e><9c>\\<84>Õ^Wy<91>^F+¸^T^[¥1<9c>¹^Ly<86>[ÏUN}Þs<80>VB<8f>À©û^gßÊ]yk^VÝ^]ßOý<8e> mc2r8+æÊ<80>£Üº'!¾t®«C^Sc+ù^HÌÏkÍ<83>^V<90>äÀNÌY<8c>£^EyÂKÅ¿vS^Zõx<92>·ÎÉ<9b>8è^CCÄÏ^?ðJ§^Bål;úÖlÂÌ6Æ¢Åb6<83>~^N<92>÷ß8,^[½:^T<87>¸ÈP^]¥/ô!¯^Rä%U¢@U_Ç^D[<86>ñG^XE=^E><94>Ã<88>´M7<9a>­å¹Ñ<98>^TêIh=^R0L2<C<84>Üß7¤^Vï{^?$<87><82>-^PÇÁ)^S·<87><9c>^<97>¦îÚ^XÎ¦±b<9a><8d>ÇX£<88><81>/^F~«ø<9f>±^VÃwÎjw^L\<98>u Ø ;Ôûc£^Ox<9f>ÌØÏØhçøÊ/°ì|t^Y<93>=B ZÀI^V^L
M¡lLúT´£<8c><8b>@<93>ª²A^X^@¡dýÑ<92>HÓh^X¦^UÏÄ¼Ò4^[ <97>MmÌÎô*KAý¥)EÌt$ÿ^_^\<95>´^MÄ^\ÅÌ<98>ÚâèîãÚMA<88>^K^B^@LïcâQ!ÁÐ<91>U¡xÝJù
B<98><83>Ýù^Xa&&úw^T¦^Éå}^D¾?mÝ0â<83>X<89>Ù*<82>J^R{÷è3Y¹ö\Îî|È÷<96>'ú6^PÛ^^ÅúÿP`4x<89>X^O<87><95>çäÈ30ù<82>Ï£BK4<98>
^Q-»©<91>T¯ª(A<8d>èbã<99>?öúÂ÷}æuâÊ¢Ø^Zyd<83>û"<9b>43aÉþ^Vó<8f>Ð<9d>2P½är^TÞ^U*éÃÜ¼·Gñ°Jô<8e>º×ÒªÓj^L^E<9a>^Q1<80>^Hr³wÏ2^[^]]róÜþw÷c^U¢äÑ`[ó$^Eí±Ì<84>m¡÷<8a>Ë^A<8e>^@é
Já^Q9Ô-<92>Hv`^Q<82>D"j¡®^?Òp^]Ù#úÌs¥k4]^@½T^R¥® Ú<84>^Dsy¶Ó<89><9b><85>°êÆ·ºÚb©Ø^H^P ã,î=Û÷ÊÄ^Sp(<8c>S^Qe<9f>òm³^?Ò¾Û<8a>|)^8,:;\s<90>æÏéíólLo^V´fbV(Þ'<qcµ^[b<81>ù    <^RØoÊðí@^Zj!5²¥<98>æ^P<9e>^CRvfÝbhÌ^@Í=WU5¥^YÒû]jk²÷B<8f>^CYÎð1yvÓ.<8c>Ô<9f><96>m^ZA7c<88>z<9a>QkTSo¦<99>>d¥^M4<80>^XD^A,PÕz²;<94>^]<93>ø÷b?<8d>I7<89><97>kì¿>=qÏN^"¡Þáö^OPV^Dõ÷¼;û¿<89>Å=Íôá^\k)Yg^S<9e>oÐ2NÙÖÎ×¾Dø};x^Wð~Í[N!|<üDZÖÛ)<åÛ@H<81>¥c¶D0<82>^CU^F *<86>H<86>÷^M^A^G^A <82>^CF^D<82>^CB0<82>^C>0<82>^C:^F^K*<86>H<86>÷^M^A^L
^A^B <82>^B¦0<82>^B¢0^\^F
*<86>H<86>÷^M^A^L^A^C0^N^D^H^G_cA¦<9c>^Pì^B^B^H^@^D<82>^B<80>M#<íø~`^?ó^B,wÔ¼Í»,:Ó<89>ùâ2¸¦8<99>ðÉ{^_`?í;^L@d©×)°^[<94>§ÌËKOÂ<9d>C:<%Ü<90>ÿ I<9a>î\É    ^Lq<94>mëi\^KCÁ<93>,§<84>á<97><85><85>Â^A0ä^RMlAÖË#(!jy^_ Jß&ýqßÒ^^9øÏU¥¸ïG @<9e>   F{H^EåÙfßÁW#t<9c>>iJ\_Ý9á^AÓr^Vx¯^^+pº<81>^?csPÎ|ûybé^H^P/¾ðË}Ôwû·^Z¿õ^Q<88>&^OÈkJ^Cÿ^YLëªÁE÷;tÉ
<82>I^AjX<84>ª9^S^MXÔÈt&Í<82>^_ï&4@<92>0^?;tK;^@<91>úÛ¨z<94><86><`<9a>·^O0í,<85>ÊëÔàG§Ó#"<80>Ù #<8b>l<9d>;ÞUn<84>þ<8e>^OÈü^XÎ^PÌ~ÅÃ<^A<8c><8e>Ýy'zCÌ<9c>«<94>4³VF<hß2<8d>äKæ<88>J«<9c>»½P©^XÞbLZ^@^M?Ky<95>^[q}NÒ¬yP-ñmÓ^K<9a><8a>^KÊC^Fu^Süé^^Ì<8f><99>^Tó&üëÓE~C=<85>#of7^O_^R
<9e>ë±ÌóVn#;N]±³^[5Y°<89>Ðe±<8a>^[ý'ÄÅR!ÿ)  ¸Oé<9f>ÑsÄ0Ï:üá#¡^Ww+¦^ANè<96>P^KùÀ<81>^P¢<9d>#¸<88><97>:<84><95>^@+^U^SIÁ¶<89>Å7ìÀçõæMX<8d>^V^G@=<89>°^E Ù6<9b><96>Év0<91>[ë^Mx^V'°^AIåÓüïðÈ\í=Â^MN^L$x.ÿÑZevÀöâ¡ü<9d>^H¼d<92>
u<9e>GB5Â<9f><93>k^_<91>k^Ku@<8a>÷@S<87> z/1    úýfÌ^D^L<95>Ðz<À½)0ÔÝòJßf<9d>A^Aìø+<93>µ^Zn^BI"VH<9c>-<91>yÏ^P^V^SM4¥úµ<8a>Y7æ«<91>éµË¼¸_^K<96>Ú<87>iw8sÖ^K^UÀ<9f>Ìå^Po<8b>ãjçLæÃP^GUÇ<93>^H<91>^Q<81>$+d^»º<9a>Xè<9f>ø1<81><80>0#^F    *<86>H<86>÷^M^A ^U1^V^D^TÖ^H|^Vê \Ñ^T<9f>éMC=^A^Mª"{Ø0Y^F   *<86>H<86>÷^M^A ^T1L^^J^@J^@u^@n^@q^@u^@a^@n^@ ^@Y^@i^@/^@e^@m^@a^@i^@l^@A^@d^@d^@r^@e^@s^@s^@=^@1^@3^@0^@1^@9^@@^@1^@6^@3^@.^@c^@o^@m010!0 ^F^E+^N^C^B^Z^E^@^D^T<86>£Òr²'þH´¸þ8éò]<8f>^Q´Ç4^D^H<89>ÿ <8a>>§^B"^B^B^H^@

```

:



```
root@hspEdu01 /u/l/ssl.ca-0.1# vim random-bits 
root@hspEdu01 /u/l/ssl.ca-0.1# cat ca.db.index 
V	230607113149Z		01	unknown	/C=CA/ST=XiAn/L=ShanXi/O=SDiji/OU=Siji/CN=www.yjqlovemhy.com/emailAddress=2696974822@qq.com
V	230607113901Z		02	unknown	/CN=Junquan Yi/emailAddress=13019@163.com
root@hspEdu01 /u/l/ssl.ca-0.1# cat ca.db.index.attr 
unique_subject = yes
root@hspEdu01 /u/l/ssl.ca-0.1# cat ca.db.index.attr.old 
unique_subject = yes
root@hspEdu01 /u/l/ssl.ca-0.1# cat ca.db.serial 
03
```


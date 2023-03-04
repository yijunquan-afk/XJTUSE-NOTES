首先，您必须创建一个自签名的根证书颁发机构。这可以通过运行"new-root- cash .sh"来完成。请记住此密钥的密码。



运行“new-server-cert.sh”创建web服务器的私钥和公钥。脚本将继续输入证书信息。我们称之为“证书签名请求(csr)”。格式为x509。需要执行“sign-server-cert.sh”命令批准并使用根密钥进行签名。



执行命令new-user-cert.sh生成用户证书。您可以使用此文件在使用MS Outlook Express或Netscape浏览器时签名和加密电子邮件。



需要执行“sign-user-cert.sh”命令进行签名。最后，运行“p12.sh”将私钥、签名密钥与CA的公钥一起打包到一个文件中。p12”扩展。你可以把它下载到你的电脑上，然后导入。

这个自述文件将解释如何使用这个方便的“工具包”来获得您的；使用openssl更容易完成证书创建和签名任务。你也可以修改脚本以满足您的需要。

这些脚本是专门设计来处理web服务器证书的生成和用户证书生成(S/MIME)。然后剧本让你把证书打包成pkcs12格式，供你下载至网景浏览器沟通使用。

大多数代码都借鉴自RSE的Mod-SSL包。我只“扩展”它可能使它更容易使用。





1. 使用Apache作为核心Web服务器
2. 使用OpenSSL作为核心SSL加密
3. 使用Mod-SSL粘合Apache和OpenSSL

4. 使用带有LDAP功能的PHP3来完成我的编程任务

5. 使用Netscape Directory Server 4.11作为后端数据库的目的(可以是PostgreSQL或MySQL)


我不会详细介绍Apache、OpenSSL、Mod-SSL、PHP3和LDAPSDK的编译…Apache和Mod-SSL的文档特别有用。好几个月前RSE已经完成了伟大的工作!





Requirement to run this program

\-------------------------------



\1. Unix system that can run "sh". I prefer Linux. But I write this on

  Solaris 8 due to work requirement. Yes, it runs on Linux.



\2. Working openssl 0.9.4 or above. I am using 0.95a. Yes, it runs on Linux.



To test the certification, you need,



\1. Web Server that use the server certificate



\2. Netscape Communicator 4.5 and above that use the S/MIME certificate.

  I have tested IE5 on Windows 2000. Also the Outlook Express. Work.





How to use it

\-------------



脚本是基于这个概念编写的:

> 您有一个自签名的根证书(根CA)，用于对将要生成的所有内容进行签名。
>
> 您可以生成Apache+Mod-SSL使用的web服务器证书。
>
> 您可以生成S/MIME密钥供Netscape浏览器使用。



首先，您必须创建一个自签名的根证书颁发机构。这可以通过运行"new-root- cash .sh"来完成。记住这个的密码。

运行“new-server-cert.sh”创建web服务器的私钥和公钥。脚本将继续输入证书信息。

我们称之为“证书签名请求(csr)”。格式为x509。您需要运行“sign-server-cert.sh”来批准和签名您的根密钥。

执行命令new-user-cert.sh生成用户证书。您可以使用此文件在使用MS Outlook Express或Netscape浏览器时签名和加密电子邮件。

需要执行“sign-user-cert.sh”命令进行签名。最后，运行“p12.sh”将私钥、签名密钥与CA的公钥一起打包到一个文件中。p12”扩展。你可以把它下载到你的电脑上，然后导入。

我希望解释是非常直接的。



Example Usage

\-------------



## Create the self-signed Root CA key创建自签名根CA密钥





\# ./new-root-ca.sh 

No Root CA key round. Generating one

Generating RSA private key, 1024 bit long modulus

....++++++

.............++++++

e is 65537 (0x10001)

Enter PEM pass phrase:

Verifying password - Enter PEM pass phrase:



Self-sign the root CA...

Using configuration from root-ca.conf

Enter PEM pass phrase:

You are about to be asked to enter information that will be incorporated

into your certificate request.

What you are about to enter is what is called a Distinguished Name or a DN.

There are quite a few fields but you can leave some blank

For some fields there will be a default value,

If you enter '.', the field will be left blank.

\-----

Country Name (2 letter code) [MY]:

State or Province Name (full name) [Perak]:

Locality Name (eg, city) [Sitiawan]:

Organization Name (eg, company) [My Directory Sdn Bhd]:

Organizational Unit Name (eg, section) [Certification Services Division]:

Common Name (eg, MD Root CA) []:MD Root CA

Email Address []:md-ca@md.com.my





## 创建服务器证书



\# ./new-server-cert.sh 

Usage: ./new-server-cert.sh <www.domain.com>

\# ./new-server-cert.sh www.md.com.my

No www.md.com.my.key round. Generating one

Generating RSA private key, 1024 bit long modulus

...++++++

......++++++

e is 65537 (0x10001)



Fill in certificate data

Using configuration from server-cert.conf

You are about to be asked to enter information that will be incorporated

into your certificate request.

What you are about to enter is what is called a Distinguished Name or a DN.

There are quite a few fields but you can leave some blank

For some fields there will be a default value,

If you enter '.', the field will be left blank.

\-----

Country Name (2 letter code) [MY]:

State or Province Name (full name) [Perak]:   

Locality Name (eg, city) [Sitiawan]:

Organization Name (eg, company) [My Directory Sdn Bhd]:

Organizational Unit Name (eg, section) [Secure Web Server]:

Common Name (eg, www.domain.com) []:www.md.com.my

Email Address []:info@md.com.my



You may now run ./sign-server-cert.sh to get it signed





## Sign the server cert签署服务器证书





\# ./sign-server-cert.sh 

Usage: ./sign-server-cert.sh <www.domain.com>

\# ./sign-server-cert.sh www.md.com.my

CA signing: www.md.com.my.csr -> www.md.com.my.crt:

Using configuration from ca.config

Enter PEM pass phrase:

Check that the request matches the signature

Signature ok

The Subjects Distinguished Name is as follows

countryName      :PRINTABLE:'MY'

stateOrProvinceName  :PRINTABLE:'Perak'

localityName      :PRINTABLE:'Sitiawan'

organizationName    :PRINTABLE:'My Directory Sdn Bhd'

organizationalUnitName:PRINTABLE:'Secure Web Server'

commonName       :PRINTABLE:'www.md.com.my'

emailAddress      :IA5STRING:'info@md.com.my'

Certificate is to be certified until Apr 24 12:43:27 2001 GMT (365 days)

Sign the certificate? [y/n]:y





1 out of 1 certificate requests certified, commit? [y/n]y

Write out database with 1 new entries

Data Base Updated

CA verifying: www.md.com.my.crt <-> CA cert

www.md.com.my.crt: OK





## Create the user cert创建用户证书





\# ./new-user-cert.sh 

Usage: ./new-user-cert.sh user@email.address.com

\# ./new-user-cert.sh yeak@md.com.my

No yeak@md.com.my.key round. Generating one

Generating RSA private key, 1024 bit long modulus

....................++++++

............++++++

e is 65537 (0x10001)



Fill in certificate data

Using configuration from user-cert.conf

You are about to be asked to enter information that will be incorporated

into your certificate request.

What you are about to enter is what is called a Distinguished Name or a DN.

There are quite a few fields but you can leave some blank

For some fields there will be a default value,

If you enter '.', the field will be left blank.

\-----

Common Name (eg, John Doe) []:Yeak Nai Siew   

Email Address []:yeak@md.com.my



You may now run ./sign-user-cert.sh to get it signed





## Sign the user cert 签署用户证书





\# ./sign-user-cert.sh   

Usage: ./sign-user-cert.sh user@email.address.com

\# ./sign-user-cert.sh yeak@md.com.my

CA signing: yeak@md.com.my.csr -> yeak@md.com.my.crt:

Using configuration from ca.config

Enter PEM pass phrase:

Check that the request matches the signature

Signature ok

The Subjects Distinguished Name is as follows

commonName       :PRINTABLE:'Yeak Nai Siew'

emailAddress      :IA5STRING:'yeak@md.com.my'

Certificate is to be certified until Apr 24 12:53:58 2001 GMT (365 days)

Sign the certificate? [y/n]:y





1 out of 1 certificate requests certified, commit? [y/n]y

Write out database with 1 new entries

Data Base Updated

CA verifying: yeak@md.com.my.crt <-> CA cert

yeak@md.com.my.crt: OK







## Package the user certificate into a single pkcs12 file将用户证书打包到一个pkcs12文件中。





\# ./p12.sh 

Usage: ./p12.sh user@email.address.com

\# ./p12.sh yeak@md.com.my

Enter Export Password:

Verifying password - Enter Export Password:



The certificate for yeak@md.com.my has been collected into a pkcs12 file.

You can download to your browser and import it.





## DONE!





PROBLEM SOLVING

\---------------

I am sure you have problem to even using the script. Yes, I actually need

to improve them first. But see the following first (I know, I know...)



Q. "openssl" not found?



A. You need to have openssl 0.9.4 in your machine. For Red Hat Linux, just

  install the openssl-xxxx.rpm package. On Solaris, make sure your PATH

  environment is set correctly. In my case, I have openssl installed in

  /usr/local/ssl/bin/openssl. So I need to make sure my PATH contain

  "/usr/local/ssl/bin" and export the this PATH variable.



Q. warning, not much extra random data, consider using the -rand option

  14044:error:20.......................... PRNG not seeded:md_rand.c



A. In openssl 0.9.5a version, the random seed (PRNG) is important in order to

  generate any private key. This is a security feature, not a bug. In

  my kit, I actually include the random-bits that I make up. Do not simply

  use it! You should try to modify the "new-root-ca.sh" file, look for

  "-rand random-bits", change it to "-rand /var/adm/wtmpx:/var/adm/messages"

  in order to get your own unique seed. Note, once you successfully make

  the openssl run, it will create a $HOME/.rnd file for future use.



  In Linux, you can use "-rand /dev/urandom". If you use

  "-rand /dev/random", be sure to patiently wait until it start to generate

  something... :-)



Q. where should I install ssl.ca directory?



A. You can put anywhere. I put inside /usr/local/apache/conf where I have

  ssl.key, ssl.crt, ssl.csr... around.



Q. I can't important the p12 files to my Mac or PC!



A. Make sure the file is downloaded as "binary". Windows NT 4 might have

  problem to read the key. Try Windows 2000 or Windows 98.





TODO

\----

Web based certificate management. Need a lot of input from all of you out

there.





CONTACT

\-------

This is my signature (my vcard!)



Yeak Nai Siew    [NIC:NY628] << Mac OS Forever >> << Linux Forever>>

(yeak@md.com.my | yeak@mac.com)          http://www.md.com.my/

e-certificate    http://www.brainbench.com/transcript.jsp?pid=120196

ICQ#: 13391181                 Chief Technology Officer
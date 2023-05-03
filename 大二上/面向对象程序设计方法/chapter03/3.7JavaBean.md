# JavaBean

JavaBean是一种Java语言写成的可重用组件。
所谓javaBean，是指符合如下标准的Java类：

> 类是公共的
> 有一个无参的公共的构造器
> 有属性，且有对应的get、set方法

用户可以使用JavaBean将功能、处理、值、数据库访问和其他任何可以用Java代码创造的对象进行打包，并且其他的开发者可以通过内部的JSP页面、Servlet、其他JavaBean、applet程序或者应用来使用这些对象。用户可以认为JavaBean提供了一种随时随地的复制和粘贴的功能，而不用关心任何改变。

**代码示例**

```java
public class JavaBean {
private String name; // 属性一般定义为private
private int age;
public JavaBean() {
}
public int getAge() {

      return age;
}
public void setAge(int a) {

      age = a;
}
public String getName() {

      return name;
}
public void setName(String n) {

      name = n;
}
}

```
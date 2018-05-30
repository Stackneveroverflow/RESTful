RESTful API初试
==========
---
### 后端
控制器使用@RestController注解声明，隐式默认为每个方法加上@ResponseBody注解,返回的数据不再被视图解析器解析
```Java
@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
```
👆拦截POST方式且数据格式为application/json的/register请求
```Java
public ResponseEntity<User> register(@RequestBody @Valid User user) {
        HttpStatus status = userService.addUser(user) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(user, status);
    }
```
`@RequestBody`接收数据，将JSON转化为对象放入user中
返回一个ResponseEntity包含对象和HTTP状态码,HttpStatus是枚举类,

---
### 前端
* JSON

JSON 是 JS 对象的字符串表示法，它使用文本表示一个 JS 对象的信息，本质是一个字符串。
```javascript
var obj = {a: 'Hello', b: 'World'}; //这是一个对象，注意键名也是可以使用引号包裹的
var json = '{"a": "Hello", "b": "World"}'; //这是一个 JSON 字符串，本质是一个字符串
```
和普通的 JS 数组一样，JSON 表示数组的方式也是使用方括号 []。如：
```javascript
{
    "name": "中国",
    "province": [{
        "name": "黑龙江",
        "cities": {
            "city": ["哈尔滨", "大庆"]
        }
    }, {
        "name": "广东",
        "cities": {
            "city": ["广州", "深圳", "珠海"]
        }
    }, {
        "name": "台湾",
        "cities": {
            "city": ["台北", "高雄"]
        }
    }, {
        "name": "新疆",
        "cities": {
            "city": ["乌鲁木齐"]
        }
    }]
}
```

json2.js一个json插件，它包含
`JSON.stringify(value, replacer, space)`
`JSON.parse(text, reviver)`

要实现从对象转换为 JSON 字符串，使用 JSON.stringify() 方法：
```javascript
var json = JSON.stringify({a: 'Hello', b: 'World'}); //结果是 '{"a": "Hello", "b": "World"}'
```
要实现从 JSON 转换为对象，使用 JSON.parse() 方法：
```javascript
var obj = JSON.parse('{"a": "Hello", "b": "World"}'); //结果是 {a: 'Hello', b: 'World'}
```
* Ajax

Ajax 即“Asynchronous Javascript And XML”
是一种用于创建快速动态网页的技术,无需重新加载整个网页的情况下，能够更新部分网页的技术. 
通过在后台与服务器进行少量数据交换，Ajax 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
```javascript
$.ajax(
    {
        url: "register",
        //期望得到的返回数据
        dataType: "json",
        //POST方式提交数据到服务器处理
        type: "post",
        //声明发送的内容格式
        contentType: "application/json",
        //异步，默认为true
        async: false,
        //JSON数据，使用插件转为JSON格式
        data: JSON.stringify({
            account: _accountValue,
            password: pwd_md5
        }),
        //返回2XX HTTP状态码进入
        success: function () {
            alert("注册成功");
            tab = $(".account_number").attr('class').split(' ')[0];
            checkBtn();
            checkBtn();
            $(".account_number").addClass("on");
            $(".signup").removeClass("on");
            $(".form2").addClass("hide");
            $(".form1").removeClass("hide");
            $("#num").val(_accountValue);
            $("#pass").val("");
            $("#passport").val("");
            $("#passport2").val("");
            $("#account").val("");
            document.getElementById('pass').focus();
            tab = 'account_number';
        },
        //返回4XX或5XX码进入
        error: function () {
            info1.innerHTML = "账号已存在";
            $("#account").val("");
            document.getElementById('account').focus();
        }
    });

```
---
### 增强
* CORS
全称是”跨域资源共享”（Cross-origin resource sharing）服务端只需添加相关响应头信息，即可实现客户端发出AJAX跨域请求。

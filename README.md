# todo

## 概要

[Jersey](https://jersey.github.io/)を使用したtodo REST APIです。

todoの検索、登録、更新、削除を行うことができます。

## ドキュメント

`apidoc/index.html`に[apidoc](http://apidocjs.com/)を使用して生成したドキュメントがあります。

## 実行方法

#### テストコードの実行
```sh
$ mvn clean test
```

#### 起動
```sh
$ mvn exec:java
```

以下のように表示されれば起動OKです。

```
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building simple-service 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] >>> exec-maven-plugin:1.2.1:java (default-cli) > validate @ simple-service >>>
[INFO]
[INFO] <<< exec-maven-plugin:1.2.1:java (default-cli) < validate @ simple-service <<<
[INFO]
[INFO]
[INFO] --- exec-maven-plugin:1.2.1:java (default-cli) @ simple-service ---
Nov 21, 2017 6:19:44 AM org.glassfish.grizzly.http.server.NetworkListener start
情報: Started listener bound to [localhost:3000]
Nov 21, 2017 6:19:44 AM org.glassfish.grizzly.http.server.HttpServer start
情報: [HttpServer] Started.
Jersey app started with WADL available at http://localhost:3000/application.wadl
Hit enter to stop it...
```

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
[INFO] Building todo 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] >>> exec-maven-plugin:1.2.1:java (default-cli) > validate @ todo >>>
[INFO]
[INFO] <<< exec-maven-plugin:1.2.1:java (default-cli) < validate @ todo <<<
[INFO]
[INFO]
[INFO] --- exec-maven-plugin:1.2.1:java (default-cli) @ todo ---
Nov 23, 2017 7:03:42 AM org.glassfish.grizzly.http.server.NetworkListener start
情報: Started listener bound to [localhost:3000]
Nov 23, 2017 7:03:42 AM org.glassfish.grizzly.http.server.HttpServer start
情報: [HttpServer] Started.
Jersey app started with WADL available at http://localhost:3000/application.wadl
Hit enter to stop it...
```

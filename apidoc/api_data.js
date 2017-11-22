define({ "api": [  {    "type": "post",    "url": "/todos/",    "title": "Create",    "name": "CreateTodo",    "group": "Todo",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "title",            "description": "<p>todoのタイトル.</p>"          },          {            "group": "Parameter",            "type": "String",            "optional": true,            "field": "contents",            "description": "<p>todoの内容.</p>"          }        ]      },      "examples": [        {          "title": "Request-Example: ",          "content": "{ \"title\": \"example todo\",\n                 \"contents\": \"This is an example content\" }",          "type": "json"        }      ]    },    "success": {      "examples": [        {          "title": "Success-Response: ",          "content": "HTTP/1.1 201 Created \n{\n\t\"id\": \"0\", \"title\": \"todo\", \"contents\": \"todoの内容\"\n}",          "type": "json"        }      ]    },    "error": {      "fields": {        "Error 400": [          {            "group": "Error 400",            "optional": false,            "field": "TitleRequired",            "description": "<p>タイトルが設定されていない.</p>"          },          {            "group": "Error 400",            "optional": false,            "field": "InvalidJsonSyntax",            "description": "<p>JSONの文法が不正.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 400 Bad Request\n{\n  \"error\": \"TitleRequired\"\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./src/main/java/com/example/resources/TodosResource.java",    "groupTitle": "Todo"  },  {    "type": "delete",    "url": "/todos/{todoid}",    "title": "Delete",    "name": "DeleteTodo",    "group": "Todo",    "version": "0.0.0",    "filename": "./src/main/java/com/example/resources/TodoResource.java",    "groupTitle": "Todo",    "error": {      "fields": {        "Error 404": [          {            "group": "Error 404",            "optional": false,            "field": "TodoNotFound",            "description": "<p>Todoが存在しない.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 404 Not Found\n{\n  \"error\": \"TodoNotFound\"\n}",          "type": "json"        }      ]    }  },  {    "type": "get",    "url": "/todos/{todoid}",    "title": "Get",    "name": "GetTodo",    "group": "Todo",    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "id",            "description": "<p>Todoのid.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "title",            "description": "<p>Todoのタイトル.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "contents",            "description": "<p>Todoの内容.</p>"          }        ]      },      "examples": [        {          "title": "Success-Response: ",          "content": "HTTP/1.1 200 OK \n{\n\t\"id\": \"0\", \"title\": \"todo\", \"contents\": \"todoの内容\"\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./src/main/java/com/example/resources/TodoResource.java",    "groupTitle": "Todo",    "error": {      "fields": {        "Error 404": [          {            "group": "Error 404",            "optional": false,            "field": "TodoNotFound",            "description": "<p>Todoが存在しない.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 404 Not Found\n{\n  \"error\": \"TodoNotFound\"\n}",          "type": "json"        }      ]    }  },  {    "type": "get",    "url": "/todos/search",    "title": "Search",    "name": "SearchTodo",    "group": "Todo",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "q",            "description": "<p>検索文字列.</p>"          }        ]      }    },    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "id",            "description": "<p>Todoのid.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "title",            "description": "<p>Todoのタイトル.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "contents",            "description": "<p>Todoの内容.</p>"          }        ]      },      "examples": [        {          "title": "Success-Response: ",          "content": "HTTP/1.1 200 OK \n{\n\t[\"id\": \"0\", \"title\": \"todo\", \"contents\": \"todoの内容\"],\n [\"id\": \"1\", \"title\": \"more todo\", \"contents\": \"todoの内容\"]\n}",          "type": "json"        }      ]    },    "error": {      "fields": {        "Error 400": [          {            "group": "Error 400",            "optional": false,            "field": "QueryRequired",            "description": "<p>クエリが設定されていない.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 400 Bad Request\n{\n  \"error\": \"QueryRequired\"\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./src/main/java/com/example/resources/TodosResource.java",    "groupTitle": "Todo"  },  {    "type": "put",    "url": "/todos/{todoid}",    "title": "Update",    "name": "UpdateTodo",    "group": "Todo",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": true,            "field": "title",            "description": "<p>todoのタイトル.</p>"          },          {            "group": "Parameter",            "type": "String",            "optional": true,            "field": "contents",            "description": "<p>todoの内容.</p>"          }        ]      },      "examples": [        {          "title": "Request-Example: ",          "content": "\t\t\t\t\t{ \"title\": \"example todo\",\n                 \"contents\": \"This is an example content\" }",          "type": "json"        }      ]    },    "error": {      "fields": {        "Error 400": [          {            "group": "Error 400",            "optional": false,            "field": "InvalidJsonSyntax",            "description": "<p>JSONの文法が不正.</p>"          }        ],        "Error 404": [          {            "group": "Error 404",            "optional": false,            "field": "TodoNotFound",            "description": "<p>Todoが存在しない.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 404 Not Found\n{\n  \"error\": \"TodoNotFound\"\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./src/main/java/com/example/resources/TodoResource.java",    "groupTitle": "Todo"  },  {    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "optional": false,            "field": "varname1",            "description": "<p>No type.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "varname2",            "description": "<p>With type.</p>"          }        ]      }    },    "type": "",    "url": "",    "version": "0.0.0",    "filename": "./apidoc/main.js",    "group": "_Users_kn1kn1_Documents_workspace4_4sr2_todo_apidoc_main_js",    "groupTitle": "_Users_kn1kn1_Documents_workspace4_4sr2_todo_apidoc_main_js",    "name": ""  }] });

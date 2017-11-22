package com.example.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.example.models.Todo;
import com.example.models.Todos;

@Path("todos")
public class TodosResource {

	@GET
	public Response getTodos() throws JSONException {
		return Response.ok().build();
	}

	/**
	 * @api {post} /todos/ Create
	 * @apiName CreateTodo
	 * @apiGroup Todo
	 *
	 * @apiParam {String} title todoのタイトル.
	 * @apiParam {String} [contents] todoの内容.
	 * 
	 * @apiParamExample {json} Request-Example: 
	 * { "title": "example todo",
	 *                  "contents": "This is an example content" }
	 *                  
	 * @apiError (Error 400) TitleRequired タイトルが設定されていない.
	 * @apiError (Error 400) InvalidJsonSyntax JSONの文法が不正.
	 *
	 * @apiErrorExample  Response (example):
	 *     HTTP/1.1 400 Bad Request
	 *     {
	 *       "error": "TitleRequired"
	 *     }
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTodo(String message) {
		System.out.println("message: " + message);
		JSONObject json = null;
		try {
			json = new JSONObject(message);
		} catch (JSONException e) {
			String m = "error: InvalidJsonSyntax";
			return Response.status(Status.BAD_REQUEST).entity(m).build();
		}
		Todo todo = new Todo();
		try {
			String title = json.getString("title");
			todo.setTitle(title);
		} catch (JSONException e) {
			String m = "error: TitleRequired";
			return Response.status(Status.BAD_REQUEST).entity(m).build();
		}
		try {
			String contents = json.getString("contents");
			todo.setContents(contents);
		} catch (JSONException e) {
			// 内容はオプションなのでここは無視する
		}
		Todos.getInstance().addTodo(todo);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * @api {get} /todos/search Search
	 * @apiName SearchTodo
	 * @apiGroup Todo
	 *
	 * @apiParam {String} q 検索文字列.
	 *
	 * @apiSuccess {String} id Todoのid.
	 * @apiSuccess {String} title Todoのタイトル.
	 * @apiSuccess {String} contents Todoの内容.
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * 	["id": "0", "title": "todo", "contents": "todoの内容"],
	 *  ["id": "1", "title": "more todo", "contents": "todoの内容"]
	 * }
	 * 
	 * @apiError (Error 400) QueryRequired クエリが設定されていない.
	 *
	 * @apiErrorExample  Response (example):
	 *     HTTP/1.1 400 Bad Request
	 *     {
	 *       "error": "QueryRequired"
	 *     }
	 */
	@Path("search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchTodo(@Context UriInfo ui) throws JSONException {
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		List<String> queryList = queryParams.get("q");
		if (queryList == null || queryList.isEmpty()) {
			String m = "error: QueryRequired";
			return Response.status(Status.BAD_REQUEST).entity(m).build();
		}
		JSONArray jsonArray = new JSONArray();
		for (String query : queryList) {
			Todo[] todos = Todos.getInstance().searchTodo(query);
			for (Todo todo : todos) {
				try {
					jsonArray.put(TodoResource.toJSON(todo));
				} catch (JSONException e) {
					// 内部で保持しているTodoでJSONExceptionが発生することは考えにくい
					continue;
				}
			}
		}
		String message = jsonArray.toString();
		return Response.ok(message).build();
	}

	@Path("{todoid}")
	public TodoResource getTodoResource(@PathParam("todoid") String todoid) {
		return new TodoResource(todoid);
	}
}

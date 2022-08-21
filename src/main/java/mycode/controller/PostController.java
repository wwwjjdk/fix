package mycode.controller;


import com.google.gson.Gson;
import mycode.exceprtion.NotFoundException;
import mycode.model.Post;
import mycode.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

//прием запроса
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    final Gson gson = new Gson();

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.getById(id);
        response.getWriter().print(gson.toJson(data));

    }

    public void save(Reader body, HttpServletResponse response) throws IOException {

        response.setContentType(APPLICATION_JSON);
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        service.removeById(id);
        response.getWriter().printf(gson.toJson("объект с id[%s] удален"), id);

    }

}

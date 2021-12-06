package ba.academy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/diary")
public class DiaryResource {

    public static List<Diary> diaries=new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiary(){

        return Response.ok(diaries).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDiary(Diary diaryInput){
        diaries.add(diaryInput);
        return Response.ok(diaries).build();

    }

    @PUT
    @Path("{id}/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDiary(@PathParam("id") Long id, @PathParam("title") String title){
        diaries=diaries.stream().map(diary -> {
            if(diary.getId().equals(id)){
                diary.setTitle(title);
            }
            return diary;
        }).collect(Collectors.toList());
        return Response.ok(diaries).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteDiary(@PathParam("id") Long id){
        Optional<Diary> diaryToDelete=diaries.stream().filter(diary ->diary.getId().equals(id)).findFirst();
        boolean removed=false;
        if(diaryToDelete.isPresent()){
            removed=diaries.remove(diaryToDelete.get());
        }
        if(removed){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();


    }

}

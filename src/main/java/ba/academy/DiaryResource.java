package ba.academy;

import ba.academy.dto.Diary;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/diary")
@Tag(name = "Diary Resource", description="Diary REST APIs")
public class DiaryResource {

    public static List<Diary> diaries=new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
	summary = "Get Diary",
	description = "Get all diaries inside the list",
            operationId="getDiary"
)
    @APIResponse(
	responseCode = "200",
	description = "Operation completed",
	content = @Content(mediaType = MediaType.APPLICATION_JSON)

)
    public Response getDiary(){

        return Response.ok(diaries).build();

    }

    @GET
    @Path("{/id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response getDiaryById(@PathParam("id") String id){
        Diary diaryInput=diaries.stream().filter(object ->id.equals(object.getId().toString())).findFirst().get();
        return Response.ok(diaryInput).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Create a new diary",
            description = "Create a new diary to add inside the list",
            operationId="createDiary"
    )
    @APIResponse(
            responseCode = "201",
            description = "Diary created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)

    )
    public Response createDiary(@RequestBody(
            description = "Diary to create",
            required = true,
            content=@Content(schema=@Schema(implementation =Diary.class ))
    ) Diary diaryInput){
        diaries.add(diaryInput);
        return Response.status(Response.Status.CREATED).entity(diaries).build();

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Update existing diary",
            description = "Update diary inside list",
            operationId="upadateDiary"
    )
    @APIResponse(
            responseCode = "200",
            description = "Diary updated",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)

    )
    public Response updateDiary(
            @Parameter(
                description = "Diary id",
                required = true
                )
            @PathParam("id") Long id,
            @Parameter(
                description = "Diary object",
                required = true

            )
            Diary diaryInput){

        diaries=diaries.stream().map(diary -> {
            if(diary.getId().toString().equals(id)){
                diary.setTitle(diaryInput.getTitle());
                diary.setCount(diaryInput.getCount());
                diary.setMedicines(diaryInput.getMedicines());
                diary.setMedicineType(diaryInput.getMedicineType());

            }
            return diary;
        }).collect(Collectors.toList());
        return Response.ok(diaries).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Delete existing diary",
            description = "Delete diary inside list",
            operationId="deleteDiary"
    )
    @APIResponse(
            responseCode = "284",
            description = "Diary deleted",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)

    )
    @APIResponse(
            responseCode = "400",
            description = "Diary not valid",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)

    )
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

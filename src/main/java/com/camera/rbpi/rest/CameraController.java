package com.camera.rbpi.rest;

import com.camera.rbpi.schema.CameraCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static com.camera.rbpi.schema.CameraCommand.BASH;

@Slf4j
@RestController
@RequestMapping("/camera/image")
@Tag(name = "Camera Image Controller", description = "Controller to manage camera images")
@SecurityRequirement(name = "BasicAuth")
public class CameraController {

    @Operation(summary = "Click picture using provided configuration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Picture clicked",
                    content = {@Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(implementation = Array.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized, credentials required as basic auth", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PostMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] clickPicture(@Valid @RequestBody CameraCommand cameraCommand) {
        log.info("Command to be executed: [{}]", cameraCommand.generateCommand());
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(BASH, "-c", cameraCommand.generateCommand());
            Process process = processBuilder.start();
            int responseCode = process.waitFor();
            log.info("Command completed with response code [{}], and image file name is: [{}]", responseCode, cameraCommand.getImageFileOutputName());
            FileSystemResource fileSystemResource = new FileSystemResource("/home/rbpi/camera/" + cameraCommand.getImageFileOutputName());
            return IOUtils.toByteArray(fileSystemResource.getInputStream());
        } catch (IOException | InterruptedException e) {
            log.warn("Exception executing click picture: {}", e.getMessage());
            log.trace("Exception stacktrace", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException argumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        argumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

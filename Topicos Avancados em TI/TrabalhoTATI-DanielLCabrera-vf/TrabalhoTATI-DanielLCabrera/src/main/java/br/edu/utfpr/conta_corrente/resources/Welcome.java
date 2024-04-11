package br.edu.utfpr.conta_corrente.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.jsonldjava.utils.JsonUtils;

@RestController
public class Welcome {

    @GetMapping("/")
    public ResponseEntity<Object> json() throws IOException {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("src/myOntology.owl");
            Object jsonObject = JsonUtils.fromInputStream(inputStream);
            return ResponseEntity.ok().body(jsonObject);
        } catch (FileNotFoundException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}

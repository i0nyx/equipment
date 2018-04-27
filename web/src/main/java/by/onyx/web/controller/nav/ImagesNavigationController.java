package by.onyx.web.controller.nav;

import by.onyx.common.service.file.FileService;
import by.onyx.common.util.StringUtilExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@Controller
@RequestMapping(value = "/")
public class ImagesNavigationController {

    private static final Logger log = LoggerFactory.getLogger(ImagesNavigationController.class);

    static final String MEDIA_URL = "control/";

    @Autowired
    private FileService fileService;


    @RequestMapping(value = MEDIA_URL + "**")
    public ResponseEntity<byte[]> handleImageUrl(HttpServletRequest request) throws IOException {

        String uri = request.getRequestURI();
        uri = uri.substring(MEDIA_URL.length());
        String[] urlPartsArray = uri.split("/");
        StringBuilder fsPath = new StringBuilder();
        fsPath.append(fileService.getBaseFolderPath());

        ArrayList<String> urlParts = new ArrayList<String>(Arrays.asList(urlPartsArray));
        Iterator<String> iterator = urlParts.iterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if (StringUtilExt.isNoBlank(s)) {
                fsPath.append(s);
                if (iterator.hasNext()) {
                    fsPath.append(File.separator);
                }
            }
        }

        log.debug("handleImageUrl uri:" + uri + "file:" + fsPath);

        ResponseEntity<byte[]> responseEntity;
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        try {
            Path path = Paths.get(fsPath.toString());
            byte[] imageBytes = Files.readAllBytes(path);
            if (imageBytes != null) {
                responseEntity = new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.CREATED);
                return responseEntity;
            }
        }catch (NoSuchFileException noSuchFileException){
            log.error(noSuchFileException.getMessage());
        }catch (Exception e) {
            log.error("ImagesNavigationController handleImageUrl()", e);
            return null;
        }
        return null;
    }
}

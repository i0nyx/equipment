package by.onyx.common.service.file;

import by.onyx.common.util.StringUtilExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileServiceImpl implements FileService{

    private static final String PROPERTY_NAME_BASE_MEDIA_RESOURCE_FOLDER = "app.media.basefolder";
    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Resource
    private Environment env;


    @NotNull
    public String createFolder(String folderName){
        String path = getBaseFolderPath() + folderName;
        if(StringUtilExt.isNoBlank(path)) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
                log.info("create folder " + path);
            }
        }
        return path;
    }

    @NotNull
    public String getBaseFolderPath() {
        return env.getRequiredProperty(PROPERTY_NAME_BASE_MEDIA_RESOURCE_FOLDER);
    }
}

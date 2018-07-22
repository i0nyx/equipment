package by.onyx.common.service.file;

import by.onyx.common.util.StringUtilExt;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private static final String PROPERTY_NAME_BASE_MEDIA_RESOURCE_FOLDER = "app.media.basefolder";

    @Resource
    private Environment env;


    @NotNull
    public String createFolder(String folderName) {
        String path = getBaseFolderPath() + folderName;
        if (StringUtilExt.isNoBlank(path)) {
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

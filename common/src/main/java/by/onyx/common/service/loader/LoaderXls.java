package by.onyx.common.service.loader;


import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface LoaderXls {

    OutputStream generateXlsFile(OutputStream out);

    void uploadXlsFile(MultipartFile file);
}

package blogSpring.core.utilities.externalServiceAdapters.imageUploader;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import blogSpring.core.utilities.results.DataResult;

public interface CloudinaryService {
	
	@SuppressWarnings("rawtypes")
	DataResult<Map> upload(MultipartFile imageFile);
	
//	DataResult<Map> delete(String id) throws IOException;

}

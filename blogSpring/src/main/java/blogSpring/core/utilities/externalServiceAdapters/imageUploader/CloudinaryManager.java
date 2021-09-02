	package blogSpring.core.utilities.externalServiceAdapters.imageUploader;

	import java.io.IOException;
	import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
	import org.springframework.web.multipart.MultipartFile;

	import com.cloudinary.*;
	import com.cloudinary.utils.ObjectUtils;

	import blogSpring.core.utilities.results.DataResult;
	import blogSpring.core.utilities.results.ErrorDataResult;
	import blogSpring.core.utilities.results.SuccessDataResult;

	@Service
	@Component
	public class CloudinaryManager implements CloudinaryService {
		
		private Cloudinary cloudinary;
		
		public CloudinaryManager() {

			this.cloudinary = new Cloudinary(ObjectUtils.asMap(
					"cloud_name","dwsq1lxha",
					"api_key", "637772425194474",
					"api_secret","AlMGdtKHQ7BtLBskmQeiJ3Zp0Rg",
					"secure", true));
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public DataResult<Map> upload(MultipartFile imageFile) {
			try {
				Map<String, String> resultMap =(Map<String, String>) cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
				return new SuccessDataResult<Map>(resultMap);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ErrorDataResult<Map>();
		}

	}
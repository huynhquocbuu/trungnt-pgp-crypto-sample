package hds.crypto.pgp;

import hds.crypto.pgp.service.PGPHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Slf4j
@SpringBootApplication
public class MomoApplication {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = SpringApplication.run(MomoApplication.class, args);
		AppConfig appConfig = context.getBean(AppConfig.class);
		log.info("app.config.location = " + appConfig.getConfigLocation());
		PGPHelper.init(appConfig.getHdsPrivateKeyPath(), appConfig.getHdsPublicKeyPath(), appConfig.getHdsPassword());

	}

	@Component
	class AppConfig {
		@Value("${crypto.decrypt.pgp.rsa.hds.private-key}")
		private String hdsPrivateKeyPath;
		@Value("${crypto.encrypt.pgp.rsa.hds.public-key}")
		private String hdsPublicKeyPath;
		@Value("${crypto.decrypt.pgp.hds.password}")
		private String hdsPassword;

		@Value("${crypto.decrypt.pgp.rsa.momo.private-key}")
		private String momoPrivateKeyPath;
		@Value("${crypto.encrypt.pgp.rsa.momo.public-key}")
		private String momoPublicKeyPath;

		@Value("${app.config.location}")
		private String configLocation;

		public String getHdsPrivateKeyPath() {
			return hdsPrivateKeyPath;
		}

		public String getHdsPublicKeyPath() {
			return hdsPublicKeyPath;
		}

		public String getConfigLocation() {
			return configLocation;
		}
		public String getHdsPassword() {
			return hdsPassword;
		}

		public String getMomoPrivateKeyPath(){
			return momoPrivateKeyPath;
		}

		public String getMomoPublicKeyPath(){
			return momoPublicKeyPath;
		}
	}

}

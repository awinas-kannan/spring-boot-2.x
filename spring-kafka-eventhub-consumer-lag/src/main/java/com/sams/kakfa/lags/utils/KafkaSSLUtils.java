package com.sams.kakfa.lags.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaSSLUtils {

	private KafkaSSLUtils() {
	}

	/**
	 * Method to read truststore.txt/keystore.txt from secrets and write to
	 * truststore.jks/keystore.jks file in tmp directory
	 *
	 * @return file location
	 */
	public static Object decodeBase64TruststoreAndWriteToTmpFile(byte[] truststoreLocation, String truststoreFilename) {
		byte[] truststore = null;

		try {
			log.info("trustFileName: {}", truststoreLocation);
			truststore = Base64.getDecoder().decode(truststoreLocation);
		} catch (Exception e) {
			log.error("error reading file: {} | {} | {}", e.getClass().getCanonicalName(), e.getMessage(),
					e.getCause());
			e.printStackTrace();
			return "";
		}

		File file = new File(truststoreFilename);
		log.info("Truststore File Location: {}", truststoreFilename);
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(truststore);
		} catch (Exception e) {
			log.error("error writing file: {} | {} | {}", e.getClass().getCanonicalName(), e.getMessage(),
					e.getCause());
			return "";
		}
		return truststoreFilename;
	}
	
	public static Object decodeBase64TruststoreAndWriteToTmpFile(String truststoreLocation, String truststoreFilename) {
		byte[] truststore = null;

		try {
			log.info("trustFileName: {}", truststoreLocation);
			truststore = Base64.getDecoder().decode(truststoreLocation);
		} catch (Exception e) {
			log.error("error reading file: {} | {} | {}", e.getClass().getCanonicalName(), e.getMessage(),
					e.getCause());
			e.printStackTrace();
			return "";
		}

		File file = new File(truststoreFilename);
		log.info("Truststore File Location: {}", truststoreFilename);
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(truststore);
		} catch (Exception e) {
			log.error("error writing file: {} | {} | {}", e.getClass().getCanonicalName(), e.getMessage(),
					e.getCause());
			return "";
		}
		return truststoreFilename;
	}
}